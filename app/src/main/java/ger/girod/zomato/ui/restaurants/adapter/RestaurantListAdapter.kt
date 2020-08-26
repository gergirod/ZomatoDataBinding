package ger.girod.zomato.ui.restaurants.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import ger.girod.zomato.R
import ger.girod.zomato.databinding.ItemRowBindingImpl
import ger.girod.zomato.ui.models.RestaurantViewModel
import java.util.*

class RestaurantListAdapter :
    RecyclerView.Adapter<RestaurantListAdapter.RestaurantRowViewHolder>() {

    companion object {
        @JvmStatic
        @BindingAdapter("items")
        fun RecyclerView.bindItems(items: List<RestaurantViewModel>?) {
            val adapter = adapter as RestaurantListAdapter
            items?.let {
                adapter.setList(items)
            }
        }
    }

    private var list: ArrayList<RestaurantViewModel> = ArrayList()

    fun setList(list: List<RestaurantViewModel>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantRowViewHolder {
        return RestaurantRowViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_row,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: RestaurantRowViewHolder, position: Int) {
        holder.bindingImpl.restaurant = list[position]
    }

    inner class RestaurantRowViewHolder(val bindingImpl: ItemRowBindingImpl) :
        RecyclerView.ViewHolder(bindingImpl.root) {
    }
}