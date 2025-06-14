package ParcelPost;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import ParcelPost.model.ParcelContact;

/**
 * ParcelContact类单元测试
 * 测试 Lombok 能不能用
 * 
 * @version 1.0
 * @since 2025-06-14
 */
public class ParcelContactTest {
    
    @Test
    @DisplayName("测试ParcelContact的getter和setter方法")
    public void testGetterAndSetter() {
        // 创建测试对象
        ParcelContact contact = new ParcelContact(
            "测试包裹", "测试地址", "测试城市",
            "测试州", "测试邮编", "测试电话");
        
        // 测试初始值
        assertEquals("测试包裹", contact.getName());
        assertEquals("测试地址", contact.getAddress());
        assertEquals("测试城市", contact.getCity());
        assertEquals("测试州", contact.getState());
        assertEquals("测试邮编", contact.getPostcode());
        assertEquals("测试电话", contact.getPhoneNumber());
        
        // 测试setter方法
        contact.setName("测试包裹1");
        contact.setAddress("测试地址1");
        
        // 验证修改后的值
        assertEquals("测试包裹1", contact.getName());
        assertEquals("测试地址1", contact.getAddress());
    }
    
    @Test
    @DisplayName("测试ParcelContact的toString方法")
    public void testToString() {
        ParcelContact contact = new ParcelContact(
            "测试包裹", "测试地址", "测试城市",
            "测试州", "测试邮编", "测试电话");
            
        // 修改部分属性
        contact.setName("测试包裹1");
        contact.setAddress("测试地址1");
        
        // 验证toString输出
        String expected = "ParcelContact(name=测试包裹1, address=测试地址1, city=测试城市, state=测试州, postcode=测试邮编, phoneNumber=测试电话)";
        assertEquals(expected, contact.toString());
    }
} 