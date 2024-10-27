package com.software.somding.ui.mypage

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.software.somding.R
import com.software.somding.data.model.home.CategoryProjectData
import com.software.somding.data.model.home.CategoryProjectResponse
import com.software.somding.data.model.mypage.RegisteredProjectData
import com.software.somding.databinding.FragmentMyPageRegisteredProjectBinding
import com.software.somding.ui.category.adapter.CategoryProjectListAdapter
import com.software.somding.ui.common.BaseFragment
import com.software.somding.ui.common.NavigationUtil.navigate
import com.software.somding.ui.mypage.adapter.MyPageRegisteredProjectAdapter
import com.software.somding.ui.mypage.viewmodel.MyPageViewModel
import com.software.somding.ui.project.viewmodel.ProjectViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyPageRegisteredProjectFragment :
	BaseFragment<FragmentMyPageRegisteredProjectBinding>(R.layout.fragment_my_page_registered_project) {
	private val viewModel: ProjectViewModel by viewModels()
	private val registeredProjectData = mutableListOf<CategoryProjectData>()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		initProjectRecyclerView()

		viewModel.getMyProject()
		viewModel.myProject.observe(viewLifecycleOwner, Observer { projects ->
			projects?.let {
				updateRecyclerView(it)
			}
		})

	}


	/*    private fun initProjectRecyclerView() {
			val adapter = MyPageRegisteredProjectAdapter()

			adapter.dataList = registeredProjectData
			binding.recyclerView.adapter = adapter
			binding.recyclerView.layoutManager =
				LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false) //레이아웃 매니저 연결
		}*/

	private fun initProjectRecyclerView() {
		val adapter = MyPageRegisteredProjectAdapter { projectId ->
			val bundle = Bundle().apply {
				putInt("projectId", projectId)
			}
			navigate(R.id.action_categoryFragment_to_projectFragment, bundle)
//			parentFragmentManager.beginTransaction()
//				.replace(R.id.fcv_main, ProjectFragment())
//				.addToBackStack(null) // 뒤로가기 스택에 추가
//				.commit()
		}
		adapter.dataList = registeredProjectData
		binding.recyclerView.adapter = adapter
		binding.recyclerView.layoutManager =
			LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
	}

	private fun updateRecyclerView(newData: CategoryProjectResponse) {
		registeredProjectData.clear() // 기존 데이터 클리어
		registeredProjectData.addAll(newData.result) // 새로운 데이터 추가
//		binding.rvCategoryProject.adapter = adapter
		binding.recyclerView.adapter?.notifyDataSetChanged()
	}
}