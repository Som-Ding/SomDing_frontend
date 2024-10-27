package com.software.somding.ui.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.fragment.app.activityViewModels
import com.software.somding.R
import com.software.somding.databinding.FragmentJoinEmailBinding
import com.software.somding.ui.common.BaseFragment
import com.software.somding.ui.common.NavigationUtil.navigate
import com.software.somding.ui.login.viewmodel.JoinViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JoinEmailFragment : BaseFragment<FragmentJoinEmailBinding>(R.layout.fragment_join_email) {
	private val joinViewModel: JoinViewModel by activityViewModels()

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

//		binding.etUserEmail.addTextChangedListener { text ->
//			joinViewModel.setEmail(text.toString())
//		}
		binding.etUserEmail.addTextChangedListener(object : TextWatcher {
			override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
				Log.d("mytag", "바뀌기 전")
			}

			override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
				Log.d("mytag", "바뀌었을 때")
			}

			override fun afterTextChanged(p0: Editable?) {
				Log.d("mytag", "바뀌고 나서")
				joinViewModel.setEmail(p0.toString())
			}
		})

		binding.btnNext.setOnClickListener {
			navigate(R.id.action_joinEmailFragment_to_joinPwFragment)
		}
	}
}