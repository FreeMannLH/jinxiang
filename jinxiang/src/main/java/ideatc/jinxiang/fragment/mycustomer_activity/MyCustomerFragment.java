package ideatc.jinxiang.fragment.mycustomer_activity;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
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
import ideatc.jinxiang.activity.CustomerDetailInfoActivity;
import ideatc.jinxiang.base.activity.BaseActivity;
import ideatc.jinxiang.base.fragment.BaseFragment;
import ideatc.jinxiang.base.manager.SnackBarManager;
import ideatc.jinxiang.bean.crm.CustomerListBean;
import ideatc.jinxiang.utils.ParamsUtil;

public class MyCustomerFragment extends BaseFragment {

    @Bind(R.id.recyclerView)
    RecyclerView mListView;
    @Bind(R.id.id_project_search)
    Button mSearchBtn;
    @Bind(R.id.swipe_container)
    SwipyRefreshLayout mSwipeRefreshLayout;

    private List<CustomerListBean.CustomerBean> mData = new ArrayList<>();


    private RecyclerView.LayoutManager mLayoutManager;
    private MyCustomerItemAdapter mMyCustomerItemAdapter;

    private  int flag = 0;
    private  String searchName = "";
    private  boolean isSearchMode = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_customer, container, false);
        ButterKnife.bind(this, view);

        mListView.setHasFixedSize(true);
        if (getActivity() != null) mLayoutManager = new LinearLayoutManager(getActivity());
        mListView.setLayoutManager(mLayoutManager);
        mListView.setItemAnimator(new DefaultItemAnimator());
        mMyCustomerItemAdapter = new MyCustomerItemAdapter();
        mListView.setAdapter(mMyCustomerItemAdapter);

        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection swipyRefreshLayoutDirection) {
                if (swipyRefreshLayoutDirection == SwipyRefreshLayoutDirection.TOP) {
                    refresh();
                    mSwipeRefreshLayout.setRefreshing(false);
                } else if (swipyRefreshLayoutDirection == SwipyRefreshLayoutDirection.BOTTOM) {
                    //上拉加载
//                    loadMore();
//                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }
        });
        mSearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mData.size() == 0) {
                    Toast.makeText(getActivity(), R.string.isNull_noSearch, Toast.LENGTH_SHORT).show();
                    return;
                }
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater = getActivity().getLayoutInflater();
                View view = inflater.inflate(R.layout.fragment_dialog_search_contacts, null);
                final EditText eSearchEdit = (EditText) view.findViewById(R.id.id_contacts_search_editText);
                builder.setView(view).setPositiveButton("搜索", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(eSearchEdit.getWindowToken(), 0);
                        if (TextUtils.isEmpty(eSearchEdit.getText().toString())) {
                            SnackBarManager.make(getActivity().getCurrentFocus(), getString(R.string.content_not_right), Snackbar.LENGTH_INDEFINITE);
                            return;
                        }
                        isSearchMode = true;
                        searchName = eSearchEdit.getText().toString();

                        getMyAllCustomer(flag);
                    }
                }).setCancelable(true);
                builder.create().show();
            }
        });

        getMyAllCustomer(flag);

        return view;
    }

    private class MyCustomerItemAdapter extends RecyclerView.Adapter<MyCustomerItemAdapter.MyHolder> {

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyHolder holder = new MyHolder(LayoutInflater.from(getActivity()).inflate(R.layout.mycustomer_item, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyHolder holder, int position) {
            final CustomerListBean.CustomerBean customerBean = mData.get(position);
            holder.mTargetCustomerName.setText(customerBean.getName());
            holder.mCustomerType.setText(customerBean.getCustTypeStr());
            holder.mCustomerLevel.setText("客户等级：" + customerBean.getCustLevel());
            holder.mCustomerIndustry.setText("客户行业：" + customerBean.getTradeName());
            holder.mCustomerManager.setText("客户经理：" + customerBean.getCustManagerName());
            holder.mCustomerArea.setText("归属片区：" + customerBean.getCustArea());
            holder.mCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it = new Intent(getActivity(), CustomerDetailInfoActivity.class);
                    it.putExtra("customerBean", customerBean);
                    startActivity(it);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        public class MyHolder extends RecyclerView.ViewHolder {
            private TextView mTargetCustomerName;
            private TextView mCustomerType;
            private TextView mCustomerLevel;
            private TextView mCustomerManager;
            private TextView mCustomerArea;
            private TextView mCustomerIndustry;
            private CardView mCardView;

            public MyHolder(View itemView) {
                super(itemView);
                mTargetCustomerName = (TextView) itemView.findViewById(R.id.id_target_customer_name);
                mCustomerType = (TextView) itemView.findViewById(R.id.id_customer_type);
                mCustomerManager = (TextView) itemView.findViewById(R.id.id_customer_manager);
                mCustomerIndustry = (TextView) itemView.findViewById(R.id.id_customer_industry);
                mCustomerArea = (TextView) itemView.findViewById(R.id.id_customer_area);
                mCustomerLevel = (TextView) itemView.findViewById(R.id.id_customer_level);
                mCardView = (CardView) itemView.findViewById(R.id.id_customer_item);
            }
        }
    }

    public static MyCustomerFragment newInstance(int flag) {
        MyCustomerFragment fragment = new MyCustomerFragment();
        Bundle args = new Bundle();
        args.putInt("flag", flag);
        fragment.setArguments(args);
        return fragment;
    }

    private int page = 1;

    /**
     * 获取我所有的客户
     */
    public void getMyAllCustomer(int customerFlag) {
        String url = URL_OA + "/getmycustomers";
        RequestParams params = ParamsUtil.getSignParams();
        params.put("uid", user.getId());
        params.put("page", page);
        params.put("flag", customerFlag);
        if (isSearchMode) {
            params.put("name", searchName);
        }
        httpClient().get(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.e("我的客户获取成功", BaseActivity.getString(responseBody));
                CustomerListBean customerListBean = new Gson().fromJson(BaseActivity.getString(responseBody), CustomerListBean.class);
                List<CustomerListBean.CustomerBean> list = customerListBean.getList();
                if (list == null || list.size() == 0) return;
                mData.clear();
                for (CustomerListBean.CustomerBean bean : list) {
                    mData.add(bean);
                }
                mMyCustomerItemAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("根据flag获取客户", statusCode + "");
            }

            @Override
            public void onStart() {
                super.onStart();
                dialog.show();

            }

            @Override
            public void onFinish() {
                super.onFinish();
                dialog.dismiss();
            }
        });

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            flag = getArguments().getInt("flag");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    /**
     * 下拉刷新
     */
    private void refresh() {
        isSearchMode = false;
        page = 1;
        mData.clear();
        getMyAllCustomer(flag);
    }

    //    /**
//     * 上拉加载更多
//     *
//     * @return
//     */
//    private void loadMore() {
//        isSearchMode = false;
//        page++;
//        getMyAllCustomer(flag);
//    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        refresh();
    }
}
