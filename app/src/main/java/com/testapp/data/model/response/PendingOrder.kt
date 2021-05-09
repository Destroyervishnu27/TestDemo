package com.testapp.data.model.response

data class PendingOrder(
    val created_at: String,
    val customer_name: String,
    val load_ref: String,
    val order_number: String,
    val order_price_formatted: String,
    val phone_number: String,
    val tahmeel_fee_in_cents: String
){
    fun getCustomerNameText():String{
        return "Customer Name: "+customer_name
    }

    fun getPriceText():String{
        return "Price: "+order_price_formatted
    }

    fun getPhoneText():String{
        return "Phone Number: "+phone_number
    }
    fun getLoadRefText():String{
        return "Load Ref: "+load_ref
    }
}