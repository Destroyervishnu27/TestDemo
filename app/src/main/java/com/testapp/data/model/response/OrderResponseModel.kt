package com.testapp.data.model.response

data class OrderResponseModel(
    val page: Int,
    val pending_orders: List<PendingOrder>
)