package com.testapp.data.repo.home

import com.testapp.data.model.response.OrderResponseModel
import com.testapp.data.repo.WebService
import com.testapp.data.repo.repo_base.CallbackWrapper
import com.testapp.data.repo.repo_base.RepoCallback
import com.testapp.data.repo.repo_base.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class HomeRepository @Inject constructor(private val webService: WebService) : HomeApi {


    override fun getOrderList(callback: RepoCallback<OrderResponseModel>): Disposable {
        val callbackWrapper = getCallbackWrapper(callback)
        webService.getOrderList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(callbackWrapper)
        return callbackWrapper
    }

    private fun getCallbackWrapper(callback: RepoCallback<OrderResponseModel>): CallbackWrapper<OrderResponseModel> {
        return CallbackWrapper(object :
            CallbackWrapper.HttpHandler<OrderResponseModel>(callback) {
            override fun onSuccess(t: OrderResponseModel?) {
                callback.onResult(Resource(t, Resource.Status.SUCCESS, ""))
            }
        })
    }

}