package app.nahehuo.com.base;

public class GlobalVariables {

    public static String UID = "";
    public static String access_token = "";
    public static String fake_access_token = "";
    public static final String device = "app";
    public static final String pagesize = "15";

    //real api
    /* public static final String IP_ADDRESS = "https://api2016.nahehuo.com/";*/

    //test api
    public static final String IP_ADDRESS
            = "http://testapi2016.zcspin.com:808/";

    //共通接口-----------------------------------------------------------------------------------------
    // 检查共通接口是否需要更新
    public static final String URL_CHECK_DICTVERSION = IP_ADDRESS +
            "api/dict/check_version";
    // 获取城市列表
    public static final String URL_GET_DICTPROVINCECITY = IP_ADDRESS +
            "api/dict/area";
    // 获取行业字典
    public static final String URL_GET_DICTINDUSTRY = IP_ADDRESS +
            "api/dict/industry";
    //获取热门城市字典
    public static final String URL_GET_DICTHOTCITY = IP_ADDRESS +
            "api/dict/hot_city";
    // 获取圈子类型字典
    public static final String URL_GET_CIRCLETYPE = IP_ADDRESS +
            "api/dict/circle_type";
    // 获取银行字典
    public static final String URL_GET_DICTBANK = IP_ADDRESS + "api/dict/bank";
    // 获取活动主题字典
    public static final String URL_GET_DICTEVENTTHEME = IP_ADDRESS +
            "api/dict/event_theme";
    // 获取活动形式字典
    public static final String URL_GET_DICTEVENTSTYLE = IP_ADDRESS +
            "api/dict/event_style";
    // 获取项目类型字典
    public static final String URL_GET_DICTPROJECTTYPE = IP_ADDRESS +
            "api/dict/project_plan";
    // 获取项目计划字典
    public static final String URL_GET_DICTPROJECTPLAN = IP_ADDRESS +
            "api/dict/project_type";
    // 获取工作年限字典
    public static final String URL_GET_DICTWORKYEAR = IP_ADDRESS +
            "api/dict/work_year";
    // 获取语言字典
    public static final String URL_GET_DICTLANG = IP_ADDRESS +
            "api/dict/language";
    // 获取职位类别字典
    public static final String URL_GET_DICTPOSITIONCATE = IP_ADDRESS +
            "api/dict/position_cate";
    // 获取学历字典
    public static final String URL_GET_DICTEDULEVEL = IP_ADDRESS +
            "api/dict/education";
    //获取学位字典
    public static final String URL_GET_DICTEDUDEGREE = IP_ADDRESS +
            "api/dict/degree";
    // 获取职位级别字典
    public static final String URL_GET_DICTPOSITIONLEVEL = IP_ADDRESS +
            "api/dict/position_level";
    // 获取薪水字典
    public static final String URL_GET_DICTSALARY = IP_ADDRESS +
            "api/dict/salary";
    // 获取个人关系字典
    public static final String URL_GET_DICTUSERRELATION = IP_ADDRESS +
            "api/dict/user_relation";
    // 获取语言级别字典
    public static final String URL_GET_DICTLANGLEVEL = IP_ADDRESS +
            "api/dict/language_level";
    // 获取技能字典
    public static final String URL_GET_DICTSKILL = IP_ADDRESS +
            "api/dict/skill";
    // 获取技能证书字典
    public static final String URL_GET_DICTCERT = IP_ADDRESS + "api/dict/cert";
    // 求职意向
    public static final String URL_GET_DICTINTENTION = IP_ADDRESS +
            "api/dict/intention";

    //共通接口-----------------------------------------------------------------------------------------

    // common（公共服务API组-----------------------------------------------------
    // 获取手机验证码
    public static final String GETPHONECODE = IP_ADDRESS +
            "api/common/send_phonecode";
    //检测字典版本更新数
    public static final String SEARCHAREA = IP_ADDRESS +
            "api/common/search_area";
    //检测手机是否存在或已被使用
    public static final String CHECKPHONEEXIT = IP_ADDRESS +
            "api/user/check_phone";

    // 验证码是否正确
    public static final String CHECKPHONECODE = IP_ADDRESS +
            "api/user/check_phonecode";
    // 注册
    public static final String REGISTER_POST = IP_ADDRESS + "api/user/register";

    // 用户注册完善资料接口
    public static final String REGISTER_PERFECTINFO = IP_ADDRESS +
            "api/user/register_perfectinfo";
    // 修改头像
    public static final String AVATER = IP_ADDRESS + "api/person/avatar";

    // 忘记密码 手机找回
    public static final String PHONE_RESETPASSWORD = IP_ADDRESS +
            "api/user/forgot_password";
    // 忘记密码,邮箱找回
    public static final String EMAIL_RESETPASSWORD = IP_ADDRESS +
            "api/user/forgot_password_email";
    // 登录
    public static final String LOGIN_GET = IP_ADDRESS + "api/user/login";

    //job（职位API组）----------------------------------------------------
    // 职位订阅
    public static final String JOB_SUBSCRIPTION = IP_ADDRESS +
            "api/job/subscription";
    // 职位收藏
    public static final String JOB_COLLECT = IP_ADDRESS +
            "api/job/collect_create";
    // 推荐的职位
    public static final String JOB_RECOMMEND = IP_ADDRESS + "api/job/recommend";
    // 职位列表
    public static final String JOB_LIST = IP_ADDRESS + "api/job/list";
    // 职位搜索
    public static final String JOB_SEARCH = IP_ADDRESS + "api/job/search";
    // 职位详情
    public static final String JOB_DETAIL = IP_ADDRESS + "api/job/show";
    // 职位投递申请
    public static final String JOB_APPLY = IP_ADDRESS + "api/job/apply";
    // 面试经验列表
    public static final String JOB_INTERVIEW = IP_ADDRESS + "api/job/interview";
    // 面经发布
    public static final String JOB_INTERVIEW_CREATE = IP_ADDRESS +
            "api/job/interview/create";
    //job（职位API组）----------------------------------------------------

    //company（企业API组）-------------------------------------------------
    // 推荐企业
    public static final String RECOMMEND_COMPANY = IP_ADDRESS +
            "api/company/recommend";
    // 企业列表
    public static final String COMPANY_LIST = IP_ADDRESS + "api/company/list";
    // 企业搜索
    public static final String COMPANY_SEARCH = IP_ADDRESS +
            "api/company/search";
    //企业详情
    public static final String COMPANY_DETAIL = IP_ADDRESS + "api/company/show";
    //企业标签
    public static final String COMPANY_TAG = IP_ADDRESS + "api/company/tag";
    //企业产品
    public static final String COMPANY_PRODUCT = IP_ADDRESS +
            "api/company/product";
    //企业team
    public static final String COMPANY_TEAM = IP_ADDRESS + "api/company/team";
    //企业职位列表
    public static final String COMPANY_JOB_LIST = IP_ADDRESS +
            "api/company/job";
    //企业历程
    public static final String COMPANY_HISTORY = IP_ADDRESS +
            "api/company/history";
    //企业评价
    public static final String COMPANY_COMMENT = IP_ADDRESS +
            "api/company/comment";
    //企业印象
    public static final String COMPANY_IMPRESSION = IP_ADDRESS +
            "api/company/impression";
    //企业印象列表
    public static final String COMPANY_IMPRESSION_LIST = IP_ADDRESS +
            "api/company/impression_list";
    //添加企业点评
    public static final String COMPANY_CREATE = IP_ADDRESS +
            "api/company/comment_create";
    //企业收藏
    public static final String COMPANY_COLLECT_CREATE = IP_ADDRESS +
            "api/company/collect_create";
    //company（企业API组）-------------------------------------------------

    //order（订单API组）----------------------------------------------------
    //创建订单
    public static final String ORDER_CREATE = IP_ADDRESS + "api/order/create";
    //order（订单API组）----------------------------------------------------

    //authpay（支付API组）--------------------------------------------------
    //APP端支付宝支付
    public static final String ALIPAY = IP_ADDRESS + "authpay/app/alipay";
    //APP端微信支付
    public static final String WYPAY = IP_ADDRESS + "authpay/app/wxpay";
    // 钱包支付
    public static final String WALLET_PAY = IP_ADDRESS + "authpay/wallet/pay";
    //authpay（支付API组）----------------------------------------------------

    //event（活动API组）------------------------------------------------------
    //活动列表
    public static final String EVENT_LIST = IP_ADDRESS + "api/event/list";
    //活动搜索
    public static final String EVENT_SEARCH = IP_ADDRESS + "api/event/search";
    //评论列表
    public static final String EVENT_COMMENT = IP_ADDRESS +
            "api/event/comment/list";
    //活动详情
    public static final String EVENT_DETAIL = IP_ADDRESS + "api/event/show";
    //照片列表
    public static final String EVENT_PHOTO_LIST = IP_ADDRESS +
            "api/event/photo/list";
    //活动成员列表
    public static final String EVENT_MEMBER_LIST = IP_ADDRESS +
            "api/event/member";
    //表单验证
    public static final String EVENT_CHECK = IP_ADDRESS + "api/event/check";
    //活动发布/编辑
    public static final String EVENT_UPDATE = IP_ADDRESS + "api/event/update";
    //活动报名支付列表
    public static final String EVENT_PAY = IP_ADDRESS + "api/event/pay";
    //活动发布评论
    public static final String EVENT_COMMENT_CREATE = IP_ADDRESS +
            "api/event/comment/create";
    //event（活动API组）------------------------------------------------------

    //circle（圈子API组）-----------------------------------------------------
    //话题列表
    public static final String TOPIC_LIST = IP_ADDRESS +
            "api/circle/topic/list";
    //circle（圈子API组）-----------------------------------------------------

    //project（项目API组)------------------------------------------------------

    //本周合伙人推荐
    public static final String PROJECT_RECOMMEND = IP_ADDRESS +
            "api/project/recommend";
    //项目列表
    public static final String PROJECT_LIST = IP_ADDRESS + "api/project/list";
    //项目搜索
    public static final String PROJECT_SEARCH = IP_ADDRESS +
            "api/project/search";
    //项目详情
    public static final String PROJECT_SHOW = IP_ADDRESS + "api/project/show";
    //项目关注列表
    public static final String PROJECT_ATTENTION = IP_ADDRESS +
            "api/project/attention/list";
    //项目赞列表
    public static final String PROJECT_LAUD_LIST = IP_ADDRESS +
            "api/project/laud/list";
    //项目赞操作
    public static final String PROJECT_LAUD_UPDATE = IP_ADDRESS +
            "api/project/updatelaud";
    //关注项目
    public static final String PROJECT_COLLECT = IP_ADDRESS +
            "api/project/collect";
    //项目约谈
    public static final String PROJECT_INTERVIEW = IP_ADDRESS +
            "api/project/interview";
    //项目发布/编辑
    public static final String PROJECT_UPDATE = IP_ADDRESS +
            "api/project/update";
    //大家都在搜
    public static final String PROJECT_SEARCH_LIST = IP_ADDRESS +
            "api/project/search/list";
    //project（项目API组)------------------------------------------------------

    //person（档案API组）------------------------------------------------------
    //基本信息
    public static final String PERSON_USER_INFO = IP_ADDRESS +
            "api/person/user/info";
    //添加标签
    public static final String PERSON_ADD_TAG = IP_ADDRESS +
            "api/person/tag/add";
    //删除标签
    public static final String PERSON_DEL_TAG = IP_ADDRESS +
            "api/notice/friend/del";
    //基本资料
    public static final String PERSON_BASIC_INFO = IP_ADDRESS +
            "api/person/basicinfo";
    //编辑基本信息
    public static final String PERSON_UPDATE_BASIC_INFO = IP_ADDRESS +
            "api/person/update";
    //检查手机号是否可用
    public static final String PERSON_CHECK_PHONE = IP_ADDRESS +
            "api/person/check_phone";
    //绑定手机
    public static final String PERSON_UPDATE_PHONE = IP_ADDRESS +
            "api/person/update_phone";
    //工作经历列表
    public static final String PERSON_WORK = IP_ADDRESS +
            "api/person/work";
    //工作经历详情
    public static final String PERSON_WORK_SHOW = IP_ADDRESS +
            "api/person/work/show";
    //删除工作经历
    public static final String PERSON_WORK_DELETE = IP_ADDRESS +
            "api/person/work/delete";
    //person（档案API组）------------------------------------------------------
}
