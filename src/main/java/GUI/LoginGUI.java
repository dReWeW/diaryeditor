/*
 * @Descripttion: 注册界面GUI
 * @version: 1.0
 * @Author: 郑浩龙-2018141493022
 * @Date: 2020-12-13 19:49:19
 * @LastEditors: Please set LastEditors
 * @LastEditTime: 2020-12-15 11:58:15
 */
package GUI;

import java.awt.event.*;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class LoginGUI extends JFrame {
    private static final long serialVersionUID = 2L;
    private JButton loginButton;
    private JButton backButton;
    private JLabel userLabel;
    private JLabel pwLabel;
    private JTextField userField;
    private JTextField pwField;
    private JPanel panel;

    public static void callLoginGUI() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                LoginGUI mynewLoginGUI = new LoginGUI();
                mynewLoginGUI.setVisible(true);
            }
        });
        ;
    }

    public LoginGUI() {
        init();
    }

    public void init() {
        // panel初始化
        panel = new JPanel();
        setTitle("用户登录");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(new Rectangle(100, 100, 700, 500));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel.setLayout(null);
        setContentPane(panel);
        // 各组件初始化
        userLabel = new JLabel("用户名：");
        userLabel.setBounds(new Rectangle(175, 400, 124, 45));
        userLabel.setFont(new Font(LayOut.WINDOWFONT, Font.BOLD, 32));
        panel.add(userLabel);

        userField = new JTextField();
        userField.setBounds(new Rectangle(200, 400, 300, 30));
        userField.setColumns(10);
        panel.add(userField);

        pwLabel = new JLabel("密码：");
        pwLabel.setBounds(new Rectangle(175, 300, 124, 45));
        pwLabel.setFont(new Font(LayOut.WINDOWFONT, Font.BOLD, 32));
        panel.add(pwLabel);

        pwField = new JTextField();
        pwField.setBounds(new Rectangle(200, 300, 300, 30));
        pwField.setColumns(24);
        panel.add(pwField);

        // 登录按钮初始化
        loginButton = new JButton("登录");
        loginButton.setBounds(new Rectangle(65, 263, LayOut.BUTTON_WIDTH, LayOut.BUTTON_HEIGHT));
        loginButton.setFont(new Font(LayOut.WINDOWFONT, Font.PLAIN, 12));
        loginButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent mouseEvent) {
                event_Login();
            }
        });

        loginButton.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER)
                    event_Login();
            }
        });
        panel.add(loginButton);
        // 返回按钮初始化
        backButton = new JButton("返回");
        backButton.setBounds(new Rectangle(489, 263, LayOut.BUTTON_WIDTH, LayOut.BUTTON_HEIGHT));
        backButton.setFont(new Font(LayOut.WINDOWFONT, Font.PLAIN, 12));
        backButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent mouseEvent) {
                event_back();
            }
        });
        panel.add(backButton);
    }

    // 数据库访问（待施工）
    private void event_Login() {
        setVisible(false);
        DiaryWriteGUI.callDiaryGUI();
    }

    private void event_back() {
        setVisible(false);
        StartGUI.runGUI();
    }
}