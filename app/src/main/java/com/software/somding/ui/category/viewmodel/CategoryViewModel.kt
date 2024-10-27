package com.software.somding.ui.category.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.software.somding.data.model.common.CommonResponse
import com.software.somding.data.model.home.CategoryProjectData
import com.software.somding.data.model.home.CategoryProjectResponse
import com.software.somding.data.repository.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
	private val repository: CategoryRepository
) : ViewModel() {

	private val _categoryProjects = MutableLiveData<CategoryProjectResponse?>()
	val categoryProjects: MutableLiveData<CategoryProjectResponse?> = _categoryProjects

	fun getProjectsByCategory(category: String, sort: String) {
		repository.getProjectsByCategory(category, sort).observeForever { response ->
			_categoryProjects.postValue(response)
		}
	}

/*	fun remainingDays(data: CategoryProjectData): String {
		val currentTime = System.currentTimeMillis()
		val remainingTimeMillis = data.targetDate - currentTime // targetDate는 종료일의 타임스탬프 (Long)

		// 남은 일수를 계산하고, 결과 문자열 반환
		val remainingDays = TimeUnit.MILLISECONDS.toDays(remainingTimeMillis).toInt()
		return if (remainingDays > 0) {
			"$remainingDays일 남음"
		} else {
			"종료"
		}
	}*/
}
