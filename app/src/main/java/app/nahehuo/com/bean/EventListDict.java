package app.nahehuo.com.bean;

/**
 * Created by Administrator on 2016/1/25.
 */
public class EventListDict {

    String tv_event_title;
    String tv_per_name;
    String tv_per_pos;
    String tv_event_time;
    String tv_event_number;
    String tv_event_location;
    String tv_event_distance;
    String tv_event_comment;
    String tv_event_watch_num;
    String tv_event_com_num;
    String tv_event_per_num;
    String iv_per_avater;
    int type;


    public EventListDict(String tv_event_title, String tv_per_name, String tv_per_pos, String tv_event_time, String tv_event_number, String tv_event_location, String tv_event_distance, String tv_event_comment, String tv_event_watch_num, String tv_event_com_num, String tv_event_per_num, String iv_per_avater, int type) {
        this.tv_event_title = tv_event_title;
        this.tv_per_name = tv_per_name;
        this.tv_per_pos = tv_per_pos;
        this.tv_event_time = tv_event_time;
        this.tv_event_number = tv_event_number;
        this.tv_event_location = tv_event_location;
        this.tv_event_distance = tv_event_distance;
        this.tv_event_comment = tv_event_comment;
        this.tv_event_watch_num = tv_event_watch_num;
        this.tv_event_com_num = tv_event_com_num;
        this.tv_event_per_num = tv_event_per_num;
        this.iv_per_avater = iv_per_avater;
        this.type = type;
    }


    public int getType() {
        return type;
    }


    public void setType(int type) {
        this.type = type;
    }


    public String getTv_event_title() {
        return tv_event_title;
    }


    public void setTv_event_title(String tv_event_title) {
        this.tv_event_title = tv_event_title;
    }


    public String getTv_per_name() {
        return tv_per_name;
    }


    public void setTv_per_name(String tv_per_name) {
        this.tv_per_name = tv_per_name;
    }


    public String getTv_per_pos() {
        return tv_per_pos;
    }


    public void setTv_per_pos(String tv_per_pos) {
        this.tv_per_pos = tv_per_pos;
    }


    public String getTv_event_time() {
        return tv_event_time;
    }


    public void setTv_event_time(String tv_event_time) {
        this.tv_event_time = tv_event_time;
    }


    public String getTv_event_number() {
        return tv_event_number;
    }


    public void setTv_event_number(String tv_event_number) {
        this.tv_event_number = tv_event_number;
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


    public String getTv_event_comment() {
        return tv_event_comment;
    }


    public void setTv_event_comment(String tv_event_comment) {
        this.tv_event_comment = tv_event_comment;
    }


    public String getTv_event_watch_num() {
        return tv_event_watch_num;
    }


    public void setTv_event_watch_num(String tv_event_watch_num) {
        this.tv_event_watch_num = tv_event_watch_num;
    }


    public String getTv_event_com_num() {
        return tv_event_com_num;
    }


    public void setTv_event_com_num(String tv_event_com_num) {
        this.tv_event_com_num = tv_event_com_num;
    }


    public String getTv_event_per_num() {
        return tv_event_per_num;
    }


    public void setTv_event_per_num(String tv_event_per_num) {
        this.tv_event_per_num = tv_event_per_num;
    }


    public String getIv_per_avater() {
        return iv_per_avater;
    }


    public void setIv_per_avater(String iv_per_avater) {
        this.iv_per_avater = iv_per_avater;
    }
}
