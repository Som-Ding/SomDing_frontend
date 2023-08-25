package com.software.somding.presentation.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.software.somding.R
import com.software.somding.data.main.ProjectData
import com.software.somding.databinding.FragmentMainBinding
import com.software.somding.databinding.FragmentMyPageBinding
import com.software.somding.presentation.common.BaseFragment
import com.software.somding.presentation.common.NavigationUtil.navigate
import com.software.somding.presentation.main.adapter.MainProjectListAdapter

class MainFragment : BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {

    val dataList = mutableListOf<ProjectData>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnToCategory.setOnClickListener {
            navigate(R.id.action_mainFragment_to_categoryFragment)
        }

        initializeList()
        initProjectRecyclerView()
    }

    private fun initProjectRecyclerView() {
        val adapter = MainProjectListAdapter() //어댑터 객체 만듦
        adapter.dataList = dataList
        binding.rvRecentProject.adapter = adapter
        binding.rvRecentProject.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false) //레이아웃 매니저 연결
    }

    private fun initializeList() { //임의로 데이터 넣어서 만들어봄
        with(dataList) {
            add(ProjectData("의류", "컴과 과잠", "70%"))
            add(ProjectData("의류", "동덕 사과 학잠!!", "20%"))
            add(ProjectData("의류", "동덕 은실 학잠", "30%"))
            add(ProjectData("인형", "판다 솜솜이", "90%"))
            add(ProjectData("잡화", "솜솜이 물통", "20%"))
            add(ProjectData("인형", "토끼 솜솜이", "999%"))
        }
    }
}
