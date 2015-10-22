package ideatc.jinxiang.activity;

import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatSpinner;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import ideatc.jinxiang.R;
import ideatc.jinxiang.base.activity.ToolBarBaseActivity;
import ideatc.jinxiang.utils.ParamsUtil;

public class CooperateChangeStageActivity extends ToolBarBaseActivity {

    @Bind(R.id.emp_no)
    EditText empNoEditText;
    @Bind(R.id.changed_stage_spinner)
    AppCompatSpinner stageSpinner;
    @Bind(R.id.change_reason)
    EditText reasonEditText;
    @Bind(R.id.id_btn_confirm)
    AppCompatButton mBtnConfirm;

    private String cooperateId = "";

    private int stageInt;
    private List<String> mData;//阶段
    private ArrayAdapter<String> mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cooperate_change_stage);
        ButterKnife.bind(this);
        if (getIntent() != null) cooperateId = getIntent().getStringExtra("cooperateId");
        initToolbar();
        initView();
        initData();
        getStage();
    }

    @Override
    public void initData() {
        mData = new ArrayList<>();
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mData);
    }

    @Override
    public void initView() {
        empNoEditText.setText(user.getId());

        mBtnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirm();
            }
        });
        stageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                stageInt = position + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * 阶段变更
     */
    private void confirm() {

        String reason = reasonEditText.getText().toString();
        String empNo = empNoEditText.getText().toString();

        if (TextUtils.isEmpty(empNo)) {
            Toast.makeText(CooperateChangeStageActivity.this, "工号不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        RequestParams params = ParamsUtil.getSignParams();
        params.put("id", cooperateId);
        params.put("stage", stageInt);
        params.put("reason", reason);
        params.put("uid", empNo);
        httpClient().get(URL_OA + "/chancestagepost", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = getString(responseBody);
                Log.e("阶段变更", result);
                try {
                    JSONObject obj = new JSONObject(result);
                    if (obj.getBoolean("Status")) {
                        Toast.makeText(CooperateChangeStageActivity.this, obj.getString("Message"), Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(CooperateChangeStageActivity.this, obj.getString("Message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
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

    /**
     * 获取阶段变更列表
     */
    private void getStage() {
        httpClient().get(URL_OA + "/getstagelist", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    JSONArray array = new JSONArray(getString(responseBody));
                    mData.clear();
                    for (int i = 0; i < array.length(); i++) {
                        mData.add(array.getJSONObject(i).getString("text"));
                    }
                    stageSpinner.setAdapter(mAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
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
    public void initToolbar() {
        getToolbar().setNavigationIcon(R.drawable.back);
        getToolbar().setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
