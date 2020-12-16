/*
 * @Descripttion: 日记编辑GUI
 * @version: 
 * @Author: 郑浩龙-2018141493022
 * @Date: 2020-12-13 19:49:34
 * @LastEditors: Please set LastEditors
 * @LastEditTime: 2020-12-16 23:12:28
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
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.plaf.ListUI;
import javax.swing.plaf.basic.BasicTabbedPaneUI.MouseHandler;

import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
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
    private JPanel testPanel;

    // 测试方法
    public static void main(String args[]) {
        DiaryWriteGUI.callDiaryGUI();
    }

    {
        try {
            javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
        backButton.setToolTipText("返回上一级");
        ImageIcon backImageIcon = new ImageIcon(
                "H:\\JavaWorkSpace\\vscode_for_java\\dirayEditor\\src\\main\\resources\\back.png");
        // 添加快捷键（太麻烦了JAVA添加快捷键，只添加一个）
        backButton.registerKeyboardAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (LayOut.DEBUG) {
                    System.out.println("actionPerformed!");
                }
                event_back();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_B, KeyEvent.CTRL_DOWN_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
        backButton.setIcon(backImageIcon);

        alignLeftButton = new JButton("");
        alignLeftButton.setToolTipText("左对齐");
        ImageIcon alignLeftImageIcon = new ImageIcon(
                "H:\\JavaWorkSpace\\vscode_for_java\\dirayEditor\\src\\main\\resources\\left.png");
        alignLeftButton.setIcon(alignLeftImageIcon);

        alignCenterButton = new JButton();
        alignCenterButton.setToolTipText("居中对齐");
        ImageIcon alignCenterImageIcon = new ImageIcon(
                "H:\\JavaWorkSpace\\vscode_for_java\\dirayEditor\\src\\main\\resources\\center.png");
        alignCenterButton.setIcon(alignCenterImageIcon);

        alignRightButton = new JButton();
        alignRightButton.setToolTipText("右对齐");
        ImageIcon alignRightImageIcon = new ImageIcon(
                "H:\\JavaWorkSpace\\vscode_for_java\\dirayEditor\\src\\main\\resources\\right.png");
        alignRightButton.setIcon(alignRightImageIcon);

        alignJustifyButton = new JButton();
        alignJustifyButton.setToolTipText("自适应");
        ImageIcon alignJustifyImageIcon = new ImageIcon(
                "H:\\JavaWorkSpace\\vscode_for_java\\dirayEditor\\src\\main\\resources\\justify.png");
        alignJustifyButton.setIcon(alignJustifyImageIcon);

        backgroundColorButton = new JButton();
        backgroundColorButton.setToolTipText("背景颜色");
        ImageIcon backgroundColorImageIcon = new ImageIcon(
                "H:\\JavaWorkSpace\\vscode_for_java\\dirayEditor\\src\\main\\resources\\color.png");
        backgroundColorButton.setIcon(backgroundColorImageIcon);

        selectAllButton = new JButton();
        selectAllButton.setToolTipText("全选");
        ImageIcon selectAllImageIcon = new ImageIcon(
                "H:\\JavaWorkSpace\\vscode_for_java\\dirayEditor\\src\\main\\resources\\paste.png");
        selectAllButton.setIcon(selectAllImageIcon);

        // 监听事件添加

        // ********* */
        // 日记列表初始化
        diaryNames = new String[LayOut.DIARYSIZE];
        diaryNums = 0;
        if (LayOut.DEBUG) {
            diaryNames[0] = "与海航的一天scxasdasdasd";
            diaryNums += 1;
            diaryNames[1] = "海海";
        }
        DefaultListModel listModel = new DefaultListModel();
        listModel.addElement("海海");
        diaryList = new JList(listModel);
        diaryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // 设置JList布局
        // diaryList.setFont(new Font(LayOut.WINDOWFONT, Font.PLAIN, 20));
        // diaryList.setFixedCellHeight(20);
        tabPanel.add(backButton);
        tabPanel.add(alignLeftButton);
        tabPanel.add(alignCenterButton);
        tabPanel.add(alignRightButton);
        tabPanel.add(alignJustifyButton);
        tabPanel.add(backgroundColorButton);
        tabPanel.add(selectAllButton);

        testPanel = new JPanel();
        testPanel.setLayout(new BoxLayout(testPanel, BoxLayout.X_AXIS));
        testPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel.add(testPanel);
        // 日记编辑区
        diaryEditor = new JTextPane();
        diaryEditor.setBounds(new Rectangle(0, 31, 568, 179));
        // panel.setLayout(new BoxLayout(tabPanel, BoxLayout.X_AXIS));
        testPanel.add(diaryList);
        testPanel.add(diaryEditor);

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