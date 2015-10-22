package ideatc.jinxiang.activity;

import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatSpinner;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import ideatc.jinxiang.R;
import ideatc.jinxiang.base.activity.ToolBarBaseActivity;
import ideatc.jinxiang.bean.WaitHandleListBean;
import ideatc.jinxiang.utils.ParamsUtil;
import ideatc.jinxiang.utils.SPUtils;

public class AgreeActivity extends ToolBarBaseActivity implements View.OnClickListener {

    @Bind(R.id.id_next_step_spinner)
    AppCompatSpinner mNextStepSpinner;
    @Bind(R.id.id_solve_staff)
    AppCompatSpinner mSolveStaffSpinner;
    @Bind(R.id.id_agree_edittext)
    AppCompatEditText mAgreeEdittext;
    @Bind(R.id.id_agree_confirm_button)
    AppCompatButton mAgreeConfirmButton;
    @Bind(R.id.id_agree_cancel_button)
    AppCompatButton mAgreeCancelButton;


    private WaitHandleListBean.RowsEntity waitHandleBean;
    private String selectName = "";
    private String handler = "";

    private Map<String, String> idsMap = new HashMap<>();

    private List<String> stepValueList = new ArrayList<>();
    private List<String> stepKeyList = new ArrayList<>();
    private List<String> handlerValueList = new ArrayList<>();

    private Map<String, String> stepMap = new HashMap<>();
    private Map<String, JSONArray> handlerMap = new HashMap<>();

    private ArrayAdapter<String> mAdapter1;
    private ArrayAdapter<String> mAdapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agree);
        ButterKnife.bind(this);
        initToolbar();
        initView();
        initData();
        getAgreeNodeData();
    }

    @Override
    public void initData() {
        if (getIntent() != null) waitHandleBean = getIntent().getParcelableExtra("waitHandleBean");
    }

    @Override
    public void initView() {
        mAgreeCancelButton.setOnClickListener(this);
        mAgreeConfirmButton.setOnClickListener(this);
        mAdapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, stepValueList);
        mAdapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, handlerValueList);
        mNextStepSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectName = mAdapter1.getItem(position);
                String key = stepMap.get(selectName);
                Log.e("根据名字获得", key);
                handlerValueList.clear();
                mAdapter2.notifyDataSetChanged();
                if (!TextUtils.isEmpty(key)) {
                    JSONArray array = handlerMap.get(key);
                    Log.e("获得的数组的大小", array.length() + "");
                    if (array != null && array.length() != 0) {
                        for (int i = 0,size =array.length() ; i < size; i++) {
                            try {
                                JSONObject obj = array.getJSONObject(i);
                                String name = obj.getString("Name");
                                handlerValueList.add(name);
                                idsMap.put(name, obj.getString("Id"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                        mAdapter2.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        mSolveStaffSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                handler = mAdapter2.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                handler = "";
            }
        });
        mNextStepSpinner.setAdapter(mAdapter1);
        mSolveStaffSpinner.setAdapter(mAdapter2);

    }


    @Override
    public void initToolbar() {
        getToolbar().setTitle(getString(R.string.agree));
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
            case R.id.id_agree_confirm_button:
                try {
                    submit();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.id_agree_cancel_button:
                finish();
                break;
        }
    }


    /**
     * 同意
     */
    private void getAgreeNodeData() {

        String url = URL_OA + "/SysFlowNodeOperator";
        RequestParams params = ParamsUtil.getSignParams();
        params.put("sysFlowId", waitHandleBean.getSysFlowId());
        params.put("sysUserId", SPUtils.getInt("id", 0));
        params.put("statusId", waitHandleBean.getId());
//        Log.e("同意页面 sysFlowId", waitHandleBean.getSysFlowId() + "");
//        Log.e("sysUserId", SPUtils.getInt("id", 0) + "");
//        Log.e("statusId", waitHandleBean.getId() + "");
        httpClient().get(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = getString(responseBody);
                Log.e("同意", result);
                try {
                    JSONObject object = new JSONObject(result);
                    JSONArray stepArray = object.getJSONArray("NextNodeList");
                    if (stepArray != null && stepArray.length() != 0) {
                        for (int i = 0,size =stepArray.length() ; i < size; i++) {
                            JSONObject obj = stepArray.getJSONObject(i);
                            String value = obj.getString("Value");
                            String key = obj.getString("Key");
                            stepValueList.add(value);
                            stepKeyList.add(key);
                            stepMap.put(value, key);
                        }
                        mAdapter1.notifyDataSetChanged();
                    }

                    JSONArray nameArray = object.getJSONArray("SysEmployeeList");
                    if (nameArray != null && nameArray.length() != 0) {
                        for (int i = 0, size = nameArray.length(); i < size; i++) {
                            JSONObject obj = nameArray.getJSONObject(i);
                            String key = obj.getString("Key");
                            JSONArray array = obj.getJSONArray("Value");
                            handlerMap.put(key, array);
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("同意节点", statusCode + "");
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
     * 提交
     */
    private void submit() throws JSONException {
        boolean isFinish = false;
        if (stepValueList != null && stepValueList.size() != 0) {
            if (TextUtils.isEmpty(handler)) {
                Toast.makeText(AgreeActivity.this, "请选择处理人员", Toast.LENGTH_SHORT).show();
                return;
            }
        } else isFinish = true;

        if (TextUtils.isEmpty(mAgreeEdittext.getText().toString())) {
            Toast.makeText(AgreeActivity.this, "请填写意见", Toast.LENGTH_SHORT).show();
            return;
        }
        String url = URL_OA + "/SubmitSysFlow";
        RequestParams params = new RequestParams();
        params.put("sysUserId", SPUtils.getInt("id", 0));
        params.put("statusId", waitHandleBean.getId());
        params.put("suggestion", mAgreeEdittext.getText().toString());
        if (!isFinish) {
            String id = idsMap.get(handler);
            List<String> list = new ArrayList<>();
            list.add(id);
            params.put("employeeIdStr", list);
        } else {
            params.put("employeeIdStr", new ArrayList<>());
        }

        Log.e("点击提交 ",params.toString());
        httpClient().get(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = getString(responseBody);
                Log.e("点击提交", result);
                try {
                    JSONObject obj = new JSONObject(result);
                    if (obj.getBoolean("Status")) {
                        Toast.makeText(AgreeActivity.this, obj.getString("Message"), Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(AgreeActivity.this, obj.getString("Message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("提交", statusCode + "");
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
