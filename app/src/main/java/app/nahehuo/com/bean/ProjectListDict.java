package app.nahehuo.com.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/1/18.
 */
public class ProjectListDict {

    private String projectPic;
    private String personPic;
    private String projectTitle;
    private String projectTitleDetail;
    private String projectComName;
    private String projectComPosition;
    private List<TagItemColor> projectTagList;
    private String tagPosition;
    private String tagFind;


    public ProjectListDict( String projectTitle, String projectTitleDetail,
                            String projectComName, String projectComPosition,
                            String tagPosition,String tagFind) {
        this.projectTitle = projectTitle;
        this.projectTitleDetail = projectTitleDetail;
        this.projectComName = projectComName;
        this.projectComPosition = projectComPosition;
        this.tagFind=tagFind;
        this.tagPosition=tagPosition;
    }


    public String getProjectTitleDetail() {
        return projectTitleDetail;
    }


    public void setProjectTitleDetail(String projectTitleDetail) {
        this.projectTitleDetail = projectTitleDetail;
    }

    public String getProjectPic() {
        return projectPic;
    }


    public void setProjectPic(String projectPic) {
        this.projectPic = projectPic;
    }


    public String getPersonPic() {
        return personPic;
    }


    public void setPersonPic(String personPic) {
        this.personPic = personPic;
    }


    public String getProjectTitle() {
        return projectTitle;
    }


    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }


    public String getProjectComName() {
        return projectComName;
    }


    public void setProjectComName(String projectComName) {
        this.projectComName = projectComName;
    }


    public String getProjectComPosition() {
        return projectComPosition;
    }


    public void setProjectComPosition(String projectComPosition) {
        this.projectComPosition = projectComPosition;
    }


    public String getTagPosition() {
        return tagPosition;
    }


    public void setTagPosition(String tagPosition) {
        this.tagPosition = tagPosition;
    }


    public String getTagFind() {
        return tagFind;
    }


    public void setTagFind(String tagFind) {
        this.tagFind = tagFind;
    }
}
