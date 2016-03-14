package app.nahehuo.com.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/3/10.
 */
public class NetRecomJob {

    /**
     * message : 获取推荐职位成功
     * code : 200
     * data : [{"jid":14292,"position":"财务","type":1,"prov":"上海","city":"黄浦区","workexp":"1-2年","wagemin":3,"wagemax":8,"edu":"大专","attraction":"福利待遇好，工作环境好","cid":22904,"company":"色得锐克（上海）商务咨询有限公司","logo":"http://test2015.zcspin.com/http://test2015.zcspin.com/thumb/f/a/43/2970_big.jpg","cstatus":3,"financle":"未融资","size":"20-99人","positiontype":"财务","industry":"影视/媒体"},{"jid":14293,"position":"剪辑师","type":1,"prov":"上海","city":"黄浦区","workexp":"1-2年","wagemin":3,"wagemax":8,"edu":"大专","attraction":"福利待遇好","cid":22904,"company":"色得锐克（上海）商务咨询有限公司","logo":"http://test2015.zcspin.com/http://test2015.zcspin.com/thumb/f/a/43/2970_big.jpg","cstatus":3,"financle":"未融资","size":"20-99人","positiontype":"技术研发","industry":"影视/媒体"},{"jid":94068,"position":"WEB前端技术开发工程师","type":1,"prov":"上海","city":"浦东新区","workexp":"1-2年","wagemin":6,"wagemax":10,"edu":"大专","attraction":"Web前端开发","cid":2151,"company":"迪彩（上海）投资管理咨询有限公司","logo":"http://test2015.zcspin.com/","cstatus":0,"financle":false,"size":"100-499人","positiontype":"其他","industry":"其他行业"},{"jid":105708,"position":"海洋馆摄影门店店员","type":1,"prov":"上海","city":"浦东新区","workexp":false,"wagemin":3,"wagemax":5,"edu":false,"attraction":"缴纳社保，晋升空间大","cid":2151,"company":"迪彩（上海）投资管理咨询有限公司","logo":"http://test2015.zcspin.com/","cstatus":0,"financle":false,"size":"100-499人","positiontype":"其他","industry":"其他行业"}]
     */

    private String message;
    private int code;
    /**
     * jid : 14292
     * position : 财务
     * type : 1
     * prov : 上海
     * city : 黄浦区
     * workexp : 1-2年
     * wagemin : 3
     * wagemax : 8
     * edu : 大专
     * attraction : 福利待遇好，工作环境好
     * cid : 22904
     * company : 色得锐克（上海）商务咨询有限公司
     * logo : http://test2015.zcspin.com/http://test2015.zcspin.com/thumb/f/a/43/2970_big.jpg
     * cstatus : 3
     * financle : 未融资
     * size : 20-99人
     * positiontype : 财务
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
        private int cid;
        private String company;
        private String logo;
        private int cstatus;
        private String financle;
        private String size;
        private String positiontype;
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


        public void setCid(int cid) { this.cid = cid;}


        public void setCompany(String company) { this.company = company;}


        public void setLogo(String logo) { this.logo = logo;}


        public void setCstatus(int cstatus) { this.cstatus = cstatus;}


        public void setFinancle(String financle) { this.financle = financle;}


        public void setSize(String size) { this.size = size;}


        public void setPositiontype(String positiontype) {
            this.positiontype = positiontype;
        }


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


        public int getCid() { return cid;}


        public String getCompany() { return company;}


        public String getLogo() { return logo;}


        public int getCstatus() { return cstatus;}


        public String getFinancle() { return financle;}


        public String getSize() { return size;}


        public String getPositiontype() { return positiontype;}


        public String getIndustry() { return industry;}
    }
}
