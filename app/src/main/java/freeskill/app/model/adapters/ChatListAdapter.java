package freeskill.app.model.adapters;

import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.bumptech.glide.signature.StringSignature;
import freeskill.app.FreeskillApplication;
import freeskill.app.R;
import freeskill.app.model.DataConnection;
import freeskill.app.model.chat.Chat;
import freeskill.app.model.chat.ChatList;
import freeskill.app.model.chat.Message;
import freeskill.app.utils.Constants;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.RED;
import static freeskill.app.utils.Tools.parseMessageDate;

/**
 * Created by Sofiane-e on 10/02/2018.
 */

public class ChatListAdapter extends BaseAdapter {
    private ChatList chatList;

    public ChatListAdapter(ChatList chatList) {
        this.chatList = chatList;
    }

    @Override
    public int getCount() {
        return null != chatList.getChat() ? chatList.getChat().size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return null != chatList.getChat() ? chatList.getChat().get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        LayoutInflater mInflater = LayoutInflater.from(FreeskillApplication.getContext());

        if(null == convertView){
            convertView = mInflater.inflate(R.layout.chat_list_item, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        //get chats
        final Chat chat = (Chat) getItem(position);

        //Add text composants
        holder.username.setText(chat.getName());
        Message lastMessage = chat.getLastMessage();

        //If there is no message
        if(lastMessage == null){
            holder.lastMessage.setText(R.string.noMessage);
            holder.lastMessage.setTextColor(RED);
            holder.lastMessage.setTypeface(null, Typeface.BOLD);
            holder.date.setText(parseMessageDate(chat.getMatchDate()));
        }else{//if message and received and no read, set in bold the textview
            if(lastMessage.isReceived() && !lastMessage.isRead()){
                holder.lastMessage.setTypeface(null, Typeface.BOLD);
            }else{
                holder.lastMessage.setTypeface(null, Typeface.NORMAL);
            }
            holder.lastMessage.setText(lastMessage.getMessage());
            holder.lastMessage.setTextColor(BLACK);
            holder.date.setText(parseMessageDate(lastMessage.getDate()));
        }

        /*
        TODO ADD constat URI to get image
         */
        String urlImage = "https://freeskill.ddns.net/user/getimage/" + chat.getIdUser();
        GlideUrl glideUrl = new GlideUrl(urlImage, new LazyHeaders.Builder()
                .addHeader(Constants.General.KEY_ACCESS_TOKEN, DataConnection.getInstance().getJWT())
                .build());

        Glide.with(FreeskillApplication.getContext()).load(glideUrl)
                .signature(new StringSignature(String.valueOf(System.currentTimeMillis())))
                .into(holder.image);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                TODO: Call activity to Chat
                 */
                String s = holder.username.getText().toString();
                Log.d("click chat", s);
            }
        });

        return convertView;
    }


    private class ViewHolder {
        private ImageView image;
        private TextView username;
        private TextView lastMessage;
        private TextView date;

        private ViewHolder(View view){
            image = (ImageView) view.findViewById(R.id.ChatListItemImageView);
            username = (TextView) view.findViewById(R.id.ChatListItemTextusername);
            lastMessage = (TextView) view.findViewById(R.id.ChatListItemTextLastMessage);
            date = (TextView) view.findViewById(R.id.ChatListItemTextDate);
        }
    }
}
