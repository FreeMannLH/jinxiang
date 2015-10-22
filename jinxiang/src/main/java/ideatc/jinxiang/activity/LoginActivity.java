package ideatc.jinxiang.activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.exception.DbException;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import ideatc.jinxiang.R;
import ideatc.jinxiang.base.activity.BaseActivity;
import ideatc.jinxiang.base.manager.SnackBarManager;
import ideatc.jinxiang.bean.UserBean;
import ideatc.jinxiang.constans.URL;
import ideatc.jinxiang.utils.AndroidUtils;
import ideatc.jinxiang.utils.ParamsUtil;
import ideatc.jinxiang.utils.SPUtils;
import ideatc.jinxiang.widget.dialog.FinishDialog;
import ideatc.jinxiang.widget.dialog.VersionUpdateDialog;
import info.hoang8f.widget.FButton;

public class LoginActivity extends BaseActivity {

    @Bind(R.id.textinputPassword)
    TextInputLayout mTextInputLayoutPassword;
    @Bind(R.id.textinputAccount)
    TextInputLayout mTextInputLayoutUserName;
    @Bind(R.id.btn_login)
    FButton mButtonLogin;

    private EditText etUserName;
    private EditText etPassword;
    private String url = null;
    private String url_userInfo = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        url = URL_OA + "/Login";
        url_userInfo = URL_OA + "/GetSysUserInfo";
        initView();
        checkUpdate();
    }

    @Override
    public void initView() {

        etUserName = mTextInputLayoutUserName.getEditText();
        etPassword = mTextInputLayoutPassword.getEditText();

        mButtonLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    @Override
    public void initData() {
    }

    private void login() {
        String username = etUserName.getText().toString();
        String pwd = etPassword.getText().toString();
        if (TextUtils.isEmpty(username)) {
            mTextInputLayoutUserName.setError(getString(R.string.username_error));
            return;
        }
        if (TextUtils.isEmpty(pwd)) {
            mTextInputLayoutPassword.setError(getString(R.string.password_error));
            return;
        }
        RequestParams params = ParamsUtil.getSignParams();
        params.put("code", username);
        params.put("pwd", pwd);

        httpClient().get(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.e("登陆", getString(responseBody));
                try {
                    JSONObject obj = new JSONObject(getString(responseBody));
                    if (!obj.getBoolean("Status")) {
                        SnackBarManager.make(getCurrentFocus(), obj.getString("Message"), Snackbar.LENGTH_LONG);
                        return;
                    }

                    JSONObject userIdObject = obj.getJSONObject("Other");

                    //保存sysUserId
                    int id = userIdObject.getInt("Id");
                    SPUtils.putInt("id", id);
                    getUserInfo(id);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("login statusCode", statusCode + "");
                SnackBarManager.make(getCurrentFocus(), getString(R.string.login_faild_please_retry), Snackbar.LENGTH_INDEFINITE);
            }

            @Override
            public void onStart() {
                super.onStart();
                dialog.show();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                dialog.dismiss();
            }
        });
    }


    /**
     * 获取用户信息
     *
     * @param id
     */
    private void getUserInfo(int id) {
        RequestParams params = ParamsUtil.getSignParams();
        params.put("sysUserId", id);
        httpClient().get(url_userInfo, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = getString(responseBody);
                Log.e("获取用户信息", result);
                UserBean userBean = new Gson().fromJson(result, UserBean.class);
                if (!userBean.getId().equals("admin")) {
                    if (userBean.getDepartment() != null && userBean.getDepartment().size() != 0)
                        SPUtils.putString("department", userBean.getDepartment().get(0));
                    if (userBean.getPost() != null && userBean.getPost().size() != 0)
                        SPUtils.putString("position", userBean.getPost().get(0));
                }

                user = userBean;
                try {
                    dbUtils.deleteAll(UserBean.class);
                    dbUtils.save(userBean);
                } catch (DbException e) {
                    e.printStackTrace();
                }
                Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                startActivity(HomeActivity.class);
                finish();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }

            @Override
            public void onStart() {
                super.onStart();
                dialog.show();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                dialog.dismiss();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onBackPressed() {
        FinishDialog dialog = new FinishDialog();
        dialog.show(getSupportFragmentManager(), "退出应用");
    }

    /**
     * 检查版本
     */
    private void checkUpdate() {
        httpClient().get(URL.CHECK_APP_VERSION, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = getString(responseBody);
                result = result.substring(1, result.length() - 1);
                Log.e("check update", result);
                if (AndroidUtils.getVersionName(LoginActivity.this).equals(result)) {
                    Log.e("version", result);
                } else {
                    VersionUpdateDialog dialog = VersionUpdateDialog.instance(URL.APP_DOWNLOAD_URL);
                    dialog.show(getSupportFragmentManager(), getString(R.string.check_update));
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("check update", statusCode + "");
            }
        });
    }
}
