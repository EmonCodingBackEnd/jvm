/*
 * 文件名称：JavaVMStackSOF.java
 * 系统名称：[系统名称]
 * 模块名称：[模块名称]
 * 软件版权：Copyright (c) 2011-2018, liming20110711@163.com All Rights Reserved.
 * 功能说明：虚拟机栈和本地方法栈溢出
 * 开发人员：Rushing0711
 * 创建日期：20180111 10:10
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20180111-01         Rushing0711     M201801111010 新建文件
 ********************************************************************************/
package com.coding.jvm.example.memory;

/**
 * 虚拟机栈和本地方法栈溢出.
 *
 * <p>创建时间: <font style="color:#00FFFF">20180111 10:11</font><br>
 * 由于在HotSpot虚拟机中并不区分虚拟机栈和本地方法栈，因此，对于HotSpot来说，虽然-Xoss参数<br>
 * （设置本地方法栈大小）存在，但实际上是无效的，栈容量只由-Xss参数设定。<br>
 * 如果线程请求的栈深度大于虚拟机所允许的最大深度，将抛出StackOverflowError异常。<br>
 * 如果虚拟机在扩展栈时无法申请到足够的内存空间，则抛出OutOfMemoryError异常。
 *
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
public class VMStackSOF {

    private void dontStop() {
        while (true) {}
    }

    public void stackLeakByThread() {
        while (true) {
            Thread thread =
                    new Thread(
                            new Runnable() {
                                @Override
                                public void run() {
                                    dontStop();
                                }
                            });
        }
    }

    public static void main(String[] args) {
        VMStackSOF oom = new VMStackSOF();
        oom.stackLeakByThread();
    }
}
