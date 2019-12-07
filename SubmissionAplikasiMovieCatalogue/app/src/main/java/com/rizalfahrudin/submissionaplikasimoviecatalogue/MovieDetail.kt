package com.rizalfahrudin.submissionaplikasimoviecatalogue

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import org.w3c.dom.Text

class MovieDetail : AppCompatActivity() {
    companion object {
        const val EXTRA_MOVIES = "extra_movies"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val actionBar = supportActionBar
        actionBar!!.title = "Movie Detail"
        actionBar.setDisplayHomeAsUpEnabled(true)

        val tvTitle: TextView = findViewById(R.id.tv_content_title)
        val tvDate: TextView = findViewById(R.id.tv_content_release)
        val tvGenres: TextView = findViewById(R.id.tv_content_genres)
        val tvRuntime: TextView = findViewById(R.id.tv_content_runtime)
        val tvDesc: TextView = findViewById(R.id.tv_content_description)
        val imgPoster: ImageView = findViewById(R.id.img_detail_poster)

        val dataMoviesDetail = intent.getParcelableExtra(EXTRA_MOVIES) as Movie

        tvTitle.text = dataMoviesDetail.title
        tvDate.text = dataMoviesDetail.date
        tvGenres.text = dataMoviesDetail.genres
        tvRuntime.text = dataMoviesDetail.runtime
        tvDesc.text = dataMoviesDetail.description
        imgPoster.setImageResource(dataMoviesDetail.image!!)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
