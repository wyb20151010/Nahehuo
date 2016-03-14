package app.nahehuo.com.bean;

/**
 * Created by Administrator on 2016/2/24.
 */
public class WorkInfo {

    private String position;
    private String company;
    private String time;


    public WorkInfo(String position, String company, String time) {
        this.position = position;
        this.company = company;
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


    public String getTime() {
        return time;
    }


    public void setTime(String time) {
        this.time = time;
    }
}
