package com.example.springbootjmx.mbean;

import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

public class MBeanDemo {


    public static void main(String[] args) throws Exception {


        //MBean 服务器
        MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();

        //注册
        //注册对象  注册名称
        SimpleData simpleData = new SimpleData();

        ObjectName objectName = createObjectName(simpleData);

        mBeanServer.registerMBean(simpleData, objectName);

        //服务器不马上停止
        Thread.sleep(Long.MAX_VALUE);


    }

    private static ObjectName createObjectName(Object mBean) throws MalformedObjectNameException {


        Class<?> mNeanClass = mBean.getClass();

        String packageName = mNeanClass.getPackage().getName();

        String className = mNeanClass.getSimpleName();

        return new ObjectName(packageName + ":type" + className);


    }


}


















































