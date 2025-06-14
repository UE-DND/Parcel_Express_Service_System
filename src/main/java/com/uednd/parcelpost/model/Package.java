package com.uednd.parcelpost.model;
import lombok.*;

/**
 * 普通包裹基类
 * 
 * <p>包含所有包裹类型共有的基本属性，例如发件人信息、收件人信息和包裹重量。</p>
 * <p>定义了计算普通包裹邮费的基本方法。</p>
 * 
 * @version 1.0.0
 * @since 2025-06-10
 */
@Data
@NoArgsConstructor
public class Package {
    /**
     * 包裹收发件人信息
     */
    private ParcelContact sender;
    private ParcelContact recipient;

    /**
     * 包裹自身信息
     */
    private double packageWeight;  // 重量

    /**
     * 包裹基本运费
     */
    @Getter
    public static final double COST_PER_OUNCE = 1.16;

    /**
     * 公司订单总数
     */
    @Getter
    private static int numberOfShippingOrder = 0;

    /**
     * 初始化一个新的包裹对象
     * 
     * <p>此构造方法会验证包裹重量，并自动增加系统中的总运单数量。</p>
     *
     * @param sender        寄件人信息。
     * @param recipient     收件人信息。
     * @param packageWeight 包裹的重量（以盎司为单位）。
     * @throws IllegalArgumentException 非法参数异常：如果包裹重量不是正数。
     */
    public Package(ParcelContact sender, ParcelContact recipient, double packageWeight) {
        if(packageWeight <= 0)
            throw new IllegalArgumentException("包裹信息错误：包裹重量不能为负数");

        this.sender = sender;
        this.recipient = recipient;
        this.packageWeight = packageWeight;

        numberOfShippingOrder++;
    }

    /**
     * 计算包裹的总运费
     * <p>
     * 运费的计算公式为：包裹重量 × 每盎司的基础运费。
     * 
     * @return 包裹的总运费。
     */
    public double calculateCost() {
        return this.packageWeight * COST_PER_OUNCE;
    }

    /**
     * 重置订单计数器
     * <p>
     * 此方法主要用于测试目的，用于重置静态计数器，防止进行 Maven test 时计数器在内存中累计。
     */
    public static void resetOrderCount() {
        numberOfShippingOrder = 0;
    }

}
