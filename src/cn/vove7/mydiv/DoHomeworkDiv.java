package cn.vove7.mydiv;


import WebDB.StudentDAO;
import beans.ChoiceHomework;
import beans.CompletionHomework;
import beans.Homework;
import beans.Users;

/**
 * Created by Vove on 2017/5/25.
 * 学生做作业布局
 */
public class DoHomeworkDiv {
   private Users student;
   private Homework homework;

   public DoHomeworkDiv(Users student, Homework homework) {
      this.student = student;
      this.homework = homework;
   }

   public String getChoicesValue() {
      StringBuilder builder = new StringBuilder();
      int index = 1;
      for (ChoiceHomework choiceHomework : homework.getChoiceHomeworkList()) {
         builder.append(buildChoiceValue(index++, choiceHomework));
      }
      return builder.toString();
   }

   public String getCompletionsValue() {
      StringBuilder builder = new StringBuilder();
      int index = 1;
      for (CompletionHomework completionHomework : homework.getCompletionHomeworkList()) {
         builder.append(getCompletionValue(index++, completionHomework));
      }
      return builder.toString();
   }

   private String buildChoiceValue(int index, ChoiceHomework choiceHomework) {
      String str,
              check_A = "",
              check_B = "",
              check_C = "",
              check_D = "";
      String savedKey = new StudentDAO(student).getChoiceAnswer(choiceHomework.getId());
      switch (ChoiceOption.valueOf(savedKey)) {
         case A:
            check_A = "checked";
            break;
         case B:
            check_B = "checked";
            break;
         case C:
            check_C = "checked";
            break;
         case D:
            check_D = "checked";
            break;
      }
      str = " <div class=\"choiceBump\"><h3>\n" +"问题" +index+"</h3>"+
              "     <div class=\"choice-question\">"+
              "<input type=\"hidden\" name=\"choiceId_"+index+"\" value=\""+choiceHomework.getId()+"\">"+
              ":"+choiceHomework.getQuestion()+"\n" +
              "     </div>\n" +
              "     <ul style=\"list-style-type:none\">\n" +
              "         <li class=\"option\">\n" +
              "             <input class=\"radio\" name=\"choice_"+index+"\"  "+check_A+" type=\"radio\" value=\"A\">"+choiceHomework.getChoice_A()+"\n" +
              "         </li>\n" +
              "         <li class=\"option\">\n" +
              "             <input class=\"radio\" name=\"choice_"+index+"\" "+check_B+" type=\"radio\" value=\"B\">"+choiceHomework.getChoice_B()+"\n" +
              "         </li>\n" +
              "         <li class=\"option\">\n" +
              "             <input class=\"radio\" name=\"choice_"+index+"\" "+check_C+" type=\"radio\" value=\"C\">"+choiceHomework.getChoice_C()+"\n" +
              "         </li>\n" +
              "         <li class=\"option\">\n" +
              "             <input class=\"radio\" name=\"choice_"+index+"\" "+check_D+" type=\"radio\" value=\"D\">"+ choiceHomework.getChoice_D()+"\n" +
              "         </li>\n" +
              "     </ul>\n" +
              " </div>";
      return str;
   }

   private String getCompletionValue(int index, CompletionHomework completionHomework) {
      String str;
      String savedAnswer=new StudentDAO(student).getCompletionAnswer(completionHomework.getId());
      str = ":<div class=\"completionBump\">" +
              "<input type=\"hidden\" name=\"completionId_"+index+"\" value=\""+completionHomework.getId()+"\">"+
              "<h3>问题"+index+"</h3><div class=\"completion-question\">"
              +completionHomework.getCompletionContent()+"</div>" +
              " 回答：<input class=\"completion_answer\" id=\"completion_answer_"+index+"\"\n" +
              "            name=\"completion_answer_"+index+"\" type=\"text\" value=\""+savedAnswer+"\">\n" +
              "</div>";
      return str;
   }

   public enum ChoiceOption {
      A, B, C, D,NULL
   }
}


