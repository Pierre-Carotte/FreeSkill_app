package freeskill.app.controller.listener;

import android.widget.CompoundButton;

import freeskill.app.model.CurrentApp;
import freeskill.app.model.ProfileEditor;
import freeskill.app.model.Settings;

/**
 * Created by Olivier on 05/02/2018.
 */

public class SwitchOnCheckedChangeListener implements CompoundButton.OnCheckedChangeListener {

    CurrentApp currentApp;
    Settings settings;
    String type;

    public SwitchOnCheckedChangeListener(String type) {
        this.currentApp = CurrentApp.getInstance(null);
        this.settings = currentApp.profileEditor.getCurrentSettings();
        this.type = type;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (this.type) {
            case "notif_match": settings.setNotif_match(isChecked);
                break;
            case "notif_message": settings.setNotif_message(isChecked);
                break;
            case "notif_meeting": settings.setNotif_meeting(isChecked);
                break;
            case "notif_reminder": settings.setNotif_meeting_reminder(isChecked);
                break;
            case "notif_mark": settings.setNotif_notation(isChecked);
                break;
        }
    }
}
