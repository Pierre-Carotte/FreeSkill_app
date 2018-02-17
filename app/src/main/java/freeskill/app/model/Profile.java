package freeskill.app.model;

import android.graphics.Bitmap;

import java.util.ArrayList;


/**
 * Created by Olivier on 11/12/2017.
 */

public class Profile {
    private int id;
    private int idAccess;
    private int idFacebook;
    private int idFCM;
    private int perimeter;
    private int distance;
    private String first_name;
    private String last_name;
    private String email;
    private double latitude;
    private double longitude;
    private Bitmap picture;
    private double averageMark;
    private String description;
    private ArrayList marks;
    private ArrayList tagShare;
    private ArrayList tagDiscover;
    private boolean isAssos;


    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getPerimeter() {
        return perimeter;
    }

    public void setPerimeter(int perimeter) {
        this.perimeter = perimeter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Bitmap getPicture() {
        return picture;
    }

    public void setPicture(Bitmap picture) {
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

    public ArrayList getTagShareArray() {
        return tagShare;
    }

    public void setTagShareArray(ArrayList tagShare) {
        this.tagShare = tagShare;
    }

    public void setTagShare(String tagShare) {
        this.tagShare.add(tagShare);
    }

    public ArrayList getTagDiscoverArray() {
        return tagDiscover;
    }


    public void setTagDiscoverArray(ArrayList tagDiscover) {
        this.tagDiscover = tagDiscover;
    }

    public void setTagDiscover(String tagDiscover) {
        this.tagDiscover.add(tagDiscover);
    }

    public boolean isAssos() {
        return isAssos;
    }

    public void setAssos(int assos) {
        this.isAssos = assos == 1;
    }

    public String getTagToString(ArrayList tagShare) {
        String text_tags = "";
        for (int i = 0; i < tagShare.size(); i++) {
            System.out.println("TAB_SHARE : " + tagShare.size() + tagShare.toString());
            text_tags = text_tags.concat("#" + tagShare.get(i).toString() + " ");
        }
        return text_tags;
    }

    public Profile() {
        this.marks = new ArrayList<Mark>();
        this.tagShare = new ArrayList<String>();
        this.tagDiscover = new ArrayList<String>();
    }
}
