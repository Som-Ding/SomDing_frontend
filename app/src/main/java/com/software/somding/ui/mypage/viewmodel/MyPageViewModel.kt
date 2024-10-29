package com.software.somding.ui.mypage.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.software.somding.data.model.auth.LoginRequest
import com.software.somding.data.model.auth.LoginResponse
import com.software.somding.data.model.common.CommonResponse
import com.software.somding.data.model.home.CategoryProjectResponse
import com.software.somding.data.model.mypage.MyPageResponse
import com.software.somding.data.model.mypage.MyProjectOrderResponse
import com.software.somding.data.model.mypage.UpdateProfileDTO
import com.software.somding.data.repository.LoginRepository
import com.software.somding.data.repository.MyPageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import okhttp3.MultipartBody
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
	private val repository: MyPageRepository
) : ViewModel() {

	private val _myPageResponse = MutableLiveData<CommonResponse<MyPageResponse>?>()
	val myPageResponse: MutableLiveData<CommonResponse<MyPageResponse>?> = _myPageResponse

	private val _profileResponse = MutableLiveData<CommonResponse<String>?>()
	val profileResponse: MutableLiveData<CommonResponse<String>?> = _profileResponse

	private val _nickname = MutableLiveData<String>()
	val nickname: LiveData<String> get() = _nickname

	private val _myOrderProject = MutableLiveData<MyProjectOrderResponse?>()
	val myOrderProject: LiveData<MyProjectOrderResponse?> get() = _myOrderProject

	private val _scrapResponse = MutableLiveData<CategoryProjectResponse?>()
	val scrapResponse: LiveData<CategoryProjectResponse?> get() =  _scrapResponse

	fun getMyPage() {
		repository.getMyPage().observeForever {
			_myPageResponse.postValue(it)
		}
	}

	fun updateProfile(updateProfileDTO: UpdateProfileDTO, image: MultipartBody.Part?) {
		repository.updateProfile(updateProfileDTO, image.toString()).observeForever {
			_profileResponse.postValue(it)
		}
	}

	fun orderMyProject() {
		repository.orderMyProject().observeForever {
			_myOrderProject.postValue(it)
		}
	}

	fun getScrap() {
		repository.getScrap().observeForever {
			_scrapResponse.postValue(it)
		}
	}

}
