package com.software.somding.ui.project

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.tabs.TabLayout
import com.software.somding.R
import com.software.somding.databinding.FragmentProjectBinding
import com.software.somding.ui.category.adapter.ImageSliderAdapter
import com.software.somding.ui.common.BaseFragment
import com.software.somding.ui.project.viewmodel.ProjectViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProjectFragment : BaseFragment<FragmentProjectBinding>(R.layout.fragment_project) {
	private var isBottomSheetExpanded = false
	private val viewModel: ProjectViewModel by viewModels()
	private lateinit var imageSliderAdapter: ImageSliderAdapter

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		setupTabLayout()
		setupBottomSheet()
		setupSpinner()

		val projectId = arguments?.getInt("projectId") ?: return
		Log.d("projectId!", projectId.toString())

		viewModel.getProjectsDetail(projectId)

		viewModel.projectDetail.observe(viewLifecycleOwner) { projectDetail ->
			if (projectDetail != null) {
				binding.title.text = projectDetail.title
				binding.category.text = projectDetail.category
				binding.totalPrice.text = projectDetail.targetDate

				imageSliderAdapter =
					viewModel.projectDetail.value?.let { ImageSliderAdapter(it.imgList) }!!
				binding.viewPager.adapter = imageSliderAdapter
			} else {
				Log.e("ProjectFragment", "null")
			}
		}
	}

	private fun setupTabLayout() {
		val tabLayout = binding.tablayout
		val reviewFragment = ReviewFragment()
		val qnaFragment = QnAFragment()

		viewModel.projectDetail.observe(viewLifecycleOwner) { projectDetail ->
			if (projectDetail != null) {
				val detailFragment =
					DetailFragment.newInstance(projectDetail.introduce, projectDetail.policy, projectDetail.schedule)

				// 기본 프래그먼트 설정
				requireActivity().supportFragmentManager.beginTransaction()
					.replace(R.id.main_view, detailFragment).commit()

				// 탭 리스너 설정
				tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
					override fun onTabSelected(tab: TabLayout.Tab?) {
						val selectedFragment = when (tab?.position) {
							0 -> detailFragment
							1 -> reviewFragment
							2 -> qnaFragment
							else -> detailFragment
						}
						requireActivity().supportFragmentManager.beginTransaction()
							.replace(R.id.main_view, selectedFragment).commit()
					}

					override fun onTabUnselected(tab: TabLayout.Tab?) {}
					override fun onTabReselected(tab: TabLayout.Tab?) {}
				})
			}
		}
	}

	private fun setupBottomSheet() {
		binding.projBtn.setOnClickListener {
			val bottomSheetBehavior = BottomSheetBehavior.from(binding.sheet)
			if (!isBottomSheetExpanded) {
				bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
				isBottomSheetExpanded = true
			} else {
				bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
				isBottomSheetExpanded = false
			}
		}
	}

	private fun setupSpinner() {
		val spinner = binding.spinnerItem
		val items = arrayOf("item1", "item2", "item3", "item4", "item5")
		val adapter = ArrayAdapter(requireContext(), R.layout.item_option_list, items)
		spinner.adapter = adapter

		spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
			override fun onItemSelected(
				parentView: AdapterView<*>?,
				selectedItemView: View?,
				position: Int,
				id: Long
			) {
				val selectedOption = items[position]
				// 선택된 옵션에 대한 동작을 여기에 추가
			}

			override fun onNothingSelected(parentView: AdapterView<*>?) {}
		}
	}
}
