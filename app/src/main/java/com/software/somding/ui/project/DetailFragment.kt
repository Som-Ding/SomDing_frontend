package com.software.somding.ui.project

import android.os.Bundle
import android.view.View
import com.software.somding.R
import com.software.somding.databinding.FragmentProjectDetailBinding
import com.software.somding.ui.common.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment: BaseFragment<FragmentProjectDetailBinding>(R.layout.fragment_project_detail) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}