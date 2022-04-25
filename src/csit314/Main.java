package csit314;

import java.sql.*;
import java.util.ArrayList;

import org.json.JSONArray;

import org.json.JSONObject;


enum PayType {
    VISA, MASTERCARD, UNIONPAY, PAYPAL;
}

public class Main {
    ArrayList curCustomer = new ArrayList<Customer>();
    static String json = "{\"contacDetails\": {\n" +   //JSON text in the file is written here
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

    public static void main(String[] args) throws Exception {
        JSONObject ob = readJson(json);
    }
    //初始化连接sql
    public static Connection connectSql() throws ClassNotFoundException, SQLException {
        final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        final String DB_URL      = "jdbc:mysql://localhost:3306/RUNOOB";

        // MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
        //static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
        //static final String DB_URL = "jdbc:mysql://localhost:3306/RUNOOB?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";


        // 数据库的用户名与密码，需要根据自己的设置
        final String USER = "root";
        final String PASS = "123456";

        Connection conn = null;
        Statement  stmt = null;
        // 注册 JDBC 驱动
        Class.forName(JDBC_DRIVER);

        // 打开链接
        System.out.println("连接数据库...");
        conn = DriverManager.getConnection(DB_URL, USER, PASS);
        return conn;
    }
    //sql select
    public static Customer sqlCusSelect(JSONObject ob) throws SQLException, ClassNotFoundException {
        Connection conn = connectSql();
        int cusID = ob.getInt("cusID");
        String sql = "select * from CUSTOMER where cusID = ?";
        PreparedStatement psmt = conn.prepareStatement(sql);
        psmt.setInt(1,cusID);
        ResultSet rs = psmt.executeQuery();
        ArrayList<Vehicle> vehicleList = sqlVehicleSelect(ob);
        Pre_Order curOrder = new Pre_Order();
        Customer cus = new Customer(rs.getInt("cusId"),rs.getString("userName"),rs.getString("gender"),rs.getString("DOB"), rs.getNString("phoneNum"),rs.getString("password"),
                                    rs.getString("email"),rs.getString("vipStart"),rs.getString("vipEnd"),vehicleList,curOrder);
        return cus;
    }

    public static Professional sqlProSelect(JSONObject ob) throws SQLException, ClassNotFoundException {
        Connection conn = connectSql();
        int proID = ob.getInt("proID");
        String sql = "select * from PROFESSION where proID = ?";
        PreparedStatement psmt = conn.prepareStatement(sql);
        psmt.setInt(1,proID);
        ResultSet rs = psmt.executeQuery();
        Professional pro = new Professional(rs.getInt("proId"),rs.getString("userName"),rs.getString("gender"),rs.getString("DOB"), rs.getNString("phoneNum"),rs.getString("password"),
                                    rs.getString("email"),rs.getFloat("plevel"),rs.getDouble("balance"),rs.getString("location"));
        return pro;
    }
    public static ArrayList sqlVehicleSelect(JSONObject ob) throws SQLException, ClassNotFoundException {
        Connection conn = connectSql();
        int cusID = ob.getInt("cusID");
        String sql = "select * from VEHICLE where cusID = ?";
        PreparedStatement psmt = conn.prepareStatement(sql);
        psmt.setInt(1,cusID);
        ResultSet rs = psmt.executeQuery();
        ArrayList vehicleList = new ArrayList<Vehicle>();
        while (rs.next()){
            Vehicle vehicle = new Vehicle(rs.getString("plateNum"),rs.getString("model"));
            vehicleList.add(vehicle);
        }
        return vehicleList;
    }

    public static JSONObject sqlOrderSelect(JSONObject ob) throws SQLException, ClassNotFoundException {
        Connection conn = connectSql();
        int userID = ob.getInt("userID");
        String sql = "select * from ORDER where userID = ?";
        PreparedStatement psmt = conn.prepareStatement(sql);
        psmt.setInt(1,userID);
        ResultSet rs = psmt.executeQuery();
        JSONObject result = new JSONObject();
        while (rs.next()){
            //返回一个Json
        }
        return result;
    }


    //sql insert customer
    public static boolean sqlCusInsert(JSONObject ob) throws SQLException, ClassNotFoundException {
        Connection con = connectSql();

        int userID =0;
        String userName="";
        String gender="";
        String DOB="";
        String phoneNum="";
        String password="";
        String email="";
        String vipStart="";
        String vipEnd="";

        String sql="insert into CUSTOMER (userID,userName,gender,DOB,phoneNum,password,email,vipStart,vipEnd) values (?,?,?,?,?,?,?,?,?);";

        PreparedStatement psmt = con.prepareStatement(sql);
        int columnOfSql=1;
        psmt.setInt(columnOfSql, userID);
        psmt.setString(columnOfSql++, userName);
        psmt.setString(columnOfSql++, gender);
        psmt.setString(columnOfSql++, DOB);
        psmt.setString(columnOfSql++, phoneNum);
        psmt.setString(columnOfSql++, password);
        psmt.setString(columnOfSql++, email);
        psmt.setString(columnOfSql++, vipStart);
        psmt.setString(columnOfSql++, vipEnd);

        return psmt.execute();
    }
    //sql insert professional
    public static boolean sqlProInsert(JSONObject ob) throws SQLException, ClassNotFoundException {
        Connection con = connectSql();

        int userID =0;
        String userName="";
        String gender="";
        String DOB="";
        String phoneNum="";
        String password="";
        String email="";
        float plevel = 1;
        double balance = 1.0;
        String location = "";

        String sql="insert into CUSTOMER (userID,userName,gender,DOB,phoneNum,password,email,plevel,balance,location) values (?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement psmt = con.prepareStatement(sql);
        int columnOfSql=1;
        psmt.setInt(columnOfSql, userID);
        psmt.setString(columnOfSql++, userName);
        psmt.setString(columnOfSql++, gender);
        psmt.setString(columnOfSql++, DOB);
        psmt.setString(columnOfSql++, phoneNum);
        psmt.setString(columnOfSql++, password);
        psmt.setString(columnOfSql++, email);
        psmt.setFloat(columnOfSql++, plevel);
        psmt.setDouble(columnOfSql++, balance);
        psmt.setString(columnOfSql++, location);
        return psmt.execute();
    }
    //sql insert vehicle
    public static boolean sqlVehInsert(JSONObject ob) throws SQLException, ClassNotFoundException {
        Connection con = connectSql();

        int userID =0;
        String plateNum="";
        String model="";


        String sql="insert into CUSTOMER (userID,plateNum,model) values (?,?,?)";
        PreparedStatement psmt = con.prepareStatement(sql);
        int columnOfSql=1;
        psmt.setInt(columnOfSql, userID);
        psmt.setString(columnOfSql++, plateNum);
        psmt.setString(columnOfSql++, model);
        return psmt.execute();
    }
    //sql insert order
    public static boolean sqlOrdInsert(JSONObject ob) throws SQLException, ClassNotFoundException {
        Connection con = connectSql();

        String orderID="";
        String orderStartDate="";
        Customer customerID = new Customer();
        double price=1;
        Vehicle vehiclePlate = new Vehicle();
        String location="";
        String issue="";
        Professional professional= new Professional();
        String orderEndDate="";
        String review="";
        float rating=1;
        String payCardNum="";
        PayType payType=PayType.valueOf("VISA");
        String sql="insert into CUSTOMER (orderID,orderStartDate,customerID,price,vehiclePlate,location,issue,professional,orderEndDate,review,rating,payCardNum,payType) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement psmt = con.prepareStatement(sql);
        int columnOfSql=1;
        psmt.setString(columnOfSql, orderID);
        psmt.setString(columnOfSql++, orderStartDate);
        psmt.setInt(columnOfSql++, customerID.getUserID());
        psmt.setDouble(columnOfSql++, price);
        psmt.setString(columnOfSql++, vehiclePlate.getPlateNum());
        psmt.setString(columnOfSql++, location);
        psmt.setString(columnOfSql++, issue);
        psmt.setInt(columnOfSql++, professional.getUserID());
        psmt.setString(columnOfSql++, orderEndDate);
        psmt.setString(columnOfSql++, review);
        psmt.setFloat(columnOfSql++, rating);
        psmt.setString(columnOfSql++, payCardNum);
        psmt.setString(columnOfSql++, String.valueOf(payType));

        return psmt.execute();
    }

    //sql update customer
    public static boolean updateCustomer(JSONObject ob) throws SQLException, ClassNotFoundException {
        Connection con = connectSql();


        int userID =0;
        String userName="";
        String gender="";
        String DOB="";
        String phoneNum="";
        String password="";
        String email="";
        String vipStart="";
        String vipEnd="";

        String sql = "" +
                "update CUSTOMER " +
                "set userName=?,gender=?,DOB=?,phoneNum=?,password=?,email=?,vipStart=?,vipEnd=?," +
                "where userID=?";
        //预编译sql语句
        PreparedStatement psmt = con.prepareStatement(sql);
        int columnOfSql=1;
        //先对应SQL语句，给SQL语句传递参数
        psmt.setInt(columnOfSql, userID);
        psmt.setString(columnOfSql++, userName);
        psmt.setString(columnOfSql++, gender);
        psmt.setString(columnOfSql++, DOB);
        psmt.setString(columnOfSql++, phoneNum);
        psmt.setString(columnOfSql++, password);
        psmt.setString(columnOfSql++, email);
        psmt.setString(columnOfSql++, vipStart);
        psmt.setString(columnOfSql++, vipEnd);
        //执行SQL语句
        return psmt.execute();
    }
    //sql update Professional
    public static boolean updateProfessional(JSONObject ob) throws SQLException, ClassNotFoundException {
        Connection con = connectSql();

        int userID =0;
        String userName="";
        String gender="";
        String DOB="";
        String phoneNum="";
        String password="";
        String email="";
        float plevel = 1;
        double balance = 1.0;
        String location = "";

        String sql = "" +
                "update CUSTOMER " +
                "set userName=?,gender=?,DOB=?,phoneNum=?,password=?,email=?,plevel=?,balance=?,location=?" +
                "where userID=?";
        //预编译sql语句
        PreparedStatement psmt = con.prepareStatement(sql);
        int columnOfSql=1;
        //先对应SQL语句，给SQL语句传递参数
        psmt.setInt(columnOfSql, userID);
        psmt.setString(columnOfSql++, userName);
        psmt.setString(columnOfSql++, gender);
        psmt.setString(columnOfSql++, DOB);
        psmt.setString(columnOfSql++, phoneNum);
        psmt.setString(columnOfSql++, password);
        psmt.setString(columnOfSql++, email);
        psmt.setFloat(columnOfSql++, plevel);
        psmt.setDouble(columnOfSql++, balance);
        psmt.setString(columnOfSql++, location);
        //执行SQL语句
        return psmt.execute();
    }

    //sql delete
    public boolean sqlDeleteVehicle(JSONObject ob) throws SQLException, ClassNotFoundException {
        Connection conn = connectSql();
        int cusID = ob.getInt("cusID");
        String plantNum = ob.getString("plantNum");
        String sql = "delete * from VEHICLE where cusID = ? and plantNum = ?";
        PreparedStatement psmt = conn.prepareStatement(sql);
        psmt.setInt(1,cusID);
        psmt.setString(2,plantNum);
        return psmt.execute();
    }
    public ResultSet sqlSelect(String SQL) throws SQLException, ClassNotFoundException {
        Connection conn = connectSql();
        Statement  stmt = null;
        try {

            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL);

            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
            return rs;
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally {
            // 关闭资源
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            }// 什么都不做
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
        return null;
    }
    public boolean sqlInsert(String SQL) throws SQLException, ClassNotFoundException {
        Connection conn = connectSql();
        Statement  stmt = null;

    }



    //sql语句查询最后一个已存在的用户ID
    public int getNewID() throws SQLException {
        String    sql   = "";
        ResultSet rs    = sqlSelect(sql);
        int       newID = rs.getInt("id");
        newID++;
        return newID;
    }

    public static JSONObject readJson(String json) throws Exception {
        JSONObject ob = new JSONObject(json);
        return ob;
    }

    public static void register(JSONObject ob) throws Exception {
        //getting first and last name
        String firstName = ob.getJSONObject("contacDetails").getString("firstName");
        String lastName  = ob.getJSONObject("contacDetails").getString("lastName");
        System.out.println("FirstName " + firstName);
        System.out.println("LastName " + lastName);

        //loop for printing the array as phoneNumber is stored as array.
        JSONArray arr = ob.getJSONArray("phoneNumbers");
        for (int i = 0; i < arr.length(); i++) {
            String post_id = arr.getJSONObject(i).getString("phone-number");
            System.out.println(post_id);
        }
    }

    public boolean checkPW(int userID, String inputPW) throws SQLException {
        String sql = "";
        ResultSet rs = sqlSelect(sql);
        String  PW  = rs.getString("password");
        boolean PWbool = false;
        if (PW == inputPW) {
            PWbool = true;
        }
        return PWbool;
    }



    public Customer cuslogin(int userID) throws SQLException {
        String sql = "";
        ResultSet rs = sqlSelect(sql);
        String sqlVeh = "";
        ResultSet rsVeh = sqlSelect(sqlVeh);
        String    userName   = rs.getString("Name");
        String    gender     = rs.getString("gender");
        String    DOB        = rs.getString("DOB");
        String    phone      = rs.getString("phone");
        String    password   = rs.getString("password");
        String    email      = rs.getString("email");
        String    vipStart   = rs.getString("vipStart");
        String    vipEnd     = rs.getString("vipEnd");
        ArrayList vehcleList = createVehicle(rsVeh);
        Pre_Order curOrder = new Pre_Order();
        Customer  cus      = new Customer(userID, userName, gender, DOB, phone, password, email, vipStart, vipEnd, vehcleList, curOrder);
        return cus;
    }

    public ArrayList createVehicle(ResultSet rsVeh) throws SQLException {
        ArrayList vehcleList = new ArrayList<Vehicle>();
        while (rsVeh.next()){
            String    plateNum      = rsVeh.getString("plateNum");
            String    model   = rsVeh.getString("model");
            Vehicle vehicle = new Vehicle(plateNum, model);
            vehcleList.add(vehicle);
        }
        return vehcleList;
    }

    public boolean dropVehicle(int userID, String plateNum) throws SQLException {
        //通过userID和车牌号从vehicle里面删除一行
        String sql = "";
        ResultSet rs = sqlSelect(sql);

    }
}
