
/*
 * @Author: 郑浩龙-2018141493022
 * @Date: 2020-12-13 14:27:04
 * @LastEditTime: 2020-12-14 10:55:44
 * @LastEditors: 郑浩龙-2018141493022
 * @Description: 欢迎界面的GUI
 * @FilePath: \dirayEditor\src\main\java\GUI\StartGUI.java
 */
package GUI;

import java.awt.*;
import java.awt.event.*;
// import java.awt.event.KeyAdapter;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MouseInputAdapter;

import org.w3c.dom.events.MouseEvent;

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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(new Rectangle(100, 100, LayOut.WINDOW_WIDTH, LayOut.WINDOW_HEIGHT));
        panel.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(panel);
        panel.setLayout(null);

        WelcomeLabel = new JLabel("日记编辑器");
        WelcomeLabel.setBounds(new Rectangle(132, 74, 386, 35));
        WelcomeLabel.setFont(new Font("Microsoft YaHei", Font.BOLD, 32));
        panel.add(WelcomeLabel);
        loginButton = new JButton("登录");
        loginButton.setBounds(new Rectangle(65, 263, LayOut.BUTTON_WIDTH, LayOut.BUTTON_HEIGHT));
        loginButton.setFont(new Font("Microsoft YaHei", Font.PLAIN, 12));
        loginButton.addMouseListener(new MouseInputAdapter() {
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
        registerButton = new JButton("注册");
        registerButton.setBounds(new Rectangle(489, 263, LayOut.BUTTON_WIDTH, LayOut.BUTTON_HEIGHT));
        registerButton.setFont(new Font(LayOut.WINDOWFONT, Font.PLAIN, 12));
        registerButton.addMouseListener(new MouseInputAdapter() {
            public void mouseClicked(MouseEvent mouseEvent) {
                event_Register();
            }
        });

        registerButton.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == KeyEvent.VK_ENTER)
                    event_Register();
            }
        });

        panel.add(registerButton);
        panel.add(loginButton);
    }

    private void event_Login() {
        setVisible(false);
        // new LoginGUI().loginGUI();

    }

    private void event_Register() {
        setVisible(false);
        RegisterGUI.startRegister();
    }

}