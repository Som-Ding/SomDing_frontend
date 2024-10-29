package com.software.somding.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.software.somding.data.model.common.CommonResponse
import com.software.somding.data.model.home.CategoryProjectResponse
import com.software.somding.data.model.project.CreateQuestionRequestDTO
import com.software.somding.data.model.project.ProjectDetailResponse
import com.software.somding.data.model.project.QuestionResponse
import com.software.somding.network.api.ProjectApi
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
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

	/***
	 * 프로젝트 상세조회 api
	 */
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

	/***
	 * 프로젝트 생성 api
	 */
	fun createProject(
		titlePart: RequestBody,
		introducePart: RequestBody,
		policyPart: RequestBody,
		schedulePart: RequestBody,
		categoryPart: RequestBody,
		targetPricePart: RequestBody,
		pricePart: RequestBody,
		targetDatePart: RequestBody,
		colorListPart: RequestBody,
		sizeListPart: RequestBody,
		otherListPart: RequestBody,
		imageParts: MutableList<MultipartBody.Part>
	): LiveData<CommonResponse<String>?> {
		val responseLiveData = MutableLiveData<CommonResponse<String>?>()

		projectApi.createProject(
			titlePart, introducePart, policyPart, schedulePart,
			categoryPart, targetPricePart, pricePart, targetDatePart,
			colorListPart, sizeListPart, otherListPart, imageParts
		)
			.enqueue(object : Callback<CommonResponse<String>> {
				override fun onResponse(
					call: Call<CommonResponse<String>>,
					response: Response<CommonResponse<String>>
				) {
					if (response.isSuccessful) {
						responseLiveData.postValue(response.body())
						Log.d("register", "성공 오우예 ${response.body()}")
					} else {
						responseLiveData.postValue(null)

						val errorMsg = response.errorBody()?.string() ?: "Unknown error"
						Log.d(
							"RegisterFragment",
							"Error: ${response.code()} - $errorMsg"
						)
					}
				}

				override fun onFailure(call: Call<CommonResponse<String>>, t: Throwable) {
					responseLiveData.postValue(null)
				}
			})

		return responseLiveData
	}

	/***
	 * 내가 등록한 프로젝트 조회 api
	 */
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
							Log.d(
								"ProjectRepository",
								"Response not successful: ${response.message()}"
							)
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


	/***
	 * 프로젝트 Q&A api
	 */
	fun getAllQuestion(projectId: Int): LiveData<QuestionResponse?> {
		val responseLiveData = MutableLiveData<QuestionResponse?>()
		projectApi.getAllQuestion(projectId)
			.enqueue(object : Callback<QuestionResponse> {
				override fun onResponse(
					call: Call<QuestionResponse>,
					response: Response<QuestionResponse>
				) {
					if (response.isSuccessful) {
						responseLiveData.postValue(response.body())
						Log.d("QnA", "Received data: ${response.body()}")
					} else {
						responseLiveData.value = null
						Log.d("QnA", "Response not successful: ${response.message()}")
					}
				}

				override fun onFailure(
					call: Call<QuestionResponse>,
					t: Throwable
				) {
					_error.postValue("네트워크 오류: ${t.message}")
					Log.d("QnA", "Network error: ${t.message}")
				}
			})
		return responseLiveData
	}


	fun createQuestion(
		projectId: Int,
		title: String,
		question: String
	): MutableLiveData<CommonResponse<String>?> {
		val responseLiveData = MutableLiveData<CommonResponse<String>?>()

		projectApi.createQuestion(
			request = CreateQuestionRequestDTO(
				question = question,
				title = title,
				projectId = projectId
			)
		)
			.enqueue(object : Callback<CommonResponse<String>> {
				override fun onResponse(
					call: Call<CommonResponse<String>>,
					response: Response<CommonResponse<String>>
				) {
					if (response.isSuccessful) {
						responseLiveData.postValue(response.body())
						Log.d("QnA: create", "Received data: ${response.body()}")
					} else {
						responseLiveData.value = null
						val errorMsg = response.errorBody()?.string() ?: "Unknown error"
						Log.d(
							"QnA: create",
							"Response not successful: ${response.code()} - $errorMsg"
						)
					}
				}

				override fun onFailure(
					call: Call<CommonResponse<String>>,
					t: Throwable
				) {
					_error.postValue("네트워크 오류: ${t.message}")
					Log.d("QnA", "Network error: ${t.message}")
				}
			})
		return responseLiveData
	}
}
