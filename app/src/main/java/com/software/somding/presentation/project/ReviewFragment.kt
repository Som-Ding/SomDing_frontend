package com.software.somding.presentation.project

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.software.somding.R
import com.software.somding.data.project.ReviewData
import com.software.somding.databinding.FragmentProjectReviewBinding
import com.software.somding.presentation.common.BaseFragment
import com.software.somding.presentation.project.adapter.ProjectReviewAdapter

class ReviewFragment: BaseFragment<FragmentProjectReviewBinding>(R.layout.fragment_project_review) {
    private val reviewData = mutableListOf<ReviewData>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initializeList();
        initProjectRecyclerView();
    }
    private fun initProjectRecyclerView() {
        val adapter = ProjectReviewAdapter(reviewData)
        binding.recyclerReview.adapter = adapter
        binding.recyclerReview.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false) //레이아웃 매니저 연결

    }

    private fun initializeList() { // 더미 데이터
        with(reviewData) {
            add(ReviewData("2012-12-22", "컴과 과잠", "검정"))
            add(ReviewData("2012-12-22", "동덕 사과 학잠!!", "검정"))
            add(ReviewData("2022-12-11", "동덕 은실 학잠", "검정"))
            add(ReviewData("2022-12-11", "판다 솜솜이", "검정"))
            add(ReviewData("2022-12-11", "솜솜이 물통", "파랑"))
            add(ReviewData("2023-12-11", "토끼 솜솜이", "파랑"))
        }
        Log.d("데이터 들어감", "데이터 들어" +reviewData[1])
    }
}