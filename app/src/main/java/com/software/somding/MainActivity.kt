package com.software.somding

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.software.somding.presentation.common.BaseActivity
import com.software.somding.databinding.ActivityMainBinding
import com.software.somding.presentation.main.MainFragment
import com.software.somding.presentation.mypage.MyPageFragment

class MainActivity: BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

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

    //액션버튼 클릭 했을 때
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val transaction = supportFragmentManager.beginTransaction()

        return when(item.itemId){
            R.id.action_share -> {
                //공유 버튼 눌렀을 때
                Log.d("myPage", "공유 버튼 눌림")

                transaction.replace(R.id.fcv_main, MyPageFragment())
                transaction.addToBackStack(null)
                transaction.commit()

                super.onOptionsItemSelected(item)
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}