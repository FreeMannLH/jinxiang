package ideatc.jinxiang.system.service;

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

import java.util.ArrayList;
import java.util.List;

import ideatc.jinxiang.R;
import ideatc.jinxiang.activity.SplashActivity;
import ideatc.jinxiang.base.activity.BaseActivity;
import ideatc.jinxiang.bean.WaitHandleBean;
import ideatc.jinxiang.utils.ParamsUtil;
import ideatc.jinxiang.utils.SPUtils;

/**
 * @author ccb
 *         create at 2015/10/8 15:22
 */
public class CheckNewMsgService extends Service {

    private boolean haveNewMsg = false;//是否有新消息

    private int count = 0;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    //获取消息线程
//    private MessageThread messageThread = null;
    //点击查看
    private Intent messageIntent = null;
    private PendingIntent messagePendingIntent = null;
    //通知栏消息
    private int messageNotificationID = 1000;
    private Notification messageNotification = null;
    private NotificationManager messageNotificationManager = null;
    private boolean isRunning = true;

    private List<WaitHandleBean> mData = new ArrayList<>();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //初始化
        messageNotification = new Notification();
        messageNotification.icon = R.drawable.app_icon;  //通知图片
        messageNotification.tickerText = "来自劲翔的新消息";         //通知标题
//        messageNotification.defaults = Notification.DEFAULT_SOUND;
        messageNotification.flags |= Notification.FLAG_AUTO_CANCEL;
        messageNotificationManager = (NotificationManager) getSystemService(this.NOTIFICATION_SERVICE);
        //点击查看
        messageIntent = new Intent(this, SplashActivity.class);
        messagePendingIntent = PendingIntent.getActivity(this, 0, messageIntent, 0);
        //开启线程
        MessageThread thread = new MessageThread();
        isRunning = true;
        thread.start();
        checkNewMsg();
        return Service.START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        startService(new Intent(this, CheckNewMsgService.class));
    }

    /**
     * 有消息时提示通知
     */
    private Handler myHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    checkNewMsg();
                    break;
                case 2:
//                    isRunning = false;
//                    设置消息内容和标题
                    if (!isAppRunning(CheckNewMsgService.this)) {
                        messageNotification.setLatestEventInfo(CheckNewMsgService.this, "劲翔智慧产业", "您有" + count + "个未处理事项", messagePendingIntent);
                        //发布消息
                        messageNotificationManager.notify(messageNotificationID, messageNotification);
                        //避免覆盖消息，采取ID自增
//                    messageNotificationID++;
                    }
                    break;
            }
            super.handleMessage(msg);
        }
    };

    /***
     * 获取是否有待办事项,通知主线程
     */
    class MessageThread extends Thread {
        //运行状态
        @Override
        public void run() {
            while (isRunning) {
                try {
                    //30分钟提醒一次
                    Thread.currentThread().sleep(10000 * 6 * 30);
//                    Thread.currentThread().sleep(10000);
                    Message msg = myHandler.obtainMessage();
                    if (haveNewMsg) msg.what = 2;
                    else msg.what = 1;
                    myHandler.sendMessage(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 查询是否有待办事项
     */
    private void checkNewMsg() {
        if (SPUtils.getInt("id", 0) == 0) return;
        RequestParams params = ParamsUtil.getSignParams();
        params.put("sysUserId", SPUtils.getInt("id", 0));
        AsyncHttpClient httpClient = new AsyncHttpClient();
        httpClient.get(BaseActivity.URL_OA + "/GetWaitHandleCount", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = BaseActivity.getString(responseBody);
                Log.e("checkNewMsg", result);
                Gson gson = new Gson();
                JsonParser parser = new JsonParser();
                JsonElement element = parser.parse(result);
                JsonArray array = element.getAsJsonArray();
                if (array == null || array.size() == 0) {
                    haveNewMsg = false;
                } else {
                    haveNewMsg = true;
//                    count = Integer.parseInt(result);
                    for (int i = 0, size = array.size(); i < size; i++) {
                        WaitHandleBean bean = gson.fromJson(gson.toJson(array.get(i)), WaitHandleBean.class);
                        count += bean.getItem().get(i).getCount();
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    /**
     * 检测app是否启动
     *
     * @param context
     * @return
     */
    private boolean isAppRunning(Context context) {
//        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
//        ComponentName cn = am.getRunningTasks(1).get(0).topActivity;
//        String currentPackageName = cn.getPackageName();
//        if (!TextUtils.isEmpty(currentPackageName) && currentPackageName.equals(getPackageName())) {
//            return true;
//        }
//
//        return false;
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(100);
        boolean isAppRunning = false;
        String MY_PKG_NAME = "ideatc.jinxiang";
        for (ActivityManager.RunningTaskInfo info : list) {
            if (info.topActivity.getPackageName().equals(MY_PKG_NAME) || info.baseActivity.getPackageName().equals(MY_PKG_NAME)) {
                isAppRunning = true;
                break;
            }
        }
        return isAppRunning;
    }
}
