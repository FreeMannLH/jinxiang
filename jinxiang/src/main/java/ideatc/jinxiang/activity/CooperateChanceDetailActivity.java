package ideatc.jinxiang.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import ideatc.jinxiang.R;
import ideatc.jinxiang.base.activity.ToolBarBaseActivity;
import ideatc.jinxiang.bean.crm.CooperateChanceDetailBean;
import ideatc.jinxiang.fragment.cooperate_chance_detail_activity.CooperateBasicInfoFragment;
import ideatc.jinxiang.fragment.cooperate_chance_detail_activity.CooperateContactFragment;
import ideatc.jinxiang.fragment.cooperate_chance_detail_activity.CooperateMattersFragment;
import ideatc.jinxiang.fragment.cooperate_chance_detail_activity.CooperateStageFragment;
import ideatc.jinxiang.utils.ParamsUtil;

public class CooperateChanceDetailActivity extends ToolBarBaseActivity {

    @Bind(R.id.tablayout)
    TabLayout mTabLayout;
    @Bind(R.id.viewPager)
    ViewPager mViewPager;

    private static final String[] category = {"基本资料", "联系人", "业务事项", "阶段变更"};

    private String cooperateId;

    private List<Fragment> fragments = null;
    private List<String> mTitles = null;
    private FragmentManager fragmentManager;

    private CooperateChanceDetailBean.CooperateModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cooperate_chance_detail);
        ButterKnife.bind(this);
        if (getIntent() != null) cooperateId = getIntent().getStringExtra("cooperateId");
        initToolbar();
        initView();
        initData();
        getCooperateChanceDetailInfo();
    }


    /**
     * 获取合作机会详细信息
     */
    private void getCooperateChanceDetailInfo() {
        RequestParams params = ParamsUtil.getSignParams();
        params.put("uid", user.getId());
        params.put("chance", cooperateId);
        httpClient().get(URL_OA + "/getchanceinfo", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = getString(responseBody);
                Gson gson = new Gson();
                CooperateChanceDetailBean bean = gson.fromJson(result, CooperateChanceDetailBean.class);
                if (bean != null) {
                    model = bean.getCooperatmodel();
                    fragments.add(CooperateBasicInfoFragment.newInstance(model));
                    fragments.add(CooperateContactFragment.newInstance(model));
                    fragments.add(CooperateMattersFragment.newInstance(model));
                    fragments.add(CooperateStageFragment.newInstance(model));
                    MyFragmentPagerAdapter mfp = new MyFragmentPagerAdapter(fragmentManager);
                    mViewPager.setAdapter(mfp);
                    mTabLayout.setupWithViewPager(mViewPager);
                    mTabLayout.setTabsFromPagerAdapter(mfp);

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
    public void initData() {
        fragments = new ArrayList<>();
        mTitles = new ArrayList<>();
        for (int position = 0, size = category.length; position < size; position++) {
            mTitles.add(category[position]);
        }
    }

    @Override
    public void initView() {
        fragmentManager = getSupportFragmentManager();
        //主页导航分类
        mTabLayout.setTabTextColors(Color.BLACK, getResources().getColor(R.color.material_blue_toolbar));
        mTabLayout.addTab(mTabLayout.newTab(), true);
        mTabLayout.addTab(mTabLayout.newTab(), false);
        mTabLayout.addTab(mTabLayout.newTab(), false);
        mTabLayout.addTab(mTabLayout.newTab(), false);
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private class MyFragmentPagerAdapter extends FragmentPagerAdapter {


        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles.get(position);
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cooperate_chance_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.stage_change) {
            Intent it = new Intent(CooperateChanceDetailActivity.this, CooperateChangeStageActivity.class);
            it.putExtra("cooperateId", cooperateId);
            startActivity(it);
            finish();
            return true;

        } else if (id == R.id.transform_project) {
            Gson gson = new Gson();
            String contactJson = "";
            for (int i = 0, size = model.getContacts().size(); i < size; i++) {
                CooperateChanceDetailBean.CooperateModel.ContactsEntity bean = model.getContacts().get(i);
                bean.setModifyTime("");
                bean.setCreateTime("");
                if (i == size - 1) contactJson = contactJson + gson.toJson(bean);
                else contactJson = contactJson + gson.toJson(bean) + ",";
            }
            Log.e("contactJson", contactJson);

            String mattersJson = "";
            for (int i = 0, size = model.getMatters().size(); i < size; i++) {
                CooperateChanceDetailBean.CooperateModel.MattersEntity bean = model.getMatters().get(i);
                bean.setModifyTime("");
                bean.setCreateTime("");

                if (i == size - 1) mattersJson = mattersJson + gson.toJson(bean);
                else mattersJson = mattersJson + gson.toJson(bean) + ",";
            }

            Intent it = new Intent(CooperateChanceDetailActivity.this, CooperateTransProjectActivity.class);
            it.putExtra("customerId", model.getCustomerId());
            it.putExtra("cooperateId", cooperateId);
            it.putExtra("contactJson", contactJson);
            it.putExtra("mattersJson", mattersJson);
            startActivity(it);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
