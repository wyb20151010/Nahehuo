package app.nahehuo.com.ui.job.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.application.MyApplication;
import app.nahehuo.com.bean.CompanyTeam;
import com.makeramen.RoundedImageView;
import com.nostra13.universalimageloader.core.ImageLoader;

/**
 * Created by Administrator on 2016/3/16.
 */
public class CompanyTeamFragment extends Fragment {

    private CompanyTeam mCompanyTeam;
    private TextView tv_name, tv_position, tv_content;
    private RoundedImageView iv_upload;


    public CompanyTeamFragment() {
    }


    public static CompanyTeamFragment getInstance(CompanyTeam mCompanyTeam) {

        Bundle bundle = new Bundle();
        bundle.putSerializable("mCompanyTeam", mCompanyTeam);
        CompanyTeamFragment teamFragment = new CompanyTeamFragment();
        teamFragment.setArguments(bundle);
        return teamFragment;
    }


    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.item_company_team, null);
        tv_name = (TextView) v.findViewById(R.id.tv_name);
        tv_position = (TextView) v.findViewById(R.id.tv_position);
        tv_content = (TextView) v.findViewById(R.id.tv_content);
        iv_upload = (RoundedImageView) v.findViewById(R.id.iv_upload);
        return v;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mCompanyTeam = (CompanyTeam) getArguments().getSerializable(
                "mCompanyTeam");
        tv_name.setText(mCompanyTeam.getName());
        tv_content.setText(mCompanyTeam.getDesp());
        tv_position.setText(mCompanyTeam.getPosition());
        ImageLoader.getInstance()
                   .displayImage(mCompanyTeam.getPic(), iv_upload,
                           MyApplication.getDisplayDefaultOption());

    }
}
