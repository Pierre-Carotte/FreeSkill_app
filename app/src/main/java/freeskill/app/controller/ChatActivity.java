package freeskill.app.controller;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import freeskill.app.R;
import freeskill.app.controller.fragments.ChatFragment;
import freeskill.app.model.chat.Chat;
import freeskill.app.model.query.DeleteMatch;
import freeskill.app.model.query.GetMessages;
import freeskill.app.model.query.PutReportUser;

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
        AlertDialog.Builder builder;
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
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(this);
                }
                builder.setTitle(R.string.delete_user_dialog_name)
                        .setMessage(R.string.delete_user_dialog_message)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                deleteMatch();
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .show();
                break;
            case R.id.chat_signal:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(this);
                }
                builder.setTitle(R.string.report_user_dialog_name)
                        .setMessage(R.string.report_user_dialog_message)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                reportUser();
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // do nothing
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .show();
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
    }

    private void updateChat() {
        GetMessages.getInstance().request(chatFragment, chat.getIdUser());
    }

    public void deleteMatch() {
        DeleteMatch.getInstance().request(this, chat.getIdUser());
    }

    public void reportUser() {
        PutReportUser.getInstance().request(this, chat.getIdUser());
    }

    public void notifyDeleteMatch() {
        finish();
    }

    public void notifyReportUser() {
        deleteMatch();
    }
}