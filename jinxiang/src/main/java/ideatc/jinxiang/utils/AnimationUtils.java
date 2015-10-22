package ideatc.jinxiang.utils;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;

import ideatc.jinxiang.R;

/**
 * Created by Administrator on 2015/8/6.
 * activity切换动画
 */
public class AnimationUtils {

    /***
     * 震动
     *
     * @return
     */
    public static Bundle shake(Context context) {
        return ActivityOptionsCompat.makeCustomAnimation(context.getApplicationContext(), R.anim.abc_grow_fade_in_from_bottom, R.anim.abc_shrink_fade_out_from_bottom).toBundle();
    }

    /***
     * 左右滑动进入退出
     *
     * @return
     */
    public static Bundle slide_from_left(Context context) {
        return ActivityOptionsCompat.makeCustomAnimation(context.getApplicationContext(), R.anim.fab_in, R.anim.fab_out).toBundle();
    }

    /***
     * 上进下出
     *
     * @return
     */
    public static Bundle slide_from_top(Context context) {
        return ActivityOptionsCompat.makeCustomAnimation(context.getApplicationContext(), R.anim.abc_slide_in_top, R.anim.abc_slide_out_bottom).toBundle();
    }

    /***
     * 从无到有缩放，相册显示图片
     *
     * @return
     */
    public static Bundle scale(View view) {
        return ActivityOptionsCompat.makeScaleUpAnimation(view, view.getWidth() / 2, view.getHeight() / 2, 0, 0).toBundle();
    }

}
