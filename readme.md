### api		: 用来定义服务接口,以及共用的entity
### core	：核心依赖，也就是共用的方法与类
### dao		：用于数据库交互实现
### service	：服务实现，用来实现定义的接口
### web		：对外提供web服务的接口

##依赖关系
<ol>
<li>web 	依赖 	api  core  service</li>
<li>service 依赖  	core  dao  api</li>
<li>api		依赖  	dao core</li>
</ol>


### 例子：入口类 TestController

<ol>
<li>浏览器访问 127.0.0.1:8082/test_dao </li>
<li>框架的调度服务根据url匹配规则找到了 TestController 类下的 testDao() 方法</li>
<li>参数绑定（此处没有方法参数）</li>
<li>调用exampleService接口的get方法 （ExampleService接口通过依赖注入，实际注入对象为ExampleServiceImpl）</li>
<li>ExampleServiceImpl调用get实现，该类通过依赖注入了ExampleMapper </li>
<li>ExampleMapper 类继承了BaseMapper<Example> BaseMapper类为 mybatis的框架类（此处需查阅mybatis文档，查看相关用法）</li>
<li>ExampleMapper调用了selectById方法(此方法为Mybatis的默认实现方法)，从数据库中获取到了model对象</li>
<li>通过类型转换工具ConvertUtils 将 查询出来的model对象(example)转化为了vo对象(ExampleVo)</li>
<li>利用Results工具类返回结果</li>
</ol>

### 注意：先将示例表导入到本地数据库  表文件为 springboot2.sql
### 配置文件地址：user-web/src/main/resources/application.yml