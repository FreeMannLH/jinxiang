package ideatc.jinxiang.constans;

/**
 * Created by Ccb on 2015/8/3.
 */
public class Constants {
    /**
     * 客户flag标志位,潜在客户或者是正式客户
     */
//    public static final int FLAG_CUSTOMER_NOMAL = 0x001;
//    public static final int FLAG_CUSTOMER_UNNOMAL = 0x002;

    public static String[] category = {"办理事项", "电子公告", "客户管理", "通讯录"};


    public static String getSex(boolean sex) {
        return sex == true ? "男" : "女";
    }

    public static String[] matters_daiban_category = {"物品管理", "人力资源", "派车管理", "快递管理", "公章管理", "业务管理"};

    //客户查询相关
    /**
     * 潜在级别
     */
    public static String[] PotentialLevel = {"即将签订合同", "有意向需长期跟踪", "近期内可以签单", "营销失败客户"};
    /**
     * 客户等级
     */
    public static String[] CustomerLevel = {"大客户", "中客户", "小客户"};

    /**
     * 客户来源
     */
    public static String[] CustomerResource = {"电话来访", "客户介绍", "独立开发", "公开招标", "其他"};
    /**
     * 所属片区
     */
    public static String[] CustomerArea = {"丰泽区", "鲤城区", "洛江区", "泉港区", "晋江市", "南安市", "石狮市", "惠安县", "安溪县", "永春县", "德化县"};
    /**
     * 客户行业
     */
    public static String[] CustomerIndustry = {"农、林、牧、渔业", "制造业", "信息传输、计算机服务业和软件业", "住宿和餐饮业", "金融业", "批发和零售业", "非金属矿物制品业", "设备制造业", "其他"};
    //客户查询相关

}
