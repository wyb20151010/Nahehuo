package app.nahehuo.com.bean;

/**
 * Created by Administrator on 2016/3/2.
 */
public class MyInter {

    private String position;
    private String company;
    private String salary;
    private String time;
    private String status;
    private String status_name;


    public MyInter(String position, String company, String salary, String time, String status, String status_name) {
        this.position = position;
        this.company = company;
        this.salary = salary;
        this.time = time;
        this.status = status;
        this.status_name = status_name;
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


    public String getSalary() {
        return salary;
    }


    public void setSalary(String salary) {
        this.salary = salary;
    }


    public String getTime() {
        return time;
    }


    public void setTime(String time) {
        this.time = time;
    }


    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
    }


    public String getStatus_name() {
        return status_name;
    }


    public void setStatus_name(String status_name) {
        this.status_name = status_name;
    }
}
