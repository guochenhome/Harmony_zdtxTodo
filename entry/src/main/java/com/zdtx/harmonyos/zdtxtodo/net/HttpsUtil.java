package com.zdtx.harmonyos.zdtxtodo.net;

import ohos.net.NetHandle;
import ohos.net.NetManager;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * 创建日期:2021/2/25·14:27
 * 功能说明:﹝网络请求工具类﹞
 *
 * @author Glen·Guo
 * @version ﹝1.0.0﹞
 * @since ☞...☜
 */
public class HttpsUtil {

    private static InputStream inputStream;
    private static HttpsURLConnection connection;

    public static InputStream getInputStream(String urlStr,String methodType) {

        NetManager netManager = NetManager.getInstance(null);
        if (!netManager.hasDefaultNet()) {
            return null;
        }

        NetHandle netHandle = netManager.getDefaultNet();
        try {
            URL url = new URL(urlStr);
            URLConnection urlConnection = netHandle.openConnection(url, java.net.Proxy.NO_PROXY);
            if (urlConnection instanceof HttpsURLConnection) {
                connection = (HttpsURLConnection) urlConnection;
            }

            //GET POST HEAD OPTIONS PUT DELETE TRACE
            connection.setRequestMethod(methodType);
            connection.connect();

            inputStream = connection.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return inputStream;
    }

    public static void closeStream() {
        try {
            if (inputStream == null) {
                return;
            }
            inputStream.close();
            if (connection == null) {
                return;
            }
            connection.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
