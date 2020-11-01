# 集成hibernate

## 初始化实体映射表
系统启动时基于配置spring.jpa.hibernate.ddl-auto=update和bean实体自动映射创建表结构

## CURD
参考com.xbcxs.hibernate.user下代码示例。

当前版Hibernate在构建跨表联合返回对象时需要使用addScalar()构建属性映射。
```
private NativeQuery scalarForPage(int from, int size) {
    String sql = "select u.id as \"id\", u.login_name as \"loginName\", u.name as \"name\", u.phone as \"phone\", u.create_time as \"createTime\", u.update_time as \"updateTime\", d.name as \"departmentName\" from sys_user u ,sys_dept d, sys_user_dept ud where u.id=ud.user_id and ud.dept_id=d.id";
    NativeQuery nativeQuery = (NativeQuery) entityManager.createNativeQuery(sql);
    // 默认1表示第一页
    nativeQuery.setFirstResult(from);
    // 每页的条数
    nativeQuery.setMaxResults(size);
    nativeQuery.addScalar("id");
    nativeQuery.addScalar("loginName");
    nativeQuery.addScalar("name");
    nativeQuery.addScalar("phone");
    nativeQuery.addScalar("createTime");
    nativeQuery.addScalar("updateTime");
    nativeQuery.addScalar("departmentName");
    return nativeQuery;
}
```


