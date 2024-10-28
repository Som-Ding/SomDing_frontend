package com.software.somding.ui.project

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
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
import java.io.File

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>(R.layout.fragment_register) {
	private val viewModel: ProjectViewModel by viewModels()
	private var selectedCategory: String? = null
	private var selectedImageUri: Uri? = null  // 선택된 이미지 URI

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
	    binding.btnCloth.setOnClickListener {
		    selectedCategory = "CLOTHING"
		    Log.d("RegisterFragment", "Selected Category: $selectedCategory")
	    }

	    binding.btnDoll.setOnClickListener {
		    selectedCategory = "DOLL"
		    Log.d("RegisterFragment", "Selected Category: $selectedCategory")
	    }

	    binding.btnEtc.setOnClickListener {
		    selectedCategory = "ETC"
		    Log.d("RegisterFragment", "Selected Category: $selectedCategory")
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
		val projectReq = ProjectRequest(
			title = binding.etProjectTitle.text.toString(),
			introduce = binding.etIntroduce.text.toString(),
			policy = binding.etPolicy.text.toString(),
			schedule = binding.etSchedule.text.toString(),
			category = selectedCategory ?: "ETC",
			targetPrice = binding.etTargetPrice.text.toString(),
			price = binding.etPrice.text.toString(),
			targetDate = binding.etTargetDate.text.toString(),
			colorList = listOf("Red", "Blue"),
			sizeList = listOf("M", "L"),
			otherList = listOf("Additional Info"),
		)

		val titlePart = RequestBody.create("text/plain".toMediaTypeOrNull(), projectReq.title)
		val introducePart = RequestBody.create("text/plain".toMediaTypeOrNull(), projectReq.introduce)
		val policyPart = RequestBody.create("text/plain".toMediaTypeOrNull(), projectReq.policy)
		val schedulePart = RequestBody.create("text/plain".toMediaTypeOrNull(), projectReq.schedule)
		val categoryPart = RequestBody.create("text/plain".toMediaTypeOrNull(), projectReq.category)
		val targetPricePart = RequestBody.create("text/plain".toMediaTypeOrNull(), projectReq.targetPrice)
		val pricePart = RequestBody.create("text/plain".toMediaTypeOrNull(), projectReq.price)
		val targetDatePart = RequestBody.create("text/plain".toMediaTypeOrNull(), projectReq.targetDate)
		val colorListPart = RequestBody.create("application/json".toMediaTypeOrNull(), Gson().toJson(projectReq.colorList))
		val sizeListPart = RequestBody.create("application/json".toMediaTypeOrNull(), Gson().toJson(projectReq.sizeList))
		val otherListPart = RequestBody.create("application/json".toMediaTypeOrNull(), Gson().toJson(projectReq.otherList))


		val gson = Gson()
		val projectJson = gson.toJson(projectReq)
		val requestBody = RequestBody.create("application/json".toMediaTypeOrNull(), projectJson)

		val imageParts = mutableListOf<MultipartBody.Part>()
		selectedImageUri?.let { uri ->
			val file = File(uri.path!!)
			val requestFile = RequestBody.create("image/*".toMediaTypeOrNull(), file)
			val imagePart = MultipartBody.Part.createFormData("images", file.name, requestFile)
			imageParts.add(imagePart)
		}

//		viewModel.createProject(projectReq, imageParts)
		viewModel.createProject(
			titlePart, introducePart, policyPart, schedulePart,
			categoryPart, targetPricePart, pricePart, targetDatePart,
			colorListPart, sizeListPart, otherListPart, imageParts
		)
		Log.d("RegisterFragment", "넘어감")
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