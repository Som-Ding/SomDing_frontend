package com.software.somding.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.software.somding.data.model.common.CommonResponse
import com.software.somding.data.model.mypage.MyPageResponse
import com.software.somding.data.model.mypage.UpdateProfileDTO
import com.software.somding.network.api.MyPageApi
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MyPageRepository @Inject constructor(
	private val myPageApi: MyPageApi
) {
	private val _error: MutableLiveData<String> = MutableLiveData()
	val error: LiveData<String>
		get() = _error

	private val _nickname = MutableLiveData<String>()
	val nickname: LiveData<String> get() = _nickname

	fun getMyPage(): MutableLiveData<CommonResponse<MyPageResponse>?> {
		val myPageResponse = MutableLiveData<CommonResponse<MyPageResponse>?>()

		myPageApi.getMyPage().enqueue(object : Callback<CommonResponse<MyPageResponse>> {
			override fun onResponse(
				call: Call<CommonResponse<MyPageResponse>>,
				response: Response<CommonResponse<MyPageResponse>>
			) {
				if (response.isSuccessful) {
					myPageResponse.postValue(response.body())
					_nickname.value = response.body()?.result?.nickname // 닉네임 업데이트
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
	fun updateProfile(updateProfileDTO: UpdateProfileDTO, imagePath: String?): MutableLiveData<CommonResponse<String>?> {
		val profileResponse = MutableLiveData<CommonResponse<String>?>()

		val updateProfileJson = Gson().toJson(updateProfileDTO)
		val updateProfileBody = RequestBody.create("application/json".toMediaTypeOrNull(), updateProfileJson)

		val imagePart = if (!imagePath.isNullOrEmpty()) {
			val imageFile = File(imagePath)
			val requestFile = RequestBody.create("multipart/form-data".toMediaTypeOrNull(), imageFile)
			MultipartBody.Part.createFormData("image", imageFile.name, requestFile)
		} else {
			null
		}

		// API 호출
		myPageApi.updateProfile(updateProfileBody, imagePart)
			.enqueue(object : Callback<CommonResponse<String>?> {
				override fun onResponse(
					call: Call<CommonResponse<String>?>,
					response: Response<CommonResponse<String>?>
				) {
					if (response.isSuccessful) {
						profileResponse.postValue(response.body())
//						_nickname.value = updateProfileDTO.nickname
						getMyPage()
						Log.d("MyPageApi: updateProfile", "Response: ${profileResponse.value}")
					} else {
						Log.e(
							"MyPageApi: updateProfile",
							"Error: ${response.code()} - ${response.message()}"
						)
					}
				}

				override fun onFailure(call: Call<CommonResponse<String>?>, t: Throwable) {
					Log.e("MyPageApi: updateProfile", "Failure: ${t.message}")
				}
			})

		return profileResponse
	}

}
