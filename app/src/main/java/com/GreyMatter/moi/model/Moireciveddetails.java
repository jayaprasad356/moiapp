package com.GreyMatter.moi.model;

public class Moireciveddetails {
    String id,s_no,name,mobile_no,place,amount;

    public Moireciveddetails() {

    }

    public Moireciveddetails(String id, String s_no, String name, String mobile_no, String place, String amount) {
        this.id = id;
        this.s_no = s_no;
        this.name = name;
        this.mobile_no = mobile_no;
        this.place = place;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getS_no() {
        return s_no;
    }

    public void setS_no(String s_no) {
        this.s_no = s_no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(String mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
