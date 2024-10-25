package com.software.somding.network

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.software.somding.BuildConfig
import com.software.somding.network.api.CategoryApi
import com.software.somding.network.api.LoginApi
import com.software.somding.network.api.MyPageApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

	@Provides
	fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
		return context.getSharedPreferences("auth", Context.MODE_PRIVATE)
	}

	@Provides
	fun provideAuthInterceptor(sharedPreferences: SharedPreferences): AuthInterceptor {
		return AuthInterceptor(sharedPreferences)
	}

	@Provides
	@Singleton
	fun provideOkHttpClient(headerInterceptor: HeaderInterceptor, authInterceptor: AuthInterceptor): OkHttpClient {
		return OkHttpClient.Builder()
			.addInterceptor(headerInterceptor)
			.addInterceptor(authInterceptor)
			.build()
	}

	@Provides
	@Singleton
	fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
		return Retrofit.Builder()
			.client(okHttpClient)
			.baseUrl(BuildConfig.BASE_URL)
			.addConverterFactory(GsonConverterFactory.create())
			.build()
	}

	@Provides
	@Singleton
	fun provideCategoryApi(retrofit: Retrofit): CategoryApi {
		return retrofit.create(CategoryApi::class.java)
	}

	@Provides
	@Singleton
	fun provideLoginApi(retrofit: Retrofit): LoginApi {
		return retrofit.create(LoginApi::class.java)
	}

	@Provides
	@Singleton
	fun provideMyPageApi(retrofit: Retrofit): MyPageApi {
		return retrofit.create(MyPageApi::class.java)
	}
}
