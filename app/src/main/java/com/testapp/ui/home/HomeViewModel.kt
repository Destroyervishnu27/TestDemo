package com.testapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.testapp.data.model.response.OrderResponseModel
import com.testapp.data.repo.home.HomeApi
import com.testapp.data.repo.repo_base.RepoCallback
import com.testapp.data.repo.repo_base.Resource
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val homeRepository: HomeApi) : ViewModel() {

    private var result = MutableLiveData<Resource<OrderResponseModel, Resource.Status>>()
    private var disposable: Disposable? = null


    fun getLiveData(): LiveData<Resource<OrderResponseModel, Resource.Status>> {
        return result
    }

    fun shouldLoadData() = result.value?.status != Resource.Status.SUCCESS


    fun getOrderList(): LiveData<Resource<OrderResponseModel, Resource.Status>> {
        disposable =
            homeRepository.getOrderList( object : RepoCallback<OrderResponseModel> {

                override fun onResult(result: Resource<OrderResponseModel?, Resource.Status>) {
                    this@HomeViewModel.result.postValue(
                        Resource(
                            result.payload,
                                result.status,
                            result.message
                        )
                    )
                }
            })
        return result
    }

    override fun onCleared() {
        disposable?.dispose()
        super.onCleared()
    }
}