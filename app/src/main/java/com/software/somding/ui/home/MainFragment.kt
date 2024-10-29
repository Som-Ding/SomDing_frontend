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

	    initProjectRecyclerView()

	    binding.btnToCategoryCloth.setOnClickListener {
			val bundle = Bundle().apply {
		    putString("selectedCategory", Category.CLOTHING.toString())
	    }
		    navigateWithBundle(R.id.action_mainFragment_to_categoryFragment, bundle)
        }

	    binding.btnToCategoryDoll.setOnClickListener {
		    val bundle = Bundle().apply {
			    putString("selectedCategory", Category.DOLL.toString())
		    }
		    navigateWithBundle(R.id.action_mainFragment_to_categoryFragment, bundle)
	    }

	    binding.btnToCategoryEtc.setOnClickListener {
		    val bundle = Bundle().apply {
			    putString("selectedCategory", Category.VARIOUS.toString())
		    }
		    navigateWithBundle(R.id.action_mainFragment_to_categoryFragment, bundle)
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
		binding.rvPopularProject.adapter?.notifyDataSetChanged()
	}

}
