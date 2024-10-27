package com.software.somding.ui.category

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.software.somding.R
import com.software.somding.data.model.enum.Category
import com.software.somding.data.model.enum.Sort
import com.software.somding.data.model.home.CategoryProjectData
import com.software.somding.data.model.home.CategoryProjectResponse
import com.software.somding.databinding.FragmentCategoryAllBinding
import com.software.somding.ui.common.BaseFragment
import com.software.somding.ui.category.adapter.CategoryProjectListAdapter
import com.software.somding.ui.category.viewmodel.CategoryViewModel
import com.software.somding.ui.common.NavigationUtil.navigate
import com.software.somding.ui.project.ProjectFragment
import com.software.somding.ui.project.viewmodel.ProjectViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryAllFragment :
	BaseFragment<FragmentCategoryAllBinding>(R.layout.fragment_category_all) {
	private val viewModel: CategoryViewModel by viewModels()
	private val projectViewModel: ProjectViewModel by viewModels()
	private val categoryProjectData = mutableListOf<CategoryProjectData>()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		initProjectRecyclerView()

		viewModel.getProjectsByCategory(Category.ALL.toString(), Sort.LATEST.toString())
		viewModel.categoryProjects.observe(viewLifecycleOwner, Observer { projects ->
			projects?.let {
				updateRecyclerView(it)
			}
		})
	}

	private fun initProjectRecyclerView() {
		val adapter = CategoryProjectListAdapter { projectId ->
			val bundle = Bundle().apply {
                putInt("projectId", projectId)
            }
            navigate(R.id.action_categoryFragment_to_projectFragment, bundle)
//			parentFragmentManager.beginTransaction()
//				.replace(R.id.fcv_main, ProjectFragment())
//				.addToBackStack(null) // 뒤로가기 스택에 추가
//				.commit()
		}
		adapter.dataList = categoryProjectData
		binding.rvCategoryProject.adapter = adapter
		binding.rvCategoryProject.layoutManager =
			LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
	}

	private fun updateRecyclerView(newData: CategoryProjectResponse) {
		categoryProjectData.clear() // 기존 데이터 클리어
		categoryProjectData.addAll(newData.result) // 새로운 데이터 추가
//		binding.rvCategoryProject.adapter = adapter
		binding.rvCategoryProject.adapter?.notifyDataSetChanged()
	}
}