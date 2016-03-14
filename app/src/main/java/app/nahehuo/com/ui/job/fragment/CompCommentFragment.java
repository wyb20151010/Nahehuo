package app.nahehuo.com.ui.job.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.adapter.CompCommentAdapter;
import app.nahehuo.com.bean.CompanyCommentDict;
import app.nahehuo.com.bean.TagItem;
import app.nahehuo.com.ui.job.CompanyDetailActivity;
import app.nahehuo.com.ui.job.WriteInterExpActivity;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WYB on 2016/1/11.
 */
public class CompCommentFragment extends Fragment
        implements TagFlowLayout.OnTagClickListener, View.OnClickListener {

    private TagFlowLayout tfl_company_tag;
    private List<TagItem> mTagItems = new ArrayList<>();
    private Context mContext;
    private List<CompanyCommentDict> mCommentDicts = new ArrayList<>();
    private CompCommentAdapter mCompCommentAdapter;
    private RecyclerView recycler_view;
    private Button btn_comp_comment;
    private CompanyDetailActivity mCompanyDetailActivity;


    @Override public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (activity instanceof CompanyDetailActivity) {
            mCompanyDetailActivity = (CompanyDetailActivity) activity;
        }
        else {
            throw new IllegalArgumentException(
                    "The activity must be a CompanyDetailActivity !");
        }
    }


    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_comp_comment, null);
        mContext = getActivity();
        btn_comp_comment = (Button) v.findViewById(R.id.btn_comp_comment);
        btn_comp_comment.setOnClickListener(this);
        initData();
        initTagFlowLayout(v);
        initPlv(v);
        return v;
    }


    private void initPlv(View v) {
        mCompCommentAdapter = new CompCommentAdapter(mCommentDicts, mContext);
        recycler_view = (RecyclerView) v.findViewById(R.id.recycler_view);
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler_view.setLayoutManager(manager);
        recycler_view.setAdapter(mCompCommentAdapter);
    }


    private void initData() {
        CompanyCommentDict commentDict = new CompanyCommentDict();
        commentDict.setAvater(
                "http://www.nahehuo.com/thumb/6/9/b8/32410_middle.jpg");
        commentDict.setScore("描述相符: 4.5  工作环境: 5.0 工作氛围: 5.0");
        commentDict.setUsername("张三李四");
        commentDict.setComment_content(
                "庙堂之上，朽木为官；殿陛之间，禽兽食禄。以至狼心狗肺之辈汹汹当朝，奴颜婢膝之徒纷纷秉政，以致社稷变为丘墟，苍生饱受涂炭之苦");
        commentDict.setComment_time("2015-12-12 15:33");
        mCommentDicts.add(commentDict);
        CompanyCommentDict commentDict1 = new CompanyCommentDict();
        commentDict1.setAvater(
                "http://www.nahehuo.com/thumb/6/9/b8/32410_middle.jpg");
        commentDict1.setScore("描述相符: 4.5  工作环境: 5.0 工作氛围: 5.0");
        commentDict1.setUsername("王耀彬");
        commentDict1.setComment_content("我还需要继续努力啊，才能进步");
        commentDict1.setComment_time("2015-12-12 15:33");
        mCommentDicts.add(commentDict1);

        CompanyCommentDict commentDict2 = new CompanyCommentDict();
        commentDict2.setAvater(
                "http://www.nahehuo.com/thumb/6/9/b8/32410_middle.jpg");
        commentDict2.setScore("描述相符: 4.5  工作环境: 5.0 工作氛围: 5.0");
        commentDict2.setUsername("李蒙");
        commentDict2.setComment_content(
                "庙堂之上，朽木为官；殿陛之间，禽兽食禄。以至狼心狗肺之辈汹汹当朝，奴颜婢膝之徒纷纷秉政，以致社稷变为丘墟，苍生饱受涂炭之苦 ");
        commentDict2.setComment_time("2015-12-12 15:33");
        mCommentDicts.add(commentDict2);
        CompanyCommentDict commentDict3 = new CompanyCommentDict();
        commentDict3.setAvater(
                "http://www.nahehuo.com/thumb/6/9/b8/32410_middle.jpg");
        commentDict3.setScore("描述相符: 4.5  工作环境: 5.0 工作氛围: 5.0");
        commentDict3.setUsername("李蒙");
        commentDict3.setComment_content(
                "庙堂之上，朽木为官；殿陛之间，禽兽食禄。以至狼心狗肺之辈汹汹当朝，奴颜婢膝之徒纷纷秉政，以致社稷变为丘墟，苍生饱受涂炭之苦 ");
        commentDict3.setComment_time("2015-12-12 15:33");
        mCommentDicts.add(commentDict3);
    }


    private void initTagFlowLayout(View v) {
        mTagItems.add(new TagItem("领导好", 3, true));
        mTagItems.add(new TagItem("免费培训", 999, true));
        mTagItems.add(new TagItem("节日礼物", 3, true));
        mTagItems.add(new TagItem("下午茶", 100, true));
        tfl_company_tag = (TagFlowLayout) v.findViewById(R.id.tfl_company_tag);
        tfl_company_tag.setAdapter(new TagAdapter(mTagItems) {
            @Override
            public View getView(FlowLayout parent, int position, Object o) {
                TagItem item = mTagItems.get(position);
                LayoutInflater inflater
                        = (LayoutInflater) mContext.getSystemService(
                        Context.LAYOUT_INFLATER_SERVICE);
                View v = inflater.inflate(R.layout.item_tag_red_gray, parent,
                        false);
                TextView tv_tag = (TextView) v.findViewById(R.id.tv_tag);
                TextView tv_number = (TextView) v.findViewById(R.id.tv_number);
                tv_tag.setText(item.getContent());
                tv_number.setText(item.getNumber() + "");
                return v;
            }
        });
        tfl_company_tag.setOnTagClickListener(this);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    @Override
    public boolean onTagClick(View view, int position, FlowLayout parent) {
        TextView textView = (TextView) view.findViewById(R.id.tv_number);
        TagItem item = mTagItems.get(position);
        boolean unselected = item.isUnselected();
        if (unselected) {
            textView.setText((item.getNumber() + 1) + "");
            item.setNumber(item.getNumber() + 1);
            unselected = false;
            item.setUnselected(unselected);
        }
        else {
            textView.setText((item.getNumber() - 1) + "");
            item.setNumber(item.getNumber() - 1);
            unselected = true;
            item.setUnselected(unselected);
        }
        return true;
    }


    @Override public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_comp_comment:
                startActivity(
                        new Intent(mContext, WriteInterExpActivity.class));
                mCompanyDetailActivity.overridePendingTransition(
                        R.anim.push_left_in, R.anim.push_left_out);
                break;
        }
    }
}
