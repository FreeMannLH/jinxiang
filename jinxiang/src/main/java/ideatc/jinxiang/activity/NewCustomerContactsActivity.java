package ideatc.jinxiang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import ideatc.jinxiang.R;
import ideatc.jinxiang.base.activity.ToolBarBaseActivity;
import ideatc.jinxiang.bean.CustomerContactBean;
import ideatc.jinxiang.bean.crm.CustomerDetailBean;
import ideatc.jinxiang.utils.ParamsUtil;
import ideatc.jinxiang.views.DividerItemDecoration;

public class NewCustomerContactsActivity extends ToolBarBaseActivity {

    @Bind(R.id.recyclerView)
    RecyclerView mListView;
    @Bind(R.id.add_contact_tv)
    TextView addContactTv;

    private CustomerDetailBean.CustomerBean mCustomerBean;
    //存放新增的联系人
    private List<CustomerContactBean> mData = new ArrayList<>();


    private CustomerContactAdapter mCustomerContactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_customer_contacts);
        ButterKnife.bind(this);

        if (getIntent() != null) mCustomerBean = getIntent().getParcelableExtra("customerBean");
        initToolbar();
        initView();

    }

    private class CustomerContactAdapter extends RecyclerView.Adapter<CustomerContactAdapter.MyHolder> {

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyHolder holder = new MyHolder(LayoutInflater.from(NewCustomerContactsActivity.this).inflate(R.layout.fragment_customer_contacts_item, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyHolder holder, final int position) {
            if (mData != null && mData.size() != 0) {
                final CustomerContactBean customerContactBean = mData.get(position);
                holder.mName.setText(customerContactBean.getName() + customerContactBean.getTitle() + "");
                holder.mPosition.setText("(" + customerContactBean.getPost() + ")");
                holder.mLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent it = new Intent(NewCustomerContactsActivity.this, ContactDetailActivity.class);
                        it.putExtra("customerContactBean", customerContactBean);
                        startActivity(it);
                    }
                });
                /**
                 * 长按删除
                 */
                holder.mLayout.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        mData.remove(position);
                        mCustomerContactAdapter.notifyItemRemoved(position);
                        return false;
                    }
                });
            }
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
    public void initData() {

    }

    @Override
    public void initView() {

        mListView.setHasFixedSize(true);
        mListView.setLayoutManager(new LinearLayoutManager(this));
        mListView.setItemAnimator(new DefaultItemAnimator());
        mListView.addItemDecoration(new DividerItemDecoration(NewCustomerContactsActivity.this, DividerItemDecoration.VERTICAL_LIST));
        mCustomerContactAdapter = new CustomerContactAdapter();
        mListView.setAdapter(mCustomerContactAdapter);

        addContactTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(NewCustomerContactsActivity.this, NewCustomerContactInfoActivity.class);
                it.putExtra("flag", "new");
                startActivityForResult(it, 1000);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_new_customer_contacts, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.submit) {
            postCustomer();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 提交客户数据到服务器
     */
    private void postCustomer() {
        Gson gson = new Gson();
        RequestParams params = ParamsUtil.getSignParams();
        params.put("Id", mCustomerBean.getId());
        params.put("uid", mCustomerBean.getModifyByNo());
        params.put("Name", mCustomerBean.getName());
        params.put("CustType", mCustomerBean.getCustType());
        params.put("LatencyLevel", mCustomerBean.getLatencyLevel());
        params.put("CustLevel", mCustomerBean.getCustLevel());
        params.put("CustSource", mCustomerBean.getCustSource());
        params.put("CustArea", mCustomerBean.getCustArea());
        params.put("Addr", mCustomerBean.getAddr());
        params.put("TradeId", mCustomerBean.getTradeId());
        params.put("StaffNum", mCustomerBean.getStaffNum());
        params.put("AnnualTurnover", mCustomerBean.getAnnualTurnover());
        params.put("RegisteredCapital", mCustomerBean.getRegisteredCapital());
        params.put("FoundingTime", mCustomerBean.getFoundingTimeStr());
        params.put("LegalAddr", mCustomerBean.getLegalAddr());
        params.put("Remark", mCustomerBean.getRemark());
        params.put("Intent", mCustomerBean.getIntent());
        params.put("Flag", 1);
//        params.put("Honors", gson.toJson(mCustomerBean.getHonors()));

        String contactJson = "";
        for (int i = 0; i < mData.size(); i++) {
            if (i == mData.size() - 1) contactJson = contactJson + gson.toJson(mData.get(i));
            else contactJson = contactJson + gson.toJson(mData.get(i)) + ",";
        }
        params.put("ContactsJson", contactJson);
        httpClient().get(URL_OA + "/customerpost", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = getString(responseBody);
                Log.e("添加客户", result);
                try {
                    JSONObject obj = new JSONObject(result);
                    if (obj.getBoolean("Status")) {
                        Toast.makeText(NewCustomerContactsActivity.this, obj.getString("Message"), Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(NewCustomerContactsActivity.this, obj.getString("Message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("添加客户", statusCode + "");
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
    public void initToolbar() {
        getToolbar().setNavigationIcon(R.drawable.back);
        getToolbar().setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1000) {
            if (data.getParcelableExtra("contactBean") == null) return;
            CustomerContactBean entity = data.getParcelableExtra("contactBean");
            mData.add(entity);
            mCustomerContactAdapter.notifyDataSetChanged();
//            mCustomerContactAdapter.notifyItemInserted(mData.size());
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
