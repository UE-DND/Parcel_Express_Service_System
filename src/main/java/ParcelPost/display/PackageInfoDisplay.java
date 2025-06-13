package ParcelPost.display;

import ParcelPost.model.Package;
import ParcelPost.model.TwoDayPackage;
import ParcelPost.model.OvernightPackage;
import ParcelPost.model.ParcelContact;

/**
 * 包裹信息显示类
 * 
 * <p>负责显示包裹相关信息</p>
 * 
 * @version 1.0.0
 * @since 2025-06-10
 */
public class PackageInfoDisplay {
    
    /**
     * 打印运单信息
     */
    public static void printShippingOrderInfo(Package p) {
        try {
            // 判断并打印包裹类型
            String parcelType;
            String typeIcon;
            if (p instanceof TwoDayPackage) {
                parcelType = "隔日达包裹";
                typeIcon = "🕒";
            } else if (p instanceof OvernightPackage) {
                parcelType = "隔夜达包裹";
                typeIcon = "🌙";
            } else {
                parcelType = "普通包裹";
                typeIcon = "📦";
            }
            
            System.out.println();
            System.out.println(MenuDisplay.YELLOW + typeIcon + " " + MenuDisplay.BOLD + "包裹类型: " + MenuDisplay.WHITE + parcelType + MenuDisplay.RESET);
            System.out.println(MenuDisplay.YELLOW + "─────────────────────────────────────────────" + MenuDisplay.RESET);
            
            // 检查发件人信息
            ParcelContact sender = p.getSender();
            if (sender == null) {
                throw new IllegalArgumentException("发件人信息不能为空");
            }
            
            // 打印寄件人信息
            System.out.println(MenuDisplay.GREEN + "📤 " + MenuDisplay.BOLD + "发件人: " + MenuDisplay.WHITE + sender.getName() + MenuDisplay.RESET);
            System.out.println(MenuDisplay.WHITE + "   地址: " + sender.getAddress() + MenuDisplay.RESET);
            System.out.println(MenuDisplay.WHITE + "        " + sender.getCity() + ", " + sender.getState() + MenuDisplay.RESET);
            System.out.println(MenuDisplay.WHITE + "   电话: " + sender.getPhoneNumber() + MenuDisplay.RESET);
            System.out.println(MenuDisplay.WHITE + "   邮编: " + sender.getPostcode() + MenuDisplay.RESET);
            System.out.println(MenuDisplay.YELLOW + "─────────────────────────────────────────────" + MenuDisplay.RESET);
            
            // 检查收件人信息
            ParcelContact recipient = p.getRecipient();
            if (recipient == null) {
                throw new IllegalArgumentException("收件人信息不能为空");
            }
            
            // 打印收件人信息
            System.out.println(MenuDisplay.GREEN + "📥 " + MenuDisplay.BOLD + "收件人: " + MenuDisplay.WHITE + recipient.getName() + MenuDisplay.RESET);
            System.out.println(MenuDisplay.WHITE + "   地址: " + recipient.getAddress() + MenuDisplay.RESET);
            System.out.println(MenuDisplay.WHITE + "        " + recipient.getCity() + ", " + recipient.getState() + MenuDisplay.RESET);
            System.out.println(MenuDisplay.WHITE + "   电话: " + recipient.getPhoneNumber() + MenuDisplay.RESET);
            System.out.println(MenuDisplay.WHITE + "   邮编: " + recipient.getPostcode() + MenuDisplay.RESET);
            System.out.println(MenuDisplay.YELLOW + "─────────────────────────────────────────────" + MenuDisplay.RESET);
            
            // 打印重量和费用
            System.out.println(MenuDisplay.YELLOW + "⚖️ " + MenuDisplay.BOLD + "重量: " + MenuDisplay.WHITE + String.format("%.2f", p.getPackageWeight()) + " 盎司" + MenuDisplay.RESET);
            System.out.println(MenuDisplay.GREEN + "💰 " + MenuDisplay.BOLD + "费用: $ " + MenuDisplay.WHITE + String.format("%.2f", p.calculateCost()) + MenuDisplay.RESET);
            System.out.println();
            
        } catch (IllegalArgumentException e) {
            System.out.println(MenuDisplay.RED + " ⚠️ 打印运单信息错误: " + MenuDisplay.YELLOW + e.getMessage() + MenuDisplay.RESET);
        } catch (Exception e) {
            System.out.println(MenuDisplay.RED + " ⚠️ 打印运单信息时发生未知错误: " + MenuDisplay.YELLOW + e.getMessage() + MenuDisplay.RESET);
            System.out.println(MenuDisplay.RED + " ⚠️ 系统已退出" + MenuDisplay.RESET);
            System.exit(1);
        }
    }
} 