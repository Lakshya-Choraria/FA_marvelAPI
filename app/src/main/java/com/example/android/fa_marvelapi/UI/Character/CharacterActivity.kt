package com.example.android.fa_marvelapi.UI.Character

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.android.fa_marvelapi.databinding.ActivityCharacterBinding
import com.example.android.fa_marvelapi.UI.CharacterList.CharViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CharacterActivity : AppCompatActivity() {
    private val viewModelCharacter : CharViewModel by viewModels()
    private var id : Int = 0
    private lateinit var binding : ActivityCharacterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharacterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if(intent!=null){
            id = intent.getIntExtra("id",0)
            viewModelCharacter.getCharacterByIdValue(id.toString())
            CoroutineScope(Dispatchers.Main).launch {
                viewModelCharacter._characterValue.collect {
                    when{
                        it.isLoading ->{
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        it.error.isNotBlank() ->{
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(this@CharacterActivity,"Unexpected Error", Toast.LENGTH_LONG).show()
                        }
                        it.characterDetail.isNotEmpty()->{
                            binding.progressBar.visibility = View.GONE
                            it.characterDetail.map { character ->
                                val url = "${character.thumbnail}/landscape_medium.${character.thumbnailExt}"
                                Picasso.get().load(url).placeholder(R.drawable.image5).into(binding.appCompatImageView)
                                binding.textView.text = character.name
                                binding.textView2.text = character.description
                                Log.d("description",character.description)
                                binding.textView3.text = character.comics.toString()
                            }
                        }
                    }
                }
            }
        }
    }
}