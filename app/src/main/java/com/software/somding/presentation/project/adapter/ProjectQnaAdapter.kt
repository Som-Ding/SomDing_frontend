package com.software.somding.presentation.project.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.software.somding.data.project.QnaData
import com.software.somding.data.project.ReviewData
import com.software.somding.databinding.ItemProjectQnaBinding
import com.software.somding.databinding.ItemProjectReviewBinding

class ProjectQnaAdapter(val  dataList: MutableList<QnaData>): RecyclerView.Adapter<ProjectQnaAdapter.MyViewHolder> (){

    inner class MyViewHolder(private val binding: ItemProjectQnaBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(qnaData: QnaData) {
            binding.content.text = qnaData.content
            binding.date.text = qnaData.date
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemProjectQnaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(dataList[position])
    }
}