package ideatc.jinxiang.fragment.matter_detail_acitivity;


import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import ideatc.jinxiang.R;
import ideatc.jinxiang.base.fragment.BaseFragment;
import ideatc.jinxiang.bean.SysInfoLogBean;

public class SysFlowLogFragment extends BaseFragment {


    @Bind(R.id.recyclerView)
    RecyclerView mListView;

    private SysFlowLogItemAdapter mSysFlowLogItemAdapter;
    private String logStr;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<SysInfoLogBean> entities = new ArrayList<>();

    public static SysFlowLogFragment instance(String logStr) {
        SysFlowLogFragment fragment = new SysFlowLogFragment();
        Bundle args = new Bundle();
        args.putString("logStr", logStr);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Bundle bundle = getArguments();
            logStr = bundle.getString("logStr");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sysflow_log, container, false);
        ButterKnife.bind(this, view);

        entities = new Gson().fromJson(logStr, new TypeToken<List<SysInfoLogBean>>() {
        }.getType());

        mListView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mListView.setLayoutManager(mLayoutManager);
        mListView.setItemAnimator(new DefaultItemAnimator());
        mSysFlowLogItemAdapter = new SysFlowLogItemAdapter();
        mListView.setAdapter(mSysFlowLogItemAdapter);
        return view;
    }

    private class SysFlowLogItemAdapter extends RecyclerView.Adapter<SysFlowLogItemAdapter.MyHolder> {

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            MyHolder holder = new MyHolder(LayoutInflater.from(getActivity()).inflate(R.layout.layout_sysflow_log_item, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyHolder holder, int position) {

            SysInfoLogBean entity = entities.get(position);

            holder.mName.setText(""+entity.getUser());
            holder.mDate.setText(entity.getOperateDateStr());
            holder.mCurrentStep.setText("" + entity.getPrevNode());
            holder.mNextStep.setText("" + entity.getNode());
            holder.mNextStepHandler.setText(""+entity.getUser());
            holder.mOperateName.setText("" + entity.getStatusStr());
        }

        @Override
        public int getItemCount() {
            return entities.size();
        }

        public class MyHolder extends RecyclerView.ViewHolder {

            private TextView mName;
            private TextView mDate;
            private TextView mOperateName;
            private TextView mCurrentStep;
            private TextView mNextStepHandler;
            private TextView mNextStep;

            public MyHolder(View itemView) {

                super(itemView);
                mName = (TextView) itemView.findViewById(R.id.id_handler_name);
                mDate = (TextView) itemView.findViewById(R.id.id_log_date);
                mCurrentStep = (TextView) itemView.findViewById(R.id.id_current_step);
                mOperateName = (TextView) itemView.findViewById(R.id.id_operate_name);
                mNextStepHandler = (TextView) itemView.findViewById(R.id.id_next_step_handler);
                mNextStep = (TextView) itemView.findViewById(R.id.id_next_step);
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
