package app.nahehuo.com.bean;

import java.util.List;

/**
 * Created by wyb on 2016/3/3.
 */
public class PerCollectJob {

    private String position;

    private String company;

    private List<TagStatus> tags;


    public PerCollectJob(String position, String company, List<TagStatus> tags) {
        this.position = position;
        this.company = company;
        this.tags = tags;
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


    public List<TagStatus> getTags() {
        return tags;
    }


    public void setTags(List<TagStatus> tags) {
        this.tags = tags;
    }
}
