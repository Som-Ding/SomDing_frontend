package com.software.somding.presentation.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.software.somding.R
import com.software.somding.databinding.FragmentLoginOrJoinBinding
import com.software.somding.databinding.FragmentMainBinding
import com.software.somding.presentation.common.BaseFragment
import com.software.somding.presentation.common.NavigationUtil.navigate

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