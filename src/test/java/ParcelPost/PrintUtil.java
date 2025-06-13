package ParcelPost;

import ParcelPost.model.Package;
import ParcelPost.model.TwoDayPackage;
import ParcelPost.model.OvernightPackage;

/**
 * 包裹信息打印工具类
 * 保留原始测试类中的格式化输出功能
 */
public class PrintUtil {
    
    /**
     * 打印运单信息
     * @param p 要打印的包裹对象
     */
    public static void printShippingOrderInfo(Package p) {
        System.out.println("==========================================");
        
        // 判断并打印包裹类型
        String parcelType;
        if (p instanceof TwoDayPackage)
            parcelType = "TwoDayPackage";
        else if (p instanceof OvernightPackage)
            parcelType = "OvernightPackage";
        else
            parcelType = "Package";
        
        System.out.println("[••• PARCEL TYPE •••] " + parcelType);
        System.out.println();

        // 打印寄件人信息
        System.out.println("From: " + p.getSender().getName());
        System.out.println(p.getSender().getAddress() + ", " + p.getSender().getCity() + ", " + p.getSender().getState());
        System.out.println("Tel: " + p.getSender().getPhoneNumber() + " ZIP: " + p.getSender().getPostcode());
        System.out.println();

        // 打印收件人信息
        System.out.println("To: " + p.getRecipient().getName());
        System.out.println(p.getRecipient().getAddress() + ", " + p.getRecipient().getCity() + ", " + p.getRecipient().getState());
        System.out.println("Tel: " + p.getRecipient().getPhoneNumber() + " ZIP: " + p.getRecipient().getPostcode());
        System.out.println();

        // 打印重量和费用
        System.out.printf("WEIGHT: %.2f ounce\n", p.getPackageWeight());
        System.out.printf("PAYMENT: $ %.2f\n", p.calculateCost());
        
        System.out.println("==========================================");
        System.out.println();
    }
    
    /**
     * 打印快递公司总运单数
     */
    public static void printTotalOrderCount() {
        System.out.println("\n快递公司总共产生了 " + Package.getNumberOfShippingOrder() + " 个运单。");
    }
} 