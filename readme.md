我们在系统中经常会遇到需要对输入的数据进行验证。

这里我们使用Spring Boot 结合Bean Validation API来进行数据验证。

Bean Validation API是Java定义的一个验证参数的规范。

具体可以参考：http://beanvalidation.org/specification/

##定义需要验证的请求
例子中我们定义如下的请求消息。它包含......
```java

```
##编写Controller
我们使用Spring 提供的``` @RestController```注解。
做一个简单的表单提交，如下
```java
```
##进行测试看看是我们获得了什么


##如何定义提示

##再测试看看我们获得了什么结果





