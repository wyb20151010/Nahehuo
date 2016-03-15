package app.nahehuo.com.bean;

/**
 * Created by WYB on 2016/1/8.
 */
public class CompanyCommentDict {

    private String username;
    private String score;
    private int state;
    private String comment_title;
    private String comment_content;
    private String comment_time;
    private String avater;
    private int anony;


    public int getAnony() {
        return anony;
    }


    public void setAnony(int anony) {
        this.anony = anony;
    }


    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public String getScore() {
        return score;
    }


    public void setScore(String score) {
        this.score = score;
    }


    public int getState() {
        return state;
    }


    public void setState(int state) {
        this.state = state;
    }


    public String getComment_title() {
        return comment_title;
    }


    public void setComment_title(String comment_title) {
        this.comment_title = comment_title;
    }


    public String getComment_content() {
        return comment_content;
    }


    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }


    public String getComment_time() {
        return comment_time;
    }


    public void setComment_time(String comment_time) {
        this.comment_time = comment_time;
    }


    public String getAvater() {
        return avater;
    }


    public void setAvater(String avater) {
        this.avater = avater;
    }
}
