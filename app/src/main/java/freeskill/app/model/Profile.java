package freeskill.app.model;

import java.util.ArrayList;

/**
 * Created by Olivier on 11/12/2017.
 */

public class Profile {
    private int idAccess;
    private int idFacebook;
    private int idFCM;
    private String firstname;
    private String lastname;
    private String email;
    private double latitude;
    private double longitude;
    private byte[] picture;
    private double averageMark;
    private String description;
    private ArrayList marks;
    private ArrayList tagShare;
    private ArrayList tagDiscover;

    public Profile() {
        this.marks = new ArrayList<Mark>();
        this.tagShare = new ArrayList<String>();
        this.tagDiscover = new ArrayList<String>();
    }

    public void setProfile() {

    }

    public void getProfile() {

    }
}
