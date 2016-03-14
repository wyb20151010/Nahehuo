package app.nahehuo.com.bean;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Id;
import com.lidroid.xutils.db.annotation.Table;

/**
 * Created by Administrator on 2016/3/8.
 */

@Table(name = "versiondict")
public class DictVersion {

    @Id private int id;

    @Column private int version;
    @Column private String key;


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public int getVersion() {
        return version;
    }


    public void setVersion(int version) {
        this.version = version;
    }


    public String getKey() {
        return key;
    }


    public void setKey(String key) {
        this.key = key;
    }
}
