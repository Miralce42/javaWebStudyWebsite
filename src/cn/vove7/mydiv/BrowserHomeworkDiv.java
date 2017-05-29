package cn.vove7.mydiv;

import WebDB.StudentDAO;
import beans.*;

/**
 * Created by Vove on 2017/5/29.
 * 生成查看作业
 */
public class BrowserHomeworkDiv {
    private Homework homework;
    private StudentDAO studentDAO;

    public BrowserHomeworkDiv(Users student, Homework homework) {
        this.homework = homework;
        studentDAO = new StudentDAO(student);
    }

    public String getChoiceDetailDiv() {
        StringBuilder builder = new StringBuilder();
        int index = 1;
        for (ChoiceHomework choiceHomework : homework.getChoiceHomeworkList()) {
            builder.append(buildChoiceDetail(index++, choiceHomework));
        }
        return builder.toString();
    }

    public String getCompletionDetailDiv() {
        StringBuilder builder = new StringBuilder();
        int index = 1;
        for (CompletionHomework completionHomework : homework.getCompletionHomeworkList()) {
            builder.append(buildCompletionDetail(index++, completionHomework));
        }
        return builder.toString();
    }

    public String getOperationDetailDiv() {
        StringBuilder builder = new StringBuilder();
        int index = 1;
        for (OperationHomework operationHomework : homework.getOperationHomeworkList()) {
            builder.append(buildOperationDetail(index++, operationHomework));
        }
        return builder.toString();
    }

    private String buildChoiceDetail(int index, ChoiceHomework choiceHomework) {
        String commitKey = studentDAO.getChoiceAnswer(choiceHomework.getId());
        String ref_key=choiceHomework.getRef_ky();
        String answerScore=studentDAO.getChoiceAnswerScore(choiceHomework.getId());//得分
        if(answerScore==null){
            answerScore="未批阅";
        }
        if(commitKey.equals("")){
            commitKey="未选择";
        }
        boolean isCorrect=commitKey.equals(ref_key);
        String imgSrc="img/"+(isCorrect?"right.png":"wrong.png");

        return "<div class=\"choiceBump\">\n" +
                "   <div class=\"choice-question\">\n" +
                "      <div style=\"float: left\">" + index + "、</div>\n" +
                "      " + choiceHomework.getQuestion() + "\n" +
                "   </div>\n" +
                "   <div class=\"option\">\n" +
                "      A."+choiceHomework.getChoice_A() +
                "   </div>\n" +
                "   <div class=\"option\">\n" +
                "      B."+choiceHomework.getChoice_B() +
                "   </div>\n" +
                "   <div class=\"option\">\n" +
                "      C."+choiceHomework.getChoice_C() +
                "   </div>\n" +
                "   <div class=\"option\">\n" +
                "      D."+choiceHomework.getChoice_D() +
                "   </div>\n" +
                "<div style=\"float:left\"><strong>你的选择：" + commitKey + "&nbsp&nbsp&nbsp&nbsp&nbsp正确答案："
                +ref_key+"&nbsp&nbsp&nbsp&nbsp&nbsp(得分："+answerScore+")</strong></div>" +
                "<div class=\"is-correct\"><img align=\"right\" width=20px src=\""+imgSrc+"\"></div>" +
                "</div>";
    }

    private String buildCompletionDetail(int index, CompletionHomework completionHomework) {
        String commitKey = studentDAO.getCompletionAnswer(completionHomework.getId());
        String ref_key=completionHomework.getRefKey();
        String answerScore=studentDAO.getCompletionAnswerScore(completionHomework.getId());//得分
        if(answerScore==null){
            answerScore="未批阅";
        }
        boolean isCorrect=commitKey.equals(ref_key);
        String imgSrc="img/"+(isCorrect?"right.png":"wrong.png");
        return
                " <div class=\"completionBump\"><input type=\"hidden\" name=\"completionId_1\" value=\"4\">\n" +
                "    <div class=\"completion-question\">\n" +
                "       <div style=\"float: left\">"+index+"、</div>\n" +
                    completionHomework.getCompletionContent() +
                "    </div>\n" +
                "    <div>\n" +
                "       <div style=\"float:left\"><strong>你的回答:"+commitKey+"&nbsp&nbsp&nbsp&nbsp&nbsp  正确答案："
                        +ref_key +"&nbsp&nbsp&nbsp&nbsp&nbsp(得分："+answerScore+")</strong></div>" +
                "    </div>\n" +
                "    <div class=\"is-correct\"><img align=\"right\" width=20px src=\"" + imgSrc + "\"></div>" +
                " </div>";
    }

    private String buildOperationDetail(int index, OperationHomework operationHomework) {
        String commitKey = studentDAO.getOperationAnswer(operationHomework.getId());
        String answerScore=studentDAO.getOperationAnswerScore(operationHomework.getId());//得分
        if(answerScore==null){
            answerScore="未批阅";
        }
        return
                " <div class=\"operation_field\"><input type=\"hidden\" name=\"operation_id_1\" value=\"5\">\n" +
                "    <div class=\"operationQuestion\">\n" +
                "       <div style=\"float: left\">"+index+"、</div>\n" +
                            operationHomework.getQuestionContent()+
                "       <strong>你的回答：&nbsp&nbsp&nbsp&nbsp&nbsp(得分："+answerScore+")</strong>\n" +
                "       <div class=\"operation-answer\">\n" + commitKey+
                "       </div>\n" +
                "    </div>\n" +
                " </div>";
    }
}
