package freeskill.app.controller.listener;

import android.widget.CompoundButton;

import freeskill.app.model.CurrentApp;
import freeskill.app.model.ProfileEditor;
import freeskill.app.model.Settings;
import freeskill.app.utils.Constants;
import freeskill.app.utils.Tools;

/**
 * Created by Olivier on 05/02/2018.
 */

public class SwitchOnCheckedChangeListener implements CompoundButton.OnCheckedChangeListener {

    CurrentApp currentApp;
    Settings settings;
    ProfileEditor profileEditor;
    String type;

    public SwitchOnCheckedChangeListener(String type) {
        this.currentApp = CurrentApp.getInstance(null);
        this.settings = currentApp.profileEditor.getCurrentSettings();
        this.profileEditor = currentApp.profileEditor;
        this.type = type;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (this.type) {
            case "notif_match":
                settings.setNotif_match(isChecked);
                this.profileEditor.updateCurrentSettings(Constants.JSONparameters.NOTIF_MATCH,
                        Tools.booleanToInt(this.settings.isNotif_match()));
                break;
            case "notif_message":
                settings.setNotif_message(isChecked);
                this.profileEditor.updateCurrentSettings(Constants.JSONparameters.NOTIF_MESSAGE,
                        Tools.booleanToInt(this.settings.isNotif_message()));
                break;
            case "notif_meeting":
                settings.setNotif_meeting(isChecked);
                this.profileEditor.updateCurrentSettings(Constants.JSONparameters.NOTIF_MEETING,
                        Tools.booleanToInt(this.settings.isNotif_meeting()));
                break;
            case "notif_reminder":
                settings.setNotif_meeting_reminder(isChecked);
                this.profileEditor.updateCurrentSettings(Constants.JSONparameters.NOTIF_REMINDER,
                        Tools.booleanToInt(this.settings.isNotif_meeting_reminder()));
                break;
            case "notif_mark":
                settings.setNotif_notation(isChecked);
                this.profileEditor.updateCurrentSettings(Constants.JSONparameters.NOTIF_MARK,
                        Tools.booleanToInt(this.settings.isNotif_notation()));
                break;
        }
    }
}
