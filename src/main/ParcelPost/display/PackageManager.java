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
            
            if (packages.size() == 0) {
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
    
    /**
     * 使用测试用例
     * 
     * @param packages 包裹列表
     * @param scanner 输入流
     */
    public static void addTestCases(ArrayList<Package> packages, Scanner scanner) {
        try {
            MenuDisplay.displayTestCaseMenu();
            
            // 包裹1: 普通包裹
            ParcelContact sender1 = new ParcelContact("LeBron James", "#23 Main St", "Cleveland", "Ohio", "11111", "(+86)139-1111-1111");
            ParcelContact recipient1 = new ParcelContact("Dirk Nowitzki", "#41 Waterview Pkwy", "Dallas", "Texas", "22222", "(+86)139-2222-2222");
            Package package1 = new Package(sender1, recipient1, 3.02);
            packages.add(package1);
            System.out.println(MenuDisplay.GREEN + " ✓ " + MenuDisplay.WHITE + "已添加测试用例 1: 普通包裹" + MenuDisplay.RESET);

            // 包裹2: 隔日达包裹
            ParcelContact sender2 = new ParcelContact("Kobe Bryant", "#24 Broadway", "Los Angeles", "California", "33333", "(+86)139-3333-3333");
            ParcelContact recipient2 = new ParcelContact("Michael Jordan", "#23 King Rd", "Chicago", "Illinois", "44444", "(+86)139-4444-4444");
            TwoDayPackage package2 = new TwoDayPackage(sender2, recipient2, 12.28, 5.5);
            packages.add(package2);
            System.out.println(MenuDisplay.GREEN + " ✓ " + MenuDisplay.WHITE + "已添加测试用例 2: 隔日达包裹" + MenuDisplay.RESET);
            
            // 包裹3: 隔夜达包裹
            ParcelContact sender3 = new ParcelContact("James Harden", "#13 Oak St", "Houston", "Texas", "55555", "(+86)139-5555-5555");
            ParcelContact recipient3 = new ParcelContact("Stephen Curry", "#30 Cambridge Rd", "San Francisco", "California", "66666", "(+86)139-6666-6666");
            OvernightPackage package3 = new OvernightPackage(sender3, recipient3, 11.24, 0.75);
            packages.add(package3);
            System.out.println(MenuDisplay.GREEN + " ✓ " + MenuDisplay.WHITE + "已添加测试用例 3: 隔夜送达包裹" + MenuDisplay.RESET);
            
            System.out.println(MenuDisplay.GREEN + "\n ✓ " + MenuDisplay.YELLOW + MenuDisplay.BOLD + "成功添加所有测试用例！" + MenuDisplay.RESET);
            System.out.print(MenuDisplay.YELLOW + "\n按回车键继续..." + MenuDisplay.RESET);
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println(MenuDisplay.RED + " ⚠️ 添加测试用例时发生未知错误: " + MenuDisplay.YELLOW + e.getMessage() + MenuDisplay.RESET);
            System.out.println(MenuDisplay.RED + " ⚠️ 系统已退出" + MenuDisplay.RESET);
            scanner.close();
            System.exit(1);
        }
    }
}
