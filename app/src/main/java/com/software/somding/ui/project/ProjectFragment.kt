package com.software.somding.ui.project

import android.icu.text.DecimalFormat
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.tabs.TabLayout
import com.software.somding.R
import com.software.somding.data.model.project.ProjectOption
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
	private var projectId: Int? = null

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		setupTabLayout()
		setupBottomSheet()
		setupSpinner()

		val projectId = arguments?.getInt("projectId") ?: return
		Log.d("projectId!", projectId.toString())

		/***
		 * 프로젝트 상세페이지 조회
		 */
		viewModel.getProjectsDetail(projectId)
		viewModel.projectDetail.observe(viewLifecycleOwner) { projectDetail ->
			if (projectDetail != null) {
				binding.title.text = projectDetail.title
				binding.category.text = projectDetail.category
				binding.likeBtn.text = projectDetail.scrapNum.toString()
				binding.sponsorNum.text = projectDetail.sponsorNum.toString()+"명"
				val totalPriceFormatted = DecimalFormat("#,###").format(projectDetail.gatherPrice)
				binding.totPrice.text = totalPriceFormatted

				val achievementRate = if (projectDetail.targetPrice != 0) {
					(projectDetail.gatherPrice.toDouble() / projectDetail.targetPrice * 100).toInt()
				} else {
					0
				}
				binding.pricePer.text = "$achievementRate%"

				imageSliderAdapter =
					viewModel.projectDetail.value?.let { ImageSliderAdapter(it.imgList) }!!
				binding.viewPager.adapter = imageSliderAdapter

				val remainingDays = projectDetail.getRemainingDays()
				binding.remainDate.text = "$remainingDays 일"
			} else {
				Log.e("ProjectFragment", "null")
			}
		}
//		setupSpinner(
//			viewModel.projectDetail.value!!.colorList,
//			viewModel.projectDetail.value!!.sizeList,
//			viewModel.projectDetail.value!!.otherList
//		)

		/***
		 * 프로젝트 스크랩
		 */
		var isLiked = false

		binding.likeBtn.setOnClickListener {
			viewModel.getScrap(projectId)

			isLiked = !isLiked // 상태 토글

			if (isLiked) {
				binding.likeBtn.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_heart_filled, 0, 0)
			} else {
				binding.likeBtn.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_heart, 0, 0)
			}
		}
		viewModel.scrap.observe(viewLifecycleOwner) {
			if (isLiked) {
				Toast.makeText(requireContext(), "스크랩 되었습니다.", LENGTH_SHORT).show()
			} else {
				Toast.makeText(requireContext(), "스크랩이 취소되었습니다.", LENGTH_SHORT).show()
			}
		}
	}

	private fun setupTabLayout() {
		val tabLayout = binding.tablayout
		val reviewFragment = ReviewFragment()
		var qnaFragmentProject = ProjectQnAFragment()
		var questionProject = QuestionFragment()

		viewModel.projectDetail.observe(viewLifecycleOwner) { projectDetail ->
			if (projectDetail != null) {
				val detailFragment =
					DetailFragment.newInstance(
						projectDetail.introduce,
						projectDetail.policy,
						projectDetail.schedule
					)

				qnaFragmentProject =
					arguments?.getInt("projectId")?.let {
						ProjectQnAFragment.newInstance(
							it
						)
					}!!

				questionProject =
					arguments?.getInt("projectId")?.let {
						QuestionFragment.newInstance(
							it
						)
					}!!

				// 기본 프래그먼트 설정
				requireActivity().supportFragmentManager.beginTransaction()
					.replace(R.id.main_view, detailFragment).commit()

				// 탭 리스너 설정
				tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
					override fun onTabSelected(tab: TabLayout.Tab?) {
						val selectedFragment = when (tab?.position) {
							0 -> detailFragment
							1 -> reviewFragment
							2 -> qnaFragmentProject
							else -> detailFragment
						}
						if (selectedFragment != null) {
							requireActivity().supportFragmentManager.beginTransaction()
								.replace(R.id.main_view, selectedFragment).commit()
						}
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

	private fun setupSpinner(
		colorList: List<ProjectOption>,
		sizeList: List<ProjectOption>,
		otherList: List<ProjectOption>
	) {
		val spinner = binding.spinnerItem

		val allItems = colorList + sizeList + otherList
		val options = allItems.map { it.option } // 옵션 텍스트만 추출

		val adapter = ArrayAdapter(requireContext(), R.layout.item_option_list, options)
		spinner.adapter = adapter

		spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
			override fun onItemSelected(
				parentView: AdapterView<*>?,
				selectedItemView: View?,
				position: Int,
				id: Long
			) {
				val selectedOption = allItems[position]
				Log.d("Spinner", "Selected option: ${selectedOption.option}")
			}

			override fun onNothingSelected(parentView: AdapterView<*>?) {}
		}
	}
}
