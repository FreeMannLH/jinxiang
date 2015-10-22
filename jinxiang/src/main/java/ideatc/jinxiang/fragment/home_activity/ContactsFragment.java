package ideatc.jinxiang.fragment.home_activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ideatc.jinxiang.R;
import ideatc.jinxiang.activity.MyCompanyContactsActivity;
import ideatc.jinxiang.base.activity.BaseActivity;
import ideatc.jinxiang.base.fragment.BaseFragment;
import ideatc.jinxiang.bean.ContactBean;
import ideatc.jinxiang.utils.AnimationUtils;
import ideatc.jinxiang.utils.ParamsUtil;

/**
 * Created by Ccb on 2015/8/6.
 */
public class ContactsFragment extends BaseFragment {

    private SearchView mSearchBtn;
    private ExpandableListView mExpandableListView;
    private SwipyRefreshLayout mSwipeRefreshLayout;

    private List<String> names = new ArrayList<>();
    private List<List<ContactBean>> childArray = new ArrayList<>();
    private Activity context;

    private ContactsAdapter mContactsAdapter;

    /***
     * @return
     */
    public static ContactsFragment instance() {
        Bundle bundle = new Bundle();
//        bundle.putString("key", key);
        ContactsFragment fragment = new ContactsFragment();
        fragment.setUserVisibleHint(true);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_contacts, container, false);
        context = getActivity();
        initView(view);
        getMyContact();
        return view;
    }

    private String searchName = "";
    private boolean isSearchMode = false;

    private void initView(View view) {
        mSwipeRefreshLayout = (SwipyRefreshLayout) view.findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setColorSchemeResources(android.R.color.holo_blue_light, android.R.color.holo_red_light, android.R.color.holo_orange_light, android.R.color.holo_green_light);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection swipyRefreshLayoutDirection) {
                refresh();
            }
        });

        mSearchBtn = (SearchView) view.findViewById(R.id.id_contacts);
        mSearchBtn.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (!TextUtils.isEmpty(query)) {
                    isSearchMode = true;
                    searchName = query;
                    getMyContact();
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (TextUtils.isEmpty(newText)) {
                    //如果在刷新,就取消刷新状态
                    if (mSwipeRefreshLayout.isRefreshing())
                        mSwipeRefreshLayout.setRefreshing(false);
                    //否则就重新获取联系人列表
                } else {
                    refresh();
                }

                return true;
            }
        });
//        mSearchBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//                LayoutInflater inflater = getActivity().getLayoutInflater();
//                View view = inflater.inflate(R.layout.fragment_dialog_search_contacts, null);
//                final EditText eSearchEdit = (EditText) view.findViewById(R.id.id_contacts_search_editText);
//                builder.setView(view).setPositiveButton("搜索", new DialogInterface.OnClickListener() {
//
//                    @Override
//                    public void onClick(DialogInterface dialog, int id) {
//                        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
//                        imm.hideSoftInputFromWindow(eSearchEdit.getWindowToken(), 0);
//                        if (TextUtils.isEmpty(eSearchEdit.getText().toString())) {
//                            SnackBarManager.make(getActivity().getCurrentFocus(), getString(R.string.content_not_right), Snackbar.LENGTH_INDEFINITE);
//                            return;
//                        }
//                        isSearchMode = true;
//                        searchName = eSearchEdit.getText().toString();
//                        getMyContact();
//                    }
//                }).setCancelable(true);
//                builder.create().show();
//            }
//        });
        mExpandableListView = (ExpandableListView) view.findViewById(R.id.id_contacts_expandable);
        mExpandableListView.setMinimumWidth(45);


    }

    private class ContactsAdapter extends BaseExpandableListAdapter {


        @Override
        public int getGroupCount() {
            return names.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return childArray.get(groupPosition).size();
        }

        @Override
        public Object getGroup(int groupPosition) {
            return names.get(groupPosition);
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
            String key = names.get(groupPosition);
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
            final ContactBean contactBean = childArray.get(groupPosition).get(childPosition);
            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.layout_contacs_expandable_child_item, null);
            }
            final TextView tv = (TextView) convertView.findViewById(R.id.
                    id_contacts_expandable_child_tv);
            tv.setText(contactBean.getName());
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it = new Intent(getActivity(), MyCompanyContactsActivity.class);
                    it.putExtra("contactBean", contactBean);
                    ActivityCompat.startActivity(context, it, AnimationUtils.shake(context));

                }
            });
            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return true;
        }

    }


    /**
     * 获取通讯录
     */
    private void getMyContact() {
        String url = URL_OA + "/GetEmployeeList";
        RequestParams params = ParamsUtil.getSignParams();
        if (isSearchMode) params.put("name", searchName);
        httpClient().get(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = BaseActivity.getString(responseBody);
                Log.e("通讯录", result);
                try {

                    JSONArray array = new JSONArray(result);

                    if (array == null || array.length() == 0) {
                        isSearchMode = false;
                        Toast.makeText(getActivity(), R.string.search_fail_no_contact, Toast.LENGTH_SHORT).show();
                        return;
                    }

                    names.clear();
                    childArray.clear();

                    for (int i = 0; i < array.length(); i++) {
                        JSONObject obj = array.getJSONObject(i);
                        names.add(obj.getString("Key"));
                        List<ContactBean> tempArray = new ArrayList<>();
                        JSONArray array1 = obj.getJSONArray("Value");
                        for (int k = 0; k < array1.length(); k++) {
                            JSONObject object = array1.getJSONObject(k);
                            ContactBean contactBean = new Gson().fromJson(object.toString(), ContactBean.class);
                            tempArray.add(contactBean);
                        }
                        childArray.add(tempArray);
                    }

                    mContactsAdapter = new ContactsAdapter();
                    mExpandableListView.setAdapter(mContactsAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("通讯录", statusCode + "");
            }

            @Override
            public void onStart() {
                super.onStart();
//                dialog.show();
                mSwipeRefreshLayout.setRefreshing(true);
            }

            //
            @Override
            public void onFinish() {
                super.onFinish();
//                dialog.dismiss();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }


    /**
     * 刷新
     */
    private void refresh() {
        isSearchMode = false;
        getMyContact();
    }

}
