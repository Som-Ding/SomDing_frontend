package com.software.somding.ui

import android.os.Bundle
import com.software.somding.R
import com.software.somding.databinding.ActivityLoginBinding
import com.software.somding.ui.common.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity: BaseActivity<ActivityLoginBinding>(R.layout.activity_login){
	private val transaction = supportFragmentManager.beginTransaction()


	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)


	}

}
