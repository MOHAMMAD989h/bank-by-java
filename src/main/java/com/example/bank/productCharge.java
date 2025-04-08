package com.example.bank;

class productCharge {
    private String price;
    private String type;

    public String getType() {
        return type;
    }


    public String getPrice() {
        return price;
    }


    public productCharge(String type, String price) {
        this.type = type;
        this.price = price;
    }

}
