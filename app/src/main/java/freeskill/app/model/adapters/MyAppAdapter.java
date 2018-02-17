package freeskill.app.model.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import freeskill.app.R;
import freeskill.app.model.Profile;

/**
 * Created by Florian on 21/12/2017.
 */

public class MyAppAdapter extends ArrayAdapter<Profile> {

    private Context context;
    private ArrayList<Profile> objects;
    private HashMap<Integer, Bitmap> images;

    public HashMap<Integer, Bitmap> getImages() {
        return images;
    }

    public MyAppAdapter(@NonNull Context context, int resource, ArrayList<Profile> objects, HashMap<Integer, Bitmap> images) {
        super(context, resource, objects);
        this.objects = objects;
        this.context = context;
        this.images = images;
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


    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        }
        CardViewHolder viewHolder = (CardViewHolder) convertView.getTag();
        if (viewHolder == null) {
            viewHolder = new CardViewHolder();
            viewHolder.firstName = (TextView) convertView.findViewById(R.id.firstName);
            viewHolder.mark = (RatingBar) convertView.findViewById(R.id.mark);
            viewHolder.description = (TextView) convertView.findViewById(R.id.descriptionText);
            viewHolder.tags_share = (TextView) convertView.findViewById(R.id.tag_share_content);
            viewHolder.tags_discover = (TextView) convertView.findViewById(R.id.tag_discover_content);
            viewHolder.imageProfile = (ImageView) convertView.findViewById(R.id.imageView);
            convertView.setTag(viewHolder);
        }
        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        Profile profile = getItem(position);

        //fill the view
        viewHolder.firstName.setText(profile.getFirstname());
        viewHolder.mark.setRating((float) profile.getAverageMark());
        viewHolder.description.setText(profile.getDescription());
        viewHolder.tags_share.setText(profile.getTagToString(profile.getTagShareArray()));
        viewHolder.tags_discover.setText(profile.getTagToString(profile.getTagDiscoverArray()));
        //System.out.println(this.images.size());
        //viewHolder.imageProfile.setImageBitmap(profile.getPicture());
        //System.out.println(this.images.size());


        return convertView;
    }

    @Nullable
    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }

    private class CardViewHolder {
        public TextView firstName;
        private RatingBar mark;
        public TextView description;
        public TextView tags_share;
        public TextView tags_discover;
        public ImageView imageProfile;
    }
}
