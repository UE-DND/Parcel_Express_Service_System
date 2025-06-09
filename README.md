# 《面向对象程序设计》课程项目 - 包裹快递服务系统

[English](README_EN.md) | 中文

## 项目概述
本项目要求设计一个包裹快递服务系统，模拟FedEx、DHL和UPS等快递公司的包裹投递服务。系统使用面向对象的继承体系来表示不同种类的包裹及其邮费计算规则。

## 系统需求

### 继承体系
- **Package**：作为基类，表示普通包裹
- **TwoDayPackage**：Package的子类，表示隔日送达包裹
- **OvernightPackage**：Package的子类，表示隔夜送达/次晨必达包裹

### 类设计要求

#### Package类（普通包裹）
1. 成员变量：
   - 发件人信息（姓名、地址、城市、州/省、邮编、电话）
   - 收件人信息（姓名、地址、城市、州/省、邮编、电话）
   - 包裹重量（单位：盎司）
   - 常量：每盎司的基本运费(COST_PER_OUNCE)为1.16美元
   - **静态变量**：记录快递公司订单总数的变量(numberOfShippingOrder)，每创建一个包裹对象时自动递增

2. 构造方法：
   - 初始化包裹的基本属性，包裹重量必须是正数
   - **错误处理**：如果传入的重量不是正数，应抛出异常或给出错误提示

3. 普通方法：
   - 发件人和收件人属性的访问器和修改器
   - 包裹重量的访问器和修改器（设置重量时需验证是否为正数）
   - getCostPerOunce：获取每盎司的基本运费
   - calculateCost：计算包裹的总运费
     - **计算公式**：`totalCost = weight × COST_PER_OUNCE`

#### TwoDayPackage类（隔日送达包裹）
1. 额外成员变量：
   - flatFee：额外统一费用

2. 构造方法：
   - 初始化包裹基本属性和额外统一费用，要求重量和统一费用必须是正数
   - **错误处理**：如果统一费用不是正数，应抛出异常或给出错误提示

3. 重写方法：
   - calculateCost：在普通包裹邮费基础上加上额外统一费用
     - **计算公式**：`totalCost = weight × COST_PER_OUNCE + flatFee`

#### OvernightPackage类（隔夜送达包裹）
1. 额外成员变量：
   - overnightFeePerOunce：每盎司额外费用

2. 构造方法：
   - 初始化包裹基本属性和每盎司额外费用，要求重量和每盎司额外费用必须是正数
   - **错误处理**：如果每盎司额外费用不是正数，应抛出异常或给出错误提示

3. 重写方法：
   - calculateCost：计算总运费为重量×(基本运费+每盎司额外费用)
     - **计算公式**：`totalCost = weight × (COST_PER_OUNCE + overnightFeePerOunce)`

#### TestParcelPost类（测试类）
1. main方法：测试创建不同类型的包裹
2. printShippingOrderInfo方法：打印运单信息，包括：
   - 运单类型
   - 发件人和收件人信息
   - 包裹重量
   - 包裹的总邮费（精确到小数点后2位）
   - **输出格式**：
     ```
     =======================================
     [••• PARCEL TYPE •••] 包裹类型名称
     
     From: 发件人姓名
     #发件人地址, 发件人城市, 发件人州/省
     Tel:(发件人电话)  ZIP:(发件人邮编)
     ---------------------------------------
     To: 收件人姓名
     #收件人地址, 收件人城市, 收件人州/省
     Tel:(收件人电话)  ZIP:(收件人邮编)
     
     WEIGHT: 重量值 ounce
     PAYMENT: $ 费用值（保留两位小数）
     =======================================
     ```
3. 最后显示快递公司总共产生的运单数量
   - 显示格式：`快递公司总共产生了 XX 个运单。`（其中XX为静态变量numberOfShippingOrder的值）

## 测试用例

### 包裹1: 普通包裹
```java
Package package1 = new Package("LeBron James", "#23 Main St", "Cleveland", 
"Ohio", 11111, "(+86)139-1111-1111", "Dirk Nowitzki", "#41 Waterview Pkwy", 
"Dallas", "Texas", 22222, "(+86)139-2222-2222", 3.02);
```

### 包裹2: 隔日送达包裹
```java
TwoDayPackage package2 = new TwoDayPackage("Kobe Bryant", "#24 Broadway", 
"Los Angeles", "California", 33333, "(+86)139-3333-3333", "Michael Jordan", 
"#23 King Rd", "Chicago", "Illinois", 44444, "(+86)139-4444-4444", 12.28, 5.5);
```

### 包裹3: 隔夜送达包裹
```java
OvernightPackage package3 = new OvernightPackage("James Harden", "#13 Oak St", 
"Houston", "Texas", 55555, "(+86)139-5555-5555", "Stephen Curry", 
"#30 Cambridge Rd", "San Francisco", "California", 66666, "(+86)139-6666-6666", 
11.24, 0.75);
```

## 项目结构
- 包名：ParcelPost
- 类名：
  - Package.java
  - TwoDayPackage.java
  - OvernightPackage.java
  - TestParcelPost.java