package freeskill.app.model.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

import freeskill.app.R;
import freeskill.app.model.Mark;

/**
 * Created by Florian on 12/02/2018.
 */

public class MarksAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Mark> marks;

    public MarksAdapter(Context context, ArrayList<Mark> marks) {
        this.context = context;
        this.marks = marks;
    }

    @Override
    public int getCount() {
        return marks.size();
    }

    @Override
    public Object getItem(int position) {
        return marks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_mark_by_tag, parent, false);
        }
        ViewHolder viewHolder = (ViewHolder) convertView.getTag();
        if (viewHolder == null) {
            viewHolder = new ViewHolder();
            viewHolder.tagName = convertView.findViewById(R.id.tag);
            viewHolder.ratingBar = convertView.findViewById(R.id.mark2);
            viewHolder.nbMarks = convertView.findViewById(R.id.number_of_mark);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Tweet> tweets
        Mark mark = (Mark) getItem(position);

        //fill the view
        viewHolder.tagName.setText(mark.getTag());
        viewHolder.ratingBar.setRating(mark.getMark_average());
        viewHolder.nbMarks.setText(String.valueOf(mark.getNbMark()));

        return convertView;
    }

    private class ViewHolder {
        public TextView tagName;
        public RatingBar ratingBar;
        public TextView nbMarks;
    }
}
