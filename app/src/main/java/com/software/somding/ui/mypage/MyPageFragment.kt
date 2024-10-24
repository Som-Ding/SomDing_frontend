package com.software.somding.ui.mypage

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.software.somding.R
import com.software.somding.databinding.FragmentMyPageBinding
import com.software.somding.ui.common.BaseFragment
import com.software.somding.ui.common.NavigationUtil.navigate
import com.software.somding.ui.mypage.viewmodel.MyPageViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyPageFragment : BaseFragment<FragmentMyPageBinding>(R.layout.fragment_my_page) {

    private val viewModel: MyPageViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getMyPage()
	    viewModel.myPageResponse.observe(viewLifecycleOwner) { response ->
		    if (response != null) {
			    binding.myPageName.text = response.nickname
			    binding.myPageEmail.text = response.email
			    Log.d("MyPage", response.nickname)
			} else {
			    binding.myPageName.text = "error"
		    }
	    }

        binding.btnProfileModify.setOnClickListener {
	        navigate(R.id.action_myPageFragment_to_myPageProfileFragment)
        }

        binding.btnMyRegisteredProject.setOnClickListener {
            navigate(R.id.action_myPageFragment_to_myPageRegisteredProjectFragment)
        }

        binding.btnMyDonatedProject.setOnClickListener {
	        navigate(R.id.action_myPageFragment_to_donatedProjectFragment)
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