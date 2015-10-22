package ideatc.jinxiang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.AppCompatSpinner;
import android.text.TextUtils;
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
import ideatc.jinxiang.bean.crm.CooperateChanceDetailBean;
import ideatc.jinxiang.utils.ParamsUtil;

public class CooperateAddContactActivity extends ToolBarBaseActivity {

    @Bind(R.id.name)
    EditText nameEditText;
    @Bind(R.id.phone)
    EditText phoneEditText;
    @Bind(R.id.title)
    AppCompatSpinner titleSpinner;
    @Bind(R.id.contact_man)
    AppCompatSpinner contactManSpinner;
    @Bind(R.id.isMain)
    AppCompatCheckBox isMainCheckBox;
    @Bind(R.id.position)
    AppCompatSpinner rolesSpinner;
    @Bind(R.id.support_lv)
    AppCompatSpinner supportLvSpinner;
    @Bind(R.id.id_btn_confirm)
    AppCompatButton mConfirmBtn;

    private int customerId;

    // 称谓
//    private int titleId;
    private List<String> mTitles = new ArrayList<>();
    //    private List<Integer> mTitleIds = new ArrayList<>();
    private ArrayAdapter<String> mAdapter;
    private String title;

    // 角色
    private int roleId;
    private List<String> mRoles = new ArrayList<>();
    private List<Integer> mRolesIds = new ArrayList<>();
    private ArrayAdapter<String> mAdapterRoles;
    private String roleStr;

    // 支持程度
    private int supportId;
    private List<String> mSupportDatas = new ArrayList<>();
    private List<Integer> mSupportIds = new ArrayList<>();
    private ArrayAdapter<String> supportAdapter;
    private String supportStr;

    // 客户联系人
    private List<Integer> contactIds = new ArrayList<>();
    private List<String> mContactDatas = new ArrayList<>();
    private ArrayAdapter<String> contactAdapter;
    private int contactId = 17;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cooperate_add_contact);
        ButterKnife.bind(this);
        if (getIntent() != null) customerId = getIntent().getIntExtra("customerId", 0);
        initToolbar();
        initView();
        initData();

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
                        mTitles.add(array.getJSONObject(i).getString("text"));
//                        mTitleIds.add(array.getJSONObject(i).getInt("id"));
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
     * 获取角色
     */
    private void getRoles() {
        httpClient().get(URL_OA + "/getroletype", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    JSONArray array = new JSONArray(getString(responseBody));
                    for (int i = 0; i < array.length(); i++) {
                        mRoles.add(array.getJSONObject(i).getString("text"));
                        mRolesIds.add(array.getJSONObject(i).getInt("id"));
                    }
                    mAdapterRoles.notifyDataSetChanged();
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
     * 获取支持程度
     */
    private void getSupportLv() {
        httpClient().get(URL_OA + "/getsupporttype", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    JSONArray array = new JSONArray(getString(responseBody));
                    for (int i = 0; i < array.length(); i++) {
                        mSupportDatas.add(array.getJSONObject(i).getString("text"));
                        mSupportIds.add(array.getJSONObject(i).getInt("id"));
                    }
                    supportAdapter.notifyDataSetChanged();
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
     * 客户联系人列表
     */
    private void getCustomerContacts() {
        RequestParams params = ParamsUtil.getSignParams();
        params.put("cid", customerId);
        params.put("uid", user.getId());
        httpClient().get(URL_OA + "/getcustcontact", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    JSONArray array = new JSONArray(getString(responseBody));
                    for (int i = 0; i < array.length(); i++) {
                        mContactDatas.add(array.getJSONObject(i).getString("text"));
                        contactIds.add(array.getJSONObject(i).getInt("id"));
                    }
                    contactAdapter.notifyDataSetChanged();
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
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mTitles);
        titleSpinner.setAdapter(mAdapter);

        mAdapterRoles = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mRoles);
        rolesSpinner.setAdapter(mAdapterRoles);

        supportAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mSupportDatas);
        supportLvSpinner.setAdapter(supportAdapter);

        contactAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mContactDatas);
        contactManSpinner.setAdapter(contactAdapter);

        getContactTitle();
        getRoles();
        getSupportLv();
        getCustomerContacts();
    }

    @Override
    public void initView() {
        titleSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                titleId = mTitleIds.get(position);
                title = mAdapter.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        contactManSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                contactId = contactIds.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        supportLvSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                supportId = mSupportIds.get(position);
                supportStr = supportAdapter.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        rolesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                roleId = mRolesIds.get(position);
                roleStr = mAdapterRoles.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mConfirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirm();
            }
        });
    }

    /**
     * 提交数据
     */
    private void confirm() {
        String name = nameEditText.getText().toString();
        String phone = phoneEditText.getText().toString();
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(phone)) {
            Toast.makeText(CooperateAddContactActivity.this, "信息没有填写完整", Toast.LENGTH_SHORT).show();
            return;
        }

        CooperateChanceDetailBean.CooperateModel.ContactsEntity entity = new CooperateChanceDetailBean.CooperateModel.ContactsEntity();
        entity.setContactName(name);
        entity.setContactPhone(phone);
        entity.setContactTitle(title);
        entity.setRoleStr(roleStr);
        entity.setRole(roleId);
        entity.setSupportStr(supportStr);
        entity.setSupport(supportId);
        entity.setContactId(contactId);
        entity.setId(0);
        entity.setIsMain(isMainCheckBox.isChecked());

        Intent it = new Intent();
        it.putExtra("contactEntity", entity);
        setResult(NewCooperateContactsAndMattersActivity.REQUEST_CODE_CONTACT, it);
        finish();
    }

    @Override
    public void initToolbar() {
        getToolbar().setNavigationIcon(R.drawable.back);
        getToolbar().setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setRes();
            }
        });
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        setRes();
    }

    private void setRes() {
        setResult(NewCustomerContactInfoActivity.REQUEST_CODE_BACK, new Intent());
        finish();
    }
}
