package com.software.somding.ui.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.software.somding.R
import com.software.somding.data.model.auth.JoinRequest
import com.software.somding.databinding.FragmentJoinNicknameBinding
import com.software.somding.ui.common.BaseFragment
import com.software.somding.ui.common.NavigationUtil.navigate
import com.software.somding.ui.login.viewmodel.JoinViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JoinNicknameFragment : BaseFragment<FragmentJoinNicknameBinding>(R.layout.fragment_join_nickname) {
	private val joinViewModel: JoinViewModel by activityViewModels()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		binding.etCurrentNickname.addTextChangedListener(object : TextWatcher {
			override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
			}

			override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
			}

			override fun afterTextChanged(p0: Editable?) {
				joinViewModel.nickname.value = p0.toString()
			}
		})

		binding.btnNext.setOnClickListener {
			val joinRequest = JoinRequest(
				email = joinViewModel.email.value ?: "",
				nickname = joinViewModel.nickname.value ?: "",
				password = joinViewModel.againPw.value ?: ""
			)

			joinViewModel.join(joinRequest)
		}

		// joinResponse 관찰 및 처리
		joinViewModel.joinResponse.observe(viewLifecycleOwner) { response ->
			response?.let {
				if (response.code == "200") {
					val navOptions = navOptions {
						popUpTo(R.id.loginOrJoinFragment) { inclusive = false }
					}
					findNavController().navigate(R.id.action_joinNicknameFragment_to_joinFragment, null, navOptions)
				} else {
					binding.etCurrentNickname.error = it.message ?: "회원가입에 실패했습니다."
				}
			}
		}
	}
}