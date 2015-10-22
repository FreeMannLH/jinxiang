package ideatc.jinxiang.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ccb on 2015/8/14.
 * 物品采购模型
 */
public class PurchaseOrderModel implements Parcelable {
   private int Id;
    private String OrderNo;
    private String Remark;
    private String Employee;
    private String EmployeeId;
    private String MakerId;
    private BigDecimal TotalMoney;
    private int TotalQuantity;
    private String MakeDateStr;
    private List<PurchaseOrderItemModel> PurchaseOrderItem;

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

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
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

    public String getMakerId() {
        return MakerId;
    }

    public void setMakerId(String makerId) {
        MakerId = makerId;
    }

    public BigDecimal getTotalMoney() {
        return TotalMoney;
    }

    public void setTotalMoney(BigDecimal totalMoney) {
        TotalMoney = totalMoney;
    }

    public int getTotalQuantity() {
        return TotalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        TotalQuantity = totalQuantity;
    }

    public String getMakeDateStr() {
        return MakeDateStr;
    }

    public void setMakeDateStr(String makeDateStr) {
        MakeDateStr = makeDateStr;
    }

    public List<PurchaseOrderItemModel> getList() {
        return PurchaseOrderItem;
    }

    public void setList(List<PurchaseOrderItemModel> list) {
        this.PurchaseOrderItem = list;
    }

    /**
     * 采购物品列表模型
     */
    public static class PurchaseOrderItemModel implements Parcelable {
        private  int Id;
        private  int GoodsId;
        private  String GoodsName;
        private  String GoodsSpec;
        private  int Quantity;
        private  String GoodsUnit;
        private  String Remark;
        private  BigDecimal Price;
        private  BigDecimal TotalMoney;

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

        public String getGoodsName() {
            return GoodsName;
        }

        public void setGoodsName(String goodsName) {
            GoodsName = goodsName;
        }

        public String getGoodsSpec() {
            return GoodsSpec;
        }

        public void setGoodsSpec(String goodsSpec) {
            GoodsSpec = goodsSpec;
        }

        public int getQuantity() {
            return Quantity;
        }

        public void setQuantity(int quantity) {
            Quantity = quantity;
        }

        public String getGoodsUnit() {
            return GoodsUnit;
        }

        public void setGoodsUnit(String goodsUnit) {
            GoodsUnit = goodsUnit;
        }

        public String getRemark() {
            return Remark;
        }

        public void setRemark(String remark) {
            Remark = remark;
        }

        public BigDecimal getPrice() {
            return Price;
        }

        public void setPrice(BigDecimal price) {
            Price = price;
        }

        public BigDecimal getTotalMoney() {
            return TotalMoney;
        }

        public void setTotalMoney(BigDecimal totalMoney) {
            TotalMoney = totalMoney;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.Id);
            dest.writeInt(this.GoodsId);
            dest.writeString(this.GoodsName);
            dest.writeString(this.GoodsSpec);
            dest.writeInt(this.Quantity);
            dest.writeString(this.GoodsUnit);
            dest.writeString(this.Remark);
            dest.writeSerializable(this.Price);
            dest.writeSerializable(this.TotalMoney);
        }

        public PurchaseOrderItemModel() {
        }

        protected PurchaseOrderItemModel(Parcel in) {
            this.Id = in.readInt();
            this.GoodsId = in.readInt();
            this.GoodsName = in.readString();
            this.GoodsSpec = in.readString();
            this.Quantity = in.readInt();
            this.GoodsUnit = in.readString();
            this.Remark = in.readString();
            this.Price = (BigDecimal) in.readSerializable();
            this.TotalMoney = (BigDecimal) in.readSerializable();
        }

        public static final Creator<PurchaseOrderItemModel> CREATOR = new Creator<PurchaseOrderItemModel>() {
            public PurchaseOrderItemModel createFromParcel(Parcel source) {
                return new PurchaseOrderItemModel(source);
            }

            public PurchaseOrderItemModel[] newArray(int size) {
                return new PurchaseOrderItemModel[size];
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
        dest.writeString(this.Remark);
        dest.writeString(this.Employee);
        dest.writeString(this.EmployeeId);
        dest.writeString(this.MakerId);
        dest.writeSerializable(this.TotalMoney);
        dest.writeInt(this.TotalQuantity);
        dest.writeString(this.MakeDateStr);
        dest.writeList(this.PurchaseOrderItem);
    }

    public PurchaseOrderModel() {
    }

    protected PurchaseOrderModel(Parcel in) {
        this.Id = in.readInt();
        this.OrderNo = in.readString();
        this.Remark = in.readString();
        this.Employee = in.readString();
        this.EmployeeId = in.readString();
        this.MakerId = in.readString();
        this.TotalMoney = (BigDecimal) in.readSerializable();
        this.TotalQuantity = in.readInt();
        this.MakeDateStr = in.readString();
        this.PurchaseOrderItem = new ArrayList<>();
        in.readList(this.PurchaseOrderItem, List.class.getClassLoader());
    }

    public static final Parcelable.Creator<PurchaseOrderModel> CREATOR = new Parcelable.Creator<PurchaseOrderModel>() {
        public PurchaseOrderModel createFromParcel(Parcel source) {
            return new PurchaseOrderModel(source);
        }

        public PurchaseOrderModel[] newArray(int size) {
            return new PurchaseOrderModel[size];
        }
    };
}
