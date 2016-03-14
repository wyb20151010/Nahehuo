package app.nahehuo.com.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/3/9.
 */
public class NetHotCity {

    /**
     * message : 热门城市字典获取成功
     * code : 200
     * data : [{"value":"567","name":"北京"},{"value":"569","name":"上海"},{"value":"175","name":"杭州"},{"value":"166","name":"苏州"},{"value":"289","name":"广州"},{"value":"291","name":"深圳"},{"value":"385","name":"成都"},{"value":"258","name":"武汉"},{"value":"570","name":"重庆"},{"value":"568","name":"天津"}]
     * other : {"version":1}
     */

    private String message;
    private int code;
    /**
     * version : 1
     */

    private OtherEntity other;
    /**
     * value : 567
     * name : 北京
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
