package beans;
import beans.StudentHomework.HomeworkStatus;

import java.util.ArrayList;

/**
 * Created by Vove on 2017/5/27.
 * 作业答题卡
 */
public class HomeworkAnswerSheet {
    private String userId;
    private String homeworkId;
    private HomeworkStatus homeworkStatus;//
    private ArrayList<AnswerSheet> choiceAnswerSheets;//选择题
    private ArrayList<AnswerSheet> completionAnswerSheets;//填空题
    private ArrayList<AnswerSheet> operationAnswerSheet;//操作题


    public HomeworkAnswerSheet(String userId, String homeworkId, HomeworkStatus homeworkStatus) {
        this.userId = userId;
        this.homeworkId = homeworkId;
        this.homeworkStatus = homeworkStatus;
    }

    public HomeworkAnswerSheet(String userId, String homeworkId, HomeworkStatus homeworkStatus,
                               ArrayList<AnswerSheet> choiceAnswerSheets,
                               ArrayList<AnswerSheet> completionAnswerSheets,
                               ArrayList<AnswerSheet> operationAnswerSheet) {
        this.userId = userId;
        this.homeworkId = homeworkId;
        this.homeworkStatus = homeworkStatus;
        this.choiceAnswerSheets = choiceAnswerSheets;
        this.completionAnswerSheets = completionAnswerSheets;
        this.operationAnswerSheet = operationAnswerSheet;
    }

    public ArrayList<AnswerSheet> getOperationAnswer() {
        return operationAnswerSheet;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(String homeworkId) {
        this.homeworkId = homeworkId;
    }

    public HomeworkStatus getHomeworkStatus() {
        return homeworkStatus;
    }

    public void setHomeworkStatus(HomeworkStatus homeworkStatus) {
        this.homeworkStatus = homeworkStatus;
    }

    public ArrayList<AnswerSheet> getChoiceAnswers() {
        return choiceAnswerSheets;
    }

    public void setChoiceAnswers(ArrayList<AnswerSheet> choiceAnswerSheets) {
        this.choiceAnswerSheets = choiceAnswerSheets;
    }

    public ArrayList<AnswerSheet> getCompletionAnswers() {
        return completionAnswerSheets;
    }

    public void setCompletionAnswers(ArrayList<AnswerSheet> completionAnswerSheets) {
        this.completionAnswerSheets = completionAnswerSheets;
    }
}
