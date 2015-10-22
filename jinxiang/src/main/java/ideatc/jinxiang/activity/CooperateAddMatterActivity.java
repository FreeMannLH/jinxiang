package ideatc.jinxiang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatSpinner;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.loopj.android.http.AsyncHttpResponseHandler;

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

public class CooperateAddMatterActivity extends ToolBarBaseActivity {


    @Bind(R.id.title)
    AppCompatSpinner matterNameSpinner;
    @Bind(R.id.office_cost)
    EditText officeCostEditText;
    @Bind(R.id.agent_cost)
    EditText agentCostEditText;
    @Bind(R.id.other_cost)
    EditText otherCostEditText;
    @Bind(R.id.total_cost)
    EditText totalCostEditText;
    @Bind(R.id.remark)
    EditText remarkEditText;
    @Bind(R.id.id_btn_confirm)
    AppCompatButton mBtnConfirm;

    private List<String> mattersList = new ArrayList<>();
    private List<Integer> ids = new ArrayList<>();
    private ArrayAdapter<String> mAdapter;
    private String matterStr = "";
    private int matterId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cooperate_add_matter);
        ButterKnife.bind(this);
        initToolbar();
        initView();
        initData();
        getMatters();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mattersList);
        matterNameSpinner.setAdapter(mAdapter);
        matterNameSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                matterId = ids.get(position);
                matterStr = mAdapter.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mBtnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirm();
            }
        });
    }

    /**
     * 确认
     */
    private void confirm() {
        int offCost, otherCost, agentCost, totalCost;
        if (TextUtils.isEmpty(officeCostEditText.getText().toString())) {
            offCost = 0;
        } else {
            offCost = Integer.parseInt(officeCostEditText.getText().toString());
        }
        if (TextUtils.isEmpty(otherCostEditText.getText().toString())) {
            otherCost = 0;
        } else {
            otherCost = Integer.parseInt(otherCostEditText.getText().toString());
        }
        if (TextUtils.isEmpty(agentCostEditText.getText().toString())) {
            agentCost = 0;
        } else {
            agentCost = Integer.parseInt(agentCostEditText.getText().toString());
        }

        String remark = remarkEditText.getText().toString();
        totalCost = offCost + otherCost + agentCost;
        totalCostEditText.setText(totalCost + "");
        CooperateChanceDetailBean.CooperateModel.MattersEntity matterEntity = new CooperateChanceDetailBean.CooperateModel.MattersEntity();
        matterEntity.setId(0);
        matterEntity.setMatterName(matterStr);
        matterEntity.setMatterId(matterId);
        matterEntity.setOfficialCost(offCost);
        matterEntity.setOtherCost(otherCost);
        matterEntity.setTotalCost(totalCost);
        matterEntity.setAgencyCost(agentCost);
        matterEntity.setRemark(remark);

        Intent it = new Intent();
        it.putExtra("matterEntity", matterEntity);
        setResult(NewCooperateContactsAndMattersActivity.REQUEST_CODE_MATTER, it);
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

    /**
     * 获取业务事项列表
     */
    private void getMatters() {
        httpClient().get(URL_OA + "/getmatterlist", new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    JSONArray array = new JSONArray(getString(responseBody));
                    for (int i = 0; i < array.length(); i++) {
                        mattersList.add(array.getJSONObject(i).getString("text"));
                        ids.add(array.getJSONObject(i).getInt("id"));
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


    private void setRes() {
        setResult(NewCustomerContactInfoActivity.REQUEST_CODE_BACK, new Intent());
        finish();
    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        setRes();
    }
}
