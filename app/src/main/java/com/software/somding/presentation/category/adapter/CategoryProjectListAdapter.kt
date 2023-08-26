package com.software.somding.presentation.category.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.software.somding.data.main.CategoryProjectData
import com.software.somding.databinding.ItemCategoryProjectBinding

class CategoryProjectListAdapter : RecyclerView.Adapter<CategoryProjectListAdapter.MyViewHolder>() {

    var dataList = mutableListOf<CategoryProjectData>()

    inner class MyViewHolder(private val binding: ItemCategoryProjectBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(projectData: CategoryProjectData){
//            binding.projectImg = projectData.contentImg
            binding.contentCategory.text = projectData.category
            binding.contentProjectTitle.text = projectData.projectTitle
            binding.contentAchievement.text = projectData.projectPercent.toString()
            binding.contentPrice.text = projectData.projectPrice.toString()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val binding = ItemCategoryProjectBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }


}