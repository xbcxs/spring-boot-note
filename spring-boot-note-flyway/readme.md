# 基于Flyway的SQL脚本自动升级

## 简介
Flyway is an open-source database migration tool. It strongly favors simplicity and convention over configuration.

It is based around just 7 basic commands: Migrate, Clean, Info, Validate, Undo, Baseline and Repair.

Migrations can be written in SQL (database-specific syntax (such as PL/SQL, T-SQL, ...) is supported) or Java (for advanced data transformations or dealing with LOBs).

It has a Command-line client. If you are on the JVM, we recommend using the Java API (also works on Android) for migrating the database on application startup. Alternatively, you can also use the Maven plugin or Gradle plugin.

And if that's not enough, there are plugins available for Spring Boot, Dropwizard, Grails, Play, SBT, Ant, Griffon, Grunt, Ninja and more!

Supported databases are Oracle, SQL Server (including Amazon RDS and Azure SQL Database), DB2, MySQL (including Amazon RDS, Azure Database & Google Cloud SQL), Aurora MySQL, MariaDB, Percona XtraDB Cluster, PostgreSQL (including Amazon RDS, Azure Database, Google Cloud SQL & Heroku), Aurora PostgreSQL, Redshift, CockroachDB, SAP HANA, Sybase ASE, Informix, H2, HSQLDB, Derby, Snowflake, SQLite and Firebird.

本文主要用Flyway来实现SQL脚本升级。每次程序启动时执行Flyway，Flyway检车指定SQL目录并比对数据库执行历程，将新SQL执行到书库，完成SQL升级。
## 引入相关包

```
<!-- 引入数据库 -->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <!--<scope>runtime</scope>-->
</dependency>
<!-- 引入JDBC依赖触发DataSource加载 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-jdbc</artifactId>
</dependency>
<!-- 引入flyway -->
<dependency>
    <groupId>org.flywaydb</groupId>
    <artifactId>flyway-core</artifactId>
</dependency>
```

## 执行

### 自动执行
默认Springboot会自动装配Flyway进行执行

### 手动执行
有自定义需求时也可以手动执行

```
    @Autowired
    private DataSource dataSource;

    @Test
    public void springDataSourceTest() {
        Flyway flyway = Flyway.configure().dataSource(dataSource).load();
        flyway.migrate();
    }
```

## 核心扩展

### Callback
实现Callback接口
```
@Component
public class MigrationCallback implements Callback {

    private static final Logger logger = LoggerFactory.getLogger(MigrationCallback.class);

    /**
     * Whether this callback supports this event or not. This is primarily meant as a way to optimize event handling
     *
     * @param event
     * @param context
     * @return
     */
    @Override
    public boolean supports(Event event, Context context) {
        switch (event) {
            case BEFORE_MIGRATE:
            case AFTER_MIGRATE:
            case AFTER_EACH_MIGRATE:
                return true;
            default:
                return false;
        }
    }

    /**
     * Whether this event can be handled in a transaction or whether it must be handled outside a transaction instead.
     *
     * @param event
     * @param context
     * @return
     */
    @Override
    public boolean canHandleInTransaction(Event event, Context context) {
        return false;
    }

    @Override
    public void handle(Event event, Context context) {
        if (event.equals(Event.BEFORE_MIGRATE)) {
            logger.info(Event.AFTER_MIGRATE.toString());
        } else if (event.equals(Event.AFTER_MIGRATE)) {
            logger.info(Event.AFTER_MIGRATE.toString());
        } else if (event.equals(Event.AFTER_EACH_MIGRATE)) {
            MigrationInfo migrationInfo = context.getMigrationInfo();
            logger.info("Flyway执行脚本:{}完成！", migrationInfo != null ? migrationInfo.getScript() : null);
        }
    }
}
```

### FlywayMigrationStrategy
默认为flyway.migrate()，可以通过实现FlywayMigrationStrategy复写默认实现。

>  flyway.repair()作用：当SQL升级执行错误后会在flyway_schema_history中记录一条执行记录，该记录success为0表示执行错误。当第二修复SQL执行后再次执行升级时， flyway.repair()为修复该记录为1，保障升级继续执行。

```
@Component
public class MigrationStrategy implements FlywayMigrationStrategy {

    private static final Logger logger = LoggerFactory.getLogger(MigrationStrategy.class);

    @Override
    public void migrate(Flyway flyway) {
        logger.info("Flyway start...");
        flyway.repair();
        flyway.migrate();
        logger.info("Flyway end...");
    }
}
```

## Flyway脚本格式
<html>
<div class="row">
    <div class="col-md-4">
        <h4>Versioned Migrations</h4>
        <pre>Prefix  Separator       Suffix
   <i class="fa fa-long-arrow-down" style="padding: 4px"></i>   <i class="fa fa-long-arrow-down" style="padding: 4px"></i>                <i class="fa fa-long-arrow-down" style="padding: 4px"></i>
   <span style="color: white; font-weight: bold"><span style="background-color: #0000AA; padding: 4px">V</span><span style="background-color: #AA0000; padding: 4px">2</span><span style="background-color: #00AA00; padding: 4px">__</span><span style="background-color: #AAAA00; padding: 4px">Add_new_table</span><span style="background-color: #00AAAA; padding: 4px">.sql</span></span>
     <i class="fa fa-long-arrow-up" style="padding: 4px"></i>         <i class="fa fa-long-arrow-up" style="padding: 4px"></i>
 Version    Description</pre>
    </div>
</div>

</html>

> The file name consists of the following parts:
> 
> Prefix: V for versioned (configurable), U for undo (configurable) and R for repeatable migrations (configurable)
> Version: Version with dots or underscores separate as many parts as you like (Not for repeatable migrations)
> Separator: __ (two underscores) (configurable)
> Description: Underscores or spaces separate the words
> Suffix: .sql (configurable)
> Optionally versioned SQL migrations can also omit both the separator and the description.
> 
> The configuration option validateMigrationNaming determines how Flyway handles files that do not correspond with the naming pattern when carrying out a migration: if true then Flyway will simply ignore all such files, if false then Flyway will fail fast and list all files which need to be corrected.


## 常用配置

```
# 是否开启flyway
spring.flyway.enabled=true
# 禁止flyway的clean命令会删除指定schema下的所有table
spring.flyway.clean-disabled=true
# SQL 脚本的目录,多个路径使用逗号分隔 默认值 classpath:db/migration
spring.flyway.locations=classpath:db/sql
# Metadata版本控制信息表，默认flyway_schema_history
spring.flyway.table=flyway_schema_history
# 自动初始化flyway_schema_history
spring.flyway.baseline-on-migrate=true
# 默认1，基线初始版本号
spring.flyway.baseline-version=1
# 编码
spring.flyway.encoding=UTF-8
# 是否允许无序迁移，例如在V1和V2后面执行V1.1
spring.flyway.outOfOrder=true
# 执行迁移时是否自动调用验证:当你的版本不符合逻辑(你先执行了DML而没有对应的DDL会抛出异常)
validate-on-migrate=true
```

官方配置详解：https://flywaydb.org/documentation/configfiles#environment-variable-substitution

## 集群支持
根据官方FQA，Flyway通过数据锁技术支持多节点部署。
> Can multiple nodes migrate in parallel?
> Yes! Flyway uses the locking technology of your database to coordinate multiple nodes. This ensures that even if multiple instances of your application attempt to migrate the database at the same time, it still works. Cluster configurations are fully supported.

## 参考
1. https://flywaydb.org/documentation/
2. https://docs.spring.io/spring-boot/docs/2.3.4.RELEASE/reference/htmlsingle/#howto-database-initialization