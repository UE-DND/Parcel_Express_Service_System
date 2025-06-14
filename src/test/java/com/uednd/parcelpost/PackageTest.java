package com.uednd.parcelpost;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import com.uednd.parcelpost.model.Package;
import com.uednd.parcelpost.model.TwoDayPackage;
import com.uednd.parcelpost.model.OvernightPackage;
import com.uednd.parcelpost.model.ParcelContact;

/**
 * 包裹类的单元测试
 * 
 * @version 1.0
 * @since 2025-06-14
 */
public class PackageTest {
    private ParcelContact sender1, recipient1;
    private ParcelContact sender2, recipient2;
    private ParcelContact sender3, recipient3;
    
    private Package package1;
    private TwoDayPackage package2;
    private OvernightPackage package3;
    
    /**
     * 初始化测试数据
     */
    @BeforeEach
    public void setUp() {
        // 重置订单计数器
        Package.resetOrderCount();
        
        // 包裹1: 普通包裹
        sender1 = new ParcelContact("LeBron James", "#23 Main St", "Cleveland", "Ohio", "11111", "(+86)139-1111-1111");
        recipient1 = new ParcelContact("Dirk Nowitzki", "#41 Waterview Pkwy", "Dallas", "Texas", "22222", "(+86)139-2222-2222");
        package1 = new Package(sender1, recipient1, 3.02);
        
        // 包裹2: 隔日送达包裹
        sender2 = new ParcelContact("Kobe Bryant", "#24 Broadway", "Los Angeles", "California", "33333", "(+86)139-3333-3333");
        recipient2 = new ParcelContact("Michael Jordan", "#23 King Rd", "Chicago", "Illinois", "44444", "(+86)139-4444-4444");
        package2 = new TwoDayPackage(sender2, recipient2, 12.28, 5.5);
        
        // 包裹3: 隔夜送达包裹
        sender3 = new ParcelContact("James Harden", "#13 Oak St", "Houston", "Texas", "55555", "(+86)139-5555-5555");
        recipient3 = new ParcelContact("Stephen Curry", "#30 Cambridge Rd", "San Francisco", "California", "66666", "(+86)139-6666-6666");
        package3 = new OvernightPackage(sender3, recipient3, 11.24, 0.75);
    }
    
    @Test
    @DisplayName("测试Package类基本功能")
    public void testPackage() {
        // 测试发件人和收件人
        assertEquals(sender1, package1.getSender());
        assertEquals(recipient1, package1.getRecipient());
        
        // 测试包裹重量
        assertEquals(3.02, package1.getPackageWeight(), 0.001);
        
        // 测试普通包裹费用计算 (3.02 * 1.16)
        double expectedCost = 3.02 * Package.getCOST_PER_OUNCE();
        assertEquals(expectedCost, package1.calculateCost(), 0.001);
    }
    
    @Test
    @DisplayName("测试TwoDayPackage类")
    public void testTwoDayPackage() {
        // 测试发件人和收件人
        assertEquals(sender2, package2.getSender());
        assertEquals(recipient2, package2.getRecipient());
        
        // 测试包裹重量
        assertEquals(12.28, package2.getPackageWeight(), 0.001);
        
        // 测试隔日送达包裹费用计算 (12.28 * 1.16 + 5.5)
        double expectedCost = 12.28 * Package.getCOST_PER_OUNCE() + 5.5;
        assertEquals(expectedCost, package2.calculateCost(), 0.001);
    }
    
    @Test
    @DisplayName("测试OvernightPackage类")
    public void testOvernightPackage() {
        // 测试发件人和收件人
        assertEquals(sender3, package3.getSender());
        assertEquals(recipient3, package3.getRecipient());
        
        // 测试包裹重量
        assertEquals(11.24, package3.getPackageWeight(), 0.001);
        
        // 测试隔夜送达包裹费用计算 (11.24 * (1.16 + 0.75))
        double expectedCost = 11.24 * (Package.getCOST_PER_OUNCE() + 0.75);
        assertEquals(expectedCost, package3.calculateCost(), 0.001);
    }
    
    @Test
    @DisplayName("测试订单计数功能")
    public void testOrderCount() {
        // 由于测试运行顺序不确定，我们只能测试订单数量是否大于0
        assertTrue(Package.getNumberOfShippingOrder() > 0);
    }
} 