package freeskill.app.model;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import org.json.JSONObject;

import freeskill.app.FreeskillApplication;
import freeskill.app.utils.JWTUtils;

/**
 * Created by Sofiane-e on 02/02/2018.
 */

public class DataConnection {
    private String JWT;
    private int idUser;
    private String email;
    private SharedPreferences sharedPreferences = null;


    private static final DataConnection ourInstance = new DataConnection();

    public static DataConnection getInstance() {
        return ourInstance;
    }

    private DataConnection() {
        Context c = FreeskillApplication.getContext();
        this.sharedPreferences = c.getSharedPreferences("freeskillSharedPrefs", Context.MODE_PRIVATE);
    }

    public String getJWT() {
        return this.sharedPreferences.getString("jwt", null);
    }

    public void setJWT(String JWT) {
        this.JWT = JWT;
        try {
            JSONObject jBody = JWTUtils.decoded(JWT);
            this.sharedPreferences.edit().putString("jwt", JWT).commit();
            this.sharedPreferences.edit().putInt("idUser", jBody.getInt("idUser")).commit();
            this.sharedPreferences.edit().putString("email", jBody.getString("email")).commit();
            Log.d("test", this.sharedPreferences.getString("email", null));

        } catch (Exception e) {
            Log.e("JWT_DECODED", "BAD TOKEN");
        }
    }

    public int getIdUser() { return this.sharedPreferences.getInt("idUser", -1); }

    public String getEmail() {
        return this.sharedPreferences.getString("email", null);
    }

    public void clearDataConnection(){
        if(null != this.getEmail()){
            Log.d("test", this.getEmail());
        }
        this.sharedPreferences.edit().putString("jwt", "").commit();
        this.sharedPreferences.edit().putInt("idUser", -1).commit();
        this.sharedPreferences.edit().putString("email", "").commit();
    }

}
