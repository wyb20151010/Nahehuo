package app.nahehuo.com.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/3/9.
 */
public class NetBank {

    /**
     * message : 银行字典获取成功
     * code : 200
     * data : [{"value":"ICBC","name":"中国工商银行"},{"value":"ABC","name":"中国农业银行"},{"value":"BOC","name":"中国银行"},{"value":"CCB","name":"中国建设银行"},{"value":"PSBC","name":"中国邮政储蓄银行"},{"value":"COMM","name":"交通银行"},{"value":"SPDB","name":"浦发银行"},{"value":"CMB","name":"招商银行"},{"value":"CIB","name":"兴业银行"},{"value":"GDB","name":"广发银行"},{"value":"CMBC","name":"中国民生银行"},{"value":"CITIC","name":"中信银行"},{"value":"CEB","name":"中国光大银行"},{"value":"SPABANK","name":"平安银行"}]
     * other : {"version":1}
     */

    private String message;
    private int code;
    /**
     * version : 1
     */

    private OtherEntity other;
    /**
     * value : ICBC
     * name : 中国工商银行
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
