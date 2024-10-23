package com.software.somding.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.software.somding.data.model.auth.LoginRequest
import com.software.somding.data.model.auth.LoginResponse
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

	fun login(loginRequest: LoginRequest): MutableLiveData<LoginResponse?> {
		val loginResponseLiveData = MutableLiveData<LoginResponse?>()

		// 로그인 API 호출
		loginApi.login(loginRequest).enqueue(object : Callback<LoginResponse> {
			override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
				if (response.isSuccessful) {
					loginResponseLiveData.value = response.body()
					Log.d("login", "${response.body()}")
				} else {
					loginResponseLiveData.value = null
					Log.d("login", "음ㄴ")
				}
			}

			override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
				_error.postValue("네트워크 오류: ${t.message}")
				Log.d("login", "${t.message}")
			}
		})

		return loginResponseLiveData
	}
}
