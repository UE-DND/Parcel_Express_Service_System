package ParcelPost;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import ParcelPost.model.Package;
import ParcelPost.model.TwoDayPackage;
import ParcelPost.model.OvernightPackage;
import ParcelPost.model.ParcelContact;

/**
 * 使用题目中测试用例
 * 
 * @version 1.0
 * @since 2025-06-14
 */
public class UseTestCases {

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
        System.out.println("快递公司总共产生了 " + Package.getNumberOfShippingOrder() + " 个运单。");
    }

    @Test
    @DisplayName("题目测试用例")
    public void testParcelService() {
        // 重置订单计数器
        Package.resetOrderCount();
        
        // 创建包裹列表
        ArrayList<Package> packages = new ArrayList<>();

        // --- 使用文档中的测试用例 ---
        // 包裹1: 普通包裹
        ParcelContact sender1 = new ParcelContact("LeBron James", "#23 Main St", "Cleveland", "Ohio", "11111", "(+86)139-1111-1111");
        ParcelContact recipient1 = new ParcelContact("Dirk Nowitzki", "#41 Waterview Pkwy", "Dallas", "Texas", "22222", "(+86)139-2222-2222");
        Package package1 = new Package(sender1, recipient1, 3.02);
        packages.add(package1);

        // 包裹2: 隔日送达包裹
        ParcelContact sender2 = new ParcelContact("Kobe Bryant", "#24 Broadway", "Los Angeles", "California", "33333", "(+86)139-3333-3333");
        ParcelContact recipient2 = new ParcelContact("Michael Jordan", "#23 King Rd", "Chicago", "Illinois", "44444", "(+86)139-4444-4444");
        TwoDayPackage package2 = new TwoDayPackage(sender2, recipient2, 12.28, 5.5);
        packages.add(package2);
        
        // 包裹3: 隔夜送达包裹
        ParcelContact sender3 = new ParcelContact("James Harden", "#13 Oak St", "Houston", "Texas", "55555", "(+86)139-5555-5555");
        ParcelContact recipient3 = new ParcelContact("Stephen Curry", "#30 Cambridge Rd", "San Francisco", "California", "66666", "(+86)139-6666-6666");
        OvernightPackage package3 = new OvernightPackage(sender3, recipient3, 11.24, 0.75);
        packages.add(package3);

        System.out.println("\n=============== 题目测试用例 ===============\n");
        
        // 打印信息
        for (Package p : packages) {
            printShippingOrderInfo(p);
        }
        
        // 打印总运单数
        printTotalOrderCount();
        
        System.out.println("\n==========================================\n");
    }
    
    /**
     * 手动运行测试用例
     */
    public static void main(String[] args) {
        // 创建测试实例并手动运行测试
        UseTestCases test = new UseTestCases();
        test.testParcelService();
    }
} 