/*
 * @Author: your name
 * @Date: 2020-12-14 03:46:09
 * @LastEditTime: 2020-12-15 23:30:21
 * @LastEditors: Please set LastEditors
 * @Description: 控制user信息与数据库的链接
 * @FilePath: \dirayEditor\src\main\java\\util\UserManager.java
 */
package util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

import javax.xml.namespace.QName;

public class UserManager {
    private static String driverClass, url, userName, passWord;
    private static Connection con;
    static {
        try {
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

    public static void readUser() throws IOException {
        try {
            Class.forName(driverClass);
            con = DriverManager.getConnection(url, userName, passWord);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void writeUser() throws IOException {

    }
}
