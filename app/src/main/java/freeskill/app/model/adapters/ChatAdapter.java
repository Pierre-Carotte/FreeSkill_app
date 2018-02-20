package freeskill.app.model.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;

import java.text.DateFormat;

import freeskill.app.FreeskillApplication;
import freeskill.app.R;
import freeskill.app.model.DataConnection;
import freeskill.app.model.chat.Chat;
import freeskill.app.model.chat.Message;
import freeskill.app.utils.Constants;
import freeskill.app.utils.Tools;

/**
 * Created by Sofiane-e on 11/02/2018.
 */

public class ChatAdapter extends RecyclerView.Adapter {
    private Chat chat;
    private static final int VIEW_TYPE_MESSAGE_SENT = 1;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 2;

    public ChatAdapter(Chat chat) {
        this.chat = chat;
    }

    @Override
    public int getItemCount() {
        return chat.getMessages().size();
    }

    // Determines the appropriate ViewType according to the sender of the message.
    @Override
    public int getItemViewType(int position) {
        Message message = (Message) chat.getMessages().get(position);
        if (!message.isReceived()) {
            // If the current user is the sender of the message
            return VIEW_TYPE_MESSAGE_SENT;
        } else {
            // If some other user sent the message
            return VIEW_TYPE_MESSAGE_RECEIVED;
        }
    }

    // Inflates the appropriate layout according to the ViewType.
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == VIEW_TYPE_MESSAGE_SENT) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_message_sended, parent, false);
            return new SentMessageHolder(view);
        } else if (viewType == VIEW_TYPE_MESSAGE_RECEIVED) {
            view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_message_received, parent, false);
            return new ReceivedMessageHolder(view);
        }

        return null;
    }

    // Passes the message object to a ViewHolder so that the contents can be bound to UI.
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Message message = (Message) chat.getMessages().get(position);
        switch (holder.getItemViewType()) {
            case VIEW_TYPE_MESSAGE_SENT:
                ((SentMessageHolder) holder).bind(message);
                break;
            case VIEW_TYPE_MESSAGE_RECEIVED:
                ((ReceivedMessageHolder) holder).bind(message, chat);
        }
    }

    private class SentMessageHolder extends RecyclerView.ViewHolder {
        TextView messageText, timeText;

        SentMessageHolder(View itemView) {
            super(itemView);
            messageText = (TextView) itemView.findViewById(R.id.text_messageSend_body);
            timeText = (TextView) itemView.findViewById(R.id.text_messageSend_time);
        }

        void bind(Message message) {
            messageText.setText(message.getMessage());
            // Format the stored timestamp into a readable String using method.
            DateFormat shortDateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT,
                    DateFormat.SHORT);
            timeText.setText(shortDateFormat.format(message.getDate()));
        }
    }

    private class ReceivedMessageHolder extends RecyclerView.ViewHolder {
        TextView messageText, timeText, nameText;
        ImageView profileImage;

        ReceivedMessageHolder(View itemView) {
            super(itemView);
            messageText = (TextView) itemView.findViewById(R.id.text_message_body);
            timeText = (TextView) itemView.findViewById(R.id.text_message_time);
            nameText = (TextView) itemView.findViewById(R.id.text_message_name);
            profileImage = (ImageView) itemView.findViewById(R.id.image_message_profile);
        }

        void bind(Message message, Chat chat) {
            messageText.setText(message.getMessage());
            // Format the stored timestamp into a readable String using method.
            DateFormat shortDateFormat = DateFormat.getDateTimeInstance(DateFormat.SHORT,
                    DateFormat.SHORT);
            timeText.setText(shortDateFormat.format(message.getDate()));
            nameText.setText(chat.getName());

            String urlImage = Constants.API.GetImage.URI + chat.getIdUser();
            GlideUrl glideUrl = new GlideUrl(urlImage, new LazyHeaders.Builder()
                    .addHeader(Constants.General.KEY_ACCESS_TOKEN, DataConnection.getInstance().getJWT())
                    .build());

            Glide.with(FreeskillApplication.getContext()).
                    load(glideUrl)
                    .transform(new Tools.CircleTransform(FreeskillApplication.getContext()))
                    .into(profileImage);
        }
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }
}
