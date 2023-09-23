package com.example.a31b;

public class Announcements {
    private String announce;
    private String postedby;
    private String posteddate;

    public Announcements() {
    }

    public Announcements(String announce, String postedby, String posteddate) {
        this.announce = announce;
        this.postedby = postedby;
        this.posteddate = posteddate;
    }

    public String getAnnounce() {
        return announce;
    }

    public void setAnnounce(String announce) {
        this.announce = announce;
    }

    public String getPostedby() {
        return postedby;
    }

    public void setPostedby(String postedby) {
        this.postedby = postedby;
    }

    public String getPosteddate() {
        return posteddate;
    }

    public void setPosteddate(String posteddate) {
        this.posteddate = posteddate;
    }

}
