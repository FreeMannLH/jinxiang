package ideatc.jinxiang.fragment.cooperate_chance_detail_activity;


import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import ideatc.jinxiang.R;
import ideatc.jinxiang.base.fragment.BaseFragment;
import ideatc.jinxiang.bean.crm.CooperateChanceDetailBean;

public class CooperateStageFragment extends BaseFragment {

    @Bind(R.id.recyclerView)
    RecyclerView mListView;

    private List<CooperateChanceDetailBean.CooperateModel.StageLogsEntity> mData = new ArrayList<>();
    private RecyclerView.LayoutManager mLayoutManager;
    private ProjectRateItemAdapter mProjectRateItemAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cooperate_stage, container, false);
        ButterKnife.bind(this, view);
        mListView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mListView.setLayoutManager(mLayoutManager);
        mListView.setItemAnimator(new DefaultItemAnimator());
        mProjectRateItemAdapter = new ProjectRateItemAdapter();
        mListView.setAdapter(mProjectRateItemAdapter);
        return view;
    }

    private class ProjectRateItemAdapter extends RecyclerView.Adapter<ProjectRateItemAdapter.MyHolder> {

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyHolder holder = new MyHolder(LayoutInflater.from(getActivity()).inflate(R.layout.cooperate_stage_item, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyHolder holder, int position) {

            CooperateChanceDetailBean.CooperateModel.StageLogsEntity schedulesEntity = mData.get(position);
            if (schedulesEntity != null) {
                holder.mTime.setText(schedulesEntity.getCreateTimeStr() + "");
                holder.mStaff.setText(schedulesEntity.getCreateByName() + "");
                holder.mReason.setText(schedulesEntity.getReason() + "");
                holder.mStage.setText(schedulesEntity.getStageStr() + "");
            }
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        public class MyHolder extends RecyclerView.ViewHolder {
            private TextView mTime;
            private TextView mStaff;
            private TextView mReason;
            private TextView mStage;

            public MyHolder(View itemView) {
                super(itemView);
                mStaff = (TextView) itemView.findViewById(R.id.changed_staff);
                mReason = (TextView) itemView.findViewById(R.id.changed_reason);
                mStage = (TextView) itemView.findViewById(R.id.changed_stage);
                mTime = (TextView) itemView.findViewById(R.id.id_rate_time);
            }
        }
    }


    public static CooperateStageFragment newInstance(CooperateChanceDetailBean.CooperateModel model) {
        CooperateStageFragment fragment = new CooperateStageFragment();
        Bundle args = new Bundle();
        args.putParcelable("model", model);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            CooperateChanceDetailBean.CooperateModel model = getArguments().getParcelable("model");
            mData = model.getStageLogs();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
