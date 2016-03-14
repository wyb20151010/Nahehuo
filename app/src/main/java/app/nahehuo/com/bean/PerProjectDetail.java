package app.nahehuo.com.bean;

/**
 * Created by wyb on 2016/3/4.
 */
public class PerProjectDetail {

    private String name;
    private String position;
    private String phone;
    private String email;


    public PerProjectDetail(String name, String position, String phone, String email) {
        this.name = name;
        this.position = position;
        this.phone = phone;
        this.email = email;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getPosition() {
        return position;
    }


    public void setPosition(String position) {
        this.position = position;
    }


    public String getPhone() {
        return phone;
    }


    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }
}
