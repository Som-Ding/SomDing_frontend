package com.software.somding.ui.login

import android.os.Bundle
import android.view.View
import com.software.somding.R
import com.software.somding.databinding.FragmentJoinPwBinding
import com.software.somding.ui.common.BaseFragment
import com.software.somding.ui.common.NavigationUtil.navigate

class JoinPwFragment : BaseFragment<FragmentJoinPwBinding>(R.layout.fragment_join_pw) {

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		binding.btnNext.setOnClickListener {
			navigate(R.id.action_joinPwFragment_to_joinPwAgainFragment)
		}
	}
}