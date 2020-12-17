/*
 * @Descripttion: 
 * @version: 
 * @Author: 郑浩龙-2018141493022
 * @Date: 2020-12-17 08:31:10
 * @LastEditors: 郑浩龙-2018141493022
 * @LastEditTime: 2020-12-17 09:08:46
 */
package util;

public class DiaryClass {
    public String author;
    public String title;
    public String date;

    public DiaryClass() {
        // do nothong...
    }

    public void addDiary(String au, String til, String da) {
        author = au;
        title = til;
        date = da;
    }

    public DiaryClass(String au, String til, String da) {
        author = au;
        title = til;
        date = da;
    }
}