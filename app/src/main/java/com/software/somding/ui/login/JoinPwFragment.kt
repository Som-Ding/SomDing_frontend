package com.software.somding.ui.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
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
				joinViewModel.setCurrentPw(p0.toString())
			}
		})
		// 다음 버튼 클릭 리스너
		binding.btnNext.setOnClickListener {
//			if (isPasswordValid(joinViewModel.currentPw.value)) {
				navigate(R.id.action_joinPwFragment_to_joinPwAgainFragment)
//			} else {
//				binding.currentPw.error = "비밀번호는 6자 이상이어야 합니다."
//			}
		}
	}

	// 비밀번호 유효성 검사 함수
	private fun isPasswordValid(password: String?): Boolean {
		return !password.isNullOrEmpty() && password.length >= 6
	}
}