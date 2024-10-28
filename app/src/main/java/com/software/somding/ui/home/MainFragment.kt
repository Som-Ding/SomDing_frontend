package com.software.somding.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.software.somding.R
import com.software.somding.data.model.enum.Category
import com.software.somding.data.model.enum.Sort
import com.software.somding.data.model.home.CategoryProjectData
import com.software.somding.data.model.home.CategoryProjectResponse
import com.software.somding.data.model.home.PopularProjectData
import com.software.somding.data.model.home.ProjectData
import com.software.somding.databinding.FragmentMainBinding
import com.software.somding.ui.category.CategoryFragment
import com.software.somding.ui.category.adapter.CategoryProjectListAdapter
import com.software.somding.ui.category.viewmodel.CategoryViewModel
import com.software.somding.ui.common.BaseFragment
import com.software.somding.ui.common.NavigationUtil.navigate
import com.software.somding.ui.common.NavigationUtil.navigateWithBundle
import com.software.somding.ui.home.adapter.MainPopularProjectListAdapter
import com.software.somding.ui.home.adapter.MainProjectListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {

    private val recentProjectData = mutableListOf<ProjectData>()
    private val popularProjectData = mutableListOf<CategoryProjectData>()
	private val viewModel: CategoryViewModel by viewModels()

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

	    viewModel.getProjectsByCategory(Category.ALL.toString(), Sort.POPULARITY.toString())
	    viewModel.categoryProjects.observe(viewLifecycleOwner, Observer { projects ->
		    projects?.let {
			    updateRecyclerView(it)
		    }
	    })
    }

	private fun initProjectRecyclerView() {
		val adapter = MainProjectListAdapter { projectId ->
			val bundle = Bundle().apply {
				putInt("projectId", projectId)
			}
			navigateWithBundle(R.id.action_mainFragment_to_projectFragment, bundle)
		}
		adapter.dataList = popularProjectData
		binding.rvPopularProject.adapter = adapter
		binding.rvPopularProject.layoutManager =
			LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
	}

	private fun updateRecyclerView(newData: CategoryProjectResponse) {
		popularProjectData.clear() // 기존 데이터 클리어
		popularProjectData.addAll(newData.result) // 새로운 데이터 추가
//		binding.rvCategoryProject.adapter = adapter
		binding.rvPopularProject.adapter?.notifyDataSetChanged()
	}
//    private fun initProjectRecyclerView() {
//        val adapter = MainProjectListAdapter()
//        adapter.dataList = recentProjectData
//        binding.rvRecentProject.adapter = adapter
//        binding.rvRecentProject.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false) //레이아웃 매니저 연결
//
//        val adapter2 = MainPopularProjectListAdapter()
//        adapter2.dataList = popularProjectData
//        binding.rvPopularProject.adapter = adapter2
//        binding.rvPopularProject.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false) //레이아웃 매니저 연결
//    }

    private fun initializeList() { // 더미 데이터
        with(recentProjectData) {
            add(ProjectData("의류", "컴과 과잠", 70))
            add(ProjectData("의류", "동덕 사과 학잠!!", 20))
            add(ProjectData("의류", "동덕 은실 학잠", 30))
            add(ProjectData("인형", "판다 솜솜이", 90))
            add(ProjectData("잡화", "솜솜이 물통", 20))
            add(ProjectData("인형", "토끼 솜솜이", 99))
        }
    }

}
