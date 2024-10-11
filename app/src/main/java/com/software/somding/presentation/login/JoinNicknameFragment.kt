package com.software.somding.presentation.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.software.somding.R
import com.software.somding.databinding.FragmentJoinNicknameBinding
import com.software.somding.databinding.FragmentMainBinding
import com.software.somding.presentation.common.BaseFragment
import com.software.somding.presentation.common.NavigationUtil.navigate

class JoinNicknameFragment : BaseFragment<FragmentJoinNicknameBinding>(R.layout.fragment_join_nickname) {

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		binding.btnNext.setOnClickListener {
			navigate(R.id.action_joinNicknameFragment_to_joinFragment)
		}
	}
}