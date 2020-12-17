/*
 * @Author: your name
 * @Date: 2020-12-14 03:46:09
 * @LastEditTime: 2020-12-17 17:57:14
 * @LastEditors: 郑浩龙-2018141493022
 * @Description: 控制user信息与数据库的链接
 * @FilePath: \dirayEditor\src\main\java\\util\UserManager.java
 */
package util;

import GUI.LayOut;

import java.io.IOException;
import java.io.InputStream;
import java.security.spec.ECFieldF2m;
import java.sql.*;
import java.util.Properties;

public class UserManager {
    private static String driverClass, url, userName, passWord;
    private static Connection con;
    static {
        try {
            System.out.println("static code block!\n");
            Properties pro = new Properties();
            InputStream in = UserManager.class.getClassLoader().getResourceAsStream("jdbc.properties");
            pro.load(in);
            driverClass = pro.getProperty("driver");
            url = pro.getProperty("url");
            userName = pro.getProperty("username");
            passWord = pro.getProperty("password");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean readUser(String username, String password) throws IOException {
        try {
            // Class.forName(driverClass);
            con = DriverManager.getConnection(url, userName, passWord);
            con.setAutoCommit(false);
            Statement stmt = con.createStatement();
            String sql = String.format(
                    "SELECT UserName,UserPassword from USERS WHERE UserName='%s' AND UserPassword=%s", username,
                    password);
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {// 如果有查询结果，直接允许登录
                con.close();
                rs.close();
                stmt.close();
                return true;
            } else {
                con.close();
                rs.close();
                stmt.close();
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;

    }

    public static void main(String args[]) {
        UserManager u = new UserManager();
        try {
            u.writeUser("drew", "123456");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeUser(String username, String password) throws IOException {
        try {
            // Class.forName(driverClass);
            con = DriverManager.getConnection(url, userName, passWord);
            con.setAutoCommit(false);
            Statement stmt = con.createStatement();
            String sql = String.format("INSERT INTO USERS VALUES('%s','%s')", username, password);
            // ResultSet rs = stmt.executeQuery(sql);
            stmt.executeUpdate(sql);
            stmt.executeUpdate(sql);
            con.commit();
            // 创建用户表
            con.close();
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
