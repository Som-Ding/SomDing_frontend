package com.software.somding

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.software.somding.presentation.common.BaseActivity
import com.software.somding.databinding.ActivityMainBinding
import com.software.somding.presentation.main.MainFragment
import com.software.somding.presentation.mypage.MyPageDonatedProjectFragment
import com.software.somding.presentation.mypage.MyPageFragment
import com.software.somding.presentation.mypage.MyPageInterestedProjectFragment
import com.software.somding.presentation.mypage.MyPageProfileFragment
import com.software.somding.presentation.mypage.MyPageRegisteredProjectFragment
import com.software.somding.presentation.mypage.MyPageUpdateCurrentPwFragment

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
                //공유 버튼 눌렀을 때
                Log.d("myPage", "공유 버튼 눌림")

                transaction
                    .replace(R.id.fcv_main, MyPageFragment()) // 내 등록 프로젝트
                    .commit()
//                val intent = Intent(this, MyPageActivity::class.java)
//                startActivity(intent)

                super.onOptionsItemSelected(item)
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

}