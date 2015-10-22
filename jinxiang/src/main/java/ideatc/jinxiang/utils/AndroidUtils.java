package ideatc.jinxiang.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.DisplayMetrics;

/**
 * ��ȡ�汾��
 * 
 * @author liquancai
 * 
 *         liquancai1223@163.com 2014-3-1
 */
public class AndroidUtils {

	/***
	 * ��ȡ����汾��
	 * 
	 * @param context
	 * @return
	 */
	public static String getVersionName(Context context) {
		PackageManager pm = context.getPackageManager();// contextΪ��ǰActivity������
		PackageInfo pi = null;
		try {
			pi = pm.getPackageInfo(context.getPackageName(), 0);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String version = pi.versionName;
		return version;
	}

	/**
	 * ��ȡ�ֱ��� ���
	 * 
	 * @param context
	 * @return
	 */
	public static int getDisplayWidth(Context context) {
		// int screenWidth = ((Activity)
		// context).getWindowManager().getDefaultDisplay().getWidth(); //
		// ��Ļ�?���أ��磺480px��
		// int screenHeight = ((Activity)
		// context).getWindowManager().getDefaultDisplay().getHeight();
		DisplayMetrics metric = new DisplayMetrics();
		((Activity) context).getWindowManager().getDefaultDisplay()
				.getMetrics(metric);
		int width = metric.widthPixels; // ��Ļ��ȣ����أ�

		return width;
	}

	/**
	 * ��ȡ�ֱ��� �߶�
	 * 
	 * @param context
	 * @return
	 */
	public static int getDisplayHeight(Context context) {
		// int screenWidth = ((Activity)
		// context).getWindowManager().getDefaultDisplay().getWidth(); //
		// ��Ļ�?���أ��磺480px��
		// int screenHeight = ((Activity)
		// context).getWindowManager().getDefaultDisplay().getHeight();
		DisplayMetrics metric = new DisplayMetrics();
		((Activity) context).getWindowManager().getDefaultDisplay()
				.getMetrics(metric);

		int height = metric.heightPixels; // ��Ļ�߶ȣ����أ�
		return height;
	}

	/**
	 * ��ȡ�ֱ���
	 * 
	 * @param context
	 * @return int[0]��� int[1]�߶�
	 */
	public static int[] getDisplay(Context context) {
		// int screenWidth = ((Activity)
		// context).getWindowManager().getDefaultDisplay().getWidth(); //
		// ��Ļ�?���أ��磺480px��
		// int screenHeight = ((Activity)
		// context).getWindowManager().getDefaultDisplay().getHeight();
		DisplayMetrics metric = new DisplayMetrics();
		((Activity) context).getWindowManager().getDefaultDisplay()
				.getMetrics(metric);
		int width = metric.widthPixels;
		int height = metric.heightPixels; // ��Ļ�߶ȣ����أ�
		return new int[] { width, height };
	}

	// ʱ���ת��
	public String getStrTime(String time) {
		String re_StrTime = null;
		SimpleDateFormat sdf = null;
		if (time.equals("")) {
			return "";
		}
		sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		long loc_time = Long.valueOf(time);
		re_StrTime = sdf.format(new Date(loc_time * 1000L));
		return re_StrTime;
	}

}
