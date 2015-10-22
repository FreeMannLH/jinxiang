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

public class ProjectBusinessFragment extends BaseFragment {

    @Bind(R.id.recyclerView)
    RecyclerView mListView;

    private ProjectDetailBean.ModelEntity modelEntity;

    private RecyclerView.LayoutManager mLayoutManager;
    private ProjectBusinessItemAdapter mProjectBusinessItemdapter;

    private List<ProjectDetailBean.ModelEntity.MattersEntity> mData = new ArrayList<>();

    public static ProjectBusinessFragment newInstance(ProjectDetailBean.ModelEntity modelEntity) {
        ProjectBusinessFragment fragment = new ProjectBusinessFragment();
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
            mData = modelEntity.getMatters();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_project_business, container, false);
        ButterKnife.bind(this, view);
        mListView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mListView.setLayoutManager(mLayoutManager);
        mListView.setItemAnimator(new DefaultItemAnimator());
        mProjectBusinessItemdapter = new ProjectBusinessItemAdapter();
        mListView.setAdapter(mProjectBusinessItemdapter);
        return view;
    }


    private class ProjectBusinessItemAdapter extends RecyclerView.Adapter<ProjectBusinessItemAdapter.MyHolder> {

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyHolder(LayoutInflater.from(getActivity()).inflate(R.layout.project_business_item, parent, false));
        }

        @Override
        public void onBindViewHolder(MyHolder holder, int position) {

            ProjectDetailBean.ModelEntity.MattersEntity mattersEntity = mData.get(position);
            holder.mBusinessName.setText(mattersEntity.getMatterName() + "");
            holder.mBusinessAgentPrice.setText(mattersEntity.getAgencyCost() + "");
            holder.mBusinessOfficePrice.setText(mattersEntity.getOfficialCost() + "");
            holder.mBusinessOtherPrice.setText(mattersEntity.getOtherCost() + "");
            holder.mBusinessTotalPrice.setText(mattersEntity.getTotalCost() + "");
            int index = position + 1;
            holder.mBusinessOrderNum.setText(index + "");
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        public class MyHolder extends RecyclerView.ViewHolder {
            private TextView mBusinessName;
            private TextView mBusinessOfficePrice;
            private TextView mBusinessAgentPrice;
            private TextView mBusinessOtherPrice;
            private TextView mBusinessTotalPrice;
            private TextView mBusinessOrderNum;

            public MyHolder(View itemView) {
                super(itemView);
                mBusinessName = (TextView) itemView.findViewById(R.id.id_business_name);
                mBusinessOfficePrice = (TextView) itemView.findViewById(R.id.id_business_office_price);
                mBusinessAgentPrice = (TextView) itemView.findViewById(R.id.id_business_agent_price);
                mBusinessOtherPrice = (TextView) itemView.findViewById(R.id.id_business_other_price);
                mBusinessTotalPrice = (TextView) itemView.findViewById(R.id.id_business_total_price);
                mBusinessOrderNum = (TextView) itemView.findViewById(R.id.id_business_order_num);
            }
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
