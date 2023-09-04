package com.software.somding.presentation.category

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.software.somding.R
import com.software.somding.data.model.main.CategoryProjectData
import com.software.somding.databinding.FragmentCategoryAllBinding
import com.software.somding.presentation.common.BaseFragment
import com.software.somding.presentation.category.adapter.CategoryProjectListAdapter

class CategoryAllFragment : BaseFragment<FragmentCategoryAllBinding>(R.layout.fragment_category_all) {

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
            add(CategoryProjectData("의류", "컴과 과잠", 70, 9999, 1))
            add(CategoryProjectData("의류", "동덕 사과 학잠!!", 20, 99999, 3))
            add(CategoryProjectData("의류", "동덕 은실 학잠", 30, 200500, 10))
            add(CategoryProjectData("인형", "판다 솜솜이", 90, 7000, 30))
            add(CategoryProjectData("잡화", "솜솜이 물통", 20, 530000, 5))
            add(CategoryProjectData("인형", "토끼 솜솜이", 99, 1000000, 60))
        }
    }

}