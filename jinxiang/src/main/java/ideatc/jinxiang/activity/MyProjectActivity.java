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
import ideatc.jinxiang.bean.crm.ProjectListBean;
import ideatc.jinxiang.utils.ParamsUtil;
import ideatc.jinxiang.widget.dialog.SearchDialog;

public class MyProjectActivity extends ToolBarBaseActivity implements SearchDialog.SearchListener {

    @Bind(R.id.id_project_search)
    Button mSearchBtn;
    @Bind(R.id.recyclerView)
    RecyclerView mListView;
    @Bind(R.id.swipe_container)
    SwipyRefreshLayout mSwipeRefreshLayout;

    private ProjectListBean projectListBean;
    private List<ProjectListBean.ProjectBean> mData = new ArrayList<>();

    private MyProjectItemAdapter mMyProjectItemAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_project);
        ButterKnife.bind(this);

        initToolbar();
        initView();
        getMyProjects();
    }

    @Override
    public void initData() {

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
                    loadMore();
                }
            }
        });

        mListView.setHasFixedSize(true);
        mListView.setLayoutManager(new LinearLayoutManager(this));
        mListView.setItemAnimator(new DefaultItemAnimator());
        mMyProjectItemAdapter = new MyProjectItemAdapter();
        mListView.setAdapter(mMyProjectItemAdapter);


        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mData.size() == 0) {
                    Toast.makeText(MyProjectActivity.this, R.string.project_null, Toast.LENGTH_SHORT).show();
                    return;
                }
                SearchDialog dialog = new SearchDialog();
                dialog.show(getSupportFragmentManager(), "搜索框");
            }
        });
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
        getMyProjects();
    }

    private class MyProjectItemAdapter extends RecyclerView.Adapter<MyProjectItemAdapter.MyHolder> {

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyHolder holder = new MyHolder(LayoutInflater.from(MyProjectActivity.this).inflate(R.layout.project_item, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyHolder holder, int position) {
            final ProjectListBean.ProjectBean projectBean = mData.get(position);
            holder.mProjectStr.setText(projectBean.getId() + "");
            holder.mProjectDate.setText("签订日期：" + projectBean.getSignDateStr());
            holder.mProjectMoney.setText("项目金额：" + projectBean.getPrice() + "");
            holder.mProjectCustomer.setText("目标客户：" + projectBean.getCustomerName());
            holder.mProjectNumber.setText("合同编号：" + projectBean.getContractNo());
            holder.mProjectDetail.setText(projectBean.getTitle() + "");
            holder.mProjectPayWay.setText("付款方式：" + projectBean.getPayFlagStr());
            holder.mProjectVisitMan.setText("拜访人员：" + projectBean.getCustManagerName());
            holder.mProjectStatus.setText(projectBean.getStatusStr() + "");
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (projectBean != null) {
                        Intent it = new Intent(MyProjectActivity.this, ProjectDetailActivity.class);
                        it.putExtra("projectBean", projectBean);
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
            private TextView mProjectStr;
            private TextView mProjectDate;
            private TextView mProjectMoney;
            private TextView mProjectCustomer;
            private TextView mProjectNumber;
            private TextView mProjectPayWay;
            private TextView mProjectVisitMan;
            private TextView mProjectStatus;
            private TextView mProjectDetail;
            private CardView cardView;

            public MyHolder(View itemView) {
                super(itemView);
                mProjectStr = (TextView) itemView.findViewById(R.id.id_project);
                mProjectDate = (TextView) itemView.findViewById(R.id.id_project_date);
                mProjectMoney = (TextView) itemView.findViewById(R.id.id_project_money);
                mProjectCustomer = (TextView) itemView.findViewById(R.id.targetCustomer);
                mProjectNumber = (TextView) itemView.findViewById(R.id.id_project_num);
                mProjectPayWay = (TextView) itemView.findViewById(R.id.id_project_payway);
                mProjectVisitMan = (TextView) itemView.findViewById(R.id.visit_object);
                mProjectStatus = (TextView) itemView.findViewById(R.id.id_project_status);
                mProjectDetail = (TextView) itemView.findViewById(R.id.id_project_detail);
                cardView = (CardView) itemView.findViewById(R.id.project_item_cardview);
            }
        }
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
     * 获取我的项目合同列表
     */
    private void getMyProjects() {
        RequestParams params = ParamsUtil.getSignParams();
        params.put("uid", user.getId());
        params.put("page", page);
        if (isSearch) {
            params.put("start", startDate);
            params.put("end", endDate);
        }
        String url = URL_OA + "/getmycontracts";
        httpClient().get(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.e("projects", getString(responseBody));
                projectListBean = new Gson().fromJson(getString(responseBody), ProjectListBean.class);
                List<ProjectListBean.ProjectBean> list = projectListBean.getList();
                if (list == null || list.size() == 0) {
//                    Toast.makeText(MyProjectActivity.this, "没有更多数据了", Toast.LENGTH_SHORT).show();
                    return;
                }
                for (ProjectListBean.ProjectBean bean : list) {
                    mData.add(bean);
                }
//                mData = projectListBean.getList();
                mMyProjectItemAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("myProjects", statusCode + "");
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
        getMyProjects();
    }

    /**
     * 上拉加载更多
     *
     * @return
     */
    private void loadMore() {
        page++;
        getMyProjects();
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_project, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.id_start_project:
//                Toast.makeText(MyProjectActivity.this, "发起项目", Toast.LENGTH_SHORT).show();
//                break;
//        }
////        return super.onOptionsItemSelected(item);
//        return true;
//    }

    /**
     * 发起项目
     */
    private void buildProject() {

    }


    @Override
    protected void onRestart() {
        super.onRestart();
        refresh();
    }
}
