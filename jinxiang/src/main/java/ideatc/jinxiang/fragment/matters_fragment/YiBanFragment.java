package ideatc.jinxiang.fragment.matters_fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import fr.castorflex.android.smoothprogressbar.SmoothProgressBar;
import ideatc.jinxiang.R;
import ideatc.jinxiang.activity.MatterDetailForHandledActivity;
import ideatc.jinxiang.base.activity.BaseActivity;
import ideatc.jinxiang.base.fragment.BaseFragment;
import ideatc.jinxiang.bean.WaitHandleListBean;
import ideatc.jinxiang.constans.Constants;
import ideatc.jinxiang.utils.ParamsUtil;
import ideatc.jinxiang.utils.SPUtils;

public class YiBanFragment extends BaseFragment {

    @Bind(R.id.matters_expandable_listview)
    ExpandableListView mExpandableListView;

    private SmoothProgressBar mProgress;

    private List<String> groupArray = new ArrayList<>();
    private List<List<WaitHandleListBean.RowsEntity>> childArray = new ArrayList<>();

    private List<WaitHandleListBean.RowsEntity> expressMailModels = new ArrayList<>();
    private List<WaitHandleListBean.RowsEntity> sealLendModels = new ArrayList<>();
    private List<WaitHandleListBean.RowsEntity> sendCarModels = new ArrayList<>();
    private List<WaitHandleListBean.RowsEntity> scrappedModels = new ArrayList<>();
    private List<WaitHandleListBean.RowsEntity> contractModels = new ArrayList<>();
    private List<WaitHandleListBean.RowsEntity> temp = new ArrayList<>();
    private List<WaitHandleListBean.RowsEntity> leaveModels = new ArrayList<>();

    private String url;
    private DaiBanAdapter mDaiBanAdapter;

    /***
     * @return DaiBanFragment
     */
    public static YiBanFragment newInstance() {
        YiBanFragment fragment = new YiBanFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_dai_ban, container, false);
        ButterKnife.bind(this, view);
        url =URL_OA + "/GetHandled";
        mExpandableListView.setMinimumWidth(45);
        mProgress = (SmoothProgressBar) view.findViewById(R.id.progress);
        for (String name : Constants.matters_daiban_category) {
            groupArray.add(name);
        }
        for (int i = 0; i < groupArray.size(); i++) {
            childArray.add(temp);
        }
        mDaiBanAdapter = new DaiBanAdapter();
        mExpandableListView.setAdapter(mDaiBanAdapter);
        getData();
        return view;
    }


    private class DaiBanAdapter extends BaseExpandableListAdapter {


        @Override
        public int getGroupCount() {
            return groupArray.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return childArray.get(groupPosition).size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return groupArray.get(groupPosition);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return childArray.get(groupPosition).get(childPosition);
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return false;
        }

        @Override
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            String key = groupArray.get(groupPosition);
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.layout_contacs_expandable_group_item, null);
            }
            TextView tv = (TextView) convertView.findViewById(R.id.
                    id_contacts_expandable_group_tv);
                tv.setText(key + " ( " + childArray.get(groupPosition).size() + " )");
            return convertView;
        }

        @Override
        public View getChildView(final int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            final WaitHandleListBean.RowsEntity rowsEntity = childArray.get(groupPosition).get(childPosition);
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.layout_matters_expandable_child_item, null);
            }
            TextView name = (TextView) convertView.findViewById(R.id.id_matters_expandable_child_name);
            TextView detail = (TextView) convertView.findViewById(R.id.id_matters_expandable_child_detail);
            TextView time = (TextView) convertView.findViewById(R.id.id_matters_expandable_child_time);
            name.setText(rowsEntity.getMaker());
            time.setText(rowsEntity.getSendDateStr());
            detail.setText(rowsEntity.getSysFlow());
            final LinearLayout layout = (LinearLayout) convertView.findViewById(R.id.
                    id_matters_child_layout);
            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it = new Intent(getActivity(), MatterDetailForHandledActivity.class);
                    //rowsEntity 具体的待办事项
                    it.putExtra("waitHandleBean", rowsEntity);
                    startActivity(it);
                }
            });
            //child view 监听
            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    /**
     * 获取待办事项列表
     */
    private void getData() {
        RequestParams params =  ParamsUtil.getSignParams();
        params.put("sysUserId", SPUtils.getInt("id", 0));
        httpClient().get(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.e("我的待办事项", BaseActivity.getString(responseBody));
                Log(BaseActivity.getString(responseBody));
                WaitHandleListBean waitHandleListBean = new Gson().fromJson(BaseActivity.getString(responseBody), WaitHandleListBean.class);
                for (WaitHandleListBean.RowsEntity rowsEntity : waitHandleListBean.getRows()) {
                    String modelName = rowsEntity.getModelName();
                    Log.e("modelName", modelName);

                    //派车
                    if ("SendCarModel".equals(modelName)) {
                        sendCarModels.add(rowsEntity);

                    }
                    //人力
                    else if ("LeaveModel".equals(modelName)) {
                        leaveModels.add(rowsEntity);
                    }
                    //公章
                    else if ("SealLendModel".equals(modelName)) {
                        sealLendModels.add(rowsEntity);
                    }
                    //快递
                    else if ("ExpressMailModel".equals(modelName) || "ExpressDeliveryModel".equals(modelName)) {
                        expressMailModels.add(rowsEntity);
                    }
                    //物品
                    else if ("ScrappedModel".equals(modelName)) {
                        scrappedModels.add(rowsEntity);
                    }
                    //公章
                    else if ("StampManagerModel".equals(modelName)) {
                        sealLendModels.add(rowsEntity);
                    }
                    //物品
                    else if ("OfficeRecipientsModel".equals(modelName)) {
                        scrappedModels.add(rowsEntity);
                    }
                    //物品采购
                    else if ("PurchaseOrderModel".equals(modelName)) {
                        scrappedModels.add(rowsEntity);
                    }
                    //业务
                    else if ("ContractModel".equals(modelName)) {
                        contractModels.add(rowsEntity);
                    }
                }
                childArray.clear();
                for (int i = 0; i < groupArray.size(); i++) {

                    if (i == 0) {
                        childArray.add(scrappedModels);
                    } else if (i == 1) {
                        childArray.add(leaveModels);
                    } else if (i == 2) {
                        childArray.add(sendCarModels);
                    } else if (i == 3) {
                        childArray.add(expressMailModels);
                    } else if (i == 4) {
                        childArray.add(sealLendModels);
                    } else if (i == 5) {
                        childArray.add(contractModels);
                    }
                }
                Log.e("childarray", childArray.size() + "");
                mDaiBanAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log(statusCode);
            }

            @Override
            public void onStart() {
                super.onStart();
                mProgress.setIndeterminate(true);
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mProgress.setIndeterminate(false);
            }
        });
    }
}
