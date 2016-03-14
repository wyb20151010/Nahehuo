package app.nahehuo.com.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import app.nahehuo.com.R;
import app.nahehuo.com.application.MyApplication;
import app.nahehuo.com.ui.fragment.JobToolBarFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

/**
 * Created by WYB on 2015/12/30.
 */
public class MainActivity extends AppCompatActivity
        implements View.OnClickListener {

    private Context mContext;
    private long firstTime;
    private SlidingMenu menu;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        initView();
        initSlidingMenu();
        replaceFragment(R.id.toolbar1, new JobToolBarFragment());
    }


    private void initSlidingMenu() {
        menu = new SlidingMenu(this);
        menu.setMode(SlidingMenu.LEFT);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        menu.setFadeDegree(0.35f);
        menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
        menu.setMenu(R.layout.left_menu);
        menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
    }


    private void initView() {

    }


    @Override public void onClick(View v) {

    }


    public void replaceFragment(int id_content, Fragment fragment) {
        FragmentTransaction transaction
                = getSupportFragmentManager().beginTransaction();
        transaction.replace(id_content, fragment);
        transaction.commit();
    }


    @Override public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            long secondTime = System.currentTimeMillis();

            if (secondTime - firstTime > 800) {// 如果两次按键时间间隔大于800毫秒，则不退出
                Toast.makeText(MainActivity.this, "再按一次退出程序...",
                        Toast.LENGTH_SHORT).show();
                firstTime = secondTime;// 更新firstTime
                return true;
            }
            else {
                MyApplication.getInstance().finishAll();// 否则退出程序
            }
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (menu.isMenuShowing()) {
                    menu.toggle();
                }
                else {
                    menu.showMenu();
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    public SlidingMenu getMenu() {
        return menu;
    }


    public void setMenu(SlidingMenu menu) {
        this.menu = menu;
    }
}
