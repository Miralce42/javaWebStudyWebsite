package beans;

/**
 * Created by 韩壮 on 2017/5/17.
 *
 */
public class Users {
    private String username;
    private UserType user_type;
    private String name;
    private String password;
    private String sex;
    private String phone;
    private String major;
    private String classNum;

    private UserType userType;

    private String userPasswordOne;

    public UserType getEnumUserType(){
        return userType;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserPasswordOne() {
        return userPasswordOne;
    }

    public void setUserPasswordOne(String userPasswordOne) {
        this.userPasswordOne = userPasswordOne;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser_type() {
        return user_type.name();
    }

    public void setUser_type(String user_type) {
        this.user_type = UserType.valueOf(user_type.toUpperCase());
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }


    public enum UserType{
        STUDENT,//学生
        TEACHER,//教师
    }
}
