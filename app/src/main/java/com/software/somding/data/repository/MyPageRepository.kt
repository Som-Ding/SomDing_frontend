package com.software.somding.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.software.somding.data.model.auth.LoginRequest
import com.software.somding.data.model.auth.LoginResponse
import com.software.somding.data.model.common.CommonResponse
import com.software.somding.data.model.mypage.MyPageResponse
import com.software.somding.network.api.LoginApi
import com.software.somding.network.api.MyPageApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MyPageRepository @Inject constructor(
	private val myPageApi: MyPageApi
) {
	private val _error: MutableLiveData<String> = MutableLiveData()
	val error: LiveData<String>
		get() = _error

	fun getMyPage(): MutableLiveData<MyPageResponse?> {
		val myPageResponse = MutableLiveData<MyPageResponse?>()

		myPageApi.getMyPage().enqueue(object : Callback<CommonResponse<MyPageResponse>> {
			override fun onResponse(call: Call<CommonResponse<MyPageResponse>>, response: Response<CommonResponse<MyPageResponse>>) {
				if (response.isSuccessful) {
					myPageResponse.postValue(response.body()?.result)
					Log.d("MyPage", "${response.body()}")
				} else {
					myPageResponse.value = null
					Log.e("MyPage", "Error code: ${response.code()}, Error message: ${response.message()}")
					Log.d("MyPage", "음")
				}
			}

			override fun onFailure(call: Call<CommonResponse<MyPageResponse>>, t: Throwable) {
				_error.postValue("네트워크 오류: ${t.message}")
				Log.d("My Page", "${t.message}")
			}
		})

		return myPageResponse
	}
}
