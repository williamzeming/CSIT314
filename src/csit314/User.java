package csit314;

import java.util.ArrayList;

public class User {
    protected String userID;
    protected String userName;
    protected String gender;
    protected String DOB;
    protected String phoneNum;
    protected String password;
    protected ArrayList<Payment> paymentList = new ArrayList<Payment>();
    protected ArrayList<Finshed_Order> orderList = new ArrayList<Finshed_Order>();
    public User(){

    }
    public User(String userID, String userName, String gender, String DOB, String phoneNum, String password, ArrayList<Payment> paymentList, ArrayList<Finshed_Order> orderList) {
        this.userID = userID;
        this.userName = userName;
        this.gender = gender;
        this.DOB = DOB;
        this.phoneNum = phoneNum;
        this.password = password;
        this.paymentList = paymentList;
        this.orderList = orderList;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Payment> getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(ArrayList<Payment> paymentList) {
        this.paymentList = paymentList;
    }

    public ArrayList<Finshed_Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(ArrayList<Finshed_Order> orderList) {
        this.orderList = orderList;
    }
//    public String toJson(){
//
//
//    }
}
