/*
 * @Descripttion: 登录界面GUI
 * @version: 1.0
 * @Author: 郑浩龙-2018141493022
 * @Date: 2020-12-13 19:49:10
 * @LastEditors: 郑浩龙-2018141493022
 * @LastEditTime: 2020-12-14 11:46:29
 */
package GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import java.sql.Connection;
//sql部分待施工

public class RegisterGUI extends JFrame {
    private JPanel registerPanel;
    private JLabel userNameLabel;
    private JLabel userPwLabel;
    private JLabel userConfirmLabel;
    private JTextField userNameField;
    private JTextField userPwField;
    private JTextField userConfirmField;
    private JButton signUpButton;
    private JButton backButton;

    public static void startRegister() {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                RegisterGUI registerGUI = new RegisterGUI();
                registerGUI.setVisible(true);
            }
        });
    }

    public RegisterGUI() {
        init();
    }

    public void init() {
        registerPanel = new JPanel();
        setTitle("用户注册");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 700, 500);
        registerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(registerPanel);
        registerPanel.setLayout(null);

        userNameLabel = new JLabel("用户名");
        userNameLabel.setBounds(new Rectangle(175, 175, 124, 45));
        userNameLabel.setFont(new Font(LayOut.WINDOWFONT, Font.BOLD, 32));
        registerPanel.add(userNameLabel);

        userNameField = new JTextField();
        userNameField.setBounds(new Rectangle(200, 175, 200, 30));
        userNameField.setColumns(10);
        registerPanel.add(userNameField);

        userPwLabel = new JLabel("用户名");
        userNameLabel.setBounds(new Rectangle(175, 175, 124, 45));
        userNameLabel.setFont(new Font(LayOut.WINDOWFONT, Font.BOLD, 32));
        registerPanel.add(userNameLabel);

        userNameField = new JTextField();
        userNameField.setBounds(new Rectangle(200, 175, 200, 30));
        userNameField.setColumns(10);
        registerPanel.add(userNameField);

    }

    private void event_signup() {

    }

    private void event_back() {

    }

}