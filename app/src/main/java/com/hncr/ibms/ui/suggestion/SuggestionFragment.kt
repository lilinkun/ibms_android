package com.hncr.ibms.ui.suggestion

import android.os.Bundle
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.hncr.ibms.R
import com.hncr.ibms.base.BaseFragment
import com.hncr.ibms.databinding.FragmentSuggestionBinding
import com.hncr.ibms.http.data.SuggestionBean
import com.hncr.ibms.tools.EVENTBUS_ADDSUGGESTION_SUCCESS
import com.hncr.ibms.tools.EVENTBUS_TOAST_STRING
import com.hncr.ibms.tools.hideKeyboard
import com.hncr.ibms.ui.floornavigation.FloorNagationViewModel
import com.jeremyliao.liveeventbus.LiveEventBus
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by LG
 * on 2023/8/7  11:19
 * Description：
 */
class SuggestionFragment : BaseFragment<FragmentSuggestionBinding>(){

    private val mSuggestionViewModel by viewModel<SuggestionViewModel>()

    override fun getLayoutResId(): Int = R.layout.fragment_suggestion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        LiveEventBus.get(EVENTBUS_ADDSUGGESTION_SUCCESS,Boolean::class.java).observe(this){
            if (it){
                Toast.makeText(activity,"感谢您的评价",Toast.LENGTH_LONG).show()
                binding.etSuggestion.setText("")
            }
        }

    }

    override fun initView() {

        binding.llBackSuggestion.setOnClickListener{
            findNavController().navigateUp()
        }

        binding.tvSubmit.setOnClickListener{
            val suggestion = SuggestionBean(binding.etSuggestion.text.toString(),"")
            mSuggestionViewModel.addSuggestion(suggestion)
        }

        binding.rlSuggestion.setOnClickListener{
            hideKeyboard(binding.rlSuggestion)
        }

        binding.tvCancel.setOnClickListener{
            binding.etSuggestion.setText("")
        }
    }

    override fun observeViewModels() {
    }
}