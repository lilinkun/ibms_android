package com.hncr.ibms.ui.floornavigation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.hncr.ibms.R
import com.hncr.ibms.adapter.GridAdapter
import com.hncr.ibms.base.BaseFragment
import com.hncr.ibms.databinding.FragmentFloorNavigationBinding
import com.hncr.ibms.http.data.FloorItem
import com.hncr.ibms.tools.EVENTBUS_FLOOR_DATA
import com.hncr.ibms.tools.EVENTBUS_FLOOR_ID
import com.hncr.ibms.tools.EVENTBUS_TOAST_STRING
import com.hncr.ibms.tools.hideKeyboard
import com.hncr.ibms.ui.main.MainFragmentDirections
import com.jeremyliao.liveeventbus.LiveEventBus
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Created by LG
 * on 2023/7/30  11:38
 * Description：
 */
class FloorNavigationFragment : BaseFragment<FragmentFloorNavigationBinding>() {

    private val mFloorViewModel by viewModel<FloorNagationViewModel>()

    var floorList : List<FloorItem> = listOf()

    override fun getLayoutResId(): Int {
        return R.layout.fragment_floor_navigation
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LiveEventBus.get(EVENTBUS_FLOOR_DATA,
        Boolean::class.java).observe(this){
            if (it){
                getFloorSuccess(mFloorViewModel.floorList)
            }
        }

        LiveEventBus.get(EVENTBUS_FLOOR_ID,String::class.java).observe(this){

            findNavController().navigate(FloorNavigationFragmentDirections.actionFloorNavigationFragmentToFloorPersonFragment(it,1))
            binding.rlFloorNavigation.visibility = View.INVISIBLE
        }

        mFloorViewModel.init()
    }

    override fun initView() {

        binding.llBackFloor.setOnClickListener(View.OnClickListener {
            findNavController().navigateUp()
        })

        binding.tvSearch.setOnClickListener{

            if (binding.etName.text.isNullOrBlank()){
                LiveEventBus.get(EVENTBUS_TOAST_STRING,String::class.java).post("请输入搜索姓名")
            }else {

                findNavController().navigate(
                    FloorNavigationFragmentDirections.actionFloorNavigationFragmentToFloorPersonFragment(
                        binding.etName.text.toString(),2
                    )
                )
                binding.rlFloorNavigation.visibility = View.INVISIBLE

            }
        }

        binding.rlFloorNavigation.setOnClickListener{
            hideKeyboard(it)
        }

    }

    override fun observeViewModels() {
    }

    override fun onStart() {
        super.onStart()

        binding.rlFloorNavigation.visibility = View.VISIBLE

        if (mFloorViewModel.floorList != null){
            getFloorSuccess(mFloorViewModel.floorList)
        }
    }

    fun getFloorSuccess(floorItems : List<FloorItem>){
        binding.gridView.adapter = activity?.let { GridAdapter(it,floorItems) }
    }
}