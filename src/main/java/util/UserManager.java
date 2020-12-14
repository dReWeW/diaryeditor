/*
 * @Author: your name
 * @Date: 2020-12-14 03:46:09
 * @LastEditTime: 2020-12-14 11:07:41
 * @LastEditors: Please set LastEditors
 * @Description: 控制user信息与数据库的链接
 * @FilePath: \dirayEditor\src\main\java\\util\UserManager.java
 */
package util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class UserManager {
    private static String driverClass, url, userName, passWord;
    private static Connection con;

    public static void readUser() throws IOException {
        Properties pro = new Properties();
        InputStream in = UserManager.class.getClassLoader().getResourceAsStream("jdbc.properties");
    }

    public static void writeUser() throws IOException {

    }
}
