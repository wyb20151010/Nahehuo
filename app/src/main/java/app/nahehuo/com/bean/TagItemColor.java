package app.nahehuo.com.bean;

/**
 * Created by WYB on 2015/12/21.
 */
public class TagItemColor {
    private  String content;
    private int color;


    public TagItemColor(int color, String content) {
        this.color = color;
        this.content = content;
    }


    public String getContent() {
        return content;
    }


    public void setContent(String content) {
        this.content = content;
    }


    public int getColor() {
        return color;
    }


    public void setColor(int color) {
        this.color = color;
    }
}
