package com.hncr.ibms.ext

import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.hncr.ibms.R
import com.hncr.ibms.base.AppContext

/**
 * Created by LG
 * on 2023/7/26  10:06
 * Description：
 */
fun btiToast(str: String) {
    val toast = Toast.makeText(AppContext, str, Toast.LENGTH_LONG)
    toast.setGravity(Gravity.BOTTOM, 0, 2340 / 9)
    val layout = toast.view
    if (layout is LinearLayout) {
        layout.setBackgroundResource(R.drawable.shape_toast)
        if (layout.childCount > 0) {
            val tv = layout.getChildAt(0)
            if (tv is TextView) {
                tv.textSize = tv.resources.getDimension(R.dimen.dp14)
                tv.setTextColor(ContextCompat.getColor(tv.context, R.color.white))
            }
        }
    }
    toast.show()
}
