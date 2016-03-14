package app.nahehuo.com.bean;

/**
 * Created by Administrator on 2016/3/3.
 */
public class TagStatus {

    private String name;
    private String type;


    public TagStatus(String name, String type) {
        this.name = name;
        this.type = type;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getType() {
        return type;
    }


    public void setType(String type) {
        this.type = type;
    }
}
