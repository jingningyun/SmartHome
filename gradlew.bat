package com.jk184.demo.common.ble;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.clj.fastble.BleManager;
import com.clj.fastble.callback.BleNotifyCallback;
import com.clj.fastble.callback.BleWriteCallback;
import com.clj.fastble.data.BleDevice;
import com.clj.fastble.exception.BleException;
import com.jk184.demo.common.ble.cmd.BleCmd;
import com.jk184.demo.common.ble.cmd.RequstBleCmd;
import com.jk184.demo.common.ble.model.BleData;
import com.jk184.demo.common.ble.model.BlePoint;
import com.jk184.demo.common.ble.model.BleSport;
import com.jk184.demo.common.constans.BleConstans;
import com.jk184.demo.common.utils.DateUtils;
import com.jk184.demo.common.utils.HexDump;
import com.jk184.demo.model.bean.MdlCircle;
import com.jk184.demo.model.bean.MdlPoint;
import com.jk184.demo.model.bean.MdlSport;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jingning on 2019/4/1 0001 15:26
 * E-Mail Address：jingning1101@163.com
 * 业务相关的蓝牙操作
 */
public class BleHelper {

    private static final String TAG = "BleHelper";

    private static final int TIME_OUT = 30 * 1000; //20s 总操作时间，超过这个时长超时。


    private static final int OPERATION_TIME_OUT = 0;
    private static final int OPEN_NOTIFY_CMD = 1;
    private static final int OPEN_NOTIFY_DATA = 2;
    private static final int SYNC_TIME = 3;
    private static final int GET_ALL_RECORD = 4;
    private static final int GET_SPORT_INFO = 5;
    private static final int GET_POINT_INFO = 6;
    private static final int DEL_SPORT_RECORD = 7;

    private BleDevice bleDevice;

    private int sportCount = 0;//运动条目数
    private int sportIndex = 0;//查询运动的游标
    private int pointDataLength = 0;//一个坐标点的数据长度

    private MdlSport sport; //当前正在操作的运动(数据库)
    private BleSport bleSport;//当前正在操作的运动(蓝牙)

    private List<BlePoint> blePoints = new ArrayList<BlePoint>();   //存储ble获取的坐标点

    private Handler handler = new Handler() {
        @Override