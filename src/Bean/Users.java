package Bean;

/**
 * Created by 韩壮 on 2017/5/17.
 */
public class Users {
    private String username;
    private String password;
    private String userPasswordOne;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        System.out.println("setPassword运行了");
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        System.out.println("setUsername运行了");
        this.username = username;
    }

    public String getUserPasswordOne() {
        return userPasswordOne;
    }

    public void setUserPasswordOne(String userPasswordOne) {
        this.userPasswordOne = userPasswordOne;
    }
}
