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
import com.software.somding.databinding.FragmentCategoryAllBinding
import com.software.somding.ui.common.BaseFragment
import com.software.somding.ui.category.adapter.CategoryProjectListAdapter
import com.software.somding.ui.category.viewmodel.CategoryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryAllFragment : BaseFragment<FragmentCategoryAllBinding>(R.layout.fragment_category_all) {

	private val viewModel: CategoryViewModel by viewModels()
    private val categoryProjectDate = mutableListOf<CategoryProjectData>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeList()
        initProjectRecyclerView()
	    viewModel.categoryProjects.observe(requireActivity(), Observer {

	    })

	    viewModel.getProjectsByCategory(Category.ALL.toString(), Sort.LATEST.toString())
    }

    private fun initProjectRecyclerView() {
        val adapter = CategoryProjectListAdapter()
        adapter.dataList = categoryProjectDate
        binding.rvCategoryProject.adapter = adapter
        binding.rvCategoryProject.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false) //레이아웃 매니저 연결
    }

    private fun initializeList() { // 더미 데이터

        with(categoryProjectDate) {
//            add(CategoryProjectData(0, "컴과 과잠", Category.CLOTHING.toString(), 70, 9999, 1, ))
//            add(CategoryProjectData(1, "동덕 사과 학잠!!", Category.CLOTHING.toString(),20, 99999, 3))
//            add(CategoryProjectData(2, "동덕 은실 학잠", Category.CLOTHING.toString(), 30, 200500, 10))
//            add(CategoryProjectData(3,"판다 솜솜이", Category.DOLL.toString(), 90, 7000, 30))
//            add(CategoryProjectData(4, "솜솜이 물통", Category.VARIOUS.toString(), 20, 530000, 5))
//            add(CategoryProjectData(5, "토끼 솜솜이", Category.DOLL.toString(), 99, 1000000, 60))
        }
    }

}