package com.software.somding.ui.common

import android.content.Context
import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.software.somding.R
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File


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

	fun getFileFromUri(context: Context, uri: Uri): File? {
		val filePath = uri.path ?: return null
		return File(filePath)
	}

	fun prepareFilePart(file: File): MultipartBody.Part {
		val requestFile = RequestBody.create("image/*".toMediaTypeOrNull(), file)
		return MultipartBody.Part.createFormData("image", file.name, requestFile)
	}

}