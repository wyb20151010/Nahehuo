package app.nahehuo.com.bean;

/**
 * Created by Administrator on 2016/2/16.
 */
public class PersonPartner {

    private String name;
    private String position;
    private String pingyintou;


    public PersonPartner() {
    }


    public PersonPartner(String name, String position, String pingyintou) {
        this.name = name;
        this.position = position;
        this.pingyintou = pingyintou;
    }


    public String getPingyintou() {
        return pingyintou;
    }


    public void setPingyintou(String pingyintou) {
        this.pingyintou = pingyintou;
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
}
