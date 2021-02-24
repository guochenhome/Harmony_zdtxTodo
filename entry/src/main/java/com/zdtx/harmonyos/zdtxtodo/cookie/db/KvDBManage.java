package com.zdtx.harmonyos.zdtxtodo.cookie.db;

import com.zdtx.harmonyos.zdtxtodo.cookie.Field;
import ohos.app.Context;
import ohos.data.distributed.common.*;
import ohos.data.distributed.device.DeviceFilterStrategy;
import ohos.data.distributed.device.DeviceInfo;
import ohos.data.distributed.user.SingleKvStore;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建日期:2021/2/24·13:36
 * 功能说明:
 * ﹝
 * 分布式数据库服务管理类
 * 数据库管理器
 * ﹞
 *
 * @author Glen·Guo
 * @version ﹝1.0.0﹞
 * @since ☞...☜
 */
public class KvDBManage implements Field {
    private volatile static KvDBManage manage;
    private Context context;
    private KvManagerConfig config;
    private KvManager kvManager;

    private KvDBManage(Context context) {
        this.context = context;
        this.config = new KvManagerConfig(this.context);
        this.kvManager = KvManagerFactory.getInstance().createKvManager(this.config);
    }

    public static KvDBManage getManage(Context context) {
        if (manage == null) {
            synchronized (KvDBManage.class) {
                if (manage == null) {
                    manage = new KvDBManage(context);
                }
            }
        }
        return manage;
    }

    /**
     * 创建单版本分布式数据库
     */
    public SingleKvStore createSingleKvStore() {
        return createSingleKvStore(DB_StoreID);
    }

    public SingleKvStore createSingleKvStore(String storeId) {
        Options CREATE = new Options();

        CREATE.setCreateIfMissing(true).setEncrypt(false).setKvStoreType(KvStoreType.SINGLE_VERSION);
        return kvManager.getKvStore(CREATE, storeId);
    }

    /**
     * 订阅分布式数据变化
     * 注册KvStoreObserver
     */
    public void observerKvStore(SingleKvStore singleKvStore, KvStoreObserverClient.KvStoreObserverClientCallback clientCallback) {
        KvStoreObserver observer = new KvStoreObserverClient(clientCallback);
        singleKvStore.subscribe(SubscribeType.SUBSCRIBE_TYPE_ALL, observer);
    }


    /**
     * SP中写入String
     *
     * @param key   键
     * @param value 值
     */
    public void put(SingleKvStore singleKvStore, final String key, final String value) {
        singleKvStore.putString(key, value);
    }

    /**
     * SP中读取String
     *
     * @param key 键
     * @return 存在返回对应值，不存在返回默认值{@code ""}
     */
    public String getString(final SingleKvStore singleKvStore, final String key) {
        return singleKvStore.getString(key);
    }

    /**
     * SP中写入int
     *
     * @param key   键
     * @param value 值
     */
    public void put(SingleKvStore singleKvStore, final String key, final int value) {
        singleKvStore.putInt(key, value);
    }

    /**
     * SP中读取int
     *
     * @param key 键
     * @return 存在返回对应值，不存在返回默认值-1
     */
    public int getInt(final SingleKvStore singleKvStore, final String key) {
        return singleKvStore.getInt(key);
    }


    /**
     * SP中写入float
     *
     * @param key   键
     * @param value 值
     */
    public void put(SingleKvStore singleKvStore, final String key, final float value) {
        singleKvStore.putFloat(key, value);
    }

    /**
     * SP中读取float
     *
     * @param key 键
     * @return 存在返回对应值，不存在返回默认值-1
     */
    public float getFloat(final SingleKvStore singleKvStore, final String key) {
        return singleKvStore.getFloat(key);
    }

    /**
     * SP中写入boolean
     *
     * @param key   键
     * @param value 值
     */
    public void put(SingleKvStore singleKvStore, final String key, final boolean value) {
        singleKvStore.putBoolean(key, value);
    }

    /**
     * SP中读取boolean
     *
     * @param key 键
     * @return 存在返回对应值，不存在返回默认值{@code false}
     */
    public boolean getBoolean(final SingleKvStore singleKvStore, final String key) {
        return singleKvStore.getBoolean(key);
    }

    /**
     * SP中写入double
     *
     * @param key   键
     * @param value 值
     */
    public void put(SingleKvStore singleKvStore, final String key, final double value) {
        singleKvStore.putDouble(key, value);
    }

    /**
     * SP中读取double
     *
     * @param key 键
     * @return 存在返回对应值，不存在返回默认值{@code false}
     */
    public double getDouble(final SingleKvStore singleKvStore, final String key) {
        return singleKvStore.getDouble(key);
    }

    /**
     * SP中写入byte[]
     *
     * @param key   键
     * @param value 值
     */
    public void put(SingleKvStore singleKvStore, final String key, final byte[]  value) {
        singleKvStore.putByteArray(key, value);
    }

    /**
     * SP中读取byte[]
     *
     * @param key 键
     * @return 存在返回对应值，不存在返回默认值{@code false}
     */
    public byte[] getByteArray(final SingleKvStore singleKvStore, final String key) {
        return singleKvStore.getByteArray(key);
    }

    /**
     * SP中写入Entry
     *
     * @param value 值
     */
    public void put(SingleKvStore singleKvStore, final List<Entry> value) {
        singleKvStore.putBatch(value);
    }

    /**
     * SP中读取Entry
     *
     * @param key 键
     * @return 存在返回对应值，不存在返回默认值{@code false}
     */
    public List<Entry> getEntries(final SingleKvStore singleKvStore, final String key) {
        return singleKvStore.getEntries(key);
    }

    /**
     * 关闭数据库
     */
    public void closeKvStore(final SingleKvStore singleKvStore){
        this.kvManager.closeKvStore(singleKvStore);
    }
    /**
     * 删除数据库
     */
    public void deleteKvStore(){
        this.kvManager.deleteKvStore(DB_StoreID);
    }
    /**
     * 数据库同步与其他设备
     */
    public void sync(SingleKvStore singleKvStore){
        List<DeviceInfo> deviceInfoList = kvManager.getConnectedDevicesInfo(DeviceFilterStrategy.NO_FILTER);
        List<String> deviceIdList = new ArrayList<>();
        for (DeviceInfo deviceInfo : deviceInfoList) {
            deviceIdList.add(deviceInfo.getId());
        }
        singleKvStore.sync(deviceIdList, SyncMode.PUSH_ONLY);
    }
}
