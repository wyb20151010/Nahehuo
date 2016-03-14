package app.nahehuo.com.bean;

/**
 * Created by Administrator on 2016/3/4.
 */
public class PerProjectPub {

    private String title;

    private String time;

    private String type;


    public PerProjectPub(String title, String time, String type) {
        this.title = title;
        this.time = time;
        this.type = type;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String getTime() {
        return time;
    }


    public void setTime(String time) {
        this.time = time;
    }


    public String getType() {
        return type;
    }


    public void setType(String type) {
        this.type = type;
    }
}
