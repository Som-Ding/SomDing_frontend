package com.software.somding.ui.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.software.somding.data.model.auth.JoinRequest
import com.software.somding.data.model.common.CommonResponse
import com.software.somding.data.model.common.JoinResponse
import com.software.somding.data.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
@HiltViewModel
class JoinViewModel @Inject constructor(
	private val repository: LoginRepository
) : ViewModel() {
	private val _error = MutableLiveData<String?>()
	val error: LiveData<String?> get() = _error

	private val _email = MutableLiveData<String>()
	val email: MutableLiveData<String> = _email

	private val _currentPw = MutableLiveData<String>()
	val currentPw: MutableLiveData<String> = _currentPw

	private val _againPw = MutableLiveData<String>()
	val againPw: MutableLiveData<String> = _againPw

	private val _nickname = MutableLiveData<String>()
	val nickname: MutableLiveData<String> = _nickname

	private val _joinResponse = MutableLiveData<JoinResponse?>()
	val joinResponse: MutableLiveData<JoinResponse?> = _joinResponse

	fun join(joinRequest: JoinRequest) {
		repository.join(joinRequest).observeForever { response ->
			_joinResponse.postValue(response)
		}
	}

	fun setEmail(email: String) {
		if (!isValidEmail(email)) {
			_error.value = "유효하지 않은 이메일입니다."
		} else {
			_error.value = null // 유효한 이메일이면 null 설정
			_email.value = email
		}
	}

	private fun isValidEmail(email: String): Boolean {
		// 이메일 유효성 검사 로직
		return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
	}

	fun setCurrentPw(password: String) {
		_currentPw.value = password
	}

	fun setAgainPw(password: String) {
		_againPw.value = password
	}
}