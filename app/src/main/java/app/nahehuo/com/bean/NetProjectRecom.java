package app.nahehuo.com.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/3/21.
 */
public class NetProjectRecom {

    /**
     * message : 获取推荐项目成功
     * code : 200
     * data : [{"pid":1,"uid":71214,"pic":null,"tagid":"858,859,860,861,","tag":"大学生,情侣招聘,APP,生活","plan":"1,2,3","descp":"近年涌现出来的上百家食材电商仔细看下来大多区别不大，90%以上都是重模式，剩下的 10%是平台轻模式。重模式从本质上来说是电商不如说是食材供应商的互联网化应用。一次，我在给一个投资人大讲重模式绝不会成为主流，也无法爆发并形成垄断时，他问我那为什么有这么多人投重模式？这一句话就让我沉默了。还有一次，当我兴致勃勃的证明我们粘性有多强时，对方的眼中充满迷惑，没有光芒放出来，他不理解粘性怎么值得花这么多时间陈述。投资者一定是站在行业最前沿，对行业应该有深刻认知和前瞻性，但事实他们有另一套判定方法，而那方法可能对关键点的认知完全不同。这让我第一次认识到投资人对行业的了解可能是很少的，而且可能并不那么成熟。\r\n近年涌现出来的上百家食材电商仔细看下来大多区别不大，90%以上都是重模式，剩下的 10%是平台轻模式。重模式从本质上来说是电商不如说是食材供应商的互联网化应用。一次，我在给一个投资人大讲重模式绝不会成为主流，也无法爆发并形成垄断时，他问我那为什么有这么多人投重模式？这一句话就让我沉默了。还有一次，当我兴致勃勃的证明我们粘性有多强时，对方的眼中充满迷惑，没有光芒放出来，他不理解粘性怎么值得花这么多时间陈述。投资者一定是站在行业最前沿，对行业应该有深刻认知和前瞻性，但事实他们有另一套判定方法，而那方法可能对关键点的认知完全不同。这让我第一次认识到投资人对行业的了解可能是很少的，而且可能并不那么成熟。","ended":1470585600,"tradefir":["IT行业","IT行业","金融行业","专业服务"],"tradesec":["互联网","电子/半导体/集成电路","银行","会计/审计"],"area":"上海","avatar":null,"username":"咯玉兔","company":0,"job":"0"}]
     */

    private String message;
    private int code;
    /**
     * pid : 1
     * uid : 71214
     * pic : null
     * tagid : 858,859,860,861,
     * tag : 大学生,情侣招聘,APP,生活
     * plan : 1,2,3
     * descp : 近年涌现出来的上百家食材电商仔细看下来大多区别不大，90%以上都是重模式，剩下的 10%是平台轻模式。重模式从本质上来说是电商不如说是食材供应商的互联网化应用。一次，我在给一个投资人大讲重模式绝不会成为主流，也无法爆发并形成垄断时，他问我那为什么有这么多人投重模式？这一句话就让我沉默了。还有一次，当我兴致勃勃的证明我们粘性有多强时，对方的眼中充满迷惑，没有光芒放出来，他不理解粘性怎么值得花这么多时间陈述。投资者一定是站在行业最前沿，对行业应该有深刻认知和前瞻性，但事实他们有另一套判定方法，而那方法可能对关键点的认知完全不同。这让我第一次认识到投资人对行业的了解可能是很少的，而且可能并不那么成熟。
     近年涌现出来的上百家食材电商仔细看下来大多区别不大，90%以上都是重模式，剩下的 10%是平台轻模式。重模式从本质上来说是电商不如说是食材供应商的互联网化应用。一次，我在给一个投资人大讲重模式绝不会成为主流，也无法爆发并形成垄断时，他问我那为什么有这么多人投重模式？这一句话就让我沉默了。还有一次，当我兴致勃勃的证明我们粘性有多强时，对方的眼中充满迷惑，没有光芒放出来，他不理解粘性怎么值得花这么多时间陈述。投资者一定是站在行业最前沿，对行业应该有深刻认知和前瞻性，但事实他们有另一套判定方法，而那方法可能对关键点的认知完全不同。这让我第一次认识到投资人对行业的了解可能是很少的，而且可能并不那么成熟。
     * ended : 1470585600
     * tradefir : ["IT行业","IT行业","金融行业","专业服务"]
     * tradesec : ["互联网","电子/半导体/集成电路","银行","会计/审计"]
     * area : 上海
     * avatar : null
     * username : 咯玉兔
     * company : 0
     * job : 0
     */

    private List<DataEntity> data;


    public String getMessage() { return message;}


    public void setMessage(String message) { this.message = message;}


    public int getCode() { return code;}


    public void setCode(int code) { this.code = code;}


    public List<DataEntity> getData() { return data;}


    public void setData(List<DataEntity> data) { this.data = data;}


    public static class DataEntity {
        private int pid;
        private int uid;
        private String pic;
        private String tagid;
        private String tag;
        private String plan;
        private String descp;
        private int ended;
        private String area;
        private String avatar;
        private String username;
        private String company;
        private String job;
        private List<String> tradefir;
        private List<String> tradesec;


        public int getPid() { return pid;}


        public void setPid(int pid) { this.pid = pid;}


        public int getUid() { return uid;}


        public void setUid(int uid) { this.uid = uid;}


        public String getPic() { return pic;}


        public void setPic(String pic) { this.pic = pic;}


        public String getTagid() { return tagid;}


        public void setTagid(String tagid) { this.tagid = tagid;}


        public String getTag() { return tag;}


        public void setTag(String tag) { this.tag = tag;}


        public String getPlan() { return plan;}


        public void setPlan(String plan) { this.plan = plan;}


        public String getDescp() { return descp;}


        public void setDescp(String descp) { this.descp = descp;}


        public int getEnded() { return ended;}


        public void setEnded(int ended) { this.ended = ended;}


        public String getArea() { return area;}


        public void setArea(String area) { this.area = area;}


        public String getAvatar() { return avatar;}


        public void setAvatar(String avatar) { this.avatar = avatar;}


        public String getUsername() { return username;}


        public void setUsername(String username) { this.username = username;}


        public String getCompany() { return company;}


        public void setCompany(String company) { this.company = company;}


        public String getJob() { return job;}


        public void setJob(String job) { this.job = job;}


        public List<String> getTradefir() { return tradefir;}


        public void setTradefir(List<String> tradefir) {
            this.tradefir = tradefir;
        }


        public List<String> getTradesec() { return tradesec;}


        public void setTradesec(List<String> tradesec) {
            this.tradesec = tradesec;
        }
    }
}
