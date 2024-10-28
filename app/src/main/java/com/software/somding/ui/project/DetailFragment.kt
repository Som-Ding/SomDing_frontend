package com.software.somding.ui.project

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.software.somding.R
import com.software.somding.databinding.FragmentProjectDetailBinding
import com.software.somding.ui.category.adapter.ImageSliderAdapter
import com.software.somding.ui.common.BaseFragment
import com.software.somding.ui.project.viewmodel.ProjectViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment: BaseFragment<FragmentProjectDetailBinding>(R.layout.fragment_project_detail) {
	private val viewModel: ProjectViewModel by viewModels()

	/*override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

		val projectId = arguments?.getInt("projectId") ?: return
		viewModel.getProjectsDetail(projectId)

		viewModel.projectDetail.observe(viewLifecycleOwner) { projectDetail ->
			if (projectDetail != null) {
				binding.introTv.text = projectDetail.introduce
				binding.policyTv.text = projectDetail.policy
				binding.schedule.text = projectDetail.schedule
			} else {
				Log.e("DetailFragment", "null")
			}
		}
    }*/
	companion object {
		private const val ARG_INTRODUCE = "arg_introduce"
		private const val ARG_POLICY = "arg_policy"
		private const val ARG_SCHEDULE = "arg_schedule"

		fun newInstance(introduce: String, policy: String, schedule: String): DetailFragment {
			val fragment = DetailFragment()
			val args = Bundle().apply {
				putString(ARG_INTRODUCE, introduce)
				putString(ARG_POLICY, policy)
				putString(ARG_SCHEDULE, schedule)
			}
			fragment.arguments = args
			return fragment
		}
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		val introduce = arguments?.getString(ARG_INTRODUCE)
		val policy = arguments?.getString(ARG_POLICY)
		val schedule = arguments?.getString(ARG_SCHEDULE)

		binding.intro.text = introduce
		binding.schedule.text = schedule
		binding.policy.text = policy
	}
}