# 文件上传下载
## 设计实现
- WEB浏览器上传、下载
- HTTP上传、下载
- 加密存储

## Spring MultipartResolver两种实现
Spring多文件解析MultipartResolver有两种实现。从Spring3.1开始，Spring包含两个具体的实现
- CommonsMultipartResolver  `Apache Commons FileUpload`
- StandardServletMultipartResolver `Servlet3.0+Part API`

### 默认Servlet3.0+Part API实现上传
查看SpringBoot自动装配spring-boot-autoconfigure下spring.factories，找到自动装配MultipartAutoConfiguration。

看下MultipartAutoConfiguration配置类
```
@Bean( name = {"multipartResolver"})
@ConditionalOnMissingBean({MultipartResolver.class})
public StandardServletMultipartResolver multipartResolver() {
    StandardServletMultipartResolver multipartResolver = new StandardServletMultipartResolver();
    multipartResolver.setResolveLazily(this.multipartProperties.isResolveLazily());
    return multipartResolver;
}
```

从代码中可以看到，如果没有检测到MultipartResolver的任何子实现实例，则通过@Bean方式实例一个StandardServletMultipartResolver。也就是说自动装配没有检测到CommonsMultipartResolver对MultipartResolver的实现则默认采用StandardServletMultipartResolver。

### 切换apache.common.fileupload实现上传
- 引入apache.common.fileupload jar
- 自定义子类实现
```
// 组件ID必须是"multipartResolver"
@Component(value = "multipartResolver")
public class CustomCommonsMultipartResolver extends CommonsMultipartResolver {

    这里可以扩展实现CommonsMultipartResolver相关特性，比如实现进度等。
}
```
这样@RequestParam MultipartFile下的实现变成apache.common.fileupload来实现