package app.nahehuo.com.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/2/29.
 */
public class PerWorkExp implements Serializable{

    private String time;
    private String position;
    private String type;
    private String company;
    private Boolean education;


    public PerWorkExp(String time, String position, String type, String company, Boolean education) {
        this.time = time;
        this.position = position;
        this.type = type;
        this.company = company;
        this.education = education;
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


    public String getType() {
        return type;
    }


    public void setType(String type) {
        this.type = type;
    }


    public String getCompany() {
        return company;
    }


    public void setCompany(String company) {
        this.company = company;
    }


    public Boolean getEducation() {
        return education;
    }


    public void setEducation(Boolean education) {
        this.education = education;
    }


    @Override public String toString() {
        return "PerWorkExp{" +
                "time='" + time + '\'' +
                ", position='" + position + '\'' +
                ", type='" + type + '\'' +
                ", company='" + company + '\'' +
                ", education=" + education +
                '}';
    }
}
