package ideatc.jinxiang.fragment.matters_detail_handled_acitivty;


import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

import ideatc.jinxiang.R;
import ideatc.jinxiang.base.fragment.BaseFragment;
import ideatc.jinxiang.bean.ExpressDeliveryModel;
import ideatc.jinxiang.bean.ExpressMailModel;
import ideatc.jinxiang.bean.LeaveModel;
import ideatc.jinxiang.bean.OfficeRecipientsModel;
import ideatc.jinxiang.bean.PurchaseOrderModel;
import ideatc.jinxiang.bean.ScrappedModel;
import ideatc.jinxiang.bean.SealLendModel;
import ideatc.jinxiang.bean.SendCarModel;
import ideatc.jinxiang.bean.StampManagerModel;
import ideatc.jinxiang.bean.WaitHandleListBean;
import ideatc.jinxiang.bean.crm.ProjectDetailBean;

public class BasicInfoFragment extends BaseFragment {


    private String orderStr;
    private WaitHandleListBean.RowsEntity rowsEntity;
    private String modelName;
    private int layout;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View view = null;
        Gson gson = new Gson();


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
//
            AppCompatButton rb1 = (AppCompatButton) view.findViewById(R.id.id_rb1);
            AppCompatButton rb2 = (AppCompatButton) view.findViewById(R.id.id_rb2);
            rb1.setVisibility(View.GONE);
            rb2.setVisibility(View.GONE);

        }


        /**
         * 快递
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
            rb1.setVisibility(View.GONE);
            rb2.setVisibility(View.GONE);
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
            rb1.setVisibility(View.GONE);
            rb2.setVisibility(View.GONE);
        }


        /**
         * 项目
         */
        else if ("ContractModel".equals(modelName)) {
            Log("ContractModel");
            Log.e("orderStr", orderStr);
            ProjectDetailBean.ModelEntity projectInfo = gson.fromJson(orderStr, ProjectDetailBean.ModelEntity.class);
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
            AppCompatButton rb1 = (AppCompatButton) view.findViewById(R.id.id_rb1);
            AppCompatButton rb2 = (AppCompatButton) view.findViewById(R.id.id_rb2);
            AppCompatButton rb3 = (AppCompatButton) view.findViewById(R.id.id_rb3);
            rb1.setVisibility(View.GONE);
            rb2.setVisibility(View.GONE);
            rb3.setVisibility(View.GONE);
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
            rb1.setVisibility(View.GONE);
            rb2.setVisibility(View.GONE);
            rb3.setVisibility(View.GONE);
        }


        /**
         * 物品领用
         */
        else if ("OfficeRecipientsModel".equals(modelName)) {
            Log("isOfficeRecipientsModel");
            OfficeRecipientsModel officeRecipientsModel = gson.fromJson(orderStr, OfficeRecipientsModel.class);
            layout = R.layout.fragment_basic_info_use_office_handled;
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

            TextView fact1 = (TextView) view.findViewById(R.id.id_fact1);
            TextView fact2 = (TextView) view.findViewById(R.id.id_fact2);
            TextView fact3 = (TextView) view.findViewById(R.id.id_fact3);
            TextView fact4 = (TextView) view.findViewById(R.id.id_fact4);
            TextView fact5 = (TextView) view.findViewById(R.id.id_fact5);
            TextView fact6 = (TextView) view.findViewById(R.id.id_fact6);
            TextView fact7 = (TextView) view.findViewById(R.id.id_fact7);
            TextView fact8 = (TextView) view.findViewById(R.id.id_fact8);

            TextView[] arr1 = {goods1, goods2, goods3, goods4, goods5, goods6, goods7, goods8};
            TextView[] arr2 = {count1, count2, count3, count4, count5, count6, count7, count8};
            TextView[] arr3 = {unit1, unit2, unit3, unit4, unit5, unit6, unit7, unit8};
            TextView[] arr4 = {fact1, fact2, fact3, fact4, fact5, fact6, fact7, fact8};

            List<TextView> list1 = Arrays.asList(arr1);
            List<TextView> list2 = Arrays.asList(arr2);
            List<TextView> list3 = Arrays.asList(arr3);
            List<TextView> list4 = Arrays.asList(arr4);

            List<OfficeRecipientsModel.OfficeRecipientsItemModel> list = officeRecipientsModel.getList();
            for (int i = 0, size = list.size(); i < size; i++) {
                if (i >= 8) break;

                OfficeRecipientsModel.OfficeRecipientsItemModel item = list.get(i);
                list1.get(i).setText(item.getGoods().toString());
                list2.get(i).setText(item.getQuantity() + "");
                list3.get(i).setText(item.getUnit().toString());
                list4.get(i).setText(item.getQuantity() + "");
            }
            name.setText(officeRecipientsModel.getEmployee() + "");
            department.setText(officeRecipientsModel.getDepartment() + "");

            AppCompatButton rb1 = (AppCompatButton) view.findViewById(R.id.id_rb1);
            AppCompatButton rb2 = (AppCompatButton) view.findViewById(R.id.id_rb2);
            rb1.setVisibility(View.GONE);
            rb2.setVisibility(View.GONE);
        }


        /**
         * 物品采购
         */
        else if ("PurchaseOrderModel".equals(modelName)) {
            Log("isPurchaseOrderModel");
            PurchaseOrderModel purchaseOrderModel = gson.fromJson(orderStr, PurchaseOrderModel.class);
            layout = R.layout.fragment_basic_info_buy_things_handled;
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
            for (int i = 0, size = list.size(); i < size; i++) {
                if (i >= 8) break;

                PurchaseOrderModel.PurchaseOrderItemModel item = list.get(i);
                list1.get(i).setText(item.getGoodsName() + "");
                list2.get(i).setText(item.getQuantity() + "");
                list3.get(i).setText(item.getGoodsUnit() + "");
            }
            name.setText(purchaseOrderModel.getEmployee() + "");
            reMark.setText(purchaseOrderModel.getRemark() + "");
        }


        /**
         * 报废
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
            rb1.setVisibility(View.GONE);
            rb2.setVisibility(View.GONE);
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
            rb1.setVisibility(View.GONE);
            rb2.setVisibility(View.GONE);

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
            name.setText(stampManagerModel.getEmployee() + "");
            type.setText(stampManagerModel.getStampType() + "");
            date.setText(stampManagerModel.getStampDateStr() + "");
            num.setText(stampManagerModel.getNumber() + "");
            reMark.setText(stampManagerModel.getContent() + "");
            AppCompatButton rb1 = (AppCompatButton) view.findViewById(R.id.id_rb1);
            AppCompatButton rb2 = (AppCompatButton) view.findViewById(R.id.id_rb2);
            rb1.setVisibility(View.GONE);
            rb2.setVisibility(View.GONE);
        }
        return view;
    }


    /**
     * @param modelName
     * @param orderStr
     * @return
     */
    public static BasicInfoFragment newInstance(String modelName, String orderStr, WaitHandleListBean.RowsEntity rowsEntity) {
        BasicInfoFragment fragment = new BasicInfoFragment();
        Bundle args = new Bundle();
        args.putString("orderStr", orderStr);
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
            modelName = bundle.getString("modelName");
            rowsEntity = bundle.getParcelable("rowsEntity");
        }
    }
}
