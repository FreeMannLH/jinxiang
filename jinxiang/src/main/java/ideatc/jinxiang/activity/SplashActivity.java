package ideatc.jinxiang.activity;

import android.os.Bundle;
import android.view.Window;
import android.view.animation.Animation;
import android.widget.RelativeLayout;

import com.lidroid.xutils.exception.DbException;

import ideatc.jinxiang.R;
import ideatc.jinxiang.base.activity.BaseActivity;

public class SplashActivity extends BaseActivity {

    private Animation mAnimIn;

    private RelativeLayout mLayout;

    private static final int ANIM_DURATION = 1800;

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        mLayout = (RelativeLayout) findViewById(R.id.id_splash_view);
        mAnimIn = android.view.animation.AnimationUtils.loadAnimation(SplashActivity.this, R.anim.abc_grow_fade_in_from_bottom);
        mAnimIn.setDuration(ANIM_DURATION);

        mAnimIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                jump();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        mLayout.startAnimation(mAnimIn);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
    }

    /**
     * 跳转
     */
    private void jump() {
        try {
            if (getLoginStatus()) startActivity(HomeActivity.class);
            else startActivity(LoginActivity.class);

        } catch (DbException e) {
            e.printStackTrace();
        }
        finish();
    }
}
