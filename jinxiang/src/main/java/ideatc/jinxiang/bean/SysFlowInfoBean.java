package ideatc.jinxiang.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ccb on 2015/8/14.
 */
public class SysFlowInfoBean implements Parcelable {


    /**
     * Buttons : [{"id":"同意","text":"同意","iconCls":"easyui-icon-edit","handler":"onApprov()"},{"id":"不同意","text":"不同意","iconCls":"easyui-icon-edit","handler":"onBack()"}]
     * ModelName : SendCarModel
     * Orders : {"AfterKm":0,"BeforeKm":20000,"SendTime":"","EndAddress":"晋江","ApplyDateStr":"2015-08-10","BackDate":null,"ApplyDate":"/Date(1439136000000)/","Employee":"管理员","MakeDateStr":"2015-08-10","Usage":"外出","SendDateStr":"2015-08-10","MakeDate":"/Date(1439170922000)/","SendDate":"/Date(1439136000000)/","BackDateStr":"","OrderNo":"PCD20150810001","SysFlowId":0,"MakerId":0,"BackTime":"","Id":1,"Remark":"","Content":"外出","Maker":"管理员","EmployeeId":"admin"}
     * SysFlowLog : [{"User":"管理员","Status":1,"OperateDateStr":"2015-08-10","OperateDate":"/Date(1439170963000)/","Node":"直接上级","PrevNode":"使用人用车申请","StatusStr":"提交","Reciver":"","Id":5,"PrevUser":"管理员","Content":""}]
     */
    private List<ButtonsEntity> Buttons;
    private String ModelName;
    private OrdersEntity Orders;
    private List<SysFlowLogEntity> SysFlowLog;

    public void setButtons(List<ButtonsEntity> Buttons) {
        this.Buttons = Buttons;
    }

    public void setModelName(String ModelName) {
        this.ModelName = ModelName;
    }

    public void setOrders(OrdersEntity Orders) {
        this.Orders = Orders;
    }

    public void setSysFlowLog(List<SysFlowLogEntity> SysFlowLog) {
        this.SysFlowLog = SysFlowLog;
    }

    public List<ButtonsEntity> getButtons() {
        return Buttons;
    }

    public String getModelName() {
        return ModelName;
    }

    public OrdersEntity getOrders() {
        return Orders;
    }

    public List<SysFlowLogEntity> getSysFlowLog() {
        return SysFlowLog;
    }

    public static class ButtonsEntity implements Parcelable {
        /**
         * id : 同意
         * text : 同意
         * iconCls : easyui-icon-edit
         * handler : onApprov()
         */
        private String id;
        private String text;
        private String iconCls;
        private String handler;

        public void setId(String id) {
            this.id = id;
        }

        public void setText(String text) {
            this.text = text;
        }

        public void setIconCls(String iconCls) {
            this.iconCls = iconCls;
        }

        public void setHandler(String handler) {
            this.handler = handler;
        }

        public String getId() {
            return id;
        }

        public String getText() {
            return text;
        }

        public String getIconCls() {
            return iconCls;
        }

        public String getHandler() {
            return handler;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.id);
            dest.writeString(this.text);
            dest.writeString(this.iconCls);
            dest.writeString(this.handler);
        }

        public ButtonsEntity() {
        }

        protected ButtonsEntity(Parcel in) {
            this.id = in.readString();
            this.text = in.readString();
            this.iconCls = in.readString();
            this.handler = in.readString();
        }

        public static final Creator<ButtonsEntity> CREATOR = new Creator<ButtonsEntity>() {
            public ButtonsEntity createFromParcel(Parcel source) {
                return new ButtonsEntity(source);
            }

            public ButtonsEntity[] newArray(int size) {
                return new ButtonsEntity[size];
            }
        };
    }

    public static class OrdersEntity implements Parcelable {
        /**
         * AfterKm : 0
         * BeforeKm : 20000
         * SendTime :
         * EndAddress : 晋江
         * ApplyDateStr : 2015-08-10
         * BackDate : null
         * ApplyDate : /Date(1439136000000)/
         * Employee : 管理员
         * MakeDateStr : 2015-08-10
         * Usage : 外出
         * SendDateStr : 2015-08-10
         * MakeDate : /Date(1439170922000)/
         * SendDate : /Date(1439136000000)/
         * BackDateStr :
         * OrderNo : PCD20150810001
         * SysFlowId : 0
         * MakerId : 0
         * BackTime :
         * Id : 1
         * Remark :
         * Content : 外出
         * Maker : 管理员
         * EmployeeId : admin
         */
        private int AfterKm;
        private int BeforeKm;
        private String SendTime;
        private String EndAddress;
        private String ApplyDateStr;
        private String BackDate;
        private String ApplyDate;
        private String Employee;
        private String MakeDateStr;
        private String Usage;
        private String SendDateStr;
        private String MakeDate;
        private String SendDate;
        private String BackDateStr;
        private String OrderNo;
        private int SysFlowId;
        private int MakerId;
        private String BackTime;
        private int Id;
        private String Remark;
        private String Content;
        private String Maker;
        private String EmployeeId;

        public void setAfterKm(int AfterKm) {
            this.AfterKm = AfterKm;
        }

        public void setBeforeKm(int BeforeKm) {
            this.BeforeKm = BeforeKm;
        }

        public void setSendTime(String SendTime) {
            this.SendTime = SendTime;
        }

        public void setEndAddress(String EndAddress) {
            this.EndAddress = EndAddress;
        }

        public void setApplyDateStr(String ApplyDateStr) {
            this.ApplyDateStr = ApplyDateStr;
        }

        public void setBackDate(String BackDate) {
            this.BackDate = BackDate;
        }

        public void setApplyDate(String ApplyDate) {
            this.ApplyDate = ApplyDate;
        }

        public void setEmployee(String Employee) {
            this.Employee = Employee;
        }

        public void setMakeDateStr(String MakeDateStr) {
            this.MakeDateStr = MakeDateStr;
        }

        public void setUsage(String Usage) {
            this.Usage = Usage;
        }

        public void setSendDateStr(String SendDateStr) {
            this.SendDateStr = SendDateStr;
        }

        public void setMakeDate(String MakeDate) {
            this.MakeDate = MakeDate;
        }

        public void setSendDate(String SendDate) {
            this.SendDate = SendDate;
        }

        public void setBackDateStr(String BackDateStr) {
            this.BackDateStr = BackDateStr;
        }

        public void setOrderNo(String OrderNo) {
            this.OrderNo = OrderNo;
        }

        public void setSysFlowId(int SysFlowId) {
            this.SysFlowId = SysFlowId;
        }

        public void setMakerId(int MakerId) {
            this.MakerId = MakerId;
        }

        public void setBackTime(String BackTime) {
            this.BackTime = BackTime;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public void setRemark(String Remark) {
            this.Remark = Remark;
        }

        public void setContent(String Content) {
            this.Content = Content;
        }

        public void setMaker(String Maker) {
            this.Maker = Maker;
        }

        public void setEmployeeId(String EmployeeId) {
            this.EmployeeId = EmployeeId;
        }

        public int getAfterKm() {
            return AfterKm;
        }

        public int getBeforeKm() {
            return BeforeKm;
        }

        public String getSendTime() {
            return SendTime;
        }

        public String getEndAddress() {
            return EndAddress;
        }

        public String getApplyDateStr() {
            return ApplyDateStr;
        }

        public String getBackDate() {
            return BackDate;
        }

        public String getApplyDate() {
            return ApplyDate;
        }

        public String getEmployee() {
            return Employee;
        }

        public String getMakeDateStr() {
            return MakeDateStr;
        }

        public String getUsage() {
            return Usage;
        }

        public String getSendDateStr() {
            return SendDateStr;
        }

        public String getMakeDate() {
            return MakeDate;
        }

        public String getSendDate() {
            return SendDate;
        }

        public String getBackDateStr() {
            return BackDateStr;
        }

        public String getOrderNo() {
            return OrderNo;
        }

        public int getSysFlowId() {
            return SysFlowId;
        }

        public int getMakerId() {
            return MakerId;
        }

        public String getBackTime() {
            return BackTime;
        }

        public int getId() {
            return Id;
        }

        public String getRemark() {
            return Remark;
        }

        public String getContent() {
            return Content;
        }

        public String getMaker() {
            return Maker;
        }

        public String getEmployeeId() {
            return EmployeeId;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.AfterKm);
            dest.writeInt(this.BeforeKm);
            dest.writeString(this.SendTime);
            dest.writeString(this.EndAddress);
            dest.writeString(this.ApplyDateStr);
            dest.writeString(this.BackDate);
            dest.writeString(this.ApplyDate);
            dest.writeString(this.Employee);
            dest.writeString(this.MakeDateStr);
            dest.writeString(this.Usage);
            dest.writeString(this.SendDateStr);
            dest.writeString(this.MakeDate);
            dest.writeString(this.SendDate);
            dest.writeString(this.BackDateStr);
            dest.writeString(this.OrderNo);
            dest.writeInt(this.SysFlowId);
            dest.writeInt(this.MakerId);
            dest.writeString(this.BackTime);
            dest.writeInt(this.Id);
            dest.writeString(this.Remark);
            dest.writeString(this.Content);
            dest.writeString(this.Maker);
            dest.writeString(this.EmployeeId);
        }

        public OrdersEntity() {
        }

        protected OrdersEntity(Parcel in) {
            this.AfterKm = in.readInt();
            this.BeforeKm = in.readInt();
            this.SendTime = in.readString();
            this.EndAddress = in.readString();
            this.ApplyDateStr = in.readString();
            this.BackDate = in.readString();
            this.ApplyDate = in.readString();
            this.Employee = in.readString();
            this.MakeDateStr = in.readString();
            this.Usage = in.readString();
            this.SendDateStr = in.readString();
            this.MakeDate = in.readString();
            this.SendDate = in.readString();
            this.BackDateStr = in.readString();
            this.OrderNo = in.readString();
            this.SysFlowId = in.readInt();
            this.MakerId = in.readInt();
            this.BackTime = in.readString();
            this.Id = in.readInt();
            this.Remark = in.readString();
            this.Content = in.readString();
            this.Maker = in.readString();
            this.EmployeeId = in.readString();
        }

        public static final Creator<OrdersEntity> CREATOR = new Creator<OrdersEntity>() {
            public OrdersEntity createFromParcel(Parcel source) {
                return new OrdersEntity(source);
            }

            public OrdersEntity[] newArray(int size) {
                return new OrdersEntity[size];
            }
        };
    }

    public static class SysFlowLogEntity implements Parcelable {
        /**
         * User : 管理员
         * Status : 1
         * OperateDateStr : 2015-08-10
         * OperateDate : /Date(1439170963000)/
         * Node : 直接上级
         * PrevNode : 使用人用车申请
         * StatusStr : 提交
         * Reciver :
         * Id : 5
         * PrevUser : 管理员
         * Content :
         */
        private String User;
        private int Status;
        private String OperateDateStr;
        private String OperateDate;
        private String Node;
        private String PrevNode;
        private String StatusStr;
        private String Reciver;
        private int Id;
        private String PrevUser;
        private String Content;

        public void setUser(String User) {
            this.User = User;
        }

        public void setStatus(int Status) {
            this.Status = Status;
        }

        public void setOperateDateStr(String OperateDateStr) {
            this.OperateDateStr = OperateDateStr;
        }

        public void setOperateDate(String OperateDate) {
            this.OperateDate = OperateDate;
        }

        public void setNode(String Node) {
            this.Node = Node;
        }

        public void setPrevNode(String PrevNode) {
            this.PrevNode = PrevNode;
        }

        public void setStatusStr(String StatusStr) {
            this.StatusStr = StatusStr;
        }

        public void setReciver(String Reciver) {
            this.Reciver = Reciver;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public void setPrevUser(String PrevUser) {
            this.PrevUser = PrevUser;
        }

        public void setContent(String Content) {
            this.Content = Content;
        }

        public String getUser() {
            return User;
        }

        public int getStatus() {
            return Status;
        }

        public String getOperateDateStr() {
            return OperateDateStr;
        }

        public String getOperateDate() {
            return OperateDate;
        }

        public String getNode() {
            return Node;
        }

        public String getPrevNode() {
            return PrevNode;
        }

        public String getStatusStr() {
            return StatusStr;
        }

        public String getReciver() {
            return Reciver;
        }

        public int getId() {
            return Id;
        }

        public String getPrevUser() {
            return PrevUser;
        }

        public String getContent() {
            return Content;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.User);
            dest.writeInt(this.Status);
            dest.writeString(this.OperateDateStr);
            dest.writeString(this.OperateDate);
            dest.writeString(this.Node);
            dest.writeString(this.PrevNode);
            dest.writeString(this.StatusStr);
            dest.writeString(this.Reciver);
            dest.writeInt(this.Id);
            dest.writeString(this.PrevUser);
            dest.writeString(this.Content);
        }

        public SysFlowLogEntity() {
        }

        protected SysFlowLogEntity(Parcel in) {
            this.User = in.readString();
            this.Status = in.readInt();
            this.OperateDateStr = in.readString();
            this.OperateDate = in.readString();
            this.Node = in.readString();
            this.PrevNode = in.readString();
            this.StatusStr = in.readString();
            this.Reciver = in.readString();
            this.Id = in.readInt();
            this.PrevUser = in.readString();
            this.Content = in.readString();
        }

        public static final Creator<SysFlowLogEntity> CREATOR = new Creator<SysFlowLogEntity>() {
            public SysFlowLogEntity createFromParcel(Parcel source) {
                return new SysFlowLogEntity(source);
            }

            public SysFlowLogEntity[] newArray(int size) {
                return new SysFlowLogEntity[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.Buttons);
        dest.writeString(this.ModelName);
        dest.writeParcelable(this.Orders, flags);
        dest.writeList(this.SysFlowLog);
    }

    public SysFlowInfoBean() {
    }

    protected SysFlowInfoBean(Parcel in) {
        this.Buttons = new ArrayList<ButtonsEntity>();
        in.readList(this.Buttons, List.class.getClassLoader());
        this.ModelName = in.readString();
        this.Orders = in.readParcelable(OrdersEntity.class.getClassLoader());
        this.SysFlowLog = new ArrayList<SysFlowLogEntity>();
        in.readList(this.SysFlowLog, List.class.getClassLoader());
    }

    public static final Parcelable.Creator<SysFlowInfoBean> CREATOR = new Parcelable.Creator<SysFlowInfoBean>() {
        public SysFlowInfoBean createFromParcel(Parcel source) {
            return new SysFlowInfoBean(source);
        }

        public SysFlowInfoBean[] newArray(int size) {
            return new SysFlowInfoBean[size];
        }
    };
}
