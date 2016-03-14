package app.nahehuo.com.bean;

/**
 * Created by Administrator on 2016/1/30.
 */
public class EventGalleryDict {
    public String uid;// 41101",
    public String pid;// 10",
    public String created;// 1437619619",
    public String thumb;// http://test2015.zcspin.com/media/images/person_o.jpg",
    public String eid;// 11"


    public EventGalleryDict(String uid, String pid, String created, String thumb, String eid) {
        this.uid = uid;
        this.pid = pid;
        this.created = created;
        this.thumb = thumb;
        this.eid = eid;
    }


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

}
