package app.nahehuo.com.service;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.util.Log;
import app.nahehuo.com.base.DBHelp;
import app.nahehuo.com.base.GlobalVariables;
import app.nahehuo.com.bean.DictBank;
import app.nahehuo.com.bean.DictCert;
import app.nahehuo.com.bean.DictCircleType;
import app.nahehuo.com.bean.DictCity;
import app.nahehuo.com.bean.DictEduDegree;
import app.nahehuo.com.bean.DictEduLevel;
import app.nahehuo.com.bean.DictEventStyle;
import app.nahehuo.com.bean.DictEventTheme;
import app.nahehuo.com.bean.DictHotCity;
import app.nahehuo.com.bean.DictIndustry;
import app.nahehuo.com.bean.DictIntention;
import app.nahehuo.com.bean.DictLang;
import app.nahehuo.com.bean.DictLangLevel;
import app.nahehuo.com.bean.DictPositionCate;
import app.nahehuo.com.bean.DictPositionLevel;
import app.nahehuo.com.bean.DictProjectPlan;
import app.nahehuo.com.bean.DictProjectType;
import app.nahehuo.com.bean.DictSalary;
import app.nahehuo.com.bean.DictSkill;
import app.nahehuo.com.bean.DictUserRelation;
import app.nahehuo.com.bean.DictVersion;
import app.nahehuo.com.bean.DictWorkYear;
import app.nahehuo.com.bean.NetBank;
import app.nahehuo.com.bean.NetCert;
import app.nahehuo.com.bean.NetCircleType;
import app.nahehuo.com.bean.NetEduDegree;
import app.nahehuo.com.bean.NetEduLevel;
import app.nahehuo.com.bean.NetEventStyle;
import app.nahehuo.com.bean.NetEventTheme;
import app.nahehuo.com.bean.NetHotCity;
import app.nahehuo.com.bean.NetIntention;
import app.nahehuo.com.bean.NetLang;
import app.nahehuo.com.bean.NetLangLevel;
import app.nahehuo.com.bean.NetPositionCate;
import app.nahehuo.com.bean.NetPositionLevel;
import app.nahehuo.com.bean.NetProjectPlan;
import app.nahehuo.com.bean.NetProjectType;
import app.nahehuo.com.bean.NetSalary;
import app.nahehuo.com.bean.NetSkill;
import app.nahehuo.com.bean.NetUserRelation;
import app.nahehuo.com.bean.NetWorkYear;
import app.nahehuo.com.network.GetCallBack;
import app.nahehuo.com.network.GsonCallBack;
import app.nahehuo.com.network.HttpConnectService;
import app.nahehuo.com.network.JsonObjectCallback;
import app.nahehuo.com.util.TextUtil;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.db.sqlite.WhereBuilder;
import com.lidroid.xutils.exception.DbException;
import com.zhy.http.okhttp.OkHttpUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by WYB on 2016/2/16.
 */
public class SaveCommonDictService extends Service implements GetCallBack {

    private ExecutorService mService;
    private final static int URL_CHECK_DICTVERSION = 0;
    private final static int URL_GET_DICTPROVINCECITY = 1;
    private final static int URL_GET_DICTINDUSTRY = 2;
    private final static int URL_GET_DICTHOTCITY = 3;
    private final static int URL_GET_CIRCLETYPE = 4;
    private final static int URL_GET_DICTBANK = 5;
    private final static int URL_GET_DICTEVENTTHEME = 6;
    private final static int URL_GET_DICTEVENTSTYLE = 7;
    private final static int URL_GET_DICTPROJECTTYPE = 8;
    private final static int URL_GET_DICTPROJECTPLAN = 9;
    private final static int URL_GET_DICTWORKYEAR = 10;
    private final static int URL_GET_DICTLANG = 11;
    private final static int URL_GET_DICTPOSITIONCATE = 12;
    private final static int URL_GET_DICTEDULEVEL = 13;
    private final static int URL_GET_DICTEDUDEGREE = 14;
    private final static int URL_GET_DICTPOSITIONLEVEL = 15;
    private final static int URL_GET_DICTSALARY = 16;
    private final static int URL_GET_DICTUSERRELATION = 17;
    private final static int URL_GET_DICTLANGLEVEL = 18;
    private final static int URL_GET_DICTSKILL = 19;
    private final static int URL_GET_DICTCERT = 20;
    private final static int GETFAKETOKEN = 21;
    private final static int URL_GET_DICTINTENTION = 22;

    private SharedPreferences.Editor mEditor;
    private String fakeAccessToken;
    private HashMap<String, Integer> allVersionMap
            = new HashMap<String, Integer>();

    private List<DictVersion> intefaceVersion = new ArrayList<>();
    private List<DictVersion> dictVersion = new ArrayList<>();
    private List<DictVersion> needChangeVersion = new ArrayList<>();
    private DbUtils db;

   /* private Handler mHandler = new Handler() {
        @Override public void handleMessage(Message msg) {

            switch (msg.what) {
                case GETFAKETOKEN:

                    break;
                case URL_CHECK_DICTVERSION:

                    break;
            }
            super.handleMessage(msg);
        }
    };
*/


    private void findIntention() {
        OkHttpUtils.get()
                   .url(GlobalVariables.URL_GET_DICTINTENTION)
                   .addParams("access_token", fakeAccessToken)
                   .addParams("device", GlobalVariables.device)
                   .build()
                   .execute(new GsonCallBack<NetIntention>(NetIntention.class) {
                       @Override public void onResponse(NetIntention response) {
                           List<DictIntention> intentions
                                   = new ArrayList<DictIntention>();
                           if (response.getCode() == 200) {
                               Log.d("TAG", "findIntention");
                               for (int i = 0;
                                    i < response.getData().size();
                                    i++) {
                                   DictIntention intention
                                           = new DictIntention();
                                   intention.setName(
                                           response.getData().get(i).getName());
                                   intention.setValue(response.getData()
                                                              .get(i)
                                                              .getValue());
                                   intentions.add(intention);
                               }
                               try {
                                   db.deleteAll(DictIntention.class);
                                   db.saveAll(intentions);
                               } catch (DbException e) {
                                   e.printStackTrace();
                               }
                           }
                           super.onResponse(response);
                       }
                   });
    }


    private void findProPlan() {
        OkHttpUtils.get()
                   .url(GlobalVariables.URL_GET_DICTPROJECTPLAN)
                   .addParams("access_token", fakeAccessToken)
                   .addParams("device", GlobalVariables.device)
                   .build()
                   .execute(new GsonCallBack<NetProjectPlan>(
                           NetProjectPlan.class) {
                       @Override
                       public void onResponse(NetProjectPlan response) {
                           List<DictProjectPlan> projectPlans
                                   = new ArrayList<DictProjectPlan>();
                           if (response.getCode() == 200) {
                               Log.d("TAG", "findProPlan");
                               for (int i = 0;
                                    i < response.getData().size();
                                    i++) {
                                   DictProjectPlan projectPlan
                                           = new DictProjectPlan();
                                   projectPlan.setValue(response.getData()
                                                                .get(i)
                                                                .getValue());
                                   projectPlan.setName(
                                           response.getData().get(i).getName());
                                   projectPlans.add(projectPlan);
                               }
                               try {
                                   db.deleteAll(DictProjectPlan.class);
                                   db.saveAll(projectPlans);
                                   updateDictVersion(
                                           response.getOther().getVersion(),
                                           "project_plan");
                               } catch (DbException e) {
                                   e.printStackTrace();
                               }
                           }
                           super.onResponse(response);
                       }
                   });
    }


    private void findProType() {
        OkHttpUtils.get()
                   .url(GlobalVariables.URL_GET_DICTPROJECTTYPE)
                   .addParams("access_token", fakeAccessToken)
                   .addParams("device", GlobalVariables.device)
                   .build()
                   .execute(new GsonCallBack<NetProjectType>(
                           NetProjectType.class) {
                       @Override
                       public void onResponse(NetProjectType response) {
                           List<DictProjectType> projectTypes
                                   = new ArrayList<DictProjectType>();
                           if (response.getCode() == 200) {
                               Log.d("TAG", "findProType");
                               for (int i = 0;
                                    i < response.getData().size();
                                    i++) {
                                   DictProjectType projectType
                                           = new DictProjectType();
                                   projectType.setName(
                                           response.getData().get(i).getName());
                                   projectType.setValue(response.getData()
                                                                .get(i)
                                                                .getValue());
                                   projectTypes.add(projectType);
                               }
                               try {
                                   db.deleteAll(DictProjectType.class);
                                   db.saveAll(projectTypes);
                                   updateDictVersion(
                                           response.getOther().getVersion(),
                                           "project_type");
                               } catch (DbException e) {
                                   e.printStackTrace();
                               }
                           }

                           super.onResponse(response);
                       }
                   });
    }


    private void findEventStyle() {
        OkHttpUtils.get()
                   .url(GlobalVariables.URL_GET_DICTEVENTSTYLE)
                   .addParams("access_token", fakeAccessToken)
                   .addParams("device", GlobalVariables.device)
                   .build()
                   .execute(new GsonCallBack<NetEventStyle>(
                           NetEventStyle.class) {
                       @Override
                       public void onResponse(NetEventStyle response) {
                           List<DictEventStyle> eventStyles
                                   = new ArrayList<DictEventStyle>();
                           if (response.getCode() == 200) {
                               Log.d("TAG", "findEventStyle");
                               for (int i = 0;
                                    i < response.getData().size();
                                    i++) {
                                   DictEventStyle eventStyle
                                           = new DictEventStyle();
                                   eventStyle.setValue(response.getData()
                                                               .get(i)
                                                               .getValue());
                                   eventStyle.setName(
                                           response.getData().get(i).getName());
                                   eventStyles.add(eventStyle);
                               }
                               try {
                                   db.deleteAll(DictEventStyle.class);
                                   db.saveAll(eventStyles);
                                   updateDictVersion(
                                           response.getOther().getVersion(),
                                           "event_style");
                               } catch (DbException e) {
                                   e.printStackTrace();
                               }
                           }

                           super.onResponse(response);
                       }
                   });
    }


    private void findEventTheme() {
        OkHttpUtils.get()
                   .url(GlobalVariables.URL_GET_DICTEVENTTHEME)
                   .addParams("access_token", fakeAccessToken)
                   .addParams("device", GlobalVariables.device)
                   .build()
                   .execute(new GsonCallBack<NetEventTheme>(
                           NetEventTheme.class) {
                       @Override
                       public void onResponse(NetEventTheme response) {
                           List<DictEventTheme> eventThemes
                                   = new ArrayList<DictEventTheme>();
                           if (response.getCode() == 200) {
                               Log.d("TAG", "findEventTheme");
                               for (int i = 0;
                                    i < response.getData().size();
                                    i++) {
                                   DictEventTheme eventTheme
                                           = new DictEventTheme();
                                   eventTheme.setName(
                                           response.getData().get(i).getName());
                                   eventTheme.setValue(response.getData()
                                                               .get(i)
                                                               .getValue());
                                   eventThemes.add(eventTheme);
                               }
                               try {
                                   db.deleteAll(DictEventTheme.class);
                                   db.saveAll(eventThemes);
                                   updateDictVersion(
                                           response.getOther().getVersion(),
                                           "event_theme");
                               } catch (DbException e) {
                                   e.printStackTrace();
                               }
                           }
                           super.onResponse(response);
                       }
                   });
    }


    private void findBank() {
        OkHttpUtils.get()
                   .url(GlobalVariables.URL_GET_DICTBANK)
                   .addParams("access_token", fakeAccessToken)
                   .addParams("device", GlobalVariables.device)
                   .build()
                   .execute(new GsonCallBack<NetBank>(NetBank.class) {
                       @Override public void onResponse(NetBank response) {
                           List<DictBank> banks = new ArrayList<DictBank>();
                           if (response.getCode() == 200) {
                               Log.d("TAG", "findBank");
                               for (int i = 0;
                                    i < response.getData().size();
                                    i++) {
                                   DictBank bank = new DictBank();
                                   bank.setName(
                                           response.getData().get(i).getName());
                                   bank.setValue(response.getData()
                                                         .get(i)
                                                         .getValue());
                                   banks.add(bank);
                               }
                               try {
                                   db.deleteAll(DictBank.class);
                                   db.saveAll(banks);
                                   updateDictVersion(
                                           response.getOther().getVersion(),
                                           "bank");
                               } catch (DbException e) {
                                   e.printStackTrace();
                               }
                           }
                           super.onResponse(response);
                       }
                   });
    }


    private void findHotCity() {
        OkHttpUtils.get()
                   .url(GlobalVariables.URL_GET_DICTHOTCITY)
                   .addParams("access_token", fakeAccessToken)
                   .addParams("device", GlobalVariables.device)
                   .build()
                   .execute(new GsonCallBack<NetHotCity>(NetHotCity.class) {
                       @Override public void onResponse(NetHotCity response) {
                           List<DictHotCity> hotCities
                                   = new ArrayList<DictHotCity>();
                           if (response.getCode() == 200) {
                               Log.d("TAG", "findHotCity");
                               for (int i = 0;
                                    i < response.getData().size();
                                    i++) {
                                   DictHotCity hotCity = new DictHotCity();
                                   hotCity.setValue(response.getData()
                                                            .get(i)
                                                            .getValue());
                                   hotCity.setName(
                                           response.getData().get(i).getName());
                                   hotCities.add(hotCity);
                               }
                               try {
                                   db.deleteAll(DictHotCity.class);
                                   db.saveAll(hotCities);
                                   updateDictVersion(
                                           response.getOther().getVersion(),
                                           "hot_city");
                               } catch (DbException e) {
                                   e.printStackTrace();
                               }
                           }
                           super.onResponse(response);
                       }
                   });
    }


    private void findCircleType() {
        OkHttpUtils.get()
                   .url(GlobalVariables.URL_GET_CIRCLETYPE)
                   .addParams("access_token", fakeAccessToken)
                   .addParams("device", GlobalVariables.device)
                   .build()
                   .execute(new GsonCallBack<NetCircleType>(
                           NetCircleType.class) {
                       @Override
                       public void onResponse(NetCircleType response) {
                           List<DictCircleType> circleTypes
                                   = new ArrayList<DictCircleType>();
                           if (response.getCode() == 200) {
                               Log.d("TAG", "findCircleType");
                               for (int i = 0;
                                    i < response.getData().size();
                                    i++) {
                                   DictCircleType circleType
                                           = new DictCircleType();
                                   circleType.setName(
                                           response.getData().get(i).getName());
                                   circleType.setValue(response.getData()
                                                               .get(i)
                                                               .getValue());
                                   circleTypes.add(circleType);
                               }
                               try {
                                   db.deleteAll(DictCircleType.class);
                                   db.saveAll(circleTypes);
                                   updateDictVersion(
                                           response.getOther().getVersion(),
                                           "circle_type");
                               } catch (DbException e) {
                                   e.printStackTrace();
                               }
                           }
                           super.onResponse(response);
                       }
                   });
    }


    private void findUserRelation() {
        OkHttpUtils.get()
                   .url(GlobalVariables.URL_GET_DICTUSERRELATION)
                   .addParams("access_token", fakeAccessToken)
                   .addParams("device", GlobalVariables.device)
                   .build()
                   .execute(new GsonCallBack<NetUserRelation>(
                           NetUserRelation.class) {
                       @Override
                       public void onResponse(NetUserRelation response) {
                           List<DictUserRelation> userRelations
                                   = new ArrayList<DictUserRelation>();
                           if (response.getCode() == 200) {
                               Log.d("TAG", "findUserRelation");
                               for (int i = 0;
                                    i < response.getData().size();
                                    i++) {
                                   DictUserRelation userRelation
                                           = new DictUserRelation();
                                   userRelation.setValue(response.getData()
                                                                 .get(i)
                                                                 .getValue());
                                   userRelation.setName(
                                           response.getData().get(i).getName());
                                   userRelations.add(userRelation);
                               }
                               try {
                                   db.deleteAll(DictUserRelation.class);
                                   db.saveAll(userRelations);
                                   updateDictVersion(
                                           response.getOther().getVersion(),
                                           "user_relation");
                               } catch (DbException e) {
                                   e.printStackTrace();
                               }
                           }
                           super.onResponse(response);
                       }
                   });
    }


    private void findSalary() {
        OkHttpUtils.get()
                   .url(GlobalVariables.URL_GET_DICTSALARY)
                   .addParams("access_token", fakeAccessToken)
                   .addParams("device", GlobalVariables.device)
                   .build()
                   .execute(new GsonCallBack<NetSalary>(NetSalary.class) {
                       @Override public void onResponse(NetSalary response) {
                           List<DictSalary> salaries
                                   = new ArrayList<DictSalary>();
                           if (response.getCode() == 200) {
                               Log.d("TAG", "findSalary");
                               for (int i = 0;
                                    i < response.getData().size();
                                    i++) {
                                   DictSalary salary = new DictSalary();
                                   salary.setName(
                                           response.getData().get(i).getName());
                                   salary.setValue(response.getData()
                                                           .get(i)
                                                           .getValue());
                                   salaries.add(salary);
                               }
                               try {
                                   db.deleteAll(DictSalary.class);
                                   db.saveAll(salaries);
                                   updateDictVersion(
                                           response.getOther().getVersion(),
                                           "salary");
                               } catch (DbException e) {
                                   e.printStackTrace();
                               }
                           }
                           super.onResponse(response);
                       }
                   });
    }


    private void findEduDegree() {
        OkHttpUtils.get()
                   .url(GlobalVariables.URL_GET_DICTEDUDEGREE)
                   .addParams("access_token", fakeAccessToken)
                   .addParams("device", GlobalVariables.device)
                   .build()
                   .execute(new GsonCallBack<NetEduDegree>(NetEduDegree.class) {
                       @Override public void onResponse(NetEduDegree response) {
                           List<DictEduDegree> eduDegrees
                                   = new ArrayList<DictEduDegree>();
                           if (response.getCode() == 200) {
                               Log.d("TAG", "findEduDegree");
                               for (int i = 0;
                                    i < response.getData().size();
                                    i++) {
                                   DictEduDegree eduDegree
                                           = new DictEduDegree();
                                   eduDegree.setName(
                                           response.getData().get(i).getName());
                                   eduDegree.setValue(response.getData()
                                                              .get(i)
                                                              .getValue());
                                   eduDegrees.add(eduDegree);
                               }
                               try {
                                   db.deleteAll(DictEduDegree.class);
                                   db.saveAll(eduDegrees);
                                   updateDictVersion(
                                           response.getOther().getVersion(),
                                           "degree");
                               } catch (DbException e) {
                                   e.printStackTrace();
                               }
                           }
                           super.onResponse(response);
                       }
                   });
    }


    private void findEduLevel() {
        OkHttpUtils.get()
                   .url(GlobalVariables.URL_GET_DICTEDULEVEL)
                   .addParams("access_token", fakeAccessToken)
                   .addParams("device", GlobalVariables.device)
                   .build()
                   .execute(new GsonCallBack<NetEduLevel>(NetEduLevel.class) {
                       @Override public void onResponse(NetEduLevel response) {
                           List<DictEduLevel> eduLevels
                                   = new ArrayList<DictEduLevel>();
                           if (response.getCode() == 200) {
                               Log.d("TAG", "findEduLevel");
                               for (int i = 0;
                                    i < response.getData().size();
                                    i++) {
                                   DictEduLevel eduLevel = new DictEduLevel();
                                   eduLevel.setValue(response.getData()
                                                             .get(i)
                                                             .getValue());
                                   eduLevel.setName(
                                           response.getData().get(i).getName());
                                   eduLevels.add(eduLevel);
                               }
                               try {
                                   db.deleteAll(DictEduLevel.class);
                                   db.saveAll(eduLevels);
                                   updateDictVersion(
                                           response.getOther().getVersion(),
                                           "education");
                               } catch (DbException e) {
                                   e.printStackTrace();
                               }
                           }
                           super.onResponse(response);
                       }
                   });
    }


    private void findWorkYear() {
        OkHttpUtils.get()
                   .url(GlobalVariables.URL_GET_DICTWORKYEAR)
                   .addParams("access_token", fakeAccessToken)
                   .addParams("device", GlobalVariables.device)
                   .build()
                   .execute(new GsonCallBack<NetWorkYear>(NetWorkYear.class) {
                       @Override public void onResponse(NetWorkYear response) {
                           List<DictWorkYear> workYears
                                   = new ArrayList<DictWorkYear>();
                           if (response.getCode() == 200) {
                               Log.d("TAG", "findWorkYear");
                               for (int i = 0;
                                    i < response.getData().size();
                                    i++) {
                                   DictWorkYear workYear = new DictWorkYear();
                                   workYear.setValue(response.getData()
                                                             .get(i)
                                                             .getValue());
                                   workYear.setName(
                                           response.getData().get(i).getName());
                                   workYears.add(workYear);
                               }
                               try {
                                   db.deleteAll(DictWorkYear.class);
                                   db.saveAll(workYears);
                                   updateDictVersion(
                                           response.getOther().getVersion(),
                                           "work_year");
                               } catch (DbException e) {
                                   e.printStackTrace();
                               }
                           }
                           super.onResponse(response);
                       }
                   });
    }


    private void findLangLevel() {
        OkHttpUtils.get()
                   .url(GlobalVariables.URL_GET_DICTLANGLEVEL)
                   .addParams("access_token", fakeAccessToken)
                   .addParams("device", GlobalVariables.device)
                   .build()
                   .execute(new GsonCallBack<NetLangLevel>(NetLangLevel.class) {
                       @Override public void onResponse(NetLangLevel response) {
                           List<DictLangLevel> langLevels
                                   = new ArrayList<DictLangLevel>();
                           if (response.getCode() == 200) {
                               Log.d("TAG", "findLangLevel");
                               for (int i = 0;
                                    i < response.getData().size();
                                    i++) {
                                   DictLangLevel langLevel
                                           = new DictLangLevel();
                                   langLevel.setValue(response.getData()
                                                              .get(i)
                                                              .getValue());
                                   langLevel.setName(
                                           response.getData().get(i).getName());
                                   langLevels.add(langLevel);
                               }
                               try {
                                   db.deleteAll(DictLangLevel.class);
                                   db.saveAll(langLevels);
                                   updateDictVersion(
                                           response.getOther().getVersion(),
                                           "language_level");
                               } catch (DbException e) {
                                   e.printStackTrace();
                               }
                           }
                           super.onResponse(response);
                       }
                   });
    }


    private void findLang() {
        OkHttpUtils.get()
                   .url(GlobalVariables.URL_GET_DICTLANG)
                   .addParams("access_token", fakeAccessToken)
                   .addParams("device", GlobalVariables.device)
                   .build()
                   .execute(new GsonCallBack<NetLang>(NetLang.class) {
                       @Override public void onResponse(NetLang response) {
                           List<DictLang> dictLangs = new ArrayList<DictLang>();
                           if (response.getCode() == 200) {
                               Log.d("TAG", "findLang");
                               for (int i = 0;
                                    i < response.getData().size();
                                    i++) {
                                   DictLang dictLang = new DictLang();
                                   dictLang.setId(
                                           response.getData().get(i).getId());
                                   dictLang.setName(
                                           response.getData().get(i).getName());
                                   dictLangs.add(dictLang);
                               }
                               try {
                                   db.deleteAll(DictLang.class);
                                   db.saveAll(dictLangs);
                                   updateDictVersion(
                                           response.getOther().getVersion(),
                                           "language");
                               } catch (DbException e) {
                                   e.printStackTrace();
                               }
                           }
                           super.onResponse(response);
                       }
                   });
    }


    private void findPositionLevel() {
        OkHttpUtils.get()
                   .url(GlobalVariables.URL_GET_DICTPOSITIONLEVEL)
                   .addParams("access_token", fakeAccessToken)
                   .addParams("device", GlobalVariables.device)
                   .build()
                   .execute(new GsonCallBack<NetPositionLevel>(
                           NetPositionLevel.class) {
                       @Override
                       public void onResponse(NetPositionLevel response) {
                           List<DictPositionLevel> positionLevels
                                   = new ArrayList<DictPositionLevel>();
                           if (response.getCode() == 200) {
                               Log.d("TAG", "findPositionLevel");
                               for (int i = 0;
                                    i < response.getData().size();
                                    i++) {
                                   DictPositionLevel positionLevel
                                           = new DictPositionLevel();
                                   positionLevel.setValue(response.getData()
                                                                  .get(i)
                                                                  .getValue());
                                   positionLevel.setName(
                                           response.getData().get(i).getName());
                                   positionLevels.add(positionLevel);
                               }
                               try {
                                   db.deleteAll(DictPositionLevel.class);
                                   db.saveAll(positionLevels);
                                   updateDictVersion(
                                           response.getOther().getVersion(),
                                           "position_level");
                               } catch (DbException e) {
                                   e.printStackTrace();
                               }
                           }

                           super.onResponse(response);
                       }
                   });
    }


    private void findPositionCate() {
        OkHttpUtils.get()
                   .url(GlobalVariables.URL_GET_DICTPOSITIONCATE)
                   .addParams("access_token", fakeAccessToken)
                   .addParams("device", GlobalVariables.device)
                   .build()
                   .execute(new GsonCallBack<NetPositionCate>(
                           NetPositionCate.class) {
                       @Override
                       public void onResponse(NetPositionCate response) {
                           List<DictPositionCate> positionCates
                                   = new ArrayList<DictPositionCate>();
                           if (response.getCode() == 200) {
                               Log.d("TAG", "findPositionCate");
                               for (int i = 0;
                                    i < response.getData().size();
                                    i++) {
                                   DictPositionCate positionCate
                                           = new DictPositionCate();
                                   positionCate.setId(
                                           response.getData().get(i).getId());
                                   positionCate.setName(
                                           response.getData().get(i).getName());
                                   positionCates.add(positionCate);
                               }
                               try {
                                   db.deleteAll(DictPositionCate.class);
                                   db.saveAll(positionCates);
                                   updateDictVersion(
                                           response.getOther().getVersion(),
                                           "position_cate");
                               } catch (DbException e) {
                                   e.printStackTrace();
                               }
                           }
                           super.onResponse(response);
                       }
                   });
    }


    private void findCert() {
        OkHttpUtils.get()
                   .url(GlobalVariables.URL_GET_DICTCERT)
                   .addParams("access_token", fakeAccessToken)
                   .addParams("device", GlobalVariables.device)
                   .build()
                   .execute(new GsonCallBack<NetCert>(NetCert.class) {
                       @Override public void onResponse(NetCert response) {
                           List<DictCert> dictCerts = new ArrayList<DictCert>();
                           if (response.getCode() == 200) {
                               Log.d("TAG", "findCert");
                               for (int i = 0;
                                    i < response.getData().size();
                                    i++) {
                                   DictCert dictCert = new DictCert();
                                   dictCert.setId(
                                           response.getData().get(i).getId());
                                   dictCert.setName(
                                           response.getData().get(i).getName());
                                   dictCert.setLevel(response.getData()
                                                             .get(i)
                                                             .getLevel());
                                   dictCert.setUpid(
                                           response.getData().get(i).getUpid());
                                   dictCerts.add(dictCert);
                               }
                               try {
                                   db.deleteAll(DictCert.class);
                                   db.saveAll(dictCerts);
                                   updateDictVersion(
                                           response.getOther().getVersion(),
                                           "cert");
                               } catch (DbException e) {
                                   e.printStackTrace();
                               }
                           }

                           super.onResponse(response);
                       }
                   });
    }


    private void findSkill() {
        OkHttpUtils.get()
                   .url(GlobalVariables.URL_GET_DICTSKILL)
                   .addParams("access_token", fakeAccessToken)
                   .addParams("device", GlobalVariables.device)
                   .build()
                   .execute(new GsonCallBack<NetSkill>(NetSkill.class) {
                       @Override public void onResponse(NetSkill response) {
                           List<DictSkill> dictSkills
                                   = new ArrayList<DictSkill>();
                           if (response.getCode() == 200) {
                               Log.d("TAG", "findSkill");
                               for (int i = 0;
                                    i < response.getData().size();
                                    i++) {
                                   DictSkill dictSkill = new DictSkill();
                                   dictSkill.setId(
                                           response.getData().get(i).getId());
                                   dictSkill.setName(
                                           response.getData().get(i).getName());
                                   dictSkill.setUpid(
                                           response.getData().get(i).getUpid());
                                   dictSkill.setLevel(response.getData()
                                                              .get(i)
                                                              .getLevel());
                                   dictSkill.setOften(response.getData()
                                                              .get(i)
                                                              .getOften());
                                   dictSkills.add(dictSkill);
                               }
                               try {
                                   db.deleteAll(DictSkill.class);
                                   db.saveAll(dictSkills);
                                   updateDictVersion(
                                           response.getOther().getVersion(),
                                           "skill");
                               } catch (DbException e) {
                                   e.printStackTrace();
                               }
                           }

                           super.onResponse(response);
                       }
                   });
    }


    private void findIndustry() {
        OkHttpUtils.get()
                   .url(GlobalVariables.URL_GET_DICTINDUSTRY)
                   .addParams("access_token", fakeAccessToken)
                   .addParams("device", GlobalVariables.device)
                   .build()
                   .execute(new JsonObjectCallback() {
                       @Override public void onResponse(String response) {

                           try {
                               List<DictIndustry> industries
                                       = new ArrayList<DictIndustry>();
                               JSONObject jsonObject = new JSONObject(response);
                               int status = jsonObject.getInt("code");
                               if (status == 200) {
                                   Log.d("TAG", "findIndustry");
                                   JSONArray jsonArray
                                           = jsonObject.getJSONArray("data");
                                   for (int i = 0;
                                        i < jsonArray.length();
                                        i++) {
                                       JSONObject item
                                               = jsonArray.optJSONObject(i);
                                       DictIndustry dictIndustry
                                               = new DictIndustry();
                                       dictIndustry.setId(item.getInt("id"));
                                       dictIndustry.setLevel(
                                               item.getInt("level"));
                                       dictIndustry.setUpid(
                                               item.getInt("upid"));
                                       dictIndustry.setName(
                                               item.getString("name"));
                                       industries.add(dictIndustry);
                                   }

                                   db.deleteAll(DictIndustry.class);
                                   db.saveAll(industries);
                                   updateDictVersion(
                                           jsonObject.getJSONObject("other")
                                                     .getInt("version"),
                                           "industry");
                               }
                           } catch (JSONException e) {
                               e.printStackTrace();
                           } catch (DbException e) {
                               e.printStackTrace();
                           }

                           super.onResponse(response);
                       }
                   });
    }


    private void findCity() {
        OkHttpUtils.get()
                   .url(GlobalVariables.URL_GET_DICTPROVINCECITY)
                   .addParams("access_token", fakeAccessToken)
                   .addParams("device", GlobalVariables.device)
                   .build()
                   .execute(new JsonObjectCallback() {
                       @Override public void onResponse(String response) {
                           try {
                               List<DictCity> cityDicts
                                       = new ArrayList<DictCity>();
                               JSONObject jsonObject = new JSONObject(response);
                               int status = jsonObject.getInt("code");
                               if (status == 200) {
                                   Log.d("TAG", "findCity");
                                   JSONArray cityArray
                                           = jsonObject.getJSONArray("data");
                                   for (int i = 0;
                                        i < cityArray.length();
                                        i++) {
                                       JSONObject item
                                               = cityArray.getJSONObject(i);
                                       DictCity cityDict = new DictCity();
                                       cityDict.setCityid(item.getInt("id"));
                                       cityDict.setLevel(item.getInt("level"));
                                       cityDict.setName(item.getString("name"));
                                       cityDict.setUpid(item.getInt("upid"));
                                       cityDicts.add(cityDict);
                                   }

                                   db.deleteAll(DictCity.class);
                                   db.saveAll(cityDicts);
                                   updateDictVersion(
                                           jsonObject.getJSONObject("other")
                                                     .getInt("version"),
                                           "area");
                               }
                           } catch (JSONException e) {
                               e.printStackTrace();
                           } catch (DbException e) {
                               e.printStackTrace();
                           }
                           super.onResponse(response);
                       }
                   });
    }


    private void checkDictVersion() {
        OkHttpUtils.get()
                   .url(GlobalVariables.URL_CHECK_DICTVERSION)
                   .addParams("access_token", fakeAccessToken)
                   .addParams("device", GlobalVariables.device)
                   .build()
                   .execute(new JsonObjectCallback() {
                       @Override public void onResponse(String response) {

                           try {
                               JSONObject jsonObject = new JSONObject(response);
                               int status = jsonObject.getInt("code");
                               if (status == 200) {
                                   JSONArray allVersionArray
                                           = jsonObject.getJSONArray("data");
                                   for (int i = 0;
                                        i < allVersionArray.length();
                                        i++) {
                                       JSONObject jsonObjectItem
                                               = allVersionArray.getJSONObject(
                                               i);
                                       allVersionMap.put(
                                               jsonObjectItem.getString("key"),
                                               jsonObjectItem.getInt(
                                                       "version"));
                                       DictVersion version = new DictVersion();
                                       version.setKey(
                                               jsonObjectItem.getString("key"));
                                       version.setVersion(jsonObjectItem.getInt(
                                               "version"));

                                       intefaceVersion.add(version);
                                   }
                                   SaveOrUpdateDict();
                               }
                           } catch (JSONException e) {
                               e.printStackTrace();
                           }
                           super.onResponse(response);
                       }
                   });
    }


    private void SaveOrUpdateDict() {
        db = DBHelp.getInstance(this);
        try {
            dictVersion = db.findAll(DictVersion.class);
            if (dictVersion == null) {
                db.saveAll(intefaceVersion);
                needChangeVersion.addAll(intefaceVersion);
            }
            else {
                for (DictVersion versionDict : dictVersion) {
                    if (versionDict.getVersion() !=
                            allVersionMap.get(versionDict.getKey())) {
                        needChangeVersion.add(versionDict);
                    }
                }
            }
            for (DictVersion versionDict : needChangeVersion) {
                String key = versionDict.getKey();
                if ("area".equalsIgnoreCase(key)) {
                   /* mService.execute(new Runnable() {
                        @Override public void run() {
                            findCity();
                        }
                    });*/
                    getDict(GlobalVariables.URL_GET_DICTPROVINCECITY +
                            "?access_token=" +
                            GlobalVariables.fake_access_token +
                            "&device=app", URL_GET_DICTPROVINCECITY);
                }
                else if ("degree".equalsIgnoreCase(key)) {
                  /*  mService.execute(new Runnable() {
                        @Override public void run() {

                            findEduDegree();
                        }
                    });*/
                    getDict(GlobalVariables.URL_GET_DICTEDUDEGREE +
                            "?access_token=" +
                            GlobalVariables.fake_access_token +
                            "&device=app", URL_GET_DICTEDUDEGREE);
                   /* mHandler.sendEmptyMessage(URL_GET_DICTEDUDEGREE);*/
                }
                else if ("salary".equalsIgnoreCase(key)) {
                   /* mService.execute(new Runnable() {
                        @Override public void run() {

                            findSalary();
                        }
                    });*/
                    getDict(GlobalVariables.URL_GET_DICTSALARY +
                            "?access_token=" +
                            GlobalVariables.fake_access_token +
                            "&device=app", URL_GET_DICTSALARY);
                    /*mHandler.sendEmptyMessage(URL_GET_DICTSALARY);*/
                }
                else if ("user_relation".equalsIgnoreCase(key)) {
                   /* mService.execute(new Runnable() {
                        @Override public void run() {

                            findUserRelation();
                        }
                    });*/
                    getDict(GlobalVariables.URL_GET_DICTUSERRELATION +
                            "?access_token=" +
                            GlobalVariables.fake_access_token +
                            "&device=app", URL_GET_DICTUSERRELATION);
                   /* mHandler.sendEmptyMessage(URL_GET_DICTUSERRELATION);*/
                }
                else if ("circle_type".equalsIgnoreCase(key)) {
                 /*   mService.execute(new Runnable() {
                        @Override public void run() {
                            findCircleType();
                        }
                    });*/

                    getDict(GlobalVariables.URL_GET_CIRCLETYPE +
                            "?access_token=" +
                            GlobalVariables.fake_access_token +
                            "&device=app", URL_GET_CIRCLETYPE);
                   /* mHandler.sendEmptyMessage(URL_GET_CIRCLETYPE);*/
                }
                else if ("hot_city".equalsIgnoreCase(key)) {
                 /*   mService.execute(new Runnable() {
                        @Override public void run() {
                            findHotCity();
                        }
                    });*/
                    getDict(GlobalVariables.URL_GET_DICTHOTCITY +
                            "?access_token=" +
                            GlobalVariables.fake_access_token +
                            "&device=app", URL_GET_DICTHOTCITY);
                   /* mHandler.sendEmptyMessage(URL_GET_DICTHOTCITY);*/
                }
                else if ("bank".equalsIgnoreCase(key)) {
                  /*  mService.execute(new Runnable() {
                        @Override public void run() {
                            findBank();
                        }
                    });*/
                    getDict(GlobalVariables.URL_GET_DICTBANK +
                            "?access_token=" +
                            GlobalVariables.fake_access_token +
                            "&device=app", URL_GET_DICTBANK);
                  /*  mHandler.sendEmptyMessage(URL_GET_DICTBANK);*/
                }
                else if ("event_theme".equalsIgnoreCase(key)) {
                /*    mService.execute(new Runnable() {
                        @Override public void run() {
                            findEventTheme();
                        }
                    });*/
                    getDict(GlobalVariables.URL_GET_DICTEVENTTHEME +
                            "?access_token=" +
                            GlobalVariables.fake_access_token +
                            "&device=app", URL_GET_DICTEVENTTHEME);
                   /* mHandler.sendEmptyMessage(URL_GET_DICTEVENTTHEME);*/
                }
                else if ("event_style".equalsIgnoreCase(key)) {
                   /* mService.execute(new Runnable() {
                        @Override public void run() {
                            findEventStyle();
                        }
                    });*/
                    getDict(GlobalVariables.URL_GET_DICTEVENTSTYLE +
                            "?access_token=" +
                            GlobalVariables.fake_access_token +
                            "&device=app", URL_GET_DICTEVENTSTYLE);
                    /*mHandler.sendEmptyMessage(URL_GET_DICTEVENTSTYLE);*/
                }
                else if ("project_type".equalsIgnoreCase(key)) {
                 /*   mService.execute(new Runnable() {
                        @Override public void run() {
                            findProType();
                        }
                    });*/
                    getDict(GlobalVariables.URL_GET_DICTPROJECTTYPE +
                            "?access_token=" +
                            GlobalVariables.fake_access_token +
                            "&device=app", URL_GET_DICTPROJECTTYPE);
                    /*mHandler.sendEmptyMessage(URL_GET_DICTPROJECTTYPE);*/
                }
                else if ("project_plan".equalsIgnoreCase(key)) {
                   /* mService.execute(new Runnable() {
                        @Override public void run() {
                            findProPlan();
                        }
                    });*/
                    getDict(GlobalVariables.URL_GET_DICTPROJECTPLAN +
                            "?access_token=" +
                            GlobalVariables.fake_access_token +
                            "&device=app", URL_GET_DICTPROJECTPLAN);
                   /* mHandler.sendEmptyMessage(URL_GET_DICTPROJECTPLAN);*/
                }
                else if ("intention".equalsIgnoreCase(key)) {
                  /*  mService.execute(new Runnable() {
                        @Override public void run() {
                            findIntention();
                        }
                    });*/
                    getDict(GlobalVariables.URL_GET_DICTINTENTION +
                            "?access_token=" +
                            GlobalVariables.fake_access_token +
                            "&device=app", URL_GET_DICTINTENTION);
                    /*mHandler.sendEmptyMessage(URL_GET_DICTINTENTION);*/
                }
                else if ("industry".equalsIgnoreCase(key)) {
                    /*mService.execute(new Runnable() {
                        @Override public void run() {
                            findIndustry();
                        }
                    });*/
                    getDict(GlobalVariables.URL_GET_DICTINDUSTRY +
                            "?access_token=" +
                            GlobalVariables.fake_access_token +
                            "&device=app", URL_GET_DICTINDUSTRY);
                    /*mHandler.sendEmptyMessage(URL_GET_DICTINDUSTRY);*/
                }
                else if ("skill".equalsIgnoreCase(key)) {
                    /*mService.execute(new Runnable() {
                        @Override public void run() {
                            findSkill();
                        }
                    });*/
                    getDict(GlobalVariables.URL_GET_DICTSKILL +
                            "?access_token=" +
                            GlobalVariables.fake_access_token +
                            "&device=app", URL_GET_DICTSKILL);
                    /*mHandler.sendEmptyMessage(URL_GET_DICTSKILL);*/
                }
                else if ("cert".equalsIgnoreCase(key)) {
                  /*  mService.execute(new Runnable() {
                        @Override public void run() {
                            findCert();
                        }
                    });*/
                    getDict(GlobalVariables.URL_GET_DICTCERT +
                            "?access_token=" +
                            GlobalVariables.fake_access_token +
                            "&device=app", URL_GET_DICTCERT);
                 /*   mHandler.sendEmptyMessage(URL_GET_DICTCERT);*/
                }
                else if ("position_cate".equalsIgnoreCase(key)) {
                    /*mService.execute(new Runnable() {
                        @Override public void run() {
                            findPositionCate();
                        }
                    });*/
                    getDict(GlobalVariables.URL_GET_DICTPOSITIONCATE +
                            "?access_token=" +
                            GlobalVariables.fake_access_token +
                            "&device=app", URL_GET_DICTPOSITIONCATE);
                    /*
                    mHandler.sendEmptyMessage(URL_GET_DICTPOSITIONCATE);*/
                }
                else if ("position_level".equalsIgnoreCase(key)) {
                   /* mService.execute(new Runnable() {
                        @Override public void run() {
                            findPositionLevel();
                        }
                    });*/
                    getDict(GlobalVariables.URL_GET_DICTPOSITIONLEVEL +
                            "?access_token=" +
                            GlobalVariables.fake_access_token +
                            "&device=app", URL_GET_DICTPOSITIONLEVEL);
                   /* mHandler.sendEmptyMessage(URL_GET_DICTPOSITIONLEVEL);*/
                }
                else if ("language".equalsIgnoreCase(key)) {
                  /*  mService.execute(new Runnable() {
                        @Override public void run() {
                            findLang();
                        }
                    });*/
                    getDict(GlobalVariables.URL_GET_DICTLANG +
                            "?access_token=" +
                            GlobalVariables.fake_access_token +
                            "&device=app", URL_GET_DICTLANG);
                 /*   mHandler.sendEmptyMessage(URL_GET_DICTLANG);*/
                }
                else if ("language_level".equalsIgnoreCase(key)) {
                   /* mService.execute(new Runnable() {
                        @Override public void run() {
                            findLangLevel();
                        }
                    });*/
                    getDict(GlobalVariables.URL_GET_DICTLANGLEVEL +
                            "?access_token=" +
                            GlobalVariables.fake_access_token +
                            "&device=app", URL_GET_DICTLANGLEVEL);
               /*     mHandler.sendEmptyMessage(URL_GET_DICTLANGLEVEL);*/
                }
                else if ("work_year".equalsIgnoreCase(key)) {
                   /* mService.execute(new Runnable() {
                        @Override public void run() {

                            findWorkYear();
                        }
                    });*/
                    getDict(GlobalVariables.URL_GET_DICTWORKYEAR +
                            "?access_token=" +
                            GlobalVariables.fake_access_token +
                            "&device=app", URL_GET_DICTWORKYEAR);
                    /*mHandler.sendEmptyMessage(URL_GET_DICTWORKYEAR);*/
                }
                else if ("education".equalsIgnoreCase(key)) {
                 /*   mService.execute(new Runnable() {
                        @Override public void run() {
                            findEduLevel();
                        }
                    });*/

                    getDict(GlobalVariables.URL_GET_DICTEDULEVEL +
                            "?access_token=" +
                            GlobalVariables.fake_access_token +
                            "&device=app", URL_GET_DICTEDULEVEL);
                   /* mHandler.sendEmptyMessage(URL_GET_DICTEDULEVEL);*/
                }
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
    }


    @Override public IBinder onBind(Intent intent) {
        return null;
    }


    @Override public int onStartCommand(Intent intent, int flags, int startId) {
        mService = Executors.newFixedThreadPool(3);
        findFakeToken();
        return super.onStartCommand(intent, flags, startId);
    }


    private void findFakeToken() {
        OkHttpUtils.get()
                   .url(GlobalVariables.URL_CHECK_DICTVERSION)
                   .addParams("deviceCode", "123")
                   .addParams("deviceName", "app")
                   .build()
                   .execute(new JsonObjectCallback() {
                       @Override public void onResponse(String response) {
                           if (!TextUtil.isEmpty(response)) {
                               try {
                                   JSONObject jsonObject = new JSONObject(
                                           response);
                                   GlobalVariables.fake_access_token
                                           = jsonObject.getString(
                                           "accessToken");
                                   fakeAccessToken = jsonObject.getString(
                                           "accessToken");
                                   mEditor = getSharedPreferences("user",
                                           MODE_PRIVATE).edit();
                                   mEditor.putString("fake_access_token",
                                           jsonObject.getString("accessToken"))
                                          .commit();
                                   checkDictVersion();
                               } catch (JSONException e) {
                                   e.printStackTrace();
                               }
                           }
                           super.onResponse(response);
                       }
                   });
    }


    private void updateDictVersion(int version, String key) throws DbException {
        DictVersion dictVersion = new DictVersion();
        dictVersion.setVersion(version);
        db.update(dictVersion, WhereBuilder.b("key", "=", key), "version");
    }


    /**
     * 调用共通接口
     *
     * @param url ip地址
     * @param resultCode 请求码
     */
    private void getDict(String url, int resultCode) {
        HttpConnectService connectService = new HttpConnectService();
        connectService.setUrl(url);
        connectService.setResultCode(resultCode);
        connectService.connectGet(this, this, "");
    }


    @Override public void getCallBack(int resultCode, String result) {
        final JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(result);
            int code = jsonObject.getInt("code");

            if (code == 200) {
                if (resultCode == URL_GET_DICTPROVINCECITY) {
                    mService.execute(new Runnable() {
                        @Override public void run() {
                            try {
                                List<DictCity> cityDicts
                                        = new ArrayList<DictCity>();
                                Log.d("TAG", "findCity" +
                                        jsonObject.getString("message"));
                                JSONArray cityArray = jsonObject.getJSONArray(
                                        "data");
                                for (int i = 0; i < cityArray.length(); i++) {
                                    JSONObject item = cityArray.getJSONObject(
                                            i);
                                    DictCity cityDict = new DictCity();
                                    cityDict.setCityid(item.getInt("id"));
                                    cityDict.setLevel(item.getInt("level"));
                                    cityDict.setName(item.getString("name"));
                                    cityDict.setUpid(item.getInt("upid"));
                                    cityDicts.add(cityDict);
                                }
                                db.deleteAll(DictCity.class);
                                db.saveAll(cityDicts);
                                updateDictVersion(
                                        jsonObject.getJSONObject("other")
                                                  .getInt("version"), "area");
                            } catch (Exception e1) {
                                e1.printStackTrace();
                            }
                        }
                    });
                }
                else if (resultCode == URL_GET_DICTINDUSTRY) {
                    mService.execute(new Runnable() {
                        @Override public void run() {
                            try {
                                List<DictIndustry> industries
                                        = new ArrayList<DictIndustry>();
                                Log.d("TAG", "findIndustry" +
                                        jsonObject.getString("message"));
                                JSONArray jsonArray = jsonObject.getJSONArray(
                                        "data");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject item = jsonArray.optJSONObject(
                                            i);
                                    DictIndustry dictIndustry
                                            = new DictIndustry();
                                    dictIndustry.setId(item.getInt("id"));
                                    dictIndustry.setLevel(item.getInt("level"));
                                    dictIndustry.setUpid(item.getInt("upid"));
                                    dictIndustry.setName(
                                            item.getString("name"));
                                    industries.add(dictIndustry);
                                }

                                db.deleteAll(DictIndustry.class);
                                db.saveAll(industries);
                                updateDictVersion(
                                        jsonObject.getJSONObject("other")
                                                  .getInt("version"),
                                        "industry");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
                else if (resultCode == URL_GET_DICTSKILL) {
                    mService.execute(new Runnable() {
                        @Override public void run() {
                            try {
                                List<DictSkill> skills
                                        = new ArrayList<DictSkill>();
                                Log.d("TAG", "skill" +
                                        jsonObject.getString("message"));
                                JSONArray jsonArray = jsonObject.getJSONArray(
                                        "data");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject item = jsonArray.optJSONObject(
                                            i);
                                    DictSkill dictSkill = new DictSkill();
                                    dictSkill.setId(item.getInt("id"));
                                    dictSkill.setName(item.getString("name"));
                                    dictSkill.setUpid(item.getInt("upid"));
                                    dictSkill.setLevel(item.getInt("level"));
                                    dictSkill.setOften(item.getInt("often"));
                                    skills.add(dictSkill);
                                }
                                db.deleteAll(DictSkill.class);
                                db.saveAll(skills);
                                updateDictVersion(
                                        jsonObject.getJSONObject("other")
                                                  .getInt("version"), "skill");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
                else if (resultCode == URL_GET_DICTCERT) {
                    mService.execute(new Runnable() {
                        @Override public void run() {
                            try {
                                List<DictCert> certs
                                        = new ArrayList<DictCert>();
                                Log.d("TAG", "cert" +
                                        jsonObject.getString("message"));
                                JSONArray jsonArray = jsonObject.getJSONArray(
                                        "data");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject item = jsonArray.optJSONObject(
                                            i);
                                    DictCert dictCert = new DictCert();
                                    dictCert.setId(item.getInt("id"));
                                    dictCert.setName(item.getString("name"));
                                    dictCert.setUpid(item.getInt("upid"));
                                    dictCert.setLevel(item.getInt("level"));
                                    certs.add(dictCert);
                                }
                                db.deleteAll(DictCert.class);
                                db.saveAll(certs);
                                updateDictVersion(
                                        jsonObject.getJSONObject("other")
                                                  .getInt("version"), "cert");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
                else if (resultCode == URL_GET_DICTPOSITIONCATE) {
                    mService.execute(new Runnable() {
                        @Override public void run() {
                            try {
                                List<DictPositionCate> positionCates
                                        = new ArrayList<DictPositionCate>();
                                Log.d("TAG", "position_cate" +
                                        jsonObject.getString("message"));
                                JSONArray jsonArray = jsonObject.getJSONArray(
                                        "data");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject item = jsonArray.optJSONObject(
                                            i);
                                    DictPositionCate positionCate
                                            = new DictPositionCate();
                                    positionCate.setId(item.getInt("id"));
                                    positionCate.setName(
                                            item.getString("name"));
                                    positionCates.add(positionCate);
                                }
                                db.deleteAll(DictPositionCate.class);
                                db.saveAll(positionCates);
                                updateDictVersion(
                                        jsonObject.getJSONObject("other")
                                                  .getInt("version"),
                                        "position_cate");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
                else if (resultCode == URL_GET_DICTPOSITIONLEVEL) {
                    mService.execute(new Runnable() {
                        @Override public void run() {
                            try {
                                List<DictPositionLevel> positionLevels
                                        = new ArrayList<DictPositionLevel>();
                                Log.d("TAG", "positionLevels" +
                                        jsonObject.getString("message"));
                                JSONArray jsonArray = jsonObject.getJSONArray(
                                        "data");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject item = jsonArray.optJSONObject(
                                            i);
                                    DictPositionLevel positionLevel
                                            = new DictPositionLevel();
                                    positionLevel.setValue(
                                            item.getString("value"));
                                    positionLevel.setName(
                                            item.getString("name"));
                                    positionLevels.add(positionLevel);
                                }
                                db.deleteAll(DictPositionLevel.class);
                                db.saveAll(positionLevels);
                                updateDictVersion(
                                        jsonObject.getJSONObject("other")
                                                  .getInt("version"),
                                        "position_level");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
                else if (resultCode == URL_GET_DICTLANG) {
                    mService.execute(new Runnable() {
                        @Override public void run() {
                            try {
                                List<DictLang> langs
                                        = new ArrayList<DictLang>();
                                Log.d("TAG", "lang" +
                                        jsonObject.getString("message"));
                                JSONArray jsonArray = jsonObject.getJSONArray(
                                        "data");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject item = jsonArray.optJSONObject(
                                            i);
                                    DictLang dictLang = new DictLang();
                                    dictLang.setId(item.getInt("id"));
                                    dictLang.setName(item.getString("name"));
                                    langs.add(dictLang);
                                }
                                db.deleteAll(DictLang.class);
                                db.saveAll(langs);
                                updateDictVersion(
                                        jsonObject.getJSONObject("other")
                                                  .getInt("version"),
                                        "language");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
                else if (resultCode == URL_GET_DICTLANGLEVEL) {
                    mService.execute(new Runnable() {
                        @Override public void run() {
                            try {
                                List<DictLangLevel> langLevels
                                        = new ArrayList<DictLangLevel>();
                                Log.d("TAG", "langLevels" +
                                        jsonObject.getString("message"));
                                JSONArray jsonArray = jsonObject.getJSONArray(
                                        "data");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject item = jsonArray.optJSONObject(
                                            i);
                                    DictLangLevel langLevel
                                            = new DictLangLevel();
                                    langLevel.setValue(item.getString("value"));
                                    langLevel.setName(item.getString("name"));
                                    langLevels.add(langLevel);
                                }
                                db.deleteAll(DictLangLevel.class);
                                db.saveAll(langLevels);
                                updateDictVersion(
                                        jsonObject.getJSONObject("other")
                                                  .getInt("version"),
                                        "language_level");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
                else if (resultCode == URL_GET_DICTWORKYEAR) {
                    mService.execute(new Runnable() {
                        @Override public void run() {
                            try {
                                List<DictWorkYear> workYears
                                        = new ArrayList<DictWorkYear>();
                                Log.d("TAG", "workYears" +
                                        jsonObject.getString("message"));
                                JSONArray jsonArray = jsonObject.getJSONArray(
                                        "data");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject item = jsonArray.optJSONObject(
                                            i);
                                    DictWorkYear workYear = new DictWorkYear();
                                    workYear.setValue(item.getString("value"));
                                    workYear.setName(item.getString("name"));
                                    workYears.add(workYear);
                                }
                                db.deleteAll(DictWorkYear.class);
                                db.saveAll(workYears);
                                updateDictVersion(
                                        jsonObject.getJSONObject("other")
                                                  .getInt("version"),
                                        "work_year");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
                else if (resultCode == URL_GET_DICTEDULEVEL) {
                    mService.execute(new Runnable() {
                        @Override public void run() {
                            try {
                                List<DictEduLevel> eduLevels
                                        = new ArrayList<DictEduLevel>();
                                Log.d("TAG", "eduLevels" +
                                        jsonObject.getString("message"));
                                JSONArray jsonArray = jsonObject.getJSONArray(
                                        "data");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject item = jsonArray.optJSONObject(
                                            i);
                                    DictEduLevel eduLevel = new DictEduLevel();
                                    eduLevel.setValue(item.getString("value"));
                                    eduLevel.setName(item.getString("name"));
                                    eduLevels.add(eduLevel);
                                }
                                db.deleteAll(DictEduLevel.class);
                                db.saveAll(eduLevels);
                                updateDictVersion(
                                        jsonObject.getJSONObject("other")
                                                  .getInt("version"),
                                        "education");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
                else if (resultCode == URL_GET_DICTEDUDEGREE) {
                    mService.execute(new Runnable() {
                        @Override public void run() {
                            try {
                                List<DictEduDegree> eduDegrees
                                        = new ArrayList<DictEduDegree>();
                                Log.d("TAG", "eduDegrees" +
                                        jsonObject.getString("message"));
                                JSONArray jsonArray = jsonObject.getJSONArray(
                                        "data");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject item = jsonArray.optJSONObject(
                                            i);
                                    DictEduDegree eduDegree
                                            = new DictEduDegree();
                                    eduDegree.setValue(item.getString("value"));
                                    eduDegree.setName(item.getString("name"));
                                    eduDegrees.add(eduDegree);
                                }
                                db.deleteAll(DictEduDegree.class);
                                db.saveAll(eduDegrees);
                                updateDictVersion(
                                        jsonObject.getJSONObject("other")
                                                  .getInt("version"), "degree");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
                else if (resultCode == URL_GET_DICTSALARY) {
                    mService.execute(new Runnable() {
                        @Override public void run() {
                            try {
                                List<DictSalary> salaries
                                        = new ArrayList<DictSalary>();
                                Log.d("TAG", "salaries" +
                                        jsonObject.getString("message"));
                                JSONArray jsonArray = jsonObject.getJSONArray(
                                        "data");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject item = jsonArray.optJSONObject(
                                            i);
                                    DictSalary salary = new DictSalary();
                                    salary.setValue(item.getString("value"));
                                    salary.setName(item.getString("name"));
                                    salaries.add(salary);
                                }
                                db.deleteAll(DictEduDegree.class);
                                db.saveAll(salaries);
                                updateDictVersion(
                                        jsonObject.getJSONObject("other")
                                                  .getInt("version"), "salary");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
                else if (resultCode == URL_GET_DICTUSERRELATION) {
                    mService.execute(new Runnable() {
                        @Override public void run() {
                            try {
                                List<DictUserRelation> userRelations
                                        = new ArrayList<DictUserRelation>();
                                Log.d("TAG", "userRelations" +
                                        jsonObject.getString("message"));
                                JSONArray jsonArray = jsonObject.getJSONArray(
                                        "data");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject item = jsonArray.optJSONObject(
                                            i);
                                    DictUserRelation userRelation
                                            = new DictUserRelation();
                                    userRelation.setValue(
                                            item.getString("value"));
                                    userRelation.setName(
                                            item.getString("name"));
                                    userRelations.add(userRelation);
                                }
                                db.deleteAll(DictUserRelation.class);
                                db.saveAll(userRelations);
                                updateDictVersion(
                                        jsonObject.getJSONObject("other")
                                                  .getInt("version"),
                                        "user_relation");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
                else if (resultCode == URL_GET_CIRCLETYPE) {
                    mService.execute(new Runnable() {
                        @Override public void run() {
                            try {
                                List<DictCircleType> circleTypes
                                        = new ArrayList<DictCircleType>();
                                Log.d("TAG", "circleTypes" +
                                        jsonObject.getString("message"));
                                JSONArray jsonArray = jsonObject.getJSONArray(
                                        "data");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject item = jsonArray.optJSONObject(
                                            i);
                                    DictCircleType circleType
                                            = new DictCircleType();
                                    circleType.setValue(
                                            item.getString("value"));
                                    circleType.setName(item.getString("name"));
                                    circleTypes.add(circleType);
                                }
                                db.deleteAll(DictUserRelation.class);
                                db.saveAll(circleTypes);
                                updateDictVersion(
                                        jsonObject.getJSONObject("other")
                                                  .getInt("version"),
                                        "circle_type");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
                else if (resultCode == URL_GET_DICTHOTCITY) {
                    mService.execute(new Runnable() {
                        @Override public void run() {
                            try {
                                List<DictHotCity> hotCities
                                        = new ArrayList<DictHotCity>();
                                Log.d("TAG", "hotCities" +
                                        jsonObject.getString("message"));
                                JSONArray jsonArray = jsonObject.getJSONArray(
                                        "data");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject item = jsonArray.optJSONObject(
                                            i);
                                    DictHotCity hotCity = new DictHotCity();
                                    hotCity.setValue(item.getString("value"));
                                    hotCity.setName(item.getString("name"));
                                    hotCities.add(hotCity);
                                }
                                db.deleteAll(DictHotCity.class);
                                db.saveAll(hotCities);
                                updateDictVersion(
                                        jsonObject.getJSONObject("other")
                                                  .getInt("version"),
                                        "hot_city");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
                else if (resultCode == URL_GET_DICTBANK) {
                    mService.execute(new Runnable() {
                        @Override public void run() {
                            try {
                                List<DictBank> banks
                                        = new ArrayList<DictBank>();
                                Log.d("TAG", "banks" +
                                        jsonObject.getString("message"));
                                JSONArray jsonArray = jsonObject.getJSONArray(
                                        "data");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject item = jsonArray.optJSONObject(
                                            i);
                                    DictBank bank = new DictBank();
                                    bank.setValue(item.getString("value"));
                                    bank.setName(item.getString("name"));
                                    banks.add(bank);
                                }
                                db.deleteAll(DictBank.class);
                                db.saveAll(banks);
                                updateDictVersion(
                                        jsonObject.getJSONObject("other")
                                                  .getInt("version"), "bank");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
                else if (resultCode == URL_GET_DICTEVENTTHEME) {
                    mService.execute(new Runnable() {
                        @Override public void run() {
                            try {
                                List<DictEventTheme> eventThemes
                                        = new ArrayList<DictEventTheme>();
                                Log.d("TAG", "eventThemes" +
                                        jsonObject.getString("message"));
                                JSONArray jsonArray = jsonObject.getJSONArray(
                                        "data");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject item = jsonArray.optJSONObject(
                                            i);
                                    DictEventTheme eventTheme
                                            = new DictEventTheme();
                                    eventTheme.setValue(
                                            item.getString("value"));
                                    eventTheme.setName(item.getString("name"));
                                    eventThemes.add(eventTheme);
                                }
                                db.deleteAll(DictEventTheme.class);
                                db.saveAll(eventThemes);
                                updateDictVersion(
                                        jsonObject.getJSONObject("other")
                                                  .getInt("version"),
                                        "event_theme");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
                else if (resultCode == URL_GET_DICTEVENTSTYLE) {
                    mService.execute(new Runnable() {
                        @Override public void run() {
                            try {
                                List<DictEventStyle> eventStyles
                                        = new ArrayList<DictEventStyle>();
                                Log.d("TAG", "eventStyles" +
                                        jsonObject.getString("message"));
                                JSONArray jsonArray = jsonObject.getJSONArray(
                                        "data");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject item = jsonArray.optJSONObject(
                                            i);
                                    DictEventStyle eventStyle
                                            = new DictEventStyle();
                                    eventStyle.setValue(
                                            item.getString("value"));
                                    eventStyle.setName(item.getString("name"));
                                    eventStyles.add(eventStyle);
                                }
                                db.deleteAll(DictEventStyle.class);
                                db.saveAll(eventStyles);
                                updateDictVersion(
                                        jsonObject.getJSONObject("other")
                                                  .getInt("version"),
                                        "event_style");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
                else if (resultCode == URL_GET_DICTPROJECTTYPE) {
                    mService.execute(new Runnable() {
                        @Override public void run() {
                            try {
                                List<DictProjectType> projectTypes
                                        = new ArrayList<DictProjectType>();
                                Log.d("TAG", "projectTypes" +
                                        jsonObject.getString("message"));
                                JSONArray jsonArray = jsonObject.getJSONArray(
                                        "data");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject item = jsonArray.optJSONObject(
                                            i);
                                    DictProjectType projectType
                                            = new DictProjectType();
                                    projectType.setValue(
                                            item.getString("value"));
                                    projectType.setName(item.getString("name"));
                                    projectTypes.add(projectType);
                                }
                                db.deleteAll(DictProjectType.class);
                                db.saveAll(projectTypes);
                                updateDictVersion(
                                        jsonObject.getJSONObject("other")
                                                  .getInt("version"),
                                        "project_type");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
                else if (resultCode == URL_GET_DICTINTENTION) {
                    mService.execute(new Runnable() {
                        @Override public void run() {
                            try {
                                List<DictIntention> intentions
                                        = new ArrayList<DictIntention>();
                                Log.d("TAG", "intentions" +
                                        jsonObject.getString("message"));
                                JSONArray jsonArray = jsonObject.getJSONArray(
                                        "data");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject item = jsonArray.optJSONObject(
                                            i);
                                    DictIntention intention
                                            = new DictIntention();
                                    intention.setValue(item.getString("value"));
                                    intention.setName(item.getString("name"));
                                    intentions.add(intention);
                                }
                                db.deleteAll(DictIntention.class);
                                db.saveAll(intentions);
                                updateDictVersion(
                                        jsonObject.getJSONObject("other")
                                                  .getInt("version"),
                                        "intention");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
                else if (resultCode == URL_GET_DICTPROJECTPLAN) {
                    mService.execute(new Runnable() {
                        @Override public void run() {
                            try {
                                List<DictProjectPlan> projectPlans
                                        = new ArrayList<DictProjectPlan>();
                                Log.d("TAG", "projectPlans" +
                                        jsonObject.getString("message"));
                                JSONArray jsonArray = jsonObject.getJSONArray(
                                        "data");
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject item = jsonArray.optJSONObject(
                                            i);
                                    DictProjectPlan projectPlan
                                            = new DictProjectPlan();
                                    projectPlan.setValue(
                                            item.getString("value"));
                                    projectPlan.setName(item.getString("name"));
                                    projectPlans.add(projectPlan);
                                }
                                db.deleteAll(DictProjectPlan.class);
                                db.saveAll(projectPlans);
                                updateDictVersion(
                                        jsonObject.getJSONObject("other")
                                                  .getInt("version"),
                                        "project_plan");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }else {
                Log.d("TAG",jsonObject.getString("message")+"+"+jsonObject
                        .getInt("code"));
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
