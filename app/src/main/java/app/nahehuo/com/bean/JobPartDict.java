package app.nahehuo.com.bean;

/**
 * Created by WYB on 2016/1/13.
 */
public class JobPartDict {

    private String name;
    private String location;
    private String title;
    private String content;
    private String time;
    private String see;


    public JobPartDict(String name, String location, String title, String content, String time, String see) {
        this.name = name;
        this.location = location;
        this.title = title;
        this.content = content;
        this.time = time;
        this.see = see;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getLocation() {
        return location;
    }


    public void setLocation(String location) {
        this.location = location;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String getContent() {
        return content;
    }


    public void setContent(String content) {
        this.content = content;
    }


    public String getTime() {
        return time;
    }


    public void setTime(String time) {
        this.time = time;
    }


    public String getSee() {
        return see;
    }


    public void setSee(String see) {
        this.see = see;
    }
}
