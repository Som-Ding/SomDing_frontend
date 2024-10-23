package com.software.somding.ui.project.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.software.somding.data.model.project.ReviewData
import com.software.somding.databinding.ItemProjectReviewBinding

class ProjectReviewAdapter(val dataList: MutableList<ReviewData>): RecyclerView.Adapter<ProjectReviewAdapter.MyViewHolder>() {

    inner class MyViewHolder(private val binding: ItemProjectReviewBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(reviewData: ReviewData) {
            binding.option.text = reviewData.option
            binding.date.text = reviewData.date
            binding.reviewContent.text = reviewData.content
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemProjectReviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(dataList[position])
    }


}