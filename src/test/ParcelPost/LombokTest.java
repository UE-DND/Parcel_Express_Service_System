package ParcelPost;

import ParcelPost.model.ParcelContact;

/**
 * 测试一下 Lombok能不能用
 */
public class LombokTest {
    public static void main(String[] args) {
        ParcelContact test = new ParcelContact(
            "测试包裹","测试地址","测试城市",
            "测试州","测试邮编","测试电话");

        test.setName("测试包裹1");
        test.setAddress("测试地址1");

        System.out.println(test.getName());
        System.out.println(test.getAddress());
        System.out.println(test.toString());
    }
}

/**
 * Output:
 * 测试包裹1
 * 测试地址1
 * ParcelContact(name=测试包裹1, address=测试地址1, city=测试城市, state=测试州, postcode=测试邮编, phone=测试电话)
 */