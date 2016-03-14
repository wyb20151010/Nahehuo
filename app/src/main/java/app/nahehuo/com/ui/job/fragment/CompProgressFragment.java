package app.nahehuo.com.ui.job.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.bean.TestData;
import app.nahehuo.com.util.DpPxUtil;
import app.nahehuo.com.util.TextUtil;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WYB on 2016/1/11.
 */
public class CompProgressFragment extends Fragment {
    private RecyclerView recycler_view;
    private List<TestData> TestDatas = new ArrayList<>();
    private float mviewHeight;
    private Context mContext;
    private CompProgressAdapter mAdapter;


    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_comp_progress, null);
        mContext = getActivity();
        initData();
        mAdapter = new CompProgressAdapter(TestDatas);
        recycler_view = (RecyclerView) v.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler_view.setLayoutManager(layoutManager);
        recycler_view.setAdapter(mAdapter);
        return v;
    }


    private void initData() {
        TestData testData = new TestData();
        testData.setTitle("12-01 周一");
        testData.setContent("第一次上线我来");
        TestDatas.add(testData);

        TestData testData1 = new TestData();
        testData1.setTitle("12-02 周二");
        testData1.setContent1("为了发电，请用爱吧。用你妹啊啊啊啊啊啊");
        testData1.setContent("第二次上线，我又来了");
        TestDatas.add(testData1);
        TestData testData2 = new TestData();
        testData2.setTitle("12-02 周二");
        testData2.setContent1(
                "英国《每日邮报》21日报道，。写信者丁某自称是安徽人，说，他对此感到震惊，认为有义务将此事公开。");
        testData2.setContent("第二次上线，我又来了");
        TestDatas.add(testData2);
        TestData testData3 = new TestData();
        testData3.setTitle("12-02 周二");
        testData3.setContent1(
                "发现里面藏着一封中文信。他将信件内容翻译成英文后，发现是封“绝望的求救信”。写信者丁某自称是安徽人，39岁，被诬告涉嫌犯敲诈勒索罪，现被强行关押。阿克巴尔说，他对此感到震惊，认为有义务将此事公开。");
        testData3.setContent("第二次上线，我又来了");
        TestDatas.add(testData3);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    class CompProgressAdapter
            extends RecyclerView.Adapter<CompProgressAdapter.ViewHolder> {

        private List<TestData> mTestDatas;


        public CompProgressAdapter(List<TestData> testDatas) {
            mTestDatas = testDatas;
        }


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(mContext)
                                   .inflate(R.layout.item_comp_progress, parent,
                                           false);
            return new ViewHolder(v);
        }


        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            TestData testData = mTestDatas.get(position);
            if (!TextUtil.isEmpty(testData.getContent1())) {
                holder.tv_content1.setVisibility(View.VISIBLE);
                holder.tv_content1.setText(testData.getContent1());
            }
            holder.tv_title.setText(testData.getTitle());
            holder.tv_content.setText(testData.getContent());

            if (!TextUtil.isEmpty(testData.getContent1())) {
                holder.tv_content1.setText(testData.getContent1());

                holder.tv_content1.getViewTreeObserver()
                                  .addOnGlobalLayoutListener(
                                          new ViewTreeObserver.OnGlobalLayoutListener() {
                                              @Override
                                              public void onGlobalLayout() {

                                                  mviewHeight
                                                          = holder.tv_content1.getHeight();
                                                  Log.d("TAG",
                                                          "" + mviewHeight);
                                                  LinearLayout.LayoutParams
                                                          layoutParams
                                                          = new LinearLayout.LayoutParams(
                                                          LinearLayout.LayoutParams.MATCH_PARENT,
                                                          LinearLayout.LayoutParams.WRAP_CONTENT);

                                                  if (position ==
                                                          mTestDatas.size() -
                                                                  1) {
                                                      layoutParams.height
                                                              = DpPxUtil.dip2px(
                                                              getContext(), 85 +
                                                                      DpPxUtil.px2dip(
                                                                              getContext(),
                                                                              mviewHeight));
                                                  }
                                                  else {
                                                      layoutParams.height
                                                              = DpPxUtil.dip2px(
                                                              getContext(), 60 +
                                                                      DpPxUtil.px2dip(
                                                                              getContext(),
                                                                              mviewHeight));
                                                  }

                                                  layoutParams.width
                                                          = DpPxUtil.dip2px(
                                                          getContext(), 1);
                                                  holder.view.setLayoutParams(
                                                          layoutParams);
                                                  holder.tv_content1.getViewTreeObserver()
                                                                    .removeGlobalOnLayoutListener(
                                                                            this);
                                              }
                                          });
            }
        }


        @Override public int getItemCount() {
            return mTestDatas.size();
        }


        class ViewHolder extends RecyclerView.ViewHolder {
            TextView tv_title;
            TextView tv_content;
            TextView tv_content1;
            View view;


            public ViewHolder(View v) {
                super(v);
                tv_title = (TextView) v.findViewById(R.id.tv_title);
                tv_content1 = (TextView) v.findViewById(R.id.tv_content1);
                tv_content = (TextView) v.findViewById(R.id.tv_content);

                view = v.findViewById(R.id.view);
            }
        }
    }
}
