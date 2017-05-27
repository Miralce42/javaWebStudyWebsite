package beans;

/**
 * Created by mac on 2017/5/26.
 */
public class TeachingEvaluation {
    private String username;
    private String star1;
    private String star2;
    private String star3;
    private String star4;
    private String content;
    private String major;
    private String evaluate_date;

    public String getUsername() {
        return username;
    }
    public String getContent() {
        return content;
    }
    public String getStar1()  { return this.star1;}
    public String getStar2(){return this.star2;}
    public String getStar3(){return star3;}
    public String getStar4(){return  star4;}
    public String getMajor(){return major;}
    public String getEvaluate_date(){return evaluate_date;}

    public void setMajor(String major) {
        this.major = major;
    }

    public TeachingEvaluation(String username, String content, String major,String evaluate_date) {
        this.username = username;
        this.content = content;
        this.major = major;
        this.evaluate_date=evaluate_date;
    }

    public TeachingEvaluation(String username, String star1, String star2, String star3, String star4, String content) {
        this.username = username;
        this.star1 = star1;
        this.star2 = star2;
        this.star3 = star3;
        this.star4 = star4;
        this.content = content;
    }
}


