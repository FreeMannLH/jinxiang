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
import ideatc.jinxiang.bean.crm.CooperateChanceDetailBean;
import ideatc.jinxiang.utils.ParamsUtil;
import ideatc.jinxiang.views.DividerItemDecoration;

public class NewCooperateContactsAndMattersActivity extends ToolBarBaseActivity {

    protected static final int REQUEST_CODE_CONTACT = 1000;
    protected static final int REQUEST_CODE_MATTER = 2000;


    @Bind(R.id.recyclerViewContacts)
    RecyclerView recContacts;
    @Bind(R.id.recyclerViewMatters)
    RecyclerView recMatters;
    @Bind(R.id.add_contact_tv)
    TextView addContactTv;
    @Bind(R.id.add_matter_tv)
    TextView addMatterTv;

    private CooperateChanceDetailBean.CooperateModel model;
    //联系人
    private List<CooperateChanceDetailBean.CooperateModel.ContactsEntity> mContacts = new ArrayList<>();
    //事项
    private List<CooperateChanceDetailBean.CooperateModel.MattersEntity> mMatters = new ArrayList<>();


    private CooperateContactAdapter mCustomerContactAdapter;
    private CooperateMattersAdapter mCooperateMattersAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_cooperate_contacts_and_matters);
        ButterKnife.bind(this);
        initToolbar();
        initView();
        if (getIntent() != null) model = getIntent().getParcelableExtra("model");

        DefaultItemAnimator defaultItemAnimator = new DefaultItemAnimator();
        DividerItemDecoration decoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST);

        recContacts.setHasFixedSize(true);
        recContacts.setLayoutManager(new LinearLayoutManager(this));
        recContacts.setItemAnimator(defaultItemAnimator);
        recContacts.addItemDecoration(decoration);
        mCustomerContactAdapter = new CooperateContactAdapter();
        recContacts.setAdapter(mCustomerContactAdapter);

        recMatters.setHasFixedSize(true);
        recMatters.setLayoutManager(new LinearLayoutManager(this));
        recMatters.setItemAnimator(defaultItemAnimator);
        recMatters.addItemDecoration(decoration);
        mCooperateMattersAdapter = new CooperateMattersAdapter();
        recMatters.setAdapter(mCooperateMattersAdapter);
    }


    //合作机会联系人adapter
    private class CooperateContactAdapter extends RecyclerView.Adapter<CooperateContactAdapter.MyHolder> {

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyHolder holder = new MyHolder(LayoutInflater.from(NewCooperateContactsAndMattersActivity.this).inflate(R.layout.fragment_customer_contacts_item, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyHolder holder, final int position) {
            if (mContacts != null && mContacts.size() != 0) {
                final CooperateChanceDetailBean.CooperateModel.ContactsEntity bean = mContacts.get(position);
                holder.mName.setText(bean.getContactName() + bean.getContactTitle() + "");
                holder.mPosition.setText(" (" + bean.getRoleStr() + ")");
                holder.mLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent it = new Intent(NewCooperateContactsAndMattersActivity.this, CooperateContactsActivity.class);
                        it.putExtra("contactsEntity", bean);
                        startActivity(it);

                    }
                });
                /**
                 * 长按删除
                 */
                holder.mLayout.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        if (mContacts.size() > 1) {
                            mContacts.remove(position);
                            mCustomerContactAdapter.notifyItemRemoved(position);
                        }
                        return false;
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return mContacts.size();
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

    //合作事项adapter
    private class CooperateMattersAdapter extends RecyclerView.Adapter<CooperateMattersAdapter.MyHolder> {

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyHolder holder = new MyHolder(LayoutInflater.from(NewCooperateContactsAndMattersActivity.this).inflate(R.layout.fragment_cooperate_matters_item, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyHolder holder, final int position) {
            if (mMatters != null && mMatters.size() != 0) {
                final CooperateChanceDetailBean.CooperateModel.MattersEntity bean = mMatters.get(position);
                holder.mName.setText(bean.getMatterName() + "");
                holder.mCost.setText(getString(R.string.totalCost) + bean.getTotalCost());
                holder.mLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
                holder.mLayout.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        if (mMatters.size() > 1) {
                            mMatters.remove(position);
                            mCooperateMattersAdapter.notifyItemRemoved(position);
                        }
                        return false;
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return mMatters.size();
        }

        public class MyHolder extends RecyclerView.ViewHolder {
            private TextView mName;
            private TextView mCost;
            private LinearLayout mLayout;

            public MyHolder(View itemView) {
                super(itemView);
                mName = (TextView) itemView.findViewById(R.id.matters);
                mCost = (TextView) itemView.findViewById(R.id.cost);
                mLayout = (LinearLayout) itemView.findViewById(R.id.layout);
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_CONTACT) {
            if (data.getParcelableExtra("contactEntity") == null) return;
            CooperateChanceDetailBean.CooperateModel.ContactsEntity entity = data.getParcelableExtra("contactEntity");
            mContacts.add(entity);
            mCustomerContactAdapter.notifyDataSetChanged();
//            mCustomerContactAdapter.notifyItemInserted(mData.size());
        }
        if (requestCode == REQUEST_CODE_MATTER) {
            if (data.getParcelableExtra("matterEntity") == null) return;
            CooperateChanceDetailBean.CooperateModel.MattersEntity entity = data.getParcelableExtra("matterEntity");
            mMatters.add(entity);
            mCooperateMattersAdapter.notifyDataSetChanged();
        }
        if (NewCustomerContactInfoActivity.REQUEST_CODE_BACK == requestCode) {

        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        addContactTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(NewCooperateContactsAndMattersActivity.this, CooperateAddContactActivity.class);
                it.putExtra("customerId", model.getCustomerId());
                startActivityForResult(it, REQUEST_CODE_CONTACT);
            }
        });
        addMatterTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(NewCooperateContactsAndMattersActivity.this, CooperateAddMatterActivity.class);
                startActivityForResult(it, REQUEST_CODE_MATTER);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_new_cooperate_contacts_and_matters, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.submit) {
            submit();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 提交合作机会到服务器
     */
    private void submit() {
        if (mContacts.size() == 0 || mMatters.size() == 0) {
            Toast.makeText(NewCooperateContactsAndMattersActivity.this, "必须添加联系人和业务事项", Toast.LENGTH_SHORT).show();
            return;
        }
        RequestParams params = ParamsUtil.getSignParams();
//        params.put("Id", null);
        params.put("Title", model.getTitle());
        params.put("uid", model.getModifyByNo());
        params.put("CustomerId", model.getCustomerId());
        params.put("Stage", model.getStage());
        params.put("Remark", model.getRemark());

        String contactJson = "";
        Gson gson = new Gson();
        for (int i = 0, size = mContacts.size(); i < size; i++) {
            CooperateChanceDetailBean.CooperateModel.ContactsEntity bean = mContacts.get(i);
            if (i == size - 1) contactJson = contactJson + gson.toJson(bean);
            else contactJson = contactJson + gson.toJson(bean) + ",";
        }
        Log.e("contactJson", contactJson);
        params.put("ContactsJson", contactJson);

        String mattersJson = "";
        for (int i = 0, size = mMatters.size(); i < size; i++) {
            CooperateChanceDetailBean.CooperateModel.MattersEntity bean = mMatters.get(i);
            if (i == size - 1) mattersJson = mattersJson + gson.toJson(bean);
            else mattersJson = mattersJson + gson.toJson(bean) + ",";
        }
        Log.e("mattersJson", mattersJson);
        params.put("MattersJson", mattersJson);

        httpClient().get(URL_OA + "/chancepost", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = getString(responseBody);
                Log.e("提交合作机会", result);
                try {
                    JSONObject obj = new JSONObject(result);
                    if (obj.getBoolean("Status")) {
                        Toast.makeText(NewCooperateContactsAndMattersActivity.this, obj.getString("Message"), Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(NewCooperateContactsAndMattersActivity.this, obj.getString("Message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

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
}
