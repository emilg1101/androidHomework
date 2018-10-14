package com.github.emilg1101.homework.adapter

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.github.emilg1101.homework.R
import com.github.emilg1101.homework.holder.BaseViewHolder
import com.github.emilg1101.homework.holder.CountryViewHolder
import com.github.emilg1101.homework.model.Country
import com.github.emilg1101.homework.utils.CountryDiffUtilCallback

class CountryAdapter(countryList: List<Country>, private val itemClickListener: (Country) -> Unit) : RecyclerView.Adapter<BaseViewHolder<Country>>() {

    private var list = mutableListOf<Country>()

    init {
        this.list.addAll(countryList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Country> {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false)
        return CountryViewHolder(v)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: BaseViewHolder<Country>, position: Int) {
        holder.bindViewHolder(list[position], itemClickListener)
    }

    fun updateList(newList: List<Country>) {
        val diffResult = DiffUtil.calculateDiff(CountryDiffUtilCallback(this.list, newList), false)
        diffResult.dispatchUpdatesTo(this)
        list.clear()
        list.addAll(newList)
    }
}
