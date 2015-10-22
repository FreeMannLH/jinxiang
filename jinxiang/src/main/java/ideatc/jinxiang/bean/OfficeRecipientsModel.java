package ideatc.jinxiang.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ccb on 2015/8/14.
 * 物品领用模型
 */
public class OfficeRecipientsModel implements Parcelable {
   private int Id;
    private String OrderNo;
    private int DepartmentId;
    private String Department;
    private String EmployeeId;
    private String Employee;
    private int MakerId;
    private String Maker;
    private String MakeDateStr;
    private List<OfficeRecipientsItemModel> OfficeRecipientsItem;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getOrderNo() {
        return OrderNo;
    }

    public void setOrderNo(String orderNo) {
        OrderNo = orderNo;
    }

    public int getDepartmentId() {
        return DepartmentId;
    }

    public void setDepartmentId(int departmentId) {
        DepartmentId = departmentId;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public String getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(String employeeId) {
        EmployeeId = employeeId;
    }

    public String getEmployee() {
        return Employee;
    }

    public void setEmployee(String employee) {
        Employee = employee;
    }

    public int getMakerId() {
        return MakerId;
    }

    public void setMakerId(int makerId) {
        MakerId = makerId;
    }

    public String getMaker() {
        return Maker;
    }

    public void setMaker(String maker) {
        Maker = maker;
    }

    public List<OfficeRecipientsItemModel> getList() {
        return OfficeRecipientsItem;
    }

    public void setList(List<OfficeRecipientsItemModel> list) {
        this.OfficeRecipientsItem = list;
    }

    public String getMakeDateStr() {
        return MakeDateStr;
    }

    public void setMakeDateStr(String makeDateStr) {
        MakeDateStr = makeDateStr;
    }

    /**
     * 领用物品列表模型
     */
    public static class OfficeRecipientsItemModel implements Parcelable {
        private int Id;
        private  int GoodsId;
        private String Goods;
        private String Spec;
        private  int Quantity;
        private  int ActualQuantity;
        private String Unit;
        private  String Remark;

        public int getId() {
            return Id;
        }

        public void setId(int id) {
            Id = id;
        }

        public int getGoodsId() {
            return GoodsId;
        }

        public void setGoodsId(int goodsId) {
            GoodsId = goodsId;
        }

        public String getGoods() {
            return Goods;
        }

        public void setGoods(String goods) {
            Goods = goods;
        }

        public String getSpec() {
            return Spec;
        }

        public void setSpec(String spec) {
            Spec = spec;
        }

        public int getQuantity() {
            return Quantity;
        }

        public void setQuantity(int quantity) {
            Quantity = quantity;
        }

        public int getActualQuantity() {
            return ActualQuantity;
        }

        public void setActualQuantity(int actualQuantity) {
            ActualQuantity = actualQuantity;
        }

        public String getUnit() {
            return Unit;
        }

        public void setUnit(String unit) {
            Unit = unit;
        }

        public String getRemark() {
            return Remark;
        }

        public void setRemark(String remark) {
            Remark = remark;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.Id);
            dest.writeInt(this.GoodsId);
            dest.writeString(this.Goods);
            dest.writeString(this.Spec);
            dest.writeInt(this.Quantity);
            dest.writeInt(this.ActualQuantity);
            dest.writeString(this.Unit);
            dest.writeString(this.Remark);
        }

        public OfficeRecipientsItemModel() {
        }

        protected OfficeRecipientsItemModel(Parcel in) {
            this.Id = in.readInt();
            this.GoodsId = in.readInt();
            this.Goods = in.readString();
            this.Spec = in.readString();
            this.Quantity = in.readInt();
            this.ActualQuantity = in.readInt();
            this.Unit = in.readString();
            this.Remark = in.readString();
        }

        public static final Creator<OfficeRecipientsItemModel> CREATOR = new Creator<OfficeRecipientsItemModel>() {
            public OfficeRecipientsItemModel createFromParcel(Parcel source) {
                return new OfficeRecipientsItemModel(source);
            }

            public OfficeRecipientsItemModel[] newArray(int size) {
                return new OfficeRecipientsItemModel[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.Id);
        dest.writeString(this.OrderNo);
        dest.writeInt(this.DepartmentId);
        dest.writeString(this.Department);
        dest.writeString(this.EmployeeId);
        dest.writeString(this.Employee);
        dest.writeInt(this.MakerId);
        dest.writeString(this.Maker);
        dest.writeString(this.MakeDateStr);
        dest.writeList(this.OfficeRecipientsItem);
    }

    public OfficeRecipientsModel() {
    }

    protected OfficeRecipientsModel(Parcel in) {
        this.Id = in.readInt();
        this.OrderNo = in.readString();
        this.DepartmentId = in.readInt();
        this.Department = in.readString();
        this.EmployeeId = in.readString();
        this.Employee = in.readString();
        this.MakerId = in.readInt();
        this.Maker = in.readString();
        this.MakeDateStr = in.readString();
        this.OfficeRecipientsItem = new ArrayList<OfficeRecipientsItemModel>();
        in.readList(this.OfficeRecipientsItem, List.class.getClassLoader());
    }

    public static final Parcelable.Creator<OfficeRecipientsModel> CREATOR = new Parcelable.Creator<OfficeRecipientsModel>() {
        public OfficeRecipientsModel createFromParcel(Parcel source) {
            return new OfficeRecipientsModel(source);
        }

        public OfficeRecipientsModel[] newArray(int size) {
            return new OfficeRecipientsModel[size];
        }
    };
}
