package ideatc.jinxiang.fragment.project_detail_activity;


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
import ideatc.jinxiang.bean.crm.ProjectDetailBean;

public class ProjectRateFragment extends BaseFragment {

    @Bind(R.id.recyclerView)
    RecyclerView mListView;


    private ProjectDetailBean.ModelEntity modelEntity;

    private RecyclerView.LayoutManager mLayoutManager;
    private ProjectRateItemAdapter mProjectRateItemAdapter;

    private List<ProjectDetailBean.ModelEntity.SchedulesEntity> mData = new ArrayList<>();

    public static ProjectRateFragment newInstance(ProjectDetailBean.ModelEntity modelEntity) {
        ProjectRateFragment fragment = new ProjectRateFragment();
        Bundle args = new Bundle();
        args.putParcelable("modelEntity", modelEntity);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            modelEntity = getArguments().getParcelable("modelEntity");
            mData = modelEntity.getSchedules();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_project_rate, container, false);
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
            MyHolder holder = new MyHolder(LayoutInflater.from(getActivity()).inflate(R.layout.project_rate_item, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyHolder holder, int position) {

            ProjectDetailBean.ModelEntity.SchedulesEntity schedulesEntity = mData.get(position);
            if (schedulesEntity != null) {
                holder.mContent.setText(schedulesEntity.getRemark() + "");
                holder.mHandler.setText(schedulesEntity.getCreateByName() + "");
                holder.mRate.setText(schedulesEntity.getSchedule() + "%");
                holder.mRateDate.setText(schedulesEntity.getFinishDateStr() + "");
                holder.mRateTime.setText(schedulesEntity.getCreateTimeStr() + "");
            }
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        public class MyHolder extends RecyclerView.ViewHolder {
            private TextView mRateTime;
            private TextView mRateDate;
            private TextView mHandler;
            private TextView mRate;
            private TextView mContent;

            public MyHolder(View itemView) {
                super(itemView);
                mRateTime = (TextView) itemView.findViewById(R.id.id_rate_time);
                mRateDate = (TextView) itemView.findViewById(R.id.id_handle_date);
                mHandler = (TextView) itemView.findViewById(R.id.id_handler_man);
                mRate = (TextView) itemView.findViewById(R.id.id_rate);
                mContent = (TextView) itemView.findViewById(R.id.id_handle_content);
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
