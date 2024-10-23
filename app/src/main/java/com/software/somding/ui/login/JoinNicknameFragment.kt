package com.software.somding.ui.login

import android.os.Bundle
import android.view.View
import com.software.somding.R
import com.software.somding.databinding.FragmentJoinNicknameBinding
import com.software.somding.ui.common.BaseFragment
import com.software.somding.ui.common.NavigationUtil.navigate

class JoinNicknameFragment : BaseFragment<FragmentJoinNicknameBinding>(R.layout.fragment_join_nickname) {

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		binding.btnNext.setOnClickListener {
			navigate(R.id.action_joinNicknameFragment_to_joinFragment)
		}
	}
}