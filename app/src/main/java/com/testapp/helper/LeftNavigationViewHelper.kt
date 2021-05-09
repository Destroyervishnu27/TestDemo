package com.testapp.helper

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.navigation.NavigationView
import com.testapp.MainActivity
import com.testapp.R

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.left_menu.view.*

open class LeftNavigationViewHelper(private var activity: MainActivity) : View.OnClickListener {
    private var nav_view_left: NavigationView = activity.nav_view_left

    private var tv_home: TextView
    private var iv_home: ImageView

    init {
        this.tv_home = nav_view_left.tv_home
        this.iv_home = nav_view_left.iv_home
    }

    fun getLeftNavView(): NavigationView {
        return nav_view_left
    }

    fun init() {
        tv_home.setOnClickListener(this)
        iv_home.setOnClickListener(this)

    }

    override fun onClick(view: View?) {
        activity.toggleLeft()
        when (view!!.id) {
            R.id.iv_home, R.id.tv_home -> activity.toggleLeft()
        }
    }

}