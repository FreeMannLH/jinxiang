package ideatc.jinxiang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import org.apache.http.Header;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import ideatc.jinxiang.R;
import ideatc.jinxiang.base.activity.ToolBarBaseActivity;
import ideatc.jinxiang.bean.crm.CooperateChanceList;
import ideatc.jinxiang.utils.ParamsUtil;
import ideatc.jinxiang.widget.dialog.SearchDialog;

public class MyCooperateChanceActivity extends ToolBarBaseActivity implements SearchDialog.SearchListener {

    @Bind(R.id.id_project_search)
    Button mSearchBtn;
    @Bind(R.id.recyclerView)
    RecyclerView mListView;
    @Bind(R.id.swipe_container)
    SwipyRefreshLayout mSwipeRefreshLayout;

    private int page = 1;

    private MyCooperateChanceItemAdapter mMyCooperateChanceItemAdapter;

    private CooperateChanceList mListBean;
    private List<CooperateChanceList.CooperateChanceEntity> mData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cooperate_chance);
        ButterKnife.bind(this);
        initToolbar();
        initView();
        getMyCooperateChance();
    }

    /**
     * 获取我的合作机会列表
     */
    private void getMyCooperateChance() {
        String url = URL_OA + "/getmychances";
        RequestParams params = ParamsUtil.getSignParams();
        params.put("uid", user.getId());
        params.put("page", page);
        if (isSearch) {
            params.put("start", startDate);
            params.put("end", endDate);
        }
        httpClient().get(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = getString(responseBody);
                Log.e("cooperateChance", result);
                mListBean = new Gson().fromJson(result, CooperateChanceList.class);
                Log.e("name", mListBean.getSuccess() + "");
                List<CooperateChanceList.CooperateChanceEntity> list = mListBean.getList();

                if (list == null || list.size() == 0) return;

                for (CooperateChanceList.CooperateChanceEntity bean : list) {
                    mData.add(bean);
                }
//                mData = projectListBean.getList();
                mMyCooperateChanceItemAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }

            @Override
            public void onStart() {
                super.onStart();
                dialog.show();
//                mSwipeRefreshLayout.setRefreshing(true);
            }

            @Override
            public void onFinish() {
                super.onFinish();
                dialog.dismiss();
//                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void initData() {

    }

    private String startDate;
    private String endDate;
    private boolean isSearch = false;

    /**
     * 按时间段搜索
     *
     * @param startDate
     * @param endDate
     */
    @Override
    public void onDateInputComplete(String startDate, String endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        isSearch = true;
        mData.clear();
        getMyCooperateChance();
    }

    @Override
    public void initView() {
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection swipyRefreshLayoutDirection) {
                if (swipyRefreshLayoutDirection == SwipyRefreshLayoutDirection.TOP) {
                    refresh();
                } else if (swipyRefreshLayoutDirection == SwipyRefreshLayoutDirection.BOTTOM) {
                    //上拉加载
//                    loadMore();
                }
            }
        });

        mListView.setHasFixedSize(true);
        mListView.setLayoutManager(new LinearLayoutManager(this));
        mListView.setItemAnimator(new DefaultItemAnimator());
        mMyCooperateChanceItemAdapter = new MyCooperateChanceItemAdapter();
        mListView.setAdapter(mMyCooperateChanceItemAdapter);


        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mData.size() == 0) {
                    Toast.makeText(MyCooperateChanceActivity.this, R.string.cooperate_list_null, Toast.LENGTH_SHORT).show();
                    return;
                }
                SearchDialog dialog = new SearchDialog();
                dialog.show(getSupportFragmentManager(), "搜索框");
            }
        });
    }

    /**
     * 下拉刷新
     */
    private void refresh() {
        isSearch = false;
        page = 1;
        mData.clear();
        getMyCooperateChance();
    }

    /**
     * 上拉加载更多
     *
     * @return
     */
    private void loadMore() {
        page++;
        getMyCooperateChance();
    }

    @Override
    public void initToolbar() {
        getToolbar().setNavigationIcon(R.drawable.back);
        getToolbar().setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                supportFinishAfterTransition();
            }
        });
    }

    private class MyCooperateChanceItemAdapter extends RecyclerView.Adapter<MyCooperateChanceItemAdapter.MyHolder> {

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyHolder holder = new MyHolder(LayoutInflater.from(MyCooperateChanceActivity.this).inflate(R.layout.cooperate_item, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyHolder holder, int position) {
            final CooperateChanceList.CooperateChanceEntity mBean = mData.get(position);
            holder.mTitle.setText(mBean.getTitle() + "");
            holder.mDate.setText("创建日期：" + mBean.getCreateTimeStr());
            holder.mState.setText(getString(R.string.changed_stage) + mBean.getStageStr());
            holder.mCustomerManager.setText(getString(R.string.customer_manager) + mBean.getCustManagerName());
            holder.mOrderNumber.setText(getString(R.string.order_no) + mBean.getId());
            holder.mName.setText(getString(R.string.customer_name) + mBean.getCustomerName());
            holder.mCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mBean != null) {
                        Intent it = new Intent(MyCooperateChanceActivity.this, CooperateChanceDetailActivity.class);
                        //流水号,Id
                        it.putExtra("cooperateId", mBean.getId());
                        startActivity(it);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        public class MyHolder extends RecyclerView.ViewHolder {
            private TextView mTitle;
            private TextView mDate;
            private TextView mState;
            private TextView mCustomerManager;
            private TextView mOrderNumber;
            private TextView mName;
            private CardView mCard;

            public MyHolder(View itemView) {
                super(itemView);
                mTitle = (TextView) itemView.findViewById(R.id.id_cooperate_title);
                mDate = (TextView) itemView.findViewById(R.id.id_setup_date);
                mState = (TextView) itemView.findViewById(R.id.id_cooperate_state);
                mName = (TextView) itemView.findViewById(R.id.id_customer_name);
                mCustomerManager = (TextView) itemView.findViewById(R.id.id_cooperate_manager);
                mOrderNumber = (TextView) itemView.findViewById(R.id.id_order_num);
                mCard = (CardView) itemView.findViewById(R.id.id_cardview_item);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_my_cooperate_chance, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.add_cooperate) {
            Intent it = new Intent(MyCooperateChanceActivity.this, NewCooperateActivity.class);
            startActivity(it);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        refresh();
    }
}
