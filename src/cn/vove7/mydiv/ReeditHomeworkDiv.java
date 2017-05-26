package cn.vove7.mydiv;

import beans.ChoiceHomework;
import beans.CompletionHomework;
import beans.Homework;

/**
 * Created by Vove on 2017/5/25.
 * 重编辑页面Div
 */
public class ReeditHomeworkDiv {
   private Homework homework;


   public ReeditHomeworkDiv(Homework homework) {
      this.homework = homework;
   }

   public String getChoiceDiv() {
      int index=1;
      StringBuilder builder=new StringBuilder();
      for(ChoiceHomework choiceHomework:homework.getChoiceHomeworkList()){
         builder.append(getChoiceValue_Teacher(index++,choiceHomework));
      }
      return builder.toString();
   }

   public String getCompletionDiv(){
      StringBuilder builder=new StringBuilder();
      int index=1;
      for(CompletionHomework completionHomework:homework.getCompletionHomeworkList()){
         builder.append(getCompletionValue(index++,completionHomework));
      }
      return builder.toString();
   }


   public String getHead() {
      return  "<div class=\"float-right\">\n" +
              "    <input class=\"homework-title\" name=\"homeworkTitle\" type=\"text\" value=\"" + homework.getHomeworkTitle() + "\" placeholder=\"作业标题\">\n" +
              "    <a class=\"floatButton\" onclick=\"document.form.submit()\">发布</a>\n" +
              "</div>\n" +
              "<div align=\"right\" style=\"padding-right: 12%\">\n" +
              "    开始时间:<input CLASS=\"score\" type=\"text\" name=\"beginTime\" id=\"beginTime\" value=\"" + homework.getBeginTime() + "\" title=\"选择开始时间\"\n" +
              "           onclick=\"laydate({istime:true,format: 'YYYY-MM-DD hh:mm:ss'})\">\n" +
              "    结束时间:<input CLASS=\"score\" type=\"text\" name=\"endTime\" id=\"endTime\" value=\"" + homework.getEndTime() + "\" placeholder=\"选择结束时间\" onclick=\"laydate({istime:true,format: 'YYYY-MM-DD hh:mm:ss'})\">\n" +
              "</div>";
   }

   private String getCompletionValue(int index, CompletionHomework completionHomework){
      String completionFieldString;
      completionFieldString = "<div class=\"completion_field\">" +
              "<div class=\"title\">问题" + index + ":</div><div align=\"right\" style=\"margin-bottom: 10px;\">" +
              "分数：<input class=\"score\" id=\"comp_score_"+index+"\" name=\"comp_score_"+index+"\" type=\"text\" value=\"" + completionHomework.getScore() + "\"><a class=\"floatButton\" onclick=\"deleteCompletion(" + index + ")\">删除</a></div>" +
              "<textarea class=\"ckeditor\" name=\"question_content_" + index + "\" " +
              "id=\"question_content_" + index + "\">" + completionHomework.getCompletionContent() + "</textarea>" +
              "</div>";
      return completionFieldString;
   }


   private String getChoiceValue_Teacher(int index, ChoiceHomework choiceHomework) {
      String score = choiceHomework.getScore(),
              s_A = choiceHomework.getChoice_A(),
              s_B = choiceHomework.getChoice_B(),
              s_C = choiceHomework.getChoice_C(),
              s_D = choiceHomework.getChoice_D(),
              questionContent = choiceHomework.getQuestion(),
              ref_keyStringValue = choiceHomework.getRef_ky();

      String ckediorString = "<textarea class=\"ckeditor\" name=\"choice-title_" + index + "\" " +
              "id=\"choice-title_" + index + "\">" + questionContent + "</textarea>";

      String choiceFieldString;
      choiceFieldString = "<div class=\"choice_field\" id=\"choice_field_" + index + "\"><table width=\"100%\"><tr>" +
              "<div class=\"title narrow\">题目" + index + ":</div><div align=\"right\" style=\"margin-bottom: 10px;\">分数：<input class=\"score\" id=\"score_"
              + index + "\" name=\"score_" + index + "\" type=\"text\" value=\""
              + score + "\"><a class=\"floatButton\" onclick=\"deleteChoice(" + index + ")\">删除</a></div><td colspan=\"4\">" + ckediorString +
              "</td></tr><tr><td class=\"narrow\">选项A:</td><td><input class=\"choice\" id=\"choice_" + index + "_A\" type=\"text\" name=\"choice_" + index + "_A\" value=\""
              + s_A + "\" placeholder=\"选项A\"></td><td class=\"narrow\">选项B:</td>" +
              "<td><input class=\"choice\" type=\"text\" id=\"choice_" + index + "_B\" name=\"choice_" + index + "_B\"" +
              " value=\"" + s_B + "\" placeholder=\"选项B\"></td></tr><tr><td class=\"narrow\">选项C:</td><td><input class=\"choice\" id=\"choice_"
              + index + "_C\" type=\"text\" name=\"choice_" + index + "_C\" value=\"" + s_C + "\" placeholder=\"选项C\"></td>" +
              "<td class=\"narrow\">选项D:</td><td><input class=\"choice\" type=\"text\" id=\"choice_" + index + "_D\" name=\"choice_" + index + "_D\" value=\""
              + s_D + "\" placeholder=\"选项D\"></td> </tr> <tr> <td class=\"narrow\">" +
              "参考答案:</td><td>" + ref_keyStringValue + "</td></tr> </table></div>";
      return choiceFieldString;
   }
}
