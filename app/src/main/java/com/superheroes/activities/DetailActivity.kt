package com.superheroes.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.superheroes.R
import com.superheroes.databinding.ActivityMainBinding

class DetailActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_SUPERHERO_ID = "SUPERHERO_ID"
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var btn_back:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(R.layout.activity_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        init()

        val id = intent.getStringExtra(EXTRA_SUPERHERO_ID)!!
        println(id)
    }

    private fun init () {
        btn_back = findViewById(R.id.btn_back)

        btn_back.setOnClickListener {
            startActivity(Intent(baseContext, MainActivity::class.java))
        }
    }
}