package ideatc.jinxiang.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lidroid.xutils.exception.DbException;

import butterknife.Bind;
import butterknife.ButterKnife;
import ideatc.jinxiang.R;
import ideatc.jinxiang.base.activity.ToolBarBaseActivity;
import ideatc.jinxiang.constans.Constants;
import ideatc.jinxiang.utils.SPUtils;

public class MyInfoActivity extends ToolBarBaseActivity {

    @Bind(R.id.id_employee)
    TextView mEmployeeId;
    @Bind(R.id.name)
    TextView name;
    @Bind(R.id.sex)
    TextView sex;
    @Bind(R.id.department)
    TextView department;
    @Bind(R.id.position)
    TextView position;
    @Bind(R.id.identity)
    TextView identity;
    @Bind(R.id.birthday)
    TextView birthday;
    @Bind(R.id.phoneNumber)
    TextView phoneNumber;
    @Bind(R.id.eMail)
    TextView eMail;
    @Bind(R.id.workDate)
    TextView workDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_info);
        ButterKnife.bind(this);
        initToolbar();
        initView();
        initData();
    }


    @Override
    public void initView() {
//        Log.e("myinfo", new Gson().toJson(user));
    }

    @Override
    public void initData() {
        try {
            if (getLoginStatus()) {
                mEmployeeId.setText(user.getId());
                name.setText(user.getName());
                sex.setText(Constants.getSex(user.getSex()));
                if (user.getId().equals("admin")) {
                    position.setText("管理员");
                    department.setText("");
                } else {
                    department.setText(SPUtils.getString("department", ""));
                    position.setText(SPUtils.getString("position", ""));
                }
                identity.setText(user.getIDCard());
                birthday.setText(user.getBirthStr());
                phoneNumber.setText(user.getPhone());
                eMail.setText(user.getEmail());
                workDate.setText(user.getTakeOfficeStr());
            }
        } catch (DbException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initToolbar() {
        getToolbar().setTitle(getString(R.string.myInfo));
        getToolbar().setNavigationIcon(R.drawable.back);
        getToolbar().setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    public String getDepartmentStr() {
        String departmentStr = "";
        for (int i = 0, size = user.getDepartment().size(); i < size; i++) {
            if (i == size - 1) {
                departmentStr += user.getDepartment().get(i);
            } else {
                departmentStr += user.getDepartment().get(i) + ",";
            }
        }
        return departmentStr;
    }

    public String getPositionStr() {
        String positionStr = "";
        for (int i = 0, size = user.getPost().size(); i < size; i++) {
            if (i == size - 1) {
                positionStr += user.getPost().get(i);
            } else {
                positionStr += user.getPost().get(i) + ",";
            }
        }
        return positionStr;
    }
}
