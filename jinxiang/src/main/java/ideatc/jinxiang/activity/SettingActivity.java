package ideatc.jinxiang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;

import butterknife.Bind;
import butterknife.ButterKnife;
import ideatc.jinxiang.R;
import ideatc.jinxiang.base.activity.ToolBarBaseActivity;
import ideatc.jinxiang.bean.UserBean;
import ideatc.jinxiang.constans.URL;
import ideatc.jinxiang.utils.AndroidUtils;
import ideatc.jinxiang.widget.dialog.VersionUpdateDialog;

public class SettingActivity extends ToolBarBaseActivity implements View.OnClickListener {


    private static final int WAIT_TIME = 1600;

    @Bind(R.id.id_change_password_btn)
    TextView mChangePasswordBtn;
    @Bind(R.id.id_version_update_btn)
    TextView mVersionUpdateBtn;
    @Bind(R.id.id_about_us_btn)
    TextView mAboutUsBtn;
    @Bind(R.id.id_user_exit_btn)
    TextView mUserExitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        initToolbar();
        initView();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        mUserExitBtn.setOnClickListener(this);
        mChangePasswordBtn.setOnClickListener(this);
        String version = AndroidUtils.getVersionName(SettingActivity.this);
        mVersionUpdateBtn.setText(getString(R.string.current_version) + version + ")");
        mVersionUpdateBtn.setOnClickListener(this);
        mAboutUsBtn.setOnClickListener(this);
    }

    @Override
    public void initToolbar() {
        getToolbar().setTitle(getString(R.string.setting));
        getToolbar().setNavigationIcon(R.drawable.back);
        getToolbar().setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_user_exit_btn:
                exitLogin();
                break;
            case R.id.id_change_password_btn:
                Toast.makeText(SettingActivity.this, "暂不支持此功能", Toast.LENGTH_SHORT).show();
                break;
            case R.id.id_about_us_btn:
                startActivity(AboutUsActivity.class);
                break;
            case R.id.id_version_update_btn:
                checkUpdate();
//                VersionUpdateDialog dialog = new VersionUpdateDialog();
//                dialog.show(getSupportFragmentManager(), getString(R.string.check_update));
                break;
            default:
                break;
        }
    }

    /**
     * 退出登录
     */
    private void exitLogin() {
        dialog.show();
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                try {
                    dbUtils.deleteAll(UserBean.class);
                    if (!getLoginStatus()) {
                        dialog.dismiss();
                        Intent it = new Intent(SettingActivity.this, LoginActivity.class);
                        it.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(it);
                        finish();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, WAIT_TIME);
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
                if (AndroidUtils.getVersionName(SettingActivity.this).equals(result))
                    Toast.makeText(SettingActivity.this, R.string.not_need_update, Toast.LENGTH_SHORT).show();
                else {
                    VersionUpdateDialog dialog = VersionUpdateDialog.instance(URL.APP_DOWNLOAD_URL);
                    dialog.show(getSupportFragmentManager(), getString(R.string.check_update));
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("check update", statusCode + "");
            }

            @Override
            public void onFinish() {
                super.onFinish();
                dialog.dismiss();
            }

            @Override
            public void onStart() {
                super.onStart();
                dialog.show();
            }
        });
    }
}
