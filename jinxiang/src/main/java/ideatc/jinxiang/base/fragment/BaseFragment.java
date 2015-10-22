package ideatc.jinxiang.base.fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.exception.DbException;
import com.loopj.android.http.AsyncHttpClient;

import ideatc.jinxiang.bean.UserBean;
import ideatc.jinxiang.utils.AnimationUtils;
import ideatc.jinxiang.widget.dialog.LoadingDialog;

/**
 * Created by Ccb on 2015/8/3.
 */
public abstract class BaseFragment extends Fragment {

    public DbUtils dbUtils;

    public Dialog dialog;

    public static final String URL_OA = "http://jx.xeaxea.com/api/api";

//    public static final String URL_OA = "http://192.168.1.164:8999/api/api";
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbUtils = DbUtils.create(getActivity());
        if (user == null) {
            try {
                user = dbUtils.findFirst(UserBean.class);
            } catch (DbException e) {
                e.printStackTrace();
            }
        }
        initDialog();
    }

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
    public void onDestroy() {
        super.onDestroy();
        if (httpClient != null) {
            httpClient.cancelAllRequests(true);
            httpClient = null;
        }
    }


    /**
     * 初始化加载框
     */
    private void initDialog() {
        dialog = new LoadingDialog(getActivity());
    }

    public void startActivity(Class<?> activity) {
        Intent it = new Intent(getActivity(), activity);
        ActivityCompat.startActivity(getActivity(), it, AnimationUtils.shake(getActivity()));
    }


    public void Log(String info) {
        Log.e(info.substring(0, 5), info);
    }

    public void Log(int info) {
        Log.e(info + "", info + "");
    }


    /**
     * 用户信息
     */
    public static UserBean user = null;

    public boolean getLoginStatus() throws DbException {
        UserBean userBean = dbUtils.findFirst(UserBean.class);
        if (userBean == null) {
            user = null;
            return false;
        }
        user = userBean;
        return true;
    }


//    public static CustomerListBean mCustomerListBean;

//    /**
//     * 获取我所有的客户
//     */
//    public void getMyAllCustomter() {
//        String url = URL_OA + "/getmyallcustomers";
//        RequestParams params = new RequestParams();
//        params.put("uid", user.getId());
//        httpClient().get(url, params, new AsyncHttpResponseHandler() {
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
//
//                if (statusCode == 200) {
//                    Log.e("获取我所有客户", BaseActivity.getString(responseBody));
//                    mCustomerListBean = new Gson().fromJson(BaseActivity.getString(responseBody), CustomerListBean.class);
//                }
//            }
//
//            @Override
//            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
//                Log.e("获取我所有客户", statusCode + "");
//            }
//
//        });
//
//    }
}
