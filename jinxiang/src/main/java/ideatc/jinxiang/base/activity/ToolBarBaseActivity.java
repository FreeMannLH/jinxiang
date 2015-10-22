package ideatc.jinxiang.base.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import ideatc.jinxiang.R;

/**
 * 带Toolbar的基类
 */
public abstract class ToolBarBaseActivity extends BaseActivity {


    private Toolbar mToolBar;

    public Toolbar getToolbar() {
        mToolBar.setTitleTextColor(R.color.material_blue_500);
        return mToolBar;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void setContentView(int layoutResID) {
        setContentView(View.inflate(this, layoutResID, null));
        mToolBar = (Toolbar) findViewById(R.id.toolBar);
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        mToolBar = (Toolbar) findViewById(R.id.toolBar);
        if (mToolBar != null){
            setSupportActionBar(mToolBar);
//            mToolBar.setTitleTextColor(android.R.color.black);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
//            case R.id.action_exit:
//
//                break;
//            case R.id.action_version_info:
//
//                break;
        }
//        return super.onOptionsItemSelected(item);
        return true;
    }


    public abstract void initToolbar();
}
