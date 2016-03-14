package app.nahehuo.com.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/3/10.
 */
public class NetListJob {

    /**
     * message : 职位列表
     * code : 200
     * data : [{"jid":673,"position":"影视剧组招聘跟组演员群众演员摄影助理化妆助理导演助理等","type":1,"prov":"北京","city":"西城区","workexp":"0","wagemin":5,"wagemax":8,"edu":"0","attraction":"包食宿","published":1431868329,"cid":1645,"company":"北京天马流星影视文化传媒中心","logo":null,"cstatus":3,"financle":"0","website":"","positiontype":"艺术","size":"100-499人","industry":"影视/媒体"},{"jid":2506,"position":"证券业务员","type":1,"prov":"天津","city":"和平区","workexp":"1年以下","wagemin":4,"wagemax":5,"edu":"高中","attraction":"培养创业型人才，优秀员工享有股份分红，可提供住宿","published":1429235802,"cid":3672,"company":"天津博睿丰网络科技有限公司","logo":"http://test2015.zcspin.com/thumb/3/3/b3/1527_big.jpg","cstatus":3,"financle":"0","website":"http://www.yidejia.com","positiontype":"金融","size":"20-99人","industry":"互联网"},{"jid":2507,"position":"办公室销售","type":1,"prov":"天津","city":"和平区","workexp":"1年以下","wagemin":4,"wagemax":5,"edu":"高中","attraction":"培养创业型人才，优秀员工享有股份分红，可提供住宿","published":1429235800,"cid":3672,"company":"天津博睿丰网络科技有限公司","logo":"http://test2015.zcspin.com/thumb/3/3/b3/1527_big.jpg","cstatus":3,"financle":"0","website":"http://www.yidejia.com","positiontype":"客户服务","size":"20-99人","industry":"互联网"},{"jid":2508,"position":"电子商务营销员","type":1,"prov":"天津","city":"和平区","workexp":"1年以下","wagemin":4,"wagemax":5,"edu":"大专","attraction":"培养创业型人才，优秀员工享有股份分红，可提供住宿","published":1429236201,"cid":3672,"company":"天津博睿丰网络科技有限公司","logo":"http://test2015.zcspin.com/thumb/3/3/b3/1527_big.jpg","cstatus":3,"financle":"0","website":"http://www.yidejia.com","positiontype":"市场营销","size":"20-99人","industry":"互联网"},{"jid":2547,"position":"Android开发工程师","type":1,"prov":"江苏","city":"常州","workexp":"1-2年","wagemin":6,"wagemax":8,"edu":"大专","attraction":"交五险 餐补 年度旅游 晋升空间大 办公环境优美","published":1431758441,"cid":4415,"company":"常州麦拉风网络科技有限公司","logo":null,"cstatus":0,"financle":"0","website":"http://www.mylafe.com","positiontype":"技术研发","size":"20-99人","industry":"互联网"},{"jid":2548,"position":"IOS开发经理","type":1,"prov":"江苏","city":"常州","workexp":"1-2年","wagemin":8,"wagemax":10,"edu":"大专","attraction":"交五险 餐补 年度旅游 晋升空间大 办公环境优美","published":1431758444,"cid":4415,"company":"常州麦拉风网络科技有限公司","logo":null,"cstatus":0,"financle":"0","website":"http://www.mylafe.com","positiontype":"技术研发","size":"20-99人","industry":"互联网"},{"jid":2553,"position":"IOS开发工程师","type":1,"prov":"江苏","city":"常州","workexp":"1-2年","wagemin":6,"wagemax":8,"edu":"大专","attraction":"交五险 餐补 年度旅游 晋升空间大 办公环境优美","published":1431758446,"cid":4415,"company":"常州麦拉风网络科技有限公司","logo":null,"cstatus":0,"financle":"0","website":"http://www.mylafe.com","positiontype":"技术研发","size":"20-99人","industry":"互联网"},{"jid":2623,"position":"三维设计师（角色、模型、游戏场景(道具)","type":1,"prov":"山东","city":"青岛市","workexp":"1-2年","wagemin":3,"wagemax":6,"edu":"大专","attraction":"双休、五险一金","published":1426669348,"cid":5478,"company":"青岛蜗牛影视策划有限公司","logo":"http://test2015.zcspin.com/thumb/f/1/28/1197_big.jpg","cstatus":3,"financle":"未融资","website":"http://www.woniucg.com","positiontype":"设计&创意","size":"100-499人","industry":"体育/动漫"},{"jid":2787,"position":"网络销售","type":1,"prov":"北京","city":"朝阳区","workexp":"1-2年","wagemin":3,"wagemax":5,"edu":"大专","attraction":"五险、餐补、房补、全勤奖、朝阳区大望路CBD","published":1434185000,"cid":8873,"company":"北京铭声音文化传媒有限公司","logo":"http://test2015.zcspin.com/thumb/1/1/9f/1262_big.jpg","cstatus":0,"financle":"0","website":"","positiontype":"销售","size":"0","industry":"影视/媒体"},{"jid":2792,"position":"业务助理","type":1,"prov":"北京","city":"朝阳区","workexp":"1-2年","wagemin":3,"wagemax":5,"edu":"大专","attraction":"五险、餐补、房补、全勤奖，白领CBD商圈","published":1434184999,"cid":8873,"company":"北京铭声音文化传媒有限公司","logo":"http://test2015.zcspin.com/thumb/1/1/9f/1262_big.jpg","cstatus":0,"financle":"0","website":"","positiontype":"销售","size":"0","industry":"影视/媒体"},{"jid":3367,"position":"软件工程师","type":1,"prov":"河北","city":"邢台","workexp":"1-2年","wagemin":2,"wagemax":8,"edu":"大专","attraction":"休6天/月+保险+婚假丧假产假带薪年假+旅游+生日","published":1429165892,"cid":14211,"company":"河北擎天网络科技有限公司","logo":"http://test2015.zcspin.com/thumb/8/3/9f/2972_big.jpg","cstatus":3,"financle":"未融资","website":"http://www.hbqingtian.com","positiontype":"技术支持","size":"20-99人","industry":"计算机软件/硬件"},{"jid":3370,"position":"业务经理","type":1,"prov":"河北","city":"邢台","workexp":"应届毕业生","wagemin":3,"wagemax":8,"edu":"初中","attraction":"外出旅游、员工过生日、劳动保险","published":1429065308,"cid":14211,"company":"河北擎天网络科技有限公司","logo":"http://test2015.zcspin.com/thumb/8/3/9f/2972_big.jpg","cstatus":3,"financle":"未融资","website":"http://www.hbqingtian.com","positiontype":"管理","size":"20-99人","industry":"计算机软件/硬件"},{"jid":3369,"position":"SEO网站优化","type":1,"prov":"河北","city":"邢台","workexp":"应届毕业生","wagemin":2,"wagemax":3,"edu":"初中","attraction":"外出旅游、员工过生日、劳动保险","published":1429683290,"cid":14211,"company":"河北擎天网络科技有限公司","logo":"http://test2015.zcspin.com/thumb/8/3/9f/2972_big.jpg","cstatus":3,"financle":"未融资","website":"http://www.hbqingtian.com","positiontype":"技术支持","size":"20-99人","industry":"计算机软件/硬件"},{"jid":3371,"position":"淘宝客服","type":1,"prov":"河北","city":"邢台","workexp":"应届毕业生","wagemin":2,"wagemax":5,"edu":"初中","attraction":"外出旅游、员工过生日、劳动保险","published":1429065304,"cid":14211,"company":"河北擎天网络科技有限公司","logo":"http://test2015.zcspin.com/thumb/8/3/9f/2972_big.jpg","cstatus":3,"financle":"未融资","website":"http://www.hbqingtian.com","positiontype":"技术支持","size":"20-99人","industry":"计算机软件/硬件"},{"jid":3372,"position":"淘宝店铺管理","type":1,"prov":"河北","city":"邢台","workexp":"应届毕业生","wagemin":2,"wagemax":8,"edu":"0","attraction":"外出旅游、员工过生日、劳动保险+休6天/月","published":1429944129,"cid":14211,"company":"河北擎天网络科技有限公司","logo":"http://test2015.zcspin.com/thumb/8/3/9f/2972_big.jpg","cstatus":3,"financle":"未融资","website":"http://www.hbqingtian.com","positiontype":"技术支持","size":"20-99人","industry":"计算机软件/硬件"}]
     */

    private String message;
    private int code;
    /**
     * jid : 673
     * position : 影视剧组招聘跟组演员群众演员摄影助理化妆助理导演助理等
     * type : 1
     * prov : 北京
     * city : 西城区
     * workexp : 0
     * wagemin : 5
     * wagemax : 8
     * edu : 0
     * attraction : 包食宿
     * published : 1431868329
     * cid : 1645
     * company : 北京天马流星影视文化传媒中心
     * logo : null
     * cstatus : 3
     * financle : 0
     * website :
     * positiontype : 艺术
     * size : 100-499人
     * industry : 影视/媒体
     */

    private List<DataEntity> data;


    public void setMessage(String message) { this.message = message;}


    public void setCode(int code) { this.code = code;}


    public void setData(List<DataEntity> data) { this.data = data;}


    public String getMessage() { return message;}


    public int getCode() { return code;}


    public List<DataEntity> getData() { return data;}


    public static class DataEntity {
        private int jid;
        private String position;
        private int type;
        private String prov;
        private String city;
        private String workexp;
        private int wagemin;
        private int wagemax;
        private String edu;
        private String attraction;
        private int published;
        private int cid;
        private String company;
        private String logo;
        private int cstatus;
        private String financle;
        private String website;
        private String positiontype;
        private String size;
        private String industry;


        public void setJid(int jid) { this.jid = jid;}


        public void setPosition(String position) { this.position = position;}


        public void setType(int type) { this.type = type;}


        public void setProv(String prov) { this.prov = prov;}


        public void setCity(String city) { this.city = city;}


        public void setWorkexp(String workexp) { this.workexp = workexp;}


        public void setWagemin(int wagemin) { this.wagemin = wagemin;}


        public void setWagemax(int wagemax) { this.wagemax = wagemax;}


        public void setEdu(String edu) { this.edu = edu;}


        public void setAttraction(String attraction) {
            this.attraction = attraction;
        }


        public void setPublished(int published) { this.published = published;}


        public void setCid(int cid) { this.cid = cid;}


        public void setCompany(String company) { this.company = company;}


        public void setLogo(String logo) { this.logo = logo;}


        public void setCstatus(int cstatus) { this.cstatus = cstatus;}


        public void setFinancle(String financle) { this.financle = financle;}


        public void setWebsite(String website) { this.website = website;}


        public void setPositiontype(String positiontype) {
            this.positiontype = positiontype;
        }


        public void setSize(String size) { this.size = size;}


        public void setIndustry(String industry) { this.industry = industry;}


        public int getJid() { return jid;}


        public String getPosition() { return position;}


        public int getType() { return type;}


        public String getProv() { return prov;}


        public String getCity() { return city;}


        public String getWorkexp() { return workexp;}


        public int getWagemin() { return wagemin;}


        public int getWagemax() { return wagemax;}


        public String getEdu() { return edu;}


        public String getAttraction() { return attraction;}


        public int getPublished() { return published;}


        public int getCid() { return cid;}


        public String getCompany() { return company;}


        public String getLogo() { return logo;}


        public int getCstatus() { return cstatus;}


        public String getFinancle() { return financle;}


        public String getWebsite() { return website;}


        public String getPositiontype() { return positiontype;}


        public String getSize() { return size;}


        public String getIndustry() { return industry;}
    }
}
