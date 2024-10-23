package com.software.somding.ui.category

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.software.somding.R
import com.software.somding.data.model.home.CategoryProjectData
import com.software.somding.databinding.FragmentCategoryDollBinding
import com.software.somding.ui.category.adapter.CategoryProjectListAdapter
import com.software.somding.ui.common.BaseFragment

class CategoryDollFragment : BaseFragment<FragmentCategoryDollBinding>(R.layout.fragment_category_doll) {

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
//            add(CategoryProjectData("인형", "컴과 과잠", 70, 9999, 1))
//            add(CategoryProjectData("인형", "동덕 사과 학잠!!", 20, 99999, 3))
//            add(CategoryProjectData("인형", "동덕 은실 학잠", 30, 200500, 10))
//            add(CategoryProjectData("인형", "판다 솜솜이", 90, 7000, 30))
//            add(CategoryProjectData("인형", "솜솜이 물통", 20, 530000, 5))
//            add(CategoryProjectData("인형", "토끼 솜솜이", 99, 1000000, 60))
        }
    }
}