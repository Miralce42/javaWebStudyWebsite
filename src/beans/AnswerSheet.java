package beans;

/**
 * Created by Vove on 2017/5/27.
 * 选择题答题卡
 */
public class AnswerSheet {
    private String questionId;
    private String answer;
    private int score;

    public AnswerSheet(String questionId, String answer) {
        this.questionId = questionId;
        this.answer = answer;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
