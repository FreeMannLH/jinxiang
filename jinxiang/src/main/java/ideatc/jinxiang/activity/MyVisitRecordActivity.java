package ideatc.jinxiang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
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
import ideatc.jinxiang.bean.crm.VisitRecordListBean;
import ideatc.jinxiang.utils.ParamsUtil;
import ideatc.jinxiang.widget.dialog.SearchDialog;

public class MyVisitRecordActivity extends ToolBarBaseActivity implements SearchDialog.SearchListener {

    @Bind(R.id.id_visit_record_search_editText)
    Button mSearchBtn;
    @Bind(R.id.recyclerView)
    RecyclerView mListView;
    @Bind(R.id.swipe_container)
    SwipyRefreshLayout mSwipeRefreshLayout;

    private String url = "";
    private RecyclerView.LayoutManager mLayoutManager;
    private VisitRecordItemAdapter mElectronicItemAdapter;

    private List<VisitRecordListBean.VisitRecordBean> mData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_visit_record);
        ButterKnife.bind(this);
        initToolbar();
        url = URL_OA + "/getmyvisit";
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection swipyRefreshLayoutDirection) {
                if (swipyRefreshLayoutDirection == SwipyRefreshLayoutDirection.TOP) {
                    refresh();
                } else if (swipyRefreshLayoutDirection == SwipyRefreshLayoutDirection.BOTTOM) {
                    //上拉加载
                    loadMore();
                }
            }
        });

        mListView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mListView.setLayoutManager(mLayoutManager);
        mListView.setItemAnimator(new DefaultItemAnimator());
        mElectronicItemAdapter = new VisitRecordItemAdapter();
        mListView.setAdapter(mElectronicItemAdapter);

        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mData.size() == 0) {
                    Toast.makeText(MyVisitRecordActivity.this, R.string.no_visit_record, Toast.LENGTH_SHORT).show();
                    return;
                }
                SearchDialog dialog = new SearchDialog();
                dialog.show(getSupportFragmentManager(), "搜索框");
            }
        });

        getMyVisitRecord();
    }


    private String startDate;
    private String endDate;
    private boolean isSearch = false;

    /**
     * 按时间段搜索拜访记录
     *
     * @param startDate
     * @param endDate
     */
    @Override
    public void onDateInputComplete(String startDate, String endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        isSearch = true;
        page = 1;
        mData.clear();
        getMyVisitRecord();
    }


    private class VisitRecordItemAdapter extends RecyclerView.Adapter<VisitRecordItemAdapter.MyHolder> {

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyHolder holder = new MyHolder(LayoutInflater.from(MyVisitRecordActivity.this).inflate(R.layout.visit_record_item, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyHolder holder, int position) {
            final VisitRecordListBean.VisitRecordBean visitRecordBean = mData.get(position);
            holder.mDescribe.setText(visitRecordBean.getTitle());
            holder.mDate.setText(visitRecordBean.getVisitTimeStr());
            holder.mTargetCustomer.setText("目标客户：" + visitRecordBean.getCustName());
            holder.mVisitMan.setText("拜访人员：" + visitRecordBean.getCustManagerName());

            holder.mCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it = new Intent(MyVisitRecordActivity.this, VisitDetailActivity.class);
                    //拜访记录ID
                    it.putExtra("visitId", visitRecordBean.getId());
                    startActivity(it);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        public class MyHolder extends RecyclerView.ViewHolder {
            private TextView mDescribe;
            private TextView mDate;
            private TextView mTargetCustomer;
            private TextView mVisitMan;//拜访人员
            //            private TextView mVisitResult;
            private CardView mCardView;

            public MyHolder(View itemView) {
                super(itemView);
                mDescribe = (TextView) itemView.findViewById(R.id.id_describe);
                mDate = (TextView) itemView.findViewById(R.id.date);
                mTargetCustomer = (TextView) itemView.findViewById(R.id.targetCustomer);
                mVisitMan = (TextView) itemView.findViewById(R.id.visit_object);
//                mVisitResult = (TextView) itemView.findViewById(R.id.result);
                mCardView = (CardView) itemView.findViewById(R.id.visit_item_cardview);
            }
        }
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void initToolbar() {
        getToolbar().setNavigationIcon(R.drawable.back);
        getToolbar().setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private int page = 1;

    /**
     * 获取我的拜访记录
     */
    private void getMyVisitRecord() {
        RequestParams params = ParamsUtil.getSignParams();
        params.put("uid", user.getId());//必填
        params.put("page", page);
        params.put("rows",5);
        if (isSearch) {
            params.put("start", startDate);
            params.put("end", endDate);
        }
        httpClient().get(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = getString(responseBody);
                Log.e("visitRecord onSuccess", statusCode + "");
                Log.e("recordvisit", result);
                Gson gson = new Gson();
                VisitRecordListBean listBean = gson.fromJson(result, VisitRecordListBean.class);
                List<VisitRecordListBean.VisitRecordBean> list = listBean.getList();
                if (list == null || list.size() == 0) {
//                    Toast.makeText(MyVisitRecordActivity.this, "没有更多数据了", Toast.LENGTH_SHORT).show();
                    return;
                }
                for (VisitRecordListBean.VisitRecordBean item : list) {
                    mData.add(item);
                }
                mElectronicItemAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("visitRecord onFailure", statusCode + "");
            }

            @Override
            public void onStart() {
                super.onStart();
                dialog.show();
                mSwipeRefreshLayout.setRefreshing(true);
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mSwipeRefreshLayout.setRefreshing(false);
                dialog.dismiss();
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
        getMyVisitRecord();
    }

    /**
     * 上拉加载更多
     *
     * @return
     */
    private void loadMore() {
        page++;
        getMyVisitRecord();
    }

}
