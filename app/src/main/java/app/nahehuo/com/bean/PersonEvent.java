package app.nahehuo.com.bean;

/**
 * Created by Administrator on 2016/3/4.
 */
public class PersonEvent {

    private String name;

    private String position;


    public PersonEvent(String name, String position) {
        this.name = name;
        this.position = position;
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
