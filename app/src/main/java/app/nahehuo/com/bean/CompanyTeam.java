package app.nahehuo.com.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/3/16.
 */
public class CompanyTeam implements Serializable{

    private String name;
    private String position;
    private String desp;
    private String pic;


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


    public String getDesp() {
        return desp;
    }


    public void setDesp(String desp) {
        this.desp = desp;
    }


    public String getPic() {
        return pic;
    }


    public void setPic(String pic) {
        this.pic = pic;
    }


    @Override public String toString() {
        return "CompanyTeam{" +
                "name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", desp='" + desp + '\'' +
                ", pic='" + pic + '\'' +
                '}';
    }
}
