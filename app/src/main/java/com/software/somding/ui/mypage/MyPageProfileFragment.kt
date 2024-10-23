package com.software.somding.ui.mypage

import android.os.Bundle
import android.view.View
import com.software.somding.R
import com.software.somding.databinding.FragmentMyPageProfileBinding
import com.software.somding.ui.common.BaseFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MyPageProfileFragment : BaseFragment<FragmentMyPageProfileBinding>(R.layout.fragment_my_page_profile) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}
