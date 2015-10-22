package ideatc.jinxiang.activity;

import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatSpinner;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
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
import ideatc.jinxiang.utils.ParamsUtil;
import ideatc.jinxiang.utils.SPUtils;

public class PostProjectToFlowActivity extends ToolBarBaseActivity implements CheckBox.OnCheckedChangeListener {


    private static final String TAG = "提交到流程";
    private static final int ADMIN_ID = 0;
    //
//    @Bind(R.id.next_node_operator)
//    AppCompatSpinner nextNodeOperatorSpinner;
    @Bind(R.id.handle_opinion)
    EditText handleOpinionEditText;
    @Bind(R.id.id_btn_confirm)
    AppCompatButton mBtnConfirm;
    @Bind(R.id.id_next_step_spinner)
    AppCompatSpinner nextStepSpinner;

    @Bind(R.id.layout)
    LinearLayout layout;

    private int keyId;//流程id


//    private ArrayAdapter<String> mAdapter;

    private ArrayAdapter<String> mAdapterStep;
    private List<String> stepValueList = new ArrayList<>();
    //    private List<String> mData = new ArrayList<>();
    private List<String> mIds = new ArrayList<>();

    //    private String nameId = "";
    private String selectName = "";
    private List<String> handlerValueList = new ArrayList<>();
    private Map<String, String> idsMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_project_to_flow);
        ButterKnife.bind(this);
        if (getIntent() != null) keyId = getIntent().getIntExtra("keyId", 0);
        initToolbar();
        initView();
        initData();
        getNodeOperator();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
//        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, handlerValueList);
        mAdapterStep = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, stepValueList);

//        nextNodeOperatorSpinner.setAdapter(mAdapter);
        nextStepSpinner.setAdapter(mAdapterStep);

        nextStepSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //移除全部checkBox
                layout.removeAllViews();
                //清空勾选集合
                checkedIdList.clear();

                selectName = mAdapterStep.getItem(position);
                String key = stepMap.get(selectName);


                handlerValueList.clear();
//                mAdapter.notifyDataSetChanged();

                if (!TextUtils.isEmpty(key)) {
                    JSONArray array = handlerMap.get(key);
                    Log.e("获得的数组的大小", array.length() + "");
                    if (array.length() != 0) {
                        for (int i = 0, size = array.length(); i < size; i++) {
                            try {


                                JSONObject obj = array.getJSONObject(i);
                                String name = obj.getString("Name");
                                handlerValueList.add(name);
                                idsMap.put(name, obj.getString("Id"));

                                CheckBox checkBox = new CheckBox(PostProjectToFlowActivity.this);
//                                checkBox.setPadding(10, 10, 10, 10);
                                if (!obj.getString("Id").equals("admin")) {
                                    checkBox.setId(Integer.parseInt(obj.getString("Id")));
                                } else {
                                    checkBox.setId(ADMIN_ID);
                                }
                                checkBox.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                                checkBox.setText(name);
                                checkBox.setOnCheckedChangeListener(PostProjectToFlowActivity.this);
                                layout.addView(checkBox);


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
//                        mAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

//        nextNodeOperatorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                nameId = mIds.get(position);
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

        mBtnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirm();
            }
        });
    }

    /**
     * 提交到流程
     */
    private void confirm() {
        List<String> list = new ArrayList<>();
        for (String id : checkedIdList) {
            list.clear();
            if (Integer.parseInt(id) == ADMIN_ID) {
                id = "admin";
            }
            list.add(id);
        }
        Log.e("checked id json list", new Gson().toJson(list));
        String opinion = handleOpinionEditText.getText().toString();
        if (TextUtils.isEmpty(opinion)) {
            Toast.makeText(PostProjectToFlowActivity.this, R.string.write_suggestion, Toast.LENGTH_SHORT).show();
            return;
        }

        RequestParams params = ParamsUtil.getSignParams();
        params.put("keyId", keyId);
        params.put("uid", SPUtils.getInt("id", 0));
//        params.put("userId", nameId);
        params.put("userId", new Gson().toJson(list));
        params.put("suggestion", opinion);

        Log.e(TAG, params.toString());


        httpClient().get(URL_OA + "/sendcontracttoflow", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = getString(responseBody);
                Log.e(TAG, result);


                try {
                    JSONObject obj = new JSONObject(result);
                    if (obj.getBoolean("Status")) {
                        Toast.makeText(PostProjectToFlowActivity.this, obj.getString("Message"), Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(PostProjectToFlowActivity.this, obj.getString("Message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e(TAG, statusCode + "");
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


    private List<String> stepKeyList = new ArrayList<>();
    private Map<String, String> stepMap = new HashMap<>();
    private Map<String, JSONArray> handlerMap = new HashMap<>();

    /**
     * 获取下一节点操作人员
     */
    private void getNodeOperator() {

        String url = URL_OA + "/SysFlowNodeOperator";
        RequestParams params = ParamsUtil.getSignParams();
        params.put("sysFlowId", 23);
        params.put("sysUserId", SPUtils.getInt("id", 0));
        params.put("statusId", 0);
        httpClient().get(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = getString(responseBody);
                Log.e("获取下一节点操作人员", result);

                Log.e("同意", result);

                try {

                    JSONObject object = new JSONObject(result);
                    JSONArray stepArray = object.getJSONArray("NextNodeList");
                    if (stepArray != null && stepArray.length() != 0) {
                        for (int i = 0, size = stepArray.length(); i < size; i++) {
                            JSONObject obj = stepArray.getJSONObject(i);
                            String value = obj.getString("Value");
                            String key = obj.getString("Key");
                            stepValueList.add(value);
                            stepKeyList.add(key);
                            stepMap.put(value, key);
                        }
                        mAdapterStep.notifyDataSetChanged();
                    }
                    JSONArray nameArray = object.getJSONArray("SysEmployeeList");
                    if (nameArray != null && nameArray.length() != 0) {
                        for (int i = 0, size = nameArray.length(); i < size; i++) {
                            JSONObject obj = nameArray.getJSONObject(i);
                            String key = obj.getString("Key");
                            JSONArray array = obj.getJSONArray("Value");
                            handlerMap.put(key, array);
                            for (int k = 0; k < array.length(); k++) {
                                JSONObject ooj = array.getJSONObject(k);
//                                mData.add(ooj.getString("Name"));
                                mIds.add(ooj.getString("Id"));
                            }
                        }
                    }
//                    JSONArray nameArray = new JSONObject(result).getJSONArray("SysEmployeeList");
//                    if (nameArray != null && nameArray.length() != 0) {
//                        for (int i = 0, size = nameArray.length(); i < size; i++) {
//                            JSONArray array = nameArray.getJSONObject(i).getJSONArray("Value");
//                            for (int k = 0; k < array.length(); k++) {
//                                JSONObject ooj = array.getJSONObject(k);
//                                mData.add(ooj.getString("Name"));
//                                mIds.add(ooj.getString("Id"));
//                            }
//                        }
//                        mAdapter.notifyDataSetChanged();
//                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("获取下一节点操作人员", statusCode + "");
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

    private List<String> checkedIdList = new ArrayList<>();

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        int id = buttonView.getId();
        if (isChecked) {
            checkedIdList.add(id + "");
        } else {
            checkedIdList.remove(id + "");
        }
    }
}
