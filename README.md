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



## 1.1 程序计数器

- 线程私有
- 此区域内存是唯一一个在Java虚拟机规范中没有规定任何`OutOfMemoryError`情况的区域。



## 1.2 Java虚拟机栈

- 线程私有
- 虚拟机栈描述的是Java方法执行的内存模型：每个方法在执行的同时都会创建一个栈帧（Stack Frame）用于存储局部变量表、操作数、动态链接、方法出口等信息。
- 在Java虚拟机规范中，对这个区域规定了两种异常情况
  - 如果线程请求的栈深度大于虚拟机所允许的深度，将抛出`StackOverflowError`异常。
  - 如果虚拟机栈可以动态扩展，扩展时无法申请到足够的内存，就会抛出`OutOfMemoryError`异常。



## 1.3 本地方法栈

- ​