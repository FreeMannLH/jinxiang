package ideatc.jinxiang.fragment.matter_detail_acitivity;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePopupWindow;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import ideatc.jinxiang.R;
import ideatc.jinxiang.activity.AgreeActivity;
import ideatc.jinxiang.activity.BackActivity;
import ideatc.jinxiang.activity.FinishFlowActivity;
import ideatc.jinxiang.adapter.MyMultiChoiceAdapter;
import ideatc.jinxiang.base.activity.BaseActivity;
import ideatc.jinxiang.base.fragment.BaseFragment;
import ideatc.jinxiang.bean.ExpressDeliveryModel;
import ideatc.jinxiang.bean.ExpressMailModel;
import ideatc.jinxiang.bean.GoodSAssignBean;
import ideatc.jinxiang.bean.LeaveModel;
import ideatc.jinxiang.bean.OfficeRecipientsModel;
import ideatc.jinxiang.bean.PurchaseOrderModel;
import ideatc.jinxiang.bean.ScrappedModel;
import ideatc.jinxiang.bean.SealLendModel;
import ideatc.jinxiang.bean.SendCarModel;
import ideatc.jinxiang.bean.StampManagerModel;
import ideatc.jinxiang.bean.StockGoodsBean;
import ideatc.jinxiang.bean.WaitHandleListBean;
import ideatc.jinxiang.bean.crm.ProjectDetailBean;
import ideatc.jinxiang.utils.ParamsUtil;
import ideatc.jinxiang.utils.SPUtils;

public class BasicInfoFragment extends BaseFragment {


    private String orderStr;
    private String buttonStr;
    private WaitHandleListBean.RowsEntity rowsEntity;
    private String modelName;
    private int layout;
    private TimePopupWindow pwTime;

    public static String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View view = null;
        final Gson gson = new Gson();


        /**
         * 派车
         */
        if ("SendCarModel".equals(modelName)) {
            Log("isSendCarModel");
            SendCarModel sendCarModel = gson.fromJson(orderStr, SendCarModel.class);
            layout = R.layout.fragment_basic_info_send_car;
            view = inflater.inflate(layout, container, false);
            TextView mLendName = (TextView) view.findViewById(R.id.id_lend_name);
            TextView mTargetPlace = (TextView) view.findViewById(R.id.id_target_place);
            TextView mApplyDate = (TextView) view.findViewById(R.id.id_apply_date);
            TextView mUse = (TextView) view.findViewById(R.id.id_use);
            TextView mStartDate = (TextView) view.findViewById(R.id.id_start_date);
            TextView mBackDate = (TextView) view.findViewById(R.id.id_back_date);
            TextView mStartKm = (TextView) view.findViewById(R.id.id_start_km);
            TextView mBackKm = (TextView) view.findViewById(R.id.id_back_km);
            TextView mReason = (TextView) view.findViewById(R.id.id_reason);
            TextView mRemark = (TextView) view.findViewById(R.id.id_remark);

            mLendName.setText(sendCarModel.getEmployee() + "");
            mApplyDate.setText(sendCarModel.getApplyDateStr() + "");
            mBackDate.setText(sendCarModel.getBackDateStr() + "");
            mStartDate.setText(sendCarModel.getSendDateStr() + "");
            mTargetPlace.setText(sendCarModel.getEndAddress() + "");
            mStartKm.setText(sendCarModel.getBeforeKm() + "");
            mBackKm.setText(sendCarModel.getAfterKm() + "");
            mUse.setText(sendCarModel.getUsage() + "");
            mReason.setText(sendCarModel.getContent() + "");
            mRemark.setText(sendCarModel.getRemark() + "");

            AppCompatButton rb1 = (AppCompatButton) view.findViewById(R.id.id_rb1);
            AppCompatButton rb2 = (AppCompatButton) view.findViewById(R.id.id_rb2);
            try {
                JSONArray array = new JSONArray(buttonStr);
                if (array.getJSONObject(0) != null) {
                    rb1.setVisibility(View.VISIBLE);
                    rb1.setText(array.getJSONObject(0).getString("text"));
                } else {
                    rb1.setVisibility(View.GONE);
                    rb1.setOnClickListener(null);
                }
                if ((1 <= array.length() - 1) && array.getJSONObject(1) != null) {
                    rb2.setVisibility(View.VISIBLE);
                    rb2.setText(array.getJSONObject(1).getString("text"));
                } else {
                    rb2.setVisibility(View.GONE);
                    rb2.setOnClickListener(null);
                }
                if (rb1.getText().equals("同意") || rb1.getText().equals("核查无误")) {
                    rb1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent it = new Intent(getActivity(), AgreeActivity.class);
                            it.putExtra("waitHandleBean", rowsEntity);
                            startActivity(it);
                            getActivity().finish();
                        }
                    });
                    //onSendCarFeedback()
                } else if (rb1.getText().equals("反馈")) {
                    rb1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                            LayoutInflater inflater2 = getActivity().getLayoutInflater();
                            View view = inflater2.inflate(R.layout.fragment_dialog_car_back, null);
                            final TextView etBackDate = (TextView) view.findViewById(R.id.id_car_back_date);
                            final EditText etAfterKm = (EditText) view.findViewById(R.id.id_after_km);
                            pwTime = new TimePopupWindow(getActivity(), TimePopupWindow.Type.YEAR_MONTH_DAY);
                            //时间选择后回调
                            pwTime.setOnTimeSelectListener(new TimePopupWindow.OnTimeSelectListener() {

                                @Override
                                public void onTimeSelect(Date date) {
                                    etBackDate.setText(getTime(date));
                                }
                            });
                            //弹出时间选择器
                            etBackDate.setOnClickListener(new View.OnClickListener() {

                                @Override
                                public void onClick(View v) {
                                    pwTime.showAtLocation(etBackDate, Gravity.BOTTOM, 0, 0, new Date());
                                }
                            });
                            builder.setView(view).setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int id) {
                                    String backDate = etBackDate.getText().toString();
                                    String afterKm = etAfterKm.getText().toString();
                                    if (TextUtils.isEmpty(backDate) || TextUtils.isEmpty(afterKm)) {
                                        Toast.makeText(getActivity(), getString(R.string.content_not_right), Toast.LENGTH_SHORT).show();
                                        return;
                                    }
                                    setSendCarFeedBack(backDate, Integer.parseInt(afterKm));
                                }
                            }).setCancelable(true);
                            builder.create().show();
                        }
                    });

                }
                if (rb2.getText().equals("撤回") || rb2.getText().equals("不同意") || rb2.getText().equals("退回") || rb2.getText().equals("核查有误")) {
                    rb2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent it = new Intent(getActivity(), BackActivity.class);
                            it.putExtra("waitHandleBean", rowsEntity);
                            startActivity(it);
                            getActivity().finish();
                        }
                    });
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }


        /**
         * 快递寄件
         */
        else if ("ExpressMailModel".equals(modelName)) {
            Log("isExpressMailModel");
            ExpressMailModel expressMailModel = gson.fromJson(orderStr, ExpressMailModel.class);
            layout = R.layout.fragment_basic_info_express;
            view = inflater.inflate(layout, container, false);

            TextView mSendName = (TextView) view.findViewById(R.id.id_sender_name);
            TextView mCompany = (TextView) view.findViewById(R.id.id_express_company);
            TextView mOrderNum = (TextView) view.findViewById(R.id.id_express_order_num);
            TextView mSendDate = (TextView) view.findViewById(R.id.id_send_date);
            TextView mReceiptAddr = (TextView) view.findViewById(R.id.id_receipt_addr);
            TextView mContent = (TextView) view.findViewById(R.id.id_express_content);
            TextView mRemark = (TextView) view.findViewById(R.id.id_express_remark);

            mSendName.setText(expressMailModel.getEmployee() + "");
            mCompany.setText(expressMailModel.getDelivery() + "");
            mOrderNum.setText(expressMailModel.getExpressNo() + "");
            mSendDate.setText(expressMailModel.getExpressDateStr() + "");
            mReceiptAddr.setText(expressMailModel.getReciveAddress() + "");
            mRemark.setText(expressMailModel.getRemark() + "");
            mContent.setText(expressMailModel.getContent() + "");
            AppCompatButton rb1 = (AppCompatButton) view.findViewById(R.id.id_rb1);
            AppCompatButton rb2 = (AppCompatButton) view.findViewById(R.id.id_rb2);
            try {
                JSONArray array = new JSONArray(buttonStr);
                rb1.setText(array.getJSONObject(0).getString("text"));
                rb2.setText(array.getJSONObject(1).getString("text"));
                if (array.getJSONObject(0) != null) {
                    rb1.setVisibility(View.VISIBLE);
                    rb1.setText(array.getJSONObject(0).getString("text"));
                } else {
                    rb1.setVisibility(View.GONE);
                    rb1.setOnClickListener(null);
                }
                if ((1 <= array.length() - 1) && array.getJSONObject(1) != null) {
                    rb2.setVisibility(View.VISIBLE);
                    rb2.setText(array.getJSONObject(1).getString("text"));
                } else {
                    rb2.setVisibility(View.GONE);
                    rb2.setOnClickListener(null);
                }
                List<AppCompatButton> list = new ArrayList<>();

                if (rb1.getVisibility() == View.VISIBLE) list.add(rb1);
                if (rb2.getVisibility() == View.VISIBLE) list.add(rb2);
                for (AppCompatButton rb : list) {
                    if (rb.getText().equals("同意") || rb.getText().equals("寄出")) {
                        rb.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent it = new Intent(getActivity(), AgreeActivity.class);
                                it.putExtra("waitHandleBean", rowsEntity);
                                startActivity(it);
                                getActivity().finish();
                            }
                        });

                    } else if (rb.getText().equals("不同意") || rb.getText().equals("有异常退回")) {
                        rb.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent it = new Intent(getActivity(), BackActivity.class);
                                it.putExtra("waitHandleBean", rowsEntity);
                                startActivity(it);
                                getActivity().finish();
                            }
                        });
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        /**
         * 快递收件
         */
        else if ("ExpressDeliveryModel".equals(modelName)) {
            Log("ExpressDeliveryModel");
            ExpressDeliveryModel expressDeliveryModel = gson.fromJson(orderStr, ExpressDeliveryModel.class);
            layout = R.layout.fragment_basic_info_express_delivery;
            view = inflater.inflate(layout, container, false);

            TextView mDeliveryName = (TextView) view.findViewById(R.id.id_delivery_name);
            TextView mType = (TextView) view.findViewById(R.id.id_express_type);
            TextView mOrderNum = (TextView) view.findViewById(R.id.id_express_order_num);
            TextView mPayType = (TextView) view.findViewById(R.id.id_pay);
            TextView mCount = (TextView) view.findViewById(R.id.id_count);
            TextView mContent = (TextView) view.findViewById(R.id.id_receipt_content);
            TextView mRemark = (TextView) view.findViewById(R.id.id_express_remark);


            mType.setText(expressDeliveryModel.getExpressType() + "");
            mCount.setText(expressDeliveryModel.getMoney() + "");
            mDeliveryName.setText(expressDeliveryModel.getEmployee() + "");
            mOrderNum.setText(expressDeliveryModel.getOrderNo() + "");
            mPayType.setText(expressDeliveryModel.getDeliveryType() + "");
            mRemark.setText(expressDeliveryModel.getRemark() + "");
            mContent.setText(expressDeliveryModel.getContent() + "");
            AppCompatButton rb1 = (AppCompatButton) view.findViewById(R.id.id_rb1);
            AppCompatButton rb2 = (AppCompatButton) view.findViewById(R.id.id_rb2);
            try {
                JSONArray array = new JSONArray(buttonStr);
                rb1.setText(array.getJSONObject(0).getString("text"));
                rb2.setText(array.getJSONObject(1).getString("text"));
                if (array.getJSONObject(0) != null) {
                    rb1.setVisibility(View.VISIBLE);
                    rb1.setText(array.getJSONObject(0).getString("text"));
                } else {
                    rb1.setVisibility(View.GONE);
                    rb1.setOnClickListener(null);
                }
                if ((1 <= array.length() - 1) && array.getJSONObject(1) != null) {
                    rb2.setVisibility(View.VISIBLE);
                    rb2.setText(array.getJSONObject(1).getString("text"));
                } else {
                    rb2.setVisibility(View.GONE);
                    rb2.setOnClickListener(null);
                }
                List<AppCompatButton> list = new ArrayList<>();

                if (rb1.getVisibility() == View.VISIBLE) list.add(rb1);
                if (rb2.getVisibility() == View.VISIBLE) list.add(rb2);
                for (AppCompatButton rb : list) {
                    if (rb.getText().equals("同意")) {
                        rb.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent it = new Intent(getActivity(), AgreeActivity.class);
                                it.putExtra("waitHandleBean", rowsEntity);
                                startActivity(it);
                                getActivity().finish();
                            }
                        });

                    } else if (rb.getText().equals("不同意") || rb.getText().equals("撤回")) {
                        rb.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent it = new Intent(getActivity(), BackActivity.class);
                                it.putExtra("waitHandleBean", rowsEntity);
                                startActivity(it);
                                getActivity().finish();
                            }
                        });
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        /**
         * 合同
         */
        else if ("ContractModel".equals(modelName)) {
            Log("ContractModel");
            Log.e("orderStr", orderStr);
            final ProjectDetailBean.ModelEntity projectInfo = gson.fromJson(orderStr, ProjectDetailBean.ModelEntity.class);
            layout = R.layout.fragment_basic_info_contract;
            view = inflater.inflate(layout, container, false);
            TextView mProjectNum = (TextView) view.findViewById(R.id.id_project_num);
            TextView mProjectName = (TextView) view.findViewById(R.id.id_project_name);
            TextView mCustomerName = (TextView) view.findViewById(R.id.id_project_customer_name);
            TextView mProjectCost = (TextView) view.findViewById(R.id.id_project_cost);
            TextView mProjectPayWay = (TextView) view.findViewById(R.id.id_project_payway);
            TextView mProjectOrderNum = (TextView) view.findViewById(R.id.id_project_order_num);
            TextView mProjectStatus = (TextView) view.findViewById(R.id.id_project_status);
            TextView mProjectSignDate = (TextView) view.findViewById(R.id.id_project_sign_date);
            TextView mProjectEndDate = (TextView) view.findViewById(R.id.id_project_end_date);
            TextView mProjectRemark = (TextView) view.findViewById(R.id.id_project_remark);
            mProjectNum.setText(projectInfo.getId() + "");
            mProjectName.setText(projectInfo.getTitle() + "");
            mCustomerName.setText(projectInfo.getCustomerName() + "");
            mProjectCost.setText(projectInfo.getPrice() + "");
            mProjectPayWay.setText(projectInfo.getPayFlagStr() + "");
            mProjectOrderNum.setText(projectInfo.getContractNo() + "");
            mProjectStatus.setText(projectInfo.getStatusStr() + "");
            mProjectSignDate.setText(projectInfo.getSignDateStr() + "");
            mProjectEndDate.setText(projectInfo.getCompletionDateStr() + "");
            mProjectRemark.setText(projectInfo.getRemark() + "");
            /**
             *  onApprov()发送，
             *  onBack 退回，
             *  填写进度 writeSchedule()
             *  确认到款
             *  finishContract()
             */
            AppCompatButton rb1 = (AppCompatButton) view.findViewById(R.id.id_rb1);
            AppCompatButton rb2 = (AppCompatButton) view.findViewById(R.id.id_rb2);

            try {
                JSONArray array = new JSONArray(buttonStr);
                if (array.getJSONObject(0) != null) {
                    rb1.setVisibility(View.VISIBLE);
                    rb1.setText(array.getJSONObject(0).getString("text"));
                } else {
                    rb1.setVisibility(View.GONE);
                    rb1.setOnClickListener(null);
                }
                if ((1 <= array.length() - 1) && array.getJSONObject(1) != null) {
                    rb2.setVisibility(View.VISIBLE);
                    rb2.setText(array.getJSONObject(1).getString("text"));
                } else {
                    rb2.setVisibility(View.GONE);
                    rb2.setOnClickListener(null);
                }

                List<AppCompatButton> buttons = new ArrayList<>();
                if (rb1.getVisibility() == View.VISIBLE) buttons.add(rb1);
                if (rb2.getVisibility() == View.VISIBLE) buttons.add(rb2);
                for (AppCompatButton rb : buttons) {
                    if (rb.getText().equals("发送")) {
                        rb.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent it = new Intent(getActivity(), AgreeActivity.class);
                                it.putExtra("waitHandleBean", rowsEntity);
                                startActivity(it);
                                getActivity().finish();
                            }
                        });

                    } else if (rb.getText().equals("退回")) {
                        rb.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent it = new Intent(getActivity(), BackActivity.class);
                                it.putExtra("waitHandleBean", rowsEntity);
                                startActivity(it);
                                getActivity().finish();
                            }
                        });
                        //writeSchedule()
                    } else if (rb.getText().equals("填写进度")) {
                        rb.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
//                                ProjectRateDialog dialog  = ProjectRateDialog.newInstance(projectInfo.getId());
//                                dialog.show(getChildFragmentManager(),"确认框");
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                LayoutInflater inflater = getActivity().getLayoutInflater();
                                final View view = inflater.inflate(R.layout.fragment_dialog_project_rate, null);
                                final EditText etRate = (EditText) view.findViewById(R.id.id_rate);
                                final EditText etFinishDate = (EditText) view.findViewById(R.id.id_finish_date);
                                final EditText etRemark = (EditText) view.findViewById(R.id.id_remark);
                                builder.setView(view).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int id) {
                                        String finishDate = etFinishDate.getText().toString();
                                        String reMark = etRemark.getText().toString();
                                        String rate = etRate.getText().toString();
                                        if (TextUtils.isEmpty(finishDate) || TextUtils.isEmpty(reMark) || TextUtils.isEmpty(rate)) {
                                            Toast.makeText(getActivity(), getString(R.string.content_not_right), Toast.LENGTH_SHORT).show();
                                            return;
                                        }
                                        projectSchedulePost(projectInfo.getId(), reMark, finishDate, Integer.parseInt(rate));
                                    }
                                }).setCancelable(true);
                                builder.create().show();
                            }
                        });
                        //finishContract()
                    } else if (rb.getText().equals("确认到款")) {
                        rb.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                LayoutInflater inflater = getActivity().getLayoutInflater();
                                View view = inflater.inflate(R.layout.fragment_dialog_confirm, null);
                                final EditText etStartDate = (EditText) view.findViewById(R.id.id_start_date);

                                builder.setView(view).setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int id) {
                                        if (TextUtils.isEmpty(etStartDate.getText().toString())) {
                                            Toast.makeText(getActivity(), getString(R.string.content_not_right), Toast.LENGTH_SHORT).show();
                                            return;
                                        }
                                        finishContractFlow(etStartDate.getText().toString());

                                    }
                                }).setCancelable(true);
                                builder.create().show();
                            }
                        });
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        /**
         * 请假
         */
        else if ("LeaveModel".equals(modelName)) {
            Log("isLeaveModel");
            LeaveModel leaveModel = gson.fromJson(orderStr, LeaveModel.class);
            layout = R.layout.fragment_basic_info_leave;
            view = inflater.inflate(layout, container, false);
            TextView mLeaveName = (TextView) view.findViewById(R.id.id_leave_name);
            TextView mLeaveDate = (TextView) view.findViewById(R.id.id_leave_date);
            TextView mBackDate = (TextView) view.findViewById(R.id.id_back_date);
            TextView mDaysCount = (TextView) view.findViewById(R.id.id_count);
            TextView mLeaveReason = (TextView) view.findViewById(R.id.id_leave_reason);
            mLeaveName.setText(leaveModel.getEmployee() + "");
            mLeaveDate.setText(leaveModel.getStartDateStr() + " " + leaveModel.getStartTime());
            mBackDate.setText(leaveModel.getEndDateStr() + " " + leaveModel.getEndTime());
            mDaysCount.setText(leaveModel.getTotalDay() + "");
            mLeaveReason.setText(leaveModel.getContent() + "");
            AppCompatButton rb1 = (AppCompatButton) view.findViewById(R.id.id_rb1);
            AppCompatButton rb2 = (AppCompatButton) view.findViewById(R.id.id_rb2);
            AppCompatButton rb3 = (AppCompatButton) view.findViewById(R.id.id_rb3);

            try {
                JSONArray array = new JSONArray(buttonStr);
                rb1.setText(array.getJSONObject(0).getString("text"));
                rb2.setText(array.getJSONObject(1).getString("text"));

                List<AppCompatButton> list = new ArrayList<>();
                if (array.getJSONObject(0) != null) {
                    list.add(rb1);
                    rb1.setVisibility(View.VISIBLE);
                    rb3.setText(array.getJSONObject(0).getString("text"));
                } else {
                    rb1.setOnClickListener(null);
                    rb1.setVisibility(View.GONE);
                }
                if ((1 <= array.length() - 1) && array.getJSONObject(1) != null) {
                    list.add(rb2);
                    rb2.setVisibility(View.VISIBLE);
                    rb2.setText(array.getJSONObject(1).getString("text"));
                } else {
                    rb2.setOnClickListener(null);
                    rb2.setVisibility(View.GONE);
                }
                if ((2 <= array.length() - 1) && array.getJSONObject(2) != null) {
                    list.add(rb3);
                    rb3.setVisibility(View.VISIBLE);
                    rb3.setText(array.getJSONObject(2).getString("text"));
                } else {
                    rb3.setOnClickListener(null);
                    rb3.setVisibility(View.GONE);
                }
                for (AppCompatButton rb : list) {

                    if (rb.getText().equals("同意(发送至总经理)") || rb.getText().equals("同意")) {
                        rb.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent it = new Intent(getActivity(), AgreeActivity.class);
                                it.putExtra("waitHandleBean", rowsEntity);
                                startActivity(it);
                                getActivity().finish();
                            }
                        });

                    } else if (rb.getText().equals("不同意") || rb.getText().equals("撤回")) {
                        rb.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent it = new Intent(getActivity(), BackActivity.class);
                                it.putExtra("waitHandleBean", rowsEntity);
                                startActivity(it);
                                getActivity().finish();
                            }
                        });
                    } else if (rb.getText().equals("同意(结束)")) {
                        rb.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent it = new Intent(getActivity(), FinishFlowActivity.class);
                                it.putExtra("waitHandleBean", rowsEntity);
                                startActivity(it);
                                getActivity().finish();
                            }
                        });
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        /**
         * 物品领用
         */
        else if ("OfficeRecipientsModel".equals(modelName)) {
            Log("isOfficeRecipientsModel");
            OfficeRecipientsModel officeRecipientsModel = gson.fromJson(orderStr, OfficeRecipientsModel.class);
            layout = R.layout.fragment_basic_info_use_office;
            view = inflater.inflate(layout, container, false);

            TextView name = (TextView) view.findViewById(R.id.id_name);
            TextView department = (TextView) view.findViewById(R.id.id_department);
            TextView goods1 = (TextView) view.findViewById(R.id.id_goods1);
            TextView goods2 = (TextView) view.findViewById(R.id.id_goods2);
            TextView goods3 = (TextView) view.findViewById(R.id.id_goods3);
            TextView goods4 = (TextView) view.findViewById(R.id.id_goods4);
            TextView goods5 = (TextView) view.findViewById(R.id.id_goods5);
            TextView goods6 = (TextView) view.findViewById(R.id.id_goods6);
            TextView goods7 = (TextView) view.findViewById(R.id.id_goods7);
            TextView goods8 = (TextView) view.findViewById(R.id.id_goods8);

            TextView count1 = (TextView) view.findViewById(R.id.id_count1);
            TextView count2 = (TextView) view.findViewById(R.id.id_count2);
            TextView count3 = (TextView) view.findViewById(R.id.id_count3);
            TextView count4 = (TextView) view.findViewById(R.id.id_count4);
            TextView count5 = (TextView) view.findViewById(R.id.id_count5);
            TextView count6 = (TextView) view.findViewById(R.id.id_count6);
            TextView count7 = (TextView) view.findViewById(R.id.id_count7);
            TextView count8 = (TextView) view.findViewById(R.id.id_count8);

            TextView unit1 = (TextView) view.findViewById(R.id.id_unit1);
            TextView unit2 = (TextView) view.findViewById(R.id.id_unit2);
            TextView unit3 = (TextView) view.findViewById(R.id.id_unit3);
            TextView unit4 = (TextView) view.findViewById(R.id.id_unit4);
            TextView unit5 = (TextView) view.findViewById(R.id.id_unit5);
            TextView unit6 = (TextView) view.findViewById(R.id.id_unit6);
            TextView unit7 = (TextView) view.findViewById(R.id.id_unit7);
            TextView unit8 = (TextView) view.findViewById(R.id.id_unit8);

            EditText fact1 = (EditText) view.findViewById(R.id.id_fact1);
            EditText fact2 = (EditText) view.findViewById(R.id.id_fact2);
            EditText fact3 = (EditText) view.findViewById(R.id.id_fact3);
            EditText fact4 = (EditText) view.findViewById(R.id.id_fact4);
            EditText fact5 = (EditText) view.findViewById(R.id.id_fact5);
            EditText fact6 = (EditText) view.findViewById(R.id.id_fact6);
            EditText fact7 = (EditText) view.findViewById(R.id.id_fact7);
            EditText fact8 = (EditText) view.findViewById(R.id.id_fact8);

            TextView[] arr1 = {goods1, goods2, goods3, goods4, goods5, goods6, goods7, goods8};
            TextView[] arr2 = {count1, count2, count3, count4, count5, count6, count7, count8};
            TextView[] arr3 = {unit1, unit2, unit3, unit4, unit5, unit6, unit7, unit8};
            EditText[] arr4 = {fact1, fact2, fact3, fact4, fact5, fact6, fact7, fact8};
            List<TextView> list1 = Arrays.asList(arr1);
            List<TextView> list2 = Arrays.asList(arr2);
            List<TextView> list3 = Arrays.asList(arr3);
            final List<EditText> list4 = Arrays.asList(arr4);

            final List<OfficeRecipientsModel.OfficeRecipientsItemModel> list = officeRecipientsModel.getList();


            for (int i = 0, size = list.size(); i < size; i++) {
                if (i >= 8) break;

                OfficeRecipientsModel.OfficeRecipientsItemModel item = list.get(i);
                list1.get(i).setText(item.getGoods());
                list2.get(i).setText(item.getQuantity()+"");
                list3.get(i).setText(item.getUnit());
                list4.get(i).setText(item.getQuantity()+"");
//                GoodSAssignBean bean = new GoodSAssignBean();
//                bean.setValue(item.getQuantity());
//                bean.setKey(item.getId());
//                mapList.add(bean);
            }
            name.setText(officeRecipientsModel.getEmployee());
            department.setText(officeRecipientsModel.getDepartment());

            AppCompatButton rb1 = (AppCompatButton) view.findViewById(R.id.id_rb1);
            AppCompatButton rb2 = (AppCompatButton) view.findViewById(R.id.id_rb2);

            try {
                JSONArray array = new JSONArray(buttonStr);
                if (array.getJSONObject(0) != null) {
                    rb1.setVisibility(View.VISIBLE);
                    rb1.setText(array.getJSONObject(0).getString("text"));
                } else {
                    rb1.setVisibility(View.GONE);
                    rb1.setOnClickListener(null);
                }
                if ((1 <= array.length() - 1) && array.getJSONObject(1) != null) {
                    rb2.setVisibility(View.VISIBLE);
                    rb2.setText(array.getJSONObject(1).getString("text"));
                } else {
                    rb2.setVisibility(View.GONE);
                    rb2.setOnClickListener(null);
                }

                List<AppCompatButton> buttons = new ArrayList<>();
                if (rb1.getVisibility() == View.VISIBLE) buttons.add(rb1);
                if (rb2.getVisibility() == View.VISIBLE) buttons.add(rb2);
                for (AppCompatButton rb : buttons) {
                    if (rb.getText().equals("同意")) {
                        rb.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent it = new Intent(getActivity(), AgreeActivity.class);
                                it.putExtra("waitHandleBean", rowsEntity);
                                startActivity(it);
                                getActivity().finish();
                            }
                        });

                    } else if (rb.getText().equals("不同意")) {
                        rb.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent it = new Intent(getActivity(), BackActivity.class);
                                it.putExtra("waitHandleBean", rowsEntity);
                                startActivity(it);
                                getActivity().finish();
                            }
                        });
                        //onGoodsAssign()
                    } else if (rb.getText().equals("发放")) {
                        rb.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                List<GoodSAssignBean> mapList = new ArrayList<>();
                                for (int i = 0, size = list.size(); i < size; i++) {
                                    if (i >= 8) break;
                                    OfficeRecipientsModel.OfficeRecipientsItemModel item = list.get(i);
                                    GoodSAssignBean bean = new GoodSAssignBean();
                                    bean.setValue(Integer.parseInt(list4.get(i).getText().toString()));
                                    bean.setKey(item.getId());
                                    mapList.add(bean);
                                }
                                onGoodsAssign(gson.toJson(mapList));
                            }
                        });
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        /**
         * 物品采购
         */
        else if ("PurchaseOrderModel".equals(modelName)) {
            Log("isPurchaseOrderModel");
            PurchaseOrderModel purchaseOrderModel = gson.fromJson(orderStr, PurchaseOrderModel.class);
            layout = R.layout.fragment_basic_info_buy_things;
            view = inflater.inflate(layout, container, false);

            TextView name = (TextView) view.findViewById(R.id.id_name);
            TextView reMark = (TextView) view.findViewById(R.id.id_remark);
            TextView goods1 = (TextView) view.findViewById(R.id.id_goods1);
            TextView goods2 = (TextView) view.findViewById(R.id.id_goods2);
            TextView goods3 = (TextView) view.findViewById(R.id.id_goods3);
            TextView goods4 = (TextView) view.findViewById(R.id.id_goods4);
            TextView goods5 = (TextView) view.findViewById(R.id.id_goods5);
            TextView goods6 = (TextView) view.findViewById(R.id.id_goods6);
            TextView goods7 = (TextView) view.findViewById(R.id.id_goods7);
            TextView goods8 = (TextView) view.findViewById(R.id.id_goods8);
            TextView count1 = (TextView) view.findViewById(R.id.id_count1);
            TextView count2 = (TextView) view.findViewById(R.id.id_count2);
            TextView count3 = (TextView) view.findViewById(R.id.id_count3);
            TextView count4 = (TextView) view.findViewById(R.id.id_count4);
            TextView count5 = (TextView) view.findViewById(R.id.id_count5);
            TextView count6 = (TextView) view.findViewById(R.id.id_count6);
            TextView count7 = (TextView) view.findViewById(R.id.id_count7);
            TextView count8 = (TextView) view.findViewById(R.id.id_count8);
            TextView unit1 = (TextView) view.findViewById(R.id.id_unit1);
            TextView unit2 = (TextView) view.findViewById(R.id.id_unit2);
            TextView unit3 = (TextView) view.findViewById(R.id.id_unit3);
            TextView unit4 = (TextView) view.findViewById(R.id.id_unit4);
            TextView unit5 = (TextView) view.findViewById(R.id.id_unit5);
            TextView unit6 = (TextView) view.findViewById(R.id.id_unit6);
            TextView unit7 = (TextView) view.findViewById(R.id.id_unit7);
            TextView unit8 = (TextView) view.findViewById(R.id.id_unit8);
            TextView[] arr1 = {goods1, goods2, goods3, goods4, goods5, goods6, goods7, goods8};
            TextView[] arr2 = {count1, count2, count3, count4, count5, count6, count7, count8};
            TextView[] arr3 = {unit1, unit2, unit3, unit4, unit5, unit6, unit7, unit8};
            List<TextView> list1 = Arrays.asList(arr1);
            List<TextView> list2 = Arrays.asList(arr2);
            List<TextView> list3 = Arrays.asList(arr3);
            List<PurchaseOrderModel.PurchaseOrderItemModel> list = purchaseOrderModel.getList();
            //物品采购入库确认集合
            final List<StockGoodsBean> mapList = new ArrayList<>();

            for (int i = 0, size = list.size(); i < size; i++) {
                if (i >= 8) break;
                PurchaseOrderModel.PurchaseOrderItemModel item = list.get(i);
                list1.get(i).setText(item.getGoodsName() + "");
                list2.get(i).setText(item.getQuantity() + "");
                list3.get(i).setText(item.getGoodsUnit() + "");
                StockGoodsBean bean = new StockGoodsBean();
                bean.setKey(item.getId());
                bean.setValue(item.getQuantity());
                mapList.add(bean);

            }
            name.setText(purchaseOrderModel.getEmployee() + "");
            reMark.setText(purchaseOrderModel.getRemark() + "");

            AppCompatButton rb1 = (AppCompatButton) view.findViewById(R.id.id_rb1);
            AppCompatButton rb2 = (AppCompatButton) view.findViewById(R.id.id_rb2);
            AppCompatButton rb3 = (AppCompatButton) view.findViewById(R.id.id_rb3);
            try {
                JSONArray array = new JSONArray(buttonStr);
                if (array.getJSONObject(0) != null) {
                    rb1.setVisibility(View.VISIBLE);
                    rb1.setText(array.getJSONObject(0).getString("text"));
                } else {
                    rb1.setVisibility(View.GONE);
                    rb1.setOnClickListener(null);
                }
                if ((1 <= array.length() - 1) && array.getJSONObject(1) != null) {
                    rb2.setVisibility(View.VISIBLE);
                    rb2.setText(array.getJSONObject(1).getString("text"));
                } else {
                    rb2.setVisibility(View.GONE);
                    rb2.setOnClickListener(null);
                }
                if ((2 <= array.length() - 1) && array.getJSONObject(2) != null) {
                    rb3.setVisibility(View.VISIBLE);
                    rb3.setText(array.getJSONObject(2).getString("text"));
                } else {
                    rb3.setVisibility(View.GONE);
                    rb3.setOnClickListener(null);
                }
//
                List<AppCompatButton> buttons = new ArrayList<>();
                if (rb1.getVisibility() == View.VISIBLE) buttons.add(rb1);
                if (rb2.getVisibility() == View.VISIBLE) buttons.add(rb2);
                if (rb3.getVisibility() == View.VISIBLE) buttons.add(rb3);
                Log.e("button size", buttons.size() + "");
                for (AppCompatButton rb : buttons) {
                    Log.e("rbtext", rb.getText().toString());
                    if (rb.getText().equals("同意")) {
                        rb.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent it = new Intent(getActivity(), AgreeActivity.class);
                                it.putExtra("waitHandleBean", rowsEntity);
                                startActivity(it);
                                getActivity().finish();
                            }
                        });

                    } else if (rb.getText().equals("不同意")) {
                        rb.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent it = new Intent(getActivity(), BackActivity.class);
                                it.putExtra("waitHandleBean", rowsEntity);
                                startActivity(it);
                                getActivity().finish();
                            }
                        });
                        //onStockGoods()
                    } else if (rb.getText().equals("确认")) {
                        rb.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
//                                Toast.makeText(getActivity(), "采购入库点击", Toast.LENGTH_SHORT).show();
                                stockGoods(gson.toJson(mapList));
                            }
                        });
                    } else if (rb.getText().equals("结束")) {
                        rb.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent it = new Intent(getActivity(), FinishFlowActivity.class);
                                it.putExtra("waitHandleBean", rowsEntity);
                                startActivity(it);
                                getActivity().finish();
                            }
                        });
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        /**
         * 报废...
         */
        else if ("ScrappedModel".equals(modelName)) {
            Log("isScrappedModel");
            ScrappedModel scrappedModel = gson.fromJson(orderStr, ScrappedModel.class);
            layout = R.layout.fragment_basic_info_send_things_scrapped;
            view = inflater.inflate(layout, container, false);
            TextView name = (TextView) view.findViewById(R.id.id_name);
            TextView reMark = (TextView) view.findViewById(R.id.id_remark);
            TextView goods1 = (TextView) view.findViewById(R.id.id_goods1);
            TextView goods2 = (TextView) view.findViewById(R.id.id_goods2);
            TextView goods3 = (TextView) view.findViewById(R.id.id_goods3);
            TextView goods4 = (TextView) view.findViewById(R.id.id_goods4);
            TextView goods5 = (TextView) view.findViewById(R.id.id_goods5);
            TextView goods6 = (TextView) view.findViewById(R.id.id_goods6);
            TextView goods7 = (TextView) view.findViewById(R.id.id_goods7);
            TextView goods8 = (TextView) view.findViewById(R.id.id_goods8);
            TextView count1 = (TextView) view.findViewById(R.id.id_count1);
            TextView count2 = (TextView) view.findViewById(R.id.id_count2);
            TextView count3 = (TextView) view.findViewById(R.id.id_count3);
            TextView count4 = (TextView) view.findViewById(R.id.id_count4);
            TextView count5 = (TextView) view.findViewById(R.id.id_count5);
            TextView count6 = (TextView) view.findViewById(R.id.id_count6);
            TextView count7 = (TextView) view.findViewById(R.id.id_count7);
            TextView count8 = (TextView) view.findViewById(R.id.id_count8);
            TextView unit1 = (TextView) view.findViewById(R.id.id_unit1);
            TextView unit2 = (TextView) view.findViewById(R.id.id_unit2);
            TextView unit3 = (TextView) view.findViewById(R.id.id_unit3);
            TextView unit4 = (TextView) view.findViewById(R.id.id_unit4);
            TextView unit5 = (TextView) view.findViewById(R.id.id_unit5);
            TextView unit6 = (TextView) view.findViewById(R.id.id_unit6);
            TextView unit7 = (TextView) view.findViewById(R.id.id_unit7);
            TextView unit8 = (TextView) view.findViewById(R.id.id_unit8);
            TextView[] arr1 = {goods1, goods2, goods3, goods4, goods5, goods6, goods7, goods8};
            TextView[] arr2 = {count1, count2, count3, count4, count5, count6, count7, count8};
            TextView[] arr3 = {unit1, unit2, unit3, unit4, unit5, unit6, unit7, unit8};
            List<TextView> list1 = Arrays.asList(arr1);
            List<TextView> list2 = Arrays.asList(arr2);
            List<TextView> list3 = Arrays.asList(arr3);
            List<ScrappedModel.ScrappedItemModel> list = scrappedModel.getList();
            for (int i = 0, size = list.size(); i < size; i++) {
                if (i >= 8) break;

                ScrappedModel.ScrappedItemModel item = list.get(i);
                list1.get(i).setText(item.getGoods() + "");
                list2.get(i).setText(item.getQuantity() + "");
                list3.get(i).setText(item.getUnit() + "");
            }
            name.setText(scrappedModel.getEmployee() + "");
            reMark.setText(scrappedModel.getContent());

            AppCompatButton rb1 = (AppCompatButton) view.findViewById(R.id.id_rb1);
            AppCompatButton rb2 = (AppCompatButton) view.findViewById(R.id.id_rb2);

            try {
                JSONArray array = new JSONArray(buttonStr);
                if (array.getJSONObject(0) != null) {
                    rb1.setVisibility(View.VISIBLE);
                    rb1.setText(array.getJSONObject(0).getString("text"));
                } else {
                    rb1.setVisibility(View.GONE);
                    rb1.setOnClickListener(null);
                }
                if ((1 <= array.length() - 1) && array.getJSONObject(1) != null) {
                    rb2.setVisibility(View.VISIBLE);
                    rb2.setText(array.getJSONObject(1).getString("text"));
                } else {
                    rb2.setVisibility(View.GONE);
                    rb2.setOnClickListener(null);
                }

                List<AppCompatButton> buttons = new ArrayList<>();
                if (rb1.getVisibility() == View.VISIBLE) buttons.add(rb1);
                if (rb2.getVisibility() == View.VISIBLE) buttons.add(rb2);
                for (AppCompatButton rb : buttons) {
                    if (rb.getText().equals("同意")) {
                        rb.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent it = new Intent(getActivity(), AgreeActivity.class);
                                it.putExtra("waitHandleBean", rowsEntity);
                                startActivity(it);
                                getActivity().finish();
                            }
                        });

                    } else if (rb.getText().equals("不同意")) {
                        rb.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent it = new Intent(getActivity(), BackActivity.class);
                                it.putExtra("waitHandleBean", rowsEntity);
                                startActivity(it);
                                getActivity().finish();
                            }
                        });
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        /**
         * 公章借出
         */
        else if ("SealLendModel".equals(modelName)) {
            Log("isSealLendModel");
            SealLendModel sealLendModel = gson.fromJson(orderStr, SealLendModel.class);
            layout = R.layout.fragment_basic_info_send_seal_lend;
            view = inflater.inflate(layout, container, false);
            TextView name = (TextView) view.findViewById(R.id.id_lend_name);
            TextView mPredictDate = (TextView) view.findViewById(R.id.id_predict_date);
            TextView mContent = (TextView) view.findViewById(R.id.id_content);
            TextView mFactDate = (TextView) view.findViewById(R.id.id_fact_date);
            TextView mBackDate = (TextView) view.findViewById(R.id.id_back_date);
            TextView mBackTime = (TextView) view.findViewById(R.id.id_back_time);
            TextView mGetDate = (TextView) view.findViewById(R.id.id_get_date);
            TextView mRemark = (TextView) view.findViewById(R.id.id_remark);

            name.setText(sealLendModel.getEmployee() + "");
            mPredictDate.setText(sealLendModel.getForecastLendDateStr() + "");
            mContent.setText(sealLendModel.getContent() + "");
            mFactDate.setText(sealLendModel.getActualLenDateStr() + "");
            mBackDate.setText(sealLendModel.getBackDateStr() + "");
            mBackTime.setText(sealLendModel.getActualBackDateStr() + "");
            mGetDate.setText(sealLendModel.getActualPickDateStr() + "");
            mRemark.setText(sealLendModel.getMakeDateStr() + "");
            AppCompatButton rb1 = (AppCompatButton) view.findViewById(R.id.id_rb1);
            AppCompatButton rb2 = (AppCompatButton) view.findViewById(R.id.id_rb2);
            try {
                JSONArray array = new JSONArray(buttonStr);
                if (array.getJSONObject(0) != null) {
                    rb1.setVisibility(View.VISIBLE);
                    rb1.setText(array.getJSONObject(0).getString("text"));
                } else {
                    rb1.setVisibility(View.GONE);
                    rb1.setOnClickListener(null);
                }
                if ((1 <= array.length() - 1) && array.getJSONObject(1) != null) {
                    rb2.setVisibility(View.VISIBLE);
                    rb2.setText(array.getJSONObject(1).getString("text"));
                } else {
                    rb2.setVisibility(View.GONE);
                    rb2.setOnClickListener(null);
                }
                List<AppCompatButton> buttons = new ArrayList<>();
                if (rb1.getVisibility() == View.VISIBLE) buttons.add(rb1);
                if (rb2.getVisibility() == View.VISIBLE) buttons.add(rb2);
                for (AppCompatButton rb : buttons) {
                    if (rb.getText().equals("同意")) {
                        rb.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent it = new Intent(getActivity(), AgreeActivity.class);
                                it.putExtra("waitHandleBean", rowsEntity);
                                startActivity(it);
                                getActivity().finish();
                            }
                        });

                    } else if (rb.getText().equals("回退") || rb.getText().equals("不同意") || rb.getText().equals("撤回")) {
                        rb.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent it = new Intent(getActivity(), BackActivity.class);
                                it.putExtra("waitHandleBean", rowsEntity);
                                startActivity(it);
                                getActivity().finish();
                            }
                        });
                        //setActualPickDate()
                    } else if (rb.getText().equals("确认借出")) {
                        rb.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setSealActualPickDate();
                            }
                        });
                        //setBackDate()设置公章归还时间
                    } else if (rb.getText().equals("还章")) {
                        rb.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setSealBackDate();
                            }
                        });

                        //setActualBackDate()
                    } else if (rb.getText().equals("确认还章")) {
                        rb.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setSealActualBackDate();
                            }
                        });
                    }
                    //setActualLendDate()
                    else if (rb.getText().equals("确认")) {
                        rb.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                setSealActualLendDate();
                            }
                        });
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }


        /**
         * 盖章
         */
        else if ("StampManagerModel".equals(modelName)) {
            Log("isStampManagerModel");


            StampManagerModel stampManagerModel = gson.fromJson(orderStr, StampManagerModel.class);
            layout = R.layout.fragment_basic_info_send_stamp;
            view = inflater.inflate(layout, container, false);
            TextView name = (TextView) view.findViewById(R.id.id_use_name);
            TextView type = (TextView) view.findViewById(R.id.id_type);
            TextView date = (TextView) view.findViewById(R.id.id_date);
            TextView num = (TextView) view.findViewById(R.id.id_order_num);
            TextView reMark = (TextView) view.findViewById(R.id.id_remark);
            TextView order = (TextView) view.findViewById(R.id.id_order);
            name.setText(stampManagerModel.getEmployee() + "");
            type.setText(stampManagerModel.getStampType() + "");
            date.setText(stampManagerModel.getStampDateStr() + "");
            num.setText(stampManagerModel.getNumber() + "");
            reMark.setText(stampManagerModel.getContent() + "");
            order.setText(stampManagerModel.getOrderNo() + "");
            AppCompatButton rb1 = (AppCompatButton) view.findViewById(R.id.id_rb1);
            AppCompatButton rb2 = (AppCompatButton) view.findViewById(R.id.id_rb2);

            try {
                JSONArray array = new JSONArray(buttonStr);
                if (array.getJSONObject(0) != null) {
                    rb1.setVisibility(View.VISIBLE);
                    rb1.setText(array.getJSONObject(0).getString("text"));
                } else {
                    rb1.setVisibility(View.GONE);
                    rb1.setOnClickListener(null);
                }
                if ((1 <= array.length() - 1) && array.getJSONObject(1) != null) {
                    rb2.setVisibility(View.VISIBLE);
                    rb2.setText(array.getJSONObject(1).getString("text"));
                } else {
                    rb2.setVisibility(View.GONE);
                    rb2.setOnClickListener(null);
                }
                List<AppCompatButton> buttons = new ArrayList<>();
                if (rb1.getVisibility() == View.VISIBLE) buttons.add(rb1);
                if (rb2.getVisibility() == View.VISIBLE) buttons.add(rb2);
                for (AppCompatButton rb : buttons) {
                    if (rb.getText().equals("同意") || rb.getText().equals("确认")) {
                        rb.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent it = new Intent(getActivity(), AgreeActivity.class);
                                it.putExtra("waitHandleBean", rowsEntity);
                                startActivity(it);
                                getActivity().finish();
                            }
                        });

                    } else if (rb.getText().equals("不同意")) {
                        rb.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent it = new Intent(getActivity(), BackActivity.class);
                                it.putExtra("waitHandleBean", rowsEntity);
                                startActivity(it);
                                getActivity().finish();
                            }
                        });
                        //onRecoverStamp()
                    } else if (rb.getText().equals("回收合同")) {
                        rb.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                getStampManagerList();

                            }
                        });
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return view;
    }


    /**
     * @param modelName
     * @param orderStr
     * @return
     */
    public static BasicInfoFragment newInstance(String modelName, String orderStr, String buttonStr, WaitHandleListBean.RowsEntity rowsEntity) {
        BasicInfoFragment fragment = new BasicInfoFragment();
        Bundle args = new Bundle();
        args.putString("orderStr", orderStr);
        args.putString("buttonStr", buttonStr);
        args.putString("modelName", modelName);
        args.putParcelable("rowsEntity", rowsEntity);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Bundle bundle = getArguments();
            orderStr = bundle.getString("orderStr");
            buttonStr = bundle.getString("buttonStr");
            modelName = bundle.getString("modelName");
            rowsEntity = bundle.getParcelable("rowsEntity");
        }
    }

    /**
     * 设置公章归还时间
     */
    private void setSealBackDate() {
        String url = URL_OA + "/SetSealBackDate";
        RequestParams params = ParamsUtil.getSignParams();
        params.put("id", rowsEntity.getKeyId());
        httpClient().get(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = BaseActivity.getString(responseBody);
                Log.e("归还公章", result);
                //{"Status":true,"Message":"确认成功","Other":null}
                try {
                    JSONObject object = new JSONObject(result);
                    String msg = object.getString("Message");
                    if (object.getBoolean("Status")) {
                        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
                        Intent it = new Intent(getActivity(), AgreeActivity.class);
                        it.putExtra("waitHandleBean", rowsEntity);
                        startActivity(it);
                        getActivity().finish();
                    } else {
                        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

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
     * 公章实际归还时间
     */
    private void setSealActualBackDate() {

        String url = URL_OA + "/SetSealActualBackDate";
        RequestParams params = ParamsUtil.getSignParams();
        params.put("id", rowsEntity.getKeyId());
        httpClient().get(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = BaseActivity.getString(responseBody);
                Log.e("归还公章", result);
                //{"Status":true,"Message":"确认成功","Other":null}
                try {
                    JSONObject object = new JSONObject(result);
                    String msg = object.getString("Message");
                    if (object.getBoolean("Status")) {
                        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
                        Intent it = new Intent(getActivity(), AgreeActivity.class);
                        it.putExtra("waitHandleBean", rowsEntity);
                        startActivity(it);
                        getActivity().finish();
                    } else {
                        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

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
     * 设置实际拿到章的时间
     */
    private void setSealActualPickDate() {
        String url = URL_OA + "/SetSealActualPickDate";
        RequestParams params = ParamsUtil.getSignParams();
        params.put("id", rowsEntity.getKeyId());
        httpClient().get(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = BaseActivity.getString(responseBody);
                Log.e("归还公章", result);
                //{"Status":true,"Message":"确认成功","Other":null}
                try {
                    JSONObject object = new JSONObject(result);
                    String msg = object.getString("Message");
                    if (object.getBoolean("Status")) {
                        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
                        Intent it = new Intent(getActivity(), AgreeActivity.class);
                        it.putExtra("waitHandleBean", rowsEntity);
                        startActivity(it);
                        getActivity().finish();
                    } else {
                        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

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
     * 设置借章人确认到手时间
     */
    private void setSealActualLendDate() {
        String url = URL_OA + "/SetSealActualLendDate";
        RequestParams params = ParamsUtil.getSignParams();
        params.put("id", rowsEntity.getKeyId());
        httpClient().get(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = BaseActivity.getString(responseBody);
                Log.e("归还公章", result);
                //{"Status":true,"Message":"确认成功","Other":null}
                try {
                    JSONObject object = new JSONObject(result);
                    String msg = object.getString("Message");
                    if (object.getBoolean("Status")) {
                        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
                        Intent it = new Intent(getActivity(), AgreeActivity.class);
                        it.putExtra("waitHandleBean", rowsEntity);
                        startActivity(it);
                        getActivity().finish();
                    } else {
                        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

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
     * 回收合同
     */
    private void recoverContract() {
        String url = URL_OA + "/RecoverContract";
        RequestParams params = ParamsUtil.getSignParams();
        if (adapter.getIdMap().size() == 0) {
            Toast.makeText(getActivity(), "未选择任何一项", Toast.LENGTH_SHORT).show();
            return;
        }
        List<Integer> idList = new ArrayList<>();
        Map<Integer, Integer> map = adapter.getIdMap();
        Collection c = map.values();
        Iterator it = c.iterator();
        for (; it.hasNext(); ) {
            idList.add((Integer) it.next());
        }
        params.put("idStr", idList);
        httpClient().get(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = BaseActivity.getString(responseBody);
                Log.e("合同id列表", result);
                try {
                    JSONObject object = new JSONObject(result);
                    String msg = object.getString("Message");
                    if (object.getBoolean("Status")) {
//                        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
                        submit();
//                        getActivity().finish();
                    } else {
                        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

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
     * 提交
     */
    private void submit() throws JSONException {

        String url = URL_OA + "/SubmitSysFlow";
        RequestParams params = ParamsUtil.getSignParams();
        params.put("sysUserId", SPUtils.getInt("id", 0));
        params.put("statusId", rowsEntity.getId());
        params.put("employeeIdStr", new ArrayList<>());
        params.put("suggestion", "");
        httpClient().get(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = BaseActivity.getString(responseBody);
                Log.e("点击提交", result);
                try {
                    JSONObject obj = new JSONObject(result);
                    if (obj.getBoolean("Status")) {
//                        Toast.makeText(getActivity(),obj.getString("Message"), Toast.LENGTH_SHORT).show();
                        getActivity().finish();
                    } else {
                        Toast.makeText(getActivity(), obj.getString("Message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("提交", statusCode + "");
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

    private MyMultiChoiceAdapter adapter;

    /**
     * 获取回收合同的列表
     */
    private void getStampManagerList() {
        String url = URL_OA + "/GetStampManagerList";
        RequestParams params = ParamsUtil.getSignParams();
        params.put("id", rowsEntity.getKeyId());
        httpClient().get(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                final List<StampManagerModel> list = new ArrayList<>();
                String result = BaseActivity.getString(responseBody);
                Log.e("盖章回收列表", result);
                try {
                    JSONObject object = new JSONObject(result);
                    JSONArray array = object.getJSONArray("rows");
                    Gson gson = new Gson();
                    list.clear();
                    for (int i = 0; i < array.length(); i++) {
                        StampManagerModel model = gson.fromJson(array.getJSONObject(i).toString(), StampManagerModel.class);
                        list.add(model);
                    }
                    adapter = new MyMultiChoiceAdapter(getActivity(), list);
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    LayoutInflater inflater = getActivity().getLayoutInflater();
                    View view = inflater.inflate(R.layout.fragment_dialog_multi_choice, null);
                    final ListView lv = (ListView) view.findViewById(R.id.lv);
                    lv.setAdapter(adapter);
                    builder.setTitle("可回收列表");
                    builder.setMessage("");
                    builder.setView(view).setPositiveButton("确认", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            recoverContract();
                        }
                    }).setCancelable(true);
                    builder.create().show();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }

            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onFinish() {
                super.onFinish();
            }
        });

    }

    /**
     * 确认到款，完成合同
     */
    private void finishContractFlow(String suggestion) {
        String url = URL_OA + "/FinishContractFlow";
        RequestParams params = ParamsUtil.getSignParams();
        params.put("keyId", rowsEntity.getKeyId());
        params.put("statusId", rowsEntity.getId());
        params.put("sysUserId", SPUtils.getInt("id", 0));
        params.put("suggestion", suggestion);
        httpClient().get(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = BaseActivity.getString(responseBody);
                Log.e("确认合同到款", result);
                try {
                    JSONObject object = new JSONObject(result);
                    String msg = object.getString("Message");
                    if (object.getBoolean("Status")) {
                        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
                        getActivity().finish();
                    } else {
                        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

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
     * 用车后反馈
     */
    private void setSendCarFeedBack(String backDate, int afterKm) {
        String url = URL_OA + "/SetSendCarFeedBack";
        RequestParams params = ParamsUtil.getSignParams();
        params.put("id", rowsEntity.getKeyId());
        params.put("backDate", backDate);
        params.put("afterKm", afterKm);
        httpClient().get(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = BaseActivity.getString(responseBody);
                Log.e("用车反馈", result);
                try {
                    JSONObject object = new JSONObject(result);
                    String msg = object.getString("Message");
                    if (object.getBoolean("Status")) {
                        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
                        Intent it = new Intent(getActivity(), AgreeActivity.class);
                        it.putExtra("waitHandleBean", rowsEntity);
                        startActivity(it);
                        getActivity().finish();
                    } else {
                        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

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
     * 填写项目进度
     */
    private void projectSchedulePost(String contractId, String remark, String finishDate, int rate) {
        String url = URL_OA + "/SchedulePost";
        RequestParams params = ParamsUtil.getSignParams();
        params.put("contractId", contractId);
        params.put("remark", remark);
        params.put("sysUserId", SPUtils.getInt("id", 0));
        params.put("finishDate", finishDate);
        params.put("schedule", rate);
        httpClient().get(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = BaseActivity.getString(responseBody);
                Log.e("填写项目进度", result);
                try {
                    JSONObject object = new JSONObject(result);
                    if (object.getBoolean("Status")) {
                        Toast.makeText(getActivity(), object.getString("Message"), Toast.LENGTH_SHORT).show();
                        Intent it = new Intent(getActivity(), AgreeActivity.class);
                        it.putExtra("waitHandleBean", rowsEntity);
                        startActivity(it);
                        getActivity().finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

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
     * 采购入库
     */
    private void stockGoods(String json) {
        String url = URL_OA + "/StockGoods";
        RequestParams params = ParamsUtil.getSignParams();
        params.put("list", json);
        Log.e("list", json);
        httpClient().get(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = BaseActivity.getString(responseBody);
                Log.e("采购入库", result);
                try {
                    JSONObject object = new JSONObject(result);
                    if (object.getBoolean("Status")) {
                        Toast.makeText(getActivity(), object.getString("Message"), Toast.LENGTH_SHORT).show();
//                        Intent it = new Intent(getActivity(), AgreeActivity.class);
//                        it.putExtra("waitHandleBean", rowsEntity);
//                        startActivity(it);
//                        getActivity().finish();
                        submit();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("采购入库", statusCode + "");
            }

            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onFinish() {
                super.onFinish();
            }
        });
    }

    /**
     * 物品领用发放
     */
    private void onGoodsAssign(String json) {
        String url = URL_OA + "/SetOfficeRecipientsAssignQuantity";
        RequestParams params = ParamsUtil.getSignParams();
        params.put("list", json);
        params.put("statusId", rowsEntity.getId());
        params.put("sysUserId", SPUtils.getInt("id", 0));
        httpClient().get(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String result = BaseActivity.getString(responseBody);
                Log.e("物品发放", result);
                try {
                    JSONObject object = new JSONObject(result);
                    if (object.getBoolean("Status")) {
                        Toast.makeText(getActivity(), object.getString("Message"), Toast.LENGTH_SHORT).show();
//                        Intent it = new Intent(getActivity(), AgreeActivity.class);
//                        it.putExtra("waitHandleBean", rowsEntity);
//                        startActivity(it);
                        getActivity().finish();

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.e("物品发放", statusCode + "");
            }

            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onFinish() {
                super.onFinish();
            }
        });
    }

    /**
     * 变更客户经理
     */
    private void setCustomerManager(String manager, String suggestion) {
        String url = URL_OA + "/SetCustomerManager";
        RequestParams params = ParamsUtil.getSignParams();
        params.put("keyId", rowsEntity.getKeyId());
        params.put("statusId", rowsEntity.getStatus());
        params.put("sysUserId", SPUtils.getInt("id", 0));
        params.put("manager", manager);
        params.put("suggestion", suggestion);
        httpClient().get(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }

            @Override
            public void onStart() {
                super.onStart();
            }

            @Override
            public void onFinish() {
                super.onFinish();
            }
        });
    }


}
