package app.nahehuo.com.bean;

/**
 * Created by Administrator on 2016/3/16.
 */
public class NetCompanyDetail {

    /**
     * message : 企业详情获取成功
     * code : 200
     * data : {"cid":2151,"company":"迪彩（上海）投资管理咨询有限公司","logo":null,"cstatus":0,"address":"上海川沙路，宏图大楼6999号49栋6楼6001室","prov":"上海","city":"浦东新区","financle":"0","website":"http://pictureworks.biz/","content":"迪彩（上海）投资管理咨询有限公司的总部设立于新加坡。总公司成立于2003年，主要\n从事业务是主题乐园及景点的影像科技开发。随着业务的不断发展，全球知名乐园入驻\n中国，新加坡总部于2014年来到中国上海成立迪彩公司。同时迪彩也是Pictureworks公司全球科技研发中心，满足Pictureworks全球分公司影像科技需求，分公司网点遍布：\n美国，英国，德国，日本，墨西哥，加拿大，澳洲等。\n\nPictureworks目前国外项目众多，包括乐高，哈利波特，Hello Kitty等知名品牌，在\n国际娱乐影像行业，享有极高的知名度。在许多国际平台得到合作伙伴的高度认可。全\n球员工人数逾800人。\n\nPictureworks优势在于提供人性化服务，高端移动科技，让客户拥有更美好的互动体\n验。我们的工作理念就是如何让客户在主题乐园里享受到\u201cMagic\u201d的感觉。\n\n创造娱乐，捕捉激情，分享快乐就是Pictureworks梦想所在。\n\n你准备好了吗？欢迎您的加入!\n\n ","size":"100-499人","industry":"其他行业","avgcomment":0}
     */

    private String message;
    private int code;
    /**
     * cid : 2151
     * company : 迪彩（上海）投资管理咨询有限公司
     * logo : null
     * cstatus : 0
     * address : 上海川沙路，宏图大楼6999号49栋6楼6001室
     * prov : 上海
     * city : 浦东新区
     * financle : 0
     * website : http://pictureworks.biz/
     * content : 迪彩（上海）投资管理咨询有限公司的总部设立于新加坡。总公司成立于2003年，主要
     从事业务是主题乐园及景点的影像科技开发。随着业务的不断发展，全球知名乐园入驻
     中国，新加坡总部于2014年来到中国上海成立迪彩公司。同时迪彩也是Pictureworks公司全球科技研发中心，满足Pictureworks全球分公司影像科技需求，分公司网点遍布：
     美国，英国，德国，日本，墨西哥，加拿大，澳洲等。

     Pictureworks目前国外项目众多，包括乐高，哈利波特，Hello Kitty等知名品牌，在
     国际娱乐影像行业，享有极高的知名度。在许多国际平台得到合作伙伴的高度认可。全
     球员工人数逾800人。

     Pictureworks优势在于提供人性化服务，高端移动科技，让客户拥有更美好的互动体
     验。我们的工作理念就是如何让客户在主题乐园里享受到“Magic”的感觉。

     创造娱乐，捕捉激情，分享快乐就是Pictureworks梦想所在。

     你准备好了吗？欢迎您的加入!


     * size : 100-499人
     * industry : 其他行业
     * avgcomment : 0
     */

    private DataEntity data;


    public void setMessage(String message) { this.message = message;}


    public void setCode(int code) { this.code = code;}


    public void setData(DataEntity data) { this.data = data;}


    public String getMessage() { return message;}


    public int getCode() { return code;}


    public DataEntity getData() { return data;}


    public static class DataEntity {
        private int cid;
        private String company;
        private String logo;
        private int cstatus;
        private String address;
        private String prov;
        private String city;
        private String financle;
        private String website;
        private String content;
        private String size;
        private String industry;
        private float avgcomment;


        public void setCid(int cid) { this.cid = cid;}


        public void setCompany(String company) { this.company = company;}


        public void setLogo(String logo) { this.logo = logo;}


        public void setCstatus(int cstatus) { this.cstatus = cstatus;}


        public void setAddress(String address) { this.address = address;}


        public void setProv(String prov) { this.prov = prov;}


        public void setCity(String city) { this.city = city;}


        public void setFinancle(String financle) { this.financle = financle;}


        public void setWebsite(String website) { this.website = website;}


        public void setContent(String content) { this.content = content;}


        public void setSize(String size) { this.size = size;}


        public void setIndustry(String industry) { this.industry = industry;}


        public void setAvgcomment(float avgcomment) {
            this.avgcomment = avgcomment;
        }


        public int getCid() { return cid;}


        public String getCompany() { return company;}


        public String getLogo() { return logo;}


        public int getCstatus() { return cstatus;}


        public String getAddress() { return address;}


        public String getProv() { return prov;}


        public String getCity() { return city;}


        public String getFinancle() { return financle;}


        public String getWebsite() { return website;}


        public String getContent() { return content;}


        public String getSize() { return size;}


        public String getIndustry() { return industry;}


        public float getAvgcomment() { return avgcomment;}
    }
}
