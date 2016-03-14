package app.nahehuo.com.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/3/9.
 */
public class NetWorkYear {

    /**
     * message : 工作年限字典获取成功
     * code : 200
     * data : [{"value":"7","name":"应届毕业生"},{"value":"1","name":"1年以下"},{"value":"2","name":"1-2年"},{"value":"3","name":"3-5年"},{"value":"4","name":"6-7年"},{"value":"5","name":"8-10年"},{"value":"6","name":"10年以上"}]
     * other : {"version":1}
     */

    private String message;
    private int code;
    /**
     * version : 1
     */

    private OtherEntity other;
    /**
     * value : 7
     * name : 应届毕业生
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
