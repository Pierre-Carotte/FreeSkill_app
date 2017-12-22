package freeskill.app.controller;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.lorentzos.flingswipe.SwipeFlingAdapterView;

import java.io.InputStream;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import freeskill.app.R;
import freeskill.app.model.CurrentApp;
import freeskill.app.model.Judgement;
import freeskill.app.model.MyAppAdapter;
import freeskill.app.model.Profile;
import freeskill.app.utils.HttpsTrustManager;

/**
 * Created by Olivier on 12/12/2017.
 */

public class SwipeScreen extends AppCompatActivity {
    public ArrayList<Profile> al;
    private ArrayAdapter<String> arrayAdapter;
    private MyAppAdapter myAppArrayAdapter;
    private int i;
    private RequestQueue queue;
    private Judgement judgement;
    private CurrentApp currentApp;




    @BindView(R.id.frame)
    SwipeFlingAdapterView flingContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.swipe);
        queue = Volley.newRequestQueue(this);
        InputStream caInput=getResources().openRawResource(R.raw.letsencryptauthorityx3);
        HttpsTrustManager https = new HttpsTrustManager(caInput);
        https.allowMySSL();
        ButterKnife.bind(this);


        //progressBar=(ProgressBar) findViewById(R.id.progressBar);


        this.al = new ArrayList<>();


        this.currentApp = CurrentApp.getInstance(null);
        this.judgement = this.currentApp.createJudgement(this);
        this.judgement.requestProfiles(this.currentApp.getAccessToken());


        System.out.println( "OUI " + al.size());

        //arrayAdapter = this.judgement.getAdapter();
        myAppArrayAdapter = this.judgement.getMyAdapter();


        //flingContainer.setAdapter(arrayAdapter);
        flingContainer.setAdapter(myAppArrayAdapter);
        flingContainer.setFlingListener(new SwipeFlingAdapterView.onFlingListener() {
            @Override
            public void removeFirstObjectInAdapter() {
                // this is the simplest way to delete an object from the Adapter (/AdapterView)
                Log.d("LIST", "removed object!");
                System.out.println("AV " + al.size());
                al.remove(0);
                System.out.println("AP " + al.size());
                //arrayAdapter.notifyDataSetChanged();
                myAppArrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLeftCardExit(Object dataObject) {
                //Do something on the left!
                //You also have access to the original object.
                //If you want to use it just cast it (String) dataObject
                makeToast(SwipeScreen.this, "Left!");
            }

            @Override
            public void onRightCardExit(Object dataObject) {
                makeToast(SwipeScreen.this, "Right!");
            }

            @Override
            public void onAdapterAboutToEmpty(int itemsInAdapter) {
                // Ask for more data here
                //If the resquest is empty we have to put a message to say that there is no more similar profile available in the range
                //judgement.requestProfiles(currentApp.getAccessToken());
               //al.add("VIDE ".concat(String.valueOf(i)));
               //arrayAdapter.notifyDataSetChanged();
                //Log.d("LIST", "notified");
                //i++;
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

    }

    static void makeToast(Context ctx, String s){
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




}