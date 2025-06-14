package com.uednd.parcelpost.model;

import lombok.*;

/**
 * 次晨必达包裹
 * 
 * <p>包含普通包裹的所有基本属性。</p>
 * <p>定义了计算次晨必达包裹邮费的基本方法。</p>
 * 
 * @version 1.0.0
 * @since 2025-06-10
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OvernightPackage extends Package{
    /**
     * 额外统一费用
     */
    private double overnightFeePerOunce;

    /**
     * 构造函数
     * 
     * @param sender 寄件人信息
     * @param recipient 收件人信息
     * @param packageWeight 包裹重量
     * @param overnightFeePerOunce 次晨必达包裹邮费
     */
    public OvernightPackage(ParcelContact sender, ParcelContact recipient, double packageWeight, double overnightFeePerOunce) {
        super(sender, recipient, packageWeight);

        if(packageWeight <= 0)
            throw new IllegalArgumentException("包裹信息错误：包裹重量不能为负数");
        else if(overnightFeePerOunce <= 0)
            throw new IllegalArgumentException("包裹信息错误：次晨必达包裹邮费不能为负数");

        this.overnightFeePerOunce = overnightFeePerOunce;
    }

    /**
     * 计算次晨必达包裹邮费
     * 
     * @return 次晨必达包裹邮费
     */
    @Override
    public double calculateCost() {
        return this.getPackageWeight() * (COST_PER_OUNCE + this.overnightFeePerOunce);
    }
    
}
