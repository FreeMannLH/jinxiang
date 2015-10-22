package ideatc.jinxiang.fragment.project_detail_activity;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import ideatc.jinxiang.R;
import ideatc.jinxiang.base.fragment.BaseFragment;
import ideatc.jinxiang.bean.crm.ProjectDetailBean;

public class ProjectBasicInfoFragment extends BaseFragment {

    @Bind(R.id.id_serial_number)
    TextView mSerialNumber;
    @Bind(R.id.id_title)
    TextView mTitle;
    @Bind(R.id.id_customer_name)
    TextView mCustomerName;
    @Bind(R.id.id_project_price)
    TextView mProjectPrice;
    @Bind(R.id.id_payment_mode)
    TextView mPaymentMode;
    @Bind(R.id.id_contract_no)
    TextView mContractNo;
    @Bind(R.id.id_remark)
    TextView mRemark;

    private ProjectDetailBean.ModelEntity modelEntity;

    public static ProjectBasicInfoFragment newInstance(ProjectDetailBean.ModelEntity modelEntity) {
        ProjectBasicInfoFragment fragment = new ProjectBasicInfoFragment();
        Bundle args = new Bundle();
        args.putParcelable("modelEntity", modelEntity);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) modelEntity = getArguments().getParcelable("modelEntity");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_project_basic_info, container, false);
        ButterKnife.bind(this, view);
        initData();
        return view;
    }

    private void initData() {
        mContractNo.setText(modelEntity.getContractNo() + "");
        mCustomerName.setText(modelEntity.getCustomerName() + "");
        mSerialNumber.setText(modelEntity.getId() + "");
        mPaymentMode.setText(modelEntity.getPayFlagStr() + "");
        mProjectPrice.setText(modelEntity.getPrice() + "");
        mRemark.setText(modelEntity.getRemark() + "");
        mTitle.setText(modelEntity.getTitle() + "");
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
