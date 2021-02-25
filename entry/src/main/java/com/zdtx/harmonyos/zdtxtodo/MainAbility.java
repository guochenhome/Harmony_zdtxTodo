package com.zdtx.harmonyos.zdtxtodo;

import com.zdtx.harmonyos.zdtxtodo.slice.MainAbilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

public class MainAbility extends Ability {

    public static Ability ability;
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        ability=this;
        super.setMainRoute(MainAbilitySlice.class.getName());
//        super.addActionRoute("action.main.info",MainInfoAbilitySlice.class.getName());
        checkSelfPermission();
    }

    private void checkSelfPermission() {
        String[] permissions = new String[]{"ohos.permission.CAMERA", "ohos.permission.READ_USER_STORAGE", "ohos.permission.WRITE_USER_STORAGE"};
        requestPermissionsFromUser(permissions, 1001);
    }
}
