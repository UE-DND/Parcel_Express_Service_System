package com.uednd.parcelpost.display;

import com.uednd.parcelpost.model.ParcelContact;
import java.util.Scanner;

/**
 * 输入处理类
 * 
 * <p>负责处理用户输入</p>
 * 
 * @version 1.0.0
 * @since 2025-06-10
 */
public class InputHandler {
    
    /**
     * 获取用户菜单选择
     * 
     * @param scanner 输入流
     * @return 用户选择
     */
    public static int getUserChoice(Scanner scanner) {
        int choice = 0;
        boolean validInput = false;
        
        while (!validInput) {
            try {
                String input = scanner.nextLine();
                choice = Integer.parseInt(input);  // 字符串转整数
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.print(MenuDisplay.RED + " ❌ 输入无效！" + MenuDisplay.YELLOW + "请输入一个有效的数字 " + MenuDisplay.GREEN + MenuDisplay.BOLD + "(1-5)" + MenuDisplay.RESET + MenuDisplay.YELLOW + ": " + MenuDisplay.RESET);
            }
        }
        
        return choice;
    }
    
    /**
     * 获取联系人信息
     * 
     * @param scanner 输入流
     * @return 联系人信息
     */
    public static ParcelContact getContactInfo(Scanner scanner) {
        String name = "";
        String address = "";
        String city = "";
        String state = "";
        String postcode = "";
        String phone = "";
        
        // 获取姓名
        name = getValidInput(scanner, "姓名", "姓名不能为空");
        
        // 获取地址
        address = getValidInput(scanner, "地址", "地址不能为空");
        
        // 获取城市
        city = getValidInput(scanner, "城市", "城市不能为空");
        
        // 获取州/省
        state = getValidInput(scanner, "州/省", "州/省不能为空");
        
        // 获取邮编
        postcode = getValidInput(scanner, "邮编", "邮编不能为空");
        
        // 获取电话
        phone = getValidInput(scanner, "电话", "电话不能为空");
        
        return new ParcelContact(name, address, city, state, postcode, phone);
    }
    
    /**
     * 获取有效输入
     * 
     * @param scanner 输入流
     * @param fieldName 用户输入
     * @param errorMessage 错误消息
     * @return 有效的输入字符串
     */
    private static String getValidInput(Scanner scanner, String fieldName, String errorMessage) {
        String input = "";
        boolean validInput = false;
        
        while (!validInput) {
            try {
                System.out.print(MenuDisplay.YELLOW + " " + fieldName + ": " + MenuDisplay.RESET);
                input = scanner.nextLine();
                // 删除空白字符后为空
                if (input.trim().isEmpty()) {
                    throw new IllegalArgumentException(errorMessage);
                }
                
                validInput = true;
            } catch (IllegalArgumentException e) {
                System.out.println(MenuDisplay.RED + " ⚠️ 错误: " + MenuDisplay.YELLOW + e.getMessage() + MenuDisplay.RESET);
            } catch (Exception e) {
                System.out.println(MenuDisplay.RED + " ⚠️ 发生未知错误: " + MenuDisplay.YELLOW + e.getMessage() + MenuDisplay.RESET);
                System.out.println(MenuDisplay.RED + " ⚠️ 系统已退出" + MenuDisplay.RESET);
                scanner.close();
                System.exit(1);
            }
        }
        
        return input;
    }
    
    /**
     * 获取正浮点数输入
     * 
     * @param scanner 输入流
     * @param prompt 提示信息
     * @return 正浮点数
     */
    public static double getPositiveDoubleInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(MenuDisplay.YELLOW + " " + prompt + MenuDisplay.RESET);
            try {
                String input = scanner.nextLine();
                double value = Double.parseDouble(input);
                if (value <= 0) {
                    System.out.println(MenuDisplay.RED + " ⚠️ 请输入大于0的正数值！" + MenuDisplay.RESET);
                    continue;
                }
                return value;
            } catch (NumberFormatException e) {
                System.out.println(MenuDisplay.RED + " ⚠️ 输入无效，请输入一个有效的数字！" + MenuDisplay.RESET);
            } catch (Exception e) {
                System.out.println(MenuDisplay.RED + " ⚠️ 发生未知错误: " + MenuDisplay.YELLOW + e.getMessage() + MenuDisplay.RESET);
                System.out.println(MenuDisplay.RED + " ⚠️ 系统已退出" + MenuDisplay.RESET);
                scanner.close();
                System.exit(1);
            }
        }
    }
}
