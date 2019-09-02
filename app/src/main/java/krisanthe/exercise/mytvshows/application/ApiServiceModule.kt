package krisanthe.exercise.mytvshows.application

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import krisanthe.exercise.mytvshows.api.ShowApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiServiceModule {

    private val BASE_URL = "http://api.tvmaze.com/"

    @AppScope
    @Provides
    fun provideNumberApi(
        client: OkHttpClient,
        gson: GsonConverterFactory,
        adapterFactory: RxJava2CallAdapterFactory
    ): ShowApi {
        val retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(gson)
            .addCallAdapterFactory(adapterFactory)
            .build()

        return retrofit.create(ShowApi::class.java)
    }
}