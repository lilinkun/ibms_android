package com.hncr.ibms.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import com.hncr.ibms.ext.btiToast
import com.hncr.ibms.tools.EVENTBUS_TOAST_STRING
import com.hncr.ibms.tools.hideKeyboard
import com.jeremyliao.liveeventbus.LiveEventBus

/**
 * Created by LG
 * on 2023/7/26  9:04
 * Description：
 */
abstract class BaseFragment<T : ViewDataBinding> : Fragment(){

    lateinit var binding : T
    var hindKeyBoard = true
    var showSuccessToast = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LiveEventBus.get(EVENTBUS_TOAST_STRING,String::class.java).observe(this){
            str -> if (isActive() && showSuccessToast){
                str?.let { btiToast(str) }
        }
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,getLayoutResId(),container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observeViewModels()
    }


    abstract fun getLayoutResId(): Int
    abstract fun initView()
    abstract fun observeViewModels()


    override fun onResume() {
        super.onResume()
        if (hindKeyBoard) {
            hideKeyboard(binding.root)
        }
    }

    fun isActive(): Boolean {
        if (lifecycle.currentState == Lifecycle.State.RESUMED || lifecycle.currentState == Lifecycle.State.STARTED) {
            return true
        }
        return false
    }

    fun isResume(): Boolean {
        if (lifecycle.currentState == Lifecycle.State.RESUMED) {
            return true
        }
        return false
    }
}