package app.nahehuo.com.bean;

/**
 * Created by WYB on 2016/1/14.
 */
public class JobCommentDict {

    private String from_say;
    private String to_say;
    private String comment;


    public JobCommentDict( String from_say,String comment) {
        this.comment = comment;
        this.from_say = from_say;
    }


    public JobCommentDict(String from_say, String to_say, String comment) {
        this.from_say = from_say;
        this.to_say = to_say;
        this.comment = comment;
    }


    public String getFrom_say() {
        return from_say;
    }


    public void setFrom_say(String from_say) {
        this.from_say = from_say;
    }


    public String getTo_say() {
        return to_say;
    }


    public void setTo_say(String to_say) {
        this.to_say = to_say;
    }


    public String getComment() {
        return comment;
    }


    public void setComment(String comment) {
        this.comment = comment;
    }
}
