package ideatc.jinxiang.fragment.customer_detail_info_activity;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import ideatc.jinxiang.R;
import ideatc.jinxiang.base.fragment.BaseFragment;
import ideatc.jinxiang.bean.crm.CustomerListBean;

public class CustomerBasicInfoFragment extends BaseFragment {

    @Bind(R.id.id_customer_name)
    TextView mCustomerName;
    @Bind(R.id.id_customer_type)
    TextView mCustomerType;
    @Bind(R.id.id_customer_level)
    TextView mCustomerLevel;
    @Bind(R.id.id_customer_resource)
    TextView mCustomerResource;
    @Bind(R.id.id_customer_qianzai_level)
    TextView mCustomerQianzaiLevel;
    @Bind(R.id.id_customer_industry)
    TextView mCustomerIndustry;
    @Bind(R.id.id_customer_area)
    TextView mCustomerArea;
    @Bind(R.id.id_customer_addr)
    TextView mCustomerAddr;
    @Bind(R.id.id_customer_remark)
    TextView mCustomerRemark;
    private CustomerListBean.CustomerBean customerBean;

    public static CustomerBasicInfoFragment newInstance(CustomerListBean.CustomerBean customerBean) {
        CustomerBasicInfoFragment fragment = new CustomerBasicInfoFragment();
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
        View view = inflater.inflate(R.layout.fragment_customer_basic_info, container, false);
        ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {
        mCustomerAddr.setText(customerBean.getLegalAddr());
        mCustomerArea.setText(customerBean.getCustArea());
        mCustomerIndustry.setText(customerBean.getTradeName());
        mCustomerLevel.setText(customerBean.getCustLevel());
        mCustomerName.setText(customerBean.getName());
        mCustomerQianzaiLevel.setText(customerBean.getLatencyLevel());
        mCustomerRemark.setText(customerBean.getRemark());
        mCustomerResource.setText(customerBean.getCustSource());
        mCustomerType.setText(customerBean.getCustTypeStr());
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
