package cn.vove7.mydiv;

import beans.ChoiceHomework;
import beans.Homework;

/**
 * Created by Vove on 2017/5/25.
 * 学生做作业布局
 */
public class DoHomeworkDiv {
   private Homework homework;

   public String getChoicesValue(){
      StringBuilder builder=new StringBuilder();
      for(ChoiceHomework choiceHomework:homework.getChoiceHomeworkList()){
         builder.append(buildChoiceValue(choiceHomework));
      }
      return builder.toString();
   }
   private String buildChoiceValue(ChoiceHomework choiceHomework){
      String str;
      str="11";
      return str;
   }
}


