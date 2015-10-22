package ideatc.jinxiang.widget.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePopupWindow;

import java.text.SimpleDateFormat;
import java.util.Date;

import ideatc.jinxiang.R;
import ideatc.jinxiang.base.manager.SnackBarManager;

/**
 * Created by Ccb on 2015/8/24.
 */
public class SearchDialog extends DialogFragment {

    private TextView etStartDate;
    private TextView etEndDate;
    private TimePopupWindow pwTime;
    private TimePopupWindow pwTime2;

    public static String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }
    public static String getTimeByHours(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        return format.format(date);
    }
    public interface SearchListener {
        void onDateInputComplete(String startDate, String endDate);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_dialog_search, null);
        etStartDate = (TextView) view.findViewById(R.id.id_start_date);
        etEndDate = (TextView) view.findViewById(R.id.id_end_date);
        pwTime = new TimePopupWindow(getActivity(), TimePopupWindow.Type.YEAR_MONTH_DAY);
        pwTime2 = new TimePopupWindow(getActivity(), TimePopupWindow.Type.YEAR_MONTH_DAY);
        //时间选择后回调
        pwTime.setOnTimeSelectListener(new TimePopupWindow.OnTimeSelectListener() {

            @Override
            public void onTimeSelect(Date date) {
                etStartDate.setText(getTime(date));
            }
        });
        pwTime2.setOnTimeSelectListener(new TimePopupWindow.OnTimeSelectListener() {

            @Override
            public void onTimeSelect(Date date) {
                etEndDate.setText(getTime(date));
            }
        });
        //弹出时间选择器
        etStartDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                pwTime.showAtLocation(etStartDate, Gravity.BOTTOM, 0, 0, new Date());
            }
        });
        etEndDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                pwTime2.showAtLocation(etEndDate, Gravity.BOTTOM, 0, 0, new Date());
            }
        });
        builder.setView(view).setPositiveButton("搜索", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                if (TextUtils.isEmpty(etStartDate.getText().toString()) || TextUtils.isEmpty(etEndDate.getText().toString())) {
                    SnackBarManager.make(getActivity().getCurrentFocus(), getString(R.string.content_not_right), Snackbar.LENGTH_INDEFINITE);
                    return;
                }
                SearchListener listener = (SearchListener) getActivity();
                listener.onDateInputComplete(etStartDate.getText().toString(), etEndDate.getText().toString());

            }
        }).setCancelable(true);
        return builder.create();
    }
}
