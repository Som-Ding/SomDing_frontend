package com.software.somding.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.software.somding.R
import com.software.somding.databinding.FragmentJoinBinding
import com.software.somding.ui.LoginActivity
import com.software.somding.ui.MainActivity
import com.software.somding.ui.common.BaseFragment
import com.software.somding.ui.common.NavigationUtil.navigate
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class JoinFragment : BaseFragment<FragmentJoinBinding>(R.layout.fragment_join) {

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		lifecycleScope.launch {
			delay(1000)
//			navigate(R.id.action_joinFragment_to_loginFragment)
//			requireActivity().supportFragmentManager.popBackStack() // 생명주기 끊기!
			val intent = Intent(requireContext(), LoginActivity::class.java)
			startActivity(intent)
		}
	}
}