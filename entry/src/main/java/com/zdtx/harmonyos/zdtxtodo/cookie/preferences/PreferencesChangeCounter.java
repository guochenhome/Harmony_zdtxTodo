package com.zdtx.harmonyos.zdtxtodo.cookie.preferences;

import ohos.data.preferences.Preferences;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 创建日期:2021/2/24·11:26
 * 功能说明:﹝轻量级存储监听﹞
 *
 * @author Glen·Guo
 * @version ﹝1.0.0﹞
 * @since ☞...☜
 */
public class PreferencesChangeCounter implements Preferences.PreferencesObserver {
    private String key;
    private PreferencesChangeCounterCallback callback;

    private PreferencesChangeCounter() {
    }

    private PreferencesChangeCounter(String key, PreferencesChangeCounterCallback changeCounterCallback) {
        this.key = key;
        this.callback = changeCounterCallback;
    }

    final AtomicInteger notifyTimes = new AtomicInteger(0);

    @Override
    public void onChange(Preferences preferences, String s) {
        if (key.equals(s)) {
            notifyTimes.incrementAndGet();
            this.callback.onChange(notifyTimes.intValue() == 1);
        }
    }

    public interface PreferencesChangeCounterCallback {
        //是否更改
        void onChange(boolean isChange);
    }
}
