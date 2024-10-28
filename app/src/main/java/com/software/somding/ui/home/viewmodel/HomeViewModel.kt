package com.software.somding.ui.home.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.software.somding.data.model.home.CategoryProjectResponse
import com.software.somding.data.repository.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
	private val repository: CategoryRepository
) : ViewModel() {

	private val _popularProjects = MutableLiveData<CategoryProjectResponse?>()
	val popularProjects: MutableLiveData<CategoryProjectResponse?> = _popularProjects

	fun getPopularProjects(category: String, sort: String) {
		repository.getProjectsByCategory(category, sort).observeForever {
			_popularProjects.postValue(it)
		}
	}
}
