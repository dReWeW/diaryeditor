/*
 * @Descripttion: 日记编辑GUI
 * @version: 
 * @Author: 郑浩龙-2018141493022
 * @Date: 2020-12-13 19:49:34
 * @LastEditors: Please set LastEditors
 * @LastEditTime: 2020-12-15 23:09:40
 */
package GUI;

import java.awt.event.MouseAdapter;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import util.Diary;
import util.UserManager;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.plaf.basic.BasicTabbedPaneUI.MouseHandler;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;

public class DiaryWriteGUI extends JFrame {
    private static final long serialVersionUID = 4L;
    private String diaryNames[];
    private int diaryNums;
    private JPanel panel;
    private JPanel tabPanel;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem newFile;
    private JMenuItem readFile;
    private JMenuItem deleteFile;
    private JMenuItem saveFile;
    private JScrollPane diaryScrollBar;
    private JList diaryList;
    private JTabbedPane tabbedPane;// 编辑区
    private JButton backButton;// 返回按钮
    private JButton alignLeftButton;
    private JButton alignCenterButton;
    private JButton alignRightButton;
    private JButton alignJustifyButton;
    private JButton backgroundColorButton;
    private JButton selectAllButton;
    private JTextPane diaryEditor;
    private JScrollPane editorScrollBar;
    private JTextField titleField;
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
        // panel初始化
        panel = new JPanel();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(panel);
        setTitle("Notion Desktop");
        setBounds(100, 100, LayOut.WINDOW_WIDTH, LayOut.WINDOW_HEIGHT);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setContentPane(panel);
        // 工作区初始化
        tabbedPane = new JTabbedPane();
        tabbedPane.setToolTipText("Notion");
        tabbedPane.setBounds(0, 0, 574, 67);
        panel.add(tabbedPane);
        tabPanel = new JPanel();
        tabbedPane.addTab("操作菜单", null, tabPanel, null);
        tabPanel.setLayout(new BoxLayout(tabPanel, BoxLayout.X_AXIS));
        tabPanel.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
        // 工作区各按钮初始化
        backButton = new JButton();
        ImageIcon backImageIcon = new ImageIcon(
                "H:\\JavaWorkSpace\\vscode_for_java\\dirayEditor\\src\\main\\resources\\back.png");
        // 添加快捷键（太麻烦了JAVA添加快捷键，只添加一个）
        backButton.registerKeyboardAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                event_back();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_B, KeyEvent.CTRL_DOWN_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
        backButton.setIcon(backImageIcon);

        alignLeftButton = new JButton();
        ImageIcon alignLeftImageIcon = new ImageIcon(
                "H:\\JavaWorkSpace\\vscode_for_java\\dirayEditor\\src\\main\\resources\\left.png");
        alignLeftButton.setIcon(alignLeftImageIcon);

        alignCenterButton = new JButton();
        ImageIcon alignCenterImageIcon = new ImageIcon(
                "H:\\JavaWorkSpace\\vscode_for_java\\dirayEditor\\src\\main\\resources\\center.png");
        alignCenterButton.setIcon(alignCenterImageIcon);

        alignRightButton = new JButton();
        ImageIcon alignRightImageIcon = new ImageIcon(
                "H:\\JavaWorkSpace\\vscode_for_java\\dirayEditor\\src\\main\\resources\\right.png");
        alignRightButton.setIcon(alignRightImageIcon);

        alignJustifyButton = new JButton();
        ImageIcon alignJustifyImageIcon = new ImageIcon(
                "H:\\JavaWorkSpace\\vscode_for_java\\dirayEditor\\src\\main\\resources\\justify.png");
        alignJustifyButton.setIcon(alignJustifyImageIcon);

        backgroundColorButton = new JButton();
        ImageIcon backgroundColorImageIcon = new ImageIcon(
                "H:\\JavaWorkSpace\\vscode_for_java\\dirayEditor\\src\\main\\resources\\color.png");
        backgroundColorButton.setIcon(backgroundColorImageIcon);

        selectAllButton = new JButton();
        ImageIcon selectAllImageIcon = new ImageIcon(
                "H:\\JavaWorkSpace\\vscode_for_java\\dirayEditor\\src\\main\\resources\\paste.png");
        selectAllButton.setIcon(selectAllImageIcon);

        // 监听事件添加

        // ********* */
        // 日记列表初始化
        diaryNames = new String[LayOut.DIARYSIZE];
        diaryNums = 0;
        diaryList = new JList(diaryNames);
        tabPanel.add(backButton);
        tabPanel.add(alignLeftButton);
        tabPanel.add(alignCenterButton);
        tabPanel.add(alignRightButton);
        tabPanel.add(alignJustifyButton);
        tabPanel.add(backgroundColorButton);
        tabPanel.add(selectAllButton);

        diaryEditor = new JTextPane();
        diaryEditor.setBounds(new Rectangle(0, 31, 568, 179));
        panel.add(diaryEditor);

        titleField = new JTextField();
        if (LayOut.DEBUG)
            titleField.setText("标题");
        titleField.setFont(new Font(LayOut.WINDOWFONT, Font.BOLD, 8));
        titleField.setBounds(new Rectangle(5, 5, 200, 20));
        titleField.setColumns(20);

    }

    private void diaryAdd(String[] diarys, int num, String diaryTitle) {
        if (num < LayOut.DIARYSIZE) {
            diarys[num] = diaryTitle;
            num++;
        } else {
            JOptionPane.showMessageDialog(this, "日记数量已达最大", "错误", JOptionPane.ERROR_MESSAGE);
        }
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