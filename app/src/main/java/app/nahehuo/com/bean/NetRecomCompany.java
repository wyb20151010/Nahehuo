package app.nahehuo.com.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/3/16.
 */
public class NetRecomCompany {

    /**
     * message : 获取推荐企业成功
     * code : 200
     * data : [{"cid":1145,"uid":12934,"name":"今翌信息科技（上海）有限公司","logo":"http://test2015.zcspin.com/thumb/1/b/fb/949_big.jpg","cstatus":3,"financle":"天使轮","prov":"上海","city":"松江区","orgtype":"1","website":"http://www.kinyi.cn/","size":"100-499人","nature":"民营","industry":"互联网"},{"cid":29063,"uid":47147,"name":"北京慧博科技有限公司上海分公司","logo":"http://test2015.zcspin.com/thumb/e/d/20/3320_big.jpg","cstatus":3,"financle":"未融资","prov":"上海","city":"杨浦区","orgtype":"0","website":"http://www.jkcrm.cn","size":"100-499人","nature":"民营","industry":"互联网"},{"cid":14621,"uid":30043,"name":"华为技术有限公司","logo":"http://test2015.zcspin.com/thumb/0/3/15/1770_big.jpg","cstatus":0,"financle":"0","prov":"广东","city":"深圳","orgtype":"0","website":"http://www.huawei.com","size":"1000-9999人","nature":"0","industry":"计算机系统/通信"},{"cid":16077,"uid":31637,"name":"北京奇虎科技有限公司","logo":"http://test2015.zcspin.com/thumb/d/e/cd/3378_big.jpg","cstatus":0,"financle":"0","prov":"北京","city":"朝阳区","orgtype":"0","website":"http://360.cn/","size":"1000-9999人","nature":"上市公司","industry":"互联网"},{"cid":13956,"uid":29303,"name":"美丽说（北京）网络科技有限公司","logo":"http://test2015.zcspin.com/thumb/b/e/fb/3381_big.jpg","cstatus":0,"financle":"0","prov":"北京","city":"海淀区","orgtype":"0","website":"http://www.meilishuo.com/","size":"500-999人","nature":"民营","industry":"互联网"},{"cid":1015,"uid":12666,"name":"美团网","logo":"http://test2015.zcspin.com/thumb/7/1/e7/3022_big.jpg","cstatus":0,"financle":"D轮以上","prov":"北京","city":"朝阳区","orgtype":"0","website":"http://www.meituan.com","size":"1000-9999人","nature":"0","industry":"移动互联网"},{"cid":14470,"uid":29872,"name":"炫一下（北京）科技有限公司","logo":"http://test2015.zcspin.com/thumb/d/7/1c/1551_big.jpg","cstatus":0,"financle":"0","prov":"北京","city":"朝阳区","orgtype":"0","website":"http://www.yixia.com","size":"20-99人","nature":"民营","industry":"互联网"}]
     */

    private String message;
    private int code;
    /**
     * cid : 1145
     * uid : 12934
     * name : 今翌信息科技（上海）有限公司
     * logo : http://test2015.zcspin.com/thumb/1/b/fb/949_big.jpg
     * cstatus : 3
     * financle : 天使轮
     * prov : 上海
     * city : 松江区
     * orgtype : 1
     * website : http://www.kinyi.cn/
     * size : 100-499人
     * nature : 民营
     * industry : 互联网
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
