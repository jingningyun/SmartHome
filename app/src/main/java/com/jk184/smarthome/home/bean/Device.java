package com.jk184.smarthome.home.bean;

/**
 * Created by jingning on 2019/5/27 0027 15:35
 * E-Mail Address：jingning1101@163.com
 */
public class Device {

    protected String name; //设备的名字
    protected String mac;  //设备的唯一标示
    protected int type;     //设备的类型

    public Device(int type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getMac() {
        return mac;
    }

    public int getType() {
        return type;
    }
}
