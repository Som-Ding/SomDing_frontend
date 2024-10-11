package com.software.somding.presentation.project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.tabs.TabLayout
import com.software.somding.R
import com.software.somding.databinding.FragmentProjectBinding
import com.software.somding.presentation.common.BaseFragment

class ProjectFragment : BaseFragment<FragmentProjectBinding>(R.layout.fragment_project) {
    private var isBottomSheetExpanded = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 바인딩을 사용하여 UI 구성요소에 접근
        val tabLayout = binding.tablayout
//        val textInputLayout = binding.inputLayout
//        val autoCompleteTextView = binding.textItem
//        val textShowItem = binding.textShowItem

        val detailFragment: Fragment = DetailFragment()
        val reviewFragment: Fragment = ReviewFragment()
        val qnaFragment: Fragment = QnAFragment()

        requireActivity().supportFragmentManager.beginTransaction().replace(R.id.main_view, detailFragment).commit()
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {
                        requireActivity().supportFragmentManager.beginTransaction().replace(R.id.main_view, detailFragment).commit()
                    }
                    1->{
                        requireActivity().supportFragmentManager.beginTransaction().replace(R.id.main_view, reviewFragment).commit()
                    }
                    2->{
                        requireActivity().supportFragmentManager.beginTransaction().replace(R.id.main_view, qnaFragment).commit()
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })

        binding.projBtn.setOnClickListener {
            if (!isBottomSheetExpanded) {
                // Bottom Sheet가 축소된 상태에서 버튼 클릭 시, Bottom Sheet를 확장
                BottomSheetBehavior.from(binding.sheet).state = BottomSheetBehavior.STATE_EXPANDED
                isBottomSheetExpanded = true
            } else {
                // Bottom Sheet가 확장된 상태에서 버튼 클릭 시, Bottom Sheet를 축소
                BottomSheetBehavior.from(binding.sheet).state = BottomSheetBehavior.STATE_COLLAPSED
                isBottomSheetExpanded = false
            }
        }
//        BottomSheetBehavior.from(binding.sheet).apply {
//            isBottomSheetExpanded = !isBottomSheetExpanded
//        }

//        val items = arrayOf("item1", "item2", "item3", "item4", "item5", "item1", "item2", "item3", "item4", "item5")
//        val itemAdapter = ArrayAdapter(requireContext(), R.layout.item_option_list, items)
//        autoCompleteTextView.setAdapter(itemAdapter)
//
//        autoCompleteTextView.setOnItemClickListener { adapterView, view, position, id ->
//            textShowItem.text = adapterView.getItemAtPosition(position) as String
//        }
//
//        val spinnerItem = binding.spinnerItem
//        val items = arrayOf("item1", "item2", "item3", "item4", "item5", "item1", "item2", "item3", "item4", "item5")
//
        val spinner = binding.spinnerItem
        val items = arrayOf("item1", "item2", "item3", "item4", "item5", "item1", "item2", "item3", "item4", "item5")
        val adapter = ArrayAdapter(requireContext(), R.layout.item_option_list, items)
        spinner.adapter = adapter
//
//// Set a selection listener if needed
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>?, selectedItemView: View?, position: Int, id: Long) {
                // Handle item selection here
                val selectedOption = items[position]
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {
                // Handle no selection if needed
            }
        }



    }

}