package app.nahehuo.com.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/3/9.
 */
public class NetEventTheme {

    /**
     * message : 活动主题字典获取成功
     * code : 200
     * data : [{"value":"1","name":"创业"},{"value":"2","name":"商务"},{"value":"3","name":"公益"},{"value":"4","name":"社交"},{"value":"5","name":"亲子"},{"value":"6","name":"午餐"},{"value":"7","name":"电影"},{"value":"8","name":"娱乐"},{"value":"9","name":"生活"},{"value":"10","name":"音乐"},{"value":"11","name":"科技"},{"value":"12","name":"运动"},{"value":"13","name":"课程"},{"value":"14","name":"校园"},{"value":"15","name":"文化"},{"value":"16","name":"其他"}]
     * other : {"version":1}
     */

    private String message;
    private int code;
    /**
     * version : 1
     */

    private OtherEntity other;
    /**
     * value : 1
     * name : 创业
     */

    private List<DataEntity> data;


    public void setMessage(String message) { this.message = message;}


    public void setCode(int code) { this.code = code;}


    public void setOther(OtherEntity other) { this.other = other;}


    public void setData(List<DataEntity> data) { this.data = data;}


    public String getMessage() { return message;}


    public int getCode() { return code;}


    public OtherEntity getOther() { return other;}


    public List<DataEntity> getData() { return data;}


    public static class OtherEntity {
        private int version;


        public void setVersion(int version) { this.version = version;}


        public int getVersion() { return version;}
    }

    public static class DataEntity {
        private String value;
        private String name;


        public void setValue(String value) { this.value = value;}


        public void setName(String name) { this.name = name;}


        public String getValue() { return value;}


        public String getName() { return name;}
    }
}
