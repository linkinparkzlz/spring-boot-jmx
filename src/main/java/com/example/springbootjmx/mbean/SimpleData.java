package com.example.springbootjmx.mbean;

import javax.management.*;
import java.util.concurrent.atomic.AtomicLong;

import static javax.management.AttributeChangeNotification.ATTRIBUTE_CHANGE;

public class SimpleData extends NotificationBroadcasterSupport implements SimpleDataMBean, NotificationListener, NotificationFilter {


    private String data;

    private static final AtomicLong sequenceNumber = new AtomicLong();

    public SimpleData() {

        this.addNotificationListener(this, this, null);
    }

    @Override
    public void setData(String data) {


        String oldData = this.data;


        this.data = data;


        AttributeChangeNotification changeNotification = new AttributeChangeNotification(true, sequenceNumber.incrementAndGet()
                , System.currentTimeMillis(), "Data has been change :" + oldData, "data", String.class.getName(), oldData, data);

        sendNotification(changeNotification);

    }

    @Override
    public String getData() {
        return data;
    }

    @Override
    public String displayData() {
        return data;
    }

    /**
     * 处理通知
     *
     * @param notification
     * @param handback
     */
    @Override
    public void handleNotification(Notification notification, Object handback) {


        AttributeChangeNotification attributeChangeNotification = (AttributeChangeNotification) notification;

        String oldData = (String) attributeChangeNotification.getOldValue();

        String newData = (String) attributeChangeNotification.getNewValue();

        System.out.println(oldData + "    和   " + newData);


    }


    @Override
    public boolean isNotificationEnabled(Notification notification) {

        //只关心这个通知
        if (AttributeChangeNotification.class.isAssignableFrom(notification.getClass())) {


            AttributeChangeNotification attributeChangeNotification
                    = AttributeChangeNotification.class.cast(notification);

            if ("data".equals(attributeChangeNotification.getAttributeName())) {
                return true;
            }

        }

        return false;
    }


    /**
     * 暴露通知信息
     */

    @Override
    public MBeanNotificationInfo[] getNotificationInfo() {
        return new MBeanNotificationInfo[]{
                new MBeanNotificationInfo(new String[]{ATTRIBUTE_CHANGE}, "Data Change Notication", "数据改变通知")
        };
    }
}
