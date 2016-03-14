package app.nahehuo.com.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/3/9.
 */
public class NetLang {

    /**
     * message : 语言字典获取成功
     * code : 200
     * data : [{"id":1,"name":"英语"},{"id":2,"name":"日语"},{"id":3,"name":"法语"},{"id":4,"name":"中文普通话"}]
     * other : {"version":1}
     */

    private String message;
    private int code;
    /**
     * version : 1
     */

    private OtherEntity other;
    /**
     * id : 1
     * name : 英语
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
        private int id;
        private String name;


        public void setId(int id) { this.id = id;}


        public void setName(String name) { this.name = name;}


        public int getId() { return id;}


        public String getName() { return name;}
    }
}
