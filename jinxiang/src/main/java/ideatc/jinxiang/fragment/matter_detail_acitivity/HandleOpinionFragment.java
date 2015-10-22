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
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import ideatc.jinxiang.R;
import ideatc.jinxiang.base.fragment.BaseFragment;
import ideatc.jinxiang.bean.SysFlowInfoBean;

public class HandleOpinionFragment extends BaseFragment {


    @Bind(R.id.recyclerView)
    RecyclerView mListView;
    @Bind(R.id.swipe_container)
    SwipyRefreshLayout mSwipeRefreshLayout;

    private HandleOpinionItemAdapter mHandleOpinionItemAdapter;
    private String logStr;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<SysFlowInfoBean.SysFlowLogEntity> entities = new ArrayList<>();

    public static HandleOpinionFragment instance(String logStr) {
        HandleOpinionFragment fragment = new HandleOpinionFragment();
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
        View view = inflater.inflate(R.layout.fragment_handle_opinion, container, false);
        ButterKnife.bind(this, view);

        entities = new Gson().fromJson(logStr, new TypeToken<List<SysFlowInfoBean.SysFlowLogEntity>>() {
        }.getType());

        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection swipyRefreshLayoutDirection) {
                //refresh
                if (swipyRefreshLayoutDirection == SwipyRefreshLayoutDirection.TOP) {
//                    refresh();
                } else if (swipyRefreshLayoutDirection == SwipyRefreshLayoutDirection.BOTTOM) {
                    //load more
                }
            }
        });

        mListView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mListView.setLayoutManager(mLayoutManager);
        mListView.setItemAnimator(new DefaultItemAnimator());
        mHandleOpinionItemAdapter = new HandleOpinionItemAdapter();
        mListView.setAdapter(mHandleOpinionItemAdapter);
        return view;
    }

    private class HandleOpinionItemAdapter extends RecyclerView.Adapter<HandleOpinionItemAdapter.MyHolder> {

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            MyHolder holder = new MyHolder(LayoutInflater.from(getActivity()).inflate(R.layout.layout_handle_opinion_item, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyHolder holder, int position) {

            SysFlowInfoBean.SysFlowLogEntity entity = entities.get(position);

            holder.mName.setText(entity.getUser());
            holder.mDate.setText(entity.getOperateDateStr());
            holder.mContent.setText(entity.getContent());
            try {
                if (getLoginStatus()) holder.mDepartment.setText(user.getDepartment().get(0));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public int getItemCount() {
            return entities.size();
        }

        public class MyHolder extends RecyclerView.ViewHolder {

            private TextView mName;
            private TextView mDate;
            private TextView mContent;
            private TextView mDepartment;

            public MyHolder(View itemView) {

                super(itemView);
                mName = (TextView) itemView.findViewById(R.id.name);
                mDate = (TextView) itemView.findViewById(R.id.date);
                mDepartment = (TextView) itemView.findViewById(R.id.department);
                mContent = (TextView) itemView.findViewById(R.id.content);
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
