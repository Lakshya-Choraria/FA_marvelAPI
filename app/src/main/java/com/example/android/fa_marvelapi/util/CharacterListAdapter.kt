package com.example.android.fa_marvelapi.util

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android.fa_marvelapi.R
import com.example.android.fa_marvelapi.UI.Character.CharacterActivity
import com.example.android.fa_marvelapi.domain.model.Character
import com.example.android.fa_marvelapi.domain.model.Entity
import java.util.ArrayList

class CharacterListAdapter(private val context:Context,var itemlist:ArrayList<Character>):RecyclerView.Adapter<CharacterListAdapter.CharacterListViewHolder>() {
    inner class CharacterListViewHolder(view: View):RecyclerView.ViewHolder(view){
        val characterName : TextView = view.findViewById(R.id.txtcharchtername)
        val thumbnail : ImageView = view.findViewById(R.id.charcterimage)
        val cardCharacter : LinearLayout = view.findViewById(R.id.charachtersLinerLayout)
    }
    private lateinit var mCounterViewModel: CounterViewModel
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.character_card,parent,false)
        return CharacterListViewHolder(view)
    }

    override fun onBindViewHolder(holder: CharacterListViewHolder, position: Int) {
        val list = itemlist[position]
        holder.characterName.text = list.name
        val imageURL = "${list.thumbnail}/portrait_xlarge.${list.thumbnailExit}".replace("http", "https")
        Glide.with(context).load(imageURL).into(holder.thumbnail)
        holder.cardCharacter.setOnClickListener{
            insertDatatoDatabase(holder.characterName.text.toString())
            updateDatatoDatabase(holder.characterName.text.toString())
            val intent = Intent(context, CharacterActivity::class.java)
            intent.putExtra("id",list.id)
            context.startActivity(intent)
        }
    }

    private fun updateDatatoDatabase(charName: String) {
        mCounterViewModel = CounterViewModel(context.applicationContext as Application)
        val entry = mCounterViewModel.searchDatabase("%$charName%")
        mCounterViewModel.incrementCounter(entry)
    }

    private fun insertDatatoDatabase(charName:String) {
        mCounterViewModel = CounterViewModel(context.applicationContext as Application)
        val entry = Entity(0,charName,0)
        mCounterViewModel.addCounter(entry)
    }
    override fun getItemCount(): Int {
        return itemlist.size
    }
    @SuppressLint("NotifyDataSetChanged")
    fun setData(characterList:ArrayList<Character>){
        this.itemlist.addAll(characterList)
        notifyDataSetChanged()
    }
}

