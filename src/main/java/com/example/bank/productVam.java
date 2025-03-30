package com.example.bank;

public class productVam {
    String name, price,sood,time,pagePath,discription,phoneNum;
    public productVam(String name, String price, String sood, String time, String pagePath, String discription,String phoneNum) {
        this.name = name;
        this.price = price;
        this.sood = sood;
        this.time = time;
        this.pagePath = pagePath;
        this.discription = discription;
        this.phoneNum = phoneNum;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public String getSood() {
        return sood;
    }
    public void setSood(String sood) {
        this.sood = sood;
    }
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getPagePath() {
        return pagePath;
    }
    public void setPagePath(String pagePath) {
        this.pagePath = pagePath;
    }
    public String getDiscription() {
        return discription;
    }
    public void setDiscription(String discription) {
        this.discription = discription;
    }
    public String getPhoneNum() {
        return phoneNum;
    }
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }
}
