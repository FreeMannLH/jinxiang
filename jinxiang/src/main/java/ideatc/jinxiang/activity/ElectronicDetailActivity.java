package ideatc.jinxiang.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

import butterknife.Bind;
import butterknife.ButterKnife;
import ideatc.jinxiang.R;
import ideatc.jinxiang.base.activity.ToolBarBaseActivity;
import ideatc.jinxiang.bean.BulletinBean;
import ideatc.jinxiang.utils.ParamsUtil;

public class ElectronicDetailActivity extends ToolBarBaseActivity {


    @Bind(R.id.id_bulletin_title_tv)
    TextView mBulletinTitleTv;
    @Bind(R.id.id_bulletin_time_tv)
    TextView mBulletinTimeTv;
    @Bind(R.id.id_bulletin_maker_tv)
    TextView mBulletinMakerTv;
    @Bind(R.id.id_bulletin_content_tv)
    WebView mBulletinContentTv;


    private String url = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electronic_detail);
        ButterKnife.bind(this);
        url = URL_OA + getString(R.string.GetNotice);
        initToolbar();
        initData();
    }


    @Override
    public void initData() {
        RequestParams params = ParamsUtil.getSignParams();
        if (getIntent() != null) params.put("id", getIntent().getIntExtra("id", 0));
        httpClient().get(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.e(getString(R.string.release_detail), getString(responseBody));
                BulletinBean bulletinBean = new Gson().fromJson(getString(responseBody), BulletinBean.class);
                mBulletinTitleTv.setText(bulletinBean.getTitle());
                mBulletinMakerTv.setText(bulletinBean.getMaker());
                mBulletinTimeTv.setText(bulletinBean.getMakeDateStr());

                mBulletinContentTv.getSettings().setJavaScriptEnabled(true);
                mBulletinContentTv.getSettings().setDefaultTextEncodingName("utf-8");
                mBulletinContentTv.loadData(bulletinBean.getContent(), "text/html;charset=utf-8", "null");

//                mBulletinContentTv.setText(bulletinBean.getContent());
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
    public void initView() {

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

}
