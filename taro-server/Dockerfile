# 选择一个有JDK的基础镜像
FROM openjdk:11-jre-slim

# 创建一个目录用于存放你的应用
WORKDIR /application

# 将构建产物复制到镜像中的工作目录
COPY taro-server/target/taro-server-0.0.1-SNAPSHOT.jar /application/app.jar

# 暴露应用运行时的端口
EXPOSE 8080

# 指定容器启动时运行的命令
ENTRYPOINT ["java", "-jar", "app.jar"]
