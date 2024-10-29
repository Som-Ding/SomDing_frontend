package com.software.somding.ui.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.software.somding.R
import com.software.somding.databinding.FragmentJoinPwBinding
import com.software.somding.ui.common.BaseFragment
import com.software.somding.ui.common.NavigationUtil.navigate
import com.software.somding.ui.login.viewmodel.JoinViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JoinPwFragment : BaseFragment<FragmentJoinPwBinding>(R.layout.fragment_join_pw) {
	private val joinViewModel: JoinViewModel by activityViewModels()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		binding.etCurrentPw.addTextChangedListener(object : TextWatcher {
			override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
			}

			override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
			}

			override fun afterTextChanged(p0: Editable?) {
				joinViewModel.setCurrentPw(binding.etCurrentPw.text.toString())
			}
		})

		// 다음 버튼 클릭 리스너
		binding.btnNext.setOnClickListener {
			if (binding.etCurrentPw.text.isNullOrEmpty()) {
				Toast.makeText(requireContext(), "필수 입력 값입니다.", Toast.LENGTH_SHORT).show()
				return@setOnClickListener // 넘어가지 않도록 반환
			}

			if (isPasswordValid(joinViewModel.currentPw.value)) {
				joinViewModel.setCurrentPw(binding.etCurrentPw.text.toString())
				Log.d(" 엥1", joinViewModel.currentPw.value.toString())
				navigate(R.id.action_joinPwFragment_to_joinPwAgainFragment)
			} else {
				Toast.makeText(requireContext(), "비밀번호는 6자 이상이어야 합니다.", Toast.LENGTH_SHORT).show()
				return@setOnClickListener // 넘어가지 않도록 반환
			}
		}
	}

	// 비밀번호 유효성 검사 함수
	private fun isPasswordValid(password: String?): Boolean {
		return !password.isNullOrEmpty() && password.length >= 6
	}
}