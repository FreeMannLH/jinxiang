package ideatc.jinxiang.activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.bigkoo.pickerview.TimePopupWindow;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import ideatc.jinxiang.R;
import ideatc.jinxiang.base.activity.ToolBarBaseActivity;
import ideatc.jinxiang.base.manager.SnackBarManager;
import ideatc.jinxiang.utils.ParamsUtil;
import ideatc.jinxiang.utils.SPUtils;
import ideatc.jinxiang.utils.StringUtils;
import ideatc.jinxiang.widget.dialog.SearchDialog;

public class TravelRegActivity extends ToolBarBaseActivity {

    @Bind(R.id.maker)
    EditText witnessEditText;//见证人
    @Bind(R.id.phone)
    EditText phoneEditText;//联系电话
    @Bind(R.id.travel_date)
    TextView travelDate;//出差日期
    @Bind(R.id.start_time)
    TextView startTime;//出发时间  19:00
    @Bind(R.id.back_time)
    TextView backTime;//返回时间
    @Bind(R.id.reason)
    EditText reason;//出差事由
    @Bind(R.id.visit_address)
    TextView mAddress;
    @Bind(R.id.id_submit)
    AppCompatButton mSubmitBtn;

    private String url = "";
    private LocationClient mLocationClient;
    public MyLocationListener mMyLocationListener;//定义监听类

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_reg);
        ButterKnife.bind(this);
        url = URL_OA + "/AddTravelReg";
        initToolbar();
        initView();
        initData();
    }

    /**
     * 初始化定位，获取经纬度和位置信息
     */
    @Override
    public void initData() {
        mLocationClient = new LocationClient(this.getApplicationContext());
        mMyLocationListener = new MyLocationListener();
        mLocationClient.registerLocationListener(mMyLocationListener);
        LocationClientOption option = new LocationClientOption();
//        option.setOpenGps(true);                                //打开gps
        option.setIgnoreKillProcess(true);
        option.setPriority(LocationClientOption.NetWorkFirst);  //设置网络优先
        option.setProdName("travelReg");                      //设置产品线名称
        option.setScanSpan(10000);//定时定位，每隔10秒钟定位一次。
        option.setAddrType("all");
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);//设置高精度定位定位模式
        option.setCoorType("bd09ll");//设置百度经纬度坐标系格式
        option.setIsNeedAddress(true);//反编译获得具体位置，只有网络定位才可以
        option.setIsNeedLocationDescribe(true);
        mLocationClient.setLocOption(option);
        mLocationClient.start();
    }


    private TimePopupWindow pwTime;
    private TimePopupWindow pwTime2;

    @Override
    public void initView() {

        pwTime = new TimePopupWindow(TravelRegActivity.this, TimePopupWindow.Type.HOURS_MINS);
        pwTime2 = new TimePopupWindow(TravelRegActivity.this, TimePopupWindow.Type.HOURS_MINS);
        //时间选择后回调
        pwTime.setOnTimeSelectListener(new TimePopupWindow.OnTimeSelectListener() {

            @Override
            public void onTimeSelect(Date date) {
                startTime.setText(SearchDialog.getTimeByHours(date));
            }
        });
        pwTime2.setOnTimeSelectListener(new TimePopupWindow.OnTimeSelectListener() {

            @Override
            public void onTimeSelect(Date date) {
                backTime.setText(SearchDialog.getTimeByHours(date));
            }
        });
        mSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                travelReg();
            }
        });
        startTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pwTime.showAtLocation(startTime, Gravity.BOTTOM, 0, 0, new Date());
            }
        });
        backTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pwTime2.showAtLocation(backTime, Gravity.BOTTOM, 0, 0, new Date());
            }
        });
        travelDate.setText(StringUtils.getDate1());
    }

    @Override
    public void initToolbar() {
        getToolbar().setNavigationIcon(R.drawable.back);
        getToolbar().setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    /**
     * 出差登记
     */
    private void travelReg() {

        String address = mAddress.getText().toString();//地址
        String phone = phoneEditText.getText().toString();//联系电话
        String witness = witnessEditText.getText().toString();//见证人
        String date = travelDate.getText().toString();//出差日期
        String travel_reason = reason.getText().toString();//出差事由
        String start_time = startTime.getText().toString();//出发时间
        String back_time = backTime.getText().toString();//返回时间

        if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(witness) || TextUtils.isEmpty(date) || TextUtils.isEmpty(travel_reason) || TextUtils.isEmpty(start_time) || TextUtils.isEmpty(back_time)) {
            SnackBarManager.make(getCurrentFocus(), "内容填写不完整", Snackbar.LENGTH_INDEFINITE);
            return;
        }
        RequestParams params = ParamsUtil.getSignParams();
        params.put("employeeId", user.getId());//工号
        params.put("makerId", SPUtils.getInt("id", 0));//sysUserId
        params.put("content", travel_reason);//出差事由
        params.put("phone", phone);//联系电话
        params.put("witness", witness);//见证人
        params.put("startTime", start_time);//开始时间
        params.put("endTime", back_time);//结束时间
        params.put("travelDate", date);//出差日期
        params.put("Address", address);//地址
        httpClient().get(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.e("travel_reg", getString(responseBody));
                try {
                    JSONObject obj = new JSONObject(getString(responseBody));
                    if (obj.getBoolean("Status")) {
                        Toast.makeText(TravelRegActivity.this,obj.getString("Message"), Toast.LENGTH_SHORT).show();
                        finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("travel_reg", statusCode + "");

                Toast.makeText(TravelRegActivity.this, R.string.check_network_setting, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStart() {
                super.onStart();
                dialog.show();
            }

            @Override
            public void onFinish() {
                super.onFinish();
                dialog.dismiss();
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mLocationClient.isStarted()) mLocationClient.stop();
    }

    /**
     * 实现实位回调监听
     */
    public class MyLocationListener implements BDLocationListener {

        public void onReceiveLocation(BDLocation location) {
            if (location == null) {
                mAddress.setText(R.string.get_addr_retry);
                return;
            }
            String province = location.getProvince();//省
            String city = location.getCity();//城市
            String district = location.getDistrict();//县
            String street = location.getStreet();
//            String floor = location.getFloor();//室内定位,楼层
            mAddress.setText(province + city + district + street + ","+location.getLocationDescribe());
//            mAddress.setText(location.getAddrStr());
        }
    }
}
