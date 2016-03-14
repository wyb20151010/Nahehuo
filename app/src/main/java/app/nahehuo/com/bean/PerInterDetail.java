package app.nahehuo.com.bean;

/**
 * Created by Administrator on 2016/3/2.
 */
public class PerInterDetail {

    private String status;
    private String status_name;
    private String msg_time;
    private String time;
    private String location;
    private String hrphone;


    public PerInterDetail(String status, String status_name, String msg_time, String time, String location, String hrphone) {
        this.status = status;
        this.status_name = status_name;
        this.msg_time = msg_time;
        this.time = time;
        this.location = location;
        this.hrphone = hrphone;
    }


    public String getStatus_name() {
        return status_name;
    }


    public void setStatus_name(String status_name) {
        this.status_name = status_name;
    }


    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
    }


    public String getMsg_time() {
        return msg_time;
    }


    public void setMsg_time(String msg_time) {
        this.msg_time = msg_time;
    }


    public String getTime() {
        return time;
    }


    public void setTime(String time) {
        this.time = time;
    }


    public String getLocation() {
        return location;
    }


    public void setLocation(String location) {
        this.location = location;
    }


    public String getHrphone() {
        return hrphone;
    }


    public void setHrphone(String hrphone) {
        this.hrphone = hrphone;
    }
}
