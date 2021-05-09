package com.testapp.data.repo

import com.testapp.data.model.response.OrderResponseModel
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET

interface WebService {
    @GET("pending?page=0")
    fun getOrderList(): Observable<Response<OrderResponseModel>>
}