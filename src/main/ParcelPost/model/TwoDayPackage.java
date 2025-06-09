package ParcelPost.model;
import lombok.*;

/**
 * 隔日达包裹
 * 
 * <p>包含普通包裹的所有基本属性。</p>
 * <p>定义了计算隔日达包裹邮费的基本方法。</p>
 * 
 * @version 1.0.0
 * @since 2025-06-10
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TwoDayPackage extends Package{
    /**
     * 额外统一费用
     */
    private double flatFlee;

    /**
     * 构造函数
     * 
     * @param sender 寄件人信息
     * @param recipient 收件人信息
     * @param packageWeight 包裹重量
     * @param flatFlee 额外统一费用
     */
    public TwoDayPackage(ParcelContact sender, ParcelContact recipient, double packageWeight, double flatFlee) {
        super(sender, recipient, packageWeight);

        if(packageWeight <= 0)
            throw new IllegalArgumentException("包裹信息错误：包裹重量不能为负数");
        else if(flatFlee <= 0)
            throw new IllegalArgumentException("包裹信息错误：额外统一费用不能为负数");
        else if(packageWeight <= 0 && flatFlee <= 0)
            throw new IllegalArgumentException("包裹信息错误：包裹重量和额外统一费用不能为负数");

        this.flatFlee = flatFlee;
    }

    /**
     * 计算隔日达包裹邮费
     * 
     * @return 隔日达包裹邮费
     */
    @Override
    public double calculateCost() {
        return super.calculateCost() + flatFlee;
    }

}
