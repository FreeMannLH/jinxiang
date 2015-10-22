package ideatc.jinxiang.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ccb on 2015/8/13.
 * 待办事项集合
 */
public class WaitHandleListBean implements Parcelable {


    /**
     * total : 1
     * rows : [{"SendDateStr":"2015-08-07","Status":1,"KeyId":2,"SysFlow":"客户资料转交","SysFlowId":9,"PrevNode":"同部门审核","StatusStr":"提交","Id":10,"CurrentNode":"同部门审核","Maker":"管理员"}]
     */
    private int total;
    private List<RowsEntity> rows;

    public void setTotal(int total) {
        this.total = total;
    }

    public void setRows(List<RowsEntity> rows) {
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public List<RowsEntity> getRows() {
        return rows;
    }

    public static class RowsEntity implements Parcelable {

        /**
         * SendDateStr : 2015-08-07
         * Status : 1
         * KeyId : 2
         * SysFlow : 客户资料转交
         * SysFlowId : 9
         * PrevNode : 同部门审核
         * StatusStr : 提交
         * Id : 10
         * CurrentNode : 同部门审核
         * Maker : 管理员
         */
        private String ModelName;
        private String SendDateStr;

        public String getModelName() {
            return ModelName;
        }

        public void setModelName(String modelName) {
            ModelName = modelName;
        }

        private int Status;
        private int KeyId;
        private String SysFlow;
        private int SysFlowId;
        private String PrevNode;
        private String StatusStr;
        private int Id;
        private String CurrentNode;
        private String Maker;

        public void setSendDateStr(String SendDateStr) {
            this.SendDateStr = SendDateStr;
        }

        public void setStatus(int Status) {
            this.Status = Status;
        }

        public void setKeyId(int KeyId) {
            this.KeyId = KeyId;
        }

        public void setSysFlow(String SysFlow) {
            this.SysFlow = SysFlow;
        }

        public void setSysFlowId(int SysFlowId) {
            this.SysFlowId = SysFlowId;
        }

        public void setPrevNode(String PrevNode) {
            this.PrevNode = PrevNode;
        }

        public void setStatusStr(String StatusStr) {
            this.StatusStr = StatusStr;
        }

        public void setId(int Id) {
            this.Id = Id;
        }

        public void setCurrentNode(String CurrentNode) {
            this.CurrentNode = CurrentNode;
        }

        public void setMaker(String Maker) {
            this.Maker = Maker;
        }

        public String getSendDateStr() {
            return SendDateStr;
        }

        public int getStatus() {
            return Status;
        }

        public int getKeyId() {
            return KeyId;
        }

        public String getSysFlow() {
            return SysFlow;
        }

        public int getSysFlowId() {
            return SysFlowId;
        }

        public String getPrevNode() {
            return PrevNode;
        }

        public String getStatusStr() {
            return StatusStr;
        }

        public int getId() {
            return Id;
        }

        public String getCurrentNode() {
            return CurrentNode;
        }

        public String getMaker() {
            return Maker;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.SendDateStr);
            dest.writeInt(this.Status);
            dest.writeInt(this.KeyId);
            dest.writeString(this.SysFlow);
            dest.writeInt(this.SysFlowId);
            dest.writeString(this.PrevNode);
            dest.writeString(this.StatusStr);
            dest.writeInt(this.Id);
            dest.writeString(this.CurrentNode);
            dest.writeString(this.Maker);
            dest.writeString(this.ModelName);
        }

        public RowsEntity() {
        }

        protected RowsEntity(Parcel in) {
            this.SendDateStr = in.readString();
            this.Status = in.readInt();
            this.KeyId = in.readInt();
            this.SysFlow = in.readString();
            this.SysFlowId = in.readInt();
            this.PrevNode = in.readString();
            this.StatusStr = in.readString();
            this.Id = in.readInt();
            this.CurrentNode = in.readString();
            this.Maker = in.readString();
            this.ModelName = in.readString();
        }

        public static final Creator<RowsEntity> CREATOR = new Creator<RowsEntity>() {
            public RowsEntity createFromParcel(Parcel source) {
                return new RowsEntity(source);
            }

            public RowsEntity[] newArray(int size) {
                return new RowsEntity[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.total);
        dest.writeList(this.rows);
    }

    public WaitHandleListBean() {
    }

    protected WaitHandleListBean(Parcel in) {
        this.total = in.readInt();
        this.rows = new ArrayList<RowsEntity>();
        in.readList(this.rows, List.class.getClassLoader());
    }

    public static final Parcelable.Creator<WaitHandleListBean> CREATOR = new Parcelable.Creator<WaitHandleListBean>() {
        public WaitHandleListBean createFromParcel(Parcel source) {
            return new WaitHandleListBean(source);
        }

        public WaitHandleListBean[] newArray(int size) {
            return new WaitHandleListBean[size];
        }
    };
}
