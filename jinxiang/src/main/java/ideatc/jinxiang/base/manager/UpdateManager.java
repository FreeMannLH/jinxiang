package ideatc.jinxiang.base.manager;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.view.ViewGroup.LayoutParams;
import android.widget.ProgressBar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import ideatc.jinxiang.R;
import me.zhanghai.android.materialprogressbar.HorizontalProgressDrawable;

public class UpdateManager {

	private Context mContext;
	private String apkUrl = "";
	private Dialog downloadDialog;
	private static final String savePath = "/sdcard/";
	private static final String saveFileName = savePath + "jinxiang.apk";
	private ProgressBar mProgress;
	private static final int DOWN_UPDATE = 1;
	private static final int DOWN_OVER = 2;
	private int progress;
	private Thread downLoadThread;
	private boolean interceptFlag = false;
//	private TextView view;
	private android.support.v7.app.AlertDialog.Builder builder;

	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case DOWN_UPDATE:
				mProgress.setProgress(progress);
				downloadDialog.setTitle(mContext.getString(R.string.is_loading) + progress + mContext.getString(R.string.is_loading1));
//				view.setText(mContext.getString(R.string.is_loading) + progress + mContext.getString(R.string.is_loading1));
				break;
			case DOWN_OVER:
				mProgress.setProgress(100);
				downloadDialog.setTitle(R.string.download_success);
//				view.setText(R.string.download_success);
				downloadDialog.dismiss();
				installApk();
				break;
			default:
				break;
			}
		};
	};

	public UpdateManager(Context context) {
		this.mContext = context;
	}
	public void startUpdateInfo(String url) {
		apkUrl = url;
		showDownloadDialog();
	}


	private void showDownloadDialog() {
		builder = new android.support.v7.app.AlertDialog.Builder(mContext);
		builder.setTitle(mContext.getString(R.string.downloading));
		builder.setMessage(mContext.getString(R.string.install_tint));
		mProgress = new ProgressBar(mContext, null,
				android.R.attr.progressBarStyleHorizontal);
		mProgress.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		mProgress.setProgressDrawable(new HorizontalProgressDrawable(mContext));
//		mProgress.setProgress(50);
//		view = new TextView(mContext);
//		view.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
//				LayoutParams.WRAP_CONTENT));
//		view.setPadding(20,20,20,20);
//		view.setText(R.string.ready_download);
//		view.setTextSize(18);
		builder.setView(mProgress);
//		builder.setCustomTitle(view);
		builder.setNegativeButton(R.string.cancel, new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
				interceptFlag = true;

			}
		});
		downloadDialog = builder.create();
		downloadDialog.show();

		downloadApk();
	}

	private Runnable mDownApkRunnable = new Runnable() {
		@Override
		public void run() {
			try {
				URL url = new URL(apkUrl);

				HttpURLConnection conn = (HttpURLConnection) url
						.openConnection();
				conn.connect();
				int length = conn.getContentLength();
				InputStream is = conn.getInputStream();

				File file = new File(savePath);
				if (!file.exists()) {
					file.mkdir();
				}
				String apkFile = saveFileName;
				File ApkFile = new File(apkFile);
				FileOutputStream fos = new FileOutputStream(ApkFile);

				int count = 0;
				byte buf[] = new byte[1024];

				do {
					int numberRead = is.read(buf);
					count += numberRead;
					progress = (int) (((float) count / length) * 100);

					mHandler.sendEmptyMessage(DOWN_UPDATE);
					if (numberRead <= 0) {
						mHandler.sendEmptyMessage(DOWN_OVER);
						break;
					}
					fos.write(buf, 0, numberRead);
				} while (!interceptFlag);
				fos.close();
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	};

	private void downloadApk() {
		downLoadThread = new Thread(mDownApkRunnable);
		downLoadThread.start();
	}

	private void installApk() {

		File apkFile = new File(saveFileName);
		if (!apkFile.exists()) {
			return;
		}
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setDataAndType(Uri.parse("file://" + apkFile.toString()),
				"application/vnd.android.package-archive");
		mContext.startActivity(i);
	}
}
