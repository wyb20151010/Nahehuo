package app.nahehuo.com.bean;

import java.io.Serializable;

/**
 * Created by WYB on 2016/2/18.
 */
public class PartnerVP implements Serializable{

    private String name;
    private String position;
    private String company;


    public PartnerVP(String name, String position, String company) {
        this.name = name;
        this.position = position;
        this.company = company;
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


    public String getCompany() {
        return company;
    }


    public void setCompany(String company) {
        this.company = company;
    }


    @Override public String toString() {
        return "PartnerVP{" +
                "name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", company='" + company + '\'' +
                '}';
    }
}
