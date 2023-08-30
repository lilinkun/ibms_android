package com.hncr.ibms.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.hncr.ibms.activity.MainActivity

/**
 * Created by LG
 * on 2023/8/4  14:18
 * Description：
 */
class BootReceiver : BroadcastReceiver() {


    override fun onReceive(context: Context?, intent: Intent?) {
        val action =  intent?.action
        if (action.equals(Intent.ACTION_BOOT_COMPLETED)) run {
            startApp(context)
        }
    }

    fun startApp(context: Context?){
        val intent = Intent(context, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY)
        context?.startActivity(intent)

    }
}