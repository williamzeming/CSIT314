package csit314;

import netscape.javascript.JSObject;

import java.sql.*;
import java.util.ArrayList;
import java.io.*;
import org.json.JSONArray;

import org.json.JSONObject;


enum PayType{
    VISA,MASTERCARD,UNIONPAY,PAYPAL;
}

public class Main {
    ArrayList curCustomer = new ArrayList<Customer>();
    public static void main(String[] args) throws Exception {


        readJson();

        final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        final String DB_URL = "jdbc:mysql://localhost:3306/RUNOOB";

        // MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
        //static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        //static final String DB_URL = "jdbc:mysql://localhost:3306/RUNOOB?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";


        // 数据库的用户名与密码，需要根据自己的设置
        final String USER = "root";
        final String PASS = "123456";

        Connection conn = null;
        Statement stmt = null;
        try{
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT id, name, url FROM websites";
            ResultSet rs = stmt.executeQuery(sql);

            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                int id  = rs.getInt("id");
                String name = rs.getString("name");
                String url = rs.getString("url");

                // 输出数据
                System.out.print("ID: " + id);
                System.out.print(", 站点名称: " + name);
                System.out.print(", 站点 URL: " + url);
                System.out.print("\n");
            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");


    }
    public int getNewID(){
        int newID = 0;
        newID++;
        return newID;
    }

    public static void readJson() throws Exception {

        String json = "{\"contacDetails\": {\n" +   //JSON text in the file is written here
                "            \"firstName\": \"Ram\",\n" +
                "            \"lastName\": \"Sharma\"\n" +
                "    },\n" +
                "    \"phoneNumbers\": [\n" +
                "            {\n" +
                "                \"type\": \"home\",\n" +
                "                \"phone-number\": \"212 888-2365\",\n" +
                "            }\n" +
                "    ]" +
                "}";

        JSONObject ob = new JSONObject(json);
        register(ob);

    }
    public static void register(JSONObject ob) throws Exception {


        //getting first and last name
        String firstName = ob.getJSONObject("contacDetails").getString("firstName");
        String lastName = ob.getJSONObject("contacDetails").getString("lastName");
        System.out.println("FirstName " + firstName);
        System.out.println("LastName " + lastName);

        //loop for printing the array as phoneNumber is stored as array.
        JSONArray arr = ob.getJSONArray("phoneNumbers");
        for (int i = 0; i < arr.length(); i++) {
            String post_id = arr.getJSONObject(i).getString("phone-number");
            System.out.println(post_id);
        }


    }



//    public boolean checkPW(String userID , String inputPW) {
//        //根据userID去sql找PW
//        String PW = getPW();
//        boolean PWbool = false;
//        if (PW == inputPW){
//            PWbool = true;
//        }
//        return PWbool;
//    }
//    public ResultSet loadCusByID(String userID){
//        ResultSet rs = stmt.executeQuery(sql);
//        rs.close();
//        stmt.close();
//        conn.close();
//        return rs;
//    }
//
//    public ResultSet getVehicleByID(String userID){
//        ResultSet rs;
//        return rs;
//    }
//    public Customer cuslogin(String userID) throws SQLException {
//        ResultSet rs = loadCusByID(userID);
//        String    userName = rs.getString("Name");
//        String    gender = rs.getString("gender");
//        String    DOB = rs.getString("DOB");
//        String    phone = rs.getString("phone");
//        String    password = rs.getString("password");
//        String    email = rs.getString("email");
//        String    vipStart = rs.getString("vipStart");
//        String    vipEnd = rs.getString("vipEnd");
//        ResultSet vehcle = getVehicleByID(userID);
//        ArrayList vehcleList = new ArrayList<Vehicle>();
//        vehcleList.add(vehcle);
//        Pre_Order curOrder = new Pre_Order();
//        Customer cus = new Customer(userID,userName,gender,DOB,phone,password,email,vipStart,vipEnd,vehcleList,curOrder);
//        return cus;
//    }
//    public Vehicle createVehicle(String plateNum,String model){
//        Vehicle vehicle = new Vehicle(plateNum,model);
//        return vehicle;
//    }
}
