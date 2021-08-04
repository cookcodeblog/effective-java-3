[TOC]

# Effective Java 3rd Cheat Sheet



根据Effective Java第3版整理。



工具与环境：

- Intellij IDEA Ultimate
- Java 8
- JUnit 5

## 第7章 Lambda和Stream

### 第42条：Lambda优先于匿名类

- 删除所有Lamdba参数的类型，除非他们的存在能够使程序变得更加清晰。
- 对Lamdba而言，一行是最理想的，三行是合理的最大极限。
- 从Java8开始，Lambda就成了表示小函数对象的最佳方式。



示例代码：

- [Item42Test.java](./src/test/java/cn/xdevops/ch7/lambdastream/Item42Test.java)



### 第43条：方法引用优先于Lambda

- 只要方法引用更加简洁、清晰，就用方法引用。
- 如果方法引用并不简洁，就坚持用Lambda。



示例代码：

- [Item43Test.java](./src/test/java/cn/xdevops/ch7/lambdastream/Item43Test.java)



### 第44条：坚持使用标准的函数接口



- 优先使用标准的函数接口
- 只有在以下情况定制函数接口 （比如Comparator接口）
  - 通用，并且将受益于描述性的名称
  - 具有与其关联的严格的契约
  - 将受益于定制的缺省方法



`java.util.funciton` 包的6个基础函数接口：

| 函数接口            | 函数签名              | 范例                  | 说明           |
| ------------------- | --------------------- | --------------------- | -------------- |
| `UnaryOperator<T>`  | `T apply(T t)`        | `String::toLowerCase` | 一元运算       |
| `BianryOperator<T>` | `T apply(T t1, T t2)` | `BigInteger::add`     | 二元运算       |
| `Predicate<T>`      | `boolean test(T t)`   | `Collection::isEmpty` | 判断真假       |
| `Function<T, R>`    | `R apply(T t)`        | `Arrays::asList`      | 有参数有返回值 |
| `Supplier<T>`       | `T get()`             | `Instant::now`        | 无参数有返回值 |
| `Consumer<T>`       | `void accpet(T t)`    | `System.out:println`  | 有参数无返回值 |



### 第45条：谨慎使用Stream

- Stream中的数据元素可以是对象引用或基本类型值，支持三种基本类型：int, long 和 double。
- 一个Stream pipeline中包含一个源Stream，零个或多个中间操作，一个终止操作。
- 中间操作将一个Stream转换成另一个Stream，比如映射(map)、过滤(filter)。
- 终止操作执行一个最终的计算，比如将元素保存到一个集合中（collect）、遍历打印（forEach）。
- Stream pipeline通常是lazy的，直到调用终止操作时才会开始计算。千万不要忘记终止操作。
- 滥用Stream会使程序更难以读懂和维护。
- 在没有显式类型的情况下，仔细命名Lambda参数，对Stream pipeline的可读性至关重要。所以用单个字符来作为Lambda参数会降低代码可读性。
- 在Stream pipeline中使用helper方法，对于可读性而言，比在迭代化代码中更为重要。
- 建议使用Stream的场景：
  - 统一转换元素的序列（map）
  - 过滤元素的序列（filter）
  - 利用单个操作（比如添加、连接或计算最小值）合并元素的序列 （reduce）
  - 将元素的序列存放到一个集合中，比如根据某些公共属性进行分组（collect(groupingBy(), collect(toList()))
  - 搜索满足某些条件的元素的序列（find）



示例代码：

- [Item45Test.java](./src/test/java/cn/xdevops/ch7/lambdastream/Item44Test.java)



## 第9章 通用编程

### 第61条： 基本类型优先于装箱基本类型



- 自动装箱（auto boxing）和自动拆箱（auto-unboxing）模糊了（但没有完全抹去）基本类型和装箱基本类型的的区别。
- 基本类型和装箱基本类型的的主要区别：
  - 基本类型只有值（value），而装箱基本类型还有对象的同一性（identity）。
  - 基本类型只有函数值（可用于计算的值），而装箱基本类型还有一个null。
  - 基本类型通常比装箱基本类型更节省时间和空间。
- 对装箱基本类型运用==操作符几乎总是错误的。（因为这时不是比较值，而是比较对象的同一性）
- 当在一项操作中混合使用基本类型和装箱基本类型时，装箱基本类型就会自动拆箱。
  - 如果装箱基本类型的的对象还没有初始化，默认值为null，可能会导致NullPointerException。
  - 因此建议总是要初始化装箱基本类型的的对象。
- 注意当程序装箱了基本类型值时，会导致较高的资源消耗和不必要的对象创建。
- 在以下场景使用装箱基本类型：
  - 作为集合中的元素、键和值。
  - 在参数化类型和方法中，作为类型参数。
  - 在进行反射的方法调用时使用。





| 基本类型  | 基本类型默认值 | 装箱基本类型 | 装箱基本类型默认值 |
| --------- | -------------- | ------------ | ------------------ |
| `int`     | `0`            | `Integer`    | `null`             |
| `long`    | `0`            | `Long`       | `null`             |
| `double`  | `0.0`          | `Double`     | `null`             |
| `boolean` | `false`        | `Boolean`    | `null`             |



- 示例代码：

  - [Item61Test.java](./src/test/java/cn/xdevops/ch9/general/Item61Test.java)

  

  
