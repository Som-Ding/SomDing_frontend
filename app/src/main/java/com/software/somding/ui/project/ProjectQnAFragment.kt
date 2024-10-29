package com.software.somding.ui.project

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.software.somding.R
import com.software.somding.data.model.project.Question
import com.software.somding.data.model.project.QuestionResponse
import com.software.somding.databinding.FragmentProjectQnaBinding
import com.software.somding.ui.common.BaseFragment
import com.software.somding.ui.common.NavigationUtil.navigate
import com.software.somding.ui.project.adapter.ProjectQnaAdapter
import com.software.somding.ui.project.viewmodel.ProjectViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProjectQnAFragment: BaseFragment<FragmentProjectQnaBinding>(R.layout.fragment_project_qna) {
    private val qnaData = mutableListOf<QuestionResponse>()
	private val viewModel: ProjectViewModel by viewModels()
	private val question = mutableListOf<Question>()

	companion object {
		const val ARG_PROJECT_ID = "arg_project_id"

		fun newInstance(projectId: Int): ProjectQnAFragment {
			val fragment = ProjectQnAFragment()
			val args = Bundle().apply {
				putInt(ARG_PROJECT_ID, projectId)
			}
			fragment.arguments = args
			return fragment
		}
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		val questionFragment = QuestionFragment()
		initProjectRecyclerView()

		val projectId = arguments?.getInt(ARG_PROJECT_ID)
		if (projectId != null) {
			viewModel.getAllQuestion(projectId)
		}

	    viewModel.question.observe(viewLifecycleOwner, Observer { projects ->
		    projects?.let {
			    updateRecyclerView(it)
		    }
	    })

		binding.qnaBtn.setOnClickListener {
			val questionFragment = projectId?.let { it1 -> QuestionFragment.newInstance(it1) }
			val transaction = parentFragmentManager.beginTransaction()
			if (questionFragment != null) {
				transaction.replace(R.id.main_view, questionFragment)
			}
			transaction.addToBackStack(null)
			transaction.commit()
		}

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