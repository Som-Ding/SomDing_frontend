package com.software.somding

import android.os.Bundle
import android.view.Menu
import com.software.somding.databinding.ActivityMainBinding
import com.software.somding.presentation.common.BaseActivity
import com.software.somding.presentation.main.MainFragment

class LoginActivity: BaseActivity<ActivityMainBinding>(R.layout.activity_login){

	private val transaction = supportFragmentManager.beginTransaction()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		setSupportActionBar(binding.toolbar) // 커스텀한 toolbar를 액션바로 사용
		supportActionBar?.setDisplayShowTitleEnabled(false)

		binding.toolbarTitle.setOnClickListener {
			supportFragmentManager.beginTransaction()
				.replace(R.id.fcv_main, MainFragment())
				.addToBackStack(null)
				.commit()
		}
	}

	//액션버튼 메뉴 액션바에 집어 넣기
	override fun onCreateOptionsMenu(menu: Menu?): Boolean {
		menuInflater.inflate(R.menu.toolbar_menu, menu)
		return true
	}

}
