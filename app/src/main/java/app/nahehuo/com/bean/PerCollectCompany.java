package app.nahehuo.com.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/3/3.
 */
public class PerCollectCompany {

    private String company;

    private List<String> tags;


    public PerCollectCompany(String company, List<String> tags) {
        this.company = company;
        this.tags = tags;
    }


    public String getCompany() {
        return company;
    }


    public void setCompany(String company) {
        this.company = company;
    }


    public List<String> getTags() {
        return tags;
    }


    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
