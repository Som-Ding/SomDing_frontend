package com.software.somding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.ActivityNavigator
import com.example.refit.presentation.common.BaseActivity
import com.software.somding.databinding.ActivityMainBinding
import com.software.somding.presentation.mypage.MyPageFragment

class MainActivity: BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnUserMyPage.setOnClickListener {

        }
    }
}