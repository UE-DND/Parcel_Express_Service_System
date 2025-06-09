#!/bin/bash

# 清理并重新创建输出目录
rm -rf dist
mkdir -p dist
echo "清理目录..."

# 首先执行构建脚本
scripts/build/Linux/build.sh

# 创建清单文件
echo "Main-Class: ParcelPost.ParcelExpressSystem" > manifest.txt

# 创建JAR文件
echo "创建JAR文件..."
jar cfm dist/ParcelExpressSystem.jar manifest.txt -C bin .

echo "打包完成！JAR文件位于 dist/ParcelExpressSystem.jar" 