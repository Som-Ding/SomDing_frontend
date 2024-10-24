package com.software.somding.ui.mypage.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.software.somding.data.model.auth.LoginRequest
import com.software.somding.data.model.auth.LoginResponse
import com.software.somding.data.model.common.CommonResponse
import com.software.somding.data.model.mypage.MyPageResponse
import com.software.somding.data.repository.LoginRepository
import com.software.somding.data.repository.MyPageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
	private val repository: MyPageRepository
) : ViewModel() {

	private val _myPageResponse = MutableLiveData<MyPageResponse?>()
	val myPageResponse: MutableLiveData<MyPageResponse?> = _myPageResponse

	fun getMyPage() {
		repository.getMyPage().observeForever {
			_myPageResponse.postValue(it)
		}
	}
}
