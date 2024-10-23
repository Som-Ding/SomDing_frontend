package com.software.somding.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.software.somding.data.model.home.ProjectData
import com.software.somding.databinding.ItemMainProjectBinding

class MainProjectListAdapter : RecyclerView.Adapter<MainProjectListAdapter.MyViewHolder>() {

    var dataList = mutableListOf<ProjectData>()

    inner class MyViewHolder(private val binding: ItemMainProjectBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(projectData: ProjectData){
//            binding.projectImg = projectData.contentImg
            binding.contentCategory.text = projectData.category
            binding.contentProjectTitle.text = projectData.projectTitle
            binding.contentAchievement.text = projectData.projectPercent.toString()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val binding = ItemMainProjectBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }


}