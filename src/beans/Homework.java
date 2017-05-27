package beans;

import java.util.ArrayList;

/**
 * Created by Vove on 2017/5/25.
 * 作业类封装
 */
public class Homework {
    private String homeworkId;
    private String homeworkTitle;
    private String beginTime;
    private String endTime;
    private ArrayList<ChoiceHomework> choiceHomeworkList;//选择题
    private ArrayList<CompletionHomework> completionHomeworkList;//填空题
    private ArrayList<OperationHomework> operationHomeworkList;//操作题，使用填空类

    public Homework() {
    }

    public Homework(String homeworkTitle, String beginTime,
                    String endTime, ArrayList<ChoiceHomework> choiceHomeworkList,
                    ArrayList<CompletionHomework> completionHomeworkList,
                    ArrayList<OperationHomework> operationHomeworkList) {
        this.homeworkTitle = homeworkTitle;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.choiceHomeworkList = choiceHomeworkList;
        this.operationHomeworkList = operationHomeworkList;
        this.completionHomeworkList = completionHomeworkList;
    }

    public ArrayList<OperationHomework> getOperationHomeworkList() {
        return operationHomeworkList;
    }

    public void setOperationHomeworkList(ArrayList<OperationHomework> operationHomeworkList) {
        this.operationHomeworkList = operationHomeworkList;
    }

    public String getHomeworkId() {
        return homeworkId;
    }

    public Homework setHomeworkTitle(String homeworkTitle) {
        this.homeworkTitle = homeworkTitle;
        return this;
    }

    public Homework setBeginTime(String beginTime) {
        this.beginTime = beginTime;
        return this;
    }

    public Homework setEndTime(String endTime) {
        this.endTime = endTime;
        return this;
    }

    public void setChoiceHomeworkList(ArrayList<ChoiceHomework> choiceHomeworkList) {
        this.choiceHomeworkList = choiceHomeworkList;
    }

    public void setCompletionHomeworkList(ArrayList<CompletionHomework> completionHomeworkList) {
        this.completionHomeworkList = completionHomeworkList;
    }

    public void setHomeworkId(String homeworkId) {
        this.homeworkId = homeworkId;
    }

    public String getHomeworkTitle() {
        return homeworkTitle;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public ArrayList<ChoiceHomework> getChoiceHomeworkList() {
        return choiceHomeworkList;
    }

    public ArrayList<CompletionHomework> getCompletionHomeworkList() {
        return completionHomeworkList;
    }
}
