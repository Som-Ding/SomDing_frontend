package com.software.somding.presentation

import android.os.Bundle
import com.software.somding.R
import com.software.somding.databinding.ActivityLoginBinding
import com.software.somding.presentation.common.BaseActivity

class LoginActivity: BaseActivity<ActivityLoginBinding>(R.layout.activity_login){
	private val transaction = supportFragmentManager.beginTransaction()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)


	}

}
