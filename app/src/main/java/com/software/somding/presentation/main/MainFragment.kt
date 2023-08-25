package com.software.somding.presentation.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.software.somding.R
import com.software.somding.databinding.FragmentMyPageBinding
import com.software.somding.presentation.common.BaseFragment
import com.software.somding.presentation.common.NavigationUtil.navigate

class MainFragment : BaseFragment<FragmentMyPageBinding>(R.layout.fragment_main) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnToProject.setOnClickListener {
            navigate(R.id.action_mainFragment_to_categoryFragment)
        }
    }

}