package ideatc.jinxiang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import ideatc.jinxiang.R;
import ideatc.jinxiang.base.activity.BaseActivity;
import ideatc.jinxiang.base.activity.ToolBarBaseActivity;
import ideatc.jinxiang.bean.crm.CooperateChanceDetailBean;
import ideatc.jinxiang.bean.crm.CustomerListBean;
import ideatc.jinxiang.utils.ParamsUtil;

public class NewCooperateActivity extends ToolBarBaseActivity {

    @Bind(R.id.employee)
    EditText employeeEditText;
    @Bind(R.id.title)
    EditText titleEditText;
    @Bind(R.id.stage)
    AppCompatSpinner stageSpinner;
    @Bind(R.id.remark)
    EditText remarkEditText;
    @Bind(R.id.customer_spinner)
    AppCompatSpinner customerSpinner;

    private List<String> mData;//阶段
    private ArrayAdapter<String> mAdapter;

    private List<String> mDataCus;//客户名称
    private List<Integer> mDataIds;//客户ID
    private ArrayAdapter<String> mAdapterCus;

    private int stageInt = 0;

    private int customerId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_cooperate);
        ButterKnife.bind(this);
        initToolbar();
        initView();
        initData();
        getStage();
        getMyAllCustomer();
    }


    /**
     * 获取阶段列表
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
    public void initData() {
        mData = new ArrayList<>();
        mDataCus = new ArrayList<>();
        mDataIds = new ArrayList<>();
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mData);
        mAdapterCus = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mDataCus);
    }

    @Override
    public void initView() {
        employeeEditText.setText(user.getId());
        stageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                stageInt = position + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        customerSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                customerId = mDataIds.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_new_cooperate, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.next_step) {
            goNextStep();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void goNextStep() {

        String title = titleEditText.getText().toString();
        String empNo = employeeEditText.getText().toString();
        String reMark = remarkEditText.getText().toString();

        if (TextUtils.isEmpty(title)) {
            Toast.makeText(NewCooperateActivity.this, "请填写标题！", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(empNo)) {
            Toast.makeText(NewCooperateActivity.this, "请填写工号！", Toast.LENGTH_SHORT).show();
            return;
        }
        if (stageInt == 0) {
            Toast.makeText(NewCooperateActivity.this, "请选择合作阶段！", Toast.LENGTH_SHORT).show();
            return;
        }

        CooperateChanceDetailBean.CooperateModel model = new CooperateChanceDetailBean.CooperateModel();
        model.setTitle(title);
        model.setRemark(reMark);
        model.setModifyByNo(empNo);
        model.setStage(stageInt);
        model.setId("0");
        model.setCustomerId(customerId);
        Intent it = new Intent(NewCooperateActivity.this,NewCooperateContactsAndMattersActivity.class);
        it.putExtra("model",model);
        startActivity(it);
        finish();
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

    private int page = 1;

    /**
     * 获取我所有的客户
     */
    public void getMyAllCustomer() {
        String url = URL_OA + "/getmycustomers";
        RequestParams params = ParamsUtil.getSignParams();
        params.put("uid", user.getId());
        params.put("page", page);
        params.put("flag", 1);
        httpClient().get(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.e("我的客户获取成功", BaseActivity.getString(responseBody));
                CustomerListBean customerListBean = new Gson().fromJson(BaseActivity.getString(responseBody), CustomerListBean.class);
                List<CustomerListBean.CustomerBean> list = customerListBean.getList();
                if (list == null || list.size() == 0) return;
                mDataCus.clear();
                mDataIds.clear();
                for (CustomerListBean.CustomerBean bean : list) {
                    mDataCus.add(bean.getName());
                    mDataIds.add(bean.getId());
                    Log.e("id",bean.getId()+"");
                }
                customerSpinner.setAdapter(mAdapterCus);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("根据flag获取客户", statusCode + "");
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
