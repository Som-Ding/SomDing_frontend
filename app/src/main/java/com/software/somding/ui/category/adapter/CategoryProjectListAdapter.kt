package com.software.somding.ui.category.adapter

import android.icu.text.DecimalFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.software.somding.data.model.home.CategoryProjectData
import com.software.somding.databinding.ItemCategoryProjectBinding
import com.software.somding.ui.common.ItemBinding.loadImage

class CategoryProjectListAdapter(
	private val onItemClick: (Int) -> Unit
) : RecyclerView.Adapter<CategoryProjectListAdapter.MyViewHolder>() {

	var dataList = mutableListOf<CategoryProjectData>()

	inner class MyViewHolder(private val binding: ItemCategoryProjectBinding) :
		RecyclerView.ViewHolder(binding.root) {
		fun bind(projectData: CategoryProjectData) {
			loadImage(binding.projectImg, projectData.img.toString())

			binding.contentCategory.text = projectData.category // 카테고리
			binding.contentProjectTitle.text = projectData.title // 제목
			val totalPriceFormatted = DecimalFormat("#,###").format(projectData.gatherPrice)
			binding.contentPrice.text = totalPriceFormatted+"원"

			val achievementRate = if (projectData.targetPrice != 0) {
				(projectData.gatherPrice.toDouble() / projectData.targetPrice * 100).toInt()
			} else {
				0
			}
			binding.contentAchievement.text = "$achievementRate% 달성"
			binding.linearProgressIndicator.progress =
				((projectData.gatherPrice * 100) / projectData.targetPrice) ?: 0
			binding.executePendingBindings()

			binding.root.setOnClickListener {
				onItemClick(projectData.projectId)
			}
		}
	}

	override fun onCreateViewHolder(
		parent: ViewGroup,
		viewType: Int
	): MyViewHolder {
		val binding =
			ItemCategoryProjectBinding.inflate(LayoutInflater.from(parent.context), parent, false)
		return MyViewHolder(binding)
	}

	override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
		holder.bind(dataList[position])
		holder.itemView.setOnClickListener {
			onItemClick(dataList[position].projectId) // 클릭된 프로젝트 ID 전달
		}
	}

	override fun getItemCount(): Int {
		return dataList.size
	}
}