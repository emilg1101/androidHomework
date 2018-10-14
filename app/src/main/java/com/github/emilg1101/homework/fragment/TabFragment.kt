package com.github.emilg1101.homework.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.emilg1101.homework.R
import kotlinx.android.synthetic.main.fragment_tab.*

class TabFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragment_tab, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        text_frag.text = arguments?.getString(TAG)
    }

    companion object {

        const val TAG = "tag"

        fun newInstance(tag: String) = TabFragment().apply {
            arguments = Bundle().apply {
                putString(TAG, tag)
            }
        }
    }
}
