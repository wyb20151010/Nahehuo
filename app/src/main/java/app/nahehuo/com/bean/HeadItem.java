package app.nahehuo.com.bean;

/**
 * Created by Administrator on 2016/2/15.
 */
public class HeadItem {

    private boolean isOpen;
    private String title;


    public HeadItem(boolean isOpen, String title) {
        this.isOpen = isOpen;
        this.title = title;
    }


    public boolean isOpen() {
        return isOpen;
    }


    public void setIsOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }


    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }
}
