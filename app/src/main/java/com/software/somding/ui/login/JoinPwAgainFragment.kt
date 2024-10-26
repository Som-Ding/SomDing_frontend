package com.software.somding.ui.login

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.software.somding.R
import com.software.somding.databinding.FragmentJoinPwAgainBinding
import com.software.somding.ui.common.BaseFragment
import com.software.somding.ui.common.NavigationUtil.navigate
import com.software.somding.ui.login.viewmodel.JoinViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JoinPwAgainFragment : BaseFragment<FragmentJoinPwAgainBinding>(R.layout.fragment_join_pw_again) {
	private val joinViewModel: JoinViewModel by viewModels()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		binding.btnNext.setOnClickListener {
			navigate(R.id.action_joinPwAgainFragment_to_joinNicknameFragment)
		}

		binding.etAgainPw.addTextChangedListener { text ->
			joinViewModel.againPw.value = text.toString()
		}
	}
}