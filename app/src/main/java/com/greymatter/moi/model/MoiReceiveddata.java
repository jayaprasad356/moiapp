package com.greymatter.moi.model;

public class MoiReceiveddata {
    String id,name,mobile,location,amount;

    public MoiReceiveddata() {

    }

    public MoiReceiveddata(String id, String name, String mobile, String location, String amount) {
        this.id = id;
        this.name = name;
        this.mobile = mobile;
        this.location = location;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
