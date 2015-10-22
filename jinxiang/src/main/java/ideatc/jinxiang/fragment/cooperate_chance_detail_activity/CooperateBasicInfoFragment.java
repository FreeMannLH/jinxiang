package ideatc.jinxiang.fragment.cooperate_chance_detail_activity;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import ideatc.jinxiang.R;
import ideatc.jinxiang.base.fragment.BaseFragment;
import ideatc.jinxiang.bean.crm.CooperateChanceDetailBean;

/**
 * @author ccb
 *         create at 2015/9/18 8:46
 */
public class CooperateBasicInfoFragment extends BaseFragment {


    @Bind(R.id.order_no)
    TextView orderNo;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.stage)
    TextView stage;
    @Bind(R.id.customer_name)
    TextView customerName;
    @Bind(R.id.id_customer_manager)
    TextView customerManager;
    @Bind(R.id.remark)
    TextView remark;

    private CooperateChanceDetailBean.CooperateModel model;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_cooperate_basic_info, container, false);
        ButterKnife.bind(this, view);
        orderNo.setText(model.getId());
        title.setText(model.getTitle());
        stage.setText(model.getStageStr());
        customerName.setText(model.getCustomerName());
        customerManager.setText(model.getCustManagerName());
        remark.setText(model.getRemark());

        return view;
    }

    public static CooperateBasicInfoFragment newInstance(CooperateChanceDetailBean.CooperateModel model) {
        CooperateBasicInfoFragment fragment = new CooperateBasicInfoFragment();
        Bundle args = new Bundle();
        args.putParcelable("model", model);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            model = getArguments().getParcelable("model");
        }
    }

}
