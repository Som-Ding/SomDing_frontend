package com.software.somding.ui.category.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.software.somding.data.model.home.CategoryProjectResponse
import com.software.somding.databinding.ItemCategoryProjectBinding

class CategoryProjectListAdapter : RecyclerView.Adapter<CategoryProjectListAdapter.MyViewHolder>() {

    var dataList = mutableListOf<CategoryProjectResponse>()

    inner class MyViewHolder(private val binding: ItemCategoryProjectBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(projectData: CategoryProjectResponse){
//            binding.projectImg = projectData.img
            binding.contentCategory.text = projectData.category
            binding.contentProjectTitle.text = projectData.title
            binding.contentAchievement.text = projectData.targetPrice.toString()
            binding.contentPrice.text = projectData.price.toString()
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

        holder.itemView.setOnClickListener {

        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }


}