package freeskill.app.model;

import com.android.volley.RequestQueue;

import freeskill.app.controller.ProfileScreen;
import freeskill.app.model.query.CurrentProfileQuery;

/**
 * Created by Olivier on 11/12/2017.
 */

public class ProfileEditor{
    private Profile profile;
    private Settings settings;
    private String accessToken;
    private RequestQueue queue;

    public ProfileEditor(String accessToken, RequestQueue queue) {
        this.profile = new Profile();
        this.settings = new Settings();
        this.accessToken = accessToken;
        this.queue = queue;
    }

    public void setCurrentProfile(){
        //CurrentProfileQuery profile = new CurrentProfileQuery();
        //profile.getProfile(accessToken, queue);
    }

    public void createCurrentProfile(ProfileScreen profileScreen) {
        CurrentProfileQuery currentProfile = new CurrentProfileQuery(profileScreen);
        currentProfile.getCurrentProfile(accessToken, queue);
    }

    public Profile getProfile() {
        return profile;
    }
}
