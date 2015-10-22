package ideatc.jinxiang.base.activity;

import android.app.ActivityOptions;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;
import com.loopj.android.http.AsyncHttpClient;

import java.io.UnsupportedEncodingException;

import ideatc.jinxiang.bean.UserBean;
import ideatc.jinxiang.utils.AnimationUtils;
import ideatc.jinxiang.widget.dialog.LoadingDialog;

/**
 * Created by Ccb on 2015/8/3.
 * base class
 */
public abstract class BaseActivity extends AppCompatActivity {

    public static final String URL_OA = "http://jx.xeaxea.com/api/api";
//    public static final String URL_OA = "http://192.168.1.164:8999/api/api";

    public Dialog dialog;

    public DbUtils dbUtils;

    /***
     * 网络请求对象
     */
    private static AsyncHttpClient httpClient = null;

    public static AsyncHttpClient httpClient() {
        if (httpClient == null) {
            synchronized (AsyncHttpClient.class) {
                if (httpClient == null) {
                    httpClient = new AsyncHttpClient();
                }
            }

        }
        return httpClient;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //禁止横屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        dbUtils = DbUtils.create(this);
        try {
            getLoginStatus();
        } catch (DbException e) {
            e.printStackTrace();
        }
        initDialog();
    }

    public abstract void initData();

    public abstract void initView();


    /***
     * 低内存时调用
     *
     * @param level
     */
    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);

    }

    /**
     * 初始化加载框
     */
    private void initDialog() {
        dialog = new LoadingDialog(BaseActivity.this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (httpClient != null) {
            httpClient.cancelAllRequests(true);
            httpClient = null;
        }
    }

    public void startActivity(Class<?> activity) {
        Intent it = new Intent(this, activity);
        if (Build.VERSION.SDK_INT >= 21) {
            startActivity(it, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
        } else {
            ActivityCompat.startActivity(BaseActivity.this, it, AnimationUtils.shake(BaseActivity.this));
        }

    }

    /**
     * 字节数组转成
     *
     * @param bytes
     * @return
     */
    public static String getString(byte[] bytes) {
        return getString(bytes, "utf8");
    }

    /**
     * 字节数组转成
     *
     * @param bytes
     * @return
     * @coidename 编码
     */
    public static String getString(byte[] bytes, String codename) {
        try {
            String str = new String(bytes, "utf-8");
            return str;
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 用户信息
     */
    public static UserBean user = null;

    public void Log(String info) {
        Log.e(info, info);
    }

    public void Log(int info) {
        Log.e(info + "", info + "");
    }

    public boolean getLoginStatus() throws DbException {
        UserBean userBean = dbUtils.findFirst(UserBean.class);
        if (userBean == null) {
            user = null;
            return false;
        }
        user = userBean;
        return true;
    }


}
