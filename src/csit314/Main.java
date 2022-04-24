package csit314;

import netscape.javascript.JSObject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

enum PayType{
    VISA,MASTERCARD,UNIONPAY,PAYPAL;
}

public class Main {
    ArrayList curCustomer = new ArrayList<Customer>();
    public static void main(String[] args) {

    }
    public int getNewID(){
        int newID = 0;
        newID++;
        return newID;
    }
    public Customer registerCustomer(int newID){
    }
    public boolean checkPW(String userID , String inputPW) {
        //根据userID去sql找PW
        String PW = getPW();
        boolean PWbool = false;
        if (PW == inputPW){
            PWbool = true;
        }
        return PWbool;
    }
    public ResultSet loadCusByID(String userID){
        ResultSet rs = stmt.executeQuery(sql);
        rs.close();
        stmt.close();
        conn.close();
        return rs;
    }

    public ResultSet getVehicleByID(String userID){
        ResultSet rs;
        return rs;
    }
    public Customer cuslogin(String userID) throws SQLException {
        ResultSet rs = loadCusByID(userID);
        String    userName = rs.getString("Name");
        String    gender = rs.getString("gender");
        String    DOB = rs.getString("DOB");
        String    phone = rs.getString("phone");
        String    password = rs.getString("password");
        String    email = rs.getString("email");
        String    vipStart = rs.getString("vipStart");
        String    vipEnd = rs.getString("vipEnd");
        ResultSet vehcle = getVehicleByID(userID);
        ArrayList vehcleList = new ArrayList<Vehicle>();
        vehcleList.add(vehcle);
        Pre_Order curOrder = new Pre_Order();
        Customer cus = new Customer(userID,userName,gender,DOB,phone,password,email,vipStart,vipEnd,vehcleList,curOrder);
        return cus;
    }
    public Vehicle createVehicle(String plateNum,String model){
        Vehicle vehicle = new Vehicle(plateNum,model);
        return vehicle;
    }
}
