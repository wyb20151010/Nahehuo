package app.nahehuo.com.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/3/17.
 */
public class NetCompanyJob {

    /**
     * message : 企业职位获取成功
     * code : 200
     * data : [{"jid":105713,"position":"收银员","type":1,"prov":"上海","city":"浦东新区","workexp":"0","wagemin":3,"wagemax":5,"edu":"0","attraction":"前景好，交金，工作环境好。","published":1457441847,"cid":2151,"company":"迪彩（上海）投资管理咨询有限公司","logo":null,"cstatus":0,"financle":"0","website":"http://pictureworks.biz/","positiontype":"其他","size":"100-499人","industry":"其他行业"},{"jid":105711,"position":"海洋馆摄影门店销售","type":1,"prov":"上海","city":"浦东新区","workexp":"0","wagemin":3,"wagemax":5,"edu":"0","attraction":"交金，发展空间大，前景好，包工作餐","published":1457441858,"cid":2151,"company":"迪彩（上海）投资管理咨询有限公司","logo":null,"cstatus":0,"financle":"0","website":"http://pictureworks.biz/","positiontype":"销售","size":"100-499人","industry":"其他行业"},{"jid":105708,"position":"海洋馆摄影门店店员","type":1,"prov":"上海","city":"浦东新区","workexp":"0","wagemin":3,"wagemax":5,"edu":"0","attraction":"缴纳社保，晋升空间大","published":1457441922,"cid":2151,"company":"迪彩（上海）投资管理咨询有限公司","logo":null,"cstatus":0,"financle":"0","website":"http://pictureworks.biz/","positiontype":"其他","size":"100-499人","industry":"其他行业"},{"jid":105714,"position":"销售","type":1,"prov":"上海","city":"浦东新区","workexp":"0","wagemin":3,"wagemax":5,"edu":"0","attraction":"前景好，交金，每月可拿提成，包工作餐","published":1457441850,"cid":2151,"company":"迪彩（上海）投资管理咨询有限公司","logo":null,"cstatus":0,"financle":"0","website":"http://pictureworks.biz/","positiontype":"其他","size":"100-499人","industry":"其他行业"},{"jid":94059,"position":"设计组长","type":1,"prov":"上海","city":"浦东新区","workexp":"3-5年","wagemin":4,"wagemax":10,"edu":"大专","attraction":"发展平台大，","published":1457441853,"cid":2151,"company":"迪彩（上海）投资管理咨询有限公司","logo":null,"cstatus":0,"financle":"0","website":"http://pictureworks.biz/","positiontype":"其他","size":"100-499人","industry":"其他行业"},{"jid":94068,"position":"WEB前端技术开发工程师","type":1,"prov":"上海","city":"浦东新区","workexp":"1-2年","wagemin":6,"wagemax":10,"edu":"大专","attraction":"Web前端开发","published":1457441855,"cid":2151,"company":"迪彩（上海）投资管理咨询有限公司","logo":null,"cstatus":0,"financle":"0","website":"http://pictureworks.biz/","positiontype":"其他","size":"100-499人","industry":"其他行业"},{"jid":94086,"position":"C++ 程序工程师","type":1,"prov":"上海","city":"浦东新区","workexp":"1-2年","wagemin":7,"wagemax":10,"edu":"本科","attraction":"C++，Java，软件工程师，工程师","published":1457441862,"cid":2151,"company":"迪彩（上海）投资管理咨询有限公司","logo":null,"cstatus":0,"financle":"0","website":"http://pictureworks.biz/","positiontype":"其他","size":"100-499人","industry":"其他行业"},{"jid":94073,"position":"平面设计师","type":1,"prov":"上海","city":"浦东新区","workexp":"0","wagemin":4,"wagemax":8,"edu":"大专","attraction":"交金，发展空间大，前景好","published":1457441864,"cid":2151,"company":"迪彩（上海）投资管理咨询有限公司","logo":null,"cstatus":0,"financle":"0","website":"http://pictureworks.biz/","positiontype":"其他","size":"100-499人","industry":"其他行业"},{"jid":105709,"position":"门店销售实习生","type":2,"prov":"上海","city":"浦东新区","workexp":"0","wagemin":2,"wagemax":3,"edu":"0","attraction":"发展前景好，实习加就业","published":1457441920,"cid":2151,"company":"迪彩（上海）投资管理咨询有限公司","logo":null,"cstatus":0,"financle":"0","website":"http://pictureworks.biz/","positiontype":"销售","size":"100-499人","industry":"其他行业"},{"jid":105710,"position":"管培生","type":2,"prov":"上海","city":"浦东新区","workexp":"0","wagemin":2,"wagemax":3,"edu":"0","attraction":"实习加就业，升职空间大","published":1457441925,"cid":2151,"company":"迪彩（上海）投资管理咨询有限公司","logo":null,"cstatus":0,"financle":"0","website":"http://pictureworks.biz/","positiontype":"运营","size":"100-499人","industry":"其他行业"}]
     */

    private String message;
    private int code;
    /**
     * jid : 105713
     * position : 收银员
     * type : 1
     * prov : 上海
     * city : 浦东新区
     * workexp : 0
     * wagemin : 3
     * wagemax : 5
     * edu : 0
     * attraction : 前景好，交金，工作环境好。
     * published : 1457441847
     * cid : 2151
     * company : 迪彩（上海）投资管理咨询有限公司
     * logo : null
     * cstatus : 0
     * financle : 0
     * website : http://pictureworks.biz/
     * positiontype : 其他
     * size : 100-499人
     * industry : 其他行业
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
        private long published;
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


        public void setPublished(long published) { this.published = published;}


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


        public long getPublished() { return published;}


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
