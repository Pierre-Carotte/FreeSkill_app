package freeskill.app.model;

import com.android.volley.RequestQueue;

import freeskill.app.controller.ProfileScreen;
import freeskill.app.controller.SettingsScreen;
import freeskill.app.model.query.CurrentProfileQuery;
import freeskill.app.model.query.CurrentSettingsQuery;
import freeskill.app.model.query.PostCurrentSettings;

/**
 * Created by Olivier on 11/12/2017.
 */

public class ProfileEditor{
    private Profile profile;
    private Settings settings;
    private String accessToken;
    private RequestQueue queue;

    public ProfileEditor(String accessToken, RequestQueue queue) {
        this.accessToken = accessToken;
        this.queue = queue;
        this.createCurrentProfile();
        this.createCurrentSettings();
    }

    public void createCurrentProfile(){
        this.profile = new Profile();
    }

    //public Profile getCurrentProfile(){
        //return this.profile;
    //}

    public void createCurrentSettings(SettingsScreen settingsScreen){
        CurrentSettingsQuery currentSettings = new CurrentSettingsQuery(settingsScreen, this.settings);
        currentSettings.getCurrentSettings(queue);
    }

    public void createCurrentSettings(){
        this.settings = new Settings();
    }

    public Settings getCurrentSettings(){
        return this.settings;
    }

    public void createCurrentProfile(ProfileScreen profileScreen) {
        CurrentProfileQuery currentProfile = new CurrentProfileQuery(profileScreen, this.profile);
        currentProfile.getCurrentProfile(queue);
    }

    public Profile getProfile() {
        return profile;
    }

    public void updateCurrentSettings(){
        PostCurrentSettings postCurrentSettings = new PostCurrentSettings();
        postCurrentSettings.postCurrentSettings(
                this.queue, this.settings.getPerimeter(), this.settings.isNotif_match(),
                this.settings.isNotif_message(), this.settings.isNotif_meeting(),
                this.settings.isNotif_meeting_reminder(), this.settings.isNotif_notation()
        );
    }
}
