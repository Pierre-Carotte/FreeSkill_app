package test.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;
import android.widget.TextView;

import test.test.utils.SeekBarListener;

/**
 * Created by Olivier on 06/12/2017.
 */

public class Settings extends AppCompatActivity {
    private SeekBar seekBarDistance;
    private TextView distanceMax;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();

        this.distanceMax = findViewById(R.id.distance_max);

        this.seekBarDistance = findViewById(R.id.seekBar_distance);
        seekBarDistance.setMax(50);

        seekBarDistance.setOnSeekBarChangeListener(new SeekBarListener(distanceMax, " km"));
    }
}
