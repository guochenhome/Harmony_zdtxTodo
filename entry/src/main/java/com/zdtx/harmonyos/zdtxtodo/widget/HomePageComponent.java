package com.zdtx.harmonyos.zdtxtodo.widget;

import com.zdtx.harmonyos.zdtxtodo.ResourceTable;
import com.zdtx.harmonyos.zdtxtodo.bean.NewsBean;
import com.zdtx.harmonyos.zdtxtodo.kits.DisplayUtils;
import com.zdtx.harmonyos.zdtxtodo.kits.ZLog;
import com.zdtx.harmonyos.zdtxtodo.tool.Constant;
import com.zdtx.harmonyos.zdtxtodo.tool.HmOSImageLoader;
import ohos.agp.components.*;
import ohos.agp.components.element.ShapeElement;
import ohos.app.Context;
import ohos.multimodalinput.event.TouchEvent;

import java.util.ArrayList;

public class HomePageComponent extends ComponentContainer {
    public HomePageComponent(Context context) {
        super(context);
        init();
    }

    public HomePageComponent(Context context, AttrSet attrSet) {
        super(context, attrSet);
        init();
    }

    public HomePageComponent(Context context, AttrSet attrSet, String styleName) {
        super(context, attrSet, styleName);
        init();
    }

    private void init() {
        Component contentLayout = LayoutScatter.getInstance(getContext()).parse(ResourceTable.Layout_layout_home_page, this, false);
        addComponent(contentLayout);

        initBannerLayout(contentLayout);
        initNoticeLayout(contentLayout);
        initPosterView(contentLayout);
        initPremiumLayout(contentLayout);
        initSeriesLayout(contentLayout);
        initPartLayout(contentLayout);
        initEducLayout(contentLayout);
        initContestLayout(contentLayout);
        initNewsLayout(contentLayout);
    }

    private int scrolledX = 0;

    private void initBannerLayout(Component contentLayout) {
        int space = DisplayUtils.vp2px(getContext(), 16);
        int width = DisplayUtils.getDisplayWidthInPx(getContext()) - space * 2;
        int height = (int) (width * 0.44);
        ZLog.i("initBannerLayout width = " + width + ", height = " + height);
        int[] bannerList = {ResourceTable.Media_img_banner_1, ResourceTable.Media_img_banner_2,
                ResourceTable.Media_img_banner_1, ResourceTable.Media_img_banner_2, ResourceTable.Media_img_banner_1};

        initPointLayout(contentLayout, 0, bannerList.length);
        DirectionalLayout bannerLayout = (DirectionalLayout) contentLayout.findComponentById(ResourceTable.Id_bannerLayout);
        for (int i = 0; i < bannerList.length; i++) {
            Image ivBanner = new Image(getContext());
            ivBanner.setPixelMap(bannerList[i]);
            ivBanner.setScaleMode(Image.ScaleMode.CLIP_CENTER);
            ivBanner.setCornerRadius(DisplayUtils.vp2px(getContext(), 5));

            LayoutConfig layoutConfig = new LayoutConfig(width, height);
            if (i == 0) {
                layoutConfig.setMarginLeft(space);
                layoutConfig.setMarginRight(space / 2);
            } else if (i == bannerList.length - 1) {
                layoutConfig.setMarginRight(space);
            } else {
                layoutConfig.setMarginRight(space / 2);
            }

            bannerLayout.addComponent(ivBanner, layoutConfig);
        }

        ScrollView bannerScroll = (ScrollView) contentLayout.findComponentById(ResourceTable.Id_bannerScroll);
        bannerScroll.setScrolledListener((component, scrollX, scrollY, oldScrollX, oldScrollY) -> scrolledX = scrollX);
        bannerScroll.setTouchEventListener((component, touchEvent) -> {
            if (touchEvent.getAction() == TouchEvent.PRIMARY_POINT_UP || touchEvent.getAction() == TouchEvent.CANCEL) {
                int distance = width + space / 2;
                int index = scrolledX / distance;
                int offsetX = scrolledX % distance;
                ZLog.i("initBannerLayout onTouchEvent scrolledX = " + scrolledX + ", distance = " + distance + ", index = " + index + ", offsetX = " + offsetX);

                if (offsetX < (distance / 2)) {
                    // 回到上一个索引位
                    bannerScroll.fluentScrollXTo(index * distance);
                    initPointLayout(contentLayout, index, bannerList.length);
                } else {
                    // 滚动到下一个索引位
                    bannerScroll.fluentScrollXTo((index + 1) * distance);
                    initPointLayout(contentLayout, index + 1, bannerList.length);
                }
            }
            return false;
        });
    }

    private void initPointLayout(Component contentLayout, int index, int size) {
        DirectionalLayout pointLayout = (DirectionalLayout) contentLayout.findComponentById(ResourceTable.Id_pointLayout);
        pointLayout.removeAllComponents();
        for (int i = 0; i < size; i++) {
            int res = i == index ? ResourceTable.Graphic_banner_point_select_tablet : ResourceTable.Graphic_banner_point_normal_tablet;
            Component point = new Component(getContext());
            point.setBackground(new ShapeElement(getContext(), res));

            LayoutConfig layoutConfig = new LayoutConfig(DisplayUtils.vp2px(getContext(), i == index ? 16 : 6), DisplayUtils.vp2px(getContext(), 6));
            if (i != 0) {
                layoutConfig.setMarginLeft(DisplayUtils.vp2px(getContext(), 4));
            }

            pointLayout.addComponent(point, layoutConfig);
        }
    }

    private void initNoticeLayout(Component contentLayout) {
        Text tvNotice = (Text) contentLayout.findComponentById(ResourceTable.Id_tvNotice);
        tvNotice.setTruncationMode(Text.TruncationMode.AUTO_SCROLLING);
        tvNotice.setAutoScrollingCount(Text.AUTO_SCROLLING_FOREVER);
        tvNotice.startAutoScrolling();
    }

    private void initPosterView(Component contentLayout) {
        int width = DisplayUtils.getDisplayWidthInPx(getContext()) - DisplayUtils.vp2px(getContext(), 15) * 2;
        Image ivPoster = (Image) contentLayout.findComponentById(ResourceTable.Id_ivPoster);
        ivPoster.setHeight((int) (width * 0.173333));
        HmOSImageLoader.with(getContext()).load(Constant.posterPath).def(ResourceTable.Media_img_poster).into(ivPoster);
    }

    private void initPremiumLayout(Component contentLayout) {
        HomePageTitleComponent titleComponent = (HomePageTitleComponent) contentLayout.findComponentById(ResourceTable.Id_titlePremium);
        titleComponent.setData("精品付费课", "查看全部＞", null);
    }

    private void initSeriesLayout(Component contentLayout) {
        HomePageTitleComponent titleComponent = (HomePageTitleComponent) contentLayout.findComponentById(ResourceTable.Id_titleSeries);
        titleComponent.setData("创造力培养体系", "", null);
    }

    private void initPartLayout(Component contentLayout) {
        HomePageTitleComponent titleComponent = (HomePageTitleComponent) contentLayout.findComponentById(ResourceTable.Id_titlePart);
        titleComponent.setData("配件体系课", "查看全部＞", null);
    }

    private void initEducLayout(Component contentLayout) {
        HomePageTitleComponent titleComponent = (HomePageTitleComponent) contentLayout.findComponentById(ResourceTable.Id_titleEduc);
        titleComponent.setData("亲职教育", "", null);
    }

    private void initContestLayout(Component contentLayout) {
        HomePageTitleComponent titleComponent = (HomePageTitleComponent) contentLayout.findComponentById(ResourceTable.Id_titleContest);
        titleComponent.setData("竞赛", "查看全部＞", null);
    }

    private void initNewsLayout(Component contentLayout) {
        HomePageTitleComponent titleComponent = (HomePageTitleComponent) contentLayout.findComponentById(ResourceTable.Id_titleNews);
        titleComponent.setData("学院资讯", "更多＞", null);

        ArrayList<NewsBean> newsList = new ArrayList<>();
        newsList.add(new NewsBean("关于磁力片边缘飞起瑕疵的工艺说明,如果你看了就懂了", "2020-02-27", "10834", true));
        newsList.add(new NewsBean("App 的使用方法", "2020-07-01", "499", false));
        newsList.add(new NewsBean("磁力片的起源", "2019-12-11", "93", false));

        DirectionalLayout newsContainer = (DirectionalLayout) contentLayout.findComponentById(ResourceTable.Id_newsContainer);
        for (NewsBean newsBean : newsList) {
            Component newsComponent = LayoutScatter.getInstance(getContext()).parse(ResourceTable.Layout_layout_home_page_news, newsContainer, false);
            ((Text) newsComponent.findComponentById(ResourceTable.Id_tvNewsTitle)).setText((newsBean.top ? "【置顶】" : "") + newsBean.title);
            ((Text) newsComponent.findComponentById(ResourceTable.Id_tvNewsDate)).setText(newsBean.dateTime);
            ((Text) newsComponent.findComponentById(ResourceTable.Id_tvNewsCount)).setText(newsBean.count);

            newsContainer.addComponent(newsComponent);
        }
    }
}