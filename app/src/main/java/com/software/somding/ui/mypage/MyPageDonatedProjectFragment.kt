package com.software.somding.ui.mypage

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.software.somding.R
import com.software.somding.data.model.home.CategoryProjectData
import com.software.somding.data.model.home.CategoryProjectResponse
import com.software.somding.data.model.mypage.MyProjectOrderData
import com.software.somding.data.model.mypage.MyProjectOrderResponse
import com.software.somding.data.model.mypage.RegisteredProjectData
import com.software.somding.databinding.FragmentMyPageDonatedProjectBinding
import com.software.somding.ui.common.BaseFragment
import com.software.somding.ui.common.NavigationUtil.navigateWithBundle
import com.software.somding.ui.mypage.adapter.MyPageRegisteredProjectAdapter
import com.software.somding.ui.mypage.viewmodel.MyPageViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyPageDonatedProjectFragment : BaseFragment<FragmentMyPageDonatedProjectBinding>(R.layout.fragment_my_page_donated_project) {
	private val viewModel: MyPageViewModel by viewModels()
    private val registeredProjectData = mutableListOf<CategoryProjectData>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initProjectRecyclerView()
	    viewModel.orderMyProject()
	    viewModel.myOrderProject.observe(viewLifecycleOwner, Observer { projects ->
		    projects?.let {
			    updateRecyclerView(it)
		    }
		    binding.projectContent.text = (projects?.result?.size.toString() + "개의 프로젝트가 있습니다.")
	    })
    }

	private fun initProjectRecyclerView() {
		val adapter = MyPageRegisteredProjectAdapter { projectId ->
			val bundle = Bundle().apply {
				putInt("projectId", projectId)
			}
			navigateWithBundle(R.id.action_categoryFragment_to_projectFragment, bundle)
		}
		adapter.dataList = registeredProjectData
		binding.recyclerView.adapter = adapter
		binding.recyclerView.layoutManager =
			LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
	}

	private fun updateRecyclerView(newData: CategoryProjectResponse) {
		registeredProjectData.clear() // 기존 데이터 클리어
		registeredProjectData.addAll(newData.result) // 새로운 데이터 추가
		binding.recyclerView.adapter?.notifyDataSetChanged()
	}
}