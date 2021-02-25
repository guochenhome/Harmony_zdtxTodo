package com.zdtx.harmonyos.zdtxtodo.slice;

import com.zdtx.harmonyos.zdtxtodo.ResourceTable;
import com.zdtx.harmonyos.zdtxtodo.kits.ZLog;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.agp.components.*;
import ohos.agp.utils.Color;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

public class MainAbilitySlice extends AbilitySlice {
    private int selectIndex = 0;
    private final int[] imageMedia = {
            ResourceTable.Media_tab_home_n, ResourceTable.Media_tab_home_s,
            ResourceTable.Media_tab_course_n, ResourceTable.Media_tab_course_s,
            ResourceTable.Media_tab_circle_n, ResourceTable.Media_tab_circle_s,
            ResourceTable.Media_tab_mine_n, ResourceTable.Media_tab_mine_s
    };

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);

        updateTabLayout(selectIndex);
        initTabLayout();
    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }

    private void initTabLayout() {
        findComponentById(ResourceTable.Id_homeTab).setClickedListener(v -> updateTabLayout(0));
        findComponentById(ResourceTable.Id_courseTab).setClickedListener(v -> updateTabLayout(1));
        findComponentById(ResourceTable.Id_circleTab).setClickedListener(v -> updateTabLayout(2));
        findComponentById(ResourceTable.Id_mineTab).setClickedListener(v -> updateTabLayout(3));
    }

    private void updateTabLayout(int newIndex) {
        if (newIndex < 0 || newIndex > 3) {
            return;
        }
        ComponentContainer tabItem = ((ComponentContainer) ((ComponentContainer) findComponentById(ResourceTable.Id_navLayout)).getComponentAt(newIndex));
        ((Image) tabItem.getComponentAt(0)).setPixelMap(imageMedia[newIndex * 2 + 1]);
        ((Text) tabItem.getComponentAt(1)).setTextColor(new Color(Color.getIntColor("#ffc613")));
        if (selectIndex != newIndex) {
            tabItem = ((ComponentContainer) ((ComponentContainer) findComponentById(ResourceTable.Id_navLayout)).getComponentAt(selectIndex));
            ((Image) tabItem.getComponentAt(0)).setPixelMap(imageMedia[selectIndex * 2]);
            ((Text) tabItem.getComponentAt(1)).setTextColor(new Color(Color.getIntColor("#404040")));
            selectIndex = newIndex;
        }
    }
}
