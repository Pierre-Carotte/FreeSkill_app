package freeskill.app.model;

import com.android.volley.RequestQueue;

import freeskill.app.model.query.CurrentProfileQuery;

/**
 * Created by Olivier on 11/12/2017.
 */

public class ProfileEditor{
    private CurrentProfile currentProfile;
    private Settings settings;
    private String accessToken;
    private RequestQueue queue;

    public ProfileEditor(String accessToken, RequestQueue queue) {
        this.currentProfile = new CurrentProfile();
        this.settings = new Settings();
        this.accessToken = accessToken;
        this.queue = queue;
    }

    public void setCurrentProfile(){
        //CurrentProfileQuery currentProfile = new CurrentProfileQuery();
        //currentProfile.getCurrentProfile(accessToken, queue);
    }

    public void createCurrentProfile() {
        CurrentProfileQuery currentProfile = new CurrentProfileQuery();
        currentProfile.getCurrentProfile(accessToken, queue);
    }

    public CurrentProfile getCurrentProfile() {
        return currentProfile;
    }
}
