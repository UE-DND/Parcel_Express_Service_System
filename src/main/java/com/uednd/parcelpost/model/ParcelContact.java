package com.uednd.parcelpost.model;
import lombok.*;

/**
 * 包裹收、发件人的信息
 * 
 * <p>包含包裹收、发件人的信息，例如姓名、住址、城市等</p>
 * 
 * @version 1.0.0
 * @since 2025-06-10
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParcelContact {
    /**
     * 姓名
     */
    private String name;

    /**
     * 地址
     */
    private String address;
    private String city;
    private String state;
    private String postcode;

    /**
     * 电话
     */
    private String phoneNumber;

}
