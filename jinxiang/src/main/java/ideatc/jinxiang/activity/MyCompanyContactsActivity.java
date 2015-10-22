package ideatc.jinxiang.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import ideatc.jinxiang.R;
import ideatc.jinxiang.base.activity.ToolBarBaseActivity;
import ideatc.jinxiang.base.manager.SnackBarManager;
import ideatc.jinxiang.bean.ContactBean;

public class MyCompanyContactsActivity extends ToolBarBaseActivity {

    //    @Bind(R.id.id_contact_headface)
//    CircleImageView mHeadView;
    @Bind(R.id.id_contacts_detail_name)
    TextView mName;
    @Bind(R.id.id_contacts_detail_department)
    TextView mDepartment;
    @Bind(R.id.id_contacts_detail_position)
    TextView mPosition;
    @Bind(R.id.id_contact_phoneNumber)
    TextView mPhoneNumber;//手机号码
    @Bind(R.id.id_contacts_detail_send_sms)
    ImageView mSendSMSIv;
    @Bind(R.id.id_contacts_detail_call)
    ImageView mCallPhoneNumberIv;//拨打手机号码
    @Bind(R.id.id_contact_fax)
    TextView mNumber;//电话号码
    @Bind(R.id.id_contacts_email)
    TextView mEmail;//邮箱
    @Bind(R.id.id_call_phone_iv)
    ImageView mCallPhoneIv;//拨打电话号码

    private ContactBean contactBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_company_contacts);
        ButterKnife.bind(this);
        initToolbar();
        initData();
        initView();
    }

    @Override
    public void initData() {
        if (getIntent() != null) contactBean = getIntent().getParcelableExtra("contactBean");
        mPosition.setText(contactBean.getPost() + "");
        mDepartment.setText(contactBean.getDepartment() + "");
        mName.setText(contactBean.getName() + "");
        mPhoneNumber.setText(contactBean.getTelephone() + "");
        mNumber.setText(contactBean.getPhone() + "");
        mEmail.setText(contactBean.getEmail() + "");
    }

    @Override
    public void initView() {
        //发短信到手机
        mSendSMSIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tel = mPhoneNumber.getText().toString();
                if (TextUtils.isEmpty(tel)) {
                    SnackBarManager.make(v, "未找到号码", Snackbar.LENGTH_SHORT);
                    return;
                }
                sendSms(tel, "");
            }
        });
        //手机
        mCallPhoneNumberIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tel = mPhoneNumber.getText().toString();
                if (TextUtils.isEmpty(tel)) {
                    SnackBarManager.make(v, "未找到号码", Snackbar.LENGTH_SHORT);
                    return;
                }
                call(tel);
            }
        });
        //电话
        mCallPhoneIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tel = mNumber.getText().toString();
                if (TextUtils.isEmpty(tel)) {
                    SnackBarManager.make(v, "未找到号码", Snackbar.LENGTH_SHORT);
                    return;
                }
                call(tel);
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
        it.setAction(Intent.ACTION_CALL);//指定意图动作
        it.setData(Uri.parse("tel:" + tel));//指定电话号码
        startActivity(it);
    }

    public void sendSms(String tel, String content) {
        Intent it = new Intent();
        it.setAction(Intent.ACTION_SENDTO);
        it.setData(Uri.parse("smsto:" + tel));
        it.putExtra("sms_body", content);
        startActivity(it);
    }
}
