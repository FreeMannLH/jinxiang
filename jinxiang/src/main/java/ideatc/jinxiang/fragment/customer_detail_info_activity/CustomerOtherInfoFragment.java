package ideatc.jinxiang.fragment.customer_detail_info_activity;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

import butterknife.Bind;
import butterknife.ButterKnife;
import ideatc.jinxiang.R;
import ideatc.jinxiang.base.activity.BaseActivity;
import ideatc.jinxiang.base.fragment.BaseFragment;
import ideatc.jinxiang.bean.crm.CustomerDetailBean;
import ideatc.jinxiang.bean.crm.CustomerListBean;
import ideatc.jinxiang.utils.ParamsUtil;

public class CustomerOtherInfoFragment extends BaseFragment {

    @Bind(R.id.id_customer_name)
    TextView mSetUpDate;
    @Bind(R.id.id_customer_type)
    TextView mStaffCount;
    @Bind(R.id.id_customer_level)
    TextView mRegisteredCapital;
    @Bind(R.id.id_customer_resource)
    TextView mYearTurnover;
    @Bind(R.id.id_customer_qianzai_level)
    TextView mLegalAddr;

    private CustomerListBean.CustomerBean customerBean;

    public static CustomerOtherInfoFragment newInstance(CustomerListBean.CustomerBean customerBean) {
        CustomerOtherInfoFragment fragment = new CustomerOtherInfoFragment();
        Bundle args = new Bundle();
        args.putParcelable("customerBean", customerBean);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            customerBean = getArguments().getParcelable("customerBean");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_other_info, container, false);
        ButterKnife.bind(this, view);
        getCustomerDetailInfo();
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    /**
     * 获取客户详细
     */
    private void getCustomerDetailInfo() {

        String url = URL_OA + "/getcustomerinfo";
        RequestParams params = ParamsUtil.getSignParams();
        params.put("uid", user.getId());
        params.put("customer", customerBean.getId());
        httpClient().get(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = BaseActivity.getString(responseBody);
                Log.e("客户详情", statusCode + "," + result);
                CustomerDetailBean customerDetailBean = new Gson().fromJson(result, CustomerDetailBean.class);
                CustomerDetailBean.CustomerBean modelEntity = customerDetailBean.getModel();

                mLegalAddr.setText(modelEntity.getLegalAddr() + "");
                mRegisteredCapital.setText(modelEntity.getRegisteredCapital() + "");
                mSetUpDate.setText(modelEntity.getFoundingTimeStr() + "");
                mStaffCount.setText(modelEntity.getStaffNum() + "");
                mYearTurnover.setText(modelEntity.getAnnualTurnover() + "");
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("客户详情", statusCode + "," + BaseActivity.getString(responseBody));
            }

            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onFinish() {
                super.onFinish();
            }
        });
    }
}
