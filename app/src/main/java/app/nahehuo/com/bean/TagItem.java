package app.nahehuo.com.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/12/21.
 */
public class TagItem implements Serializable{
    private  String content;
    private int number;
    private boolean unselected;


    public TagItem() {
    }


    public TagItem(String content, int number, boolean unselected) {
        this.content = content;
        this.number = number;
        this.unselected=unselected;
    }


    public boolean isUnselected() {
        return unselected;
    }


    public void setUnselected(boolean unselected) {
        this.unselected = unselected;
    }


    public String getContent() {
        return content;
    }


    public void setContent(String content) {
        this.content = content;
    }


    public int getNumber() {
        return number;
    }


    public void setNumber(int number) {
        this.number = number;
    }


    @Override public String toString() {
        return "TagItem{" +
                "content='" + content + '\'' +
                ", number=" + number +
                ", unselected=" + unselected +
                '}';
    }
}
