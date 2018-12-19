package test.belezanaweb.com.br.testebelezanaweb.model.service;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;
import test.belezanaweb.com.br.testebelezanaweb.model.Product;

public class BelezaApiService {

    private static final String URL = "https://pacific-wave-51314.herokuapp.com/";
    private Api api;

    public BelezaApiService() {

        final HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        final OkHttpClient.Builder client = new OkHttpClient.Builder();
        client.addInterceptor(interceptor);

        final Retrofit.Builder retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client.build());

        api = retrofit.build().create(Api.class);
    }

    public Api getApi() {
        return api;
    }

    public interface Api {
        @GET("products")
        Observable<List<Product>> getProducts(@Query("page") Integer pageNum, @Query("size") Integer pageSize);

        @GET("products/{sku}")
        Observable<Product> getProductDetail(@Path("sku") Long id);
    }
}
