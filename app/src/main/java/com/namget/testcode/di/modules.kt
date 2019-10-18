package com.namget.testcode.di

import com.google.gson.GsonBuilder
import com.namget.testcode.BuildConfig
import com.namget.testcode.data.repository.ApiRepository
import com.namget.testcode.data.repository.ApiRepositoryImpI
import com.namget.testcode.data.source.local.ApiLocalLocalDataSourceImpl
import com.namget.testcode.data.source.local.AppDatabase
import com.namget.testcode.data.source.remote.ApiRemoteDataSourceImpl
import com.namget.testcode.data.source.remote.ApiService
import com.namget.testcode.ui.login.LoginViewModel
import com.namget.testcode.ui.main.MainViewModel
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by Namget on 2019.09.23.
 */

private const val LOGIN_BASEURL = "https://naver.com"
private const val TIMEOUT: Long = 10L

val remoteModule = module {

    single(named("headerInterceptor")) {
        Interceptor {
            val original = it.request()
            val request = original.newBuilder()
//                .header("X-Naver-Client-Id", "lih3bjz8wm5kjJhL8Grx")
//                .header("X-Naver-Client-Secret", "pNsddDIvgZ")
                .method(original.method(), original.body())
                .build()
            it.proceed(request)
        }
    }

    single(named("httpLoggingInterceptor")) {
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
        }
    }

    single {
        GsonBuilder().setLenient().create()
    }

    single {
        OkHttpClient.Builder().addInterceptor(get(named("headerInterceptor")))
            .addInterceptor(get(named("httpLoggingInterceptor")))
            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    //apiService naming을 통해 여러 BaseUrl을 가진 apiService 생성
    //동작의 경우
    single(named("loginApi")) {
        Retrofit.Builder().apply {
            addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            addConverterFactory(GsonConverterFactory.create(get()))
            client(get())
            baseUrl(LOGIN_BASEURL)
        }.build()
    }

    single(named("ApiService")) {
        get<Retrofit>(named("loginApi")).create(ApiService::class.java)
    }
    single {
        ApiRemoteDataSourceImpl(get(named("ApiService")))
    }
}

val repositoryModule = module {
    single {
        ApiRepositoryImpI(get(), get()) as ApiRepository
    }
}


val localModule = module {
    //DB
    single {
        AppDatabase.buildDataBase(androidContext())
    }

    single {
        ApiLocalLocalDataSourceImpl(get())
    }
}

val viewModelModule = module {
    viewModel { MainViewModel(get(named("loginApi"))) }
    viewModel { LoginViewModel(get()) }

}