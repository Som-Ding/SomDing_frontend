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
import com.software.somding.data.model.home.CategoryProjectResponse
import com.software.somding.databinding.FragmentCategoryAllBinding
import com.software.somding.ui.common.BaseFragment
import com.software.somding.ui.category.adapter.CategoryProjectListAdapter
import com.software.somding.ui.category.viewmodel.CategoryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryAllFragment : BaseFragment<FragmentCategoryAllBinding>(R.layout.fragment_category_all) {

	private val viewModel: CategoryViewModel by viewModels()
    private val categoryProjectData = mutableListOf<CategoryProjectResponse>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initProjectRecyclerView()

	    viewModel.getProjectsByCategory(Category.ALL.toString(), Sort.LATEST.toString())

	    viewModel.categoryProjects.observe(viewLifecycleOwner, Observer { projects ->
		    projects?.let {
			    updateRecyclerView(projects)
		    }
	    })
    }

    private fun initProjectRecyclerView() {
        val adapter = CategoryProjectListAdapter()
        adapter.dataList = categoryProjectData
        binding.rvCategoryProject.adapter = adapter
        binding.rvCategoryProject.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false) //레이아웃 매니저 연결
	    Log.d("category", "$categoryProjectData")
	}


	// RecyclerView 업데이트 함수
	private fun updateRecyclerView(newData: CategoryProjectResponse) {
		categoryProjectData.clear()
		categoryProjectData.addAll(listOf(newData))
		binding.rvCategoryProject.adapter?.notifyDataSetChanged()
	}
}