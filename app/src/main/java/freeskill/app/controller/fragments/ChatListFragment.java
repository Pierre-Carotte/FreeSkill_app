package freeskill.app.controller.fragments;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.android.volley.AuthFailureError;

import freeskill.app.R;
import freeskill.app.controller.interfaces.ChatChangeListener;
import freeskill.app.model.CurrentApp;
import freeskill.app.model.adapters.ChatListAdapter;
import freeskill.app.model.chat.Chat;
import freeskill.app.model.chat.ChatList;
import freeskill.app.model.query.GetMatches;

/**
 * Created by Sofiane-e on 10/02/2018.
 */

public class ChatListFragment extends Fragment implements ChatChangeListener {

    private ListView mListView;

    public ChatListFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_chatlist, container, false);
        Log.d("Add chatlist fragrement", "ok");
        mListView = (ListView) rootView.findViewById(R.id.chatListView);


        //make the progressBar
        final ProgressBar progressBar = new ProgressBar(getActivity());
        progressBar.setLayoutParams(new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.MATCH_PARENT,
                Gravity.CENTER));
        progressBar.setIndeterminate(true);

        mListView.setEmptyView(progressBar);

        //Ajout de la progressBar au fragment
        ViewGroup root = (ViewGroup) rootView.findViewById(R.id.chatListRootRelativeLayout);
        root.addView(progressBar);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("Start fragrement", "ok");
        GetMatches getMatches = new GetMatches(this);
        CurrentApp ca = CurrentApp.getInstance(null);
        try {
            getMatches.getChatList(ca.getQueue());
        } catch (AuthFailureError authFailureError) {
            Log.e("GETMATCHESFRAMGMENT", authFailureError.getMessage());
        }

    }

    @Override
    public void onChatRetrieved(ChatList chatList) {
        /*for (Chat chat : chatList.getChat()){
            Log.d("ChatRetrieved", chat.getName());
        }*/
        final ChatListAdapter chatListAdapter = new ChatListAdapter(chatList);
        mListView.setAdapter(chatListAdapter);
    }
}
