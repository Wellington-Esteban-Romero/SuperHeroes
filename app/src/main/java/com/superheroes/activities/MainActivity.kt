package com.superheroes.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.superheroes.R
import com.superheroes.activities.DetailActivity.Companion.EXTRA_SUPERHERO_ID
import com.superheroes.adapters.SuperHeroAdapter
import com.superheroes.data.SuperheroItem
import com.superheroes.databinding.ActivityMainBinding
import com.superheroes.services.SuperheroService
import com.superheroes.utils.RetrofitProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var superheroesService: SuperheroService
    private lateinit var adapterSuperhero: SuperHeroAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        init()

        superheroesService =   RetrofitProvider.getRetrofit()
    }

    private fun init () {

        getActionBarSuperHero()

        adapterSuperhero = SuperHeroAdapter() { superheroItem ->
            onItemSelect(superheroItem)
        }
        binding.rvSuperheroes.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = adapterSuperhero
            hasFixedSize()
        }
    }

     private fun getActionBarSuperHero ():Unit {
        val actionBar = supportActionBar
        actionBar!!.title = "SuperHeroes"
        actionBar!!.setDisplayShowHomeEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.search_menu, menu)
        val searchItem: MenuItem = menu?.findItem(R.id.actionSearch)!!

        val searchView: SearchView = searchItem.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchByName(query.orEmpty())
                return false;
            }
            override fun onQueryTextChange(newText: String?): Boolean = false
        })
        return true
    }

    private fun onItemSelect(superheroItem: SuperheroItem) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(EXTRA_SUPERHERO_ID, superheroItem.id)
        startActivity(intent)
    }

    private fun searchByName (name: String) {
        binding.progressBar.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {
            val response = superheroesService.findSuperheroesByName(name)

            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null) {
                    Log.i("Superheroes", responseBody.toString())
                    runOnUiThread {
                        adapterSuperhero.updateSuperheroes(responseBody.superheros)
                        binding.progressBar.isVisible = false
                    }
                }
            }
        }
    }

}