package com.software.somding.presentation.login

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.software.somding.R
import com.software.somding.databinding.FragmentLoginBinding
import com.software.somding.presentation.MainActivity
import com.software.somding.presentation.common.BaseFragment

class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		binding.btnLogin.setOnClickListener {
			requireActivity().supportFragmentManager.popBackStack() // 생명주기 끊기!

			val intent = Intent(requireContext(), MainActivity::class.java)
			startActivity(intent)
		}
	}
}