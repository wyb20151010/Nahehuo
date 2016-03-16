package app.nahehuo.com.base;

public class GlobalVariables {

    public static String UID = "";
    public static String PHONE = "";
    public static String USERNAME = "";
    public static String EMAIL = "";
    public static String COMPANY = "";
    public static String POSITION = "";
    public static String AVATAR = "";
    public static String LOGINNAME = "";
    public static String access_token = "";
    public static String fake_access_token = "";
    public static final String device = "app";
    public static boolean hasNews;
    public static String last_jid = "";
    public static String togbtn_interview = "";
    public static String togbtn_cv_improper = "";
    public static String togbtn_help_reply = "";
    public static String togbtn_system_notif = "";
    public static String togbtn_connection_notify = "";

    public static String mobile_privacy_state = "";
    public static String email_privacy_state = "";

    public static final int STYPEID_HELP = 1;
    public static final int STYPEID_SKILL = 2;

    public static final int THEME_HOLO_DARK = 1; // 对话框的风格
    public static int messageNum = 0;
    public static String pay_what = null;
    public static final String pagesize = "15";

    //real api
    //public static final String IP_ADDRESS = "https://api2016.nahehuo.com/";

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
    public static final String COMPANY_LIST = IP_ADDRESS +
            "api/company/list";
    // 企业搜索
    public static final String COMPANY_SEARCH = IP_ADDRESS +
            "api/company/search";
    //企业详情
    public static final String COMPANY_DETAIL = IP_ADDRESS +
            "api/company/show";
    //企业标签
    public static final String COMPANY_TAG = IP_ADDRESS +
            "api/company/tag";
    //企业产品
    public static final String COMPANY_PRODUCT= IP_ADDRESS +
            "api/company/product";
    //企业team
    public static final String COMPANY_TEAM= IP_ADDRESS +
            "api/company/team";
    //企业职位列表
    public static final String COMPANY_JOB_LIST= IP_ADDRESS +
            "api/company/job";
    //企业历程
    public static final String COMPANY_HISTORY= IP_ADDRESS +
            "api/company/history";
    //企业评价
    public static final String COMPANY_COMMENT= IP_ADDRESS +
            "api/company/comment";
    //企业印象
    public static final String COMPANY_IMPRESSION= IP_ADDRESS +
            "api/company/impression";
    //企业印象列表
    public static final String COMPANY_IMPRESSION_LIST= IP_ADDRESS +
            "api/company/impression_list";
    //添加企业点评
    public static final String COMPANY_CREATE= IP_ADDRESS +
            "api/company/comment_create";
    //企业收藏
    public static final String COMPANY_COLLECT_CREATE= IP_ADDRESS +
            "api/company/collect_create";
    //company（企业API组）-------------------------------------------------

    //order（订单API组）----------------------------------------------------
    //创建订单
    public static final String ORDER_CREATE= IP_ADDRESS +
            "api/order/create";
    //order（订单API组）----------------------------------------------------

    //authpay（支付API组）--------------------------------------------------
    //APP端支付宝支付
    public static final String ALIPAY= IP_ADDRESS +
            "authpay/app/alipay";
    //APP端微信支付
    public static final String WYPAY= IP_ADDRESS +
            "authpay/app/wxpay";
    // 钱包支付
    public static final String WALLET_PAY= IP_ADDRESS +
            "authpay/wallet/pay";
    //authpay（支付API组）----------------------------------------------------


    // 隐私设置
    public static final String PRIVACY_SET_POST = IP_ADDRESS +
            "usersetting?access_token=";
    // 退出登陆
    public static final String USER_LOGOUT_GET = IP_ADDRESS +
            "UserLogout?access_token=";
    // 重设邮箱
    public static final String RESET_EMAIL_GET = IP_ADDRESS +
            "UserResetEmail?access_token=";
    // 求助技能列表
    public static final String HELPSKILL_GET = IP_ADDRESS +
            "getHelpSkill?access_token=";
    // 求助技能标签列表
    public static final String HELPSKILLTAG_GET = IP_ADDRESS +
            "getHelpSkillTag?access_token=";
    // 求助技能标签列表
    public static final String USERHELPSKILL_GET = IP_ADDRESS +
            "getUserHelpSkill?access_token=";
    // 求助详情
    public static final String HELPSKILLDETAIL_GET = IP_ADDRESS +
            "getHelpSkillDetail?access_token=";
    // 查询好友
    public static final String GETFOLLOW_GET = IP_ADDRESS +
            "getPersonFriend?access_token=";
    // 关注某求助
    public static final String ADDHELPSKILLFOLLOW_GET = IP_ADDRESS +
            "addHelpSkillFollow?access_token=";
    // 发布求助
    public static final String ADDHELPSKILL_POST = IP_ADDRESS +
            "addHelpSkill?access_token=";
    // 添加标签
    public static final String ADDHELPSKILLTAG_POST = IP_ADDRESS +
            "addHelpSkillTag?access_token=";
    // 完成邀请评价
    public static final String INVITEUSERFORWORKEDUCOMMENT_POST = IP_ADDRESS +
            "inviteUserForWorkEduComment?access_token=";
    // 邀请评价新
    public static final String INVITEPERSONCOMMENT_POST = IP_ADDRESS +
            "invitePersonComment?access_token=";
    // 回答求助详情
    public static final String ANSWERHELPDETAIL_GET = IP_ADDRESS +
            "answerHelpDetail?access_token=";
    // 查看某求助回答的回复
    public static final String HELPSKILLANSWERREPLY_GET = IP_ADDRESS +
            "helpSkillAnswerReply?access_token=";
    // 查看某求助回答的回复
    public static final String REPLYHELPSKILLANSWER_POST = IP_ADDRESS +
            "replyHelpSkillAnswer?access_token=";
    // 赞某个求助回答
    public static final String LAUDHELPANSWER_POST = IP_ADDRESS +
            "laudHelpAnswer?access_token=";
    // 回答求助（对于技能就是评论）
    public static final String ADDHELPSKILLANSWERCOMMENT_POST = IP_ADDRESS +
            "addHelpSkillAnswerComment?access_token=";
    // 采纳某个求助回答（只有本人发布的才可以)
    public static final String ACCEPTHELPANSWER_POST = IP_ADDRESS +
            "acceptHelpAnswer?access_token=";
    // 邀请回答
    public static final String ADDHELPSKILLINVITE_POST = IP_ADDRESS +
            "addHelpSkillInvite?access_token=";
    // 反馈列表
    public static final String GETUSERFEEDBACK_GET = IP_ADDRESS +
            "getUserFeedBack?access_token=";
    // 提交反馈
    public static final String ADDUSERFEEDBACK_GET = IP_ADDRESS +
            "addUserFeedBack?access_token=";
    // 显示个人简历
    public static final String GETRESUME_GET = IP_ADDRESS +
            "getResume?access_token=";
    // 修改简历信息
    public static final String EDITRESUME_POST = IP_ADDRESS +
            "editResume?access_token=";
    // 修改简历信息
    public static final String SAVEAVATAR_POST = IP_ADDRESS +
            "saveAvatar?access_token=";
    // 显示证书
    public static final String GETCERT_GET = IP_ADDRESS +
            "getCert?access_token=";
    // 编辑个人证书
    public static final String SAVERESUMECERT_POST = IP_ADDRESS +
            "saveResumeCert?access_token=";
    // 添加证书
    public static final String ADDCERT_POST = IP_ADDRESS +
            "addCert?access_token=";
    // 显示IT技能
    public static final String GETITSKILL_GET = IP_ADDRESS +
            "getITSkill?access_token=";
    // 增加IT技能
    public static final String ADDITSKILL_POST = IP_ADDRESS +
            "addITSkill?access_token=";
    // 编辑个人IT技能
    public static final String SAVERESUMEIT_POST = IP_ADDRESS +
            "saveResumeIT?access_token=";
    // 显示语言
    public static final String GETRESUMELANG_GET = IP_ADDRESS +
            "getResumeLang?access_token=";
    // 增加语言
    public static final String SAVERESUMELANG_POST = IP_ADDRESS +
            "saveResumeLang?access_token=";
    // 删除语言
    public static final String DELRESUMELANG_POST = IP_ADDRESS +
            "delResumeLang?access_token=";
    // 显示所有工作经历
    public static final String GETWORKEXP_GET = IP_ADDRESS +
            "getWorkExp?access_token=";
    // 显示工作详情
    public static final String GETWORKEXPDETAIL_GET = IP_ADDRESS +
            "getWorkExpDetail?access_token=";
    // 编辑、增加工作经历
    public static final String SAVEWORKEXP_POST = IP_ADDRESS +
            "saveWorkExp?access_token=";
    // 删除工作经历
    public static final String DELWORKEXP_POST = IP_ADDRESS +
            "delWorkExp?access_token=";
    // 显示所有教育经历
    public static final String GETEDU_GET = IP_ADDRESS + "getEdu?access_token=";
    // 显示教育详情
    public static final String GETEDUDETAIL_GET = IP_ADDRESS +
            "getEduDetail?access_token=";
    // 编辑、增加教育经历
    public static final String SAVEEDU_POST = IP_ADDRESS +
            "saveEdu?access_token=";
    // 删除教育经历
    public static final String DELEDU_POST = IP_ADDRESS +
            "delEdu?access_token=";
    // 通知列表
    public static final String NOTIFYLIST_GET = IP_ADDRESS +
            "notifyList?access_token=";
    // 通知列表
    public static final String NOTIFYTYPE = IP_ADDRESS +
            "notifyType?access_token=";
    // 通知详情列表
    public static final String NOTIFYLISTTYPE = IP_ADDRESS +
            "notifyListType?access_token=";
    // 面试通知详情
    public static final String NOTIFYSEEDETAIL_GET = IP_ADDRESS +
            "notifySeeDetail?access_token=";
    // 面试操作
    public static final String NOTIFYSEEOPERATE_POST = IP_ADDRESS +
            "notifySeeOperate?access_token=";
    // 简历未通过筛选详情
    public static final String NOTIFYACCEPTNODETAIL_GET = IP_ADDRESS +
            "notifyAcceptNoDetail?access_token=";
    // 新标签详情
    public static final String NOTIFYTAGDETAIL_GET = IP_ADDRESS +
            "notifyTagDetail?access_token=";
    // 新标签操作
    public static final String NOTIFYOPERATETAG_POST = IP_ADDRESS +
            "notifyOperateTag?access_token=";
    // 通知单独操作
    public static final String NOTIFYOPERATE_POST = IP_ADDRESS +
            "notifyOperate?access_token=";
    // 通知全部操作
    public static final String NOTIFYOPERATEMORE_POST = IP_ADDRESS +
            "notifyOperateMore?access_token=";
    // 推荐
    public static final String USERINVITEURL = IP_ADDRESS +
            "userInviteUrl?access_token=";
    // 获得人脉列表
    // public static final String USERRELATION = IP_ADDRESS +
    // "userRelation?access_token=";
    // 获得人脉列表(新)
    public static final String GETRELATION = IP_ADDRESS +
            "getRelation?access_token=";
    // 人脉操作
    // public static final String DORELATION = IP_ADDRESS +
    // "doUserRelation?access_token=";
    // 请求加好友
    public static final String REQUESTFRIEND = IP_ADDRESS +
            "requestFriend?access_token=";
    // 通过加好友
    public static final String REQUESTFRIENDPASS = IP_ADDRESS +
            "requestFriendPass?access_token=";
    // 拒绝加好友
    public static final String REQUESTFRIENDREFUSE = IP_ADDRESS +
            "requestFriendRefuse?access_token=";
    //解除好友
    public static final String DELDOUBLEFRIEND = IP_ADDRESS +
            "delDoubleFriend?access_token=";
    // 搜索圈子
    public static final String GETCIRCLELIST = IP_ADDRESS +
            "getCircleList?access_token=";
    // 获得推荐圈子
    public static final String GETRECOMMENDCIRCLE = IP_ADDRESS +
            "getRecommendCircle?access_token=";
    // 获得话题列表
    public static final String GETCIRCLETOPIC = IP_ADDRESS +
            "getCircleTopic?access_token=";
    // 获得话题详情
    public static final String GETCIRCLETOPICDETAIL = IP_ADDRESS +
            "getCircleTopicDetail?access_token=";
    // 赞话题
    public static final String SETCIRCLETOPICLAUD = IP_ADDRESS +
            "setCircleTopicLaud?access_token=";
    // 置顶话题
    public static final String SETCIRCLETOPICTOP = IP_ADDRESS +
            "setCircleTopicTop?access_token=";
    // 收藏话题
    public static final String SETCIRCLETOPICFOLLOW = IP_ADDRESS +
            "setCircleTopicFollow?access_token=";
    // 获得话题评论
    public static final String GETCIRCLETOPICCOMMENT = IP_ADDRESS +
            "getCircleTopicComment?access_token=";
    // 添加话题评论
    public static final String ADDCIRCLETOPICCOMMENT = IP_ADDRESS +
            "addCircleTopicComment?access_token=";
    // 发布话题
    public static final String ADDCIRCLETOPIC = IP_ADDRESS +
            "addCircleTopic?access_token=";
    // 评论话题评论
    public static final String ADDCIRCLETOPICCOMMENTREPLY = IP_ADDRESS +
            "addCircleTopicCommentReply?access_token=";
    // 赞话题的评论
    public static final String SETCIRCLETOPICCOMMENTLAUD = IP_ADDRESS +
            "setCircleTopicCommentLaud?access_token=";
    // 话题投票
    public static final String ADDTOPICVOTE = IP_ADDRESS +
            "addTopicVote?access_token=";
    // 话题辩论
    public static final String ADDTOPICDISCUSS = IP_ADDRESS +
            "addTopicDiscuss?access_token=";
    // 获得收藏的话题
    public static final String GETMYFOLLOWCIRCLETOPIC = IP_ADDRESS +
            "getMyFollowCircleTopic?access_token=";
    // 获得圈子详情 getCircleDetail
    public static final String GETCIRCLEDETAIL = IP_ADDRESS +
            "getCircleDetail?access_token=";
    // 获得圈子成员
    public static final String GETCIRCLEMEMBER = IP_ADDRESS +
            "getCircleMember?access_token=";
    // 加入圈子
    public static final String JOINCIRCLE = IP_ADDRESS +
            "joinCircle?access_token=";
    // 退出圈子
    public static final String QUITCIRCLE = IP_ADDRESS +
            "quitCircle?access_token=";
    // 获得我加入的圈子
    public static final String GETMYJOINCIRCLE = IP_ADDRESS +
            "getMyJoinCircle?access_token=";
    // 获得我创建的圈子
    public static final String GETMYCREATECIRCLE = IP_ADDRESS +
            "getMyCreateCircle?access_token=";
    // 编辑或创建圈子
    public static final String SAVECIRCLE = IP_ADDRESS +
            "saveCircle?access_token=";
    // 获得圈子公告
    public static final String GETCIRCLENOTICE = IP_ADDRESS +
            "getCircleNotice?access_token=";
    // 编辑圈子公告
    public static final String SETCIRCLENOTICE = IP_ADDRESS +
            "setCircleNotice?access_token=";
    // 编辑圈子申请列表
    public static final String SETCIRCLEMANAGEAPPLY = IP_ADDRESS +
            "setCircleManageApply?access_token=";
    // 获得圈子申请列表
    public static final String GETCIRCLEMANAGEAPPLY = IP_ADDRESS +
            "getCircleManageApply?access_token=";
    // 获取圈子黑名单
    public static final String GETCIRCLEMANAGEBLACK = IP_ADDRESS +
            "getCircleManageBlack?access_token=";
    // 设置圈子黑名单
    public static final String SETCIRCLEMANAGEBLACK = IP_ADDRESS +
            "setCircleManageBlack?access_token=";
    // 设置圈子成员管理
    public static final String SETCIRCLEMANAGEMEMBER = IP_ADDRESS +
            "setCircleManageMember?access_token=";
    // 获得圈子成员管理
    public static final String GETCIRCLEMANAGEMEMBER = IP_ADDRESS +
            "getCircleManageMember?access_token=";
    // 群发邮件
    public static final String SENDCIRCLEMAIL = IP_ADDRESS +
            "sendCircleMail?access_token=";
    // 私信详情
    public static final String NOTIFYPRIMSGSEND = IP_ADDRESS +
            "notifyPriMsgSend?access_token=";
    // 发送私信
    public static final String NOTIFYPRIMSGDETAIL = IP_ADDRESS +
            "notifyPriMsgDetail?access_token=";
    // 获得水晶球
    public static final String GETEVALLIST = IP_ADDRESS +
            "getEvalList?access_token=";
    // 获得水晶球详情
    public static final String GETEVALDETAIL = IP_ADDRESS +
            "getEvalDetail?access_token=";
    // 获得水晶球题目详情
    public static final String GETEVALSUBJECTDETAIL = IP_ADDRESS +
            "getEvalSubjectDetail?access_token=";
    // 提交水晶球结果
    public static final String EVALSUBMIT = IP_ADDRESS +
            "evalSubmit?access_token=";
    // 获取水晶球结果
    public static final String GETEVALRESULT = IP_ADDRESS +
            "getEvalResult?access_token=";
    // 获取职业测评列表
    public static final String GETCEPINGLIST = IP_ADDRESS +
            "getCepingList?access_token=";
    // 获取职业测评详情
    public static final String GETCEPINGDETAIL = IP_ADDRESS +
            "getCepingDetail?access_token=";
    // 获取职业测评题目详情
    public static final String GETCEPINGSUBJECT = IP_ADDRESS +
            "getCepingSubject?access_token=";
    // 提交测评答案
    public static final String SUBCEPINGOPTION = IP_ADDRESS +
            "subCepingOption?access_token=";
    // 获取职业测评结果
    public static final String GETCEPINGRESULT = IP_ADDRESS +
            "getCepingResult?access_token=";
    // 获取我的职业测评列表
    public static final String GETCEPINGMINE = IP_ADDRESS +
            "getCepingMine?access_token=";
    // 获取钱包详情
    public static final String GETWALLET = IP_ADDRESS +
            "getWallet?access_token=";
    // 获取红包列表
    public static final String GETREDPACKETLIST = IP_ADDRESS +
            "getRedpacketList?access_token=";
    // 提现
    public static final String WITHDRAWALET = IP_ADDRESS +
            "withdrawWallet?access_token=";
    // 获取钱包日志列表
    public static final String GETWALLETLOGLIST = IP_ADDRESS +
            "getWalletLogList?access_token=";
    // 获取订单详情
    public static final String GETORDERDETAIL = IP_ADDRESS +
            "getOrderDetail?access_token=";
    // 拆红包
    public static final String OPENREDPACKET = IP_ADDRESS +
            "openRedpacket?access_token=";
    // 支付，生成订单
    public static final String ADDORDER = IP_ADDRESS + "addOrder?access_token=";
    // 获取活动列表
    public static final String GETEVENTLIST = IP_ADDRESS +
            "getEventList?access_token=";
    // 活动详情
    public static final String GETEVENTDETAIL = IP_ADDRESS +
            "getEventDetail?access_token=";
    // 参加活动表单
    public static final String GETJOINEVENT = IP_ADDRESS +
            "getJoinEvent?access_token=";
    // 提交参加活动
    public static final String SAVEJOINEVENT = IP_ADDRESS +
            "saveJoinEvent?access_token=";
    // 收藏、取消收藏活动
    public static final String COLLECTEVENT = IP_ADDRESS +
            "collectEvent?access_token=";
    // 获取活动照片列表
    public static final String GETEVENTPHOTOLIST = IP_ADDRESS +
            "getEventPhotoList?access_token=";
    // 获取活动照片详情
    public static final String GETEVENTPHOTODETAIL = IP_ADDRESS +
            "getEventPhotoDetail?access_token=";
    // 活动照片点赞
    public static final String LAUDEVENTPHOTO = IP_ADDRESS +
            "laudEventPhoto?access_token=";
    // 上传活动照片
    public static final String UPLOADEVENTPHOTO = IP_ADDRESS +
            "uploadEventPhoto?access_token=";
    // 删除活动照片
    public static final String DELEVENTPHOTO = IP_ADDRESS +
            "delEventPhoto?access_token=";
    // 收藏的活动列表
    public static final String COLLECTEVENTLIST = IP_ADDRESS +
            "collectEventList?access_token=";
    // 我参加的活动
    public static final String JOINEVENTLIST = IP_ADDRESS +
            "joinEventList?access_token=";
    // 活动订单详情
    public static final String ORDEREVENTDETAIL = IP_ADDRESS +
            "orderEventDetail?access_token=";
}
