package com.software.somding.ui.login.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.software.somding.data.model.auth.LoginRequest
import com.software.somding.data.model.auth.LoginResponse
import com.software.somding.data.model.common.CommonResponse
import com.software.somding.data.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
	private val repository: LoginRepository
) : ViewModel() {

	private val _loginResponse = MutableLiveData<CommonResponse<LoginResponse>?>()
	val loginResponse: MutableLiveData<CommonResponse<LoginResponse>?> = _loginResponse

	fun login(loginRequest: LoginRequest) {
		repository.login(loginRequest).observeForever { response ->
			_loginResponse.postValue(response)
		}
	}
}
