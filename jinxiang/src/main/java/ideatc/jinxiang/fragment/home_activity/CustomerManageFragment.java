package ideatc.jinxiang.fragment.home_activity;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import ideatc.jinxiang.R;
import ideatc.jinxiang.activity.MyCooperateChanceActivity;
import ideatc.jinxiang.activity.MyCustomerActivity;
import ideatc.jinxiang.activity.MyProjectActivity;
import ideatc.jinxiang.activity.MyVisitRecordActivity;
import ideatc.jinxiang.activity.TravelRegActivity;
import ideatc.jinxiang.activity.VisitSignActivity;
import ideatc.jinxiang.base.fragment.BaseFragment;

public class CustomerManageFragment extends BaseFragment {


    @Bind(R.id.visit_sign)
    TextView mVisitSign;
    @Bind(R.id.customer_search)
    TextView mCustomerSearch;
    @Bind(R.id.visit_record)
    TextView mVisitRecord;
    @Bind(R.id.project_search)
    TextView mProjectSearch;
    @Bind(R.id.cooperateChance)
    TextView mCooperateChance;
    @Bind(R.id.travelReg)
    TextView mTravelReg;

    public static CustomerManageFragment newInstance() {
        CustomerManageFragment fragment = new CustomerManageFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_customer_manage, container, false);
        ButterKnife.bind(this, view);
        mVisitSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(VisitSignActivity.class);
            }
        });
        mCustomerSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(MyCustomerActivity.class);
            }
        });
        mVisitRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(MyVisitRecordActivity.class);
            }
        });
        mProjectSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(MyProjectActivity.class);
            }
        });
        mCooperateChance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(MyCooperateChanceActivity.class);
            }
        });
        mTravelReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(TravelRegActivity.class);
            }
        });


        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
