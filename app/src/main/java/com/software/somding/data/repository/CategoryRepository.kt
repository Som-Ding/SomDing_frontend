package com.software.somding.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.software.somding.data.model.common.CommonResponse
import com.software.somding.data.model.home.CategoryProjectResponse
import com.software.somding.network.ApiClient
import com.software.somding.network.api.CategoryApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CategoryRepository @Inject constructor(
	private val categoryApi: CategoryApi
) {
	private val _error: MutableLiveData<String> = MutableLiveData()
	val error: LiveData<String>
		get() = _error

	fun getProjectsByCategory(
		category: String,
		sort: String
	): MutableLiveData<CommonResponse<CategoryProjectResponse>?> {
		val responseLiveData = MutableLiveData<CommonResponse<CategoryProjectResponse>?>()

		categoryApi.getProjectsByCategory(category, sort)
			.enqueue(object : Callback<CommonResponse<CategoryProjectResponse>> {
				override fun onResponse(
					call: Call<CommonResponse<CategoryProjectResponse>>,
					response: Response<CommonResponse<CategoryProjectResponse>>
				) {
					if (response.isSuccessful) {
						responseLiveData.postValue(response.body())
					} else {
						responseLiveData.value = null
					}
				}

				override fun onFailure(
					call: Call<CommonResponse<CategoryProjectResponse>>,
					t: Throwable
				) {
					_error.postValue("네트워크 오류: ${t.message}")
					Log.d("home", "${t.message}")
				}
			})

		return responseLiveData
	}
}