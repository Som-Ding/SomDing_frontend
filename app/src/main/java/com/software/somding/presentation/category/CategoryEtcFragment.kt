package com.software.somding.presentation.category

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.software.somding.R
import com.software.somding.databinding.FragmentCategoryBinding
import com.software.somding.databinding.FragmentCategoryEtcBinding
import com.software.somding.databinding.FragmentMyPageBinding
import com.software.somding.presentation.category.adapter.ViewpagerAdapter
import com.software.somding.presentation.common.BaseFragment
import com.software.somding.presentation.common.NavigationUtil.navigate

class CategoryEtcFragment : BaseFragment<FragmentCategoryEtcBinding>(R.layout.fragment_category_etc) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}