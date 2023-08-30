package com.hncr.ibms.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Created by LG
 * on 2023/8/2  9:54
 * Description：
 */
open class BaseViewModel : ViewModel() {

    fun launch(block : suspend CoroutineScope.() -> Unit){
        viewModelScope.launch(Dispatchers.Default){

            delay(50)
            block()

        }
    }

}