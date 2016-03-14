package app.nahehuo.com.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/3/9.
 */
public class NetPositionCate {

    /**
     * message : 职位类别字典获取成功
     * code : 200
     * data : [{"id":1,"name":"管理"},{"id":2,"name":"技术研发"},{"id":3,"name":"产品"},{"id":4,"name":"运营"},{"id":5,"name":"设计&创意"},{"id":6,"name":"项目管理"},{"id":7,"name":"质量管理"},{"id":8,"name":"财务"},{"id":10,"name":"审计"},{"id":11,"name":"会计/出纳"},{"id":12,"name":"金融"},{"id":13,"name":"销售"},{"id":14,"name":"商务拓展"},{"id":15,"name":"渠道/分销"},{"id":16,"name":"客户服务"},{"id":17,"name":"市场营销"},{"id":18,"name":"咨询/顾问"},{"id":19,"name":"法律"},{"id":20,"name":"公关"},{"id":21,"name":"编辑/文案"},{"id":23,"name":"人力资源"},{"id":24,"name":"行政"},{"id":25,"name":"教育/培训"},{"id":26,"name":"贸易&进出口"},{"id":543,"name":"采购"},{"id":544,"name":"物流&供应链"},{"id":545,"name":"医疗/健康"},{"id":546,"name":"艺术"},{"id":547,"name":"工程"},{"id":548,"name":"生产&制造"},{"id":549,"name":"物业管理"},{"id":550,"name":"科研"},{"id":551,"name":"翻译"},{"id":552,"name":"公务员"},{"id":553,"name":"技术支持"},{"id":554,"name":"其他"}]
     * other : {"version":1}
     */

    private String message;
    private int code;
    /**
     * version : 1
     */

    private OtherEntity other;
    /**
     * id : 1
     * name : 管理
     */

    private List<DataEntity> data;


    public void setMessage(String message) { this.message = message;}


    public void setCode(int code) { this.code = code;}


    public void setOther(OtherEntity other) { this.other = other;}


    public void setData(List<DataEntity> data) { this.data = data;}


    public String getMessage() { return message;}


    public int getCode() { return code;}


    public OtherEntity getOther() { return other;}


    public List<DataEntity> getData() { return data;}


    public static class OtherEntity {
        private int version;


        public void setVersion(int version) { this.version = version;}


        public int getVersion() { return version;}
    }

    public static class DataEntity {
        private int id;
        private String name;


        public void setId(int id) { this.id = id;}


        public void setName(String name) { this.name = name;}


        public int getId() { return id;}


        public String getName() { return name;}
    }
}
