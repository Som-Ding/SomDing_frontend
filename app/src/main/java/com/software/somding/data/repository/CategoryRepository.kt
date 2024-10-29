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

	/***
	 * 카테고리별 프로젝트 조회 api
	 */
	fun getProjectsByCategory(
		category: String,
		sort: String
	): LiveData<CategoryProjectResponse?> {
		val responseLiveData = MutableLiveData<CategoryProjectResponse?>()

		categoryApi.getProjectsByCategory(category, sort)
			.enqueue(object : Callback<CategoryProjectResponse> {
				override fun onResponse(
					call: Call<CategoryProjectResponse>,
					response: Response<CategoryProjectResponse>
				) {
					if (response.isSuccessful) {
						responseLiveData.postValue(response.body())
						Log.d("CategoryRepository", "여기 맞나?: ${response.body()}")
					} else {
						responseLiveData.value = null
						val errorMsg = response.errorBody()?.string() ?: "Unknown error"
						Log.d("CategoryRepository", "Error: ${response.code()} - $errorMsg")					}
				}

				override fun onFailure(
					call: Call<CategoryProjectResponse>,
					t: Throwable
				) {
					_error.postValue("네트워크 오류: ${t.message}")
					Log.d("CategoryRepository", "Network error: ${t.message}")
				}
			})

		return responseLiveData
	}
}
