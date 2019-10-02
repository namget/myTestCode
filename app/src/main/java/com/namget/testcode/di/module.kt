package com.namget.testcode.di

import com.namget.testcode.BuildConfig
import com.namget.testcode.data.source.ApiRepository
import com.namget.testcode.data.source.remote.ApiRemoteDatasource
import com.namget.testcode.data.source.remote.ApiService
import com.namget.testcode.ui.login.LoginViewModel
import com.namget.testcode.ui.main.MainViewModel
import com.google.gson.GsonBuilder
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Namget on 2019.09.23.
 */
const val BASEURL = ""

val apiModule = module {
    //apiService
    single {
        val gson = GsonBuilder().setLenient().create()

        val interceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC)
        } else {
            interceptor.setLevel(HttpLoggingInterceptor.Level.NONE)
        }
        val headerInterceptor = Interceptor {
            val original = it.request()
            val request = original.newBuilder()
//                .header("X-Naver-Client-Id", "lih3bjz8wm5kjJhL8Grx")
//                .header("X-Naver-Client-Secret", "pNsddDIvgZ")
                .method(original.method(), original.body())
                .build()
            it.proceed(request)
        }

        val client =
            OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor(headerInterceptor)
                .build()


        Retrofit.Builder().apply {
            addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            addConverterFactory(GsonConverterFactory.create(gson))
            client(client)
            baseUrl(BASEURL)
        }.build().create(ApiService::class.java)
    }

    single {
        ApiRemoteDatasource(get())
    }
    single {
        ApiRepository(get())
    }
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { LoginViewModel(get()) }

}