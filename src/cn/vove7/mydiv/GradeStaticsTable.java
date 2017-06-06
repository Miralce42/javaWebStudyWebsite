package cn.vove7.mydiv;

import beans.StudentGradeStatics;

import java.util.ArrayList;

/**
 * Created by Vove on 2017/6/6.
 * 构造统计html内容
 */
public class GradeStaticsTable {
    public static String getTableContent(ArrayList<StudentGradeStatics> studentGradeStaticsList) {
        StringBuilder builder = new StringBuilder();
        int index=1;
        for (StudentGradeStatics studentGradeStatics : studentGradeStaticsList) {
            builder.append(buildContent(index++,studentGradeStatics));
        }

        return builder.toString();
    }

    private static String buildContent(int index,StudentGradeStatics studentGradeStatics) {
        return "<tr>\n" +
                "   <td>"+index+"</td>\n" +
                "   <td>"+studentGradeStatics.getStudentId()+"</td>\n" +
                "   <td>"+studentGradeStatics.getName()+"</td>\n" +
                "   <td>"+studentGradeStatics.getMajorClassname()+"</td>\n" +
                "   <td>"+studentGradeStatics.getAvgScore()+"</td>\n" +
                "</tr>";
    }
}
