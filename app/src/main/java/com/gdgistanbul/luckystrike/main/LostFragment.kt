package com.gdgistanbul.luckystrike.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gdgistanbul.luckystrike.R

class LostFragment : Fragment() {

    companion object {
        fun newInstance() = LostFragment()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater?.inflate(R.layout.fragment_lost, container, false)
        return view?.rootView!!
    }
}
