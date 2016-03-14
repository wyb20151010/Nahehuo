package app.nahehuo.com.bean;

/**
 * Created by Administrator on 2015/12/21.
 */
public class TagItem {
    private  String content;
    private int number;
    private boolean unselected;

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
}
