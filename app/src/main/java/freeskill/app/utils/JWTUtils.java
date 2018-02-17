package freeskill.app.utils;

import android.util.Base64;
import android.util.Log;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

/**
 * Created by Sofiane-e on 02/02/2018.
 */

public class JWTUtils {
    public static JSONObject decoded(String JWTEncoded) throws Exception {
        JSONObject jObject = null;
        try {
            String[] split = JWTEncoded.split("\\.");
            Log.d("JWT_DECODED", "Header: " + getJson(split[0]));
            Log.d("JWT_DECODED", "Body: " + getJson(split[1]));
            jObject = new JSONObject(getJson(split[1]));
            return jObject;
        } catch (UnsupportedEncodingException e) {
            Log.d("JWT_DECODED", "BAD TOKEN");
            return null;
        }

    }

    private static String getJson(String strEncoded) throws UnsupportedEncodingException, UnsupportedEncodingException {
        byte[] decodedBytes = Base64.decode(strEncoded, Base64.URL_SAFE);
        return new String(decodedBytes, "UTF-8");
    }
}
