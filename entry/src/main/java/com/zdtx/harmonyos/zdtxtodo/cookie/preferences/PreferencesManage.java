package com.zdtx.harmonyos.zdtxtodo.cookie.preferences;

import com.zdtx.harmonyos.zdtxtodo.cookie.Field;
import ohos.app.Context;
import ohos.data.DatabaseHelper;
import ohos.data.preferences.Preferences;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

/**
 * 创建日期:2021/2/24·10:55
 * 功能说明:﹝轻量级偏好数据库管理类﹞
 *
 * @author Glen·Guo
 * @version ﹝1.0.0﹞
 * @since ☞...☜
 */
public class PreferencesManage implements Field {
    private volatile static PreferencesManage manage;
    private Context context;
    private DatabaseHelper databaseHelper;
    private Preferences preferences;

    private PreferencesManage(Context context) {
        this.context = context;
        this.databaseHelper = new DatabaseHelper(this.context);
        this.preferences = databaseHelper.getPreferences(PF_NAME);
    }

    public static PreferencesManage getManage(Context context) {
        if (manage == null) {
            synchronized (PreferencesManage.class) {
                if (manage == null) {
                    manage = new PreferencesManage(context);
                }
            }
        }
        return manage;
    }

    //异步操作 持久化实例
    private void flush() {
        preferences.flush();
    }

    //同步操作
    private void flushSync() {
        preferences.flushSync();
    }


    /**
     * SP中写入String
     *
     * @param key   键
     * @param value 值
     */
    public void put(final String key, final String value) {
        preferences.putString(key, value);
        flushSync();
    }

    /**
     * SP中读取String
     *
     * @param key 键
     * @return 存在返回对应值，不存在返回默认值{@code ""}
     */
    public String getString(final String key) {
        return getString(key, "");
    }

    /**
     * SP中读取String
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return 存在返回对应值，不存在返回默认值{@code defaultValue}
     */
    public String getString(final String key, final String defaultValue) {
        return preferences.getString(key, defaultValue);
    }

    /**
     * SP中写入int
     *
     * @param key   键
     * @param value 值
     */
    public void put(final String key, final int value) {
        preferences.putInt(key, value);
        flushSync();
    }

    /**
     * SP中读取int
     *
     * @param key 键
     * @return 存在返回对应值，不存在返回默认值-1
     */
    public int getInt(final String key) {
        return getInt(key, -1);
    }

    /**
     * SP中读取int
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return 存在返回对应值，不存在返回默认值{@code defaultValue}
     */
    public int getInt(final String key, final int defaultValue) {
        return preferences.getInt(key, defaultValue);
    }

    /**
     * SP中写入long
     *
     * @param key   键
     * @param value 值
     */
    public void put(final String key, final long value) {
        preferences.putLong(key, value);
        flushSync();
    }

    /**
     * SP中读取long
     *
     * @param key 键
     * @return 存在返回对应值，不存在返回默认值-1
     */
    public long getLong(final String key) {
        return getLong(key, -1L);
    }

    /**
     * SP中读取long
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return 存在返回对应值，不存在返回默认值{@code defaultValue}
     */
    public long getLong(final String key, final long defaultValue) {
        return preferences.getLong(key, defaultValue);
    }

    /**
     * SP中写入float
     *
     * @param key   键
     * @param value 值
     */
    public void put(final String key, final float value) {
        preferences.putFloat(key, value);
        flushSync();
    }

    /**
     * SP中读取float
     *
     * @param key 键
     * @return 存在返回对应值，不存在返回默认值-1
     */
    public float getFloat(final String key) {
        return getFloat(key, -1f);
    }

    /**
     * SP中读取float
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return 存在返回对应值，不存在返回默认值{@code defaultValue}
     */
    public float getFloat(final String key, final float defaultValue) {
        return preferences.getFloat(key, defaultValue);
    }

    /**
     * SP中写入boolean
     *
     * @param key   键
     * @param value 值
     */
    public void put(final String key, final boolean value) {
        preferences.putBoolean(key, value);
        flushSync();
    }

    /**
     * SP中读取boolean
     *
     * @param key 键
     * @return 存在返回对应值，不存在返回默认值{@code false}
     */
    public boolean getBoolean(final String key) {
        return getBoolean(key, false);
    }

    /**
     * SP中读取boolean
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return 存在返回对应值，不存在返回默认值{@code defaultValue}
     */
    public boolean getBoolean(final String key, final boolean defaultValue) {
        return preferences.getBoolean(key, defaultValue);
    }

    /**
     * SP中写入String集合
     *
     * @param key    键
     * @param values 值
     */
    public void put(final String key, final Set<String> values) {
        preferences.putStringSet(key, values);
        flushSync();
    }

    /**
     * SP中读取StringSet
     *
     * @param key 键
     * @return 存在返回对应值，不存在返回默认值{@code Collections.<String>emptySet()}
     */
    public Set<String> getStringSet(final String key) {
        return getStringSet(key, Collections.<String>emptySet());
    }

    /**
     * SP中读取StringSet
     *
     * @param key          键
     * @param defaultValue 默认值
     * @return 存在返回对应值，不存在返回默认值{@code defaultValue}
     */
    public Set<String> getStringSet(final String key, final Set<String> defaultValue) {
        return preferences.getStringSet(key, defaultValue);
    }

    /**
     * SP中获取所有键值对
     *
     * @return Map对象
     */
    public Map<String, ?> getAll() {
        return preferences.getAll();
    }

    /**
     * SP中是否存在该key
     *
     * @param key 键
     * @return {@code true}: 存在<br>{@code false}: 不存在
     */
    public boolean contains(final String key) {
        return preferences.hasKey(key);
    }

    /**
     * SP中移除该key
     *
     * @param key 键
     */
    public void remove(final String key) {
        preferences.delete(key);
    }

    /**
     * 移除PF数据库
     * 移除Preferences实例
     */
    public void removePF() {
        databaseHelper.removePreferencesFromCache(PF_NAME);
    }

    /**
     * 删除指定文件
     */
    public boolean deletePF() {
       return databaseHelper.deletePreferences(PF_NAME);
    }

    /**
     * 移动PF
     */
    public boolean movePF(Context srcContext, String targetFile) {
        return databaseHelper.movePreferences(srcContext, PF_NAME, targetFile);
    }
}
