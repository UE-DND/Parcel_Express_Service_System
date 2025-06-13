package ParcelPost;

import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import ParcelPost.model.Package;
import ParcelPost.model.TwoDayPackage;
import ParcelPost.model.OvernightPackage;
import ParcelPost.model.ParcelContact;

/**
 * 包裹服务综合测试类
 * 结合了单元测试与原有的格式化输出功能
 * <p>
 */
public class ParcelServiceTest {

    @Test
    @DisplayName("包裹服务综合测试")
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

        System.out.println("\n============ 包裹服务综合测试 ============\n");
        
        // 使用PrintUtil打印信息
        for (Package p : packages) {
            PrintUtil.printShippingOrderInfo(p);
        }
        
        // 打印总运单数
        PrintUtil.printTotalOrderCount();
        
        System.out.println("\n======================================\n");
    }
    
    /**
     * 手动运行方法
     */
    public static void main(String[] args) {
        // 创建测试实例并手动运行测试
        ParcelServiceTest test = new ParcelServiceTest();
        test.testParcelService();
    }
} 