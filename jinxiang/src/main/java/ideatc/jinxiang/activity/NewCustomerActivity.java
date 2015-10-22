package ideatc.jinxiang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
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

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import ideatc.jinxiang.R;
import ideatc.jinxiang.base.activity.ToolBarBaseActivity;
import ideatc.jinxiang.bean.crm.CustomerDetailBean;
import ideatc.jinxiang.utils.ParamsUtil;

public class NewCustomerActivity extends ToolBarBaseActivity {


    @Bind(R.id.employee)
    EditText mEmployee;
    @Bind(R.id.customer_name)
    EditText mCustomerName;
    @Bind(R.id.customer_type)
    AppCompatSpinner mCustomerTypeSpinner;
    @Bind(R.id.latencyLevel)
    AppCompatSpinner mLatencyLevelSpinner;
    @Bind(R.id.customer_Level)
    AppCompatSpinner mCustomerLevelSpinner;
    @Bind(R.id.customerSource)
    AppCompatSpinner mCustomerSourceSpinner;
    @Bind(R.id.customer_area)
    AppCompatSpinner mCustomerAreaSpinner;
    @Bind(R.id.trade)
    AppCompatSpinner mTradeSpinner;
    @Bind(R.id.intent)
    AppCompatSpinner mIntentSpinner;
    @Bind(R.id.customer_addr)
    EditText mCustomerAddr;
    @Bind(R.id.customer_employee_num)
    EditText mCustomerEmployeeNum;
    @Bind(R.id.customer_annual_turnover)
    EditText mCustomerAnnualTurnover;
    @Bind(R.id.register_capital)
    EditText mRegisterCapital;
    @Bind(R.id.founding_time)
    EditText mFoundingTime;
    @Bind(R.id.legal_addr)
    EditText mLegalAddr;
    @Bind(R.id.remark)
    EditText mRemark;
//    @Bind(R.id.id_button_next_step)
//    Button mButtonNextStep;

    private List<String> mData;
    private List<String> mData1;
    private List<String> mData2;
    private List<String> mData3;
    private List<String> mData4;
    private List<String> mData5;
    private List<String> mData6;
    private List<String> mData7;
    private ArrayAdapter<String> mAdapter;
    private ArrayAdapter<String> mAdapter1;
    private ArrayAdapter<String> mAdapter2;
    private ArrayAdapter<String> mAdapter3;
    private ArrayAdapter<String> mAdapter4;
    private ArrayAdapter<String> mAdapter5;
    private ArrayAdapter<String> mAdapter6;

    /**
     * spinner item value
     */
    private int typeStr;
    private String custLvStr = "";
    private String latencylvStr = "";
    private String custSourceStr = "";
    private String areaStr = "";
    private int tradeId = 3;
    private int intentId = 1;

    private void goNextStep() {
        String employeeNo = mEmployee.getText().toString();
        String cusName = mCustomerName.getText().toString();
        String addr = mCustomerAddr.getText().toString();
        if (TextUtils.isEmpty(employeeNo)) {
            Toast.makeText(NewCustomerActivity.this, getString(R.string.content_not_right), Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(cusName)) {
            Toast.makeText(NewCustomerActivity.this, "请填写客户姓名!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(addr)) {
            Toast.makeText(NewCustomerActivity.this, "请填写客户地址!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(latencylvStr) || getString(R.string.please_choose).equals(latencylvStr)) {
            Toast.makeText(NewCustomerActivity.this, "请填写客户潜在级别!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(custLvStr) || getString(R.string.please_choose).equals(custLvStr)) {
            Toast.makeText(NewCustomerActivity.this, "请客户客户等级!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(areaStr) || getString(R.string.please_choose).equals(areaStr)) {
            Toast.makeText(NewCustomerActivity.this, "请填写客户所属片区!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(custSourceStr) || getString(R.string.please_choose).equals(custSourceStr)) {
            Toast.makeText(NewCustomerActivity.this, "请填写客户来源!", Toast.LENGTH_SHORT).show();
            return;
        }


        CustomerDetailBean.CustomerBean customerBean = new CustomerDetailBean.CustomerBean();
        customerBean.setAddr(addr);
        //年营业额
        if (!TextUtils.isEmpty(mCustomerAnnualTurnover.getText().toString()))
            customerBean.setAnnualTurnover(Integer.parseInt(mCustomerAnnualTurnover.getText().toString()));
        customerBean.setModifyByNo(employeeNo);
        customerBean.setName(cusName);
        customerBean.setId(0);
        customerBean.setLatencyLevel(latencylvStr);
        customerBean.setCustSource(custSourceStr);
        customerBean.setCustArea(areaStr);
        customerBean.setCustLevel(custLvStr);
        customerBean.setCustType(typeStr);
        //成立日期
        customerBean.setFoundingTimeStr(mFoundingTime.getText().toString());
        //法定地址
        customerBean.setLegalAddr(mLegalAddr.getText().toString());
        //注册资金
        if (!TextUtils.isEmpty(mRegisterCapital.getText().toString()))
            customerBean.setRegisteredCapital(Integer.parseInt(mRegisterCapital.getText().toString()));
        customerBean.setTradeId(tradeId);
        customerBean.setIntent(intentId);
        customerBean.setRemark(mRemark.getText().toString());
        //职员数
        if (!TextUtils.isEmpty(mCustomerEmployeeNum.getText().toString()))
            customerBean.setStaffNum(Integer.parseInt(mCustomerEmployeeNum.getText().toString()));
        customerBean.setHonors(mData7);
        Intent it = new Intent(NewCustomerActivity.this, NewCustomerContactsActivity.class);
        it.putExtra("customerBean", customerBean);
        startActivity(it);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_customer);
        ButterKnife.bind(this);
        initToolbar();
        initData();
        initView();

    }

    @Override
    public void initData() {
        mData = new ArrayList<>();
        mData1 = new ArrayList<>();
        mData2 = new ArrayList<>();
        mData3 = new ArrayList<>();
        mData4 = new ArrayList<>();
        mData5 = new ArrayList<>();
        mData6 = new ArrayList<>();
        mData7 = new ArrayList<>();
    }

    @Override
    public void initView() {
        mEmployee.setText(user.getId());

        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mData);
        mAdapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mData1);
        mAdapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mData2);
        mAdapter3 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mData3);
        mAdapter4 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mData4);
        mAdapter5 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mData5);
        mAdapter6 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mData6);
//        mAdapter7 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mData7);
        getCustomerType();
        getLatencyLevel(1);
        getLatencyLevel(2);
        getLatencyLevel(3);
        getLatencyLevel(4);
        getLatencyLevel(6);
        getTrade();
        getCooperateIntent();
        mCustomerAreaSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                areaStr = mAdapter4.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mCustomerSourceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                custSourceStr = mAdapter3.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mIntentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                intentId = position + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mTradeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tradeId = position + 3;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mCustomerLevelSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                custLvStr = mAdapter2.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mLatencyLevelSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                latencylvStr = mAdapter1.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mCustomerTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                typeStr = position + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//        mButtonNextStep.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //执行下一步操作
//                goNextStep();
//            }
//        });
    }


    /**
     * 客户类型
     */
    private void getCustomerType() {
        httpClient().get(URL_OA + "/getcusttype", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    JSONArray array = new JSONArray(getString(responseBody));
                    mData.clear();
                    for (int i = 0; i < array.length(); i++) {
                        mData.add(array.getJSONObject(i).getString("text"));
                    }
                    mCustomerTypeSpinner.setAdapter(mAdapter);
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
        });
    }

    /**
     * 行业
     */
    private void getTrade() {
        httpClient().get(URL_OA + "/gettradetree", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    JSONArray array = new JSONArray(getString(responseBody));
                    mData5.clear();
                    for (int i = 0; i < array.length(); i++) {
                        mData5.add(array.getJSONObject(i).getString("text"));
                    }
                    mTradeSpinner.setAdapter(mAdapter5);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }

        });
    }

    /**
     * 业务事项列表,合作意图
     */
    private void getCooperateIntent() {
        httpClient().get(URL_OA + "/getmatterlist", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    JSONArray array = new JSONArray(getString(responseBody));
                    mData6.clear();
                    for (int i = 0; i < array.length(); i++) {
                        mData6.add(array.getJSONObject(i).getString("text"));
                    }
                    mIntentSpinner.setAdapter(mAdapter6);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }

            @Override
            public void onFinish() {
                super.onFinish();
                dialog.dismiss();
            }
        });
    }

    /**
     * 四种
     *
     * @param flag
     */
    private void getLatencyLevel(final int flag) {
        RequestParams params = ParamsUtil.getSignParams();
        params.put("flag", flag);
        httpClient().get(URL_OA + "/getdictionarylist", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    JSONArray array = new JSONArray(getString(responseBody));
                    switch (flag) {
                        case 1:
                            for (int i = 0; i < array.length(); i++) {
                                mData1.add(array.getJSONObject(i).getString("text"));
                            }
                            mLatencyLevelSpinner.setAdapter(mAdapter1);
                            break;
                        case 2:
                            for (int i = 0; i < array.length(); i++) {
                                mData2.add(array.getJSONObject(i).getString("text"));
                            }
                            mCustomerLevelSpinner.setAdapter(mAdapter2);
                            break;
                        case 3:
                            for (int i = 0; i < array.length(); i++) {
                                mData3.add(array.getJSONObject(i).getString("text"));
                            }
                            mCustomerSourceSpinner.setAdapter(mAdapter3);
                            break;
                        case 4:
                            for (int i = 0; i < array.length(); i++) {
                                mData4.add(array.getJSONObject(i).getString("text"));
                            }
                            mCustomerAreaSpinner.setAdapter(mAdapter4);
                        case 6:
                            for (int i = 0; i < array.length(); i++) {
                                mData6.add(array.getJSONObject(i).getString("text"));
                            }
//                            mHonorSpinner.setAdapter(mAdapter6);
                            break;
                    }

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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_customer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            goNextStep();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
