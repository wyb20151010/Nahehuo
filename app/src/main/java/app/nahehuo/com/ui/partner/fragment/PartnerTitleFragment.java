package app.nahehuo.com.ui.partner.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import app.nahehuo.com.R;
import app.nahehuo.com.bean.PartnerVP;

/**
 * Created by WYB on 2016/2/18.
 */
public class PartnerTitleFragment extends Fragment {

    private PartnerVP mPartnerVP;

    private TextView tv_name, tv_position, tv_company;


    public PartnerTitleFragment() {

    }


    public static PartnerTitleFragment getInstance(PartnerVP mPartnerVP) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("mPartnerVP", mPartnerVP);
        PartnerTitleFragment fragment = new PartnerTitleFragment();
        fragment.setArguments(bundle);
        return fragment;
    }


    @Nullable @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.item_partner_rec, null);
        tv_name = (TextView) v.findViewById(R.id.tv_name);
        tv_position = (TextView) v.findViewById(R.id.tv_position);
        tv_company = (TextView) v.findViewById(R.id.tv_company);

        return v;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPartnerVP = (PartnerVP) getArguments().getSerializable("mPartnerVP");
        Log.d("PartnerTitleFragment", mPartnerVP.getName());
        tv_name.setText(mPartnerVP.getName());
        tv_company.setText(mPartnerVP.getCompany());
        tv_position.setText(mPartnerVP.getPosition());
    }


}
