package com.example.bank;

public class productAccount {
    String userName,bankLogo,bankName,shbaNum,cartNum,accountNum, cvv2,mojudi,engeza;
    public productAccount(String userName, String bankLogo, String bankName, String shbaNum, String cartNum, String accountNum,String cvv2,String mojudi,String engeza) {
        this.userName = userName;
        this.bankLogo = bankLogo;
        this.bankName = bankName;
        this.shbaNum = shbaNum;
        this.cartNum = cartNum;
        this.accountNum = accountNum;
        this.cvv2 = cvv2;
        this.mojudi = mojudi;
        this.engeza = engeza;
    }
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getBankLogo() {
        return bankLogo;
    }
    public void setBankLogo(String bankLogo) {
        this.bankLogo = bankLogo;
    }
    public String getBankName() {
        return bankName;
    }
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
    public String getShbaNum() {
        return shbaNum;
    }
    public void setShbaNum(String shbaNum) {
        this.shbaNum = shbaNum;
    }
    public String getCartNum() {
        return cartNum;
    }
    public void setCartNum(String cartNum) {
        this.cartNum = cartNum;
    }
    public String getAccountNum() {
        return accountNum;
    }
    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }
    public String getCvv2() {return cvv2;}
    public void setCvv2(String cvv2) {
        this.cvv2 = cvv2;
    }
    public String getMojudi() {
        return mojudi;
    }
    public void setMojudi(String mojudi) {
        this.mojudi = mojudi;
    }
    public String getEngeza() {
        return engeza;
    }
    public void setEngeza(String engeza) {
        this.engeza = engeza;
    }
}
