/*
 * @Author: your name
 * @Date: 2020-12-13 14:27:04
 * @LastEditTime: 2020-12-13 14:31:40
 * @LastEditors: Please set LastEditors
 * @Description: In User Settings Edit
 * @FilePath: \dirayEditor\src\main\java\GUI\StartGUI.java
 */
import java.awt.EventQueue;
 public class StartGUI{
     public void init(){
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame = new IndexGUI(); //实例化frame
                    frame.setVisible(true); //设置窗体可见性
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
     }
     public 
 }