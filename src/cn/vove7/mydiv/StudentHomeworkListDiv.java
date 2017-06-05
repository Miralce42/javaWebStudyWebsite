package cn.vove7.mydiv;

import beans.HomeworkStudentStatus;
import beans.StudentHomework.HomeworkStatus;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


/**
 * Created by Vove on 2017/5/29.
 * 学生作业列表块
 */
public class StudentHomeworkListDiv {

    @NotNull
    public static String getDivValue(ArrayList<HomeworkStudentStatus> homeworkStudentStatusArrayList, HomeworkStatus homeworkStatus, boolean b){
        StringBuilder builder=new StringBuilder();
//        int i = 1;
//        boolean haveStudent = false;
        for (HomeworkStudentStatus homeworkStudentStatus : homeworkStudentStatusArrayList) {
            if (b||homeworkStudentStatus.getHomeworkStatus() == homeworkStatus) {
//                if (i % 3 == 1) {
//                    builder.append("<div class=\"row\">\n");//行div
//                }
//                haveStudent = true;
                builder.append(homeworkStudentStatus.getStatusDivContent());
//                if (i % 3 == 0) {
//                    builder.append("</div>");
//                }
//                i++;
            }
        }
//        //收尾
//        if (haveStudent && i % 3 != 1) {
//            builder.append("</div>");
//        }
        return builder.toString();
    }
}
