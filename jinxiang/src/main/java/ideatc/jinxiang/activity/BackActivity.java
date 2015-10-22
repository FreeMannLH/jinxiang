package ideatc.jinxiang.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import ideatc.jinxiang.R;
import ideatc.jinxiang.base.activity.ToolBarBaseActivity;
import ideatc.jinxiang.bean.WaitHandleListBean;
import ideatc.jinxiang.utils.ParamsUtil;
import ideatc.jinxiang.utils.SPUtils;

public class BackActivity extends ToolBarBaseActivity {

    EditText mBackEditText;

    @Bind(R.id.id_back_textInput)
    TextInputLayout mBackTextInput;
    @Bind(R.id.id_btn_confirm)
    AppCompatButton mBtnConfirm;
    @Bind(R.id.id_btn_cancel)
    AppCompatButton mBtnCancel;

    private WaitHandleListBean.RowsEntity waitHandleBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back);
        ButterKnife.bind(this);
        initToolbar();
        initView();
        initData();
    }

    @Override
    public void initData() {
        if (getIntent() != null) waitHandleBean = getIntent().getParcelableExtra("waitHandleBean");
    }

    @Override
    public void initView() {
        mBackEditText = mBackTextInput.getEditText();
        mBtnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postBackReason();
            }
        });
        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void initToolbar() {
        getToolbar().setNavigationIcon(R.drawable.back);
        getToolbar().setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    /**
     * 提交退回原因
     */
    private void postBackReason() {

        String suggestion = mBackEditText.getText().toString();
        if (TextUtils.isEmpty(suggestion)) {
            mBackTextInput.setError(getString(R.string.back_reason));
            return;
        }
        String url = URL_OA + "/BackSysFlow";
        RequestParams params = ParamsUtil.getSignParams();
        params.put("statusId", waitHandleBean.getId());
        params.put("sysUserId", SPUtils.getInt("id", 0));
        params.put("suggestion", suggestion);
        httpClient().get(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = getString(responseBody);
                Log.e("退回意见", result);
                try {
                    JSONObject object = new JSONObject(result);
                    if (object.getBoolean("Status")) {
                        Toast.makeText(BackActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(BackActivity.this, object.getString("Message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("退回意见", statusCode + "," + getString(responseBody));
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
}
