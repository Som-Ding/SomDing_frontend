package com.software.somding.ui.project

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.google.gson.Gson
import com.software.somding.R
import com.software.somding.data.model.project.ProjectRequest
import com.software.somding.databinding.FragmentRegisterBinding
import com.software.somding.ui.common.BaseFragment
import com.software.somding.ui.common.NavigationUtil.navigate
import com.software.somding.ui.project.viewmodel.ProjectViewModel
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import java.io.File
import java.io.FileOutputStream

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>(R.layout.fragment_register) {
	private val viewModel: ProjectViewModel by viewModels()
	private var selectedCategory: String? = null
	private var selectedImageUri: Uri? = null  // 선택된 이미지 URI
	private var selectedButton: View? = null  // 현재 선택된 버튼

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
/*		binding.btnCloth.setOnClickListener {
			selectedCategory = "CLOTHING"
			Log.d("RegisterFragment", "Selected Category: $selectedCategory")
		}

		binding.btnDoll.setOnClickListener {
			selectedCategory = "DOLL"
			Log.d("RegisterFragment", "Selected Category: $selectedCategory")
		}

		binding.btnEtc.setOnClickListener {
			selectedCategory = "VARIOUS"
			Log.d("RegisterFragment", "Selected Category: $selectedCategory")
		}*/
		binding.btnCloth.setOnClickListener {
			updateSelectedCategory("CLOTHING", binding.btnCloth)
		}

		binding.btnDoll.setOnClickListener {
			updateSelectedCategory("DOLL", binding.btnDoll)
		}

		binding.btnEtc.setOnClickListener {
			updateSelectedCategory("ETC", binding.btnEtc)
		}
		binding.btnSelectImage.setOnClickListener {
			if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE)
				!= PackageManager.PERMISSION_GRANTED) {
				ActivityCompat.requestPermissions(requireActivity(),
					arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
					1)
			} else {
				openGallery()
			}
		}

		// 프로젝트 등록 버튼 클릭 이벤트
		binding.btnRegister.setOnClickListener {
			registerProject()
		}

		viewModel.createProjectResponse.observe(viewLifecycleOwner) { response ->
			if (response != null) {
				Log.d("RegisterFragment", "Project created: $response")
				navigate(R.id.action_registerFragment_to_categoryFragment)
			} else {
				Log.e("RegisterFragment", "Failed to create project")
			}
		}
		setDateFormatWatcher(binding.etTargetDate)
	}

	private fun openGallery() {
		val intent = Intent(Intent.ACTION_PICK).apply {
			type = "image/*"
		}
		imagePickerLauncher.launch(intent)
	}
	private fun updateSelectedCategory(category: String, selectedBtn: View) {
		// 선택된 카테고리 및 버튼 업데이트
		selectedCategory = category

		// 기존에 선택된 버튼의 배경을 원래 상태로 되돌림
		selectedButton?.setBackgroundResource(R.drawable.bg_line_gray_radius_10)

		// 새로 선택된 버튼에 배경색 적용
		selectedBtn.setBackgroundResource(R.drawable.bg_shared_magenta_10)

		// 선택된 버튼을 현재 버튼으로 갱신
		selectedButton = selectedBtn
	}
	private val imagePickerLauncher =
		registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
			if (result.resultCode == AppCompatActivity.RESULT_OK) {
				result.data?.data?.let { uri ->
					selectedImageUri = uri
					binding.imageView.setImageURI(selectedImageUri)
				}
			}
		}

	private fun registerProject() {
		val imageParts = mutableListOf<MultipartBody.Part>()

		selectedImageUri?.let { uri ->
			// 선택된 이미지 처리
			val filePath = getRealPathFromURI(uri)
			if (filePath != null) {
				val file = File(filePath)
				if (file.exists()) {
					val requestFile = RequestBody.create("image/*".toMediaTypeOrNull(), file)
					val imagePart = MultipartBody.Part.createFormData("images", file.name, requestFile)
					imageParts.add(imagePart)
				} else {
					Log.e("RegisterFragment", "File does not exist: $filePath")
				}
			}
		} ?: run {
			// 기본 이미지 사용
			val drawable = requireContext().getDrawable(R.drawable.ic_somding_logo)
			if (drawable is BitmapDrawable) {
				val bitmap = drawable.bitmap
				val defaultImageFile = File(requireContext().cacheDir, "default_image.png")
				FileOutputStream(defaultImageFile).use {
					bitmap.compress(Bitmap.CompressFormat.PNG, 100, it)
				}

				val requestFile = RequestBody.create("image/*".toMediaTypeOrNull(), defaultImageFile)
				val imagePart = MultipartBody.Part.createFormData("images", defaultImageFile.name, requestFile)
				imageParts.add(imagePart)
			}
		}

		val jsonObject = JSONObject().apply {
			put("title", binding.etProjectTitle.text.toString())
			put("introduce", binding.etIntroduce.text.toString())
			put("policy", binding.etPolicy.text.toString())
			put("schedule", binding.etSchedule.text.toString())
			put("category", selectedCategory ?: "ETC")
			put("targetPrice", binding.etTargetPrice.text.toString())
			put("price", binding.etPrice.text.toString())
			put("targetDate", binding.etTargetDate.text.toString())
			put("colorList", JSONArray(listOf("Red", "Blue")))
			put("sizeList", JSONArray(listOf("Small", "Medium", "Large")))
			put("otherList", JSONArray(listOf("Option1", "Option2")))
		}
		val projectReqBody = jsonObject.toString().toRequestBody("application/json".toMediaTypeOrNull())

		viewModel.createProject(projectReqBody, imageParts)
		Log.d("RegisterFragment", "Request Body: $projectReqBody")
		Log.d("RegisterFragment", "Image Parts: $imageParts")
		Log.d("RegisterFragment", "넘어감")
	}

	private fun getRealPathFromURI(uri: Uri): String? {
		val cursor = requireContext().contentResolver.query(uri, null, null, null, null)
		return cursor?.use {
			if (it.moveToFirst()) {
				val index = it.getColumnIndex(MediaStore.Images.Media.DATA)
				it.getString(index)
			} else null
		}
	}

	private fun setDateFormatWatcher(editText: AppCompatEditText) {
		editText.addTextChangedListener(object : TextWatcher {
			private var isFormatting = false

			override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
			}

			override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
			}

			override fun afterTextChanged(s: Editable?) {
				if (isFormatting) return
				isFormatting = true

				// Remove non-digit characters
				val input = s.toString().replace(Regex("[^0-9]"), "")

				if (input.length >= 8) {
					val formattedDate =
						"${input.substring(0, 4)}-${input.substring(4, 6)}-${input.substring(6, 8)}"
					editText.setText(formattedDate)
					editText.setSelection(formattedDate.length)
				} else {
					editText.setText(input)
					editText.setSelection(input.length)
				}

				isFormatting = false
			}
		})
	}
}
