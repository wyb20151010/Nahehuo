package app.nahehuo.com.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.application.MyApplication;
import app.nahehuo.com.bean.RecomJob;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import java.util.ArrayList;
import java.util.List;

/**
 * 卡片View项
 *
 * @author xmuSistone
 */
@SuppressLint("NewApi") public class CardItemView extends FrameLayout {

    public ImageView iv_title;
    private TextView tv_position;
    private TextView tv_company;
    private TextView tv_wage;
    private TextView tv_work_year;
    private TextView tv_city;
    private TagFlowLayout fl_job_detailone;
    private TagFlowLayout fl_job_detailtwo;
    private FloatingActionButton fab_add;
    private List<String> needs = new ArrayList<>();
    private List<String> wants = new ArrayList<>();
    private String jobType, work_exp, edu_exp, finance;
    private Context mContext;


    public CardItemView(Context context) {
        this(context, null);
    }


    public CardItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public CardItemView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        inflate(context, R.layout.item_card_job, this);
        mContext = context;
        iv_title = (ImageView) findViewById(R.id.iv_title);
        tv_position = (TextView) findViewById(R.id.tv_position);
        tv_company = (TextView) findViewById(R.id.tv_company);
        tv_wage = (TextView) findViewById(R.id.tv_wage);
        tv_work_year = (TextView) findViewById(R.id.tv_work_year);
        tv_city = (TextView) findViewById(R.id.tv_city);
        fl_job_detailone = (TagFlowLayout) findViewById(R.id.fl_job_detailone);
        fl_job_detailtwo = (TagFlowLayout) findViewById(R.id.fl_job_detailtwo);
    }


    public void fillData(RecomJob itemData) {
        jobType = itemData.getType() == 1 ? "全职" : "实习";
        work_exp = itemData.getWorkexp().equals("0")
                   ? "工作经验不限"
                   : itemData.getWorkexp();
        edu_exp = itemData.getEdu().equals("0") ? "教育经验不限" : itemData.getEdu();
        finance = itemData.getFinancle().equals("0")
                  ? "未融资"
                  : itemData.getFinancle();
        needs.add(jobType);
        needs.add(itemData.getPositiontype());
        needs.add(edu_exp);
        needs.add(itemData.getAttraction());

        wants.add(itemData.getIndustry());
        wants.add(itemData.getSize());
        wants.add(finance);
        ImageLoader.getInstance().displayImage(itemData.getLogo(), iv_title,
                MyApplication.getDisplayDefaultOption());
        tv_position.setText(itemData.getPosition());
        tv_company.setText(itemData.getCompany() + "");
        tv_wage.setText(
                itemData.getWagemin() + "k-" + itemData.getWagemax() + "k");
        tv_city.setText(itemData.getCity());
        tv_work_year.setText(work_exp);
        fl_job_detailone.setAdapter(new TagAdapter(needs) {
            @Override
            public View getView(FlowLayout parent, int position, Object o) {
                TextView textView = (TextView) LayoutInflater.from(mContext)
                                                             .inflate(
                                                                     R.layout.item_tag_bule1,
                                                                     parent,
                                                                     false);
                textView.setText(needs.get(position));
                return textView;
            }
        });
        fl_job_detailtwo.setAdapter(new TagAdapter(wants) {
            @Override
            public View getView(FlowLayout parent, int position, Object o) {
                TextView textView = (TextView) LayoutInflater.from(mContext)
                                                             .inflate(
                                                                     R.layout.item_tag_dark_green,
                                                                     parent,
                                                                     false);
                textView.setText(wants.get(position));
                return textView;
            }
        });
    }
}
