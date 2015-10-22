package ideatc.jinxiang.fragment.customer_detail_info_activity;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import ideatc.jinxiang.R;
import ideatc.jinxiang.activity.ContactDetailActivity;
import ideatc.jinxiang.base.activity.BaseActivity;
import ideatc.jinxiang.base.fragment.BaseFragment;
import ideatc.jinxiang.bean.CustomerContactBean;
import ideatc.jinxiang.bean.crm.CustomerListBean;
import ideatc.jinxiang.utils.ParamsUtil;
import ideatc.jinxiang.views.DividerItemDecoration;

/**
 * 客户联系人列表
 */
public class CustomerContactsInfoFragment extends BaseFragment {


    @Bind(R.id.recyclerView)
    RecyclerView mListView;

    private RecyclerView.LayoutManager mLayoutManager;
    private CustomerContactAdapter mCustomerContactAdapter;

    private CustomerListBean.CustomerBean customerBean;
    private List<CustomerContactBean> mData = new ArrayList<>();

    public static CustomerContactsInfoFragment newInstance(CustomerListBean.CustomerBean customerBean) {
        CustomerContactsInfoFragment fragment = new CustomerContactsInfoFragment();
        Bundle args = new Bundle();
        args.putParcelable("customerBean", customerBean);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            customerBean = getArguments().getParcelable("customerBean");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_contacts_info, container, false);
        ButterKnife.bind(this, view);
        mListView.setHasFixedSize(true);
        if (getActivity() != null) mLayoutManager = new LinearLayoutManager(getActivity());
        mListView.setLayoutManager(mLayoutManager);
        mListView.setItemAnimator(new DefaultItemAnimator());
        mListView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        mCustomerContactAdapter = new CustomerContactAdapter();
        mListView.setAdapter(mCustomerContactAdapter);

        getCustomerContactInfo();

        return view;
    }

    private class CustomerContactAdapter extends RecyclerView.Adapter<CustomerContactAdapter.MyHolder> {

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyHolder holder = new MyHolder(LayoutInflater.from(getActivity()).inflate(R.layout.fragment_customer_contacts_item, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyHolder holder, int position) {
            final CustomerContactBean customerContactBean = mData.get(position);
            holder.mName.setText(customerContactBean.getName() + customerContactBean.getTitle() + "");
            holder.mPosition.setText("("+customerContactBean.getPost() + ")");
            holder.mLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it = new Intent(getActivity(), ContactDetailActivity.class);
                    it.putExtra("customerContactBean", customerContactBean);
                    startActivity(it);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        public class MyHolder extends RecyclerView.ViewHolder {
            private TextView mName;
            private TextView mPosition;
            private LinearLayout mLayout;

            public MyHolder(View itemView) {
                super(itemView);
                mName = (TextView) itemView.findViewById(R.id.id_customer_contact_name);
                mPosition = (TextView) itemView.findViewById(R.id.id_customer_contact_position);
                mLayout = (LinearLayout) itemView.findViewById(R.id.id_customer_contact_layout);
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    /**
     * 获取客户的联系人信息
     */
    private void getCustomerContactInfo() {
        String url = URL_OA + "/getcustomerinfo";
        RequestParams params = ParamsUtil.getSignParams();
        params.put("uid", user.getId());
        params.put("customer", customerBean.getId());
        httpClient().get(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.e("客户联系人", statusCode + "");
                Gson gson = new Gson();
                String result = BaseActivity.getString(responseBody);
                Log.e("result", result);
                try {
                    JSONObject obj = new JSONObject(result).getJSONObject("model");
                    String beanStr = obj.getJSONArray("Contacts").toString();
                    Type type = new TypeToken<List<CustomerContactBean>>() {
                    }.getType();
                    mData = gson.fromJson(beanStr, type);
                    mCustomerContactAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("客户联系人", statusCode + "");
            }

            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onFinish() {
                super.onFinish();
            }
        });
    }

}
