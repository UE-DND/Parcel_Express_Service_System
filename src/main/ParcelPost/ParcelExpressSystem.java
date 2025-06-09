package ParcelPost;

import ParcelPost.display.*;
import ParcelPost.model.Package;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 包裹快递服务系统交互界面
 * 
 * <p>提供命令行交互界面，允许用户添加包裹、查看包裹信息和统计数据。</p>
 * 
 * @version 1.0.0
 * @since 2025-06-11
 */
public class ParcelExpressSystem {
    
    private static ArrayList<Package> packages = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);  // 这里共享输入流，避免创建多个 Scanner对象
    
    public static void main(String[] args) {
        // 系统是否继续运行
        boolean isRunning = true;
        // 是否显示菜单界面
        boolean showMenu = true;
        // 当前会话的运行次数
        int runtimes = 0;
        // 系统总运行次数
        int totalruntimes = 0;
        
        while (isRunning) {
            try {
                if (showMenu) {
                    MenuDisplay.displayMainMenu();
                    showMenu = false;  // 输入错误不会循环显示菜单
                }
                
                int choice = InputHandler.getUserChoice(scanner);
                switch(choice) {
                    case 1:
                        PackageManager.addPackage(packages, scanner);
                        showMenu = true;
                        break;
                    case 2:
                        PackageManager.displayAllPackages(packages, scanner);
                        showMenu = true;
                        break;
                    case 3:
                        PackageManager.displayTotalOrders(scanner);
                        showMenu = true;
                        break;
                    case 4:
                        PackageManager.addTestCases(packages, scanner);
                        showMenu = true;
                        break;
                    case 5:
                        System.out.println(MenuDisplay.GREEN + " ✓ " + MenuDisplay.YELLOW + "系统已退出" + MenuDisplay.RESET);
                        isRunning = false;
                        break;
                    default:
                        System.out.print(MenuDisplay.RED + " ❌ 无效选择！" + MenuDisplay.YELLOW + "请输入一个有效的数字 " + MenuDisplay.GREEN + MenuDisplay.BOLD + "(1-5)" + MenuDisplay.RESET + MenuDisplay.YELLOW + ": " + MenuDisplay.RESET);
                }
            } catch (Exception e) {
                if (runtimes < 3) {
                    System.out.println(MenuDisplay.RED + " ⚠️ 发生未知错误: " + MenuDisplay.YELLOW + e.getMessage() + MenuDisplay.RESET);
                    System.out.print(MenuDisplay.YELLOW + " 尝试重新输入选择 " + MenuDisplay.GREEN + MenuDisplay.BOLD + "(1-5)" + MenuDisplay.RESET + MenuDisplay.YELLOW + ": " + MenuDisplay.RESET);
                    runtimes++;
                } else if(runtimes >= 3 && totalruntimes >= 2) {  // 共计出现 3次异常或此处连续出现 2次异常后退出
                    System.out.println(MenuDisplay.RED + " ⚠️ 重试次数过多，系统已自动退出" + MenuDisplay.RESET);
                    isRunning = false;
                    scanner.close();
                    System.exit(0);
                }
            }
        }
        
        scanner.close();
    }
}
