package com.software.somding.presentation.mypage.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyPageViewModel : ViewModel() {

    private val _data = MutableLiveData<String>()
    val data: LiveData<String> = _data

    fun loadData() {
        // 여기에서는 간단히 로딩된 데이터를 LiveData에 업데이트합니다.
        _data.value = "로딩된 데이터"
    }
}