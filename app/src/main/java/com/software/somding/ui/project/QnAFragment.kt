package com.software.somding.ui.project

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.software.somding.R
import com.software.somding.data.model.home.CategoryProjectData
import com.software.somding.data.model.home.CategoryProjectResponse
import com.software.somding.data.model.project.Question
import com.software.somding.data.model.project.QuestionResponse
import com.software.somding.data.project.QnaData
import com.software.somding.databinding.FragmentProjectQnaBinding
import com.software.somding.ui.category.adapter.CategoryProjectListAdapter
import com.software.somding.ui.common.BaseFragment
import com.software.somding.ui.common.NavigationUtil.navigateWithBundle
import com.software.somding.ui.project.adapter.ProjectQnaAdapter
import com.software.somding.ui.project.viewmodel.ProjectViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QnAFragment: BaseFragment<FragmentProjectQnaBinding>(R.layout.fragment_project_qna) {
    private val qnaData = mutableListOf<QuestionResponse>()
	private val viewModel: ProjectViewModel by viewModels()
	private val question = mutableListOf<Question>()

	companion object {
		private const val ARG_PROJECT_ID = "arg_project_id"

		fun newInstance(projectId: String): QnAFragment {
			val fragment = QnAFragment()
			val args = Bundle().apply {
				putString(ARG_PROJECT_ID, projectId)
			}
			fragment.arguments = args
			return fragment
		}
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		val projectId = arguments?.getString(ARG_PROJECT_ID)?.toInt()

        initProjectRecyclerView();

		if (projectId != null) {
			viewModel.getAllQuestion(projectId)
		}
	    viewModel.question.observe(viewLifecycleOwner, Observer { projects ->
		    projects?.let {
			    updateRecyclerView(it)
		    }
	    })
    }
	private fun initProjectRecyclerView() {
		val adapter = ProjectQnaAdapter()
		adapter.dataList = question
		binding.recyclerReview.adapter = adapter
		binding.recyclerReview.layoutManager =
			LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
	}

	private fun updateRecyclerView(newData: QuestionResponse) {
		question.clear() // 기존 데이터 클리어
		question.addAll(newData.result) // 새로운 데이터 추가
		binding.recyclerReview.adapter?.notifyDataSetChanged()
	}
}