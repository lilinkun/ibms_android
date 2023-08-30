package com.hncr.ibms.http

import android.util.Log
import com.hncr.ibms.BuildConfig
import com.hncr.ibms.http.di.FloorApi
import com.hncr.ibms.http.di.SuggestionApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLSession

/**
 * Created by LG
 * on 2023/8/1  16:31
 * Description：
 */

const val TIME_OUT = 40L


val httpModule = module {
    single {
        val httpLoggingInterceptor = HttpLoggingInterceptor { msg ->
            Log.w("httpLoggingInterceptor", msg)
        }
        httpLoggingInterceptor.level = if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor.Level.BODY
        } else {
            HttpLoggingInterceptor.Level.NONE
        }
        httpLoggingInterceptor
    }
    single {
        OkHttpClient.Builder()
            .addInterceptor(get<HttpLoggingInterceptor>())
            .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
            .readTimeout(TIME_OUT, TimeUnit.SECONDS)
            .hostnameVerifier { _: String?, _: SSLSession? -> true }
            .build()
    }
    single<Retrofit>() {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(get<OkHttpClient>())
            .baseUrl(Config.SERVER_BASE_URL)
            .build()
    }
    single<Retrofit>(named(name = "beijing")) {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(get<OkHttpClient>())
            .baseUrl(Config.SERVER_BASE_URL)
            .build()
    }
    single<Retrofit>(named(name = "file")) {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(get<OkHttpClient>())
            .baseUrl(Config.SERVER_BASE_URL)
            .build()
    }
    single<FloorApi> { get<Retrofit>().create(FloorApi::class.java)}
    single<SuggestionApi> { get<Retrofit>().create(SuggestionApi::class.java)}
}