package app.nahehuo.com.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/3/9.
 */
public class NetProjectPlan {

    /**
     * message : 项目计划字典获取成功
     * code : 200
     * data : [{"value":"1","name":"项目融资"},{"value":"2","name":"项目合伙"},{"value":"3","name":"专利/技术购买"},{"value":"4","name":"原创/作品购买"}]
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
     * name : 项目融资
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
