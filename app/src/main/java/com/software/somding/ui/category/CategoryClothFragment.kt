package com.software.somding.ui.category

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.software.somding.R
import com.software.somding.data.model.home.CategoryProjectData
import com.software.somding.databinding.FragmentCategoryClothBinding
import com.software.somding.ui.category.adapter.CategoryProjectListAdapter
import com.software.somding.ui.common.BaseFragment

class CategoryClothFragment : BaseFragment<FragmentCategoryClothBinding>(R.layout.fragment_category_cloth) {

    private val categoryProjectDate = mutableListOf<CategoryProjectData>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeList()
        initProjectRecyclerView()
    }

    private fun initProjectRecyclerView() {
        val adapter = CategoryProjectListAdapter()
        adapter.dataList = categoryProjectDate
        binding.rvCategoryProject.adapter = adapter
        binding.rvCategoryProject.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false) //레이아웃 매니저 연결
    }

    private fun initializeList() { // 더미 데이터

        with(categoryProjectDate) {
//            add(CategoryProjectData("의류", "컴과 과잠", 70, 9999, 1))
//            add(CategoryProjectData("의류", "동덕 사과 학잠!!", 20, 99999, 3))
//            add(CategoryProjectData("의류", "동덕 은실 학잠", 30, 200500, 10))
//            add(CategoryProjectData("의류", "동덕 사과 학잠!!", 90, 7000, 30))
//            add(CategoryProjectData("의류", "동덕 은실 학잠", 20, 530000, 5))
//            add(CategoryProjectData("의류", "컴과 과잠", 99, 1000000, 60))
        }
    }
}