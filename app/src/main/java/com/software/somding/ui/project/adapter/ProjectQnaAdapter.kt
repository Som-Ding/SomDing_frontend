package com.software.somding.ui.project.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.software.somding.data.model.home.CategoryProjectData
import com.software.somding.data.model.project.Question
import com.software.somding.data.project.QnaData
import com.software.somding.databinding.ItemProjectQnaBinding

class ProjectQnaAdapter(
): RecyclerView.Adapter<ProjectQnaAdapter.MyViewHolder> (){
	var dataList = mutableListOf<Question>()

    inner class MyViewHolder(private val binding: ItemProjectQnaBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(qnaData: Question) {
            binding.title.text = qnaData.title
            binding.date.text = qnaData.createdAt


	        binding.content.text = qnaData.question
	        binding.showMore.setOnClickListener {
		        binding.content.maxLines = Int.MAX_VALUE
		        binding.content.text = qnaData.question
		        binding.showMore.visibility = View.GONE
	        }
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