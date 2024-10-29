package com.software.somding.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.software.somding.data.model.home.CategoryProjectData
import com.software.somding.databinding.ItemMainPopularProjectBinding
import com.software.somding.ui.common.ItemBinding.loadImage

class MainProjectListAdapter(
	private val onItemClick: (Int) -> Unit
) :RecyclerView.Adapter<MainProjectListAdapter.MyViewHolder>() {

    var dataList = mutableListOf<CategoryProjectData>()

	inner class MyViewHolder(private val binding: ItemMainPopularProjectBinding) :
		RecyclerView.ViewHolder(binding.root) {
		fun bind(projectData: CategoryProjectData, position: Int) {
			loadImage(binding.projectImg, projectData.img.toString())
			binding.contentCategory.text = projectData.category
			binding.contentProjectTitle.text = projectData.title
			val achievementRate = if (projectData.targetPrice != 0) {
				(projectData.gatherPrice.toDouble() / projectData.targetPrice * 100).toInt()
			} else {
				0
			}
			binding.contentAchievement.text = "$achievementRate% 달성"
			binding.contentNumber.text = (position + 1).toString()
//			binding.contentPrice.text = projectData.gatherPrice.toString()
//			binding.executePendingBindings()

			binding.root.setOnClickListener {
				onItemClick(projectData.projectId)
			}
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
        holder.bind(dataList[position], position)
	    holder.itemView.setOnClickListener {
		    onItemClick(dataList[position].projectId) // 클릭된 프로젝트 ID 전달
	    }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }


}