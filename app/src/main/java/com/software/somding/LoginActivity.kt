package com.software.somding

import android.content.Intent
import android.os.Bundle
import com.software.somding.databinding.ActivityLoginBinding
import com.software.somding.presentation.common.BaseActivity

class LoginActivity: BaseActivity<ActivityLoginBinding>(R.layout.activity_login){
	private val transaction = supportFragmentManager.beginTransaction()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

//		binding.loginButton.setOnClickListener {
//			// 로그인 성공 후 MainActivity로 전환
//			val intent = Intent(this, MainActivity::class.java)
//			startActivity(intent)
//			finish() // LoginActivity 종료
//		}
	}

}
