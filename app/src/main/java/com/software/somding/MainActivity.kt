package com.software.somding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.refit.presentation.common.BaseActivity
import com.software.somding.databinding.ActivityMainBinding

class MainActivity: BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }
}