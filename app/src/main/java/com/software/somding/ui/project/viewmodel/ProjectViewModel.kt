package com.software.somding.ui.project.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.software.somding.data.model.common.CommonResponse
import com.software.somding.data.model.home.CategoryProjectResponse
import com.software.somding.data.model.project.ProjectDetail
import com.software.somding.data.model.project.Question
import com.software.somding.data.model.project.QuestionResponse
import com.software.somding.data.repository.ProjectRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import javax.inject.Inject

@HiltViewModel
class ProjectViewModel @Inject constructor(
	private val repository: ProjectRepository
) : ViewModel() {

	private val _projectDetail = MutableLiveData<ProjectDetail?>()
	val projectDetail: LiveData<ProjectDetail?> get() = _projectDetail

	private val _myProject = MutableLiveData<CategoryProjectResponse?>()
	val myProject: LiveData<CategoryProjectResponse?> get() = _myProject

	private val _createProjectResponse = MutableLiveData<CommonResponse<String>?>()
	val createProjectResponse: MutableLiveData<CommonResponse<String>?> get() = _createProjectResponse

	private val _question = MutableLiveData<QuestionResponse?>()
	val question: LiveData<QuestionResponse?> get() = _question

	private val _qna = MutableLiveData<CommonResponse<String>>()
	val qna: LiveData<CommonResponse<String>> get() = _qna

	private val _scrap = MutableLiveData<CommonResponse<String>?>()
	val scrap: MutableLiveData<CommonResponse<String>?> get() = _scrap

	fun getProjectsDetail(projectId: Int) {
		repository.getProjectDetail(projectId).observeForever { response ->
			_projectDetail.postValue(response?.result)
		}
	}

	fun createProject(
		projectReq: RequestBody,
		imageParts: MutableList<MultipartBody.Part>
	) {
		repository.createProject(projectReq, imageParts).observeForever { response ->
			_createProjectResponse.postValue(response)
			Log.d("projectViewModel", response?.result.toString())
		}
	}

	fun getMyProject() {
		repository.getMyProject().observeForever {
			_myProject.postValue(it)
		}
	}

	fun getAllQuestion(projectId: Int) {
		repository.getAllQuestion(projectId).observeForever { response ->
			_question.postValue(response)
		}
	}

	fun createQuestion(projectId: Int, title: String, question: String) {
		repository.createQuestion(projectId, title, question).observeForever {
			_qna.postValue(it)
		}
	}

	fun getScrap(projectId: Int) {
		repository.getScrap(projectId).observeForever { response ->
			_scrap.postValue(response)
		}
	}
}
