package com.testapp.ui.splash

import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.testapp.R
import com.testapp.base.BaseFragment
import com.testapp.data.repo.repo_base.SPLASH_SCREEN_DELAY_MS
import com.testapp.databinding.FragmentSplashBinding

class SplashFragment : BaseFragment<FragmentSplashBinding, SplashViewModel>() {


    override fun getLayoutResource(): Int {
        return R.layout.fragment_splash
    }

    override fun getViewModel(): Class<SplashViewModel> {
        return SplashViewModel::class.java
    }

    override fun init() {
        initToolBar()
    }

    private fun initToolBar() {
        getMainActivity()?.apply {
            hideToolBar()
            lockDrawer()
        }
    }

    override fun onResume() {
        super.onResume()
        showHomePage()
    }

    private fun showHomePage() {
        viewModel.getResult().observe(viewLifecycleOwner, Observer {
            findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
        })
        viewModel.showHomePage(SPLASH_SCREEN_DELAY_MS)
    }

}