@echo off
echo 开始打包流程...

rem 清理并重新创建输出目录
echo 清理目录...
if exist dist rmdir /s /q dist
mkdir dist

rem 首先执行构建脚本
call scripts\build\Windows\build.bat

rem 创建清单文件
echo Main-Class: ParcelPost.ParcelExpressSystem> manifest.txt

rem 创建JAR文件
echo 创建JAR文件...
jar cfm dist/ParcelExpressSystem.jar manifest.txt -C bin .

echo 打包完成！JAR文件位于 dist\ParcelExpressSystem.jar

rem 删除临时文件
del manifest.txt 