package app.nahehuo.com.ui.partner.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import app.nahehuo.com.R;
import app.nahehuo.com.adapter.PartnerPersonAdapter;
import app.nahehuo.com.bean.PersonPartner;
import app.nahehuo.com.bean.PersonPartner2;
import app.nahehuo.com.ui.MainActivity;
import app.nahehuo.com.ui.partner.PartnerAllActivity;
import app.nahehuo.com.ui.partner.PartnerApplyActivity;
import app.nahehuo.com.ui.partner.PartnerInfoActivity;
import app.nahehuo.com.ui.partner.PartnerMsgActivity;
import app.nahehuo.com.ui.partner.PartnerNewFriActivity;
import app.nahehuo.com.ui.partner.popup.SelectMyFriPopup;
import app.nahehuo.com.util.CharacterParser;
import app.nahehuo.com.util.PingyinComparator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by WYB on 2016/2/17.
 */
public class PartnerMyFriFragment extends Fragment
        implements View.OnClickListener, AdapterView.OnItemClickListener {

    private FloatingActionButton fab_select;
    private ListView lv_partner;
    private PartnerPersonAdapter mAdapter;
    private List<PersonPartner2> mPartners = new ArrayList<>();
    private List<PersonPartner> mPartnersNeed = new ArrayList<>();
    private CharacterParser characterParser;
    private PingyinComparator pinyinComparator;
    private Context mContext;
    private SelectMyFriPopup mSelectMyFriPopup;
    private LinearLayout ll_new_friend, ll_is_partner, ll_apply_partner;
    private MainActivity mainActivity;


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
        View v = inflater.inflate(R.layout.fragment_part_my_fri, null);
        mContext = getActivity();
        characterParser = CharacterParser.getInstance();
        pinyinComparator = new PingyinComparator();
        initData();
        initView(v);
        return v;
    }


    private void initData() {
        mPartners.add(new PersonPartner2("蔡轮", "APP开发经理"));
        mPartners.add(new PersonPartner2("百科", "APP开发经理"));
        mPartners.add(new PersonPartner2("才能", "打杂的"));
        mPartners.add(new PersonPartner2("菜竜", "我也不知道干啥"));
        mPartners.add(new PersonPartner2("阿宝", "IOS开发经理"));
        mPartners.add(new PersonPartner2("李蒙", "IOS开发经理"));
        mPartners.add(new PersonPartner2("王耀彬", "IOS开发经理"));
        mPartners.add(new PersonPartner2("何小芳", "PHP开发高级程序员"));
        mPartners.add(new PersonPartner2("周韵", "测试部长"));
        findNeedData();
        Collections.sort(mPartnersNeed, pinyinComparator);
        mPartnersNeed.add(0, new PersonPartner("哪合伙小秘书", "官方账号", "#"));
    }


    private void initView(View v) {

        mAdapter = new PartnerPersonAdapter(mPartnersNeed, mContext);
        lv_partner = (ListView) v.findViewById(R.id.lv_partner);
        lv_partner.setAdapter(mAdapter);
        fab_select = (FloatingActionButton) v.findViewById(R.id.fab_select);
        fab_select.setOnClickListener(this);
        ll_new_friend = (LinearLayout) v.findViewById(R.id.ll_new_friend);
        ll_new_friend.setOnClickListener(this);
        ll_is_partner = (LinearLayout) v.findViewById(R.id.ll_is_partner);
        ll_is_partner.setOnClickListener(this);
        ll_apply_partner = (LinearLayout) v.findViewById(R.id.ll_apply_partner);
        ll_apply_partner.setOnClickListener(this);
        lv_partner.setOnItemClickListener(this);
    }


    private void findNeedData() {
        for (int i = 0; i < mPartners.size(); i++) {
            PersonPartner personPartner = new PersonPartner();
            personPartner.setName(mPartners.get(i).getName());
            personPartner.setPosition(mPartners.get(i).getPosition());
            String pingyin = characterParser.getSelling(
                    mPartners.get(i).getName());
            String pingyintou = pingyin.substring(0, 1).toUpperCase();
            if (pingyintou.matches("[A-Z]")) {
                personPartner.setPingyintou(pingyintou.toUpperCase());
            }
            else {
                personPartner.setPingyintou("=");
            }
            mPartnersNeed.add(personPartner);
        }
    }


    @Override public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab_select:
                mSelectMyFriPopup = new SelectMyFriPopup(getActivity());
                mSelectMyFriPopup.showPopupWindow();
                mSelectMyFriPopup.setCallBack(
                        new SelectMyFriPopup.SetOnClickCallBack() {
                            @Override public void setSave(String letter) {
                                mSelectMyFriPopup.dismiss();
                                Log.d("TAG", letter);
                                int position = mAdapter.getPositionForSection(
                                        letter.charAt(0));
                                if (position != -1) {
                                    lv_partner.setSelection(position);
                                }
                            }


                            @Override public void setCancel() {
                                mSelectMyFriPopup.dismiss();
                            }
                        });
                break;
            case R.id.ll_new_friend:
                startActivity(
                        new Intent(mContext, PartnerNewFriActivity.class));
                mainActivity.overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
                break;
            case R.id.ll_is_partner:
                startActivity(new Intent(mContext, PartnerAllActivity.class));
                mainActivity.overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
                break;
            case R.id.ll_apply_partner:
                startActivity(new Intent(mContext, PartnerApplyActivity.class));
                mainActivity.overridePendingTransition(R.anim.push_left_in,
                        R.anim.push_left_out);
                break;
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position == 0) {
            startActivity(new Intent(mContext, PartnerMsgActivity.class));
            mainActivity.overridePendingTransition(R.anim.push_left_in,
                    R.anim.push_left_out);
        }
        else {
            startActivity(new Intent(mContext, PartnerInfoActivity.class));
            mainActivity.overridePendingTransition(R.anim.push_left_in,
                    R.anim.push_left_out);
        }
    }
}
