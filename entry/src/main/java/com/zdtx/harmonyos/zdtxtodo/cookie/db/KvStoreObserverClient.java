package com.zdtx.harmonyos.zdtxtodo.cookie.db;

import ohos.data.distributed.common.ChangeNotification;
import ohos.data.distributed.common.Entry;
import ohos.data.distributed.common.KvStoreObserver;

import java.util.List;

/**
 * 创建日期:2021/2/24·14:00
 * 功能说明:﹝单版本分布式数据库所有（本地及远端）数据变化通知﹞
 *
 * @author Glen·Guo
 * @version ﹝1.0.0﹞
 * @since ☞...☜
 */
public class KvStoreObserverClient implements KvStoreObserver {
    private KvStoreObserverClientCallback clientCallback;

    private KvStoreObserverClient() {
    }

    public KvStoreObserverClient(KvStoreObserverClientCallback clientCallback) {
        this.clientCallback = clientCallback;
    }

    @Override
    public void onChange(ChangeNotification changeNotification) {
        List<Entry> insertEntries = changeNotification.getInsertEntries();
        List<Entry> updateEntries = changeNotification.getUpdateEntries();
        List<Entry> deleteEntries = changeNotification.getDeleteEntries();
        String deviceId = changeNotification.getDeviceId();

        if (this.clientCallback == null) {
            return;
        }
        if (insertEntries != null && !insertEntries.isEmpty()) {
            this.clientCallback.onChangeInsert(insertEntries);
        }
        if (updateEntries != null && !updateEntries.isEmpty()) {
            this.clientCallback.onChangeUpdate(updateEntries);
        }
        if (deleteEntries != null && !deleteEntries.isEmpty()) {
            this.clientCallback.onChangeDelete(deleteEntries);
        }
        if (deviceId != null && !deviceId.equals("")) {
            this.clientCallback.onChangeDeviceId(deviceId);
        }
    }

    public interface KvStoreObserverClientCallback {
        void onChangeInsert(List<Entry> insert);

        void onChangeUpdate(List<Entry> update);

        void onChangeDelete(List<Entry> delete);

        void onChangeDeviceId(String deviceId);
    }
}
