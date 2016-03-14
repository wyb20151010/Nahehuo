package app.nahehuo.com.bean;

/**
 * Created by WYB on 2016/1/19.
 */
public class ProjectPersonDict {

    private String person;
    private String position;
    private String avater;


    public ProjectPersonDict(String person, String position, String avater) {
        this.person = person;
        this.position = position;
        this.avater = avater;
    }


    public String getPerson() {
        return person;
    }


    public void setPerson(String person) {
        this.person = person;
    }


    public String getPosition() {
        return position;
    }


    public void setPosition(String position) {
        this.position = position;
    }


    public String getAvater() {
        return avater;
    }


    public void setAvater(String avater) {
        this.avater = avater;
    }
}
