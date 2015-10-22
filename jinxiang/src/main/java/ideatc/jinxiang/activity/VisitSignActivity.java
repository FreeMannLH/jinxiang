package ideatc.jinxiang.activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatSpinner;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import ideatc.jinxiang.R;
import ideatc.jinxiang.base.activity.ToolBarBaseActivity;
import ideatc.jinxiang.base.manager.SnackBarManager;
import ideatc.jinxiang.bean.crm.CustomerListBean;
import ideatc.jinxiang.utils.ParamsUtil;
import ideatc.jinxiang.utils.StringUtils;

public class VisitSignActivity extends ToolBarBaseActivity {

    @Bind(R.id.id_select_customer_spinner)
    AppCompatSpinner mAppCompatSpinner;
    @Bind(R.id.visit_theme)
    EditText mTheme;
    @Bind(R.id.visit_date)
    TextView mDate;
    @Bind(R.id.visit_address)
    TextView mAddress;
    @Bind(R.id.id_submit)
    AppCompatButton mSubmitBtn;

    private List<String> mCustomers = new ArrayList<>();
    private ArrayAdapter<String> mAdapter;
    private String selectName = "";
    private List<Map<String, Integer>> mapList = new ArrayList<>();
    private int selectPosition = 0;

    private String url = "";
    private LocationClient mLocationClient;
    public MyLocationListener mMyLocationListener;//定义监听类
    //经度
    private double mLongitude;
    //纬度
    private double mLatitude;


    private CustomerListBean mCustomerListBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visit_sign);
        ButterKnife.bind(this);
        url = URL_OA + "/addvisitpost";
        initToolbar();
        getMyAllCustomter();
        initData();
    }

    /**
     * 初始化定位，获取经纬度和位置信息
     */
    @Override
    public void initData() {
        mDate.setText(StringUtils.getDate());

        mLocationClient = new LocationClient(this.getApplicationContext());
        mMyLocationListener = new MyLocationListener();
        mLocationClient.registerLocationListener(mMyLocationListener);
        LocationClientOption option = new LocationClientOption();
//        option.setOpenGps(true);                                //打开gps
        option.setIgnoreKillProcess(true);
        option.setPriority(LocationClientOption.NetWorkFirst);  //设置网络优先
        option.setProdName("VisitSign");                      //设置产品线名称
        option.setScanSpan(10000);//定时定位，每隔10秒钟定位一次。
        option.setAddrType("all");
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);//设置高精度定位定位模式
        option.setCoorType("bd09ll");//设置百度经纬度坐标系格式
        option.setIsNeedAddress(true);//反编译获得具体位置，只有网络定位才可以
        option.setIsNeedLocationDescribe(true);
        mLocationClient.setLocOption(option);
        mLocationClient.start();
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
            mLongitude = location.getLongitude();
            mLatitude = location.getLatitude();
        }

    }
    @Override
    public void initView() {
        List<CustomerListBean.CustomerBean> list = mCustomerListBean.getList();
        for (int i = 0, size = list.size(); i < size; i++) {
            CustomerListBean.CustomerBean customerBean = list.get(i);
            mCustomers.add(customerBean.getName());
            Map<String, Integer> map = new HashMap<>();
            map.put(customerBean.getName(), customerBean.getId());
            mapList.add(map);
        }

        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, mCustomers);

        mAppCompatSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectName = mAdapter.getItem(position);
                selectPosition = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        mAppCompatSpinner.setAdapter(mAdapter);
        mSubmitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                visitSign();
            }
        });

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
     * 获取我所有的客户
     */
    public void getMyAllCustomter() {
        String url = URL_OA + "/getmyallcustomers";
        RequestParams params = ParamsUtil.getSignParams();
        params.put("uid", user.getId());
        httpClient().get(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                Log.e("getallmycustomer", getString(responseBody));
                mCustomerListBean = new Gson().fromJson(getString(responseBody), CustomerListBean.class);
                if (mCustomerListBean != null) {
                    initView();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("getallmycustomer", statusCode + "");
                Toast.makeText(VisitSignActivity.this, R.string.check_network_setting, Toast.LENGTH_SHORT).show();

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

    /**
     * 拜访签到
     */
    private void visitSign() {

        String theme = mTheme.getText().toString();
        String addr = mAddress.getText().toString();
        if (TextUtils.isEmpty(selectName)) {
            SnackBarManager.make(getCurrentFocus(), "请选择目标客户", Snackbar.LENGTH_INDEFINITE);
            return;
        }
        int targetId = mapList.get(selectPosition).get(selectName);

        if (TextUtils.isEmpty(theme)) {
            SnackBarManager.make(getCurrentFocus(), "请填写拜访主题", Snackbar.LENGTH_INDEFINITE);
            return;
        }
        RequestParams params = ParamsUtil.getSignParams();
        params.put("uid", user.getId());
        params.put("VisitTime", mDate.getText().toString());
        params.put("Title", theme);
        params.put("Addr", addr);
        params.put("CustId", targetId);
        params.put("Longitude", mLongitude);
        params.put("Latitude", mLatitude);
        params.put("Remark", "");//备注信息，非必须
        httpClient().get(url, params, new AsyncHttpResponseHandler() {


            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                Log.e("visit sign", getString(responseBody));
                try {
                    JSONObject obj = new JSONObject(getString(responseBody));
                    if (obj.getBoolean("success")) {
                        Toast.makeText(VisitSignActivity.this, R.string.sign_success, Toast.LENGTH_SHORT).show();
                        finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("visit sign", statusCode + "");
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


}
