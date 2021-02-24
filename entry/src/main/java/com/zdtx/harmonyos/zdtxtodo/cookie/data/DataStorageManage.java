package com.zdtx.harmonyos.zdtxtodo.cookie.data;

import ohos.data.usage.DataUsage;
import ohos.data.usage.MountState;
import ohos.data.usage.Volume;

import java.util.List;
import java.util.Optional;

/**
 * 创建日期:2021/2/24·10:21
 * 功能说明:﹝数据存储管理类﹞
 *
 * @author Glen·Guo
 * @version ﹝1.0.0﹞
 * @since ☞...☜
 */
public class DataStorageManage {

    private volatile static DataStorageManage manage;

    private DataStorageManage() {
    }

    public static DataStorageManage getManage() {
        if (manage == null) {
            synchronized (DataStorageManage.class) {
                if (manage == null) {
                    manage = new DataStorageManage();
                }
            }
        }
        return manage;
    }

    // 获取默认存储设备挂载状态
    public MountState getMountState() {
        return DataUsage.getDiskMountedStatus();
    }

    //获取存储设备列表
    public Optional<List<Volume>> getVolumeList() {
        return DataUsage.getVolumes();
    }

    // 默认存储设备是否为可插拔设备
    public boolean isPluggable() {
        return DataUsage.isDiskPluggable();
    }
}
