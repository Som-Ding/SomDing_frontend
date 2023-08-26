package com.software.somding.presentation.main

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.software.somding.R
import com.software.somding.data.main.PopularProjectData
import com.software.somding.data.main.ProjectData
import com.software.somding.databinding.FragmentMainBinding
import com.software.somding.presentation.common.BaseFragment
import com.software.somding.presentation.common.NavigationUtil.navigate
import com.software.somding.presentation.main.adapter.MainPopularProjectListAdapter
import com.software.somding.presentation.main.adapter.MainProjectListAdapter

class MainFragment : BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {

    private val recentProjectData = mutableListOf<ProjectData>()
    private val popularProjectData = mutableListOf<PopularProjectData>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnToCategoryCloth.setOnClickListener {
            navigate(R.id.action_mainFragment_to_categoryFragment)
        }

        initializeList()
        initProjectRecyclerView()
    }

    private fun initProjectRecyclerView() {
        val adapter = MainProjectListAdapter()
        adapter.dataList = recentProjectData
        binding.rvRecentProject.adapter = adapter
        binding.rvRecentProject.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false) //레이아웃 매니저 연결

        val adapter2 = MainPopularProjectListAdapter()
        adapter2.dataList = popularProjectData
        binding.rvPopularProject.adapter = adapter2
        binding.rvPopularProject.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false) //레이아웃 매니저 연결
    }

    private fun initializeList() { // 더미 데이터
        with(recentProjectData) {
            add(ProjectData("의류", "컴과 과잠", 70))
            add(ProjectData("의류", "동덕 사과 학잠!!", 20))
            add(ProjectData("의류", "동덕 은실 학잠", 30))
            add(ProjectData("인형", "판다 솜솜이", 90))
            add(ProjectData("잡화", "솜솜이 물통", 20))
            add(ProjectData("인형", "토끼 솜솜이", 99))
        }

        with(popularProjectData) {
            add(PopularProjectData(1, "의류", "컴과 과잠", 70))
            add(PopularProjectData(2, "의류", "동덕 사과 학잠!!", 20))
            add(PopularProjectData(3, "의류", "동덕 은실 학잠", 30))
            add(PopularProjectData(4, "인형", "판다 솜솜이", 90))
            add(PopularProjectData(5, "잡화", "솜솜이 물통", 20))
            add(PopularProjectData(6, "인형", "토끼 솜솜이", 99))
        }
    }
}
