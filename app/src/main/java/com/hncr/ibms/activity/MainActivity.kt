package com.hncr.ibms.activity

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.hncr.ibms.R
import com.hncr.ibms.ui.CompanyProfile.CompanyProfileFragment
import com.hncr.ibms.ui.main.MainFragment

/**
 * Loads [MainFragment].
 */
class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_browse_fragment, MainFragment())
                .commitNow()
        }
    }

    override fun onRestart() {
        super.onRestart()
        when (val frag =
            supportFragmentManager.fragments.first().childFragmentManager.fragments.first()) {

            is CompanyProfileFragment -> {
                frag.back()
            }
            else -> super.onBackPressed()
        }
    }

    override fun onBackPressed() {
        when (val frag =
            supportFragmentManager.fragments.first().childFragmentManager.fragments.first()) {

            is CompanyProfileFragment -> {
                frag.back()
            }
            else -> super.onBackPressed()
        }
    }
}