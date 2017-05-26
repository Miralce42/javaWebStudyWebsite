package beans;
import beans.StudentHomework.HomeworkStatus;

import java.util.ArrayList;

/**
 * Created by Vove on 2017/5/27.
 * 作业答题卡
 */
public class HomeworkAnswer {
    private String userId;
    private String homeworkId;
    private HomeworkStatus homeworkStatus;//
    private ArrayList<Answer> choiceAnswers;//选择题
    private ArrayList<Answer> completionAnswers;//填空题

    public HomeworkAnswer(String userId, String homeworkId, HomeworkStatus homeworkStatus) {
        this.userId = userId;
        this.homeworkId = homeworkId;
        this.homeworkStatus = homeworkStatus;
    }

    public HomeworkAnswer(String userId, String homeworkId, HomeworkStatus homeworkStatus, ArrayList<Answer> choiceAnswers, ArrayList<Answer> completionAnswers) {
        this.userId = userId;
        this.homeworkId = homeworkId;
        this.homeworkStatus = homeworkStatus;
        this.choiceAnswers = choiceAnswers;
        this.completionAnswers = completionAnswers;
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

    public ArrayList<Answer> getChoiceAnswers() {
        return choiceAnswers;
    }

    public void setChoiceAnswers(ArrayList<Answer> choiceAnswers) {
        this.choiceAnswers = choiceAnswers;
    }

    public ArrayList<Answer> getCompletionAnswers() {
        return completionAnswers;
    }

    public void setCompletionAnswers(ArrayList<Answer> completionAnswers) {
        this.completionAnswers = completionAnswers;
    }
}
