package com.software.somding.ui.category

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.software.somding.R
import com.software.somding.data.model.enum.Category
import com.software.somding.data.model.enum.Sort
import com.software.somding.data.model.home.CategoryProjectData
import com.software.somding.data.model.home.CategoryProjectResponse
import com.software.somding.databinding.FragmentCategoryDollBinding
import com.software.somding.ui.category.adapter.CategoryProjectListAdapter
import com.software.somding.ui.category.viewmodel.CategoryViewModel
import com.software.somding.ui.common.BaseFragment
import com.software.somding.ui.common.NavigationUtil.navigate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryDollFragment : BaseFragment<FragmentCategoryDollBinding>(R.layout.fragment_category_doll) {
	private val viewModel: CategoryViewModel by viewModels()
	private val categoryProjectData = mutableListOf<CategoryProjectData>()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		initProjectRecyclerView()

		viewModel.getProjectsByCategory(Category.DOLL.toString(), Sort.LATEST.toString())
		viewModel.categoryProjects.observe(viewLifecycleOwner, Observer { projects ->
			projects?.let {
				updateRecyclerView(it)
			}
		})
	}

	private fun initProjectRecyclerView() {
		val adapter = CategoryProjectListAdapter {
			navigate(R.id.action_categoryFragment_to_projectFragment)
		}
		adapter.dataList = categoryProjectData
		binding.rvCategoryProject.adapter = adapter
		binding.rvCategoryProject.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false) //레이아웃 매니저 연결
	}

	private fun updateRecyclerView(newData: CategoryProjectResponse) {
		categoryProjectData.clear()
		categoryProjectData.addAll(newData.result)
		binding.rvCategoryProject.adapter?.notifyDataSetChanged()
	}
}