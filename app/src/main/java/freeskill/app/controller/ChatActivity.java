package freeskill.app.controller;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import freeskill.app.R;
import freeskill.app.controller.fragments.ChatFragment;
import freeskill.app.model.chat.Chat;
import freeskill.app.model.query.GetMessages;
import freeskill.app.utils.Tools;

/**
 * Created by Sofiane-e on 11/02/2018.
 */

public class ChatActivity extends AppCompatActivity {
    private Chat chat;
    ChatFragment chatFragment;
    TimerTask timerTaskObj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        try {
            chat = (Chat) getIntent().getExtras()
                    .getSerializable("chat");
            setTitle(chat.getName());
        } catch (Exception ex) {
            Toast.makeText(this, "Error ", Toast.LENGTH_SHORT).show();
        }

        this.chatFragment = ChatFragment.newIntance(chat);
        Timer timerObj = new Timer();
        timerTaskObj = new TimerTask() {
            public void run() {
                updateChat();
            }
        };

        timerObj.schedule(timerTaskObj, 0, 1000);
        getFragmentManager().beginTransaction().add(R.id.containerchat, chatFragment).commit();
        //getFragmentManager().findFragmentById(R.id.containerchat).notify
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.chat_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id) {
            case R.id.chat_profile:
                Log.d("menu", "profile");
                Toast.makeText(this, "En cours d'implémentation.",
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.chat_marks:
                Log.d("menu", "marks");
                Toast.makeText(this, "En cours d'implémentation.",
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.chat_make_meet:
                Log.d("menu", "make meet");
                Toast.makeText(this, "En cours d'implémentation.",
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.chat_delete:
                Log.d("menu", "chat_delete");
                Toast.makeText(this, "En cours d'implémentation.",
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.chat_signal:
                Log.d("menu", "signal");
                Toast.makeText(this, "En cours d'implémentation.",
                        Toast.LENGTH_SHORT).show();
                break;
            default:
                Log.d("menu", "default");
                return super.onOptionsItemSelected(item);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d("stop chat", "stop timer");
        timerTaskObj.cancel();
        /*
        TODO: STOP TIMERTASK
         */
    }

    private void updateChat(){
        //Log.d("timerChatActivity", "ok");
        GetMessages.getInstance().request(chatFragment, chat.getIdUser());
    }


}