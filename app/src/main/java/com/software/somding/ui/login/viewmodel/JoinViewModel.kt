package com.software.somding.ui.login.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.software.somding.data.model.auth.LoginResponse
import com.software.somding.data.model.common.CommonResponse

class JoinViewModel: ViewModel() {
	private val _currentPw = MutableLiveData<String>()
	val currentPw: MutableLiveData<String> = _currentPw

	private val _againPw = MutableLiveData<String>()
	val againPw: MutableLiveData<String> = _againPw

	private val _nickname = MutableLiveData<String>()
	val nickname: MutableLiveData<String> = _nickname

}