package com.software.somding.ui.project.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.software.somding.data.model.home.CategoryProjectResponse
import com.software.somding.data.model.project.ProjectDetail
import com.software.somding.data.model.project.ProjectDetailResponse
import com.software.somding.data.repository.ProjectRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProjectViewModel @Inject constructor(
	private val repository: ProjectRepository
) : ViewModel() {

	private val _projectDetail = MutableLiveData<ProjectDetail?>()
	val projectDetail: LiveData<ProjectDetail?> get() = _projectDetail

	private val _myProject = MutableLiveData<CategoryProjectResponse?>()
	val myProject: LiveData<CategoryProjectResponse?> get() = _myProject

	fun getProjectsDetail(projectId: Int) {
		Log.d("왜 안 돼~~~~~", "$projectId")
		repository.getProjectDetail(projectId).observeForever { response ->
			_projectDetail.postValue(response?.result)
		}
	}

	fun getMyProject() {
		repository.getMyProject().observeForever {
			_myProject.postValue(it)
		}
	}
}
