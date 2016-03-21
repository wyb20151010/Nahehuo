package app.nahehuo.com.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/3/21.
 */
public class NetEventList {

    /**
     * message : 活动列表完成
     * code : 200
     * data : [{"uid":12055,"title":"活动支付测试1分钱03","started":1445707800,"ended":1459873800,"eventnum":0,"eventprice":1,"price":"0.01","prov":"上海","city":"松江区","address":"九亭镇九新公路341弄28号华西大厦7楼","follownum":0,"viewnum":99,"comnum":0,"joinnum":11,"logo":null,"avatar":null,"username":"疯少爷","company":"上海傲锐数码科技","job":"phper"},{"uid":10000,"title":"支付测试","started":1456293600,"ended":1460188800,"eventnum":0,"eventprice":1,"price":"0.01","prov":"上海","city":"松江区","address":"黄埔大道西平云路163号广电物业平云广场B塔","follownum":0,"viewnum":42,"comnum":0,"joinnum":0,"logo":null,"avatar":null,"username":"lock888"}]
     */

    private String message;
    private int code;
    /**
     * uid : 12055
     * title : 活动支付测试1分钱03
     * started : 1445707800
     * ended : 1459873800
     * eventnum : 0
     * eventprice : 1
     * price : 0.01
     * prov : 上海
     * city : 松江区
     * address : 九亭镇九新公路341弄28号华西大厦7楼
     * follownum : 0
     * viewnum : 99
     * comnum : 0
     * joinnum : 11
     * logo : null
     * avatar : null
     * username : 疯少爷
     * company : 上海傲锐数码科技
     * job : phper
     */

    private List<DataEntity> data;


    public String getMessage() { return message;}


    public void setMessage(String message) { this.message = message;}


    public int getCode() { return code;}


    public void setCode(int code) { this.code = code;}


    public List<DataEntity> getData() { return data;}


    public void setData(List<DataEntity> data) { this.data = data;}


    public static class DataEntity {
        private int uid;
        private String title;
        private int started;
        private int ended;
        private int eventnum;
        private int eventprice;
        private String price;
        private String prov;
        private String city;
        private String address;
        private int follownum;
        private int viewnum;
        private int comnum;
        private int joinnum;
        private String logo;
        private String avatar;
        private String username;
        private String company;
        private String job;


        public int getUid() { return uid;}


        public void setUid(int uid) { this.uid = uid;}


        public String getTitle() { return title;}


        public void setTitle(String title) { this.title = title;}


        public int getStarted() { return started;}


        public void setStarted(int started) { this.started = started;}


        public int getEnded() { return ended;}


        public void setEnded(int ended) { this.ended = ended;}


        public int getEventnum() { return eventnum;}


        public void setEventnum(int eventnum) { this.eventnum = eventnum;}


        public int getEventprice() { return eventprice;}


        public void setEventprice(int eventprice) {
            this.eventprice = eventprice;
        }


        public String getPrice() { return price;}


        public void setPrice(String price) { this.price = price;}


        public String getProv() { return prov;}


        public void setProv(String prov) { this.prov = prov;}


        public String getCity() { return city;}


        public void setCity(String city) { this.city = city;}


        public String getAddress() { return address;}


        public void setAddress(String address) { this.address = address;}


        public int getFollownum() { return follownum;}


        public void setFollownum(int follownum) { this.follownum = follownum;}


        public int getViewnum() { return viewnum;}


        public void setViewnum(int viewnum) { this.viewnum = viewnum;}


        public int getComnum() { return comnum;}


        public void setComnum(int comnum) { this.comnum = comnum;}


        public int getJoinnum() { return joinnum;}


        public void setJoinnum(int joinnum) { this.joinnum = joinnum;}


        public String getLogo() { return logo;}


        public void setLogo(String logo) { this.logo = logo;}


        public String getAvatar() { return avatar;}


        public void setAvatar(String avatar) { this.avatar = avatar;}


        public String getUsername() { return username;}


        public void setUsername(String username) { this.username = username;}


        public String getCompany() { return company;}


        public void setCompany(String company) { this.company = company;}


        public String getJob() { return job;}


        public void setJob(String job) { this.job = job;}
    }
}
