package com.software.somding.presentation.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import com.software.somding.R
import com.software.somding.databinding.FragmentMainBinding
import com.software.somding.presentation.common.BaseFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class JoinFragment : BaseFragment<FragmentMainBinding>(R.layout.fragment_login) {

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		lifecycleScope.launch {
			delay(1000)
			findNavController().navigate(R.id.action_joinFragment_to_mainFragment)
		}
	}
}