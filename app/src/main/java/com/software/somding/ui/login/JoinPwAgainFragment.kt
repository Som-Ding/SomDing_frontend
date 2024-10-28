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
				if (joinViewModel.currentPw.equals(p0.toString())) {
					joinViewModel.setAgainPw(p0.toString())
				} else {
					binding.etAgainPw.error = "비밀번호가 일치하지 않습니다."
				}
			}
		})

		binding.btnNext.setOnClickListener {
			navigate(R.id.action_joinPwAgainFragment_to_joinNicknameFragment)
		}
	}
}