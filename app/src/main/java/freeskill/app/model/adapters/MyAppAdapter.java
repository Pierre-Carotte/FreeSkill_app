package freeskill.app.model.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import freeskill.app.R;
import freeskill.app.controller.ProfileScreen;
import freeskill.app.controller.SwipeScreen;
import freeskill.app.model.DataConnection;
import freeskill.app.model.Profile;
import freeskill.app.model.query.Judgement;
import freeskill.app.utils.Constants;

/**
 * Created by Florian on 21/12/2017.
 */

public class MyAppAdapter extends ArrayAdapter<Profile>{

    private Context context;
    private ArrayList<Profile> objects;



    public MyAppAdapter(@NonNull Context context, int resource, ArrayList<Profile> objects) {
        super(context, resource,objects);
        this.objects = objects;
        this.context = context;
    }


    @Override
    public int getCount() {
        return objects.size();
    }

    @Nullable
    @Override
    public Profile getItem(int position) {
        return objects.get(position);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @SuppressLint("ClickableViewAccessibility")
    public View getView (int position, View convertView, ViewGroup parent ){
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item,parent, false);
        }
        CardViewHolder viewHolder = (CardViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new CardViewHolder();
            viewHolder.firstName = (TextView) convertView.findViewById(R.id.firstName);
            viewHolder.mark = (RatingBar) convertView.findViewById(R.id.mark);
            viewHolder.description = (TextView) convertView.findViewById(R.id.descriptionText);
            viewHolder.tags_share = (TextView) convertView.findViewById(R.id.tag_share_content);
            viewHolder.tags_discover = (TextView) convertView.findViewById(R.id.tag_discover_content);
            viewHolder.imageProfile = (ImageView) convertView.findViewById(R.id.imageView);
            viewHolder.distance = convertView.findViewById(R.id.distance);
            convertView.setTag(viewHolder);
        }
        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        Profile profile = getItem(position);

        //Put it in a class ?
        String urlImage = Constants.API.GetImage.URI + profile.getId();
        GlideUrl glideUrl = new GlideUrl(urlImage, new LazyHeaders.Builder()
                .addHeader(Constants.General.KEY_ACCESS_TOKEN, DataConnection.getInstance().getJWT())
                .build());

        Glide.with(context)
                .load(glideUrl)
                //.centerCrop()
                .into(viewHolder.imageProfile);


        //fill the view
        viewHolder.firstName.setText(profile.getFirstname());
        viewHolder.mark.setRating((float) profile.getAverageMark());
        viewHolder.description.setText(profile.getDescription());
        viewHolder.tags_share.setText(profile.getTagToString(profile.getTagShareArray()));
        viewHolder.tags_discover.setText(profile.getTagToString(profile.getTagDiscoverArray()));
        if(profile.getDistance() == 0){
            viewHolder.distance.setText("A moins de " + (profile.getDistance()+1) + " km de vous");
        }else{
            viewHolder.distance.setText("A " + profile.getDistance() + "km de vous");
        }
        //viewHolder.imageProfile.setImageBitmap(profile.getPicture());


        return convertView;
    }

    @Nullable
    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }

    private class CardViewHolder{
        public TextView firstName;
        public TextView distance;
        public RatingBar mark;
        public TextView description;
        public TextView tags_share;
        public TextView tags_discover;
        public ImageView imageProfile;
    }
}