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
import de.hdodenhof.circleimageview.CircleImageView;
import ideatc.jinxiang.R;
import ideatc.jinxiang.base.activity.ToolBarBaseActivity;
import ideatc.jinxiang.base.manager.SnackBarManager;
import ideatc.jinxiang.bean.CustomerContactBean;

public class ContactDetailActivity extends ToolBarBaseActivity {

    @Bind(R.id.id_contact_headface)
    CircleImageView mContactHeadface;
    @Bind(R.id.id_contacts_detail_name)
    TextView mContactsDetailName;
    @Bind(R.id.id_contacts_detail_department)
    TextView mContactsDetailDepartment;
    @Bind(R.id.id_contacts_detail_position)
    TextView mContactsDetailPosition;
    @Bind(R.id.id_contact_phoneNumber)
    TextView mContactPhoneNumber;
    @Bind(R.id.id_contacts_detail_send_sms)
    ImageView mSendSmsImageView;
    @Bind(R.id.id_contacts_detail_call)
    ImageView mCallPhoneImageView;
    @Bind(R.id.id_contact_fax)
    TextView mContactFax;
    @Bind(R.id.id_contact_office_number)
    TextView mContactOfficeNumber;
    @Bind(R.id.id_contacts_call_office)
    ImageView mContactsCallOffice;
    @Bind(R.id.id_contacts_email)
    TextView mContactsEmail;


    private CustomerContactBean customerContactBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_detail);
        ButterKnife.bind(this);
        if (getIntent() != null)
            customerContactBean = getIntent().getParcelableExtra("customerContactBean");
        initToolbar();
        initData();
        initView();
    }


    @Override
    public void initData() {
        mContactsEmail.setText(customerContactBean.getEmail() + "");
        mContactFax.setText(customerContactBean.getFax() + "");
        mContactOfficeNumber.setText(customerContactBean.getOfficeTelephone() + "");
        mContactsDetailDepartment.setText(customerContactBean.getTitle() + "");
        mContactsDetailName.setText(customerContactBean.getName() + "");
        mContactsDetailPosition.setText(customerContactBean.getPost() + "");
        mContactPhoneNumber.setText(customerContactBean.getPhone() + "");
    }

    @Override
    public void initView() {
        mCallPhoneImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = mContactPhoneNumber.getText().toString();
                if (TextUtils.isEmpty(phone)) {
                    SnackBarManager.make(v, getString(R.string.Phone_not_exist), Snackbar.LENGTH_SHORT);
                    return;
                }
                call(phone);
            }
        });
        mContactsCallOffice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = mContactOfficeNumber.getText().toString();
                if (TextUtils.isEmpty(phone)) {
                    SnackBarManager.make(v, getString(R.string.Phone_not_exist), Snackbar.LENGTH_SHORT);
                    return;
                }
                call(phone);
            }
        });
        mSendSmsImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = mContactPhoneNumber.getText().toString();
                if (TextUtils.isEmpty(phone)) {
                    SnackBarManager.make(v, getString(R.string.Phone_not_exist), Snackbar.LENGTH_SHORT);
                    return;
                }
                sendSms(phone, "");
            }
        });
    }

    @Override
    public void initToolbar() {
        getToolbar().setNavigationIcon(R.drawable.back);
        getToolbar().setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                supportFinishAfterTransition();
            }
        });
    }


    public void call(String tel) {

        Intent it = new Intent();
        it.setAction(Intent.ACTION_CALL);
        it.setData(Uri.parse("tel:" + tel));
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
