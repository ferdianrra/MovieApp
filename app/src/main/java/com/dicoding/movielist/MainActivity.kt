package com.dicoding.movielist

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.movielist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val list = ArrayList<Movie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvMovies.setHasFixedSize(true)
        

        list.addAll(getListMovies())
        showRecycleList()
        supportActionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(this, R.color.dark_blue)))


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.profile, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.about_page-> {
                val moveIntent = Intent(this@MainActivity, profilePages::class.java)
                startActivity(moveIntent)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showRecycleList() {
        binding.rvMovies.layoutManager = LinearLayoutManager(this)
        val listMovieAdapter = ListMovieAdapter(list)
        binding.rvMovies.adapter = listMovieAdapter

        listMovieAdapter.setOnItemClickCallback(object : ListMovieAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Movie) {
                val intentToDetail = Intent(this@MainActivity, DetailMoviesItem::class.java)
                intentToDetail.putExtra("MOVIE", data)
                startActivity(intentToDetail)
            }
        })
    }

    private fun getListMovies(): ArrayList<Movie> {
        val movieName = resources.getStringArray(R.array.movie_name)
        val movieDesc = resources.getStringArray(R.array.movie_desc)
        val movieCover = resources.obtainTypedArray(R.array.movie_cover)
        val movieRating = resources.getStringArray(R.array.movie_rating)
        val movieDuration = resources.getStringArray(R.array.movie_duration)
        val movieGenre = resources.getStringArray(R.array.movie_genre)
        val listMovies = ArrayList<Movie>()

        for (x in movieName.indices) {
            val movie = Movie(movieName[x], movieDesc[x], movieCover.getResourceId(x, -1),
                movieRating[x], movieDuration[x], movieGenre[x])
            listMovies.add(movie)
        }
        return listMovies
    }
}