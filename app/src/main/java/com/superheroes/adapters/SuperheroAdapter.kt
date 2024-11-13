package com.superheroes.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.superheroes.R
import com.superheroes.data.SuperheroItem
import com.superheroes.databinding.ItemSuperheroBinding

class SuperHeroAdapter(private var superheros: List<SuperheroItem> = emptyList(),
                       private val onClickListener: (SuperheroItem) -> Unit): RecyclerView.Adapter<SuperheroViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperheroViewHolder {
        return SuperheroViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_superhero, parent, false)
        )
    }

    override fun getItemCount() = superheros.size

    override fun onBindViewHolder(holder: SuperheroViewHolder, position: Int) {
        holder.bind(superheros[position], onClickListener)
    }

    fun updateSuperheroes (list: List<SuperheroItem>) {
        superheros = list
        notifyDataSetChanged()
    }
}

class SuperheroViewHolder(view:View) : RecyclerView.ViewHolder(view) {

    private val itemSuperheroBinding = ItemSuperheroBinding.bind(view)

    fun bind(superheroItem: SuperheroItem, onClickListener: (SuperheroItem) -> Unit) {
        itemSuperheroBinding.name.text = superheroItem.name
        Picasso.get().load(superheroItem.superheroImage.url).into(itemSuperheroBinding.imgHero);
        itemView.setOnClickListener {
            onClickListener(superheroItem)
        }
    }
}