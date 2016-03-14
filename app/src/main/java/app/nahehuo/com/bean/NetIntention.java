package app.nahehuo.com.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/3/10.
 */
public class NetIntention {

    /**
     * message : 求职状态获取成功
     * code : 200
     * data : [{"value":"1","name":"我目前处于离职状态，可立即上岗"},{"value":"2","name":"我目前在职，正考虑换个新环境（如有合适的工作机会，到岗时间一个月左右）"},{"value":"3","name":"目前暂无跳槽打算"},{"value":"4","name":"我对现有的工作还算满意，如有更好的工作机会，我也可以考虑（到岗时间另议）"},{"value":"5","name":"应届毕业生"}]
     */

    private String message;
    private int code;
    /**
     * value : 1
     * name : 我目前处于离职状态，可立即上岗
     */

    private List<DataEntity> data;


    public void setMessage(String message) { this.message = message;}


    public void setCode(int code) { this.code = code;}


    public void setData(List<DataEntity> data) { this.data = data;}


    public String getMessage() { return message;}


    public int getCode() { return code;}


    public List<DataEntity> getData() { return data;}


    public static class DataEntity {
        private String value;
        private String name;


        public void setValue(String value) { this.value = value;}


        public void setName(String name) { this.name = name;}


        public String getValue() { return value;}


        public String getName() { return name;}
    }
}
