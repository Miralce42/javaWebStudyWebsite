package beans;

/**
 * Created by Vove on 2017/5/27.
 * 选择题答题卡
 */
public class AnswerSheet {
    private String questionId;
    private String answer;
    private double score;

    public AnswerSheet(String questionId, String answer) {
        this.questionId = questionId;
        this.answer = answer;
    }

    public AnswerSheet(String questionId, double score) {
        this.questionId = questionId;
        this.score = score;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }
}
