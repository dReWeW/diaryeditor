/*
 * @Descripttion: 日记编辑GUI
 * @version: 
 * @Author: 郑浩龙-2018141493022
 * @Date: 2020-12-13 19:49:34
 * @LastEditors: Please set LastEditors
 * @LastEditTime: 2020-12-15 11:58:46
 */
package GUI;

import java.awt.event.MouseAdapter;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.*;
import java.awt.event.MouseEvent;
import util.Diary;
import util.UserManager;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.plaf.basic.BasicTabbedPaneUI.MouseHandler;

public class DiaryWriteGUI extends JFrame {
    private static final long serialVersionUID = 4L;
    private JPanel panel;
    private JTabbedPane tabbedPane;// 选项卡
    private JButton readButton;
    private JButton newButton;
    private JButton deleteButton;
    private JButton backButton;
    private JTextPane diaryEditor;
    private JButton saveButton;
    private JInternalFrame editorFrame;
    private JTextField titleField;
    private JTextField authorField;
    private JPanel tabPanel;
    private JFileChooser fileChooser;

    public static void callDiaryGUI() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                DiaryWriteGUI diaryGUI = new DiaryWriteGUI();
                diaryGUI.setVisible(true);
            }
        });
    }

    public DiaryWriteGUI() {
        init();
    }

    public void init() {
        panel = new JPanel();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel);
        setTitle("Notion Desktop");
        setBounds(100, 100, LayOut.WINDOW_WIDTH, LayOut.WINDOW_HEIGHT);
        panel.setLayout(null);
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setContentPane(panel);

        tabbedPane = new JTabbedPane();
        tabbedPane.setToolTipText("Notion");
        tabbedPane.setBounds(0, 0, 574, 67);
        panel.add(tabbedPane);
        tabPanel = new JPanel();
        tabbedPane.addTab("操作菜单", null, tabPanel, null);

        readButton = new JButton("读取日记");
        newButton = new JButton("读取日记");
        deleteButton = new JButton("读取日记");
        backButton = new JButton("读取日记");
        // 选项卡组件添加
        tabPanel.add(readButton);
        tabPanel.add(newButton);
        tabPanel.add(deleteButton);
        tabPanel.add(backButton);
        // 按钮添加监听事件
        readButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent mouseEvent) {
                event_read();
            }
        });

        readButton.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent keyEvent) {
                event_read();
            }
        });

        newButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent mouseEvent) {
                event_new();
            }
        });

        newButton.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent keyEvent) {
                event_new();
            }
        });

        deleteButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent mouseEvent) {
                event_delete();
            }
        });

        deleteButton.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent keyEvent) {
                event_delete();
            }
        });

        backButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent mouseEvent) {
                event_back();
            }
        });

        backButton.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent keyEvent) {
                event_back();
            }
        });
        // 编辑窗体初始化
        editorFrame = new JInternalFrame("日记编辑窗口");
        editorFrame.setBounds(0, 77, 584, 500);
        panel.add(editorFrame);
        editorFrame.getContentPane().setLayout(null);

        diaryEditor = new JTextPane();
        diaryEditor.setBounds(new Rectangle(0, 31, 568, 179));
        editorFrame.getContentPane().add(diaryEditor);

        titleField = new JTextField();
        if (LayOut.DEBUG)
            titleField.setText("标题");
        titleField.setFont(new Font(LayOut.WINDOWFONT, Font.BOLD, 8));
        titleField.setBounds(new Rectangle(5, 5, 200, 20));
        titleField.setColumns(20);
        editorFrame.getContentPane().add(titleField);

        authorField = new JTextField();
        if (LayOut.DEBUG)
            authorField.setText("作者");
        authorField.setFont(new Font(LayOut.WINDOWFONT, Font.ITALIC, 6));
        authorField.setBounds(new Rectangle(300, 5, 100, 20));
        authorField.setColumns(16);
        editorFrame.getContentPane().add(authorField);

        saveButton = new JButton("保存");
        saveButton.setBounds(465, 213, 93, 23);
        saveButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent mouseEvent) {
                event_save();
            }
        });

        editorFrame.setVisible(true);
    }

    private void event_read() {

    }

    private void event_new() {

    }

    private void event_delete() {

    }

    private void event_back() {

    }

    private void event_save() {

    }

}