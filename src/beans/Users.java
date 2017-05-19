package beans;

/**
 * Created by 韩壮 on 2017/5/17.
 *
 */
public class Users {
    private String username;
    private String password;

    private UserType userType;

    private String userPasswordOne;

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


    public enum UserType{
        STUDENT,//学生
        TEACHER,//教师
        ADMINISTRATOR//后台管理员
    }
}
