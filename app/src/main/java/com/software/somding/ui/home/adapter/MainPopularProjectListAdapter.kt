package com.software.somding.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.software.somding.data.model.home.PopularProjectData
import com.software.somding.databinding.ItemMainPopularProjectBinding

class MainPopularProjectListAdapter : RecyclerView.Adapter<MainPopularProjectListAdapter.MyViewHolder>() {

    var dataList = mutableListOf<PopularProjectData>()

    inner class MyViewHolder(private val binding: ItemMainPopularProjectBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(projectData: PopularProjectData){
//            binding.projectImg = projectData.contentImg
            binding.contentNumber.text = projectData.projectNumber.toString()
            binding.contentCategory.text = projectData.category
            binding.contentProjectTitle.text = projectData.projectTitle
            binding.contentAchievement.text = projectData.projectPercent.toString()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val binding = ItemMainPopularProjectBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }


}