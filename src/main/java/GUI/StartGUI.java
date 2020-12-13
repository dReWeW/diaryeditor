
/*
 * @Author: 郑浩龙-2018141493022
 * @Date: 2020-12-13 14:27:04
 * @LastEditTime: 2020-12-13 19:48:44
 * @LastEditors: 郑浩龙-2018141493022
 * @Description: 欢迎界面的GUI
 * @FilePath: \dirayEditor\src\main\java\GUI\StartGUI.java
 */
package GUI;

import java.awt.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StartGUI extends JFrame {
    /**
     *
     */
    // SerialVersionUID
    // 添加原因：https://blog.csdn.net/virgoboy2004/article/details/5728205
    private static final long serialVersionUID = 1L;
    // 存放组件的面板
    private JPanel panel;
    // 面板组件
    private JLabel WelcomeLabel;
    private JButton loginButton;
    private JButton registerButton;

    public static void main(String args[]) {
        runGUI();
    }

    public static void runGUI() {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                StartGUI myNewDiaryEditorGui = new StartGUI();
                myNewDiaryEditorGui.setVisible(true);
            }
        });
    }

    public StartGUI() {
        init();
    }

    // 初始化
    public void init() {
        panel = new JPanel();
        setTitle("日记编辑器");

    }

    private void event_Login() {

    }

    private void event_Register() {

    }

}