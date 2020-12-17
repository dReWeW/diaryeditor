/*
 * @Descripttion: 日记编辑GUI
 * @version: 
 * @Author: 郑浩龙-2018141493022
 * @Date: 2020-12-13 19:49:34
 * @LastEditors: 郑浩龙-2018141493022
 * @LastEditTime: 2020-12-17 16:26:07
 */
package GUI;

import java.awt.event.MouseAdapter;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.*;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.awt.Color;

import java.util.Date;

import util.DiaryManager;
import util.UserManager;
import util.DiaryClass;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
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
import javax.swing.border.BevelBorder;

import javax.swing.BoxLayout;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;

public class DiaryWriteGUI extends JFrame {
    private static final long serialVersionUID = 4L;

    // 当前窗口状态
    private String presentUser;
    private String selectedDiaryTitle;
    // 日记管理器
    private DiaryManager myDiaryManager;

    // 各组件
    private String diaryNames[];
    private int diaryNums;
    private JPanel panel;
    private JPanel tabPanel;
    private JMenuBar menuBar;
    private JMenu fileMenu;
    private JMenuItem newFile;
    private JMenuItem deleteFile;
    private JMenuItem saveFile;
    private JScrollPane diaryScrollBar;
    private JList<DiaryClass> diaryList;
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
    private JPanel textPanel;
    private DiaryClass[] diaryClasses;
    private JPanel listPanel;
    private JPanel editorPanel;
    private DefaultListModel<DiaryClass> lm;

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
        // 创建当前窗口的日记管理器
        myDiaryManager = new DiaryManager();

        // 当前窗口初始化
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Notion Desktop");
        setBounds(100, 100, LayOut.WINDOW_WIDTH, LayOut.WINDOW_HEIGHT);
        setResizable(false);

        // panel初始化
        panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        setContentPane(panel);
        // 菜单初始化
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        fileMenu = new JMenu("文件");
        menuBar.add(fileMenu);
        newFile = new JMenuItem("新建日记");
        newFile.setIcon(new ImageIcon(LayOut.resourcePath + "new.png"));
        fileMenu.add(newFile);
        newFile.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                event_new();
            }
        });
        deleteFile = new JMenuItem("删除日记");
        deleteFile.setIcon(new ImageIcon(LayOut.resourcePath + "delete.jpg"));
        fileMenu.add(deleteFile);
        deleteFile.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                String dauthor = diaryList.getSelectedValue().author;
                String dtitle = diaryList.getSelectedValue().title;
                myDiaryManager.deleteDiary(dauthor, dtitle);
            }
        });
        saveFile = new JMenuItem("保存日记");
        saveFile.setIcon(new ImageIcon(LayOut.resourcePath + "save.jpg"));
        fileMenu.add(saveFile);
        saveFile.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                String uauthor = diaryList.getSelectedValue().author;
                String utitle = diaryList.getSelectedValue().title;
                String ucontent = diaryEditor.getText();
                myDiaryManager.diaryUpdate(utitle, uauthor, ucontent);
            }
        });
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
        alignLeftButton.registerKeyboardAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (LayOut.DEBUG) {
                    System.out.println("actionPerformed!");
                }
                event_back();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_L, KeyEvent.CTRL_DOWN_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
        ImageIcon alignLeftImageIcon = new ImageIcon(
                "H:\\JavaWorkSpace\\vscode_for_java\\dirayEditor\\src\\main\\resources\\left.png");
        alignLeftButton.setIcon(alignLeftImageIcon);

        alignCenterButton = new JButton();
        alignCenterButton.setToolTipText("居中对齐");
        alignCenterButton.registerKeyboardAction(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (LayOut.DEBUG) {
                    System.out.println("actionPerformed!");
                }
                event_back();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.CTRL_DOWN_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW);
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

        // **********/
        // 日记列表初始化
        diaryNames = new String[LayOut.DIARYSIZE];
        diaryNums = 0;
        lm = new DefaultListModel<DiaryClass>();
        diaryList = new JList<>(lm);
        MyJListRender cellRender = new MyJListRender();
        diaryList.setCellRenderer(cellRender);
        if (LayOut.DEBUG) {
            lm.addElement(new DiaryClass("大龙", "与海航的一天", "2020-12-17"));
            diaryList.setFixedCellHeight(50);
        }
        // diaryList.setFixedCellWidth(200);
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

        textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.X_AXIS));
        textPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel.add(textPanel);
        listPanel = new JPanel();
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        editorPanel = new JPanel();
        editorPanel.setLayout(new BoxLayout(editorPanel, BoxLayout.X_AXIS));
        textPanel.add(listPanel);
        textPanel.add(editorPanel);
        // 日记编辑区
        diaryEditor = new JTextPane();
        diaryEditor.setBounds(new Rectangle(0, 31, 568, 179));
        // 滚动条
        editorScrollBar = new JScrollPane();
        diaryScrollBar = new JScrollPane();
        editorScrollBar.setViewportView(diaryEditor);
        diaryScrollBar.setViewportView(diaryList);
        listPanel.add(diaryList);
        listPanel.add(diaryScrollBar);
        editorPanel.add(diaryEditor);
        editorPanel.add(editorScrollBar);

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

    // 设置标题的子对话框
    public class SetTitleGUI extends JFrame {
        private static final long serialVersionUID = 6L;
        private JLabel label;
        private JTextField titleField;
        private JButton confirmButton;
        private JPanel panel;

        public SetTitleGUI() {
            // 窗体初始化
            setTitle("请输入日记标题");
            setResizable(false);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            // 画板初始化
            panel = new JPanel();
            setBounds(new Rectangle(100, 100, 300, 240));
            setContentPane(panel);
            panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, new Color(200, 200, 200, 1),
                    new Color(100, 100, 100, 1)));
            panel.setLayout(null);
            label = new JLabel();
            confirmButton = new JButton("确定");
            titleField = new JTextField();
            label.setBounds(40, 80, 100, 30);
            confirmButton.setBounds(180, 80, 60, 30);
            titleField.setBounds(80, 80, 100, 30);
            label.setIcon(new ImageIcon(LayOut.resourcePath + "input.jpg"));
            confirmButton.addMouseListener(new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    String diaryTitle = titleField.getText();
                    // 添加日记
                    Date createDate = new Date();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    String date = formatter.format(createDate);
                    myDiaryManager.addDiary(diaryTitle, presentUser, date);
                    selectedDiaryTitle = diaryTitle;
                    diaryEditor.setText("");
                    diaryNums++;
                    lm.addElement(new DiaryClass(presentUser, diaryTitle, date));
                    setVisible(false);
                }
            });
            panel.add(label);
            panel.add(titleField);
            panel.add(confirmButton);
        }
    }

    private void event_read() {

    }

    private void event_new() {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                SetTitleGUI setTitle = new SetTitleGUI();
                setTitle.setVisible(true);
            }
        });
    }

    private void event_delete() {

    }

    private void event_back() {
        StartGUI.runGUI();
        setVisible(false);
    }

    private void event_save() {

    }

}