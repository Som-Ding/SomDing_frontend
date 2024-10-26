package com.software.somding.ui.project

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.software.somding.R
import com.software.somding.data.project.QnaData
import com.software.somding.databinding.FragmentProjectQnaBinding
import com.software.somding.ui.common.BaseFragment
import com.software.somding.ui.project.adapter.ProjectQnaAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QnAFragment: BaseFragment<FragmentProjectQnaBinding>(R.layout.fragment_project_qna) {
    private val qnaData = mutableListOf<QnaData>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeList();
        initProjectRecyclerView();
    }
    private fun initProjectRecyclerView(){
        val adapter = ProjectQnaAdapter(qnaData)
        binding.recyclerReview.adapter = adapter
        binding.recyclerReview.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false) //레이아웃 매니저 연결
    }
    private fun initializeList(){
        with(qnaData) {
            add(QnaData("2012-12-22", "컴과 과잠"))
            add(QnaData("2012-12-22", "동덕 사과 학잠!!"))
            add(QnaData("2022-12-11", "동덕 은실 학잠"))
            add(QnaData("2022-12-11", "판다 솜솜이"))
            add(QnaData("2022-12-11", "솜솜이 물통"))
            add(QnaData("2023-12-11", "토끼 솜솜이"))
        }
    }
}