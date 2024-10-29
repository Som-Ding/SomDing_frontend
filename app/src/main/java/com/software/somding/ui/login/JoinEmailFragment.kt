package com.software.somding.ui.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.software.somding.R
import com.software.somding.databinding.FragmentJoinEmailBinding
import com.software.somding.ui.common.BaseFragment
import com.software.somding.ui.common.NavigationUtil.navigate
import com.software.somding.ui.login.viewmodel.JoinViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JoinEmailFragment : BaseFragment<FragmentJoinEmailBinding>(R.layout.fragment_join_email) {
	private val joinViewModel: JoinViewModel by activityViewModels()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

//		binding.etUserEmail.addTextChangedListener { text ->
//			joinViewModel.setEmail(text.toString())
//		}
		binding.etUserEmail.addTextChangedListener(object : TextWatcher {
			override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
			}

			override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
			}

			override fun afterTextChanged(p0: Editable?) {
				joinViewModel.setEmail(p0.toString())
			}
		})

		binding.btnNext.setOnClickListener {
			if (binding.etUserEmail.text.isNullOrEmpty()) {
				Toast.makeText(requireContext(), "필수 입력 값입니다.", Toast.LENGTH_SHORT).show()
				return@setOnClickListener // 넘어가지 않도록 반환
			}

			if (joinViewModel.error.value == null) {
				navigate(R.id.action_joinEmailFragment_to_joinPwFragment)
			} else {
				Toast.makeText(requireContext(), "이메일을 형식에 맞춰 입력해 주세요.", Toast.LENGTH_SHORT).show()
				return@setOnClickListener // 넘어가지 않도록 반환
			}
		}
	}
}