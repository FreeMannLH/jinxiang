package ideatc.jinxiang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.sevenheaven.segmentcontrol.SegmentControl;

import butterknife.ButterKnife;
import ideatc.jinxiang.R;
import ideatc.jinxiang.base.activity.ToolBarBaseActivity;
import ideatc.jinxiang.fragment.mycustomer_activity.MyCustomerFragment;

public class MyCustomerActivity extends ToolBarBaseActivity {

    private FragmentManager fm;
    private FragmentTransaction ft;
    private SegmentControl mSegmentControl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_customer);
        ButterKnife.bind(this);
        initToolbar();
        mSegmentControl = (SegmentControl) findViewById(R.id.id_seg);
        mSegmentControl.setSelected(true);
        initView();

    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        fm = getSupportFragmentManager();
        mSegmentControl.setOnSegmentControlClickListener(new SegmentControl.OnSegmentControlClickListener() {
            @Override
            public void onSegmentControlClick(int index) {
                ft = fm.beginTransaction();
                switch (index) {
                    case 0:
                        ft.replace(R.id.id_myCustomer_frameLayout, MyCustomerFragment.newInstance(1));
                        ft.commit();
                        break;
                    case 1:
                        ft.replace(R.id.id_myCustomer_frameLayout, MyCustomerFragment.newInstance(2));
                        ft.commit();
                        break;
                }
            }
        });
        mSegmentControl.setSelectedIndex(0);
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
        getMenuInflater().inflate(R.menu.menu_customer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.id_add_customer:
                addNewCustomer();
                break;
        }
        return true;
    }

    /**
     * 新增客户
     */
    private void addNewCustomer() {
        Intent it = new Intent(MyCustomerActivity.this, NewCustomerActivity.class);
        startActivity(it);
    }

}
