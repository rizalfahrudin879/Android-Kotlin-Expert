package com.rizalfahrudin.submissionaplikasimoviecatalogue

import android.content.Intent
import android.content.res.TypedArray
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ListView

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: MovieAdapter
    private lateinit var dataTitle: Array<String>
    private lateinit var dataDate: Array<String>
    private lateinit var dataGenres: Array<String>
    private lateinit var dataRuntime: Array<String>
    private lateinit var dataDescription: Array<String>
    private lateinit var dataPhoto: TypedArray
    private var movies = arrayListOf<Movie>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val actionBar = supportActionBar
        actionBar!!.title = "Movies List"


        val listView: ListView = findViewById(R.id.tv_list)
        adapter = MovieAdapter(this)


        listView.adapter = adapter
        prepare()
        addItem()

        listView.onItemClickListener = AdapterView.OnItemClickListener {_,_,position,_ ->

            val dataMovies = Movie(
                movies[position].title,
                movies[position].date,
                movies[position].genres,
                movies[position].runtime,
                movies[position].description,
                movies[position].image
            )

            val moveToMovieDetail = Intent(this@MainActivity, MovieDetail::class.java)
            moveToMovieDetail.putExtra(MovieDetail.EXTRA_MOVIES, dataMovies)
            startActivity(moveToMovieDetail)
        }

    }

    private fun prepare() {
        dataTitle = resources.getStringArray(R.array.data_title)
        dataDate = resources.getStringArray(R.array.data_date)
        dataGenres = resources.getStringArray(R.array.data_genres)
        dataRuntime = resources.getStringArray(R.array.data_runtime)
        dataDescription = resources.getStringArray(R.array.data_description)
        dataPhoto = resources.obtainTypedArray(R.array.data_image)
    }

    private fun addItem() {
        for (position in dataTitle.indices) {
            val movie = Movie(
                dataTitle[position],
                dataDate[position],
                dataGenres[position],
                dataRuntime[position],
                dataDescription[position],
                dataPhoto.getResourceId(position, -1)
            )
            movies.add(movie)
        }
        adapter.movies = movies
    }
}

