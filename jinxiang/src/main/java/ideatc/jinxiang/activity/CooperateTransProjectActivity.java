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

public class CooperateTransProjectActivity extends ToolBarBaseActivity {


    @Bind(R.id.emp_no)
    EditText empNoEditText;
    @Bind(R.id.project_no)
    EditText projectNoEditText;
    @Bind(R.id.title)
    EditText titleEditTextEditText;
    @Bind(R.id.end_time)
    EditText endDateEditText;
    @Bind(R.id.reMark)
    EditText reMarkEditText;
    @Bind(R.id.id_btn_confirm)
    AppCompatButton mBtnConfirm;
    @Bind(R.id.payment_mode_spinner)
    AppCompatSpinner paymentModeSpinner;


    private String cooperateId = "";//合作机会id,流水号
//    private CooperateChanceDetailBean.CooperateModel model;//合作机会详情实体

    private int payFlag = 1;

    // 支付方式
    private List<String> mData = new ArrayList<>();
    private ArrayAdapter<String> mAdapter;

    private String contactJson = "";
    private String mattersJson = "";
    private int customerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cooperate_trans_project);
        ButterKnife.bind(this);
        if (getIntent() != null) {
            cooperateId = getIntent().getStringExtra("cooperateId");
            contactJson = getIntent().getStringExtra("contactJson");
            mattersJson = getIntent().getStringExtra("mattersJson");
            customerId = getIntent().getIntExtra("customerId", 0);
        }
        initToolbar();
        initView();
        initData();
        getPayFlagList();
    }

    @Override
    public void initData() {
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mData);
        paymentModeSpinner.setAdapter(mAdapter);
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
        paymentModeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                payFlag = position + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    /**
     * 转为合同
     */
    private void confirm() {
        String empNo = empNoEditText.getText().toString();
        String projectNo = projectNoEditText.getText().toString();
        String title = titleEditTextEditText.getText().toString();
        String endDate = endDateEditText.getText().toString();
        String remark = reMarkEditText.getText().toString();
        if (TextUtils.isEmpty(empNo) || TextUtils.isEmpty(title) || TextUtils.isEmpty(endDate)) {
            Toast.makeText(CooperateTransProjectActivity.this, "信息未填写完整", Toast.LENGTH_SHORT).show();
            return;
        }
//        RequestParams params = ParamsUtil.getSignParams();
        RequestParams params = new RequestParams();
        params.put("Id", cooperateId);
        params.put("Code", 0);
        params.put("uid", empNo);
        params.put("CustomerId", customerId);
        params.put("Title", title);
        params.put("CompletionDate", endDate);
        params.put("Remark", remark);
        params.put("ContractNo", projectNo);
        params.put("PayFlag", payFlag);
        params.put("SignDate", "");
        params.put("ContactsJson", contactJson);
        params.put("MattersJson", mattersJson);
        Log.e("params", params.toString());
        httpClient().get(URL_OA + "/contractpost", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = getString(responseBody);
                Log.e("转为合同", result);
                try {
                    JSONObject obj = new JSONObject(result);
                    if (obj.getBoolean("Status")) {
                        Toast.makeText(CooperateTransProjectActivity.this, "提交成功", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(CooperateTransProjectActivity.this, obj.getString("Message"), Toast.LENGTH_SHORT).show();
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
     * 获取支付方式
     */
    private void getPayFlagList() {
        httpClient().get(URL_OA + "/getpayflag", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    JSONArray array = new JSONArray(getString(responseBody));
                    for (int i = 0; i < array.length(); i++) {
                        mData.add(array.getJSONObject(i).getString("text"));
                    }
                    mAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

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
