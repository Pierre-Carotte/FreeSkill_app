package freeskill.app.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.io.InputStream;

import freeskill.app.R;
import freeskill.app.model.CurrentApp;
import freeskill.app.model.Mark;
import freeskill.app.model.adapters.MarksAdapter;
import freeskill.app.model.query.GetMarks;
import freeskill.app.utils.HttpsTrustManager;

/**
 * Created by Florian on 11/02/2018.
 */

public class ListMarksScreen extends AppCompatActivity {

    private ListView listView;
    private RequestQueue queue;
    private CurrentApp currentApp;
    private GetMarks getMarks;
    private MarksAdapter adapter;
    private int idProfile;

    public int getIdProfile() {
        return idProfile;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.marks_by_tag);

        Intent intent = getIntent();
        this.idProfile = intent.getIntExtra("idProfile", -1);

        queue = Volley.newRequestQueue(this);
        InputStream caInput=getResources().openRawResource(R.raw.letsencryptauthorityx3);
        HttpsTrustManager https = new HttpsTrustManager(caInput);
        https.allowMySSL();

        this.currentApp = CurrentApp.getInstance(null);
        this.getMarks = new GetMarks(this.currentApp.getAccessToken(),this.queue,this);
        this.getMarks.getMarks(this.currentApp.getAccessToken());

        this.adapter = this.getMarks.getAdapter();

        // Get ListView object from xml
        listView = findViewById(R.id.listViewMarks);

        // Defined Array values to show in ListView
        String[] values = new String[] { "Android",
                "Adapter implementation",
                "Simple List View In Android",
                "Create List View Android",
                "Android Example",
                "List View Source Code",
                "List View Array Adapter",
                "Android Example List View"
        };

        // Assign adapter to ListView
        listView.setAdapter(adapter);

    }
}
