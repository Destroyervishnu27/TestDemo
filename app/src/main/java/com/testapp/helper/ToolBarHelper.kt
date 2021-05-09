package com.testapp.helper

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.testapp.MainActivity
import com.testapp.R

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.tool_bar.*

open class ToolBarHelper(private var activity: MainActivity) : View.OnClickListener {

    private var drawer_layout: DrawerLayout = activity.drawer_layout
    private var cl_tool_bar: ConstraintLayout = activity.cl_tool_bar
    private var iv_left_menu: ImageView = activity.iv_left_menu
    private var iv_back: ImageView = activity.iv_back
    private var iv_search: ImageView = activity.iv_search
    private var tv_title: TextView = activity.tv_name

    fun init() {
        iv_left_menu.setOnClickListener(this)
        iv_back.setOnClickListener(this)
        iv_search.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view!!.id) {
            R.id.iv_left_menu -> toggleLeft()
            R.id.iv_search -> searchClick()
            R.id.iv_back -> activity.onBackPressed()
        }
    }

    fun toggleLeft() {
        if (drawer_layout.isDrawerVisible(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            drawer_layout.openDrawer(GravityCompat.START)
        }
    }

  fun searchClick() {

    }

    fun isLeftMenuOpen(): Boolean {
        return drawer_layout.isDrawerVisible(GravityCompat.START)
    }

    fun hideToolBar() {
        cl_tool_bar.visibility = View.GONE
    }

    fun showToolBar() {
        cl_tool_bar.visibility = View.VISIBLE
    }

    fun hideLeftMenuOnToolBar() {
        iv_left_menu.visibility = View.GONE
    }

    fun showLeftMenuOnToolBar() {
        iv_left_menu.visibility = View.VISIBLE
    }

    fun hideTitleOnToolBar() {
        tv_title.visibility = View.GONE
    }

    fun showTitleOnToolBar(title: String) {
        tv_title.setText(title)
        tv_title.visibility = View.VISIBLE
    }

    fun hideBackOnToolBar() {
        iv_back.visibility = View.GONE
    }

    fun showBackOnToolBar() {
        iv_back.visibility = View.VISIBLE
    }

    open fun unlockDrawer() {
        drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
    }

    open fun lockDrawer() {
        drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
    }
}