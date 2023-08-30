package com.hncr.ibms.base

import android.app.Application
import android.content.ContextWrapper
import com.hncr.ibms.di.allModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * Created by LG
 * on 2023/7/26  10:09
 * Description：
 */
class BaseApplication : Application(){

    override fun onCreate() {
        super.onCreate()

        initKoin()

    }


    private fun initKoin(){
        startKoin {
            androidContext(this@BaseApplication)
            modules(allModule)
        }
    }


}
lateinit var mApplication: Application

object AppContext : ContextWrapper(mApplication)