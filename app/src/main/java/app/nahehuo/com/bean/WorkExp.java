package app.nahehuo.com.bean;

import java.io.Serializable;

/**
 * Created by WYB on 2016/2/29.
 */
public class WorkExp implements Serializable{

    private String time;
    private String position;
    private String company;
    private String type;


    public WorkExp(String time, String position, String company, String type) {
        this.time = time;
        this.position = position;
        this.company = company;
        this.type = type;
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


    public String getType() {
        return type;
    }


    public void setType(String type) {
        this.type = type;
    }


    @Override public String toString() {
        return "WorkExp{" +
                "time='" + time + '\'' +
                ", position='" + position + '\'' +
                ", company='" + company + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
