package com.software.somding.presentation.mypage

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.software.somding.R
import com.software.somding.databinding.FragmentMyPageBinding
import com.software.somding.presentation.common.BaseFragment
import com.software.somding.presentation.common.NavigationUtil.navigate
import com.software.somding.presentation.mypage.viewmodel.MyPageViewModel

class MyPageFragment : BaseFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {

    private val viewModel: MyPageViewModel by viewModels()
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()

//        // ViewModel을 사용하여 데이터 로딩
//        viewModel.loadData()

        binding.btnProfileModify.setOnClickListener {
            navigate(R.id.action_myPageFragment_to_myPageProfileFragment)
        }

        binding.btnMyRegisteredProject.setOnClickListener {
            navigate(R.id.action_myPageFragment_to_myPageRegisteredProjectFragment)
        }

        binding.btnMyDonatedProject.setOnClickListener {

        }

        binding.btnMyInterestedProject.setOnClickListener {

        }

        binding.btnMyReview.setOnClickListener {

        }

        binding.btnAddressManagement.setOnClickListener {

        }

        binding.btnPayManagement.setOnClickListener {

        }

        binding.btnLogout.setOnClickListener {

        }

        binding.btnInfoDelete.setOnClickListener {

        }

    }
}