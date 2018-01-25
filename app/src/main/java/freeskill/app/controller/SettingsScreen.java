package freeskill.app.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import freeskill.app.R;
import freeskill.app.model.CurrentApp;
import freeskill.app.model.ProfileEditor;
import freeskill.app.utils.SeekBarListener;

/**
 * Created by Olivier on 06/12/2017.
 */

public class SettingsScreen extends AppCompatActivity {
    private CurrentApp app;
    private ProfileEditor profileEditor;

    private SeekBar seekBarDistance;
    private TextView distanceMax;
    private Switch switch_new_match;
    private Switch switch_new_message;
    private Switch switch_meeting_reminder;
    private Switch switch_new_meeting;
    private Switch switch_mark;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();

        this.distanceMax = findViewById(R.id.distance_max);
        this.seekBarDistance = findViewById(R.id.seekBar_distance);
        this.switch_new_match = findViewById((R.id.switch_new_match));
        this.switch_new_message = findViewById(R.id.switch_new_message);
        this.switch_new_meeting = findViewById(R.id.switch_new_meeting);
        this.switch_meeting_reminder = findViewById(R.id.switch_meeting_reminder);
        this.switch_mark = findViewById(R.id.switch_notation);

        seekBarDistance.setMax(50);
        seekBarDistance.setOnSeekBarChangeListener(new SeekBarListener(distanceMax, " km"));

        this.app = CurrentApp.getInstance(null);
        this.profileEditor = this.app.createProfileEditor();
        this.profileEditor.createCurrentSettings(this);
    }

    public SeekBar getSeekBarDistance() {
        return seekBarDistance;
    }

    public TextView getDistanceMax() {
        return distanceMax;
    }

    public Switch getSwitch_new_match() {
        return switch_new_match;
    }

    public Switch getSwitch_new_message() {
        return switch_new_message;
    }

    public Switch getSwitch_meeting_reminder() {
        return switch_meeting_reminder;
    }

    public Switch getSwitch_new_meeting() {
        return switch_new_meeting;
    }

    public Switch getSwitch_mark() {
        return switch_mark;
    }

}
