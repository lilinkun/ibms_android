package com.hncr.ibms.ui.CompanyProfile

import android.view.View
import androidx.navigation.fragment.findNavController
import com.hncr.ibms.R
import com.hncr.ibms.base.BaseFragment
import com.hncr.ibms.databinding.FragmentCompanyProfileBinding

/**
 * Created by LG
 * on 2023/7/26  9:04
 * Description：
 */
class CompanyProfileFragment : BaseFragment<FragmentCompanyProfileBinding>() {

    override fun getLayoutResId(): Int {
        return R.layout.fragment_company_profile
    }

    override fun initView() {

        binding.llBack.setOnClickListener(View.OnClickListener {
            findNavController().navigateUp()
        })

    }

    override fun observeViewModels() {
    }

    fun back(){

    }
}