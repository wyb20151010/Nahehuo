package app.nahehuo.com.bean;

/**
 * Created by Administrator on 2016/2/19.
 */
public class PartnerNewFriend {

    private String name;
    private String position;
    private String onesay;


    public PartnerNewFriend(String name, String position, String onesay) {
        this.name = name;
        this.position = position;
        this.onesay = onesay;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getPosition() {
        return position;
    }


    public void setPosition(String position) {
        this.position = position;
    }


    public String getOnesay() {
        return onesay;
    }


    public void setOnesay(String onesay) {
        this.onesay = onesay;
    }
}
