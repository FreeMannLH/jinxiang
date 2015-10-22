package ideatc.jinxiang.fragment.cooperate_chance_detail_activity;


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

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import ideatc.jinxiang.R;
import ideatc.jinxiang.activity.CooperateContactsActivity;
import ideatc.jinxiang.base.fragment.BaseFragment;
import ideatc.jinxiang.bean.crm.CooperateChanceDetailBean;
import ideatc.jinxiang.views.DividerItemDecoration;

/**
 * @author ccb
 *         create at 2015/9/18 9:04
 */
public class CooperateContactFragment extends BaseFragment {


    @Bind(R.id.recyclerView)
    RecyclerView mListView;

    private List<CooperateChanceDetailBean.CooperateModel.ContactsEntity> mData = new ArrayList<>();
    private RecyclerView.LayoutManager mLayoutManager;
    private ProjectContactItemAdapter mProjectContactItemAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cooperate_contact, container, false);
        ButterKnife.bind(this, view);
        mListView.setHasFixedSize(true);
        if (getActivity() != null) mLayoutManager = new LinearLayoutManager(getActivity());
        mListView.setLayoutManager(mLayoutManager);
        mListView.setItemAnimator(new DefaultItemAnimator());
        mListView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        mProjectContactItemAdapter = new ProjectContactItemAdapter();
        mListView.setAdapter(mProjectContactItemAdapter);
        return view;
    }

    private class ProjectContactItemAdapter extends RecyclerView.Adapter<ProjectContactItemAdapter.MyHolder> {

        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyHolder holder = new MyHolder(LayoutInflater.from(getActivity()).inflate(R.layout.fragment_customer_contacts_item, parent, false));
            return holder;
        }

        @Override
        public void onBindViewHolder(MyHolder holder, int position) {
            final CooperateChanceDetailBean.CooperateModel.ContactsEntity contactsEntity = mData.get(position);
            holder.mName.setText(contactsEntity.getContactName() + contactsEntity.getContactTitle() + "");
            holder.mPosition.setText("(" + contactsEntity.getRoleStr() + ")");
            holder.mLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        Intent it = new Intent(getActivity(), CooperateContactsActivity.class);
                        it.putExtra("contactsEntity", contactsEntity);
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





















    public static CooperateContactFragment newInstance(CooperateChanceDetailBean.CooperateModel model) {
        CooperateContactFragment fragment = new CooperateContactFragment();
        Bundle args = new Bundle();
        args.putParcelable("model", model);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            CooperateChanceDetailBean.CooperateModel model = getArguments().getParcelable("model");
            mData = model.getContacts();
        }
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
