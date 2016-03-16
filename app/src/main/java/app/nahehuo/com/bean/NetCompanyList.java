package app.nahehuo.com.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/3/16.
 */
public class NetCompanyList {

    /**
     * message : 企业列表
     * code : 200
     * data : [{"cid":2151,"uid":16159,"name":"迪彩（上海）投资管理咨询有限公司","logo":null,"cstatus":0,"financle":"0","prov":"上海","city":"浦东新区","orgtype":"0","website":"http://pictureworks.biz/","size":"100-499人","nature":"外商独资","industry":"其他行业"},{"cid":1145,"uid":12934,"name":"今翌信息科技（上海）有限公司","logo":"http://test2015.zcspin.com/thumb/1/b/fb/949_big.jpg","cstatus":3,"financle":"天使轮","prov":"上海","city":"松江区","orgtype":"1","website":"http://www.kinyi.cn/","size":"100-499人","nature":"民营","industry":"互联网"},{"cid":52874,"uid":71189,"name":"上海专门网站测试公司专用","logo":null,"cstatus":3,"financle":"0","prov":"","city":"","orgtype":"","website":"","size":"0","nature":"0","industry":null},{"cid":52879,"uid":71198,"name":"企业C","logo":"http://test2015.zcspin.com/thumb/7/7/65/5719_big.jpg","cstatus":0,"financle":"0","prov":null,"city":null,"orgtype":"0","website":null,"size":"0","nature":"0","industry":null},{"cid":52878,"uid":71197,"name":"企业B","logo":"http://test2015.zcspin.com/thumb/9/c/63/5718_big.jpg","cstatus":0,"financle":"0","prov":null,"city":null,"orgtype":"0","website":null,"size":"0","nature":"0","industry":null},{"cid":2139,"uid":15996,"name":"企业A","logo":"http://test2015.zcspin.com/thumb/7/1/a7/5717_big.jpg","cstatus":0,"financle":"0","prov":"","city":"","orgtype":"","website":"","size":"0","nature":"0","industry":null},{"cid":34146,"uid":52232,"name":"北京京都紫华康体连锁经营有限公司","logo":null,"cstatus":0,"financle":"0","prov":"","city":"","orgtype":"","website":"","size":"0","nature":"0","industry":null},{"cid":2140,"uid":15998,"name":"是飞洒大赛的有限公司","logo":"http://test2015.zcspin.com/thumb/9/c/63/5718_big.jpg","cstatus":0,"financle":"0","prov":"","city":"","orgtype":"","website":"","size":"0","nature":"0","industry":null},{"cid":2141,"uid":15999,"name":"e飞洒大赛的有限公司","logo":"http://test2015.zcspin.com/thumb/7/7/65/5719_big.jpg","cstatus":0,"financle":"0","prov":"","city":"","orgtype":"","website":"","size":"0","nature":"0","industry":null},{"cid":52877,"uid":71192,"name":"有限责任","logo":null,"cstatus":0,"financle":"0","prov":null,"city":null,"orgtype":"0","website":null,"size":"0","nature":"0","industry":null},{"cid":52873,"uid":71187,"name":"关联账号测试信息技术有限公司","logo":null,"cstatus":0,"financle":"未融资","prov":"黑龙江","city":"齐齐哈尔","orgtype":"0","website":"","size":"1000-9999人","nature":"0","industry":"证券/基金/期货"},{"cid":52876,"uid":71191,"name":"乱填股份有限公司","logo":null,"cstatus":0,"financle":"0","prov":null,"city":null,"orgtype":"0","website":null,"size":"0","nature":"0","industry":null},{"cid":52875,"uid":71190,"name":"芭芭拉测试一下哟西公司","logo":null,"cstatus":0,"financle":"0","prov":null,"city":null,"orgtype":"0","website":null,"size":"0","nature":"0","industry":null},{"cid":14249,"uid":29640,"name":"上海时代商务咨询有限公司","logo":null,"cstatus":0,"financle":"0","prov":"上海","city":"嘉定区","orgtype":"0","website":null,"size":"20-99人","nature":"民营","industry":"投资/外汇"},{"cid":52872,"uid":71186,"name":"打打打打打打打打公司","logo":null,"cstatus":0,"financle":"0","prov":"","city":"","orgtype":"","website":"","size":"0","nature":"0","industry":null}]
     */

    private String message;
    private int code;
    /**
     * cid : 2151
     * uid : 16159
     * name : 迪彩（上海）投资管理咨询有限公司
     * logo : null
     * cstatus : 0
     * financle : 0
     * prov : 上海
     * city : 浦东新区
     * orgtype : 0
     * website : http://pictureworks.biz/
     * size : 100-499人
     * nature : 外商独资
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
        private int cid;
        private int uid;
        private String name;
        private String logo;
        private int cstatus;
        private String financle;
        private String prov;
        private String city;
        private String orgtype;
        private String website;
        private String size;
        private String nature;
        private String industry;


        public void setCid(int cid) { this.cid = cid;}


        public void setUid(int uid) { this.uid = uid;}


        public void setName(String name) { this.name = name;}


        public void setLogo(String logo) { this.logo = logo;}


        public void setCstatus(int cstatus) { this.cstatus = cstatus;}


        public void setFinancle(String financle) { this.financle = financle;}


        public void setProv(String prov) { this.prov = prov;}


        public void setCity(String city) { this.city = city;}


        public void setOrgtype(String orgtype) { this.orgtype = orgtype;}


        public void setWebsite(String website) { this.website = website;}


        public void setSize(String size) { this.size = size;}


        public void setNature(String nature) { this.nature = nature;}


        public void setIndustry(String industry) { this.industry = industry;}


        public int getCid() { return cid;}


        public int getUid() { return uid;}


        public String getName() { return name;}


        public String getLogo() { return logo;}


        public int getCstatus() { return cstatus;}


        public String getFinancle() { return financle;}


        public String getProv() { return prov;}


        public String getCity() { return city;}


        public String getOrgtype() { return orgtype;}


        public String getWebsite() { return website;}


        public String getSize() { return size;}


        public String getNature() { return nature;}


        public String getIndustry() { return industry;}
    }
}
