package freeskill.app.model;

import java.util.Date;

/**
 * Created by Olivier on 11/12/2017.
 */

public class Mark {
    private int userId;
    private int mark;
    private String review;
    private String tag;
    private Date date;
    private float mark_average;
    private int nbMark;

    public int getNbMark() {
        return nbMark;
    }

    public void setNbMark(int nbMark) {
        this.nbMark = nbMark;
    }

    public float getMark_average() {
        return mark_average;
    }

    public void setMark_average(float mark_average) {
        this.mark_average = mark_average;
    }

    public Mark() {
    }

    public int getMark() {
        return mark;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
