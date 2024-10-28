package com.software.somding.ui.mypage

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.MutableLiveData
import com.software.somding.R
import com.software.somding.data.model.common.CommonResponse
import com.software.somding.data.model.mypage.MyPageResponse
import com.software.somding.data.model.mypage.UpdateProfileDTO
import com.software.somding.databinding.FragmentMyPageProfileBinding
import com.software.somding.ui.common.BaseFragment
import com.software.somding.ui.common.NavigationUtil.navigate
import com.software.somding.ui.mypage.viewmodel.MyPageViewModel
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.MultipartBody


@AndroidEntryPoint
class MyPageProfileFragment :
	BaseFragment<FragmentMyPageProfileBinding>(R.layout.fragment_my_page_profile) {
	private val viewModel: MyPageViewModel by viewModels()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		viewModel.getMyPage()
		viewModel.myPageResponse.observe(viewLifecycleOwner) { response ->
			if (response != null) {
				binding.etNewPwAgain.text =
					Editable.Factory.getInstance().newEditable(response.result.nickname)
			} else {
				binding.etNewPwAgain.text = Editable.Factory.getInstance().newEditable("error")
			}
		}

		val nickname = binding.etNewPwAgain.text.toString()
		val image: MultipartBody.Part? = null

		val updateProfileDTO = UpdateProfileDTO(nickname)
		viewModel.updateProfile(updateProfileDTO, image)
		viewModel.nickname.observe(viewLifecycleOwner) { updatedNickname ->
			updatedNickname?.let {
				binding.etNewPwAgain.text = Editable.Factory.getInstance().newEditable(it)
			}
		}

		binding.btnComplete.setOnClickListener {
			viewModel.profileResponse.observe(viewLifecycleOwner) { response ->
				response?.let {
					if (it.code.equals(200)) {
						Log.d("MyPageProfileFragment", "Profile updated successfully: ${it.result}")
					} else {
						Log.e("MyPageProfileFragment", "Error updating profile: ${it.message}")
					}
				}
			}
			navigate(R.id.action_myPageProfileFragment_to_myPageFragment)
		}
	}

}
