package com.superheroes.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.superheroes.R
import com.superheroes.data.SuperheroItem
import com.superheroes.databinding.ItemSuperheroBinding

class SuperHeroAdapter(private var superheros: List<SuperheroItem> = emptyList()): RecyclerView.Adapter<SuperheroViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperheroViewHolder {
        return SuperheroViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_superhero, parent, false)
        )
    }

    override fun getItemCount() = superheros.size

    override fun onBindViewHolder(holder: SuperheroViewHolder, position: Int) {
        holder.bind(superheros[position])
    }
}

class SuperheroViewHolder(view:View) : RecyclerView.ViewHolder(view) {

    private val itemSuperheroBinding = ItemSuperheroBinding.bind(view)

    fun bind(superheroItem: SuperheroItem) {
        itemSuperheroBinding.name.text = superheroItem.name
       // itemSuperheroBinding.imgHero.setImageResource(superheroItem.superheroImage)
    }
}