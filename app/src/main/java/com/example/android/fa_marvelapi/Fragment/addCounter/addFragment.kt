package com.example.android.fa_marvelapi.Fragment.addCounter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.android.fa_marvelapi.R
import com.example.android.fa_marvelapi.util.CounterViewModel

class addFragment : Fragment() {
    private lateinit var mCounterViewModel: CounterViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        mCounterViewModel = ViewModelProvider(this).get(CounterViewModel::class.java)

        insertDatatoDatabase()
        return view
    }

    private fun insertDatatoDatabase() {
    }
}