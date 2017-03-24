package sean.com.gankio.http;


import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import sean.com.gankio.http.service.IService;
import sean.com.gankio.http.service.ServiceManager;

/**
 * Created by Sean on 2017/3/23.
 */

public class Client {

    //    public static final String BASE_URL = "https://private-83a99d-syringetest.apiary-mock.com/";
    private static Client instance;

    private HttpHolder mHttpHolder;

    public static synchronized Client getInstance() {

        if (null == instance) instance = new Client();
        return instance;
    }

    public Client() {

        OkHttpClient mOkHttpClient = new OkHttpClient.Builder().build();
        Retrofit mRetrofit = new Retrofit.Builder()
                .baseUrl(ApiConstant.BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .client(mOkHttpClient)
                .build();
        ServiceManager serviceManager = new ServiceManager(mRetrofit.create(IService.class));
        mHttpHolder = new HttpHolder(serviceManager);
    }

    public HttpHolder getHttpHolder() {

        return mHttpHolder;
    }

}
