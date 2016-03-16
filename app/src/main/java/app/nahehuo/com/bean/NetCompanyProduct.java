package app.nahehuo.com.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/3/16.
 */
public class NetCompanyProduct {

    /**
     * message : 企业产品获取成功
     * code : 200
     * data : [{"id":52,"name":"真才实聘","desp":"真才实聘是一个专注于人力资源配制的职场型社交网站，包括招聘求职、创业、社交、教育、圈子等板块，旨在打造一个信息源真实可靠、信息量大而全、内容新颖独特的人力资源数据库，为用户免费提供展示形象及信息发布、查找、筛选、评论等服务。\n简洁、方便、实用是我们一直追求的目标，让企业以更快捷更精准的方式定位到所需要的求职者，让求职者更明确更方便地地找到更放心企业。同时，也将是企业在高校市场展示形象的最佳推广窗口。","pic":{"thumb":"http://test2015.zcspin.com/thumb/8/1/ee/3093_big.jpg","file":"http://test2015.zcspin.com/files/7/a/96/117c3695331941c022a37d783c7b4bf40f1f96a7.png"}}]
     */

    private String message;
    private int code;
    /**
     * id : 52
     * name : 真才实聘
     * desp : 真才实聘是一个专注于人力资源配制的职场型社交网站，包括招聘求职、创业、社交、教育、圈子等板块，旨在打造一个信息源真实可靠、信息量大而全、内容新颖独特的人力资源数据库，为用户免费提供展示形象及信息发布、查找、筛选、评论等服务。
     简洁、方便、实用是我们一直追求的目标，让企业以更快捷更精准的方式定位到所需要的求职者，让求职者更明确更方便地地找到更放心企业。同时，也将是企业在高校市场展示形象的最佳推广窗口。
     * pic : {"thumb":"http://test2015.zcspin.com/thumb/8/1/ee/3093_big.jpg","file":"http://test2015.zcspin.com/files/7/a/96/117c3695331941c022a37d783c7b4bf40f1f96a7.png"}
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
        private String desp;
        /**
         * thumb : http://test2015.zcspin.com/thumb/8/1/ee/3093_big.jpg
         * file : http://test2015.zcspin.com/files/7/a/96/117c3695331941c022a37d783c7b4bf40f1f96a7.png
         */

        private PicEntity pic;


        public void setId(int id) { this.id = id;}


        public void setName(String name) { this.name = name;}


        public void setDesp(String desp) { this.desp = desp;}


        public void setPic(PicEntity pic) { this.pic = pic;}


        public int getId() { return id;}


        public String getName() { return name;}


        public String getDesp() { return desp;}


        public PicEntity getPic() { return pic;}


        public static class PicEntity {
            private String thumb;
            private String file;


            public void setThumb(String thumb) { this.thumb = thumb;}


            public void setFile(String file) { this.file = file;}


            public String getThumb() { return thumb;}


            public String getFile() { return file;}
        }
    }
}
