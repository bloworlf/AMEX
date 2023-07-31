package io.drdroid.amex.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.drdroid.amex.data.impl.RepositoryImpl
import io.drdroid.amex.data.network.ApiCall
import io.drdroid.amex.data.repo.Repository
import io.drdroid.amex.utils.MockInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun providesOkHttpInstance(): OkHttpClient {
        return OkHttpClient.Builder()
//            .addInterceptor(HttpLoggingInterceptor().apply {
//                level = HttpLoggingInterceptor.Level.BODY
//            })
            .addInterceptor(MockInterceptor().apply {
//                level = Level.BODY
            })
            .build()
    }

    @Provides
    fun providesRetrofitInstance(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://www.google.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    fun providesTvShowApi(retrofit: Retrofit): ApiCall {
        return retrofit.create(ApiCall::class.java)
    }

    @Provides
    fun providesRepository(apiCall: ApiCall): Repository {
        return RepositoryImpl(apiCall)
    }
}