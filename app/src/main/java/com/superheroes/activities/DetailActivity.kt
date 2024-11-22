package com.superheroes.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import com.google.android.material.tabs.TabLayout
import com.squareup.picasso.Picasso
import com.superheroes.R
import com.superheroes.data.SuperheroDetailsResponse
import com.superheroes.databinding.ActivityDetailBinding
import com.superheroes.databinding.ActivityMainBinding
import com.superheroes.services.SuperheroService
import com.superheroes.utils.RetrofitProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {

    private lateinit var superheroesService: SuperheroService
    private lateinit var binding: ActivityDetailBinding
    private lateinit var tabLayout:TabLayout


    companion object {
        const val EXTRA_SUPERHERO_ID = "SUPERHERO_ID"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityDetailBinding.inflate(layoutInflater)

        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        init()

        superheroesService =   RetrofitProvider.getRetrofit()

        val id = intent.getStringExtra(EXTRA_SUPERHERO_ID).orEmpty()
        getDataSuperhero(id)
        println(id)
    }

    private fun init () {
        binding.tabSuperhero
        tabLayout = findViewById(R.id.tabSuperhero)

        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {
                // Handle tab select
                print(tab)

                if (tab?.position == 1)
                    tab.setContentDescription("xxdasdasdas")
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                // Handle tab reselect
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                // Handle tab unselect
            }
        })
    }



    private fun getDataSuperhero (id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = superheroesService.findSuperheroesById(id)

            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null) {
                    Log.i("Superheroe", responseBody.toString())

                    runOnUiThread {
                        createDetails(responseBody)
                    }
                }
            }
        }
    }

    private fun createDetails (supehero: SuperheroDetailsResponse) {
        Picasso.get().load(supehero.superheroImage.url).into(binding.imgSuperheroDetail)
        binding.superheroName.text = supehero.name
    }
}