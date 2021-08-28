package SQL;

import java.sql.*;

public class sql {
    // MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/game?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    static final String USER = "root";
    static final String PASS = "root";
    static final String TABLE_NAME = "record";
    static Connection conn;
    static Statement stmt;
    static ResultSet rs;
    static PreparedStatement ps;

    public Connection getConnection(){
        try{
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try{
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return conn;
    }


    public static void getalldata() {
        try{
            sql s = new sql();
            conn = s.getConnection();
            stmt = conn.createStatement();
            String sql = "SELECT * FROM " + TABLE_NAME;
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                // 通过字段检索
                int id  = rs.getInt("id");
                String name = rs.getString("playername");
                Date date = rs.getDate("date");
                int time = rs.getInt("time");

                // 输出数据
                System.out.print("ID: " + id);
                System.out.print(", Playername " + name);
                System.out.print(", date: " + date);
                System.out.print(", time: " + time);
                System.out.println("\n");
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally{
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
    }

    public static void addData(String playername, Date date, int time){
        try {
            sql s = new sql();
            conn = s.getConnection();
            stmt = conn.createStatement();
            ps = conn.prepareStatement("INSERT INTO " + TABLE_NAME + "( `playername`, `date`, `time`) VALUES (?, ?, ?)");
            ps.setString(1,playername);
            ps.setDate(2,date);
            ps.setInt(3,time);
            ps.executeUpdate();
            System.out.println("Saved Successfully!");
        }
        catch (SQLException se){
            se.printStackTrace();
        }
    }


}
