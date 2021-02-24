package com.zdtx.harmonyos.zdtxtodo.slice;

import com.zdtx.harmonyos.zdtxtodo.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.Button;
import ohos.agp.components.Component;

public class MainAbilitySlice extends AbilitySlice {
    private Button button;
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);
        initView();
    }

    private void initView(){
        button=(Button) findComponentById(ResourceTable.Id_btn_to_info);
        button.setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                present(new MainInfoAbilitySlice(),new Intent());
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
