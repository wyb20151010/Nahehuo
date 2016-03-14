package app.nahehuo.com.ui.job;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import app.nahehuo.com.R;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by WYB on 2016/1/8.
 */
public class JobDeliverySuccessActivity extends AppCompatActivity {

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_delivery_suc);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override public void run() {
                finish();
            }
        }, 1000);
    }
}
