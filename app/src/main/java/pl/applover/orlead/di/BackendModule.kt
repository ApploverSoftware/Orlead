package pl.applover.orlead.di

import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import pl.applover.orlead.BuildConfig
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by janpawlov ( ͡° ͜ʖ ͡°) on 24/11/2018.
 */
@Module
class BackendModule {
    @Provides
    @Singleton
    fun provideAPI(): OrleadAPI = create()

    private fun create(): OrleadAPI {
        @Suppress("ConstantConditionIf")
        val baseUrl = "https://hack-yeaaah.herokuapp.com/"

        val logging = HttpLoggingInterceptor().also {
            it.level = if (BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.BODY
            else
                HttpLoggingInterceptor.Level.NONE
        }
        val okHttpClient = OkHttpClient.Builder()
            .addNetworkInterceptor(RetrofitInterceptor())
            .addNetworkInterceptor(logging)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
            .client(okHttpClient)
            .build()

        return retrofit.create(OrleadAPI::class.java)
    }

    class RetrofitInterceptor : okhttp3.Interceptor {

        override fun intercept(chain: Interceptor.Chain): Response {
            val builder = chain.request().newBuilder()
            builder.header("Accept", "application/json")
            builder.header("Content-Type", "application/json")

            val newRequest = builder!!.build()
            return chain.proceed(newRequest)
        }
    }
}