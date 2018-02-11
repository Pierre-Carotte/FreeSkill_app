package freeskill.app.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import freeskill.app.FreeskillApplication;
import freeskill.app.R;
import freeskill.app.model.DataConnection;

/**
 * Created by Florian on 01/12/2017.
 */

public class Tools {

    public static void hideKeyboard(Context context, View view) {

        if (view != null) {
            InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

    }

    public static Date parseDate(String date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Date d =null;
        try {
            d = simpleDateFormat.parse(date);
            return d;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String parseMessageDate(Date date){
        Resources res = FreeskillApplication.getContext().getResources();
        Date now = new Date();
        now.setTime(now.getTime());

        long diff = now.getTime() - date.getTime();//as given
        int minutes = Math.round(TimeUnit.MILLISECONDS.toMinutes(diff));
        // inferior 1hours
        if(59 > minutes){
            return Long.toString(minutes)  + " " + res.getString(R.string.minutes);
        }

        //if inferior one day
        if(1440 > minutes){
            return Long.toString(TimeUnit.MILLISECONDS.toHours(diff)) + " " + res.getString(R.string.hours);
        }
        // if superior one day
        return Long.toString(TimeUnit.MILLISECONDS.toDays(diff))  + "  " + res.getString(R.string.days);
    }
}
