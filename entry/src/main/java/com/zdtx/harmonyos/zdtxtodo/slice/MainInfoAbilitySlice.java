package com.zdtx.harmonyos.zdtxtodo.slice;

import com.zdtx.harmonyos.zdtxtodo.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.app.Context;

public class MainInfoAbilitySlice extends AbilitySlice {

    private Context context;

    @Override
    protected void onStart(Intent intent) {
        super.onStart(intent);
        this.context=this;
        super.setUIContent(ResourceTable.Layout_ability_main_info);
    }
}
