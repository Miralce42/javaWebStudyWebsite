package cn.vove7.mydiv;


import beans.StudentHomework;
import com.intellij.codeInsight.navigation.BackgroundUpdaterTask;

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

   public static void buildDiv(ArrayList<StudentHomework> homeworkList, StringBuilder unclosedBuilder, StringBuilder closedBuilder) {

      int unclosedNum = 1;
      boolean haveClosedHomework = false;
      int closedNum = 1;
      for (StudentHomework homework : homeworkList) {
         if (unclosedNum % 3 == 1) {
            unclosedBuilder.append("<div class=\"row\">\n");//行div
         }
         if (closedNum % 3 == 1) {
            closedBuilder.append("<div class=\"row\">\n");
         }
         String closingTime = homework.getClosingTime();
         ManagerHomeworkDiv managerHomeworkDiv = new ManagerHomeworkDiv(homework.getId(),
                 homework.getTitle(),
                 "截止时间:" + closingTime.substring(0, closingTime.length() - 2),
                 homework.statusToValue_tea()
         );

         switch (homework.getHomeworkStatus()) {
            case CLOSED:
               closedBuilder.append(managerHomeworkDiv.toTeaString());
               closedNum++;
               haveClosedHomework = true;
               break;
            case UNCLOSED:
               unclosedBuilder.append(managerHomeworkDiv.toTeaString());
               unclosedNum++;
               break;
         }

         if (unclosedNum % 3 == 1) {
            unclosedBuilder.append("</div>");
         }
         if (closedNum % 3 == 1) {
            closedBuilder.append("</div>");
         }
      }
      //结尾
      if (unclosedNum % 3 != 1) {
         unclosedBuilder.append("</div>");
      }
      if (haveClosedHomework && closedNum % 3 != 1) {
         closedBuilder.append("</div>");
      }
   }




   public String toStuString() {
   String url= buttonValue.equals("查看")?"browserHomework.jsp":"doHomework.jsp";
      return
              "        <div class=\"col-md-4\">\n" +
              "            <div class=\"panel\">\n" +
              "                <div class=\"panel-heading\">\n" +
              "                    <div class=\"page-title\">\n" +
              "                        " + title + "\n" +
              "                    </div>\n" +
              "                </div>\n" +
              "                <div class=\"panel-body\">\n"
                      + body +
              "                </div>\n" +
              "                <div class=\"panel-buttonValue\">\n" +
              "                  <a href="+url+"?homeworkId="+hwId+ " class='floatButton'>  " + buttonValue + "</a>\n" +
              "                </div>\n" +
              "            </div>\n" +
              "        </div>\n";
   }
   public String toTeaString(){
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
                      "             <a href=Re-editHomework.jsp?homeworkId="+hwId+ " class='floatButton'>"+"重新编辑"+"</a>"+
              "                  <a href=studentsHomeworkList.jsp?homeworkId="+hwId+ " class='floatButton'> 批改作业</a>\n" +
              "                </div>\n" +
              "            </div>\n" +
              "        </div>\n";
   }
}
