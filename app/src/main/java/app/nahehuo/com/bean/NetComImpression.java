package app.nahehuo.com.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/3/18.
 */
public class NetComImpression {

    /**
     * message : 企业印象获取成功
     * code : 200
     * data : [{"eid":2,"name":"姑娘多","type":1,"effectnum":0},{"eid":3,"name":"生日礼物","type":1,"effectnum":0},{"eid":4,"name":"地理位置远","type":1,"effectnum":0}]
     */

    private String message;
    private int code;
    /**
     * eid : 2
     * name : 姑娘多
     * type : 1
     * effectnum : 0
     */

    private List<DataEntity> data;


    public String getMessage() { return message;}


    public void setMessage(String message) { this.message = message;}


    public int getCode() { return code;}


    public void setCode(int code) { this.code = code;}


    public List<DataEntity> getData() { return data;}


    public void setData(List<DataEntity> data) { this.data = data;}


    public static class DataEntity {
        private int eid;
        private String name;
        private int type;
        private int effectnum;


        public int getEid() { return eid;}


        public void setEid(int eid) { this.eid = eid;}


        public String getName() { return name;}


        public void setName(String name) { this.name = name;}


        public int getType() { return type;}


        public void setType(int type) { this.type = type;}


        public int getEffectnum() { return effectnum;}


        public void setEffectnum(int effectnum) { this.effectnum = effectnum;}
    }
}
