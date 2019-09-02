package krisanthe.exercise.mytvshows.application

import android.content.Context
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import krisanthe.exercise.mytvshows.utils.AppSchedulers
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ConnectionModule {

    @AppScope
    @Provides
    fun provideInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    @AppScope
    @Provides
    fun provideCache(context: Context): Cache {
        return Cache(context.filesDir, (10 * 10 * 1000).toLong())
    }

    @AppScope
    @Provides
    fun provideHttpClient(interceptor: HttpLoggingInterceptor, cache: Cache): OkHttpClient {
        return OkHttpClient().newBuilder()
            .addInterceptor(interceptor)
            .cache(cache)
            .build()
    }

    @AppScope
    @Provides
    fun provideGsonFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    fun provideScheduler(): AppSchedulers {
        return AppSchedulers()
    }

    @AppScope
    @Provides
    fun provideAdapterFactory(scheduler: AppSchedulers): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.createWithScheduler(scheduler.internetScheduler())
    }
}