package freeskill.app.controller.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import freeskill.app.R;
import freeskill.app.model.adapters.ChatAdpater;
import freeskill.app.model.chat.Chat;
import freeskill.app.model.chat.Message;
import freeskill.app.model.query.SendMessage;
import freeskill.app.utils.Tools;

/**
 * Created by Sofiane-e on 11/02/2018.
 */

public class ChatFragment extends Fragment implements View.OnClickListener {
    private static final String SerializableName = "chat";
    private Chat chat;
    private ChatAdpater mMessageAdapter = null;
    private RecyclerView mRecyclerView;
    private EditText editText;
    private LinearLayoutManager linearLayout = null;

    public static ChatFragment newIntance(Chat chat) {
        Bundle args = new Bundle();
        args.putSerializable(SerializableName, chat);
        ChatFragment chatFragment = new ChatFragment();
        chatFragment.setArguments(args);
        return chatFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.fragment_chat, container, false);
        editText = rootView.findViewById(R.id.edit_chat_box);
        View button = rootView.findViewById(R.id.button_chatbox_send);
        //Add listener on button send to send message
        button.setOnClickListener(this);

        //Get chat serialize
        try {
            chat = (Chat) getArguments().getSerializable(SerializableName);
            chat.setMessageIsRead();
        } catch (Exception ex) {
            Toast.makeText(getActivity(), "Error ", Toast.LENGTH_SHORT).show();
            return null;
        }
        //Make chatAdpater with chat
        mMessageAdapter = new ChatAdpater(chat);

        //listener to quit edit text
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Tools.hideKeyboard(getContext(), view);
            }
        });

        //Get recycler view to add linear layout and adapter
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview_list);
        linearLayout = new LinearLayoutManager(getActivity());
        linearLayout.setStackFromEnd(true);
        mRecyclerView.setLayoutManager(linearLayout);
        mRecyclerView.setAdapter(mMessageAdapter);
        return rootView;
    }

    public void onResume() {
        super.onResume();
        mRecyclerView.setAdapter(mMessageAdapter);
    }

    @Override
    public void onClick(View v) {
        //
        if (editText.getText() != null && editText.getText() != null
                && !editText.getText().toString().isEmpty()) {
            SendMessage sm = SendMessage.getInstance();
            sm.sendMessage(this, chat.getIdUser(), editText.getText().toString());
            editText.setText("");
        }
    }

    public ChatAdpater getmMessageAdapter() {
        return mMessageAdapter;
    }

    //when we send message (query sendmessage), the query notify the fragment with new message
    public void notifyNewMessage(JSONObject message) {
        Message m = new Message(message);
        chat.getMessages().add(m);
        mMessageAdapter.notifyDataSetChanged();
        linearLayout.scrollToPosition(mMessageAdapter.getItemCount() - 1);
    }

    //when we call query getMessages of chat, the query notify with new json object chat
    public void notifyNewChat(Chat chat) {
        if (this.chat.getMessages().size() < chat.getMessages().size()) {
            this.chat = chat;
            mMessageAdapter.setChat(chat);
            mMessageAdapter.notifyDataSetChanged();
            linearLayout.scrollToPosition(mMessageAdapter.getItemCount() - 1);
        }
    }

    public Chat getChat() {
        return chat;
    }
}
