package com.zdtx.harmonyos.zdtxtodo.widget;

import com.zdtx.harmonyos.zdtxtodo.ResourceTable;
import ohos.agp.components.*;
import ohos.app.Context;

/**
 * 首页子布局 - 标题栏
 */
public class HomePageTitleComponent extends StackLayout {

    private Component contentLayout;

    public HomePageTitleComponent(Context context) {
        super(context);
        init();
    }

    public HomePageTitleComponent(Context context, AttrSet attrSet) {
        super(context, attrSet);
        init();
    }

    public HomePageTitleComponent(Context context, AttrSet attrSet, String styleName) {
        super(context, attrSet, styleName);
        init();
    }

    private void init() {
        contentLayout = LayoutScatter.getInstance(getContext()).parse(ResourceTable.Layout_layout_home_page_title, this, false);
        addComponent(contentLayout);
    }

    void setData(String title, String label, ClickedListener listener) {
        ((Text) contentLayout.findComponentById(ResourceTable.Id_tvTitle)).setText(title);
        ((Text) contentLayout.findComponentById(ResourceTable.Id_tvLabel)).setText(label);
        ((Text) contentLayout.findComponentById(ResourceTable.Id_tvLabel)).setClickedListener(listener);
    }
}
