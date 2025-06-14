package com.uednd.parcelpost.display;

/**
 * 菜单显示类
 * 
 * <p>负责系统中各种菜单的显示，包括主菜单、添加包裹菜单、测试用例菜单等。<p/>
 * 
 * @version 1.0.0
 * @since 2025-06-10
 */
public class MenuDisplay {
    // ANSI颜色代码
    public static final String RESET = "\u001B[0m";  // 重置颜色
    public static final String BOLD = "\u001B[1m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";
    
    /**
     * 主菜单
     */
    public static void displayMainMenu() {
        clearScreen();
        
        System.out.println(CYAN + "━━━━━━━━━━━━━━━━━━━━ " + YELLOW + BOLD + "快递服务系统" + RESET + CYAN + " ━━━━━━━━━━━━━━━━━━━━" + RESET);
        System.out.println();
        System.out.println("  " + YELLOW + "[" + GREEN + "1" + YELLOW + "]" + WHITE + " 📦 添加新包裹");
        System.out.println("  " + YELLOW + "[" + GREEN + "2" + YELLOW + "]" + WHITE + " 📋 查看所有包裹信息");
        System.out.println("  " + YELLOW + "[" + GREEN + "3" + YELLOW + "]" + WHITE + " 📊 查看订单总数");
        System.out.println("  " + YELLOW + "[" + GREEN + "4" + YELLOW + "]" + WHITE + " 🚪 退出系统");
        System.out.println();
        System.out.println(CYAN + "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" + RESET);
        System.out.println();
        System.out.print(YELLOW + " 请选择操作 " + GREEN + BOLD + "(1-4)" + RESET + YELLOW + ": " + RESET);
    }
    
    /**
     * 添加包裹菜单
     */
    public static void displayAddPackageMenu() {
        System.out.println();
        System.out.println(CYAN + "━━━━━━━━━━━━━━━━━━━━ " + YELLOW + BOLD + "添加新包裹" + RESET + CYAN + " ━━━━━━━━━━━━━━━━━━━━" + RESET);
        System.out.println();
        System.out.println("  " + WHITE + BOLD + "请选择包裹类型:");
        System.out.println();
        System.out.println("  " + YELLOW + "[" + GREEN + "1" + YELLOW + "]" + WHITE + " 📦 普通包裹");
        System.out.println("  " + YELLOW + "[" + GREEN + "2" + YELLOW + "]" + WHITE + " 🕒 隔日达");
        System.out.println("  " + YELLOW + "[" + GREEN + "3" + YELLOW + "]" + WHITE + " 🌙 隔夜达");
        System.out.println();
        System.out.println(CYAN + "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" + RESET);
        System.out.println();
        System.out.print(YELLOW + " 请选择 " + GREEN + BOLD + "(1-3)" + RESET + YELLOW + ": " + RESET);
    }
    

    
    /**
     * 包裹列表标题
     * 
     * @param packageCount 包裹数量
     */
    public static void displayPackageListHeader(int packageCount) {
        System.out.println();
        System.out.println(CYAN + "━━━━━━━━━━━━━━━━━━━ " + YELLOW + BOLD + "包裹信息列表" + RESET + CYAN + " ━━━━━━━━━━━━━━━━━━━" + RESET);
        
        if (packageCount == 0) {
            displayEmptyPackageList();
        } else {
            displayNonEmptyPackageList(packageCount);
        }
    }
    
    /**
     * 显示空包裹列表
     */
    private static void displayEmptyPackageList() {
        System.out.println();
        System.out.println("  " + YELLOW + "⚠️ 当前没有包裹记录！");
        System.out.println();
        System.out.println(CYAN + "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" + RESET);
        System.out.println();
        System.out.print(YELLOW + " 按回车键继续..." + RESET);
    }
    
    /**
     * 显示非空包裹列表
     * 
     * @param packageCount 包裹数量
     */
    private static void displayNonEmptyPackageList(int packageCount) {
        System.out.println();
        System.out.println("  " + GREEN + "📦 当前共有 " + WHITE + BOLD + packageCount + GREEN + " 个包裹");
        System.out.println();
        System.out.println(CYAN + "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" + RESET);
        System.out.println();
    }
    
    /**
     * 订单统计信息
     * 
     * @param orderCount 订单总数量
     */
    public static void displayOrderStatistics(int orderCount) {
        System.out.println();
        System.out.println(CYAN + "━━━━━━━━━━━━━━━━━━━━━ " + YELLOW + BOLD + "订单统计" + RESET + CYAN + " ━━━━━━━━━━━━━━━━━━━━" + RESET);
        System.out.println();
        System.out.println("  " + GREEN + "📊 总共产生了 " + WHITE + BOLD + orderCount + GREEN + " 个运单");
        System.out.println();
        System.out.println(CYAN + "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━" + RESET);
        System.out.println();
    }
    
    /**
     * 清空控制台（但是真的清除了吗）
     */
    public static void clearScreen() {
        // 打印 100个空行
        for (int i = 0; i < 100; i++) {
            System.out.println();
        }
        
        System.out.print("\033[H");  // 将光标移到左上角后打印菜单
        System.out.flush();  // 强制输出
    }
}
