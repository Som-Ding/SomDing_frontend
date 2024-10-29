package com.software.somding.ui.category

import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.software.somding.R
import com.software.somding.data.model.enum.Category
import com.software.somding.data.model.enum.Sort
import com.software.somding.data.model.home.CategoryProjectData
import com.software.somding.data.model.home.CategoryProjectResponse
import com.software.somding.databinding.FragmentCategoryClothBinding
import com.software.somding.ui.category.adapter.CategoryProjectListAdapter
import com.software.somding.ui.category.viewmodel.CategoryViewModel
import com.software.somding.ui.common.BaseFragment
import com.software.somding.ui.common.NavigationUtil.navigate
import com.software.somding.ui.common.NavigationUtil.navigateWithBundle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryClothFragment :
	BaseFragment<FragmentCategoryClothBinding>(R.layout.fragment_category_cloth) {

	private val viewModel: CategoryViewModel by viewModels()
	private val categoryProjectData = mutableListOf<CategoryProjectData>()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		initProjectRecyclerView()

		// 초기 데이터 로드
		loadProjects(Sort.LATEST)

		viewModel.categoryProjects.observe(viewLifecycleOwner, Observer { projects ->
			projects?.let {
				updateRecyclerView(it)
			}
			binding.tvClothSub.text = (projects?.result?.size.toString() + "개의 프로젝트가 있습니다.") ?: "0"
		})

		// PopupMenu 설정
		binding.filtering.setOnClickListener { view ->
			val popupMenu = PopupMenu(requireContext(), view)
			popupMenu.menuInflater.inflate(R.menu.filtering_menu, popupMenu.menu)

			popupMenu.setOnMenuItemClickListener { menuItem ->
				val selectedSort = when (menuItem.itemId) {
					R.id.latest -> Sort.LATEST
					R.id.popularity -> Sort.POPULARITY
					R.id.most_sponsored -> Sort.MOST_SPONSORED
					R.id.least_sponsored -> Sort.HIGHEST_AMOUNT
					R.id.closing_soon -> Sort.CLOSING_SOON
					else -> Sort.LATEST
				}

				binding.filtering.text = menuItem.title
				loadProjects(selectedSort)
				true
			}
			popupMenu.show()
		}
	}

	private fun initProjectRecyclerView() {
		val adapter = CategoryProjectListAdapter { projectId ->
			val bundle = Bundle().apply {
				putInt("projectId", projectId)
			}
			navigateWithBundle(R.id.action_categoryFragment_to_projectFragment, bundle)
		}
		adapter.dataList = categoryProjectData
		binding.rvCategoryProject.adapter = adapter
		binding.rvCategoryProject.layoutManager =
			LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false) //레이아웃 매니저 연결
	}

	// RecyclerView 업데이트 함수
	private fun updateRecyclerView(newData: CategoryProjectResponse) {
		categoryProjectData.clear()
		categoryProjectData.addAll(newData.result)
		binding.rvCategoryProject.adapter?.notifyDataSetChanged()
	}

	private fun loadProjects(sort: Sort) {
		viewModel.getProjectsByCategory(Category.ALL.toString(), sort.toString())
	}
}