package freeskill.app.model;

import com.android.volley.RequestQueue;

import freeskill.app.controller.HomepageScreen;
import freeskill.app.controller.SwipeScreen;
import freeskill.app.model.query.GetConnection;
import freeskill.app.model.query.Judgement;
import freeskill.app.model.query.PostUserRegistration;

/**
 * Created by Olivier on 11/12/2017.
 */

public class CurrentApp {
    public ProfileEditor profileEditor;

    private RequestQueue queue;
    private GetConnection connection;
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

    public GetConnection createConnection(HomepageScreen homepageScreen) {
        this.connection = new GetConnection(homepageScreen);
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

    public ProfileEditor getProfileEditor() {
        return profileEditor;
    }

    public void userRegistration(String firstname, String lastname, String email, String password){
        PostUserRegistration postUserRegistration = new PostUserRegistration();
        postUserRegistration.postUserRegistration(this.queue, firstname, lastname, email, password);
    }

}
