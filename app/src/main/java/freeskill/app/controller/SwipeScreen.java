package freeskill.app.controller;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.io.InputStream;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import freeskill.app.R;
import freeskill.app.model.CurrentApp;
import freeskill.app.model.query.Judgement;
import freeskill.app.model.adapters.MyAppAdapter;
import freeskill.app.model.Profile;
import freeskill.app.model.query.PostCurrentLocation;
import freeskill.app.model.query.PostJudgement;
import freeskill.app.utils.HttpsTrustManager;

/**
 * Created by Olivier on 12/12/2017.
 */

public class SwipeScreen extends AppCompatActivity {
    public ArrayList<Profile> al;
    private MyAppAdapter myAppArrayAdapter;
    private ArrayAdapter<Bitmap> arrayBitmap;
    private RequestQueue queue;
    private Judgement judgement;
    private CurrentApp currentApp;
    private PostJudgement postJudgement;
    public String meet = "";

    private FusedLocationProviderClient mFusedLocationClient;
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    private double latitude;
    private double longitude;


    @BindView(R.id.frame)
    SwipeFlingAdapterView flingContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.swipe);
        queue = Volley.newRequestQueue(this);
        InputStream caInput = getResources().openRawResource(R.raw.letsencryptauthorityx3);
        HttpsTrustManager https = new HttpsTrustManager(caInput);
        https.allowMySSL();
        ButterKnife.bind(this);

        initLocation();
        getLocation();


        //progressBar=(ProgressBar) findViewById(R.id.progressBar);


        this.al = new ArrayList<>();

        this.currentApp = CurrentApp.getInstance(null);
        this.judgement = this.currentApp.createJudgement(this);
        this.judgement.requestProfiles(this.currentApp.getAccessToken());

        this.postJudgement = new PostJudgement(this.currentApp.getAccessToken(), this.queue, this);


        myAppArrayAdapter = this.judgement.getMyAdapter();
        //arrayBitmap = this.judgement.getAdapter();


        flingContainer.setAdapter(myAppArrayAdapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                // this is the simplest way to delete an object from the Adapter (/AdapterView)
                /*
                Log.d("LIST", "removed object!");
                System.out.println("AV " + al.size());
                al.remove(0);
                System.out.println("AP " + al.size());
                //arrayAdapter.notifyDataSetChanged();
                //myAppArrayAdapter.refreshAdapter(al);
                myAppArrayAdapter.notifyDataSetChanged();
                */
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                //Send judgment request when the card exit on the left!
                //You also have access to the original object.
                //If you want to use it just cast it (String) dataObject
                meet = "PASS";
                SwipeScreen.this.postJudgement.post();
                makeToast(SwipeScreen.this, "Left!");
                al.remove(0);
                myAppArrayAdapter.remove(myAppArrayAdapter.getItem(0));
                myAppArrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                //Send judgment request when the card exit on the right!
                meet = "MEET";
                SwipeScreen.this.postJudgement.post();
                makeToast(SwipeScreen.this, "Right!");
                al.remove(0);
                myAppArrayAdapter.remove(myAppArrayAdapter.getItem(0));
                //myAppArrayAdapter.refreshAdapter(al);
                myAppArrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                // Ask for more data here
                //If the resquest is empty we have to put a message to say that there is no more similar profile available in the range
                judgement.requestProfiles(currentApp.getAccessToken());
                //arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onScroll(float scrollProgressPercent) {
                //View view = flingContainer.getSelectedView();
                //view.findViewById(R.id.item_swipe_right_indicator).setAlpha(scrollProgressPercent < 0 ? -scrollProgressPercent : 0);
                //view.findViewById(R.id.item_swipe_left_indicator).setAlpha(scrollProgressPercent > 0 ? scrollProgressPercent : 0);
            }
        });


        // Optionally add an OnItemClickListener
        flingContainer.setOnItemClickListener(new SwipeFlingAdapterView.OnItemClickListener() {
            @Override
            public void onItemClicked(int itemPosition, Object dataObject) {
                makeToast(SwipeScreen.this, "Clicked!");
            }
        });


        addCustomActionBar();

    }

    public static void makeToast(Context ctx, String s) {
        Toast.makeText(ctx, s, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.meet)
    public void right() {
        /**
         * Trigger the right event manually.
         */
        flingContainer.getTopCardListener().selectRight();
    }

    @OnClick(R.id.pass)
    public void left() {
        flingContainer.getTopCardListener().selectLeft();
    }


    public void addCustomActionBar() {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_actionbar_swipescreen);
        View view = getSupportActionBar().getCustomView();

        ImageView imageViewProfile = this.findViewById(R.id.action_bar_profile);
        imageViewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SwipeScreen.this.startActivity(new Intent(SwipeScreen.this, ProfileScreen.class));
            }
        });

        ImageView imageViewMessage = this.findViewById(R.id.action_bar_message);
        imageViewMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO uncomment when ChatList will be finished
                SwipeScreen.this.startActivity(new Intent(SwipeScreen.this, SwipeScreen.class));
            }
        });
    }

    private void initLocation() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                //Cela signifie que la permission à déjà était
                //demandé et l'utilisateur l'a refusé
                //Vous pouvez aussi expliquer à l'utilisateur pourquoi
                //cette permission est nécessaire et la redemander
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
            } else {
                //Sinon demander la permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
            }
        }

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
    }

    public void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mFusedLocationClient.getLastLocation().addOnSuccessListener(this,
                new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            // Logic to handle location object
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                            System.out.println(latitude);
                            System.out.println(longitude);
                            PostCurrentLocation postCurrentLocation = new PostCurrentLocation(latitude,
                                    longitude);
                            postCurrentLocation.getCurrentLocation(currentApp.getQueue());
                        }
                    }
                });
    }

}
