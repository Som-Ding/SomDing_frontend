package com.software.somding.ui.login

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.software.somding.R
import com.software.somding.databinding.FragmentJoinBinding
import com.software.somding.ui.common.BaseFragment
import com.software.somding.ui.common.NavigationUtil.navigate
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class JoinFragment : BaseFragment<FragmentJoinBinding>(R.layout.fragment_join) {

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		lifecycleScope.launch {
			delay(1000)
//			requireActivity().supportFragmentManager.popBackStack() // 생명주기 끊기!

			navigate(R.id.action_joinFragment_to_loginFragment)
//			val intent = Intent(requireContext(), MainActivity::class.java)
//			startActivity(intent)
		}
	}
}