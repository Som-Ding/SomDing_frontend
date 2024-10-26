package com.software.somding.ui.mypage

import android.os.Bundle
import android.view.View
import com.software.somding.R
import com.software.somding.databinding.FragmentAddressChangeBinding
import com.software.somding.ui.common.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddressChangeFragment: BaseFragment<FragmentAddressChangeBinding>(R.layout.fragment_address_change) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}