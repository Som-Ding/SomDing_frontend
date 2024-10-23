package com.software.somding.ui.category.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.software.somding.data.model.home.CategoryProjectResponse
import com.software.somding.data.repository.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
	private val repository: CategoryRepository
) : ViewModel() {

	private val _categoryProjects = MutableLiveData<CategoryProjectResponse?>()
	val categoryProjects: MutableLiveData<CategoryProjectResponse?> = _categoryProjects

	fun getProjectsByCategory(category: String, sort: String) {
		repository.getProjectsByCategory(category, sort).observeForever { response ->
			_categoryProjects.value = response
		}
	}
}
