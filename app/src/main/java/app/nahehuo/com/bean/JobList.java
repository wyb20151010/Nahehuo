package app.nahehuo.com.bean;

import java.util.List;

/**
 * Created by Administrator on 2015/12/24.
 */
public class JobList {

    /**
     * status : 200
     * message : 职位列表
     * data : [{"jid":"24321","position":"PHP工程师","type":"1","prov":"上海","city":"徐汇区","wagemin":"9","wagemax":"14","uid":"64200","company":"上海腾闻网络科技有限公司","companyen":"上海腾闻网络科技有限公司","published":"1432356112","changed":"1432356112","amount":0,"astatus":"0","cid":"46106","avatar":"http://www.nahehuo.com/media/images/company_m.jpg","utype":"1","wage":"9k-14k","publishtxt":"2015-05-23"},{"jid":"10550","position":"Android工程师","type":"1","prov":"上海","city":"虹口区","wagemin":"9","wagemax":"20","uid":"38745","company":"天联广告传媒上海有限公司","companyen":"天联广告传媒上海有限公司","published":"1425953612","changed":"1425953612","amount":0,"astatus":"0","cid":"21447","avatar":"http://www.nahehuo.com/thumb/7/6/cc/2789_middle.jpg","utype":"1","wage":"9k-20k","publishtxt":"2015-03-10"},{"jid":"10549","position":"JAVA开发工程师","type":"1","prov":"上海","city":"虹口区","wagemin":"9","wagemax":"20","uid":"38745","company":"天联广告传媒上海有限公司","companyen":"天联广告传媒上海有限公司","published":"1425953614","changed":"1425953614","amount":0,"astatus":"0","cid":"21447","avatar":"http://www.nahehuo.com/thumb/7/6/cc/2789_middle.jpg","utype":"1","wage":"9k-20k","publishtxt":"2015-03-10"},{"jid":"10548","position":"系统运维工程师","type":"1","prov":"上海","city":"虹口区","wagemin":"9","wagemax":"20","uid":"38745","company":"天联广告传媒上海有限公司","companyen":"天联广告传媒上海有限公司","published":"1425953617","changed":"1425953617","amount":0,"astatus":"0","cid":"21447","avatar":"http://www.nahehuo.com/thumb/7/6/cc/2789_middle.jpg","utype":"1","wage":"9k-20k","publishtxt":"2015-03-10"},{"jid":"10547","position":"PHP程度员","type":"1","prov":"上海","city":"虹口区","wagemin":"9","wagemax":"20","uid":"38745","company":"天联广告传媒上海有限公司","companyen":"天联广告传媒上海有限公司","published":"1425953619","changed":"1425953619","amount":0,"astatus":"0","cid":"21447","avatar":"http://www.nahehuo.com/thumb/7/6/cc/2789_middle.jpg","utype":"1","wage":"9k-20k","publishtxt":"2015-03-10"},{"jid":"6599","position":"技术合伙人 CTO","type":"1","prov":"上海","city":"闸北区","wagemin":"25","wagemax":"35","uid":"36661","company":"江苏小尼惠商网络科技有限公司","companyen":"江苏小尼惠商网络科技有限公司","published":"1421493694","changed":"1421493694","amount":0,"astatus":"0","cid":"20367","avatar":"http://www.nahehuo.com/media/images/company_m.jpg","utype":"1","wage":"25k-35k","publishtxt":"2015-01-17"},{"jid":"17468","position":"IOS高级程序员","type":"1","prov":"上海","city":"虹口区","wagemin":"8","wagemax":"10","uid":"40994","company":"上海久游网络科技有限公司","companyen":"上海久游网络科技有限公司","published":"1449458312","changed":"1449046357","amount":0,"astatus":"0","cid":"22989","avatar":"http://www.nahehuo.com/thumb/c/3/2d/24107_middle.jpg","utype":"1","wage":"8k-10k","publishtxt":"2015-12-07"},{"jid":"10551","position":"iPhone工程师","type":"1","prov":"上海","city":"虹口区","wagemin":"9","wagemax":"20","uid":"38745","company":"天联广告传媒上海有限公司","companyen":"天联广告传媒上海有限公司","published":"1425953609","changed":"1425953609","amount":0,"astatus":"0","cid":"21447","avatar":"http://www.nahehuo.com/thumb/7/6/cc/2789_middle.jpg","utype":"1","wage":"9k-20k","publishtxt":"2015-03-10"},{"jid":"10552","position":"网站营运总监","type":"1","prov":"上海","city":"虹口区","wagemin":"9","wagemax":"20","uid":"38745","company":"天联广告传媒上海有限公司","companyen":"天联广告传媒上海有限公司","published":"1425953606","changed":"1425953606","amount":0,"astatus":"0","cid":"21447","avatar":"http://www.nahehuo.com/thumb/7/6/cc/2789_middle.jpg","utype":"1","wage":"9k-20k","publishtxt":"2015-03-10"},{"jid":"17462","position":"C++ 服务端开发工程师","type":"1","prov":"上海","city":"虹口区","wagemin":"8","wagemax":"10","uid":"40994","company":"上海久游网络科技有限公司","companyen":"上海久游网络科技有限公司","published":"1449458312","changed":"1449032536","amount":0,"astatus":"0","cid":"22989","avatar":"http://www.nahehuo.com/thumb/c/3/2d/24107_middle.jpg","utype":"1","wage":"8k-10k","publishtxt":"2015-12-07"},{"jid":"17277","position":"3D特效","type":"1","prov":"上海","city":"虹口区","wagemin":"8","wagemax":"10","uid":"40994","company":"上海久游网络科技有限公司","companyen":"上海久游网络科技有限公司","published":"1449458312","changed":"1449046364","amount":0,"astatus":"0","cid":"22989","avatar":"http://www.nahehuo.com/thumb/c/3/2d/24107_middle.jpg","utype":"1","wage":"8k-10k","publishtxt":"2015-12-07"},{"jid":"17275","position":"动画特效师","type":"1","prov":"上海","city":"虹口区","wagemin":"8","wagemax":"12","uid":"40994","company":"上海久游网络科技有限公司","companyen":"上海久游网络科技有限公司","published":"1449458312","changed":"1449046360","amount":0,"astatus":"0","cid":"22989","avatar":"http://www.nahehuo.com/thumb/c/3/2d/24107_middle.jpg","utype":"1","wage":"8k-12k","publishtxt":"2015-12-07"},{"jid":"17273","position":"PHP开发工程师","type":"1","prov":"上海","city":"虹口区","wagemin":"8","wagemax":"10","uid":"40994","company":"上海久游网络科技有限公司","companyen":"上海久游网络科技有限公司","published":"1449458312","changed":"1449046359","amount":0,"astatus":"0","cid":"22989","avatar":"http://www.nahehuo.com/thumb/c/3/2d/24107_middle.jpg","utype":"1","wage":"8k-10k","publishtxt":"2015-12-07"},{"jid":"15958","position":"iOS高级研发工程师","type":"1","prov":"北京","city":"海淀区","wagemin":"15","wagemax":"30","uid":"32313","company":"北京蓝城兄弟文化传媒有限公司","companyen":"北京蓝城兄弟文化传媒有限公司","published":"1449799912","changed":"1449799912","amount":0,"astatus":"0","cid":"16655","avatar":"http://www.nahehuo.com/thumb/8/f/58/2234_middle.jpg","utype":"1","wage":"15k-30k","publishtxt":"2015-12-11"},{"jid":"24324","position":"前端工程师","type":"1","prov":"上海","city":"徐汇区","wagemin":"9","wagemax":"13","uid":"64200","company":"上海腾闻网络科技有限公司","companyen":"上海腾闻网络科技有限公司","published":"1432356166","changed":"1432356166","amount":0,"astatus":"0","cid":"46106","avatar":"http://www.nahehuo.com/media/images/company_m.jpg","utype":"1","wage":"9k-13k","publishtxt":"2015-05-23"}]
     */

    private int status;
    private String message;
    /**
     * jid : 24321
     * position : PHP工程师
     * type : 1
     * prov : 上海
     * city : 徐汇区
     * wagemin : 9
     * wagemax : 14
     * uid : 64200
     * company : 上海腾闻网络科技有限公司
     * companyen : 上海腾闻网络科技有限公司
     * published : 1432356112
     * changed : 1432356112
     * amount : 0
     * astatus : 0
     * cid : 46106
     * avatar : http://www.nahehuo.com/media/images/company_m.jpg
     * utype : 1
     * wage : 9k-14k
     * publishtxt : 2015-05-23
     */

    private List<DataEntity> data;


    public void setStatus(int status) { this.status = status;}


    public void setMessage(String message) { this.message = message;}


    public void setData(List<DataEntity> data) { this.data = data;}


    public int getStatus() { return status;}


    public String getMessage() { return message;}


    public List<DataEntity> getData() { return data;}


    public static class DataEntity {
        private String jid;
        private String position;
        private String type;
        private String prov;
        private String city;
        private String wagemin;
        private String wagemax;
        private String uid;
        private String company;
        private String companyen;
        private String published;
        private String changed;
        private int amount;
        private String astatus;
        private String cid;
        private String avatar;
        private String utype;
        private String wage;
        private String publishtxt;


        public void setJid(String jid) { this.jid = jid;}


        public void setPosition(String position) { this.position = position;}


        public void setType(String type) { this.type = type;}


        public void setProv(String prov) { this.prov = prov;}


        public void setCity(String city) { this.city = city;}


        public void setWagemin(String wagemin) { this.wagemin = wagemin;}


        public void setWagemax(String wagemax) { this.wagemax = wagemax;}


        public void setUid(String uid) { this.uid = uid;}


        public void setCompany(String company) { this.company = company;}


        public void setCompanyen(String companyen) {
            this.companyen = companyen;
        }


        public void setPublished(String published) {
            this.published = published;
        }


        public void setChanged(String changed) { this.changed = changed;}


        public void setAmount(int amount) { this.amount = amount;}


        public void setAstatus(String astatus) { this.astatus = astatus;}


        public void setCid(String cid) { this.cid = cid;}


        public void setAvatar(String avatar) { this.avatar = avatar;}


        public void setUtype(String utype) { this.utype = utype;}


        public void setWage(String wage) { this.wage = wage;}


        public void setPublishtxt(String publishtxt) {
            this.publishtxt = publishtxt;
        }


        public String getJid() { return jid;}


        public String getPosition() { return position;}


        public String getType() { return type;}


        public String getProv() { return prov;}


        public String getCity() { return city;}


        public String getWagemin() { return wagemin;}


        public String getWagemax() { return wagemax;}


        public String getUid() { return uid;}


        public String getCompany() { return company;}


        public String getCompanyen() { return companyen;}


        public String getPublished() { return published;}


        public String getChanged() { return changed;}


        public int getAmount() { return amount;}


        public String getAstatus() { return astatus;}


        public String getCid() { return cid;}


        public String getAvatar() { return avatar;}


        public String getUtype() { return utype;}


        public String getWage() { return wage;}


        public String getPublishtxt() { return publishtxt;}
    }
}
