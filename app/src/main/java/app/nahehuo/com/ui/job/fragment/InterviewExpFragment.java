package app.nahehuo.com.ui.job.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import app.nahehuo.com.R;
import app.nahehuo.com.adapter.InterviewExpAdapter;
import app.nahehuo.com.bean.CompanyCommentDict;
import app.nahehuo.com.ui.job.JobDetailActivity;
import app.nahehuo.com.ui.job.WriteInterExpActivity2;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WYB on 2016/1/8.
 */
public class InterviewExpFragment extends Fragment
        implements View.OnClickListener {

    private PullToRefreshListView plv_inter_exp;
    private InterviewExpAdapter mInterviewExpAdapter;
    private List<CompanyCommentDict> mCommentDicts = new ArrayList<>();
    private Context mContext;
    private FloatingActionButton fab_comment;
    private JobDetailActivity mJobDetailActivity;


    @Override public void onAttach(Activity activity) {
        if (activity instanceof JobDetailActivity) {
            mJobDetailActivity = (JobDetailActivity) activity;
        }
        else {
            throw new IllegalArgumentException(
                    "The activity must be a JobDetailActivity !");
        }
        super.onAttach(activity);
    }


    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_interview_exp, null);
        mContext = getActivity();
        plv_inter_exp = (PullToRefreshListView) v.findViewById(
                R.id.plv_inter_exp);
        initData();
        initPlv();
        fab_comment = (FloatingActionButton) v.findViewById(R.id.fab_comment);
        fab_comment.setOnClickListener(this);
        return v;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        setHasOptionsMenu(true);
    }


    private void initData() {
        CompanyCommentDict commentDict = new CompanyCommentDict();
        commentDict.setAvater(
                "http://www.nahehuo.com/thumb/6/9/b8/32410_middle.jpg");
        commentDict.setScore("职位相符: 4.5  面试打分: 4.0");
        commentDict.setUsername("张三李四");
        commentDict.setState(0);
        commentDict.setComment_title("[ 面试官很nice,HR很sb]");
        commentDict.setComment_content(
                "研究录取了患者治疗前及治疗三周后的临床病徵、临床乾燥症的严重度评分和皮肤镜图像。");
        commentDict.setComment_time("2015-12-12 15:33");
        mCommentDicts.add(commentDict);
        CompanyCommentDict commentDict1 = new CompanyCommentDict();
        commentDict1.setAvater(
                "http://www.nahehuo.com/thumb/6/9/b8/32410_middle.jpg");
        commentDict1.setScore("职位相符: 4.5  面试打分: 4.0");
        commentDict1.setUsername("王耀彬");
        commentDict1.setState(1);
        commentDict1.setComment_title("[ 面试官很hot]");
        commentDict1.setComment_content("我还需要继续努力啊，才能进步");
        commentDict1.setComment_time("2015-12-12 15:33");
        mCommentDicts.add(commentDict1);

        CompanyCommentDict commentDict2 = new CompanyCommentDict();
        commentDict2.setAvater(
                "http://www.nahehuo.com/thumb/6/9/b8/32410_middle.jpg");
        commentDict2.setScore("职位相符: 4.5  面试打分: 5.0");
        commentDict2.setUsername("李蒙");
        commentDict2.setState(2);
        commentDict2.setComment_title("[ 太无语了我要哭了]");
        commentDict2.setComment_content(
                "庙堂之上，朽木为官；殿陛之间，禽兽食禄。以至狼心狗肺之辈汹汹当朝，奴颜婢膝之徒纷纷秉政，以致社稷变为丘墟，苍生饱受涂炭之苦 ");
        commentDict2.setComment_time("2015-12-12 15:33");
        mCommentDicts.add(commentDict2);
    }


    private void initPlv() {
        mInterviewExpAdapter = new InterviewExpAdapter(mCommentDicts, mContext);
        plv_inter_exp.setMode(PullToRefreshBase.Mode.BOTH);
        plv_inter_exp.setAdapter(mInterviewExpAdapter);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.empty, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    @Override public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_comment:
                startActivity(
                        new Intent(mContext, WriteInterExpActivity2.class));
                mJobDetailActivity.overridePendingTransition(
                        R.anim.push_left_in, R.anim.push_left_out);
                break;
        }
    }
}
