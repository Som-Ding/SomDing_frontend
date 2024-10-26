package com.software.somding.ui.login

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.software.somding.R
import com.software.somding.databinding.FragmentJoinEmailBinding
import com.software.somding.ui.common.BaseFragment
import com.software.somding.ui.common.NavigationUtil.navigate
import com.software.somding.ui.login.viewmodel.JoinViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JoinEmailFragment : BaseFragment<FragmentJoinEmailBinding>(R.layout.fragment_join_email) {
	private val joinViewModel: JoinViewModel by viewModels()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		binding.btnNext.setOnClickListener {
			navigate(R.id.action_joinEmailFragment_to_joinPwFragment)
		}

		binding.etCurrentPw.addTextChangedListener { text ->
			joinViewModel.currentPw.value = text.toString()
		}
	}
}