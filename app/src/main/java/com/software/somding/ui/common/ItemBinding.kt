package com.software.somding.ui.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.software.somding.R


object ItemBinding {
	@JvmStatic
	@BindingAdapter("image_url")
	fun loadImage(imageView: ImageView, url: String) {
//		Glide.with(imageView.context)
		//		.load(url)
		//		.into(imageView)
		Glide.with(imageView.context)
			.load(url ?: R.drawable.bg_solid_gray_radius_10) // url이 null이면 기본 이미지 사용
			.apply(RequestOptions().placeholder(R.drawable.bg_solid_gray_radius_10).error(R.drawable.bg_solid_gray_radius_10)) // 플레이스홀더와 에러 이미지 설정
			.into(imageView)
	}

	fun Int.toStringFormat(): String {
		return this.toString()
	}
}