# Log4j2日志[文档待完善]

## JAR依赖
```
<!-- 排除默认log(Logback)依赖 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter</artifactId>
    <exclusions>
        <exclusion>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-logging</artifactId>
        </exclusion>
    </exclusions>
</dependency>
<!-- 引入log4j2依赖 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-log4j2</artifactId>
</dependency>
```
## 常用配置
查看配置文件：spring-boot-note-log\src\main\resources\log4j2.xml

## 使用示例
```
public class LogApplication {

    private static final Logger logger = LoggerFactory.getLogger(LogApplication.class);

    public static void test() {
        SpringApplication.run(LogApplication.class, args);
        logger.debug("I am debug!!!");
        logger.info("I am info!!!");
        logger.warn("I am warn!!!");
        logger.error("I am error!!!");
    }

}
```

## 参考
1. http://logging.apache.org/log4j/2.x/index.html
