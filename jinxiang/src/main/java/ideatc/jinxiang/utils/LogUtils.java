package ideatc.jinxiang.utils;

/**
 * Created by Ccb on 2015/8/3.
 */
public class LogUtils {
    private static boolean isDebug=true;
    public static void setLogMode(boolean isDebug)
    {
        LogUtils.isDebug=isDebug;

    }
    /**
     * 登记日志
     * @param obj
     */
    public static void log(Object obj)
    {
        if(isDebug)
            android.util.Log.e("Debug", (""+obj).toString());

    }
}
