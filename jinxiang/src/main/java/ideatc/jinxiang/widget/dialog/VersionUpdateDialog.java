package ideatc.jinxiang.widget.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

import ideatc.jinxiang.R;
import ideatc.jinxiang.base.manager.UpdateManager;

/**
 * Created by Administrator on 2015/9/6.
 */
public class VersionUpdateDialog extends DialogFragment {

    private static final String INSTANCE_TAG = "url";

    private String url;
    private UpdateManager updateManager;

    public static VersionUpdateDialog instance(String url) {
        VersionUpdateDialog fragment = new VersionUpdateDialog();
        Bundle args = new Bundle();
        args.putString(INSTANCE_TAG, url);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        updateManager = new UpdateManager(getActivity());
        if (getArguments() != null) {
            url = getArguments().getString(INSTANCE_TAG);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(getActivity().getString(R.string.sure_update));
        builder.setPositiveButton(getString(R.string.download), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
                updateManager.startUpdateInfo(url);
            }
        }).setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dismiss();
            }
        }).setCancelable(false);
        return builder.create();
    }
}
