package ideatc.jinxiang.widget.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import ideatc.jinxiang.R;

public class LoadingDialog extends Dialog {
	private TextView tv;

	public LoadingDialog(Context context) {
		super(context, R.style.MyDialogStyle);
		View view = LayoutInflater.from(context).inflate(
				R.layout.dialog_loading, null);
		tv = (TextView) view.findViewById(R.id.tv_loading);
		this.setContentView(view);
	}

	public void setText(String text) {
		tv.setText(text);
	}
}
