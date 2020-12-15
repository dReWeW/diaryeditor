/*
 * @Descripttion: 登录界面GUI
 * @version: 1.0
 * @Author: 郑浩龙-2018141493022
 * @Date: 2020-12-13 19:49:10
 * @LastEditors: Please set LastEditors
 * @LastEditTime: 2020-12-15 11:58:27
 */
package GUI;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

// import java.sql.Connection;
//sql部分待施工

public class RegisterGUI extends JFrame {
    private static final long serialVersionUID = 3L;
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
        userNameLabel.setBounds(new Rectangle(175, 400, 124, 45));
        userNameLabel.setFont(new Font(LayOut.WINDOWFONT, Font.BOLD, 32));
        registerPanel.add(userNameLabel);

        userNameField = new JTextField();
        userNameField.setBounds(new Rectangle(200, 400, 200, 30));
        userNameField.setColumns(10);
        registerPanel.add(userNameField);

        userPwLabel = new JLabel("输入密码");
        userPwLabel.setBounds(new Rectangle(175, 300, 124, 45));
        userPwLabel.setFont(new Font(LayOut.WINDOWFONT, Font.BOLD, 32));
        registerPanel.add(userPwLabel);

        userPwField = new JTextField();
        userPwField.setBounds(new Rectangle(200, 300, 200, 30));
        userPwField.setColumns(24);
        registerPanel.add(userPwField);

        userConfirmLabel = new JLabel("确认密码");
        userConfirmLabel.setBounds(new Rectangle(175, 200, 124, 45));
        userConfirmLabel.setFont(new Font(LayOut.WINDOWFONT, Font.BOLD, 32));
        registerPanel.add(userConfirmLabel);

        userConfirmField = new JTextField();
        userConfirmField.setBounds(new Rectangle(200, 200, 200, 30));
        userConfirmField.setColumns(10);
        registerPanel.add(userConfirmField);

        backButton = new JButton("返回");
        backButton.setBounds(489, 263, LayOut.BUTTON_WIDTH, LayOut.BUTTON_HEIGHT);
        backButton.setFont(new Font(LayOut.WINDOWFONT, Font.PLAIN, 12));
        backButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent mouseEvent) {
                event_back();
            }
        });

        backButton.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER)
                    event_back();
            }
        });

        registerPanel.add(backButton);

        // 注册Button设置（待施工）
        signUpButton = new JButton("注册");
        signUpButton.setBounds(65, 263, LayOut.BUTTON_WIDTH, LayOut.BUTTON_HEIGHT);
        signUpButton.setFont(new Font(LayOut.WINDOWFONT, Font.PLAIN, 12));
        signUpButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent mouseEvent) {
                event_signup();
            }
        });

        signUpButton.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER)
                    event_signup();
            }
        });

        registerPanel.add(signUpButton);
    }

    private void event_signup() {

    }

    private void event_back() {
        setVisible(false);
        StartGUI.runGUI();
    }

}