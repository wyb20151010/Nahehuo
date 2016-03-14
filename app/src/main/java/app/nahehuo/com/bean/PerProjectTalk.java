package app.nahehuo.com.bean;

import java.util.List;

/**
 * Created by wyb on 2016/3/4.
 */
public class PerProjectTalk {

    private String title;
    private String subTitle;
    private List<TagStatus> tags;
    private String name;
    private String position;
    private String email;
    private String phone;


    public PerProjectTalk(String title, String subTitle, List<TagStatus> tags, String name, String position, String email, String phone) {
        this.title = title;
        this.subTitle = subTitle;
        this.tags = tags;
        this.name = name;
        this.position = position;
        this.email = email;
        this.phone = phone;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }


    public String getSubTitle() {
        return subTitle;
    }


    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }


    public List<TagStatus> getTags() {
        return tags;
    }


    public void setTags(List<TagStatus> tags) {
        this.tags = tags;
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


    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }


    public String getPhone() {
        return phone;
    }


    public void setPhone(String phone) {
        this.phone = phone;
    }
}
