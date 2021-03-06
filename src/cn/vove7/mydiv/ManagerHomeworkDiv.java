package cn.vove7.mydiv;


import WebDB.TeacherDAO;
import beans.StudentHomework;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

/**
 * Created by Vove on 2017/5/20.
 * 做作业管理块
 */
public class ManagerHomeworkDiv {
    private String hwId;
    private String title;
    private String body;
    private String buttonValue;

    public ManagerHomeworkDiv(String hwId, String title, String body, String buttonValue) {
        this.title = title;
        this.hwId = hwId;
        this.body = body;
        this.buttonValue = buttonValue;
    }

    @NotNull
    public static String buildStuBumps(String userId, ArrayList<StudentHomework> homeworkList) {
        StringBuilder builder = new StringBuilder();
        int i = 1;
        for (StudentHomework homework : homeworkList) {
            if (i % 3 == 1) {
                builder.append("<div class=\"row\">\n");//行div
            }
            String closingTime = homework.getClosingTime();

            //一个作业块
            ManagerHomeworkDiv managerHomeworkDiv = new ManagerHomeworkDiv(homework.getId(),
                    homework.getTitle(),
                    "截止时间:" + closingTime.substring(0, closingTime.length() - 2),
                    homework.statusToValue_stu()
            );

            builder.append(managerHomeworkDiv.buildBump_stu(userId, homework.getId()));//divContent

            if (i % 3 == 0) {
                builder.append("</div>");
            }
            i++;
        }
        if (i % 3 != 0) {
            builder.append("</div>");
        }
        return builder.toString();
    }

    public static void buildTeaBumps(ArrayList<StudentHomework> homeworkList,
                                     StringBuilder unclosedBuilder,
                                     StringBuilder closedBuilder,
                                     StringBuilder deletedBuilder) {

        int unclosedNum = 1;
        boolean haveClosedHomework = false;
        boolean haveUnclosedHomework = false;
        boolean haveDeletedHomework = false;
        int closedNum = 1;
        int deletedNum = 1;
        for (StudentHomework homework : homeworkList) {
            if (unclosedNum % 3 == 1) {
                unclosedBuilder.append("<div class=\"row\">\n");//行div
            }
            if (closedNum % 3 == 1) {
                closedBuilder.append("<div class=\"row\">\n");
            }
            if (deletedNum % 3 == 1) {
                deletedBuilder.append("<div class=\"row\">\n");
            }
            String closingTime = homework.getClosingTime();
            ManagerHomeworkDiv managerHomeworkDiv = new ManagerHomeworkDiv(homework.getId(),
                    homework.getTitle(),
                    "截止时间:" + closingTime.substring(0, closingTime.length() - 2),
                    homework.statusToValue_tea()
            );

            switch (homework.getHomeworkStatus()) {
                case CLOSED:
                    closedBuilder.append(managerHomeworkDiv.buildBump_tea(false));
                    closedNum++;
                    haveClosedHomework = true;
                    break;
                case UNCLOSED:
                    unclosedBuilder.append(managerHomeworkDiv.buildBump_tea(false));
                    unclosedNum++;
                    haveUnclosedHomework = true;
                    break;
                case DELETED:
                    deletedBuilder.append(managerHomeworkDiv.buildBump_tea(true));
                    deletedNum++;
                    haveDeletedHomework = true;
                    break;

            }

            if (unclosedNum % 3 == 1) {
                unclosedBuilder.append("</div>");
            }
            if (closedNum % 3 == 1) {
                closedBuilder.append("</div>");
            }
            if (deletedNum % 3 == 1) {
                deletedBuilder.append("</div>");
            }
        }
        //结尾
        if (haveUnclosedHomework && unclosedNum % 3 != 1) {
            unclosedBuilder.append("</div>");
        }
        if (haveClosedHomework && closedNum % 3 != 1) {
            closedBuilder.append("</div>");
        }
        if (haveDeletedHomework && closedNum % 3 != 1) {
            deletedBuilder.append("</div>");
        }
    }


    @NotNull
    private String buildBump_stu(String userId, String homeworkId) {//学生端
        String url = buttonValue.equals("查看") ? "browserHomework.jsp" : "doHomework.jsp";
        String score ="";
        if(buttonValue.equals("查看")){
            score = TeacherDAO.getStudentGrade(userId, homeworkId);
        }
        String scoreString = "";
        if (score != null&&!score.equals("")) {
            scoreString = "<div align='right' style='color:red'>" + score + "分</div>";
        }
        return
                " <div class=\"col-md-4\">\n" +
                        "     <div class=\"panel\">\n" +
                        "         <div class=\"panel-heading\">\n" +
                        "             <div class=\"page-title\">\n" +
                        "                 " + title + "\n" +
                        "             </div>\n" +
                        "         </div>\n" +
                        "         <div class=\"panel-body\">\n"
                        + scoreString + body +
                        "         </div>\n" +
                        "         <div class=\"panel-buttonValue\">\n" +
                        "           <a href=" + url + "?homeworkId=" + hwId + " class='floatButton'>  " + buttonValue + "</a>\n" +
                        "         </div>\n" +
                        "     </div>\n" +
                        " </div>\n";
    }

    @NotNull
    @Contract(pure = true)
    private String buildBump_tea(boolean isDeleted) {//构造开放作业块

        String buttonValue =
                "<a href=" + (!isDeleted ? ("Re-editHomework.jsp") : "editDeletedHomework.jsp") + "?homeworkId=" + hwId + " class='floatButton'>"
                        + (!isDeleted ? "重新编辑" : "重新编辑发布") + "</a>" +
                        (!isDeleted ? "<a href=studentsHomeworkList.jsp?homeworkId=" + hwId + " class='floatButton'> 批改作业</a>" : "");

        return
                " <div class=\"col-md-4\">\n" +
                        "            <div class=\"panel\">\n" +
                        "                <div class=\"panel-heading\">\n" +
                        "                    <div class=\"page-title\">\n" +
                        "                        " + title + "\n" +
                        "                    </div>\n" +
                        "                </div>\n" +
                        "                <div class=\"panel-body\">\n" +
                        "\n" + body +
                        "                </div>\n" +
                        "                <div class=\"panel-buttonValue\">\n" +
                        buttonValue +

                        "                </div>\n" +
                        "            </div>\n" +
                        "        </div>\n";
    }
}
