package app.nahehuo.com.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/3/18.
 */
public class NetCompComment {

    /**
     * message : 企业评价获取成功
     * code : 200
     * data : [{"comid":103,"uid":10008,"anony":2,"content":"团队氛围不错！","matchindex":"4.0","envindex":"5.0","auraindex":"5.0","rele":"","created":1427278769,"username":"刘志贤","avatar":"http://test2015.zcspin.com/thumb/2/c/ca/741_big.jpg","partner_lid":"0","partner_level":"0"},{"comid":102,"uid":27150,"anony":1,"content":"氛围太棒！领导很牛！","matchindex":"4.0","envindex":"4.0","auraindex":"4.0","rele":"","created":1427278518,"username":"田理","avatar":"http://test2015.zcspin.com/thumb/b/4/f0/1466_big.jpg","partner_lid":"0","partner_level":"0"},{"comid":101,"uid":28063,"anony":2,"content":"工作环境好，同事nice，工作内容也是自己喜欢的。","matchindex":"4.0","envindex":"5.0","auraindex":"5.0","rele":"","created":1427275957,"username":"Treasa","avatar":"http://test2015.zcspin.com/thumb/f/2/76/1573_big.jpg","partner_lid":"0","partner_level":"0"},{"comid":100,"uid":15484,"anony":2,"content":"跟着Boss大方向走，绝对不会有错路的。","matchindex":"4.0","envindex":"4.0","auraindex":"4.0","rele":"","created":1427275805,"username":"Cindy Zhang","avatar":"http://test2015.zcspin.com/thumb/6/c/b3/950_big.jpg","partner_lid":"0","partner_level":"0"},{"comid":99,"uid":38512,"anony":1,"content":"老板人好，整个公司的氛围很好，有完善的培训体系。在这里工作觉得职业发展会很好，即使将来离开这个公司也会在市场上很抢手。公司还组织各种文化娱乐活动，定期的观影，讲座，徒步，瑜伽，拓展训练等","matchindex":"4.0","envindex":"5.0","auraindex":"5.0","rele":"","created":1427270651,"username":"毛丽红","avatar":"http://test2015.zcspin.com/thumb/3/e/25/3104_big.jpg","partner_lid":"0","partner_level":"0"},{"comid":38,"uid":10000,"anony":1,"content":"天高任我飞，只要你有想法，都可以畅谈。随性而不随意的工作环境，应该是你想要的。","matchindex":"4.0","envindex":"4.0","auraindex":"5.0","rele":"","created":1413789951,"username":"lock888","avatar":null,"partner_lid":"0","partner_level":"0"},{"comid":9,"uid":10002,"anony":1,"content":"搬到松江九亭那边了，离九亭地铁站20分钟的路，不算远也不算近，不过高峰期，路不好走，有点堵的。\n现在，分散在外面的团队终于能聚在一个空间里办公了，终于有了家的感觉。沟通起来更方便了。\n公司包了2层楼，比以前大多了。有了健身房和休息室，更爽了。公司里每个人都蛮有团队精神的，注重技能和分享，一起提高着团队的水平和效率。\n我去年6月加入今翌，那个时候还在漕河泾，就感觉公司氛围还不错，老板人也很好，对项目支持度挺大的，所以大家工作起来都比较用心。虽然碰到了不少困难，但我觉得都是事儿。\n公司也属于互联网公司，要多一点互联网的乐趣，比如：多一些拓展，多一些公司娱乐活动，现在真的很少有这些。通过一些公司举行的活动，能提高公司的朝气和活动，至少我觉得肯定会的。\n公司对个人的发展还是挺注重的，只要你有能力，升职加薪不成问题，关键你能多大的潜力，为公司做多大的贡献。\n搬到新地方了，一切都在改善！加油，今翌！","matchindex":"4.0","envindex":"5.0","auraindex":"4.0","rele":"我是公司在职员工","created":1405400816,"username":"倪盛盛","avatar":"http://test2015.zcspin.com/thumb/1/4/5e/3327_big.jpg","partner_lid":"0","partner_level":"0"},{"comid":7,"uid":12934,"anony":2,"content":"公司准备搬家升级啦~刚刚的！","matchindex":"4.0","envindex":"5.0","auraindex":"4.0","rele":"我是公司在职员工","created":1402041238,"username":"今翌信息科技（上海）有限公司","avatar":"http://test2015.zcspin.com/thumb/1/b/fb/949_big.jpg","partner_lid":"0","partner_level":"0"},{"comid":4,"uid":10002,"anony":1,"content":"离家近是一点；\n项目前景还是不错的。公司整体的氛围还是挺和谐的，虽然有压力，但部门管理上倒是轻松自由！","matchindex":"4.0","envindex":"4.0","auraindex":"4.0","rele":"合作伙伴","created":1385536395,"username":"倪盛盛","avatar":"http://test2015.zcspin.com/thumb/1/4/5e/3327_big.jpg","partner_lid":"0","partner_level":"0"}]
     * other : 10
     */

    private String message;
    private int code;
    private int other;
    /**
     * comid : 103
     * uid : 10008
     * anony : 2
     * content : 团队氛围不错！
     * matchindex : 4.0
     * envindex : 5.0
     * auraindex : 5.0
     * rele :
     * created : 1427278769
     * username : 刘志贤
     * avatar : http://test2015.zcspin.com/thumb/2/c/ca/741_big.jpg
     * partner_lid : 0
     * partner_level : 0
     */

    private List<DataEntity> data;


    public String getMessage() { return message;}


    public void setMessage(String message) { this.message = message;}


    public int getCode() { return code;}


    public void setCode(int code) { this.code = code;}


    public int getOther() { return other;}


    public void setOther(int other) { this.other = other;}


    public List<DataEntity> getData() { return data;}


    public void setData(List<DataEntity> data) { this.data = data;}


    public static class DataEntity {
        private int comid;
        private int uid;
        private int anony;
        private String content;
        private String matchindex;
        private String envindex;
        private String auraindex;
        private String rele;
        private long created;
        private String username;
        private String avatar;
        private String partner_lid;
        private String partner_level;


        public int getComid() { return comid;}


        public void setComid(int comid) { this.comid = comid;}


        public int getUid() { return uid;}


        public void setUid(int uid) { this.uid = uid;}


        public int getAnony() { return anony;}


        public void setAnony(int anony) { this.anony = anony;}


        public String getContent() { return content;}


        public void setContent(String content) { this.content = content;}


        public String getMatchindex() { return matchindex;}


        public void setMatchindex(String matchindex) {
            this.matchindex = matchindex;
        }


        public String getEnvindex() { return envindex;}


        public void setEnvindex(String envindex) { this.envindex = envindex;}


        public String getAuraindex() { return auraindex;}


        public void setAuraindex(String auraindex) {
            this.auraindex = auraindex;
        }


        public String getRele() { return rele;}


        public void setRele(String rele) { this.rele = rele;}


        public long getCreated() { return created;}


        public void setCreated(long created) { this.created = created;}


        public String getUsername() { return username;}


        public void setUsername(String username) { this.username = username;}


        public String getAvatar() { return avatar;}


        public void setAvatar(String avatar) { this.avatar = avatar;}


        public String getPartner_lid() { return partner_lid;}


        public void setPartner_lid(String partner_lid) {
            this.partner_lid = partner_lid;
        }


        public String getPartner_level() { return partner_level;}


        public void setPartner_level(String partner_level) {
            this.partner_level = partner_level;
        }
    }
}
