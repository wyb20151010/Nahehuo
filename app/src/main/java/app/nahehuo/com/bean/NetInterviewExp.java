package app.nahehuo.com.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/3/14.
 */
public class NetInterviewExp {

    /**
     * message : 获取面试经验成功
     * code : 200
     * data : {"interview":[{"id":55,"uid":10002,"anony":1,"score":"4.0","jobmatch":"4.0","result":"接到offer并入职( ^_^ )","oneword":"不错","descp":"做了面试题，还有简单的面谈，创业气氛很浓。","created":1416902201,"pid":3,"username":"倪盛盛","avatar":"http://test2015.zcspin.comhttp://test2015.zcspin.com/thumb/1/4/5e/3327_big.jpg"},{"id":56,"uid":10002,"anony":2,"score":"4.0","jobmatch":"4.0","result":"未接到offer ⊙︿⊙","oneword":"无","descp":"抱着试一试的态度去的，可惜，没录上","created":1421111217},{"id":57,"uid":10002,"anony":2,"score":"4.0","jobmatch":"4.0","result":"接到offer并入职( ^_^ )","oneword":"自信是一切的必须","descp":"因为只是短期的销售人员，所以就进行了简单的询问与个性测试","created":1422429729}],"company":{"cid":3672,"company":"天津博睿丰网络科技有限公司","logo":"http://test2015.zcspin.com/thumb/3/3/b3/1527_big.jpg","cstatus":3,"address":"天津和平海光寺","prov":"天津","city":"和平区","financle":"0","website":"http://www.yidejia.com","content":"经传集团简介：\r\n经传集团成立于2009年，集团总部设立在中国第三大城市\u2014\u2014广州市番禺区。目前投资包括：金融软件产品项目、化妆品生产研发及销售、企业管理教育培训产品研发等项目！\r\n现集团旗下共三大以公司化形式独立运营的事业部。分别为：软件事业部即广州经传信息科技有限公司，电商事业部即广州伊的家网络科技有限公司，教育事业部即广州伊智教育科技有限公司。其中软件事业部和电商事业部在过去的几年中取得非凡的成就，教育事业部在今年九月份正式启动。\r\n软件事业部简介：\r\n软件事业部于2009年9月以1000万注册资金在广州市工商局注册成立广州经传信息科技有限公司，并开始在全国范围内从事国内证券软件技术开发、服务。同时，公司也是珠三角地区首家获得上海证券交易所和深圳证券交易所行情数据授权的信息发布商。\r\n公司凭借强大的技术研发中心、依托具有十余年的证券行业经验。与敏锐的市场洞察力的核心团队，建立科学有效的数学模型，并对大量丰富的信息与数据进行挖掘，为投资者提供了一套数据丰富、操作简单、功能精专，集价值、资金、趋势为一体的证券辅助决策软件。\r\n电商事业部简介：\r\n电商事业部于2009年4月以100万注册资金在广州市工商局注册成立广州伊的家网络科技有限公司。伊的家是一家集研发、生产、设计、销售于一体的电商企业。旗下拥有两大品牌：妍诗美（肌肤补水化妆品品牌）、妍膳美（美容营养食品品牌）\r\n    2011年5月伊的家启动全国配送中心，首批仓储配送中心已覆盖广州、海口、南昌、郑州、成都、合肥、哈尔滨、天津等9个城市，实现了全民体验\u201cT+1\u201d快速物流配送服务。\r\n凭借优质的产品、贴心的服务与开创性的主动式网络营销模式，短短五年里，伊的家公司不仅赢得了客户的信赖与支持，并在全国拥有百余家代理商，成功实现产品销售业绩以每月20％的速度递增。","size":"20-99人","industry":"互联网","avgcomment":4}}
     */

    private String message;
    private int code;
    /**
     * interview : [{"id":55,"uid":10002,"anony":1,"score":"4.0","jobmatch":"4.0","result":"接到offer并入职( ^_^ )","oneword":"不错","descp":"做了面试题，还有简单的面谈，创业气氛很浓。","created":1416902201,"pid":3,"username":"倪盛盛","avatar":"http://test2015.zcspin.comhttp://test2015.zcspin.com/thumb/1/4/5e/3327_big.jpg"},{"id":56,"uid":10002,"anony":2,"score":"4.0","jobmatch":"4.0","result":"未接到offer ⊙︿⊙","oneword":"无","descp":"抱着试一试的态度去的，可惜，没录上","created":1421111217},{"id":57,"uid":10002,"anony":2,"score":"4.0","jobmatch":"4.0","result":"接到offer并入职( ^_^ )","oneword":"自信是一切的必须","descp":"因为只是短期的销售人员，所以就进行了简单的询问与个性测试","created":1422429729}]
     * company : {"cid":3672,"company":"天津博睿丰网络科技有限公司","logo":"http://test2015.zcspin.com/thumb/3/3/b3/1527_big.jpg","cstatus":3,"address":"天津和平海光寺","prov":"天津","city":"和平区","financle":"0","website":"http://www.yidejia.com","content":"经传集团简介：\r\n经传集团成立于2009年，集团总部设立在中国第三大城市\u2014\u2014广州市番禺区。目前投资包括：金融软件产品项目、化妆品生产研发及销售、企业管理教育培训产品研发等项目！\r\n现集团旗下共三大以公司化形式独立运营的事业部。分别为：软件事业部即广州经传信息科技有限公司，电商事业部即广州伊的家网络科技有限公司，教育事业部即广州伊智教育科技有限公司。其中软件事业部和电商事业部在过去的几年中取得非凡的成就，教育事业部在今年九月份正式启动。\r\n软件事业部简介：\r\n软件事业部于2009年9月以1000万注册资金在广州市工商局注册成立广州经传信息科技有限公司，并开始在全国范围内从事国内证券软件技术开发、服务。同时，公司也是珠三角地区首家获得上海证券交易所和深圳证券交易所行情数据授权的信息发布商。\r\n公司凭借强大的技术研发中心、依托具有十余年的证券行业经验。与敏锐的市场洞察力的核心团队，建立科学有效的数学模型，并对大量丰富的信息与数据进行挖掘，为投资者提供了一套数据丰富、操作简单、功能精专，集价值、资金、趋势为一体的证券辅助决策软件。\r\n电商事业部简介：\r\n电商事业部于2009年4月以100万注册资金在广州市工商局注册成立广州伊的家网络科技有限公司。伊的家是一家集研发、生产、设计、销售于一体的电商企业。旗下拥有两大品牌：妍诗美（肌肤补水化妆品品牌）、妍膳美（美容营养食品品牌）\r\n    2011年5月伊的家启动全国配送中心，首批仓储配送中心已覆盖广州、海口、南昌、郑州、成都、合肥、哈尔滨、天津等9个城市，实现了全民体验\u201cT+1\u201d快速物流配送服务。\r\n凭借优质的产品、贴心的服务与开创性的主动式网络营销模式，短短五年里，伊的家公司不仅赢得了客户的信赖与支持，并在全国拥有百余家代理商，成功实现产品销售业绩以每月20％的速度递增。","size":"20-99人","industry":"互联网","avgcomment":4}
     */

    private DataEntity data;


    public void setMessage(String message) { this.message = message;}


    public void setCode(int code) { this.code = code;}


    public void setData(DataEntity data) { this.data = data;}


    public String getMessage() { return message;}


    public int getCode() { return code;}


    public DataEntity getData() { return data;}


    public static class DataEntity {
        /**
         * cid : 3672
         * company : 天津博睿丰网络科技有限公司
         * logo : http://test2015.zcspin.com/thumb/3/3/b3/1527_big.jpg
         * cstatus : 3
         * address : 天津和平海光寺
         * prov : 天津
         * city : 和平区
         * financle : 0
         * website : http://www.yidejia.com
         * content : 经传集团简介：
         经传集团成立于2009年，集团总部设立在中国第三大城市——广州市番禺区。目前投资包括：金融软件产品项目、化妆品生产研发及销售、企业管理教育培训产品研发等项目！
         现集团旗下共三大以公司化形式独立运营的事业部。分别为：软件事业部即广州经传信息科技有限公司，电商事业部即广州伊的家网络科技有限公司，教育事业部即广州伊智教育科技有限公司。其中软件事业部和电商事业部在过去的几年中取得非凡的成就，教育事业部在今年九月份正式启动。
         软件事业部简介：
         软件事业部于2009年9月以1000万注册资金在广州市工商局注册成立广州经传信息科技有限公司，并开始在全国范围内从事国内证券软件技术开发、服务。同时，公司也是珠三角地区首家获得上海证券交易所和深圳证券交易所行情数据授权的信息发布商。
         公司凭借强大的技术研发中心、依托具有十余年的证券行业经验。与敏锐的市场洞察力的核心团队，建立科学有效的数学模型，并对大量丰富的信息与数据进行挖掘，为投资者提供了一套数据丰富、操作简单、功能精专，集价值、资金、趋势为一体的证券辅助决策软件。
         电商事业部简介：
         电商事业部于2009年4月以100万注册资金在广州市工商局注册成立广州伊的家网络科技有限公司。伊的家是一家集研发、生产、设计、销售于一体的电商企业。旗下拥有两大品牌：妍诗美（肌肤补水化妆品品牌）、妍膳美（美容营养食品品牌）
         2011年5月伊的家启动全国配送中心，首批仓储配送中心已覆盖广州、海口、南昌、郑州、成都、合肥、哈尔滨、天津等9个城市，实现了全民体验“T+1”快速物流配送服务。
         凭借优质的产品、贴心的服务与开创性的主动式网络营销模式，短短五年里，伊的家公司不仅赢得了客户的信赖与支持，并在全国拥有百余家代理商，成功实现产品销售业绩以每月20％的速度递增。
         * size : 20-99人
         * industry : 互联网
         * avgcomment : 4
         */

        private CompanyEntity company;
        /**
         * id : 55
         * uid : 10002
         * anony : 1
         * score : 4.0
         * jobmatch : 4.0
         * result : 接到offer并入职( ^_^ )
         * oneword : 不错
         * descp : 做了面试题，还有简单的面谈，创业气氛很浓。
         * created : 1416902201
         * pid : 3
         * username : 倪盛盛
         * avatar : http://test2015.zcspin.comhttp://test2015.zcspin.com/thumb/1/4/5e/3327_big.jpg
         */

        private List<InterviewEntity> interview;


        public void setCompany(CompanyEntity company) { this.company = company;}


        public void setInterview(List<InterviewEntity> interview) {
            this.interview = interview;
        }


        public CompanyEntity getCompany() { return company;}


        public List<InterviewEntity> getInterview() { return interview;}


        public static class CompanyEntity {
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
            private int avgcomment;


            public void setCid(int cid) { this.cid = cid;}


            public void setCompany(String company) { this.company = company;}


            public void setLogo(String logo) { this.logo = logo;}


            public void setCstatus(int cstatus) { this.cstatus = cstatus;}


            public void setAddress(String address) { this.address = address;}


            public void setProv(String prov) { this.prov = prov;}


            public void setCity(String city) { this.city = city;}


            public void setFinancle(String financle) {
                this.financle = financle;
            }


            public void setWebsite(String website) { this.website = website;}


            public void setContent(String content) { this.content = content;}


            public void setSize(String size) { this.size = size;}


            public void setIndustry(String industry) {
                this.industry = industry;
            }


            public void setAvgcomment(int avgcomment) {
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


            public int getAvgcomment() { return avgcomment;}
        }

        public static class InterviewEntity {
            private int id;
            private int uid;
            private int anony;
            private String score;
            private String jobmatch;
            private String result;
            private String oneword;
            private String descp;
            private int created;
            private int pid;
            private String username;
            private String avatar;


            public void setId(int id) { this.id = id;}


            public void setUid(int uid) { this.uid = uid;}


            public void setAnony(int anony) { this.anony = anony;}


            public void setScore(String score) { this.score = score;}


            public void setJobmatch(String jobmatch) {
                this.jobmatch = jobmatch;
            }


            public void setResult(String result) { this.result = result;}


            public void setOneword(String oneword) { this.oneword = oneword;}


            public void setDescp(String descp) { this.descp = descp;}


            public void setCreated(int created) { this.created = created;}


            public void setPid(int pid) { this.pid = pid;}


            public void setUsername(String username) {
                this.username = username;
            }


            public void setAvatar(String avatar) { this.avatar = avatar;}


            public int getId() { return id;}


            public int getUid() { return uid;}


            public int getAnony() { return anony;}


            public String getScore() { return score;}


            public String getJobmatch() { return jobmatch;}


            public String getResult() { return result;}


            public String getOneword() { return oneword;}


            public String getDescp() { return descp;}


            public int getCreated() { return created;}


            public int getPid() { return pid;}


            public String getUsername() { return username;}


            public String getAvatar() { return avatar;}
        }
    }
}
