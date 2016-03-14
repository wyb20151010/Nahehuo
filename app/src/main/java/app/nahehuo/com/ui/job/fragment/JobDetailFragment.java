package app.nahehuo.com.ui.job.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.ui.job.JobDeliverySuccessActivity;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

/**
 * Created by WYB on 2016/1/8.
 */
public class JobDetailFragment extends Fragment implements View.OnClickListener{

    private TagFlowLayout tfl_job_detail;
    private String job_requ
            = "我们寻找对服务业产品开发感兴趣的工程师和设计师。希望应聘者关注互联网产品，热衷于改善用户体验，注重外观设计和交互设计；同时学习能力强，能快速适应环境，并具有基本的英语读写水平。来到天联以后将参与以下一项或多项任务：<br/><br/>1. 设计简单易用功能完善的用户界面<br/><br/>2. 使用HTML5开发iOS和Android手机应用<br/><br/>3. 优化页面设计和性能，减少与原生应用在外观和感觉上的差异<br/><br/>技术要求<br/><br/>1. 熟悉 HTML5和CSS3，了解手机浏览器最前沿的开发技术<br/><br/>2. 熟悉JavaScript，了解Backbone.js, Ember.js等前台MVC设计模式<br/><br/>3. 重视细节，代码规范易读<br/><br/>4. 独立开发过网页或者手机应用者优先";
    private TextView tv_job_re;
    private Button btn_delivery_resume;
    private Context mContext;


    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_job_detail, null);
        mContext = getActivity();
        tv_job_re = (TextView) v.findViewById(R.id.tv_job_re);
        btn_delivery_resume= (Button) v.findViewById(R.id.btn_delivery_resume);
        btn_delivery_resume.setOnClickListener(this);
        tv_job_re.setText(Html.fromHtml(job_requ));
        initTagFlowLayout(v);
        return v;
    }


    private void initTagFlowLayout(View v) {
        tfl_job_detail = (TagFlowLayout) v.findViewById(R.id.tfl_job_detail);
        TagAdapter<String> adapter = new TagAdapter<String>(
                new String[] { "全职", "技术研发", "本科以上", "五险一金", "美女鼓励" }) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                LayoutInflater inflater
                        = (LayoutInflater) mContext.getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                View v = inflater.inflate(R.layout.item_tag, parent, false);
                TextView tv_tag = (TextView) v.findViewById(R.id.tv_tag);
                tv_tag.setText(s);
                return v;
            }
        };
        tfl_job_detail.setAdapter(adapter);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.job_detail, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_delivery_resume:
                startActivity(new Intent(mContext, JobDeliverySuccessActivity
                        .class));
                break;
        }
    }
}
