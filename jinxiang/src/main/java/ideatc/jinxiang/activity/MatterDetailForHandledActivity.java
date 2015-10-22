package ideatc.jinxiang.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

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
import ideatc.jinxiang.bean.WaitHandleListBean;
import ideatc.jinxiang.utils.ParamsUtil;
import ideatc.jinxiang.utils.SPUtils;

public class MatterDetailForHandledActivity extends ToolBarBaseActivity {

    @Bind(R.id.matter_detail_tablayout)
    TabLayout mTabLayout;
    @Bind(R.id.viewPager)
    ViewPager mViewPager;
    @Bind(R.id.toolBar_layout)
    Toolbar mToolbar;

    private List<Fragment> fragments = null;
    private List<String> mTitles = null;
    private FragmentManager fm;
    private String url = "";
    private WaitHandleListBean.RowsEntity rowsEntity;
    private MyFragmentPagerAdapter mfp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matter_detail);
        ButterKnife.bind(this);
        url = URL_OA + "/SysFlowInfo";

        if (getIntent() != null) rowsEntity = getIntent().getParcelableExtra("waitHandleBean");
        initToolbar();
        initView();
        initData();
    }

    @Override
    public void initData() {
        fragments = new ArrayList<>();
        mTitles = new ArrayList<>();
        mTitles.add(getString(R.string.basic_info));
        mTitles.add(getString(R.string.handle_opinion));
        mTitles.add(getString(R.string.sysflow_log));
        fm = getSupportFragmentManager();
        mfp = new MyFragmentPagerAdapter(fm);
        getSysFlowInfo();
    }

    @Override
    public void initView() {
        mTabLayout.setTabTextColors(Color.BLACK, getResources().getColor(R.color.material_blue_toolbar));
        mTabLayout.addTab(mTabLayout.newTab(), true);
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

    @Override
    public void initToolbar() {
        mToolbar.setTitle("日常工作");
        mToolbar.setNavigationIcon(R.drawable.back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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

    /**
     * 获取单据信息
     */
    private void getSysFlowInfo() {
        RequestParams params = ParamsUtil.getSignParams();
        params.put("sysUserId", SPUtils.getInt("id", 0));
        params.put("sysFlowId", rowsEntity.getSysFlowId());
        params.put("keyId", rowsEntity.getKeyId());
        Log.e("已办单据参数","sysUserId="+SPUtils.getInt("id", 0)+",sysFlowId="+ rowsEntity.getSysFlowId()+",keyId="+ rowsEntity.getKeyId());
        httpClient().get(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.e("已办流程信息", getString(responseBody));
//                SysFlowInfoBean sysFlowInfoBean = new Gson().fromJson(getString(responseBody), SysFlowInfoBean.class);
                String result = getString(responseBody);
                try {
                    JSONObject object = new JSONObject(result);
                    //基本信息的string

                    String orderStr = object.getJSONObject("Orders").toString();
                    String logStr = object.getJSONArray("SysFlowLog").toString();
                    String modelName = object.getString("ModelName");
                    fragments.add(ideatc.jinxiang.fragment.matters_detail_handled_acitivty.BasicInfoFragment.newInstance(modelName, orderStr,  rowsEntity));
                    fragments.add(ideatc.jinxiang.fragment.matters_detail_handled_acitivty.HandleOpinionFragment.instance(logStr));
                    fragments.add(ideatc.jinxiang.fragment.matters_detail_handled_acitivty.SysFlowLogFragment.instance(logStr));
                    mViewPager.setAdapter(mfp);
                    mTabLayout.setupWithViewPager(mViewPager);
                    mTabLayout.setTabsFromPagerAdapter(mfp);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log(statusCode);
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
}
