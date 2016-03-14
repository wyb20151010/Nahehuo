package app.nahehuo.com.bean;

/**
 * Created by Administrator on 2016/3/3.
 */
public class PerEvent {

    private String title;
    private String type;
    private String per_name;
    private String per_pos;
    private String time;
    private String number;
    private String tag;
    private String location;
    private String distance;


    public PerEvent(String title, String type, String per_name, String per_pos, String time, String number, String tag, String location, String distance) {
        this.title = title;
        this.type = type;
        this.per_name = per_name;
        this.per_pos = per_pos;
        this.time = time;
        this.number = number;
        this.tag = tag;
        this.location = location;
        this.distance = distance;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String getType() {
        return type;
    }


    public void setType(String type) {
        this.type = type;
    }


    public String getPer_name() {
        return per_name;
    }


    public void setPer_name(String per_name) {
        this.per_name = per_name;
    }


    public String getPer_pos() {
        return per_pos;
    }


    public void setPer_pos(String per_pos) {
        this.per_pos = per_pos;
    }


    public String getTime() {
        return time;
    }


    public void setTime(String time) {
        this.time = time;
    }


    public String getNumber() {
        return number;
    }


    public void setNumber(String number) {
        this.number = number;
    }


    public String getTag() {
        return tag;
    }


    public void setTag(String tag) {
        this.tag = tag;
    }


    public String getLocation() {
        return location;
    }


    public void setLocation(String location) {
        this.location = location;
    }


    public String getDistance() {
        return distance;
    }


    public void setDistance(String distance) {
        this.distance = distance;
    }
}
