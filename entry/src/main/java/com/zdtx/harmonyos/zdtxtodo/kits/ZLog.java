package com.zdtx.harmonyos.zdtxtodo.kits;

import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

/**
 * 创建日期:2021/2/24·15:48
 * 功能说明:﹝日志管理﹞
 *
 * @author Glen·Guo
 * @version ﹝1.0.0﹞
 * @since ☞...☜
 */
public class ZLog {

    private static boolean IS_SHOW_LOG = false;

    private static final String DEFAULT_MESSAGE = "execute";

    static  HiLogLabel LABEL;

    private static final int D = 0x2;
    private static final int I = 0x3;
    private static final int W = 0x4;
    private static final int E = 0x5;
    private static final int F = 0x6;

    public static void init(boolean isShowLog,String TAG) {
        IS_SHOW_LOG = isShowLog;
        LABEL = new HiLogLabel(HiLog.LOG_APP, 0x00201, TAG);
    }

    public static void d() {
        printLog(D, null, DEFAULT_MESSAGE);
    }

    public static void d(Object msg) {
        printLog(D, null, msg);
    }

    public static void d(String tag, Object msg) {
        printLog(D, tag, msg);
    }

    public static void i() {
        printLog(I, null, DEFAULT_MESSAGE);
    }

    public static void i(Object msg) {
        printLog(I, null, msg);
    }

    public static void i(String tag, Object msg) {
        printLog(I, tag, msg);
    }

    public static void w() {
        printLog(W, null, DEFAULT_MESSAGE);
    }

    public static void w(Object msg) {
        printLog(W, null, msg);
    }

    public static void w(String tag, Object msg) {
        printLog(W, tag, msg);
    }

    public static void e() {
        printLog(E, null, DEFAULT_MESSAGE);
    }

    public static void e(Object msg) {
        printLog(E, null, msg);
    }

    public static void e(String tag, Object msg) {
        printLog(E, tag, msg);
    }

    public static void f() {
        printLog(F, null, DEFAULT_MESSAGE);
    }

    public static void f(Object msg) {
        printLog(F, null, msg);
    }

    public static void f(String tag, Object msg) {
        printLog(F, tag, msg);
    }


    private static void printLog(int type, String tagStr, Object objectMsg) {
        if (!IS_SHOW_LOG) {
            return;
        }

        String logStr=tagStr+":["+objectMsg.toString()+"]";

        switch (type) {
            case D:
                HiLog.debug(LABEL,logStr);
                break;
            case I:
                HiLog.info(LABEL,logStr);
                break;
            case W:
                HiLog.warn(LABEL,logStr);
                break;
            case E:
                HiLog.error(LABEL,logStr);
                break;
            case F:
                HiLog.fatal(LABEL,logStr);
                break;
        }

    }

}
