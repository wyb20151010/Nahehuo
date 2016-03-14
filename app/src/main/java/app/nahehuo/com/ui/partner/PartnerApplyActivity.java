package app.nahehuo.com.ui.partner;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import app.nahehuo.com.R;
import app.nahehuo.com.ui.partner.fragment.PartnerApply1Fragment;
import app.nahehuo.com.ui.partner.fragment.PartnerApply2Fragment;

/**
 * Created by WYB on 2016/2/19.
 */
public class PartnerApplyActivity extends AppCompatActivity {

    private Context mContext;
    private int type = 4;
    private FrameLayout fl_content;
    private PartnerApply1Fragment mApply1Fragment;
    private PartnerApply2Fragment mApply2Fragment;
    public static final String TYPE = "type";


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_partner);
        mContext = this;
        fl_content = (FrameLayout) findViewById(R.id.fl_content);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        if (type != 4) {
            mApply1Fragment = new PartnerApply1Fragment();
            Bundle bundle = new Bundle();
            bundle.putInt(TYPE, type);
            mApply1Fragment.setArguments(bundle);
            transaction.replace(R.id.fl_content, mApply1Fragment);
            transaction.commit();
        }
        else {
            mApply2Fragment = new PartnerApply2Fragment();
            transaction.replace(R.id.fl_content, mApply2Fragment);
            transaction.commit();
        }
    }
}
