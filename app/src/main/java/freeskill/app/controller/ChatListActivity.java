package freeskill.app.controller;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import freeskill.app.R;
import freeskill.app.controller.fragments.ChatListFragment;

/**
 * Created by Sofiane-e on 10/02/2018.
 */

public class ChatListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);

        // Ajoute le fragment TweetsFragment au container de l'activité
        if(savedInstanceState == null){
            getFragmentManager().beginTransaction().add(R.id.container, new ChatListFragment()).commit();
        }
    }
}
