package com.software.somding.ui.login.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.software.somding.data.model.auth.JoinRequest
import com.software.somding.data.model.common.CommonResponse
import com.software.somding.data.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class JoinViewModel @Inject constructor(
	private val repository: LoginRepository
) : ViewModel() {
	private val _email = MutableLiveData<String>()
	val email: MutableLiveData<String> = _email

	private val _currentPw = MutableLiveData<String>()
	val currentPw: MutableLiveData<String> = _currentPw

	private val _againPw = MutableLiveData<String>()
	val againPw: MutableLiveData<String> = _againPw

	private val _nickname = MutableLiveData<String>()
	val nickname: MutableLiveData<String> = _nickname

	private val _joinResponse = MutableLiveData<CommonResponse<String>?>()
	val joinResponse: MutableLiveData<CommonResponse<String>?> = _joinResponse

	fun join(joinRequest: JoinRequest) {
		repository.join(joinRequest).observeForever { response ->
			_joinResponse.postValue(response)
		}
	}

	fun setEmail(email: String) {
		_email.value = email
	}

	fun setCurrentPw(password: String) {
		_currentPw.value = password
	}

	fun setAgainPw(password: String) {
		_againPw.value = password
	}
}