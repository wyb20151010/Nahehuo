package app.nahehuo.com.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import app.nahehuo.com.R;
import app.nahehuo.com.base.GlobalVariables;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by WYB on 2015/12/24.
 */
public class MyApplication extends Application{
    public static Context context;
    public static MyApplication instance = null;
    private List<Activity> activities = new LinkedList<Activity>();

    @Override public void onCreate() {
        context = this;
        super.onCreate();

        SharedPreferences sharedPreferences = getSharedPreferences("user",
                MODE_PRIVATE);
        GlobalVariables.access_token = sharedPreferences.getString(
                "access_token", "");
        GlobalVariables.UID = sharedPreferences.getString(
                "uid", "");
        GlobalVariables.fake_access_token = sharedPreferences.getString(
                "fake_access_token", "");
        // 初始化配置imageloader
        DisplayImageOptions defaultOptions
                = new DisplayImageOptions.Builder() //
                .showImageForEmptyUri(R.mipmap.ic_launcher) //
                .showImageOnFail(R.mipmap.ic_launcher) //
                .cacheInMemory(true) //
                .cacheOnDisk(true) //
                .build();//
        ImageLoaderConfiguration config = new ImageLoaderConfiguration//
                .Builder(getApplicationContext())//
                .defaultDisplayImageOptions(defaultOptions)//
                .discCacheSize(50 * 1024 * 1024)//
                .discCacheFileCount(100)// 缓存一百张图片
                .writeDebugLogs()//
                .build();//
        ImageLoader.getInstance().init(config);
    }


    public static Context getContext() {
        return context;
    }


    public static void initImageLoader(Context context) {
        // 缓存图片
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                context).threadPriority(Thread.NORM_PRIORITY - 2)
                        .denyCacheImageMultipleSizesInMemory()
                        .discCacheFileNameGenerator(new Md5FileNameGenerator())
                        .tasksProcessingOrder(
                                QueueProcessingType.LIFO).writeDebugLogs() // Remove
                        // for
                        // release
                        // app
                .build();
        ImageLoader.getInstance().init(config);
    }


    public static DisplayImageOptions getDisplayOption() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                // .showStubImage(R.drawable.nophoto) // 设置图片下载期间显示的图片
                // .showImageForEmptyUri(R.drawable.nophoto) // 设置图片Uri为空或是错误的时候显示的图片
                // .showImageOnFail(R.drawable.nophoto) // 设置图片加载或解码过程中发生错误显示的图片
                .cacheInMemory(true) // 设置下载的图片是否缓存在内存中
                .cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
                .displayer(new RoundedBitmapDisplayer(100))
                .build(); // 创建配置过得DisplayImageOption对象
        return options;
    }


    public static DisplayImageOptions getDisplayDefaultOption() {
        DisplayImageOptions options
                = new DisplayImageOptions.Builder().showStubImage(
                R.drawable.imageloading)
                                                   .showImageForEmptyUri(
                                                           R.drawable.imageloading).showImageOnFail(
                        R.drawable.imageloading)
                        // .showStubImage(R.drawable.nophoto) // 设置图片下载期间显示的图片
                        // .showImageForEmptyUri(R.drawable.nophoto) //
                        // 设置图片Uri为空或是错误的时候显示的图片
                        // .showImageOnFail(R.drawable.nophoto) // 设置图片加载或解码过程中发生错误显示的图片
                .cacheInMemory(true) // 设置下载的图片是否缓存在内存中
                .cacheOnDisc(true).build(); // 设置下载的图片是否缓存在SD卡中

        return options;
    }


    public static DisplayImageOptions getDisplayOptionByBlur() {
        DisplayImageOptions options = new DisplayImageOptions.Builder()
                // .showStubImage(R.drawable.nophoto) // 设置图片下载期间显示的图片
                // .showImageForEmptyUri(R.drawable.nophoto) //
                // 设置图片Uri为空或是错误的时候显示的图片
                // .showImageOnFail(R.drawable.nophoto) // 设置图片加载或解码过程中发生错误显示的图片
                .cacheInMemory(true) // 设置下载的图片是否缓存在内存中
                .cacheOnDisc(true).build(); // 设置下载的图片是否缓存在SD卡中
        return options;
    }


    public synchronized static MyApplication getInstance() {
        if (null == instance) {
            instance = new MyApplication();
        }
        return instance;
    }


    public void addActivity(Activity activity) {
        activities.add(activity);
    }


    public void finishAll() {
        for (Activity activity : activities) {
            if (!activity.isFinishing()) {
                activity.finish();
            }
        }
    }

}
