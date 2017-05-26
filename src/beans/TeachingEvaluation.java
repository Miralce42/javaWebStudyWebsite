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

    public String getUsername() {
        return username;
    }
    public String getContent() {
        return content;
    }
    public String getStar1()  { return star1;}
    public String getStar2(){return  star2;}
    public String getStar3(){return star3;}
    public String getStar4(){return  star4;}

    public TeachingEvaluation(String username, String star1, String star2, String star3, String star4, String content) {
        this.username = username;
        this.star1 = star1;
        this.star2 = star2;
        this.star3 = star3;
        this.star4 = star4;
        this.content = content;
    }
}


