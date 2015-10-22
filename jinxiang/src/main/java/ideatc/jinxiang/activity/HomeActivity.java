package ideatc.jinxiang.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.lidroid.xutils.exception.DbException;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;

import java.util.ArrayList;
import java.util.List;

import ideatc.jinxiang.R;
import ideatc.jinxiang.adapter.MyFragmentViewPagerAdapter;
import ideatc.jinxiang.base.activity.ToolBarBaseActivity;
import ideatc.jinxiang.constans.Constants;
import ideatc.jinxiang.constans.URL;
import ideatc.jinxiang.fragment.home_activity.ContactsFragment;
import ideatc.jinxiang.fragment.home_activity.CustomerManageFragment;
import ideatc.jinxiang.fragment.home_activity.ElectronicBulletinFragment;
import ideatc.jinxiang.fragment.home_activity.MattersFragment;
import ideatc.jinxiang.utils.AndroidUtils;
import ideatc.jinxiang.utils.AnimationUtils;
import ideatc.jinxiang.widget.dialog.FinishDialog;
import ideatc.jinxiang.widget.dialog.VersionUpdateDialog;

public class HomeActivity extends ToolBarBaseActivity {


    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    protected Activity context;
    private FragmentManager fragmentManager;
    private TabLayout mTabLayout;


    private ViewPager mViewPager;
    private NavigationView mNavigationView;

    private List<Fragment> fragments = null;
    private List<String> mTitles = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        context = HomeActivity.this;
        fragmentManager = getSupportFragmentManager();


        initToolbar();
        initView();
        initData();
    }


    @Override
    public void initData() {

        fragments = new ArrayList<>();
        mTitles = new ArrayList<>();
        for (int position = 0, size = Constants.category.length; position < size; position++) {
            mTitles.add(Constants.category[position]);
            if (position == 0) {
                fragments.add(MattersFragment.instance());
                continue;
            }
            if (position == 3) {
                fragments.add(ContactsFragment.instance());
                continue;
            }
            if (position == 1) {
                fragments.add(ElectronicBulletinFragment.instance());
                continue;
            }
            if (position == 2) {
                fragments.add(CustomerManageFragment.newInstance());
                continue;
            }
        }

        MyFragmentViewPagerAdapter mPagerAdapter = new MyFragmentViewPagerAdapter(fragmentManager, mViewPager, fragments, mTitles);
//        MyFragmentPagerAdapter mfp = new MyFragmentPagerAdapter(fragmentManager);
        mViewPager.setOffscreenPageLimit(0);
        mViewPager.setAdapter(mPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabsFromPagerAdapter(mPagerAdapter);
    }

    private ImageView headerIamgeView = null;
//    private TextView mUserName;
//    private TextView mPostion;

    @Override
    public void initView() {

        //侧滑控件
        drawerLayout = (DrawerLayout) findViewById(R.id.drawLayout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(context, drawerLayout, getToolbar(), R.string.open, R.string.close);
        actionBarDrawerToggle.syncState();
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });


        //侧滑菜单栏
        mNavigationView = (NavigationView) findViewById(R.id.navigationview_menu);


        headerIamgeView = (ImageView) mNavigationView.findViewById(R.id.iv_head_face);
        headerIamgeView.setImageResource(R.drawable.logo);
//        headerIamgeView.setOnClickListener(this);
        setupDrawerContent(mNavigationView);

        mViewPager = (ViewPager) findViewById(R.id.viewPager);


        //主页导航分类
        mTabLayout = (TabLayout) findViewById(R.id.tablayout);
        mTabLayout.setTabTextColors(Color.WHITE, getResources().getColor(R.color.material_blue_grey_800));
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

//    private void initTabStrip() {
//        mTabStrip = (PagerSlidingTabStrip) findViewById(R.id.tabStrip);
//        mTabStrip.setViewPager(mViewPager);
////        mTabStrip.setIndicatorColorResource(R.color.material_green_A200);
//        mTabStrip.setDividerColor(R.attr.colorPrimary);
//        mTabStrip.setUnderlineHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, getResources().getDisplayMetrics()));
//        mTabStrip.setIndicatorHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics()));
//        mTabStrip.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
//    }


    @Override
    public void initToolbar() {
        getToolbar().setTitle(getString(R.string.app_name));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(

                new NavigationView.OnNavigationItemSelectedListener() {

                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        int itemId = menuItem.getItemId();
                        menuItem.setChecked(false);
//                        drawerLayout.closeDrawers();
                        Intent intent = null;
                        switch (itemId) {
                            case R.id.id_menu_myInfo:
                                try {
                                    if (getLoginStatus())
                                        intent = new Intent(context, MyInfoActivity.class);
                                    else intent = new Intent(context, LoginActivity.class);

                                } catch (DbException e) {
                                    e.printStackTrace();
                                }
                                break;
                            case R.id.id_menu_setting:
                                intent = new Intent(context, SettingActivity.class);
                                break;
                        }
                        ActivityCompat.startActivity(context, intent, AnimationUtils.shake(context));
                        return true;
                    }
                });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.id_exit:
                onBackPressed();
                break;
            case R.id.id_check_update:
                checkUpdate();
                break;
        }
//        return super.onOptionsItemSelected(item);
        return true;
    }


    @Override
    public void onBackPressed() {

        FinishDialog dialog = new FinishDialog();
        dialog.show(getSupportFragmentManager(), "退出应用");
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            if (!getLoginStatus()) finish();

        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    /**
     * 检查版本
     */

    private void checkUpdate() {

        httpClient().get(URL.CHECK_APP_VERSION, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = getString(responseBody);
                result = result.substring(1, result.length() - 1);
                Log.e("check update", result);
                if (AndroidUtils.getVersionName(HomeActivity.this).equals(result))
                    Toast.makeText(HomeActivity.this, R.string.not_need_update, Toast.LENGTH_SHORT).show();
                else {
                    VersionUpdateDialog dialog = VersionUpdateDialog.instance(URL.APP_DOWNLOAD_URL);
                    dialog.show(getSupportFragmentManager(), getString(R.string.check_update));
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("check update", statusCode + "");
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

}
