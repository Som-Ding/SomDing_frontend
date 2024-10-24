package com.software.somding.ui.login

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.software.somding.R
import com.software.somding.databinding.FragmentJoinPwBinding
import com.software.somding.ui.common.BaseFragment
import com.software.somding.ui.common.NavigationUtil.navigate
import com.software.somding.ui.login.viewmodel.JoinViewModel

class JoinPwFragment : BaseFragment<FragmentJoinPwBinding>(R.layout.fragment_join_pw) {
	private val joinViewModel: JoinViewModel by viewModels()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		binding.btnNext.setOnClickListener {
			navigate(R.id.action_joinPwFragment_to_joinPwAgainFragment)
		}

		binding.currentPw.addTextChangedListener { text ->
			joinViewModel.currentPw.value = text.toString()
		}
	}
}