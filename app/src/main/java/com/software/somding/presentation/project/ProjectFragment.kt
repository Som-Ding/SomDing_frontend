package com.software.somding.presentation.project

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.replace
import com.google.android.material.tabs.TabLayout
import com.software.somding.R
import com.software.somding.databinding.FragmentProjectBinding
import com.software.somding.databinding.FragmentProjectDetailBinding

class ProjectFragment : Fragment() {

    private lateinit var binding: FragmentProjectBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProjectBinding.inflate(inflater, container, false)
        val view = binding.root

        // 바인딩을 사용하여 UI 구성요소에 접근
        val tabLayout = binding.tablayout

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

        return view
    }

}