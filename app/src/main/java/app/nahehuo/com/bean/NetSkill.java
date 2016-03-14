package app.nahehuo.com.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/3/9.
 */
public class NetSkill {

    /**
     * message : 技能字典获取成功
     * code : 200
     * data : [{"id":1,"name":"办公室应用类","level":1,"upid":0,"often":0},{"id":2,"name":"程序设计类","level":1,"upid":0,"often":0},{"id":3,"name":"数据库类","level":1,"upid":0,"often":0},{"id":4,"name":"网页技术类","level":1,"upid":0,"often":0},{"id":5,"name":"操作系统类","level":1,"upid":0,"often":0},{"id":6,"name":"排版软件类","level":1,"upid":0,"often":0},{"id":7,"name":"企业管理类","level":1,"upid":0,"often":0},{"id":8,"name":"多媒体设计类","level":1,"upid":0,"often":0},{"id":9,"name":"图像处理类","level":1,"upid":0,"often":0},{"id":10,"name":"工程制图类","level":1,"upid":0,"often":0},{"id":11,"name":"硬件设计类","level":1,"upid":0,"often":0},{"id":12,"name":"Word","level":2,"upid":1,"often":1},{"id":13,"name":"Excel","level":2,"upid":1,"often":1},{"id":14,"name":"PowerPoint","level":2,"upid":1,"often":1},{"id":15,"name":"Visio","level":2,"upid":1,"often":2},{"id":16,"name":"outlook","level":2,"upid":1,"often":2},{"id":17,"name":"ASP","level":2,"upid":2,"often":2},{"id":18,"name":".NET","level":2,"upid":2,"often":2},{"id":19,"name":"C#","level":2,"upid":2,"often":1},{"id":20,"name":"C","level":2,"upid":2,"often":1},{"id":21,"name":"C++","level":2,"upid":2,"often":1},{"id":22,"name":"Java","level":2,"upid":2,"often":1},{"id":23,"name":"JSP","level":2,"upid":2,"often":1},{"id":24,"name":"PHP","level":2,"upid":2,"often":1},{"id":25,"name":"VB","level":2,"upid":2,"often":1},{"id":26,"name":"VC++","level":2,"upid":2,"often":1},{"id":27,"name":"AJAX","level":2,"upid":2,"often":2},{"id":28,"name":"delphi","level":2,"upid":2,"often":2},{"id":29,"name":"Matlab","level":2,"upid":2,"often":2},{"id":30,"name":"python","level":2,"upid":2,"often":2},{"id":31,"name":"perl","level":2,"upid":2,"often":2},{"id":32,"name":"spring","level":2,"upid":2,"often":2},{"id":33,"name":"SQL Server","level":2,"upid":3,"often":1},{"id":34,"name":"Access","level":2,"upid":3,"often":1},{"id":35,"name":"MySQL","level":2,"upid":3,"often":1},{"id":36,"name":"Oracle","level":2,"upid":3,"often":1},{"id":37,"name":"SPSS","level":2,"upid":3,"often":1},{"id":38,"name":"CSS","level":2,"upid":4,"often":2},{"id":39,"name":"Dreamweaver","level":2,"upid":4,"often":1},{"id":40,"name":"Fireworks","level":2,"upid":4,"often":2},{"id":41,"name":"HTML","level":2,"upid":4,"often":1},{"id":42,"name":"JavaScript","level":2,"upid":4,"often":1},{"id":43,"name":"VBScript","level":2,"upid":4,"often":2},{"id":44,"name":"XML","level":2,"upid":4,"often":2},{"id":45,"name":"Linux","level":2,"upid":5,"often":1},{"id":46,"name":"WINDOWS","level":2,"upid":5,"often":1},{"id":47,"name":"Unix","level":2,"upid":5,"often":2},{"id":48,"name":"Android","level":2,"upid":5,"often":2},{"id":49,"name":"IOS","level":2,"upid":5,"often":2},{"id":50,"name":"InDesign","level":2,"upid":6,"often":2},{"id":51,"name":"Pagemaker","level":2,"upid":6,"often":2},{"id":52,"name":"方正飞腾","level":2,"upid":6,"often":2},{"id":53,"name":"SAP","level":2,"upid":7,"often":2},{"id":54,"name":"Oracle ERP","level":2,"upid":7,"often":2},{"id":55,"name":"用友","level":2,"upid":7,"often":2},{"id":56,"name":"金蝶","level":2,"upid":7,"often":2},{"id":57,"name":"SAS","level":2,"upid":7,"often":2},{"id":58,"name":"3DMAX","level":2,"upid":8,"often":1},{"id":59,"name":"Authorware","level":2,"upid":8,"often":2},{"id":60,"name":"Flash","level":2,"upid":8,"often":1},{"id":61,"name":"MAYA","level":2,"upid":8,"often":2},{"id":62,"name":"Painter","level":2,"upid":8,"often":2},{"id":63,"name":"Premiere","level":2,"upid":8,"often":1},{"id":64,"name":"Photoshop","level":2,"upid":9,"often":1},{"id":65,"name":"CorelDraw","level":2,"upid":9,"often":1},{"id":66,"name":"Illustrator","level":2,"upid":9,"often":1},{"id":67,"name":"AutoCAD","level":2,"upid":10,"often":1},{"id":68,"name":"Catia","level":2,"upid":10,"often":2},{"id":69,"name":"LabVIEW","level":2,"upid":10,"often":2},{"id":70,"name":"Pro/E","level":2,"upid":10,"often":1},{"id":71,"name":"SolidWorks","level":2,"upid":10,"often":2},{"id":72,"name":"FPGA","level":2,"upid":11,"often":2},{"id":73,"name":"PLC","level":2,"upid":11,"often":2},{"id":74,"name":"Protel","level":2,"upid":11,"often":1},{"id":75,"name":"VHDL","level":2,"upid":11,"often":2}]
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
     * name : 办公室应用类
     * level : 1
     * upid : 0
     * often : 0
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
        private int level;
        private int upid;
        private int often;


        public void setId(int id) { this.id = id;}


        public void setName(String name) { this.name = name;}


        public void setLevel(int level) { this.level = level;}


        public void setUpid(int upid) { this.upid = upid;}


        public void setOften(int often) { this.often = often;}


        public int getId() { return id;}


        public String getName() { return name;}


        public int getLevel() { return level;}


        public int getUpid() { return upid;}


        public int getOften() { return often;}
    }
}
