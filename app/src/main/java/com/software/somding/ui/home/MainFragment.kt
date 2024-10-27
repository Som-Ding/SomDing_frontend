package com.software.somding.ui.home

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.software.somding.R
import com.software.somding.data.model.home.PopularProjectData
import com.software.somding.data.model.home.ProjectData
import com.software.somding.databinding.FragmentMainBinding
import com.software.somding.ui.category.CategoryFragment
import com.software.somding.ui.common.BaseFragment
import com.software.somding.ui.common.NavigationUtil.navigate
import com.software.somding.ui.home.adapter.MainPopularProjectListAdapter
import com.software.somding.ui.home.adapter.MainProjectListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {

    private val recentProjectData = mutableListOf<ProjectData>()
    private val popularProjectData = mutableListOf<PopularProjectData>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

	    initializeList()
	    initProjectRecyclerView()

	    binding.btnToCategoryCloth.setOnClickListener {
            navigate(R.id.action_mainFragment_to_categoryFragment)
        }

	    binding.btnToCategoryDoll.setOnClickListener {
		    navigate(R.id.action_mainFragment_to_categoryFragment)
	    }

	    binding.btnToCategoryEtc.setOnClickListener {
		    navigate(R.id.action_mainFragment_to_categoryFragment)
	    }
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
        }
    }

}
