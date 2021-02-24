package com.zdtx.harmonyos.zdtxtodo;

import com.zdtx.harmonyos.zdtxtodo.slice.MainAbilitySlice;
import com.zdtx.harmonyos.zdtxtodo.slice.MainInfoAbilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

public class MainAbility extends Ability {

    public static Ability ability;
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        ability=this;
        super.setMainRoute(MainAbilitySlice.class.getName());
        super.addActionRoute("action.main.info",MainInfoAbilitySlice.class.getName());
    }
}
