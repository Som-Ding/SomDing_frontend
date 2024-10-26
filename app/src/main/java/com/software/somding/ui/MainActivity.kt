package com.software.somding.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.software.somding.R
import com.software.somding.ui.common.BaseActivity
import com.software.somding.databinding.ActivityMainBinding
import com.software.somding.ui.home.MainFragment
import com.software.somding.ui.mypage.MyPageFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private val transaction = supportFragmentManager.beginTransaction()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(binding.toolbar)
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

    //액션버튼 클릭 했을 때
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.action_share -> {
	            val intent = Intent(this, MyPageActivity::class.java)
	            startActivity(intent)
	            true
//	            transaction
//                    .replace(R.id.fcv_main, MyPageFragment()) // 내 등록 프로젝트
//                    .commit()
//	                val intent = Intent(this, MyPageActivity::class.java)
//	                startActivity(intent)
//                super.onOptionsItemSelected(item)
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

}