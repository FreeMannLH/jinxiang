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
import ideatc.jinxiang.bean.crm.ProjectDetailBean;
import ideatc.jinxiang.bean.crm.ProjectListBean;
import ideatc.jinxiang.fragment.project_detail_activity.ProjectBasicInfoFragment;
import ideatc.jinxiang.fragment.project_detail_activity.ProjectBusinessFragment;
import ideatc.jinxiang.fragment.project_detail_activity.ProjectContactFragment;
import ideatc.jinxiang.fragment.project_detail_activity.ProjectRateFragment;
import ideatc.jinxiang.utils.ParamsUtil;

public class ProjectDetailActivity extends ToolBarBaseActivity {

    @Bind(R.id.tablayout)
    TabLayout mTabLayout;
    @Bind(R.id.viewPager)
    ViewPager mViewPager;

    private static final String[] category = {"基本信息", "联系人", "业务事项", "项目进度"};
    private List<Fragment> fragments = null;
    private List<String> mTitles = null;
    private FragmentManager fragmentManager;

    private ProjectListBean.ProjectBean projectBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_detail);
        ButterKnife.bind(this);
        if (getIntent() != null) projectBean = getIntent().getParcelableExtra("projectBean");
        initToolbar();
        initView();
        initData();
        getProjectDetail();
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
//        mViewPager = (ViewPager) findViewById(R.id.viewPager);

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

    /**
     * 获取项目详细信息
     */
    private void getProjectDetail() {
        String url = URL_OA + "/getcontractinfo";
        RequestParams params = ParamsUtil.getSignParams();
        params.put("uid", user.getId());
        params.put("contract", projectBean.getId());
        httpClient().get(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.e("项目详细", getString(responseBody));

                ProjectDetailBean projectDetailBean = new Gson().fromJson(getString(responseBody), ProjectDetailBean.class);
                if (projectDetailBean != null) {
                    ProjectDetailBean.ModelEntity modelEntity = projectDetailBean.getModel();
                    fragments.add(ProjectBasicInfoFragment.newInstance(modelEntity));
                    fragments.add(ProjectContactFragment.newInstance(modelEntity));
                    fragments.add(ProjectBusinessFragment.newInstance(modelEntity));
                    fragments.add(ProjectRateFragment.newInstance(modelEntity));
                    MyFragmentPagerAdapter mfp = new MyFragmentPagerAdapter(fragmentManager);
                    mViewPager.setAdapter(mfp);
                    mTabLayout.setupWithViewPager(mViewPager);
                    mTabLayout.setTabsFromPagerAdapter(mfp);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("项目详细", statusCode + "");
            }

            @Override
            public void onFinish() {
                super.onFinish();
                dialog.dismiss();
            }

            @Override
            public void onStart() {
                super.onStart();
                dialog.show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_project, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.id_start_project:
                checkCustomerInfo();
//                Toast.makeText(ProjectDetailActivity.this, "发起流程", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    /**
     * 检查客户信息是否完整
     */
    private void checkCustomerInfo() {
        RequestParams params = ParamsUtil.getSignParams();
        params.put("cid",projectBean.getCustomerId());
        httpClient().get(URL_OA + "/CheckCustomerInfo", params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = getString(responseBody);
                Log.e("检查客户信息完整性", result);
                try {
                    JSONObject obj = new JSONObject(result);
                    if (obj.getBoolean("Status")) {
                        sendContractToFlow();
                    } else {
                        Toast.makeText(ProjectDetailActivity.this, "抱歉,请先完善客户资料", Toast.LENGTH_SHORT).show();
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

    private void sendContractToFlow() {
        Intent it = new Intent(ProjectDetailActivity.this,PostProjectToFlowActivity.class);
        it.putExtra("keyId",projectBean.getCode());
        startActivity(it);
        finish();
    }
}
