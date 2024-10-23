//package com.software.somding.network
//
//import android.content.SharedPreferences
//import com.google.android.datatransport.runtime.dagger.Provides
//import com.software.somding.BuildConfig
//import dagger.Module
//import dagger.hilt.InstallIn
//import dagger.hilt.components.SingletonComponent
//import okhttp3.Authenticator
//import okhttp3.Interceptor
//import okhttp3.OkHttpClient
//import okhttp3.logging.HttpLoggingInterceptor
//import retrofit2.Converter
//import retrofit2.Retrofit
//import retrofit2.converter.gson.GsonConverterFactory
//import javax.inject.Singleton
//
//@Module
//@InstallIn(SingletonComponent::class)
//object AppModule {
//
//    @Singleton
//    @Provides
//    fun provideHeaderInterceptor(autoLoginPreferences: SharedPreferences): HeaderInterceptor {
//        return HeaderInterceptor(autoLoginPreferences)
//    }
//
//    @Singleton
//    @Provides
//    fun provideAuthInterceptor(autoLoginPreferences: SharedPreferences): Authenticator {
//        return AuthInterceptor(autoLoginPreferences)
//    }
//
//    @Singleton
//    @Provides
//    fun provideInterceptor(): Interceptor {
//        return HttpLoggingInterceptor()
//            .setLevel(HttpLoggingInterceptor.Level.BODY)
//    }
//
//    @Singleton
//    @Provides
//    fun provideOkHttpClient(headerInterceptor: HeaderInterceptor, authInterceptor: AuthInterceptor): OkHttpClient {
//        val httpLoggingInterceptor = HttpLoggingInterceptor()
//            .setLevel(HttpLoggingInterceptor.Level.BODY)
//        return OkHttpClient.Builder()
//            .addInterceptor(httpLoggingInterceptor)
//            .addInterceptor(headerInterceptor)
//            .authenticator(authInterceptor)
//            .build()
//    }
//
//    @Singleton
//    @Provides
//    fun provideConverterFactory(): Converter.Factory{
//        return GsonConverterFactory.create()
//    }
//    @Singleton
//    @Provides
//    fun provideRetrofit(
//        okHttpClient: OkHttpClient,
//        converterFactory: Converter.Factory
//    ): Retrofit {
//        return Retrofit.Builder()
//            .addConverterFactory(converterFactory)
//            .client(okHttpClient)
//            .baseUrl(BuildConfig.BASE_URL)
//            .build()
//    }
//
//    @Singleton
//    @Provides
//    inline fun <reified T> createService(retrofit: Retrofit): T {
//	    return retrofit.create(T::class.java)
//    }
//}