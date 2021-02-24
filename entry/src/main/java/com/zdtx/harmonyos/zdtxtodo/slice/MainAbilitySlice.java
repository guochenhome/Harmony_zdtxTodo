package com.zdtx.harmonyos.zdtxtodo.slice;

import com.zdtx.harmonyos.zdtxtodo.ResourceTable;
import com.zdtx.harmonyos.zdtxtodo.kits.ZLog;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Button;
import ohos.agp.components.Component;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

public class MainAbilitySlice extends AbilitySlice {
    private Button button;
    private Button button_log;

    private static final HiLogLabel LABEL = new HiLogLabel(HiLog.LOG_APP, 0x00201, "MY_TAG");

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);
        initView();
    }

    private void initView(){
        button=(Button) findComponentById(ResourceTable.Id_btn_to_info);
        button_log=(Button)findComponentById(ResourceTable.Id_btn_log);
        button.setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                present(new MainInfoAbilitySlice(),new Intent());
            }
        });
        button_log.setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                ZLog.i("TAG","Harmony你好1");
                ZLog.d("TAG","Harmony你好2");
                ZLog.e("TAG","Harmony你好3");
                ZLog.f("TAG","Harmony你好4");
                ZLog.w("TAG","Harmony你好5");
            }
        });

    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }
}
