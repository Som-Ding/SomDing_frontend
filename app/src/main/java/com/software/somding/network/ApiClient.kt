/*
package com.software.somding.network

import com.software.somding.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
	private const val BASE_URL = BuildConfig.BASE_URL
//	val cookieManager = CookieManager()

	private var builder = OkHttpClient().newBuilder()
	private var okHttpClient = builder
//		.cookieJar(JavaNetCookieJar(CookieManager()))
		.addInterceptor(HeaderInterceptor)
		.addInterceptor
		.build()

	internal val retrofit: Retrofit by lazy {
		Retrofit.Builder()
			.baseUrl(BASE_URL)
			.client(okHttpClient)
			.addConverterFactory(GsonConverterFactory.create())
			.build()
	}

	internal inline fun <reified T> createService(): T {
		return retrofit.create(T::class.java)
	}

}*/
package com.software.somding.network

import HeaderInterceptor
import HeaderInterceptor.prefs
import com.software.somding.BuildConfig.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {
	private var builder = OkHttpClient().newBuilder()

	private fun getToken(): String? {
		return prefs.getString("accessToken", null)
	}

	// OkHttpClient 설정
	private var okHttpClient = builder
			.addInterceptor(AuthInterceptor(::getToken)) // AuthInterceptor 추가
			.addInterceptor(HeaderInterceptor) // HeaderInterceptor 추가
			.connectTimeout(30, TimeUnit.SECONDS) // 연결 타임아웃 설정
			.readTimeout(30, TimeUnit.SECONDS) // 읽기 타임아웃 설정
			.writeTimeout(30, TimeUnit.SECONDS) // 쓰기 타임아웃 설정
			.build()

	// Retrofit 설정
	private val retrofit: Retrofit by lazy {
		Retrofit.Builder()
			.baseUrl(BASE_URL)
			.client(okHttpClient)
			.addConverterFactory(GsonConverterFactory.create())
			.build()
	}

	// Retrofit 서비스 생성 함수
	internal inline fun <reified T> createService(): T {
		return retrofit.create(T::class.java)
	}
}
