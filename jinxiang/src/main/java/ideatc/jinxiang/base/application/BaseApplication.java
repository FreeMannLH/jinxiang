package ideatc.jinxiang.base.application;

import android.app.Application;
import android.content.Intent;

import ideatc.jinxiang.system.service.CheckNewMsgService;
import ideatc.jinxiang.utils.SPUtils;

/**
 * Created by Ccb on 2015/8/3.
 */
public class BaseApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        //日志记录到本地
//        CrashHandler crashHandler = CrashHandler.getInstance();
//        crashHandler.init(this);
        startService(new Intent(this, CheckNewMsgService.class));
        init();

    }


    private void init() {
        //本地持久化存储工具类
        SPUtils.init(BaseApplication.this);
        //异常处理类
//        CrashHandler.getInstance().init(BaseApplication.this);
    }


    /*
        加载本地图片
        String imagePath = "/mnt/sdcard/image.png";
        String imageUrl = ImageDownloader.Scheme.FILE.wrap(imagePath);

        //图片来源于assets
        String assetsUrl = Scheme.ASSETS.wrap("image.png");

        /图片来源于Content provider
        String contentprividerUrl = "content://media/external/audio/albumart/13";

        /图片来源于
        String drawableUrl = Scheme.DRAWABLE.wrap("R.drawable.image");

第一个参数就是我们的图片加载对象ImageLoader, 第二个是控制是否在滑动过程中暂停加载图片，如果需要暂停传true就行了，第三个参数控制猛的滑动界面的时候图片是否加载
        listView.setOnScrollListener(new PauseOnScrollListener(imageLoader, pauseOnScroll, pauseOnFling));
        gridView.setOnScrollListener(new PauseOnScrollListener(imageLoader, pauseOnScroll, pauseOnFling));
       */


//    View类中的三个方法：callOnClick()，performClick()，performLongClick()，可以直接用于触发View的点击事件，不用我们手动点击才触发；

}
