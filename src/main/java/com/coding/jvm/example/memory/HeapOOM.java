/*
 * 文件名称：HeapOOM.java
 * 系统名称：[系统名称]
 * 模块名称：[模块名称]
 * 软件版权：Copyright (c) 2011-2018, liming20110711@163.com All Rights Reserved.
 * 功能说明：Java堆溢出
 * 开发人员：Rushing0711
 * 创建日期：20180111 09:28
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20180111-01         Rushing0711     M201801110928 新建文件
 ********************************************************************************/
package com.coding.jvm.example.memory;

import java.util.ArrayList;
import java.util.List;

/**
 * Java堆溢出.
 *
 * <p>创建时间: <font style="color:#00FFFF">20180111 09:29</font><br>
 * Java堆用于存储对象实例，只要不断地创建对象，并且保证GC Roots到对象之间有可达路径来避免垃圾回收<br>
 * 机制清除这些对象，那么在对象数量到达最大堆的容量限制后就会产生内存溢出异常。<br>
 * <font style="color:#FFFF00">“VM Args:”后面的参数，是虚拟机启动参数，不包含VM Args字样。</font>
 *
 * <p>VM Args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError<br>
 * 将堆的最小值-Xms参数与最大值-Xmx参数设置为一样即可避免堆自动扩展，<br>
 * 通过参数-XX:HeapDumpOnOutOfMemoryError可以让虚拟机在出现内存溢出异常时Dump出当前的内存堆<br>
 * 转储快照以便事后进行分析。
 *
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
public class HeapOOM {

    static class OOMObject {}

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        while (true) {
            list.add(new OOMObject());
        }
    }
}
