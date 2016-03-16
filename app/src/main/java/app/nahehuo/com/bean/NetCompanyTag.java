package app.nahehuo.com.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/3/16.
 */
public class NetCompanyTag {

    /**
     * message : 企业标签获取成功
     * code : 200
     * data : [{"tid":1077,"name":"社交"}]
     */

    private String message;
    private int code;
    /**
     * tid : 1077
     * name : 社交
     */

    private List<DataEntity> data;


    public void setMessage(String message) { this.message = message;}


    public void setCode(int code) { this.code = code;}


    public void setData(List<DataEntity> data) { this.data = data;}


    public String getMessage() { return message;}


    public int getCode() { return code;}


    public List<DataEntity> getData() { return data;}


    public static class DataEntity {
        private int tid;
        private String name;


        public void setTid(int tid) { this.tid = tid;}


        public void setName(String name) { this.name = name;}


        public int getTid() { return tid;}


        public String getName() { return name;}
    }
}
