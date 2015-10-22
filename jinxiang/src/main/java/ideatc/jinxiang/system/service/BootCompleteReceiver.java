package ideatc.jinxiang.system.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * @author ccb
 *         create at 2015/10/8 17:02
 */
public class BootCompleteReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        context.startService(new Intent(context, CheckNewMsgService.class));
    }
}
