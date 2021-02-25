package com.zdtx.harmonyos.zdtxtodo.kits;

import com.zdtx.harmonyos.zdtxtodo.tool.Constant;
import ohos.agp.components.Image;
import ohos.app.Context;
import ohos.app.dispatcher.TaskDispatcher;
import ohos.app.dispatcher.task.TaskPriority;
import ohos.media.image.PixelMap;
import org.jetbrains.annotations.Nullable;

/**
 * 创建日期:2021/2/25·14:48
 * 功能说明:﹝描述...﹞
 *
 * @author Glen·Guo
 * @version ﹝1.0.0﹞
 * @since ☞...☜
 */
public class HmOSImageLoader {

    private Image image;
    private String url;
    private int defImage;
    private Context context;

    private HmOSImageLoader(@Nullable Context context){
        this.context=context;
    }

    public static HmOSImageLoader with(@Nullable Context context){
        return new HmOSImageLoader(context);
    }

    public HmOSImageLoader load(@Nullable String url){
        this.url=url;
        return this;
    }

    public HmOSImageLoader erre(@Nullable int erreImage){
        this.defImage=erreImage;
        return this;
    }

    public void into(@Nullable Image image){
        this.image=image;
        start();
    }

    private void start(){
        if(context==null){
            return;
        }
        if(image==null){
            return;
        }
        if(StringKit.isEmpty(this.url)){
            return;
        }
        if(defImage>0){
            image.setPixelMap(defImage);
        }

        TaskDispatcher refreshUITask = context.createParallelTaskDispatcher("", TaskPriority.LOW);
        refreshUITask.syncDispatch(() -> {
            PixelMap pixelMap = ImageReadUtil.createPixelMap(Constant.posterPath);
            this.image.setPixelMap(pixelMap);
            pixelMap.release();
        });
    }
}
