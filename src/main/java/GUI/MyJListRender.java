/*
 * @Descripttion: 
 * @version: 
 * @Author: 郑浩龙-2018141493022
 * @Date: 2020-12-17 08:34:21
 * @LastEditors: 郑浩龙-2018141493022
 * @LastEditTime: 2020-12-17 15:10:40
 */
package GUI;

import util.DiaryClass;
import javax.swing.JList;
import javax.swing.DefaultListCellRenderer;
import java.awt.Component;
import javax.swing.ImageIcon;
import java.awt.Color;

public class MyJListRender extends DefaultListCellRenderer {
    private static final long serialVersionUID = 5L;

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
            boolean cellHasFocus) {
        if (value instanceof DiaryClass) {
            DiaryClass l = (DiaryClass) value;
            String f = "<html><body> <div style=\"word-spacing:2px;width:60px;height:25px;line-height:5px;font:bold 8px 宋体\">"
                    + "<p style=\"text-indent:0cm;\"> %s</p><p style=\"font-size:6px\">%s</p>"
                    + "<p style=\"font-size=6px\"> %s<p></div></body></html>";
            String temp = String.format(f, l.title, l.author, l.date);
            setText(temp);
        } else {
            setText(null);
        }
        if (isSelected) {
            setBackground(Color.CYAN);
        } else {
            setBackground(Color.WHITE);
        }
        setIcon(new ImageIcon(LayOut.resourcePath + "diaryIcon.jpg"));
        return this;

    }

}