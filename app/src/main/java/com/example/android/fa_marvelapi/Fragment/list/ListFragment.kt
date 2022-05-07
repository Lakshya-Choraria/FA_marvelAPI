package com.example.android.fa_marvelapi.Fragment.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.fa_marvelapi.R
import com.example.android.fa_marvelapi.util.CounterViewModel
import com.example.android.fa_marvelapi.util.DetailListAdapter

class ListFragment : Fragment() {
    private lateinit var mUserViewModel: CounterViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_list, container, false)
        val adapter = DetailListAdapter()
        val recyclerView = view.findViewById(R.id.counterRecyclerView) as RecyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mUserViewModel = ViewModelProvider(this).get(CounterViewModel::class.java)
        mUserViewModel.readallData.observe(this, Observer { entry ->
            adapter.setData(entry)
        })
        return view
    }

}