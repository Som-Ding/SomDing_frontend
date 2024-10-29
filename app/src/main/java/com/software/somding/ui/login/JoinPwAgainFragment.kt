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
import com.software.somding.databinding.FragmentJoinPwAgainBinding
import com.software.somding.ui.common.BaseFragment
import com.software.somding.ui.common.NavigationUtil.navigate
import com.software.somding.ui.login.viewmodel.JoinViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JoinPwAgainFragment : BaseFragment<FragmentJoinPwAgainBinding>(R.layout.fragment_join_pw_again) {
	private val joinViewModel: JoinViewModel by activityViewModels()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		binding.etAgainPw.addTextChangedListener(object : TextWatcher {
			override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
			}

			override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
			}

			override fun afterTextChanged(p0: Editable?) {
				joinViewModel.setAgainPw(binding.etAgainPw.text.toString())
			}
		})

		binding.btnNext.setOnClickListener {
			if (binding.etAgainPw.text.isNullOrEmpty()) {
				Toast.makeText(requireContext(), "필수 입력 값입니다.", Toast.LENGTH_SHORT).show()
				return@setOnClickListener // 넘어가지 않도록 반환
			}

			if (binding.etAgainPw.text.toString() == joinViewModel.currentPw.value.toString()) {
				joinViewModel.setAgainPw(binding.etAgainPw.text.toString())
				navigate(R.id.action_joinPwAgainFragment_to_joinNicknameFragment)
			} else {
				Log.d(" 엥2", joinViewModel.currentPw.value.toString())
				Log.d(" 엥3", joinViewModel.againPw.value.toString())
				Toast.makeText(requireContext(), "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
				return@setOnClickListener
			}
		}
	}
}