package freeskill.app.model;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;

import freeskill.app.controller.HomepageScreen;
import freeskill.app.controller.SwipeScreen;
import freeskill.app.model.chat.ChatList;
import freeskill.app.model.query.Connection;
import freeskill.app.model.query.GetMatches;
import freeskill.app.model.query.Judgement;

/**
 * Created by Olivier on 11/12/2017.
 */

public class CurrentApp {
    public ProfileEditor profileEditor;

    private RequestQueue queue;
    private Connection connection;
    private String accessToken;
    private Judgement judgement;

    public RequestQueue getQueue() {
        return queue;
    }

    private static CurrentApp instance = null;

    private CurrentApp(RequestQueue queue) {
        this.queue = queue;
    }

    public static CurrentApp getInstance(RequestQueue queue) {
        if (instance == null) {
            instance = new CurrentApp(queue);
        }
        return instance;
    }

    public Connection createConnection(HomepageScreen homepageScreen) {
        this.connection = new Connection(homepageScreen);
        return this.connection;
    }



    public void getConnection(String email, String password) {
        this.connection.getConnection(email, password, this.queue);
    }

    public ProfileEditor createProfileEditor() {
        this.profileEditor = new ProfileEditor(this.accessToken, this.queue);
        return this.profileEditor;
    }

    public Judgement createJudgement(SwipeScreen swipeScreen) {
        this.judgement = new Judgement(this.accessToken, this.queue, swipeScreen);
        return this.judgement;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
