package com.software.somding.ui.category

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.software.somding.R
import com.software.somding.data.model.enum.Category
import com.software.somding.data.model.enum.Sort
import com.software.somding.data.model.home.CategoryProjectResponse
import com.software.somding.databinding.FragmentCategoryBinding
import com.software.somding.ui.category.adapter.ViewpagerAdapter
import com.software.somding.ui.category.viewmodel.CategoryViewModel
import com.software.somding.ui.common.BaseFragment
import com.software.somding.ui.common.NavigationUtil.navigate
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryFragment : BaseFragment<FragmentCategoryBinding>(R.layout.fragment_category) {
	private val viewModel: CategoryViewModel by viewModels()

    private lateinit var viewPager : ViewPager2
    private lateinit var tabLayout : TabLayout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnToCategory.setOnClickListener {
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