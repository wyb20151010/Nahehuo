package app.nahehuo.com.bean;

import com.lidroid.xutils.db.annotation.Column;
import com.lidroid.xutils.db.annotation.Id;
import com.lidroid.xutils.db.annotation.Table;

/**
 * Created by Administrator on 2016/3/9.
 */
@Table(name = "salarydict") public class DictSalary {

    @Id private int id;
    @Column private String value;
    @Column private String name;


    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getValue() {
        return value;
    }


    public void setValue(String value) {
        this.value = value;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }
}
