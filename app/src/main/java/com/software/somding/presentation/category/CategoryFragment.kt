package com.software.somding.presentation.category

import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.software.somding.R
import com.software.somding.databinding.FragmentCategoryBinding
import com.software.somding.presentation.category.adapter.ViewpagerAdapter
import com.software.somding.presentation.common.BaseFragment
import com.software.somding.presentation.common.NavigationUtil.navigate

class CategoryFragment : BaseFragment<FragmentCategoryBinding>(R.layout.fragment_category) {

    private lateinit var viewPager : ViewPager2
    private lateinit var tabLayout : TabLayout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnToCategory.setOnClickListener {
//            navigate(R.id.action_categoryFragment_to_projectFragment)
			navigate(R.id.action_categoryFragment_to_registerFragment)
        }

        val tabTitle = arrayOf("전체", "의류", "인형", "잡화")

        viewPager = binding.viewPager // viewPager 연결
        tabLayout = binding.tabLayout // tabLayout 연결

        val adapter = ViewpagerAdapter(this)

        adapter.addFragment(CategoryAllFragment())
        adapter.addFragment(CategoryClothFragment())
        adapter.addFragment(CategoryDollFragment())
        adapter.addFragment(CategoryEtcFragment())

        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager
        ) { tab, position -> tab.text = tabTitle[position] }.attach()
    }
}