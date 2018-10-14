package com.github.emilg1101.homework.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.View
import com.github.emilg1101.homework.adapter.CountryAdapter
import com.github.emilg1101.homework.R
import com.github.emilg1101.homework.model.Country
import com.github.emilg1101.homework.utils.CountryParser
import kotlinx.android.synthetic.main.fragment_dashboard.*

class DashboardFragment : BaseFragment() {

    private var countryList = listOf<Country>()

    private var adapter: CountryAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        countryList = CountryParser.parse(requireContext())

        list_country.layoutManager = LinearLayoutManager(activity)
        adapter = CountryAdapter(countryList) {}
        list_country.adapter = adapter
    }

    override fun onOptionsItemSelected(item: MenuItem?) = when (item?.itemId) {
        R.id.action_sort_iso -> {
            adapter?.updateList(sortByIso())
            true
        }
        R.id.action_sort_location -> {
            adapter?.updateList(sortByLocation())
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    override val title: Int
        get() = R.string.fragment_title_dashboard

    override val contentLayout: Int
        get() = R.layout.fragment_dashboard

    override val menuRes: Int?
        get() = R.menu.menu_dashboard

    private fun sortByIso() = countryList.sortedBy { it.iso }

    private fun sortByLocation() = countryList.sortedBy { it.location }

    companion object {
        fun newInstance() = DashboardFragment()
    }
}
