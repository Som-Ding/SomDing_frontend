package com.software.somding.ui.category.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.software.somding.data.model.home.CategoryProjectData
import com.software.somding.databinding.ItemCategoryProjectBinding
import com.software.somding.ui.common.ItemBinding.loadImage

class CategoryProjectListAdapter : RecyclerView.Adapter<CategoryProjectListAdapter.MyViewHolder>() {

	var dataList = mutableListOf<CategoryProjectData>()

	inner class MyViewHolder(private val binding: ItemCategoryProjectBinding) : RecyclerView.ViewHolder(binding.root) {
		fun bind(projectData: CategoryProjectData) {
			if (projectData.img.isNullOrEmpty()) {
				loadImage(binding.projectImg, projectData.img.toString())
			}

	        binding.contentCategory.text = projectData.category
	        binding.contentProjectTitle.text = projectData.title
	        binding.contentAchievement.text = projectData.targetPrice.toString()
	        binding.contentPrice.text = projectData.gatherPrice.toString()
	        binding.executePendingBindings()
		}
	}

	override fun onCreateViewHolder(
		parent: ViewGroup,
		viewType: Int
	): MyViewHolder {
		val binding = ItemCategoryProjectBinding.inflate(LayoutInflater.from(parent.context), parent, false)
		return MyViewHolder(binding)
	}

	override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
		holder.bind(dataList[position])
		holder.itemView.setOnClickListener {
			// 클릭 리스너 설정 (필요한 경우)
		}
	}

	override fun getItemCount(): Int {
		return dataList.size
	}
//	@BindingAdapter("imageUrl")
//	fun loadImage(view: ImageView, url: String?) {
//		Glide.with(view.context)
//			.load(url)
//			.apply(RequestOptions().placeholder(R.drawable.placeholder).error(R.drawable.error_image)) // 플레이스홀더와 에러 이미지 설정
//			.into(view)
//	}
}