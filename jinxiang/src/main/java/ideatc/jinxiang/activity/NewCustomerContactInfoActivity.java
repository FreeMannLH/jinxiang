package ideatc.jinxiang.activity;

import android.content.Intent;
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
import ideatc.jinxiang.bean.CustomerContactBean;
import ideatc.jinxiang.bean.crm.CustomerListBean;
import ideatc.jinxiang.utils.ParamsUtil;

public class NewCustomerContactInfoActivity extends ToolBarBaseActivity {

    @Bind(R.id.name)
    EditText mNameEditText;
    @Bind(R.id.title)
    AppCompatSpinner mTitleSpinner;
    @Bind(R.id.phone)
    EditText mPhoneEditText;
    @Bind(R.id.position)
    EditText mPositionEditText;
    @Bind(R.id.fax)
    EditText mFaxEditText;
    @Bind(R.id.email)
    EditText mEmailEditText;
    @Bind(R.id.addr)
    EditText mAddrEditText;
    @Bind(R.id.remark)
    EditText mRemarkEditText;
    @Bind(R.id.id_btn_confirm)
    AppCompatButton mButton;


    private static final String FLAG="flag";
    protected static final int REQUEST_CODE_BACK = 404;//返回键

    // 联系人称谓
    private List<String> mTitles = new ArrayList<>();
    private ArrayAdapter<String> mAdapter;

    private String title = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_customer_contact_info);
        ButterKnife.bind(this);
        initToolbar();
        initView();
        initData();
        getContactTitle();
    }

    @Override
    public void initData() {
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mTitles);
        mTitleSpinner.setAdapter(mAdapter);
    }

    @Override
    public void initView() {
        mTitleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                title = mAdapter.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirm();
            }
        });
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_new_customer_contact_info, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public void initToolbar() {
        getToolbar().setNavigationIcon(R.drawable.back);
        getToolbar().setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               onBackPressed();
            }
        });
    }

    /**
     * 获取联系人称谓
     */
    private void getContactTitle() {
        httpClient().get(URL_OA + "/getcontacttitle", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    JSONArray array = new JSONArray(getString(responseBody));
                    for (int i = 0; i < array.length(); i++) {
                        mTitles.add(array.getJSONObject(i).getString("id"));
                    }
                    mAdapter.notifyDataSetChanged();
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
     * 提交数据
     */
    private void confirm() {
        String name = mNameEditText.getText().toString() + "";
        String phone = mPhoneEditText.getText().toString() + "";
        String position = mPositionEditText.getText().toString() + "";
        String fax = mFaxEditText.getText().toString() + "";
        String email = mEmailEditText.getText().toString() + "";
        String addr = mAddrEditText.getText().toString() + "";
        String reMark = mRemarkEditText.getText().toString() + "";
        if (TextUtils.isEmpty(name)||TextUtils.isEmpty(phone)||TextUtils.isEmpty(title)) {
            Toast.makeText(NewCustomerContactInfoActivity.this, "信息未填写完整", Toast.LENGTH_SHORT).show();
            return;
        }

        CustomerContactBean contactBean = new CustomerContactBean();
        contactBean.setId(0);
        contactBean.setAddr(addr);
        contactBean.setRemark(reMark);
        contactBean.setEmail(email);
        contactBean.setFax(fax);
        contactBean.setName(name);
        contactBean.setPhone(phone);
        contactBean.setTitle(title);
        contactBean.setPost(position);
        Log.e(FLAG,getIntent().getStringExtra(FLAG));
        if (getIntent().getStringExtra(FLAG).equals(CustomerDetailInfoActivity.class.getSimpleName())) {

            CustomerListBean.CustomerBean customerBean = getIntent().getParcelableExtra("customerBean");
            String employeeNo = customerBean.getModifyByNo();

//            int customerId = customerBean.getId();
            addContactForMyCustomer(employeeNo, customerBean.getId(), contactBean);

        } else if (getIntent().getStringExtra(FLAG).equals("new")) {
            Intent it = new Intent();
            it.putExtra("contactBean", contactBean);
            setResult(1000, it);
        }
        finish();
    }

    /**
     * 我的客户,添加联系人
     */
    private void addContactForMyCustomer(String empNo, int customerId, CustomerContactBean contactBean) {

        RequestParams params = ParamsUtil.getSignParams();
        params.put("Id", 0);
        params.put("uid", empNo);
        params.put("CustomerId", customerId);
        params.put("Name", contactBean.getName());
        params.put("Title", contactBean.getTitle());
        params.put("Post", contactBean.getPost());
        params.put("Phone", contactBean.getPhone());
        params.put("Email", contactBean.getEmail());
        params.put("Fax", contactBean.getFax());
        params.put("Addr", contactBean.getAddr());
        params.put("Remark", contactBean.getRemark());
        httpClient().get(URL_OA + "/customercontactpost", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
//                {"status":true,"message":"数据操作成功","Other":""}
                String result = getString(responseBody);
                Log.e("添加联系人", result);
                try {
                    JSONObject object = new JSONObject(result);
                    if (object.getBoolean("Status")) {
                        Toast.makeText(NewCustomerContactInfoActivity.this, object.getString("Message"), Toast.LENGTH_SHORT).show();
                        finish();
                    } else
                        Toast.makeText(NewCustomerContactInfoActivity.this, object.getString("Message"), Toast.LENGTH_SHORT).show();
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
    public void onBackPressed() {
        setResult(REQUEST_CODE_BACK, new Intent());
        finish();
//        super.onBackPressed();
    }
}
