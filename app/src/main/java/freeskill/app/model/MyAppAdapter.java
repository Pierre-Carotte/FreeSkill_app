package freeskill.app.model;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import freeskill.app.R;

/**
 * Created by Florian on 21/12/2017.
 */

public class MyAppAdapter extends BaseAdapter{

    private Context context;
    private ArrayList<Profile> objects;

    public MyAppAdapter(@NonNull Context context, @NonNull ArrayList<Profile> objects) {
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


    public View getView (int position, View convertView, ViewGroup parent ){
        View rowView = convertView;

        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item,parent, false);
        }
        CardViewHolder viewHolder = (CardViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new CardViewHolder();
            viewHolder.firstName = (TextView) convertView.findViewById(R.id.firstName);
            viewHolder.description = (TextView) convertView.findViewById(R.id.descriptionText);
            viewHolder.tags_share = (TextView) convertView.findViewById(R.id.tag_share_content);
            viewHolder.tags_discover = (TextView) convertView.findViewById(R.id.tag_discover_content);
            //viewHolder.avatar = (ImageView) convertView.findViewById(R.id.avatar);
            convertView.setTag(viewHolder);
        }
        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        Profile profile = getItem(position);

        //fill the view
        viewHolder.firstName.setText(profile.getFirstname());
        viewHolder.description.setText(profile.getDescription());
        viewHolder.tags_share.setText(profile.getTagShareArray().toString());
        viewHolder.tags_discover.setText(profile.getTagDiscover().toString());


        return convertView;
    }

    @Nullable
    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }

    private class CardViewHolder{
        public TextView firstName;
        public TextView description;
        public TextView tags_share;
        public TextView tags_discover;
        //public ImageView avatar;
    }

}
