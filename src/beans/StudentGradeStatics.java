package beans;

/**
 * Created by Vove on 2017/6/6.
 */
public class StudentGradeStatics {
    public final static String querySql="select A.user_id,name,major,class,avg(ifnull(score,0)) from (\n" +
            "(SELECT user_id,name,major,class,id FROM users, homework \n" +
            "where user_type='STUDENT' and is_delete=0 \n" +
            "order by user_id,homework.id)as A left join homework_status on A.id=homework_status.hw_id and A.user_id=homework_status.user_id  )\n" +
            "group by A.user_id,name,major,class";
    private String studentId;
    private String name;
    private String major;
    private String classname;
    private String avgScore;


    public StudentGradeStatics(String studentId, String name, String major, String classname, String avgScore) {
        this.studentId = studentId;
        this.name = name;
        this.major = major;
        this.classname = classname;
        this.avgScore = avgScore;
    }

    public static String getQuerySql() {
        return querySql;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public String getMajorClassname() {
        return major+classname;
    }


    public String getAvgScore() {
        return avgScore;
    }
}
