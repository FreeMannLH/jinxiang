package ideatc.jinxiang.fragment.home_activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import org.apache.http.Header;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import ideatc.jinxiang.R;
import ideatc.jinxiang.activity.ElectronicDetailActivity;
import ideatc.jinxiang.base.activity.BaseActivity;
import ideatc.jinxiang.base.fragment.BaseFragment;
import ideatc.jinxiang.bean.BulletinListBean;

public class ElectronicBulletinFragment extends BaseFragment {

    @Bind(R.id.recyclerView)
    RecyclerView mListView;
    @Bind(R.id.swipe_container)
    SwipyRefreshLayout mSwipeRefreshLayout;
    @Bind(R.id.layout)
    TextView layout;

//    private BulletinListBean bulletinList;

    private RecyclerView.LayoutManager mLayoutManager;
    private ElectronicItemAdapter mElectronicItemAdapter;
    private String url = null;
    private List<BulletinListBean.RowsEntity> mData = new ArrayList<>();

    public static ElectronicBulletinFragment instance() {
        ElectronicBulletinFragment fragment = new ElectronicBulletinFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_electronic_bulletin, container, false);
        ButterKnife.bind(this, view);
        url = URL_OA + "/GetNoticeList";

        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection swipyRefreshLayoutDirection) {
                if (swipyRefreshLayoutDirection == SwipyRefreshLayoutDirection.TOP) {
                    refresh();
                } else if (swipyRefreshLayoutDirection == SwipyRefreshLayoutDirection.BOTTOM) {
                    //上拉加载
//                    mHandler.sendEmptyMessageDelayed(PULL_UP, 2000);
                }
            }
        });

        mListView.setHasFixedSize(true);
        if (getActivity() != null) mLayoutManager = new LinearLayoutManager(getActivity());
        mListView.setLayoutManager(mLayoutManager);
        mListView.setItemAnimator(new DefaultItemAnimator());
        mElectronicItemAdapter = new ElectronicItemAdapter();
        mListView.setAdapter(mElectronicItemAdapter);

        getData();
        return view;
    }

    private class ElectronicItemAdapter extends RecyclerView.Adapter<ElectronicItemAdapter.MyHolder> {

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyHolder(LayoutInflater.from(getActivity()).inflate(R.layout.electronic_list_item, parent, false));
        }

        @Override
        public void onBindViewHolder(MyHolder holder, int position) {
            final BulletinListBean.RowsEntity rowsEntity = mData.get(position);
            holder.mLinearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (rowsEntity != null) {
                        Intent it = new Intent(getActivity(), ElectronicDetailActivity.class);
                        it.putExtra("id", rowsEntity.getId());
                        startActivity(it);
                        getActivity().overridePendingTransition(0, 0);
                    }
                }
            });

            holder.mTitleTextView.setText(rowsEntity.getTitle());
            holder.mTimeTextView.setText(rowsEntity.getMakeDateStr());
//            holder.mContentTextView.setText(rowsEntity.getContent());
//            holder.mContentTextView.getSettings().setJavaScriptEnabled(true);
//            holder.mContentTextView.loadData(rowsEntity.getContent(), "text/html", "utf-8");
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        public class MyHolder extends RecyclerView.ViewHolder {

            private TextView mTitleTextView;
            private TextView mTimeTextView;
            //            private WebView mContentTextView;
            private LinearLayout mLinearLayout;

            public MyHolder(View itemView) {
                super(itemView);
                mTitleTextView = (TextView) itemView.findViewById(R.id.id_bulletin_title_tv);
                mTimeTextView = (TextView) itemView.findViewById(R.id.id_bulletin_time_tv);
//                mContentTextView = (WebView) itemView.findViewById(R.id.id_bulletin_content_tv);
                mLinearLayout = (LinearLayout) itemView.findViewById(R.id.id_electronic_list_item);
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    private void getData() {
        httpClient().get(getActivity(), url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {


                if (layout.getVisibility() == View.VISIBLE)
                    layout.setVisibility(View.GONE);

                BulletinListBean bulletinListBean = new Gson().fromJson(BaseActivity.getString(responseBody), BulletinListBean.class);
                if (bulletinListBean.getRows() == null || bulletinListBean.getRows().size() == 0) {
//                    layout.inflate();
                    layout.setVisibility(View.VISIBLE);
                    return;
                }
                layout.setVisibility(View.GONE);

                mData = bulletinListBean.getRows();
                mElectronicItemAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

                mData.clear();
                mElectronicItemAdapter.notifyDataSetChanged();
                layout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onStart() {
                super.onStart();
                mSwipeRefreshLayout.setRefreshing(true);
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    /**
     * 刷新
     */
    private void refresh() {
        mData.clear();
        getData();
    }
}
