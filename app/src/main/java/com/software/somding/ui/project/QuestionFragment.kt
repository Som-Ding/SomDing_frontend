package com.software.somding.ui.project

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.software.somding.R
import com.software.somding.databinding.FragmentQuestionBinding
import com.software.somding.ui.common.BaseFragment
import com.software.somding.ui.project.viewmodel.ProjectViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuestionFragment : BaseFragment<FragmentQuestionBinding>(R.layout.fragment_question) {
	private val viewModel: ProjectViewModel by viewModels()

	companion object {
		const val ARG_PROJECT_ID = "arg_project_id"

		fun newInstance(projectId: Int): QuestionFragment {
			val fragment = QuestionFragment()
			val args = Bundle().apply {
				putInt(ARG_PROJECT_ID, projectId)
			}
			fragment.arguments = args
			return fragment
		}
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		val projectId = arguments?.getInt(ARG_PROJECT_ID)
		Log.d("제발젤발젤발젲발제발", "$projectId")

		if (projectId != null) {
			binding.btnRegister.setOnClickListener {
				viewModel.createQuestion(
					projectId = projectId,
					title = binding.etProjectTitle.text.toString(),
					question = binding.etContent.text.toString()
				)
				viewModel.getAllQuestion(projectId)
			}
		}

		viewModel.qna.observe(viewLifecycleOwner, Observer {
			if (it != null) {
				Log.d("Question", "Question created: $it")
				Toast.makeText(requireContext(), "질문이 등록되었습니다.", LENGTH_SHORT).show()

				val questionFragment = ProjectQnAFragment()
				val transaction = parentFragmentManager.beginTransaction()
				transaction.replace(R.id.main_view, questionFragment)
				transaction.commit()
			} else {
				Log.e("Question", "Failed to create question")
			}
		})
	}
}