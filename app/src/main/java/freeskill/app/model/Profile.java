package freeskill.app.model;

import java.util.ArrayList;


/**
 * Created by Olivier on 11/12/2017.
 */

public class Profile {
    private int idAccess;
    private int idFacebook;
    private int idFCM;
    private String first_name;
    private String last_name;
    private String email;
    private double latitude;
    private double longitude;
    private byte[] picture;
    private double averageMark;
    private String description;
    private boolean isAssos;
    private ArrayList marks;
    private ArrayList tagShare;
    private ArrayList tagDiscover;

    public boolean isAssos() {
        return isAssos;
    }

    public void setAssos(boolean assos) {
        isAssos = assos;
    }

    public int getIdAccess() {
        return idAccess;
    }

    public void setIdAccess(int idAccess) {
        this.idAccess = idAccess;
    }

    public int getIdFacebook() {
        return idFacebook;
    }

    public void setIdFacebook(int idFacebook) {
        this.idFacebook = idFacebook;
    }

    public int getIdFCM() {
        return idFCM;
    }

    public void setIdFCM(int idFCM) {
        this.idFCM = idFCM;
    }

    public String getFirstname() {
        return first_name;
    }

    public void setFirstname(String firstname) {
        this.first_name = firstname;
    }

    public String getLastname() {
        return last_name;
    }

    public void setLastname(String lastname) {
        this.last_name = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    public double getAverageMark() {
        return averageMark;
    }

    public void setAverageMark(double averageMark) {
        this.averageMark = averageMark;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList getMarks() {
        return marks;
    }

    public void setMarks(ArrayList marks) {
        this.marks = marks;
    }

    public ArrayList getTagShare() {
        return tagShare;
    }

    public void setTagShare(ArrayList tagShare) {
        this.tagShare = tagShare;
    }

    public ArrayList getTagDiscover() {
        return tagDiscover;
    }

    public void setTagDiscover(ArrayList tagDiscover) {
        this.tagDiscover = tagDiscover;
    }

    public Profile() {
        this.marks = new ArrayList<Mark>();
        this.tagShare = new ArrayList<String>();
        this.tagDiscover = new ArrayList<String>();
    }
}
