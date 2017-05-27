package cn.vove7.mydiv;


import WebDB.StudentDAO;
import beans.*;

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
    public String getOpeartionValue(){
        StringBuilder builder = new StringBuilder();
        int index = 1;
        for (OperationHomework operationHomework:homework.getOperationHomeworkList()){
            builder.append(buildOperationValue(index++,operationHomework));
        }
        return builder.toString();
    }

    public String getCompletionsValue() {
        StringBuilder builder = new StringBuilder();
        int index = 1;
        for (CompletionHomework completionHomework : homework.getCompletionHomeworkList()) {
            builder.append(buildCompletionValue(index++, completionHomework));
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
        if (savedKey != null) {
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
        }
        str = " <div class=\"choiceBump\">" + index +
                "     、<div class=\"choice-question\">" +
                "<input type=\"hidden\" name=\"choiceId_" + index + "\" value=\"" + choiceHomework.getId() + "\">" +
                    choiceHomework.getQuestion() + "\n" +
                "     </div>\n" +
                
                "         <div class=\"option\">\n" +
                "             <input class=\"radio\" name=\"choice_" + index + "\"  " + check_A + " type=\"radio\" value=\"A\">" + choiceHomework.getChoice_A() + "\n" +
                "         </div>\n" +
                "         <div class=\"option\">\n" +
                "             <input class=\"radio\" name=\"choice_" + index + "\" " + check_B + " type=\"radio\" value=\"B\">" + choiceHomework.getChoice_B() + "\n" +
                "         </div>\n" +
                "         <div class=\"option\">\n" +
                "             <input class=\"radio\" name=\"choice_" + index + "\" " + check_C + " type=\"radio\" value=\"C\">" + choiceHomework.getChoice_C() + "\n" +
                "         </div>\n" +
                "         <div class=\"option\">\n" +
                "             <input class=\"radio\" name=\"choice_" + index + "\" " + check_D + " type=\"radio\" value=\"D\">" + choiceHomework.getChoice_D() + "\n" +
                "         </div>\n" +
               
                " </div>";
        return str;
    }

    private String buildCompletionValue(int index, CompletionHomework completionHomework) {
        String str;
        String savedAnswer = new StudentDAO(student).getCompletionAnswer(completionHomework.getId());
        if (savedAnswer == null) {
            savedAnswer = "";
        }
        str = "<div class=\"completionBump\">" +
                "<input type=\"hidden\" name=\"completionId_" + index + "\" value=\"" + completionHomework.getId() + "\">" +
                 + index + "、<div class=\"completion-question\">"
                + completionHomework.getCompletionContent() + "</div>" +
                " 回答(多空以#号分开)：<input class=\"completion_answer\" id=\"completion_answer_" + index + "\"\n" +
                "            name=\"completion_answer_" + index + "\" type=\"text\" value=\"" + savedAnswer + "\">\n" +
                "</div>";
        return str;
    }
    private String buildOperationValue(int index,OperationHomework operationHomework){
        String savedAnswer=new StudentDAO(student).getOperationAnswer(operationHomework.getId());
        String operationQuestion=operationHomework.getQuestionContent();
        if (savedAnswer==null){
            savedAnswer="";
        }
        return "<div class=\"operation_field\">" +
                "<input type=\"hidden\" name=\"operation_id_"+index+"\" value=\""+index+"\">"+
                "" + index + "、"+
                "<div class=\"operationQuestion\">"+operationQuestion+"</div>"+
                "<textarea class=\"ckeditor\" name=\"operation_content_" + index + "\" " +
                "id=\"operation_content_" + index + "\">" + savedAnswer + "</textarea>" +
                "</div>";
    }

    public enum ChoiceOption {
        A, B, C, D, NULL
    }
}


