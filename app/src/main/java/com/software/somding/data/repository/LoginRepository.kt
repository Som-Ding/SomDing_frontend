package com.software.somding.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.software.somding.data.model.auth.JoinRequest
import com.software.somding.data.model.auth.LoginRequest
import com.software.somding.data.model.auth.LoginResponse
import com.software.somding.data.model.common.CommonResponse
import com.software.somding.data.model.common.JoinResponse
import com.software.somding.network.api.LoginApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginRepository @Inject constructor(
	private val loginApi: LoginApi
) {
	private val _error: MutableLiveData<String> = MutableLiveData()
	val error: LiveData<String>
		get() = _error

	fun login(loginRequest: LoginRequest): MutableLiveData<CommonResponse<LoginResponse>?> {
		val loginResponseLiveData = MutableLiveData<CommonResponse<LoginResponse>?>()

		// 로그인 API 호출
		loginApi.login(loginRequest).enqueue(object : Callback<CommonResponse<LoginResponse>> {
			override fun onResponse(
				call: Call<CommonResponse<LoginResponse>>,
				response: Response<CommonResponse<LoginResponse>>
			) {
				if (response.isSuccessful) {
					loginResponseLiveData.value = response.body()
					Log.d("login", "${response.body()}")
				} else {
					loginResponseLiveData.value = null
					val errorMsg = response.errorBody()?.string() ?: "Unknown error"
					Log.d("login", "Error: ${response.code()} - $errorMsg")
				}
			}

			override fun onFailure(call: Call<CommonResponse<LoginResponse>>, t: Throwable) {
				_error.postValue("네트워크 오류: ${t.message}")
				Log.d("login", "${t.message}")
			}
		})

		return loginResponseLiveData
	}

	fun join(joinRequest: JoinRequest): MutableLiveData<JoinResponse?> {
		val joinResponseLiveData = MutableLiveData<JoinResponse?>()

		// 회원가입 API 호출
		loginApi.join(joinRequest).enqueue(object : Callback<JoinResponse> {
			override fun onResponse(
				call: Call<JoinResponse>,
				response: Response<JoinResponse>
			) {
				if (response.isSuccessful) {
					joinResponseLiveData.value = response.body()
					Log.d("join", "${response.body()}")
				} else {
					joinResponseLiveData.value = null
					val errorMsg = response.errorBody()?.string() ?: "Unknown error"
					Log.d("join", "Error: ${response.code()} - $errorMsg")
				}
			}

			override fun onFailure(call: Call<JoinResponse>, t: Throwable) {
				_error.postValue("네트워크 오류: ${t.message}")
				Log.d("join", "${t.message}")
			}
		})

		return joinResponseLiveData
	}
}
