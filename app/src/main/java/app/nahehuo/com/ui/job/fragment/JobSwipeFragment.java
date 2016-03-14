package app.nahehuo.com.ui.job.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import app.nahehuo.com.R;
import app.nahehuo.com.base.GlobalVariables;
import app.nahehuo.com.bean.NetRecomJob;
import app.nahehuo.com.bean.RecomJob;
import app.nahehuo.com.network.GsonCallBack;
import app.nahehuo.com.network.JsonObjectCallback;
import app.nahehuo.com.ui.MainActivity;
import app.nahehuo.com.ui.job.JobDetailActivity;
import app.nahehuo.com.ui.job.JobPositionActivity;
import app.nahehuo.com.ui.job.JobSubscribeActivity;
import app.nahehuo.com.util.MyToast;
import app.nahehuo.com.view.CardItemView;
import app.nahehuo.com.view.CardSlidePanel;
import com.victor.loading.rotate.RotateLoading;
import com.zhy.http.okhttp.OkHttpUtils;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by WYB on 2015/12/24.
 */
public class JobSwipeFragment extends Fragment implements View.OnClickListener {

    private Context mContext;
    private static final int JOB_RECOMMEND = 0;
    private static final int JOB_COLLECT = 1;

    private CardSlidePanel.CardSwitchListener cardSwitchListener;
    private CardSlidePanel.CardSwipeListenter mCardSwipeListenter;
    private CardSlidePanel slidePanel;
    private RotateLoading mRotateLoading;
    private ImageView iv_pass, iv_cancel;
    private LinearLayout ll_last_content;
    private Button btn_change_job_sub, btn_see_all_job;
    private MainActivity mainActivity;
    private List<RecomJob> recomJobs = new ArrayList<RecomJob>();
    private int mIndex;
    private Handler mHandler = new Handler() {
        @Override public void handleMessage(Message msg) {
            switch (msg.what) {
                case JOB_RECOMMEND:
                    findJobRecom();
                    break;
                case JOB_COLLECT:
                    jobCollect();
                    break;
            }
            super.handleMessage(msg);
        }
    };


    private void jobCollect() {
        OkHttpUtils.get()
                   .url(GlobalVariables.JOB_COLLECT)
                   .addParams("access_token", GlobalVariables.access_token)
                   .addParams("device", GlobalVariables.device)
                   .addParams("jid", recomJobs.get(mIndex).getJid() + "")
                   .build()
                   .execute(new JsonObjectCallback() {
                       @Override public void onResponse(String response) {
                           try {
                               JSONObject jsonObject = new JSONObject(response);
                               Log.d("TAG",
                                       jsonObject.getString("message") + ":" +
                                               jsonObject.getInt("code"));
                           } catch (JSONException e) {
                               e.printStackTrace();
                           }

                           super.onResponse(response);
                       }
                   });
    }


    private void findJobRecom() {


        Log.d("TAG",GlobalVariables.access_token+"find_access");
        recomJobs.clear();
        OkHttpUtils.get()
                   .url(GlobalVariables.JOB_RECOMMEND)
                   .addParams("access_token", GlobalVariables.access_token)
                   .addParams("device", GlobalVariables.device)
                   .build()
                   .execute(new GsonCallBack<NetRecomJob>(NetRecomJob.class) {
                       @Override public void onResponse(NetRecomJob response) {
                           Log.d("TAG",response.getMessage());
                           if (response.getCode() == 200) {

                               for (int i = 0;
                                    i < response.getData().size();
                                    i++) {
                                   RecomJob recomJob = new RecomJob();
                                   recomJob.setPosition(response.getData()
                                                                .get(i)
                                                                .getPosition());
                                   recomJob.setJid(
                                           response.getData().get(i).getJid());
                                   recomJob.setType(
                                           response.getData().get(i).getType());
                                   recomJob.setProv(
                                           response.getData().get(i).getProv());
                                   recomJob.setCity(
                                           response.getData().get(i).getCity());
                                   recomJob.setWorkexp(response.getData()
                                                               .get(i)
                                                               .getWorkexp());
                                   recomJob.setWagemax(response.getData()
                                                               .get(i)
                                                               .getWagemax());
                                   recomJob.setWagemin(response.getData()
                                                               .get(i)
                                                               .getWagemin());
                                   recomJob.setEdu(
                                           response.getData().get(i).getEdu());
                                   recomJob.setAttraction(response.getData()
                                                                  .get(i)
                                                                  .getAttraction());
                                   recomJob.setCid(
                                           response.getData().get(i).getCid());
                                   recomJob.setCompany(response.getData()
                                                               .get(i)
                                                               .getCompany());
                                   recomJob.setLogo(
                                           response.getData().get(i).getLogo());
                                   recomJob.setCompany(response.getData()
                                                               .get(i)
                                                               .getCompany());
                                   recomJob.setCstatus(response.getData()
                                                               .get(i)
                                                               .getCstatus());
                                   recomJob.setFinancle(response.getData()
                                                                .get(i)
                                                                .getFinancle());
                                   recomJob.setSize(
                                           response.getData().get(i).getSize());
                                   recomJob.setPositiontype(response.getData()
                                                                    .get(i)
                                                                    .getPositiontype());
                                   recomJob.setIndustry(response.getData()
                                                                .get(i)
                                                                .getIndustry());
                                   recomJobs.add(recomJob);
                               }
                               mRotateLoading.stop();
                               slidePanel.setVisibility(View.VISIBLE);
                               slidePanel.fillData(recomJobs);
                           }
                           else {
                               MyToast.showToast(mContext,
                                       response.getMessage());
                           }
                           super.onResponse(response);
                       }
                   });
        /* mRotateLoading.stop();
                                slidePanel.setVisibility(View.INVISIBLE);
                                ll_last_content.setVisibility(View.VISIBLE);*/
    }


    @Override public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof MainActivity) {
            mainActivity = (MainActivity) activity;
        }
        else {
            throw new IllegalArgumentException(
                    "The activity must be a MainActivity !");
        }
    }


    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_job_swipe, null);
        mContext = getActivity();
        slidePanel = (CardSlidePanel) v.findViewById(R.id.image_slide_panel);
        mRotateLoading = (RotateLoading) v.findViewById(R.id.loading);
        iv_pass = (ImageView) v.findViewById(R.id.iv_like);
        iv_cancel = (ImageView) v.findViewById(R.id.iv_notlike);
        ll_last_content = (LinearLayout) v.findViewById(R.id.ll_lastcontent);
        btn_change_job_sub = (Button) v.findViewById(R.id.btn_change_job_sub);
        btn_change_job_sub.setOnClickListener(this);
        btn_see_all_job = (Button) v.findViewById(R.id.btn_see_all_job);
        btn_see_all_job.setOnClickListener(this);
        mRotateLoading.start();

       /* if (!TextUtils.isEmpty(GlobalVariables.UID)) {*/
            mHandler.sendEmptyMessage(JOB_RECOMMEND);
        /*}
        else {
            startActivity(new Intent(mContext, LoginActivity.class));
            mainActivity.overridePendingTransition(R.anim.push_left_in,
                    R.anim.push_left_out);
        }*/
        return v;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mCardSwipeListenter = new CardSlidePanel.CardSwipeListenter() {
            @Override public void onScroll(float scrollProgressPercent) {
                CardItemView cardItemView
                        = (CardItemView) slidePanel.getSelectedView();
                if (scrollProgressPercent < -0.20f) {

                    cardItemView.findViewById(R.id.iv_notlike)
                                .setAlpha(Math.abs(scrollProgressPercent));
                    cardItemView.findViewById(R.id.ll_content)
                                .setAlpha(1 -
                                        Math.abs(scrollProgressPercent) / 1.5f);
                }
                else if (scrollProgressPercent > 0.20f) {

                    cardItemView.findViewById(R.id.iv_like)
                                .setAlpha(Math.abs(scrollProgressPercent));
                    cardItemView.findViewById(R.id.ll_content)
                                .setAlpha(1 -
                                        Math.abs(scrollProgressPercent) / 1.5f);
                }
                else {
                    cardItemView.findViewById(R.id.iv_notlike).setAlpha(0);
                    cardItemView.findViewById(R.id.iv_like).setAlpha(0);
                    cardItemView.findViewById(R.id.ll_content).setAlpha(1);
                }
            }
        };
        cardSwitchListener = new CardSlidePanel.CardSwitchListener() {

            @Override public void onShow(int index) {
                mIndex = index;
            }


            @Override public void onCardVanish(int index, int type) {
                if (type == CardSlidePanel.VANISH_TYPE_RIGHT) {
                    mHandler.sendEmptyMessage(JOB_COLLECT);
                }
            }


            @Override public void onItemClick(View cardView, int index) {
                Log.d("CardFragment", "卡片点击-");
            }


            @Override public void onFinished() {
                Log.d("CardFragment", "finished");
                slidePanel.setVisibility(View.INVISIBLE);
                ll_last_content.setVisibility(View.VISIBLE);
            }
        };
        slidePanel.setCardSwitchListener(cardSwitchListener);
        slidePanel.setCardSwipeListenter(mCardSwipeListenter);
        List<CardItemView> cardItemViews = slidePanel.getSelectedViews();
        for (int i = 0; i < cardItemViews.size(); i++) {
            cardItemViews.get(i)
                         .findViewById(R.id.ll_content)
                         .setOnClickListener(new View.OnClickListener() {
                             @Override public void onClick(View v) {
                                 startActivityForResult(new Intent(mContext,
                                         JobDetailActivity.class), 1);
                             }
                         });
        }
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_change_job_sub:
                startActivityForResult(
                        new Intent(mContext, JobSubscribeActivity.class), 1);
                mainActivity.overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
                break;
            case R.id.btn_see_all_job:
                startActivity(new Intent(mContext, JobPositionActivity.class));
                mainActivity.overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
                break;
        }
    }
}
