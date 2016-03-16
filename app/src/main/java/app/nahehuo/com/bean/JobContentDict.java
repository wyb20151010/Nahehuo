package app.nahehuo.com.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/1/6.
 */
public class JobContentDict {

    private String avatar;
    private String position;
    private List<String> tags;
    private int cid;


    public int getCid() {
        return cid;
    }


    public void setCid(int cid) {
        this.cid = cid;
    }


    public String getAvatar() {
        return avatar;
    }


    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }


    public String getPosition() {
        return position;
    }


    public void setPosition(String position) {
        this.position = position;
    }


    public List<String> getTags() {
        return tags;
    }


    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
