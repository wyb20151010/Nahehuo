package app.nahehuo.com.bean;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Id;
import com.lidroid.xutils.db.annotation.Table;

/**
 * Created by Administrator on 2016/3/8.
 */

@Table(name = "citydict") public class DictCity {

    @Id private int id;

    @Column private int cityid;
    @Column private String name;
    @Column private int level;
    @Column private int upid;


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public int getCityid() {
        return cityid;
    }


    public void setCityid(int cityid) {
        this.cityid = cityid;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public int getLevel() {
        return level;
    }


    public void setLevel(int level) {
        this.level = level;
    }


    public int getUpid() {
        return upid;
    }


    public void setUpid(int upid) {
        this.upid = upid;
    }
}
