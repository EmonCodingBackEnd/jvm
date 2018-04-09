# jvm
深入理解Java虚拟机-JVM高级特性与最佳实践



# 一、Java内存区域与内存溢出异常

- 运行时数据区域
  - 线程隔离离的数据区
    - 程序计数器（Program Counter Register）
    - Java虚拟机栈（VM Stack）
    - 本地方法栈（Native Method Stack）
  - 线程共享的数据区
    - Java堆（Heap）
    - 方法区（Method Area）
      - 运行时常量池（Runtime Constant Pool）
- 直接内存（Direct Memory）


## 1.1 运行时数据区域

### 1.1.1 程序计数器

- 线程私有
- 此区域内存是唯一一个在Java虚拟机规范中**没有规定任何`OutOfMemoryError`情况**的区域。


### 1.1.2 Java虚拟机栈

- 线程私有
- 虚拟机栈描述的是Java方法执行的内存模型：每个方法在执行的同时都会创建一个栈帧（Stack Frame）用于存储局部变量表、操作数、动态链接、方法出口等信息。
- 在Java虚拟机规范中，对这个区域规定了两种异常情况
  - 如果线程请求的栈深度大于虚拟机所允许的深度，将抛出`StackOverflowError`异常。
  - 如果虚拟机栈可以动态扩展，扩展时无法申请到足够的内存，就会抛出`OutOfMemoryError`异常。


### 1.1.3 本地方法栈

- 线程私有
- 为虚拟机使用到的Native方法服务
- 本地方法栈区域也会抛出`StackOverflowError`和`OutOfMemoryError`异常。

### 1.1.4 Java堆

- 线程共享
- JVM参数
  - -Xms：最小堆内存
  - -Xmx：最大堆内存
- 异常
  - 如果在堆中没有内存完成实例分配，并且堆也无法再扩展时，将会抛出`OutOfMemoryError`异常。

### 1.1.5 方法区

- 线程共享
- 用于存储已被虚拟机加载的类信息、常量、静态变量、即时编译器编译后的代码等数据。
- 又名，**永久代**
- 异常
  - 根据Java虚拟机规范的规定，当方法区无法满足内存分配需求时，将抛出`OutOfMemoryError`异常。
- 运行时常量池
  - 方法区的一部分
  - Class文件中除了有类的版本、字段、方法、接口等描述信息外，还有一项信息是常量池（Constant Pool Table），用于存放编译器生成的各种字面量和符号引用。
  - 异常
    - 当常量池无法再申请到内存时会抛出`OutOfMemoryError`异常。

## 1.2 直接内存

- 不是虚拟机运行时数据区的一部分，也不是Java虚拟机规范中定义的内存区域。

## 1.3 HotSpot虚拟机对象探秘

### 1.3.1 对象的创建

- 指针碰撞
- 空闲列表

### 1.3.2 对象的内存布局

在HotSpot虚拟机中，对象在内存中存储的布局可以分3块区域：

- 对象头（Header）
- 实例数据（Instance Data）
- 对齐填充（Padding）



# 二、垃圾收集器与内存分配策略



