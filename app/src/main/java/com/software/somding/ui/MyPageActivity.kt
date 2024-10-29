package com.software.somding.ui

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.software.somding.R
import com.software.somding.databinding.ActivityMyPageBinding
import com.software.somding.ui.common.BaseActivity
import com.software.somding.ui.home.MainFragment
import com.software.somding.ui.mypage.MyPageFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyPageActivity : BaseActivity<ActivityMyPageBinding> (R.layout.activity_my_page){
	private lateinit var appBarConfiguration: AppBarConfiguration

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		setSupportActionBar(binding.toolbar)
		supportActionBar?.setDisplayShowTitleEnabled(false)

		binding.toolbarTitle.setOnClickListener {
			supportFragmentManager.beginTransaction()
				.replace(R.id.fcv_my, MyPageFragment())
				.addToBackStack(null)
				.commit()
		}
	}

	override fun onSupportNavigateUp(): Boolean {
		val navController = findNavController(R.id.fcv_my)
		return navController.navigateUp(appBarConfiguration)
				|| super.onSupportNavigateUp()
	}
}