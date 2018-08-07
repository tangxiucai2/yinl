package com.desjf.dsjr.biz.retrofit;

import com.desjf.dsjr.config.DsjrConfig;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * @author YinL
 * @Describe  网络请求初始化
 */
public class BankHttpUtil {

    /**
     *  设置超时时长
        retrofit底层用的okHttp,所以设置超时还需要okHttp
        然后设置5秒超时
        TimeUnit为java.util.concurrent包下的时间单位
        TimeUnit.SECONDS这里为秒的单位
     */

   private static final OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
//            .sslSocketFactory(SSLSocketClient.getSSLSocketFactory())//忽略https验证
//            .hostnameVerifier(SSLSocketClient.getHostnameVerifier())
            .build();

    private static HttpRequestService httpRequestService;
    static {
        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://t1.dsp2p.com:9080/cgb/")
                .baseUrl(DsjrConfig.WS_BASE_DOMAIN+"cgb/app/")
                //增加返回值为String的支持
                .addConverterFactory(ScalarsConverterFactory.create())
                //增加返回值为Gson的支持(以实体类返回)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        httpRequestService = retrofit.create(HttpRequestService.class);
    }

    public static HttpRequestService getHttpRequestService() {
        return httpRequestService;
    }

}