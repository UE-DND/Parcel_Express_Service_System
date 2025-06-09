#!/bin/bash

# 创建输出目录
mkdir -p bin

# 编译源代码
echo "编译源代码..."
javac -cp "lib/lombok.jar" -processorpath "lib/lombok.jar" -d bin $(find src/main/ParcelPost -name "*.java")
echo "编译完成"