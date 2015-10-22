package ideatc.jinxiang.base.manager;

import android.app.Activity;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

import ideatc.jinxiang.R;
/**
 * @author ccb
 * create at 2015/9/7 10:57
 */
public class SnackBarManager {

    //setAction方法中的listener不能为null,否则无法正常显示String

    public static void make_toast(final Activity activity, View view, final String msg, int time) {
        Snackbar.make(view, msg, time).setAction(R.string.close, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
            }
        }).show();
    }

    public static void make(View view, final String msg, int time) {
        Snackbar.make(view, msg, time).setAction(R.string.close, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        }).show();
    }
}
