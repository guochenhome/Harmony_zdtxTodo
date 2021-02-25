package com.zdtx.harmonyos.zdtxtodo.tool;

import com.zdtx.harmonyos.zdtxtodo.kits.ZLog;
import ohos.agp.components.Image;
import ohos.app.Context;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;
import ohos.media.image.ImageSource;
import ohos.media.image.PixelMap;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InputStream;

public class HmOSImageLoader {
    private final static HiLogLabel LABEL_LOG = new HiLogLabel(HiLog.LOG_APP, 0, "HmOSImageLoader");
    private Image image;
    private String url;
    private int defImage;
    private Context context;

    private HmOSImageLoader(Context context) {
        this.context = context;
    }

    public static HmOSImageLoader with(Context context) {
        return new HmOSImageLoader(context);
    }

    public HmOSImageLoader load(@Nullable String url) {
        this.url = url;
        return this;
    }

    public HmOSImageLoader def(int defImage) {
        this.defImage = defImage;
        return this;
    }

    public void into(@Nullable Image image) {
        this.image = image;
        start();
    }

    private void start() {
        if (defImage != 0)
            image.setPixelMap(defImage);
        Request request = new Request.Builder().url(url).get().build();
        new Thread(() -> {
            OkHttpClient okHttpClient = new OkHttpClient();
            try {
                //异步网络请求
                Response execute = okHttpClient.newCall(request).execute();
                //获取流
                assert execute.body() != null;
                InputStream inputStream = execute.body().byteStream();

                ZLog.i("HmOSImageLoader start inputStream = " + inputStream);

                //利用鸿蒙api将流解码为图片源
                ImageSource imageSource = ImageSource.create(inputStream, null);
                //根据图片源创建位图
                PixelMap pixelMap = imageSource.createPixelmap(null);
                //展示到组件上
                context.getUITaskDispatcher().asyncDispatch(() -> image.setPixelMap(pixelMap));
                //释放位图
                pixelMap.release();
            } catch (IOException e) {
                HiLog.error(LABEL_LOG, " ----- " + e.getMessage());
                e.printStackTrace();
            }
        }).start();
    }
}
