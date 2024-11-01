package com.software.somding.ui.category.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewpagerAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {
    // 페이지 갯수 설정

    val fragmentList : MutableList<Fragment> = arrayListOf()

    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]
    }

    fun addFragment(fragment: Fragment){
        fragmentList.add(fragment)
        notifyItemInserted(fragmentList.size - 1)
    }

    fun removeFragment(){
        fragmentList.removeLast()
        notifyItemRemoved(fragmentList.size)
    }

	fun getFragment(position: Int): Fragment? {
		return if (position >= 0 && position < fragmentList.size) {
			fragmentList[position]
		} else {
			null
		}
	}
}