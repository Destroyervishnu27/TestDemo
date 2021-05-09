package com.testapp.base

import android.app.Activity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.testapp.MainActivity
import com.testapp.data.repo.repo_base.ConnectionDetector
import com.testapp.di.factory.ViewModelProviderFactory
import com.testapp.R
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment<D : ViewDataBinding, V : ViewModel> : DaggerFragment() {

    @set:Inject
    protected var providerFactory: ViewModelProviderFactory? = null

    protected lateinit var dataBinding: D
    protected lateinit var viewModel: V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(requireActivity(), providerFactory).get(getViewModel())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, getLayoutResource(), container, false)
        return dataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
    }

    abstract fun getLayoutResource(): Int
    abstract fun init()

    protected abstract fun getViewModel(): Class<V>

    protected fun getMainActivity(): MainActivity? {
        if (isValidActivity())
            return activity as MainActivity
        return null
    }

    protected fun isValidActivity(): Boolean {
        if (activity == null || requireActivity().isFinishing) return false
        return true
    }

    protected fun printLog(tag: String, msg: String) {
        Log.e(tag, msg)
    }

    @Synchronized
    protected fun hideKeyboard() {
        var view: View? = requireActivity().currentFocus
        view.apply {
            view = View(requireContext())
        }
        hideKeyboard(view)
    }

    @Synchronized
    protected fun hideKeyboard(view: View?) {
        if (view == null) {
            return
        }
        val imm: InputMethodManager =
            requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0)
    }


    fun showToast(msg: String) {
        Toast.makeText(
            requireContext(),
            msg,
            Toast.LENGTH_SHORT
        ).show()
    }

    protected fun getContentView(): Int {
        return R.id.fragmentRootView
    }

    protected fun getErrorView(): Int {
        return R.id.view_error_view
    }

    protected fun showErrorView(message: String) {
        val contectView = dataBinding.root.findViewById<ViewGroup>(getContentView())
        contectView.visibility = View.GONE
        val errorGrooup = dataBinding.root.findViewById<ConstraintLayout>(getErrorView())
        errorGrooup.visibility = View.VISIBLE
        errorGrooup.findViewById<TextView>(R.id.tv_message).setText(message)
        errorGrooup.findViewById<Button>(R.id.btn_retry).setOnClickListener {
            if (ConnectionDetector.isNetAvailable(requireContext())) {
                contectView.visibility = View.VISIBLE
                errorGrooup.visibility = View.GONE
                init()
            }
        }
    }

    protected open fun showSnackbarError(message: String?) {
        message?.let {
            if (!TextUtils.isEmpty(it)) {
                try {
                    val snackbar = Snackbar
                        .make(
                            dataBinding.root.findViewById(R.id.root),
                            it,
                            Snackbar.LENGTH_LONG
                        )
                    snackbar.view
                        .setBackgroundColor(
                            ContextCompat.getColor(
                                requireContext(),
                                R.color.colorAccent
                            )
                        )
                    val textView = snackbar.view
                        .findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
                    textView.setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.whiteColor
                        )
                    )
                    snackbar.show()
                } catch (e: Exception) {
                    printLog("", msg = it)
                }
            } else showSnackbarError(requireContext().getString(R.string.Something_went_wrong))
        }
    }

}