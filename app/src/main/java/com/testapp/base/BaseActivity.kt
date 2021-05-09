package com.testapp.base

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.BuildConfig
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.testapp.MainActivity
import com.testapp.helper.LeftNavigationViewHelper
import com.testapp.helper.ToolBarHelper
import com.testapp.R
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity<D : ViewDataBinding> : DaggerAppCompatActivity() {

    @Inject
    lateinit var TAG: String

    lateinit var dataBinding: D
    private lateinit var toolBarHelper: ToolBarHelper
    private lateinit var leftNavHelper: LeftNavigationViewHelper
    lateinit var navController: NavController
    private var progressDialog: Dialog? = null
    private lateinit var tv_loader_msg: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = DataBindingUtil.setContentView(this, getLayoutResource())
        init()
    }

    abstract fun getLayoutResource(): Int
    abstract fun init()

    fun initAllUi(activity: MainActivity) {
        toolBarHelper = ToolBarHelper(activity)
        leftNavHelper = LeftNavigationViewHelper(activity)
        toolBarHelper.init()
        leftNavHelper.init()
        navController = activity.findNavController(R.id.fragment_nav_host)
        inItProgressDialog()

    }

    private fun inItProgressDialog() {
        progressDialog = Dialog(this, R.style.CustomAlertDialogStyle)
        progressDialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        progressDialog?.setContentView(R.layout.progress_dialog)
        tv_loader_msg = progressDialog?.findViewById<TextView>(R.id.tv_loader_msg)!!
        progressDialog?.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    }

    fun printLog(msg: String) {
        if (BuildConfig.DEBUG)
            Log.d(TAG, msg)
    }

    fun hideToolBar() {
        toolBarHelper.hideToolBar()
    }

    fun showToolBar() {
        toolBarHelper.showToolBar()

    }

    fun showLeftMenuOnToolBar() {
        toolBarHelper.showLeftMenuOnToolBar()
    }

    fun hideLeftMenuOnToolBar() {
        toolBarHelper.hideLeftMenuOnToolBar()
    }

    fun hideTitleOnToolBar() {
        toolBarHelper.hideTitleOnToolBar()
    }

    fun showTitleOnToolBar(title: String) {
        toolBarHelper.showTitleOnToolBar(title)
    }

    fun hideBackOnToolBar() {
        toolBarHelper.hideBackOnToolBar()
    }

    fun showBackOnToolBar() {
        toolBarHelper.showBackOnToolBar()
    }


    fun toggleLeft() {
        toolBarHelper.toggleLeft()

    }

    fun showToast(msg: String) {
        if (isFinishing) return
        Toast.makeText(
            this,
            msg,
            Toast.LENGTH_SHORT
        ).show()
    }

    open fun unlockDrawer() {
        toolBarHelper.unlockDrawer()

    }

    fun lockDrawer() {
        toolBarHelper.lockDrawer()
    }

    override fun onBackPressed() {
        if (toolBarHelper.isLeftMenuOpen()) {
            toolBarHelper.toggleLeft()
            return
        }
        super.onBackPressed()
    }

}