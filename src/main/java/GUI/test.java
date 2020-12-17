/*
 * @Descripttion: 
 * @version: 
 * @Author: 郑浩龙-2018141493022
 * @Date: 2020-12-17 15:38:47
 * @LastEditors: 郑浩龙-2018141493022
 * @LastEditTime: 2020-12-17 15:41:21
 */
package GUI;

import java.text.SimpleDateFormat;
import java.util.Date;

public class test {
    public static void main(String args[]) {
        Date t = new Date();
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
        String s = formater.format(t);
        System.out.println(s);
    }
}