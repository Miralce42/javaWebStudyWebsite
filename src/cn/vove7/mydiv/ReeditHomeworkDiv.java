package cn.vove7.mydiv;

import beans.ChoiceHomework;
import beans.CompletionHomework;
import beans.Homework;
import beans.OperationHomework;
import cn.vove7.mydiv.DoHomeworkDiv.ChoiceOption;

/**
 * Created by Vove on 2017/5/25.
 * 重编辑页面Div
 */
public class ReeditHomeworkDiv {
   private Homework homework;
   private boolean isCanEdit;


   public ReeditHomeworkDiv(Homework homework, boolean isCanEdit) {
      this.homework = homework;
      this.isCanEdit = isCanEdit;
   }

   public String getChoiceDiv() {
      int index=1;
      StringBuilder builder=new StringBuilder();
      for(ChoiceHomework choiceHomework:homework.getChoiceHomeworkList()){
         builder.append(buildChoiceValue(index++,choiceHomework));
      }
      return builder.toString();
   }

   public String getCompletionDiv(){
      StringBuilder builder=new StringBuilder();
      int index=1;
      for(CompletionHomework completionHomework:homework.getCompletionHomeworkList()){
         builder.append(buildCompletionValue(index++,completionHomework));
      }
      return builder.toString();
   }
   public String getOperationDiv(){
      StringBuilder builder=new StringBuilder();
      int index=1;
      for(OperationHomework operationHomework:homework.getOperationHomeworkList()){
         builder.append(buildOperationValue(index++,operationHomework));
      }
      return builder.toString();
   }
   private String buildOperationValue(int index, OperationHomework operationHomework){
      String deleteButton=isCanEdit?"<a class=\"floatButton\" onclick=\"deleteChoice(' + num + ')\">删除</a>":"";
      String id=operationHomework.getId();
      String content=operationHomework.getQuestionContent();
      String score=operationHomework.getScore();

      return "<div class=\"operation_field\">" +
              "<input type=\"hidden\" name=\"operation_id_"+index+"\" value=\""+id+"\">"+
              "<div class=\"title\">问题" + index + ":</div><div align=\"right\" style=\"margin-bottom: 10px;\">" +
              "分数：<input class=\"score\" id=\"operation_score_"+index+"\" name=\"operation_score_"+index+"\" type=\"text\" value=\"" + score + "\">" +
              deleteButton+"</div>" +
              "<textarea class=\"ckeditor\" name=\"operation_content_" + index + "\" " +
              "id=\"operation_content_" + index + "\">" + content + "</textarea>" +
              "</div>";
   }


   private String buildCompletionValue(int index, CompletionHomework completionHomework){
      String deleteButton=isCanEdit?"<a class=\"floatButton\" onclick=\"deleteChoice(' + num + ')\">删除</a>":"";
      String completionFieldString;
      String id=completionHomework.getId();
      String refKey=completionHomework.getRefKey();
      String score=completionHomework.getScore();
      completionFieldString = "<div class=\"completion_field\">" +
              "<input type=\"hidden\" name=\"completion_id_"+index+"\" value=\""+id+"\">"+
              "<div class=\"title\">题目" + index + ":</div><div align=\"right\" style=\"margin-bottom: 20px;margin-top: 40px\">" +
              "分数：<input class=\"score\" id=\"comp_score_"+index+"\" name=\"comp_score_"+index+"\" type=\"text\" value=\"" + score + "\">"+deleteButton+"</div>" +
              "<textarea class=\"ckeditor\" name=\"question_content_" + index + "\" " +
              "id=\"question_content_" + index + "\">" + completionHomework.getCompletionContent() + "</textarea>" +
              "<p>参考答案(多空以#号分开):</p><input id=\"completion_refKey_"+index+"\" name=\"completion_refKey_"+index+"\" class=\"choice\" type=\"text\" value=\""+refKey+"\">"+
              "</div>";
      return completionFieldString;
   }

   private String buildChoiceValue(int index, ChoiceHomework choiceHomework) {
      String deleteButton=isCanEdit?"<a class=\"floatButton\" onclick=\"deleteChoice(' + num + ')\">删除</a>":"";
      String id=choiceHomework.getId(),
              score = choiceHomework.getScore(),
              s_A = choiceHomework.getChoice_A(),
              s_B = choiceHomework.getChoice_B(),
              s_C = choiceHomework.getChoice_C(),
              s_D = choiceHomework.getChoice_D(),
              questionContent = choiceHomework.getQuestion(),
              ref_keyStringValue =getRefKeyString(index,ChoiceOption.valueOf(choiceHomework.getRef_ky()));

      String ckediorString = "<textarea class=\"ckeditor\" name=\"choice-title_" + index + "\" " +
              "id=\"choice-title_" + index + "\">" + questionContent + "</textarea>";

      String choiceFieldString;
      choiceFieldString = "<div class=\"choice_field\" id=\"choice_field_" + index + "\">" +
              "<input type=\"hidden\" name=\"choice_id_"+index+"\" value=\""+id+"\">"+
              "<table width=\"100%\"><tr>" +
              "<div class=\"title narrow\">题目" + index + ":</div><div align=\"right\" style=\"margin-bottom: 10px;\">分数：<input class=\"score\" id=\"score_"
              + index + "\" name=\"score_" + index + "\" type=\"text\" value=\""
              + score + "\">"+deleteButton+"</div><td colspan=\"4\">" + ckediorString +
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
   private String getRefKeyString(int index, ChoiceOption choiceOption){
      String c_A = "",
              c_B = "",
              c_C = "",
              c_D = "";
      switch (choiceOption) {
         case A:
            c_A = "checked"; break;
         case B:
            c_B = "checked"; break;
         case C:
            c_C = "checked"; break;
         case D:
            c_D = "checked"; break;
         default: break;
      }
      String refKeyString;
      refKeyString = "<input type=\"radio\" " + c_A + " name=\"ref_key_" + index + "\"  value=\"A\">A&nbsp" +
              "<input type=\"radio\" " + c_B + " name=\"ref_key_" + index + "\" value=\"B\">B&nbsp" +
              "<input type=\"radio\" " + c_C + " name=\"ref_key_" + index + "\" value=\"C\">C&nbsp" +
              "<input type=\"radio\" " + c_D + " name=\"ref_key_" + index + "\" value=\"D\">D&nbsp";
      return refKeyString;
   }
}
