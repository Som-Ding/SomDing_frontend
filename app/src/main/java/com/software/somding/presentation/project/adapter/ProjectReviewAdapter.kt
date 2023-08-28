package com.software.somding.presentation.project.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.google.android.material.tabs.TabLayout
import com.software.somding.data.main.ProjectData
import com.software.somding.data.project.ReviewData
import com.software.somding.databinding.ItemMainProjectBinding
import com.software.somding.databinding.ItemProjectReviewBinding

class ProjectReviewAdapter:RecyclerView.Adapter<ProjectReviewAdapter.MyViewHolder>() {

    var dataList = mutableListOf<ReviewData>()

    inner class MyViewHolder(private val binding: ItemProjectReviewBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(reviewData: ReviewData){
            binding.date.text = reviewData.date
            binding.option.text = reviewData.option
            binding.reviewContent.text = reviewData.content
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val binding = ItemProjectReviewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(dataList[position])
    }

    override fun getItemCount(): Int {
        return dataList.size
    }


}