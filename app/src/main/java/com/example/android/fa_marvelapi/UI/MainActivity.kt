package com.example.android.fa_marvelapi.UI

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ShareActionProvider
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.fa_marvelapi.UI.Character.CharacterActivity
import com.example.android.fa_marvelapi.UI.CharacterList.CharViewModel
import com.example.android.fa_marvelapi.UI.Details.detailActivity
import com.example.android.fa_marvelapi.databinding.ActivityMainBinding
import com.example.android.fa_marvelapi.domain.model.Character
import com.example.android.fa_marvelapi.util.CharacterListAdapter
import com.example.android.fa_marvelapi.util.Constants
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    var flag = 3
    var paginatedValue = 0
    private  lateinit var recyclerView : RecyclerView
    private lateinit var adapter : CharacterListAdapter
    private lateinit var layoutManager : GridLayoutManager
    private lateinit var fab : FloatingActionButton
    private val viewModel: CharViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        recyclerView = binding.CharacterRecyclerView
        fab = binding.fab
        layoutManager =GridLayoutManager(this, 2)
        recyclerViewCharacter()
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if(layoutManager.findLastVisibleItemPosition()==layoutManager.itemCount-1){
                    paginatedValue += 20
                    viewModel.getAllCharactersData(paginatedValue)
                    callApi()
                }

            }
        })
        fab.setOnClickListener{
            val intent = Intent(this, detailActivity::class.java)
            this.startActivity(intent)
        }
        Log.d("tag", Constants.timeStamp)
    }

    private fun callApi() {
        CoroutineScope(Dispatchers.Main).launch {
            repeat(flag){
                viewModel._marvelValue.collect {
                    when{
                        it.isLoading ->{
                            binding.progressbar.visibility = View.VISIBLE
                        }
                        it.error.isNotBlank() ->{
                            binding.progressbar.visibility = View.GONE
                            flag = 0
                            Toast.makeText(this@MainActivity,it.error,Toast.LENGTH_LONG).show()
                        }
                        it.characterList.isNotEmpty()->{
                            binding.progressbar.visibility = View.GONE
                            flag = 0
                            adapter.setData(it.characterList as ArrayList<Character>)
                        }
                    }
                }
                delay(1000)
            }
        }
    }

    private fun recyclerViewCharacter(){
        recyclerView = binding.CharacterRecyclerView
        adapter = CharacterListAdapter(this, ArrayList())
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }
    override fun onStart() {
        super.onStart()
        viewModel.getAllCharactersData(paginatedValue)
        callApi()
    }
}