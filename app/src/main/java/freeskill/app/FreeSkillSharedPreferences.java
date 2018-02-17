package freeskill.app;

import android.content.Context;

import freeskill.app.utils.Constants;

/**
 * Created by Olivier on 25/01/2018.
 */

public class FreeSkillSharedPreferences {
    private FreeSkillSharedPreferences freeSkillSharedPreferencesConnection;
    private FreeSkillSharedPreferences freeSkillSharedPreferencesProfile;
    private FreeSkillSharedPreferences freeSkillSharedPreferencesSettings;

    private static android.content.SharedPreferences getSharedPreferencesConnection(){
        return FreeskillApplication.getContext().getSharedPreferences
                (Constants.Preferences.SHARED_PREFERENCES_CONNECTION, Context.MODE_PRIVATE);
    }

    private static android.content.SharedPreferences getSharedPreferencesProfile(){
        return FreeskillApplication.getContext().getSharedPreferences
                (Constants.Preferences.SHARED_PREFERENCES_PROFILE, Context.MODE_PRIVATE);
    }

    private static android.content.SharedPreferences getSharedPreferencesSettings(){
        return FreeskillApplication.getContext().getSharedPreferences
                (Constants.Preferences.SHARED_PREFERENCES_SETTINGS, Context.MODE_PRIVATE);
    }

    public void setProfile() {

    }

    public void setSettings(int perimeter, boolean notif_match, boolean notif_message,
                            boolean notif_meeting, boolean notif_reminder, boolean notif_mark){
        final android.content.SharedPreferences prefs = getSharedPreferencesSettings();
        prefs.edit().putInt(Constants.PreferencesSettings.PREF_PERIMETER, perimeter).apply();
        prefs.edit().putBoolean(Constants.PreferencesSettings.PREF_NOTIF_MATCH, notif_match).apply();
        prefs.edit().putBoolean(Constants.PreferencesSettings.PREF_NOTIF_MESSAGE, notif_message).apply();
        prefs.edit().putBoolean(Constants.PreferencesSettings.PREF_NOTIF_MEETING, notif_meeting).apply();
        prefs.edit().putBoolean(Constants.PreferencesSettings.PREF_NOTIF_REMINDER, notif_reminder).apply();
        prefs.edit().putBoolean(Constants.PreferencesSettings.PREF_NOTIF_MARK, notif_mark).apply();

    }

    public void setConnection(String login){
        final android.content.SharedPreferences prefs = getSharedPreferencesConnection();
        prefs.edit().putString(Constants.PreferencesConnection.PREF_LOGIN, login).apply();
    }

    public void setID_FCM(String token){
        final android.content.SharedPreferences prefs = getSharedPreferencesConnection();
        prefs.edit().putString(Constants.JSONparameters.ID_FCM, token).apply();
    }
}
