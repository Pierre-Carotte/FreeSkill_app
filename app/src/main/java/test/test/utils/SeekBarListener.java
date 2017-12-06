package test.test.utils;

import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by Olivier on 06/12/2017.
 */

public class SeekBarListener implements SeekBar.OnSeekBarChangeListener {
    private TextView textView;
    private String text;

    public SeekBarListener(TextView textView, String text) {
        this.textView = textView;
        this.text = text;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (fromUser) {
            if (progress >= 0 && progress <= seekBar.getMax()) {
                String progressString;
                if(progress == 0){
                    progressString = String.valueOf(progress+1);
                }else {
                    progressString = String.valueOf(progress);
                }
                this.textView.setText( progressString + this.text); // the TextView Reference
                seekBar.setSecondaryProgress(progress);
            }
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
