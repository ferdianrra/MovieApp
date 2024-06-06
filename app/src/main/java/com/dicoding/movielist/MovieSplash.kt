package com.dicoding.movielist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

@Suppress("DEPRECATION")
class MovieSplash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_splash)
        supportActionBar?.hide()

        Handler().postDelayed({
            val intent = Intent(this@MovieSplash, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 500)

    }
}