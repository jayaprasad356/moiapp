package com.greymatter.moi.model;

public class MoiCompareFunctions {
    String id,user_id,function_name,image,place,date,amount;

    public MoiCompareFunctions() {

    }

    public MoiCompareFunctions(String id, String user_id, String function_name, String image, String place, String date, String amount) {
        this.id = id;
        this.user_id = user_id;
        this.function_name = function_name;
        this.image = image;
        this.place = place;
        this.date = date;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getFunction_name() {
        return function_name;
    }

    public void setFunction_name(String function_name) {
        this.function_name = function_name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
