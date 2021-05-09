package com.testapp.ui.home

import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.testapp.R

import com.testapp.base.BaseFragment
import com.testapp.data.model.response.OrderResponseModel
import com.testapp.data.model.response.PendingOrder
import com.testapp.data.repo.repo_base.ConnectionDetector
import com.testapp.data.repo.repo_base.Resource
import com.testapp.databinding.FragmentHomeBinding
import com.testapp.ui.home.adapter.OrderItemAdapter

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {


    private lateinit var popularItemAdapter: OrderItemAdapter
    private var list: MutableList<PendingOrder>? = null


    override fun getLayoutResource(): Int {
        return R.layout.fragment_home
    }

    override fun getViewModel(): Class<HomeViewModel> {
        return HomeViewModel::class.java
    }

    override fun init() {
        initToolBar()
        initRv()

        viewModel.getLiveData().observe(viewLifecycleOwner, Observer {
            stopShimmerEffect()
            when (it.status) {
                Resource.Status.SUCCESS ->
                    updateUi(it.payload)
                else ->
                    showErrorView(
                        getString(R.string.network_error)
                    )
            }
        })

        if (viewModel.shouldLoadData())
            getData()

    }

    private fun initToolBar() {
        getMainActivity()?.apply {
            showToolBar()
            showLeftMenuOnToolBar()
            hideBackOnToolBar()
            showTitleOnToolBar(getString(R.string.app_name))
            unlockDrawer()
        }

    }

    private fun startShimmerEffect() {
        dataBinding.shimmerViewContainer.visibility = View.VISIBLE
        dataBinding.shimmerViewContainer.startShimmer()
    }

    private fun stopShimmerEffect() {
        dataBinding.shimmerViewContainer.visibility = View.GONE
        dataBinding.shimmerViewContainer.stopShimmer()
    }

    private fun updateUi(payload: OrderResponseModel?) {
        if (payload == null) {
            showSnackbarError(getString(R.string.not_found))
            return
        }

        val results = payload.pending_orders
        if (results.isNullOrEmpty()) {
            showSnackbarError(getString(R.string.not_found))
            return
        }
        list?.clear()
        list?.addAll(results)
        popularItemAdapter.notifyDataSetChanged()
    }

    private fun getData() {
        if (ConnectionDetector.isNetAvailable(requireContext())) {
            startShimmerEffect()
            viewModel.getOrderList()
        } else {
            showSnackbarError(getString(R.string.network_error))
        }
    }

    private fun initRv() {
        list = mutableListOf()
        dataBinding.rvView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        popularItemAdapter = OrderItemAdapter(list)
        popularItemAdapter.setAppClickListener(object : OrderItemAdapter.AppClickListener {
            override fun onClickListener(model: PendingOrder?) {
            }
        })
        dataBinding.rvView.adapter = popularItemAdapter
    }

    override fun onPause() {
        val activated = dataBinding.shimmerViewContainer.isActivated
        if (activated) {
            dataBinding.shimmerViewContainer.visibility = View.GONE
            dataBinding.shimmerViewContainer.stopShimmer()
        }
        super.onPause()
    }


}

