package app.nahehuo.com.bean;

/**
 * Created by WYB on 2016/2/19.
 */
public class PartnerAll {

    private String name;
    private String position;
    private String attenType;

    public PartnerAll(String name, String position, String attenType) {
        this.name = name;
        this.position = position;
        this.attenType = attenType;
    }


    public String getAttenType() {
        return attenType;
    }


    public void setAttenType(String attenType) {
        this.attenType = attenType;
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
