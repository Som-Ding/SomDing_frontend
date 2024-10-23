package com.software.somding.ui.mypage

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.software.somding.R
import com.software.somding.data.model.mypage.RegisteredProjectData
import com.software.somding.databinding.FragmentMyPageDonatedProjectBinding
import com.software.somding.ui.common.BaseFragment
import com.software.somding.ui.mypage.adapter.MyPageRegisteredProjectAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyPageDonatedProjectFragment : BaseFragment<FragmentMyPageDonatedProjectBinding>(R.layout.fragment_my_page_donated_project) {

    private val registeredProjectData = mutableListOf<RegisteredProjectData>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initProjectRecyclerView()
        initializeList()
    }

    private fun initProjectRecyclerView() {
        val adapter = MyPageRegisteredProjectAdapter()

        adapter.dataList = registeredProjectData
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false) //레이아웃 매니저 연결
    }

    private fun initializeList() { // 더미 데이터

        with(registeredProjectData) {
            add(RegisteredProjectData("의류", "컴과 과잠", 70, 9999, 1))
            add(RegisteredProjectData("의류", "동덕 사과 학잠!!", 20, 99999, 3))
            add(RegisteredProjectData("의류", "동덕 은실 학잠", 30, 200500, 10))
            add(RegisteredProjectData("인형", "판다 솜솜이", 90, 7000, 30))
            add(RegisteredProjectData("잡화", "솜솜이 물통", 20, 530000, 5))
            add(RegisteredProjectData("인형", "토끼 솜솜이", 99, 1000000, 60))
        }
    }
}