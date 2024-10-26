package com.software.somding.ui.login

import android.os.Bundle
import android.view.View
import com.software.somding.R
import com.software.somding.databinding.FragmentLoginOrJoinBinding
import com.software.somding.ui.common.BaseFragment
import com.software.somding.ui.common.NavigationUtil.navigate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginOrJoinFragment : BaseFragment<FragmentLoginOrJoinBinding>(R.layout.fragment_login_or_join) {

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		binding.igSomdingIconLogin.setOnClickListener {
			navigate(R.id.action_loginOrJoinFragment_to_loginFragment)
		}

		binding.igSomdingIconJoin.setOnClickListener{
			navigate(R.id.action_loginOrJoinFragment_to_joinEmailFragment)
		}
	}
}