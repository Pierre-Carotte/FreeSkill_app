package freeskill.app.model;

/**
 * Created by Olivier on 11/12/2017.
 */

public class Settings {
    private int perimeter;
    private boolean notif_match;
    private boolean notif_message;
    private boolean notif_meeting;
    private boolean notif_meeting_reminder;
    private boolean notif_notation;

    public int getPerimeter() {
        return perimeter;
    }

    public void setPerimeter(int perimeter) {
        this.perimeter = perimeter;
    }

    public boolean isNotif_match() {
        return notif_match;
    }

    public void setNotif_match(boolean notif_match) {
        this.notif_match = notif_match;
    }

    public boolean isNotif_message() {
        return notif_message;
    }

    public void setNotif_message(boolean notif_message) {
        this.notif_message = notif_message;
    }

    public boolean isNotif_meeting() {
        return notif_meeting;
    }

    public void setNotif_meeting(boolean notif_meeting) {
        this.notif_meeting = notif_meeting;
    }

    public boolean isNotif_meeting_reminder() {
        return notif_meeting_reminder;
    }

    public void setNotif_meeting_reminder(boolean notif_meeting_reminder) {
        this.notif_meeting_reminder = notif_meeting_reminder;
    }

    public boolean isNotif_notation() {
        return notif_notation;
    }

    public void setNotif_notation(boolean notif_notation) {
        this.notif_notation = notif_notation;
    }

    public void editSettings() {

    }
}
