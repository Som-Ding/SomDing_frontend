package com.software.somding.ui.project.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.software.somding.data.model.home.CategoryProjectResponse
import com.software.somding.data.model.project.ProjectDetail
import com.software.somding.data.model.project.Question
import com.software.somding.data.model.project.QuestionResponse
import com.software.somding.data.repository.ProjectRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

@HiltViewModel
class ProjectViewModel @Inject constructor(
	private val repository: ProjectRepository
) : ViewModel() {

	private val _projectDetail = MutableLiveData<ProjectDetail?>()
	val projectDetail: LiveData<ProjectDetail?> get() = _projectDetail

	private val _myProject = MutableLiveData<CategoryProjectResponse?>()
	val myProject: LiveData<CategoryProjectResponse?> get() = _myProject

	private val _createProjectResponse = MutableLiveData<String?>()
	val createProjectResponse: LiveData<String?> get() = _createProjectResponse

	private val _question = MutableLiveData<QuestionResponse?>()
	val question: LiveData<QuestionResponse?> get() = _question

	fun getProjectsDetail(projectId: Int) {
		repository.getProjectDetail(projectId).observeForever { response ->
			_projectDetail.postValue(response?.result)
		}
	}

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
	) {
		repository.createProject(
			titlePart, introducePart, policyPart, schedulePart,
			categoryPart, targetPricePart, pricePart, targetDatePart,
			colorListPart, sizeListPart, otherListPart, imageParts).observeForever { response ->
			_createProjectResponse.postValue(response.toString())
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
}
