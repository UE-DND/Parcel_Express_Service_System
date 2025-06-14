package ParcelPost.display;

import ParcelPost.model.Package;
import ParcelPost.model.TwoDayPackage;
import ParcelPost.model.OvernightPackage;
import ParcelPost.model.ParcelContact;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 包裹管理类
 * 
 * <p>负责管理包裹的添加、显示等功能。</p>
 * 
 * @version 1.0.0
 * @since 2025-06-10
 */
public class PackageManager {
    
    /**
     * 添加包裹
     * 
     * @param packages 包裹列表
     * @param scanner 输入流
     */
    public static void addPackage(ArrayList<Package> packages, Scanner scanner) {
        MenuDisplay.displayAddPackageMenu();  // 显示包裹菜单
        
        int packageType = InputHandler.getUserChoice(scanner);  // 包裹菜单->获取包裹类型
        if (packageType < 1 || packageType > 3) {
            System.out.println(MenuDisplay.RED + " ⚠️ 无效的包裹类型，返回主菜单！" + MenuDisplay.RESET);
            return;
        }
        
        // 获取发件人信息
        System.out.println("\n请输入发件人信息:");
        ParcelContact sender = InputHandler.getContactInfo(scanner);
        
        // 获取收件人信息
        System.out.println("\n请输入收件人信息:");
        ParcelContact recipient = InputHandler.getContactInfo(scanner);
        
        // 获取包裹重量
        double weight = InputHandler.getPositiveDoubleInput(scanner, "请输入包裹重量(盎司): ");
        
        try {
            Package newPackage = null;  // 提出来避免作用域问题和重复创建对象，反正只能指向三个中的某一个类型

            switch(packageType) {
                case 1: // 普通包裹
                    newPackage = new Package(sender, recipient, weight);
                    break;
                    
                case 2: // 隔日达包裹
                    double flatFee = InputHandler.getPositiveDoubleInput(scanner, "请输入额外统一费用: ");
                    newPackage = new TwoDayPackage(sender, recipient, weight, flatFee);
                    break;
                    
                case 3: // 隔夜达包裹
                    double overnightFee = InputHandler.getPositiveDoubleInput(scanner, "请输入每盎司额外费用: ");
                    newPackage = new OvernightPackage(sender, recipient, weight, overnightFee);
                    break;
            }

            packages.add(newPackage);  // 写入包裹列表

            MenuDisplay.clearScreen();
            System.out.println(MenuDisplay.GREEN + " ✓ " + MenuDisplay.WHITE + MenuDisplay.BOLD + "包裹添加成功！" + MenuDisplay.RESET);
            // 显示新添加的包裹信息
            PackageInfoDisplay.printShippingOrderInfo(newPackage);

            // 提示用户按回车键返回菜单
            System.out.print(MenuDisplay.YELLOW + " 按回车键返回菜单..." + MenuDisplay.RESET);
            scanner.nextLine();
            
        } catch (IllegalArgumentException e) {
            System.out.println("错误: " + e.getMessage());
        }
    }
    
    /**
     * 显示所有包裹信息
     * 
     * @param packages 包裹列表
     * @param scanner 输入流
     */
    public static void displayAllPackages(ArrayList<Package> packages, Scanner scanner) {
        try {
            MenuDisplay.displayPackageListHeader(packages.size());
            
            if (packages.isEmpty()) {
                scanner.nextLine();
                return;
            }
            
            for (int i = 0; i < packages.size(); i++) {
                try {
                    Package p = packages.get(i);  // 获取单个包裹对象
                    // 防止列表越界
                    if (p == null) {
                        System.out.println(MenuDisplay.RED + "\n包裹 #" + (i + 1) + ": 无效包裹数据" + MenuDisplay.RESET);
                        continue;
                    }
                    System.out.println(MenuDisplay.YELLOW + MenuDisplay.BOLD + "\n📦 包裹 #" + (i + 1) + MenuDisplay.RESET);
                    PackageInfoDisplay.printShippingOrderInfo(p);  // 打印单个包裹信息
                } catch (Exception e) {
                    System.out.println(MenuDisplay.RED + "\n⚠️ 处理包裹 #" + (i + 1) + " 时发生未知错误: " + MenuDisplay.YELLOW + e.getMessage() + MenuDisplay.RESET);
                    System.out.println(MenuDisplay.RED + " ⚠️ 系统已退出" + MenuDisplay.RESET);
                    scanner.close();
                    System.exit(1);
                }
            }
            System.out.print(MenuDisplay.YELLOW + "\n按回车键继续..." + MenuDisplay.RESET);
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println(MenuDisplay.RED + " ⚠️ 发生未知错误: " + MenuDisplay.YELLOW + e.getMessage() + MenuDisplay.RESET);
            System.out.println(MenuDisplay.RED + " ⚠️ 系统已退出" + MenuDisplay.RESET);
            scanner.close();
            System.exit(1);
        }
    }
    
    /**
     * 显示订单总数
     * 
     * @param scanner 输入流
     */
    public static void displayTotalOrders(Scanner scanner) {
        try {
            MenuDisplay.displayOrderStatistics(Package.getNumberOfShippingOrder());
            
            System.out.print(MenuDisplay.YELLOW + " 按回车键继续..." + MenuDisplay.RESET);
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println(MenuDisplay.RED + " ⚠️ 获取订单总数时发生未知错误: " + MenuDisplay.YELLOW + e.getMessage() + MenuDisplay.RESET);
            System.out.println(MenuDisplay.RED + " ⚠️ 系统已退出" + MenuDisplay.RESET);
            scanner.close();
            System.exit(1);
        }
    }
    

}
