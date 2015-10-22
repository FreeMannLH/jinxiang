package ideatc.jinxiang.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;
import ideatc.jinxiang.R;
import ideatc.jinxiang.base.activity.ToolBarBaseActivity;
import ideatc.jinxiang.bean.crm.VisitRecordListBean;
import ideatc.jinxiang.utils.ParamsUtil;

public class VisitDetailActivity extends ToolBarBaseActivity {

    @Bind(R.id.id_visit_theme)
    TextView mVisitTheme;
    @Bind(R.id.id_visit_time)
    TextView mVisitTime;
    @Bind(R.id.id_visit_customer)
    TextView mVisitCustomer;
    @Bind(R.id.id_visit_manager)
    TextView mVisitManager;
    @Bind(R.id.id_visit_address)
    TextView mVisitAddress;

    private int visitId;
    private String url = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit_detail);
        ButterKnife.bind(this);
        initToolbar();
        initData();
        getVisitDetail();
    }

    @Override
    public void initData() {
        url = URL_OA + "/getvisitinfo";
        if (getIntent() != null) visitId = getIntent().getIntExtra("visitId", 0);
    }

    @Override
    public void initView() {

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
     * 获取拜访详情
     */
    private void getVisitDetail() {
        RequestParams params = ParamsUtil.getSignParams();
        params.put("uid", user.getId());
        params.put("visit", visitId);
        httpClient().get(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    String result = getString(responseBody);
                    Log.e("visit detail", result);

                    try {
                        JSONObject obj = new JSONObject(result);
                        String beanStr = obj.getJSONObject("model").toString();
                        VisitRecordListBean.VisitRecordBean bean = new Gson().fromJson(beanStr, VisitRecordListBean.VisitRecordBean.class);
                        if (bean != null) {
                            mVisitAddress.setText(bean.getAddr());
                            mVisitCustomer.setText(bean.getCustName());
                            mVisitManager.setText(bean.getCustManagerName());
                            mVisitTheme.setText(bean.getTitle());
                            mVisitTime.setText(bean.getVisitTimeStr());
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("visitDetail", statusCode + "");
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
