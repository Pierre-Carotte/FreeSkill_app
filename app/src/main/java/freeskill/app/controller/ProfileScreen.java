package freeskill.app.controller;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;

import freeskill.app.R;

/**
 * Created by Olivier on 06/12/2017.
 */

public class ProfileScreen extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        //encode image(image from drawable) to base64 string
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.profileimage);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT);

        //decode base64 String to image
        byte[] decodedString = Base64.decode(imageString, Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        //show image in ImageView
        ImageView profileImage = findViewById(R.id.imageView);
        profileImage.setImageBitmap(decodedByte);

        /*RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        LayerDrawable stars = (LayerDrawable) ratingBar.getProgressDrawable();
        stars.getDrawable(2).setColorFilter(Color.GRAY, PorterDuff.Mode.SRC_ATOP);*/

    }

    public void edit_tags_share(View view){
        Toast.makeText(ProfileScreen.this,"Edit tags share",Toast.LENGTH_LONG).show();
    }

    public void edit_tags_discover(View view){
        Toast.makeText(ProfileScreen.this,"Edit tags discover",Toast.LENGTH_LONG).show();
    }
}
