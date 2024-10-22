package com.software.somding.network

import android.content.SharedPreferences
import com.google.gson.Gson
import com.software.somding.BuildConfig
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

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

}