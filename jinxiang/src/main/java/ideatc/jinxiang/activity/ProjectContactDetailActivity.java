package ideatc.jinxiang.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import ideatc.jinxiang.R;
import ideatc.jinxiang.base.activity.ToolBarBaseActivity;
import ideatc.jinxiang.base.manager.SnackBarManager;
import ideatc.jinxiang.bean.crm.ProjectDetailBean;

public class ProjectContactDetailActivity extends ToolBarBaseActivity {

    @Bind(R.id.id_contact_headface)
    CircleImageView mContactHeadface;
    @Bind(R.id.id_contacts_detail_name)
    TextView mContactsDetailName;
    @Bind(R.id.id_contacts_call)
    TextView mContactsCall;
    @Bind(R.id.id_contacts_detail_position)
    TextView mContactsDetailPosition;
    @Bind(R.id.id_contact_phoneNumber)
    TextView mContactPhoneNumber;
    @Bind(R.id.id_contacts_detail_send_sms)
    ImageView mSendSMSIv;
    @Bind(R.id.id_contacts_detail_call)
    ImageView mCallIv;
    @Bind(R.id.id_contact_main)
    CheckBox mCheckBox;
    @Bind(R.id.id_contact_role_type)
    TextView mContactRoleType;
    @Bind(R.id.id_contact_support_level)
    TextView mContactSupportLevel;

    private ProjectDetailBean.ModelEntity.ContactsEntity contactsEntity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_contact_detail);
        ButterKnife.bind(this);
        initToolbar();
        if (getIntent() != null)
            contactsEntity = getIntent().getParcelableExtra("contactsEntity");
        initView();
        initData();
    }

    @Override
    public void initData() {
        if (contactsEntity.isIsMain()) mCheckBox.setChecked(true);
        else mCheckBox.setChecked(false);
        mContactPhoneNumber.setText(contactsEntity.getContactPhone() + "");
        mContactRoleType.setText(contactsEntity.getRoleStr() + "");
        mContactsCall.setText(contactsEntity.getContactTitle() + "");
        mContactSupportLevel.setText(contactsEntity.getSupportStr() + "");
        mContactsDetailName.setText(contactsEntity.getContactName() + "");
    }

    @Override
    public void initView() {
        mCallIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tel = mContactPhoneNumber.getText().toString();
                if (TextUtils.isEmpty(tel)) {
                    SnackBarManager.make(v, getString(R.string.phone_num_not_find), Snackbar.LENGTH_SHORT);
                    return;
                }
                call(tel);
            }
        });
        mSendSMSIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tel = mContactPhoneNumber.getText().toString();
                if (TextUtils.isEmpty(tel)) {
                    SnackBarManager.make(v, getString(R.string.phone_num_not_find), Snackbar.LENGTH_SHORT);
                    return;
                }
                sendSms(tel);
            }
        });
    }

    @Override
    public void initToolbar() {
        getToolbar().setNavigationIcon(R.drawable.back);
        getToolbar().setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void call(String tel) {

        Intent it = new Intent();
        it.setAction(Intent.ACTION_CALL);
        it.setData(Uri.parse("tel:" + tel));
        startActivity(it);
    }

    public void sendSms(String tel) {
        Intent it = new Intent();
        it.setAction(Intent.ACTION_SENDTO);
        it.setData(Uri.parse("smsto:" + tel));
        startActivity(it);
    }
}
