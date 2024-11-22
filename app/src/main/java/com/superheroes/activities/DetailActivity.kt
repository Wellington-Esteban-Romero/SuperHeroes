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

        superheroesService =   RetrofitProvider.getRetrofit()

        val id = intent.getStringExtra(EXTRA_SUPERHERO_ID).orEmpty()
        getDataSuperhero(id)
        println(id)
    }

    private fun getDataSuperhero (id: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val response = superheroesService.findSuperheroesById(id)

            if (response.isSuccessful) {
                val responseBody = response.body()
                if (responseBody != null) {
                    Log.i("Superheroe", responseBody.toString())

                    runOnUiThread {

                    }

                }
            }
        }
    }

    private fun createUI (supehero: SuperheroDetailsResponse) {

    }
}