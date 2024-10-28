package com.software.somding.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.software.somding.data.model.home.CategoryProjectResponse
import com.software.somding.data.model.project.ProjectDetailResponse
import com.software.somding.network.api.ProjectApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProjectRepository @Inject constructor(
	private val projectApi: ProjectApi
) {
	private val _error: MutableLiveData<String> = MutableLiveData()
	val error: LiveData<String> get() = _error

	fun getProjectDetail(projectId: Int): LiveData<ProjectDetailResponse?> {
		val responseLiveData = MutableLiveData<ProjectDetailResponse?>()
			projectApi.getProjectDetail(projectId)
				.enqueue(object : Callback<ProjectDetailResponse> {
					override fun onResponse(
						call: Call<ProjectDetailResponse>,
						response: Response<ProjectDetailResponse>
					) {
						if (response.isSuccessful) {
							responseLiveData.postValue(response.body())
							Log.d("ProjectRepository", "Received data: ${response.body()}")
						} else {
							responseLiveData.value = null
							Log.d("ProjectRepository", "Response not successful: ${response.message()}")
						}
					}

					override fun onFailure(
						call: Call<ProjectDetailResponse>,
						t: Throwable
					) {
						_error.postValue("네트워크 오류: ${t.message}")
						Log.d("ProjectRepository", "Network error: ${t.message}")
					}
				})
		return responseLiveData
	}

	fun getMyProject(): LiveData<CategoryProjectResponse?> {
		val responseLiveData = MutableLiveData<CategoryProjectResponse?>()

		try {
			projectApi.getMyProject()
				.enqueue(object : Callback<CategoryProjectResponse> {
					override fun onResponse(
						call: Call<CategoryProjectResponse>,
						response: Response<CategoryProjectResponse>
					) {
						if (response.isSuccessful) {
							responseLiveData.postValue(response.body())
							Log.d("ProjectRepository", "Received data: ${response.body()}")
						} else {
							responseLiveData.value = null
							Log.d("ProjectRepository", "Response not successful: ${response.message()}")
						}
					}

					override fun onFailure(
						call: Call<CategoryProjectResponse>,
						t: Throwable
					) {
						_error.postValue("네트워크 오류: ${t.message}")
						Log.d("ProjectRepository", "Network error: ${t.message}")
					}
				})
		} catch (e: Exception) {
			_error.postValue("예외 발생: ${e.message}")
			Log.e("ProjectRepository", "Exception: ${e.message}")
		}

		return responseLiveData
	}

}
