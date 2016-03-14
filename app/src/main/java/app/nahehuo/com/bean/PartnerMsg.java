package app.nahehuo.com.bean;

/**
 * Created by wyb on 2016/2/23.
 */
public class PartnerMsg {

    private String msg;
    private boolean isLeft;


    public PartnerMsg(String msg, boolean isLeft) {
        this.msg = msg;
        this.isLeft = isLeft;
    }


    public String getMsg() {
        return msg;
    }


    public void setMsg(String msg) {
        this.msg = msg;
    }


    public boolean isLeft() {
        return isLeft;
    }


    public void setIsLeft(boolean isLeft) {
        this.isLeft = isLeft;
    }
}
