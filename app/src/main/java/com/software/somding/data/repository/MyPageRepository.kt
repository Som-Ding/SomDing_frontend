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

	fun getMyPage(): MutableLiveData<CommonResponse<MyPageResponse>?> {
		val myPageResponse = MutableLiveData<CommonResponse<MyPageResponse>?>()

		myPageApi.getMyPage().enqueue(object : Callback<CommonResponse<MyPageResponse>> {
			override fun onResponse(call: Call<CommonResponse<MyPageResponse>>, response: Response<CommonResponse<MyPageResponse>>) {
				if (response.isSuccessful) {
					myPageResponse.postValue(response.body())
					Log.d("MyPageApi", "Response: $myPageResponse")
				} else {
					Log.e("MyPageApi", "Error: ${response.code()} - ${response.message()}")
				}
			}

			override fun onFailure(call: Call<CommonResponse<MyPageResponse>>, t: Throwable) {
				Log.e("MyPageApi", "Failure: ${t.message}")
			}
		})

		return myPageResponse
	}
}
