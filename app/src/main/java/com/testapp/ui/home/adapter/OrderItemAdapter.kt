package com.testapp.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.testapp.R

import com.testapp.data.model.response.PendingOrder
import com.testapp.databinding.ItemOrderBinding

class OrderItemAdapter(val list: List<PendingOrder>?) :
    RecyclerView.Adapter<OrderItemAdapter.ViewHolder>() {

    private var listener: AppClickListener? = null

    fun setAppClickListener(listener: AppClickListener) {
        this.listener = listener
    }

    inner class ViewHolder(private val dataBinding: ItemOrderBinding) :
        RecyclerView.ViewHolder(dataBinding.root) {

        fun onBind(
            position: Int,
            resultModel: PendingOrder
        ) {
            dataBinding.resultModel = resultModel
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ItemOrderBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_order,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(position, list!!.get(position))
    }

    interface AppClickListener {
        fun onClickListener(model: PendingOrder?)
    }

}