package app.nahehuo.com.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/3/9.
 */
public class NetCert {

    /**
     * message : 技能证书字典获取成功
     * code : 200
     * data : [{"id":1,"name":"外语等级证书","level":1,"upid":0},{"id":2,"name":"计算机等级证书","level":1,"upid":0},{"id":3,"name":"计算机技术与软件专业技术证书","level":1,"upid":0},{"id":4,"name":"其他","level":1,"upid":0},{"id":5,"name":"Adobe认证证书","level":1,"upid":0},{"id":6,"name":"微软认证","level":1,"upid":0},{"id":7,"name":"思科认证","level":1,"upid":0},{"id":8,"name":"人力资源管理师","level":1,"upid":0},{"id":9,"name":"医疗证书","level":1,"upid":0},{"id":10,"name":"外语口译资格证","level":1,"upid":0},{"id":11,"name":"日语证书","level":1,"upid":0},{"id":12,"name":"韩语证书","level":1,"upid":0},{"id":13,"name":"大学英语四级","level":2,"upid":1},{"id":14,"name":"大学英语六级","level":2,"upid":1},{"id":15,"name":"专业英语四级","level":2,"upid":1},{"id":16,"name":"专业英语八级","level":2,"upid":1},{"id":17,"name":"计算机一级证","level":2,"upid":2},{"id":18,"name":"计算机二级证","level":2,"upid":2},{"id":19,"name":"计算机三级证","level":2,"upid":2},{"id":20,"name":"计算机四级证","level":2,"upid":2},{"id":21,"name":"软件设计师","level":2,"upid":3},{"id":22,"name":"网络工程师","level":2,"upid":3},{"id":23,"name":"程序员","level":2,"upid":3},{"id":24,"name":"网络管理员","level":2,"upid":3},{"id":25,"name":"普通话等级证书","level":2,"upid":4},{"id":26,"name":"会计从业资格证","level":2,"upid":4},{"id":27,"name":"助理会计师证","level":2,"upid":4},{"id":28,"name":"注册会计师证","level":2,"upid":4},{"id":29,"name":"驾驶证","level":2,"upid":4},{"id":30,"name":"证券从业资格证","level":2,"upid":4},{"id":31,"name":"期货从业资格证","level":2,"upid":4},{"id":32,"name":"法律职业资格证","level":2,"upid":4},{"id":33,"name":"教师资格证","level":2,"upid":4},{"id":34,"name":"导游证","level":2,"upid":4},{"id":35,"name":"ACCD","level":2,"upid":5},{"id":36,"name":"ACPE","level":2,"upid":5},{"id":37,"name":"MCP","level":2,"upid":6},{"id":38,"name":"MCSE","level":2,"upid":6},{"id":39,"name":"MCAD","level":2,"upid":6},{"id":40,"name":"MCDBA","level":2,"upid":6},{"id":41,"name":"CCNA","level":2,"upid":7},{"id":42,"name":"CCNP","level":2,"upid":7},{"id":43,"name":"助理人力资源管理师证","level":2,"upid":8},{"id":44,"name":"护士执业证","level":2,"upid":9},{"id":45,"name":"执业助理医师证","level":2,"upid":9},{"id":46,"name":"中级口译证书","level":2,"upid":10},{"id":47,"name":"高级口译证书","level":2,"upid":10},{"id":48,"name":"日语一级证书JLPT-1","level":2,"upid":11},{"id":49,"name":"日语二级证书JLPT-2","level":2,"upid":11},{"id":50,"name":"日语三级证书JLPT-3","level":2,"upid":11},{"id":51,"name":"日语四级证书JLPT-4","level":2,"upid":11},{"id":52,"name":"韩语初级证书","level":2,"upid":12},{"id":53,"name":"韩语中级证书","level":2,"upid":12},{"id":54,"name":"韩语高级证书","level":2,"upid":12}]
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
     * name : 外语等级证书
     * level : 1
     * upid : 0
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


        public void setId(int id) { this.id = id;}


        public void setName(String name) { this.name = name;}


        public void setLevel(int level) { this.level = level;}


        public void setUpid(int upid) { this.upid = upid;}


        public int getId() { return id;}


        public String getName() { return name;}


        public int getLevel() { return level;}


        public int getUpid() { return upid;}
    }
}
