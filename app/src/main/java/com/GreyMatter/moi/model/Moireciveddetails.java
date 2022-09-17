package com.GreyMatter.moi.model;

public class Moireciveddetails {
    String id,imgview,funname,funplace,fundate;

    public Moireciveddetails() {

    }

    public Moireciveddetails(String id, String imgview, String funname, String funplace, String fundate) {
        this.id = id;
        this.imgview = imgview;
        this.funname = funname;
        this.funplace = funplace;
        this.fundate = fundate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgview() {
        return imgview;
    }

    public void setImgview(String imgview) {
        this.imgview = imgview;
    }

    public String getFunname() {
        return funname;
    }

    public void setFunname(String funname) {
        this.funname = funname;
    }

    public String getFunplace() {
        return funplace;
    }

    public void setFunplace(String funplace) {
        this.funplace = funplace;
    }

    public String getFundate() {
        return fundate;
    }

    public void setFundate(String fundate) {
        this.fundate = fundate;
    }
}
