package com.zdtx.harmonyos.zdtxtodo;

import com.zdtx.harmonyos.zdtxtodo.kits.ZLog;
import ohos.aafwk.ability.AbilityPackage;

public class MyApplication extends AbilityPackage {
    @Override
    public void onInitialize() {
        super.onInitialize();
        ZLog.init(BuildConfig.DEBUG, "指动天下");
    }
}
