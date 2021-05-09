package com.testapp.data.repo.home

import com.testapp.data.model.response.OrderResponseModel
import com.testapp.data.repo.repo_base.RepoCallback
import io.reactivex.disposables.Disposable

interface HomeApi {
    fun getOrderList(
        callback: RepoCallback<OrderResponseModel>
    ): Disposable
}