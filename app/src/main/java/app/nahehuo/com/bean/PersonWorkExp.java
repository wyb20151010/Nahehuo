package app.nahehuo.com.bean;

/**
 * Created by Administrator on 2016/3/3.
 */
public class PersonWorkExp {

    private String time;
    private String position;
    private String company;


    public PersonWorkExp(String time, String position, String company) {
        this.time = time;
        this.position = position;
        this.company = company;
    }


    public String getTime() {
        return time;
    }


    public void setTime(String time) {
        this.time = time;
    }


    public String getPosition() {
        return position;
    }


    public void setPosition(String position) {
        this.position = position;
    }


    public String getCompany() {
        return company;
    }


    public void setCompany(String company) {
        this.company = company;
    }
}
