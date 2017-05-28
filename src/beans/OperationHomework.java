package beans;

/**
 * Created by Vove on 2017/5/27.
 *
 */
public class OperationHomework {
    private String id;
    private String questionContent;
    private String score;

    public OperationHomework(String questionContent, String score) {
        this.questionContent = questionContent;
        this.score = score;
    }

    public OperationHomework(String id, String questionContent, String score) {
        this.id = id;
        this.questionContent = questionContent;
        this.score = score;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
