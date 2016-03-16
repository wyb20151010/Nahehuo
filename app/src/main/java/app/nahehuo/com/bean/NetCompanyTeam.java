package app.nahehuo.com.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/3/16.
 */
public class NetCompanyTeam {

    /**
     * message : 企业团队获取成功
     * code : 200
     * data : [{"id":192,"name":"李正茂","position":"CEO","desp":"化学专业硕士、管理学博士、博士后，美国访问学者。\n2006年创立上海今翌文化传播有限公司，创办《大学明天》杂志和大学明天网。2008年创立上海思博体育管理有限公司和思博户外拓展培训部。2010年创立今翌信息科技（上海）有限公司，曾创办千寻美网、大工厂网和e速代购网，2014年12月推出唯动力网，2015年3月改名为真才实聘。","pic":"http://test2015.zcspin.com/thumb/3/8/7f/2904_big.jpg"}]
     */

    private String message;
    private int code;
    /**
     * id : 192
     * name : 李正茂
     * position : CEO
     * desp : 化学专业硕士、管理学博士、博士后，美国访问学者。
     2006年创立上海今翌文化传播有限公司，创办《大学明天》杂志和大学明天网。2008年创立上海思博体育管理有限公司和思博户外拓展培训部。2010年创立今翌信息科技（上海）有限公司，曾创办千寻美网、大工厂网和e速代购网，2014年12月推出唯动力网，2015年3月改名为真才实聘。
     * pic : http://test2015.zcspin.com/thumb/3/8/7f/2904_big.jpg
     */

    private List<DataEntity> data;


    public void setMessage(String message) { this.message = message;}


    public void setCode(int code) { this.code = code;}


    public void setData(List<DataEntity> data) { this.data = data;}


    public String getMessage() { return message;}


    public int getCode() { return code;}


    public List<DataEntity> getData() { return data;}


    public static class DataEntity {
        private int id;
        private String name;
        private String position;
        private String desp;
        private String pic;


        public void setId(int id) { this.id = id;}


        public void setName(String name) { this.name = name;}


        public void setPosition(String position) { this.position = position;}


        public void setDesp(String desp) { this.desp = desp;}


        public void setPic(String pic) { this.pic = pic;}


        public int getId() { return id;}


        public String getName() { return name;}


        public String getPosition() { return position;}


        public String getDesp() { return desp;}


        public String getPic() { return pic;}
    }
}
