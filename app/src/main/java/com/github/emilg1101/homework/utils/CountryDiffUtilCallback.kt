package com.github.emilg1101.homework.utils

import android.support.v7.util.DiffUtil
import com.github.emilg1101.homework.model.Country

class CountryDiffUtilCallback(private val oldList: List<Country>, private val newList: List<Country>) : DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}
