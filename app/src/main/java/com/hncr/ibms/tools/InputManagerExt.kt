package com.hncr.ibms.tools

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * Created by LG
 * on 2023/7/26  9:52
 * Description：
 */
fun hideKeyboard(view: View) {
    val manager = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    view.windowToken?.let {
        manager.hideSoftInputFromWindow(it, 0)
    }
}

fun showKeyboard(view: View) {
    val manager = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    manager.showSoftInput(view, 0)
}