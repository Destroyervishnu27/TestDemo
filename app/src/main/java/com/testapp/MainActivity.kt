package com.testapp

import com.testapp.base.BaseActivity
import com.testapp.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {

    override fun getLayoutResource(): Int {
        return R.layout.activity_main
    }

    override fun init() {
        initAllUi(this)
    }

}