package com.hncr.ibms.ui.main

import java.util.Timer

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.leanback.app.BackgroundManager
import android.util.DisplayMetrics
import android.util.Log
import android.view.View
import androidx.navigation.fragment.findNavController

import com.hncr.ibms.*
import com.hncr.ibms.base.BaseFragment
import com.hncr.ibms.databinding.FragmentMainBinding
import com.hncr.ibms.tools.EVENTBUS_FLOOR_DATA
import com.hncr.ibms.ui.floornavigation.FloorNagationViewModel
import com.jeremyliao.liveeventbus.LiveEventBus
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

/**
 * Loads a grid of cards with movies to browse.
 */
class MainFragment : BaseFragment<FragmentMainBinding>() {

    private val mHandler = Handler(Looper.myLooper()!!)
    private lateinit var mBackgroundManager: BackgroundManager
    private var mDefaultBackground: Drawable? = null
    private lateinit var mMetrics: DisplayMetrics
    private var mBackgroundTimer: Timer? = null
    private var mBackgroundUri: String? = null

    private val mFloorNagationViewModel by sharedViewModel<FloorNagationViewModel>()

    private val TAG = "MainFragment"
    override fun getLayoutResId(): Int {
        return R.layout.fragment_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        mFloorNagationViewModel.init()


    }


    override fun initView() {
        binding.llCompanyProfile.setOnClickListener {

//            if (findNavController().currentDestination?.id == R.id.ll_company_profile){
                findNavController().navigate(MainFragmentDirections.actionMainFragmentToCompanyProfileFragment())
//            findNavController().navigate(R.id.action_mainFragment_to_companyProfileFragment)
                binding.mainBrowseFragment.visibility = View.INVISIBLE
//            }
        }

        binding.llFloorNavigation.setOnClickListener{
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToFloorNavigationFragment())
            binding.mainBrowseFragment.visibility = View.INVISIBLE
        }

        binding.llSuggestionBox.setOnClickListener{
            findNavController().navigate(MainFragmentDirections.actionMainFragmentToSuggestionFragment())
            binding.mainBrowseFragment.visibility = View.INVISIBLE
        }
    }

    override fun observeViewModels() {
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: " + mBackgroundTimer?.toString())
        mBackgroundTimer?.cancel()
    }

    override fun onResume() {
        binding.mainBrowseFragment.visibility = View.VISIBLE
        super.onResume()
    }

    override fun onStop() {
        super.onStop()
    }






}