package com.dicoding.movielist

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.core.content.ContextCompat
import com.dicoding.movielist.databinding.ActivityDetailMoviesItemBinding
import com.dicoding.movielist.databinding.ItemMoviesBinding

class DetailMoviesItem : AppCompatActivity() {

    private lateinit var binding: ActivityDetailMoviesItemBinding

    companion object{
        const val MOVIES = "MOVIE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMoviesItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movie = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Movie>(MOVIES, Movie::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Movie>(MOVIES)
        }

        Log.d("Detail Data", movie?.title.toString())
        if(movie != null) {
            binding.detailTitleMovie.text = movie.title.toString()
            binding.detailItem.text = movie.description.toString()
            binding.imageView2.setImageResource(movie.photo)
            binding.genre.text = movie.genre.toString()
            binding.duration.text = movie.duration.toString()
            binding.star.text = movie.rating.toString()
        }

        binding.actionShare.setOnClickListener {
            shareDesc()
        }
        supportActionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(this, R.color.dark_blue)))

    }

    private fun shareDesc() {
        val ShareDesc = Intent(Intent.ACTION_SEND)
        ShareDesc.putExtra("CONTENT", binding.detailItem.text)
        startActivity(Intent.createChooser(ShareDesc, "Share With"))
    }

}
