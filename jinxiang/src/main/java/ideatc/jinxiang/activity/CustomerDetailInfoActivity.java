package ideatc.jinxiang.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import ideatc.jinxiang.R;
import ideatc.jinxiang.base.activity.ToolBarBaseActivity;
import ideatc.jinxiang.bean.crm.CustomerListBean;
import ideatc.jinxiang.fragment.customer_detail_info_activity.CustomerBasicInfoFragment;
import ideatc.jinxiang.fragment.customer_detail_info_activity.CustomerContactsInfoFragment;
import ideatc.jinxiang.fragment.customer_detail_info_activity.CustomerOtherInfoFragment;

/**
 * 客户详情页
 */
public class CustomerDetailInfoActivity extends ToolBarBaseActivity {

    @Bind(R.id.tablayout)
    TabLayout mTabLayout;
    @Bind(R.id.viewPager)
    ViewPager mViewPager;

    private static final String[] category = {"基本信息", "联系人信息", "其他信息"};
    private List<Fragment> fragments = null;
    private List<String> mTitles = null;
    private FragmentManager fragmentManager;

    private CustomerListBean.CustomerBean customerBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_detail_info);
        ButterKnife.bind(this);
        if (getIntent() != null) customerBean = getIntent().getParcelableExtra("customerBean");
        initToolbar();
        initView();
        initData();
    }

    @Override
    public void initData() {
        fragments = new ArrayList<>();
        mTitles = new ArrayList<>();
        for (int position = 0, size = category.length; position < size; position++) {
            mTitles.add(category[position]);
        }
        fragments.add(CustomerBasicInfoFragment.newInstance(customerBean));
        fragments.add(CustomerContactsInfoFragment.newInstance(customerBean));
        fragments.add(CustomerOtherInfoFragment.newInstance(customerBean));
        MyFragmentPagerAdapter mfp = new MyFragmentPagerAdapter(fragmentManager);
        mViewPager.setAdapter(mfp);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabsFromPagerAdapter(mfp);
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
        getMenuInflater().inflate(R.menu.menu_customer_detail_add_contact, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.add_contact) {
            Intent it = new Intent(CustomerDetailInfoActivity.this,NewCustomerContactInfoActivity.class);
            it.putExtra("customerBean",customerBean);
            it.putExtra("flag",CustomerDetailInfoActivity.class.getSimpleName());
            startActivity(it);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
