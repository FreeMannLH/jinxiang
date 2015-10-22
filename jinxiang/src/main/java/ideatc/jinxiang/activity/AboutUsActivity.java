package ideatc.jinxiang.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import ideatc.jinxiang.R;
import ideatc.jinxiang.base.activity.ToolBarBaseActivity;
import ideatc.jinxiang.utils.AndroidUtils;

public class AboutUsActivity extends ToolBarBaseActivity {

    @Bind(R.id.version)
    TextView tv_version;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        ButterKnife.bind(this);
        initToolbar();
        initView();
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        String version = AndroidUtils.getVersionName(AboutUsActivity.this);
        tv_version.setText(getString(R.string.version_code) + version);
    }

    @Override
    public void initToolbar() {
        getToolbar().setNavigationIcon(R.drawable.back);
        getToolbar().setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
