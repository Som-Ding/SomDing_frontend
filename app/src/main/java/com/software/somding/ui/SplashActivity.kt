package com.software.somding.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.software.somding.R
import com.software.somding.databinding.ActivityMyPageBinding
import com.software.somding.ui.common.BaseActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashActivity : BaseActivity<ActivityMyPageBinding>(R.layout.activity_my_page){
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_splash)

		lifecycleScope.launch {
			delay(1000)
			val intent = Intent(this@SplashActivity, LoginActivity::class.java)
			startActivity(intent)
		}
	}
}