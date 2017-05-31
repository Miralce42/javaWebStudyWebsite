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

    public String getChoiceDetailDiv(boolean b) {//是否显示答案(教师端，学生端)
        StringBuilder builder = new StringBuilder();
        int index = 1;
        for (ChoiceHomework choiceHomework : homework.getChoiceHomeworkList()) {
            builder.append(buildChoiceDetail(index++, choiceHomework,b));
        }
        return builder.toString();
    }

    public String getCompletionDetailDiv(boolean b) {
        StringBuilder builder = new StringBuilder();
        int index = 1;
        for (CompletionHomework completionHomework : homework.getCompletionHomeworkList()) {
            builder.append(buildCompletionDetail(index++, completionHomework,b));
        }
        return builder.toString();
    }

    public String getOperationDetailDiv(boolean b) {
        StringBuilder builder = new StringBuilder();
        int index = 1;
        for (OperationHomework operationHomework : homework.getOperationHomeworkList()) {
            builder.append(buildOperationDetail(index++, operationHomework,b));
        }
        return builder.toString();
    }

    private String buildChoiceDetail(int index, ChoiceHomework choiceHomework,boolean b) {
        String commitKey = studentDAO.getChoiceAnswer(choiceHomework.getId());
        String ref_key=choiceHomework.getRef_ky();
        String answerScore=studentDAO.getChoiceAnswerScore(choiceHomework.getId());//得分
        if(answerScore==null){
            answerScore="未批阅";
        }
        if(commitKey.equals("")){
            commitKey="未选择";
        }
        String str=b? "学生回答":"你的回答";
        boolean isCorrect=commitKey.equals(ref_key);
        String imgSrc="img/"+(isCorrect?"right.png":"wrong.png");

        return "<div class=\"choiceBump\">\n" +
                "   <div class=\"choice-question\">\n" +
                "      <div style=\"float: left\">" + index + "、</div>\n" +
                "      " + choiceHomework.getQuestion() + "\n" +
                "   </div>\n" +
                "   <div class=\"option\">\n" +
                "      A.&nbsp&nbsp&nbsp"+choiceHomework.getChoice_A() +
                "   </div>\n" +
                "   <div class=\"option\">\n" +
                "      B.&nbsp&nbsp&nbsp"+choiceHomework.getChoice_B() +
                "   </div>\n" +
                "   <div class=\"option\">\n" +
                "      C.&nbsp&nbsp&nbsp"+choiceHomework.getChoice_C() +
                "   </div>\n" +
                "   <div class=\"option\">\n" +
                "      D.&nbsp&nbsp&nbsp"+choiceHomework.getChoice_D() +
                "   </div>\n" +
                "<div style=\"float:left\"><strong>"+str+"：" + commitKey + "&nbsp&nbsp&nbsp&nbsp&nbsp"+
                (b?"正确答案：" +ref_key:"")+
//                "正确答案：" +ref_key+
                "&nbsp&nbsp&nbsp&nbsp&nbsp(得分："+answerScore+"/"+choiceHomework.getScore()+")</strong></div>" +
                "<div class=\"is-correct\"><img align=\"right\" width=20px src=\""+imgSrc+"\"></div>" +
                "</div>";
    }

    private String buildCompletionDetail(int index, CompletionHomework completionHomework,boolean b) {
        String commitKey = studentDAO.getCompletionAnswer(completionHomework.getId());
        String ref_key=completionHomework.getRefKey();
        String answerScore=studentDAO.getCompletionAnswerScore(completionHomework.getId());//得分
        if(answerScore==null){
            answerScore="未批阅";
        }
        String str=b? "学生回答":"你的回答";
        boolean isCorrect=commitKey.equals(ref_key);
        String imgSrc="img/"+(isCorrect?"right.png":"wrong.png");
        return
                " <div class=\"completionBump\"><input type=\"hidden\" name=\"completionId_1\" value=\"4\">\n" +
                "    <div class=\"completion-question\">\n" +
                "       <div style=\"float: left\">"+index+"、</div>\n" +
                    completionHomework.getCompletionContent() +
                "    </div>\n" +
                "    <div>\n" +
                "       <div style=\"float:left\"><strong>"+str+":"+commitKey+"&nbsp&nbsp&nbsp&nbsp&nbsp"+
                        (b?"正确答案：" +ref_key:"")+
//                        "正确答案：" +ref_key +
                        "&nbsp&nbsp&nbsp&nbsp&nbsp(得分："+answerScore+"/"+completionHomework.getScore()+")</strong></div>" +
                "    </div>\n" +
                "    <div class=\"is-correct\"><img align=\"right\" width=20px src=\"" + imgSrc + "\"></div>" +
                " </div>";
    }

    private String buildOperationDetail(int index, OperationHomework operationHomework,boolean b) {
        String commitKey = studentDAO.getOperationAnswer(operationHomework.getId());
        String answerScore=studentDAO.getOperationAnswerScore(operationHomework.getId());//得分
        if(answerScore==null){
            answerScore="未批阅";
        }
        String str=b? "学生回答":"你的回答";
        String scoreInput="<div align=\"right\" style=\"margin-bottom: 10px;\">" +
                "分数：<input class=\"score\" id=\"operation_"+index+"\" name=\"operation_score_"+operationHomework.getId() +
                "\" type=\"text\"></div>";
        return
                " <div class=\"operation_field\">" +
                        "<input type=\"hidden\" name=\"operation_id_"+index+"\" value=\""+operationHomework.getId()+"\">\n" +
                        (b? scoreInput:"")+
                "    <div class=\"operationQuestion\">\n" +
                "       <div style=\"float: left\">"+index+"、</div>\n" +
                            operationHomework.getQuestionContent()+
                "       <strong>"+str+"：&nbsp&nbsp&nbsp&nbsp&nbsp(得分："+answerScore+"/"+operationHomework.getScore()+")</strong>\n" +
                "       <div class=\"operation-answer\">\n" + commitKey+
                "       </div>\n" +
                "    </div>\n" +
                " </div>";
    }
}
