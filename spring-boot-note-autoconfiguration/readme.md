# SpringBoot实现自定义自动装配

## 注册自动装配实现
使用spring.factories来实现自动装配，在classes/META-INF/spring.factories文件中添加如下键值对.

```
org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
com.example.factories.CustomAutoConfiguration
```
key是EnableAutoConfiguration的全路径名，value是我们自己定义实现的路径名。这样就可以实现jar包中bean的自动装配。

