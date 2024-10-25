package com.software.somding.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.software.somding.R
import com.software.somding.data.model.auth.LoginRequest
import com.software.somding.databinding.FragmentLoginBinding
import com.software.somding.ui.MainActivity
import com.software.somding.ui.common.BaseFragment
import com.software.somding.ui.login.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {
	private val viewModel: LoginViewModel by viewModels()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		binding.btnLogin.setOnClickListener {
			val username = binding.etUserEmail.text.toString()
			val password = binding.etUserPassword.text.toString()
			val request = LoginRequest(username, password)

			viewModel.login(request)
		}

		viewModel.loginResponse.observe(viewLifecycleOwner) { response ->
			if (response != null) {
				Log.d("로그인", "성공 오우예: ${response.result.accessToken}")

				val sharedPreferences = context?.getSharedPreferences("auth", Context.MODE_PRIVATE)
				sharedPreferences?.edit()?.putString("accessToken", response.result.accessToken)?.apply()

				val intent = Intent(requireContext(), MainActivity::class.java)
				startActivity(intent)

			} else {
				Log.d("로그인", "실패 ㅜㅜ")
			}
		}

/*		binding.btnLogin.setOnClickListener {
			requireActivity().supportFragmentManager.popBackStack() // 생명주기 끊기!

			val intent = Intent(requireContext(), MainActivity::class.java)
			startActivity(intent)
		}*/
	}
}