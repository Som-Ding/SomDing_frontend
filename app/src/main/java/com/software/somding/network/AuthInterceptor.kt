package com.software.somding.network

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

class AuthInterceptor(private val tokenProvider: () -> String?) : Interceptor {
	@Throws(IOException::class)
	override fun intercept(chain: Interceptor.Chain): Response {
		val originalRequest = chain.request()
		val token = tokenProvider()

		// 토큰이 존재할 경우 Authorization 헤더에 추가
		return if (token != null && token.isNotEmpty()) {
			val requestWithAuth = originalRequest.newBuilder()
				.header("Authorization", "Bearer $token")
				.build()
			chain.proceed(requestWithAuth)
		} else {
			chain.proceed(originalRequest)
		}
	}
}
/*
class AuthInterceptor @Inject constructor(
    private val prefs: SharedPreferences
) : Authenticator {
    override fun authenticate(route: Route?, response: Response): Request? {
        val originRequest = response.request

        if(originRequest.header("Authorization").isNullOrEmpty()){
            return null
        }
        val refreshToken = runBlocking {
            prefs.getString("refreshToken", "")
        }

        //재발급 api 요청하기
        val refreshRequest = Request.Builder()
            .url(BuildConfig.BASE_URL)
            .post("".toRequestBody())
            .addHeader("authorization", "Bearer ${refreshToken!!}")
            .build()
        val refreshResponse = OkHttpClient().newCall(refreshRequest).execute()

        //재발급에 성공한 경우
        if(refreshResponse.code == 201) {
            val gson = Gson()
            val refreshResponseJson = gson.fromJson(refreshResponse.body?.string(), Map::class.java)
            val newAccessToken = refreshResponseJson["accessToken"].toString()
            prefs.edit().putString("accessToken", newAccessToken).apply()
            val newRequest = originRequest.newBuilder()
                .removeHeader("Authorization")
                .addHeader("Authorization", "Bearer $newAccessToken")
                .build()
            return newRequest
        }else{//재발급 실패 - refreshToken 만료
            prefs.edit().remove("accessToken").apply()
            prefs.edit().remove("refreshToken").apply()
        }
        return null

    }

}*/
