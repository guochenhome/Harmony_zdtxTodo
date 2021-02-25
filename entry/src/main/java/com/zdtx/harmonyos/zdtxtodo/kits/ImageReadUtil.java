package com.zdtx.harmonyos.zdtxtodo.kits;

import com.zdtx.harmonyos.zdtxtodo.net.HttpsUtil;
import com.zdtx.harmonyos.zdtxtodo.net.RequestMethod;
import ohos.media.image.ImageSource;
import ohos.media.image.PixelMap;

import java.io.InputStream;

/**
 * 创建日期:2021/2/25·14:28
 * 功能说明:﹝图片读取工具类﹞
 *
 * @author Glen·Guo
 * @version ﹝1.0.0﹞
 * @since ☞...☜
 */
public class ImageReadUtil {

    public static PixelMap createPixelMap(String imageUrl) {

        InputStream inputStream = HttpsUtil.getInputStream(imageUrl, RequestMethod.GET.name());
        /*
        设置sourceOptions的formatHint为指定mime类型可提高解析效果；
        sourceOptions为null则ImageSource自动根据mime类型进行解析；
         */
        ImageSource.SourceOptions sourceOptions = new ImageSource.SourceOptions();
        sourceOptions.formatHint = "image/png";

        ImageSource imageSource = ImageSource.create(inputStream,null);
        PixelMap pixelMap = imageSource.createPixelmap(null);

        /*
        读取完成后，需要关闭流资源。流未关闭会造成流资源阻塞，
        即使数据读取完成也会持续等待流数据信息，导致调用方获取不到数据
         */
        HttpsUtil.closeStream();
        ZLog.i("ImageReadUtil:"+"PixelMap info:%{public}s ",pixelMap.getImageInfo().size);

        return pixelMap;
    }

}
