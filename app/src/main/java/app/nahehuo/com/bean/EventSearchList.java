package app.nahehuo.com.bean;

/**
 * Created by Administrator on 2016/1/27.
 */
public class EventSearchList {
    String iv_event_pic;
    String tv_event_title;
    String tv_event_time;
    String tv_event_location;
    String tv_event_distance;
    String tv_event_number;
    int type;


    public EventSearchList(String iv_event_pic, String tv_event_title, String tv_event_time, String tv_event_location, String tv_event_distance, String tv_event_number, int type) {
        this.iv_event_pic = iv_event_pic;
        this.tv_event_title = tv_event_title;
        this.tv_event_time = tv_event_time;
        this.tv_event_location = tv_event_location;
        this.tv_event_distance = tv_event_distance;
        this.tv_event_number = tv_event_number;
        this.type = type;
    }


    public String getIv_event_pic() {
        return iv_event_pic;
    }


    public void setIv_event_pic(String iv_event_pic) {
        this.iv_event_pic = iv_event_pic;
    }


    public String getTv_event_title() {
        return tv_event_title;
    }


    public void setTv_event_title(String tv_event_title) {
        this.tv_event_title = tv_event_title;
    }


    public String getTv_event_time() {
        return tv_event_time;
    }


    public void setTv_event_time(String tv_event_time) {
        this.tv_event_time = tv_event_time;
    }


    public String getTv_event_location() {
        return tv_event_location;
    }


    public void setTv_event_location(String tv_event_location) {
        this.tv_event_location = tv_event_location;
    }


    public String getTv_event_distance() {
        return tv_event_distance;
    }


    public void setTv_event_distance(String tv_event_distance) {
        this.tv_event_distance = tv_event_distance;
    }


    public String getTv_event_number() {
        return tv_event_number;
    }


    public void setTv_event_number(String tv_event_number) {
        this.tv_event_number = tv_event_number;
    }


    public int getType() {
        return type;
    }


    public void setType(int type) {
        this.type = type;
    }
}


