@echo off
echo 创建输出目录...
if not exist bin mkdir bin

echo 编译源代码...
for /r src/main/ParcelPost %%f in (*.java) do (
    javac -cp "lib/lombok.jar" -processorpath "lib/lombok.jar" -d bin "%%f"
)

echo 编译完成！ 