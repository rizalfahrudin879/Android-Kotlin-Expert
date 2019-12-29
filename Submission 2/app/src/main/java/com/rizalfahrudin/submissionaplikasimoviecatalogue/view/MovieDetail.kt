package com.rizalfahrudin.submissionaplikasimoviecatalogue.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rizalfahrudin.submissionaplikasimoviecatalogue.R
import com.rizalfahrudin.submissionaplikasimoviecatalogue.model.Movie
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetail : AppCompatActivity() {
    companion object {
        const val EXTRA_MOVIES = "extra_movies"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        supportActionBar?.title = resources.getString(R.string.app_bar_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val dataMoviesDetail = intent.getParcelableExtra(EXTRA_MOVIES) as Movie

        tv_content_title.text = dataMoviesDetail.title
        tv_content_release.text = dataMoviesDetail.date
        tv_content_genres.text = dataMoviesDetail.genres
        tv_content_runtime.text = dataMoviesDetail.runtime
        tv_content_description.text = dataMoviesDetail.description
        img_detail_poster.setImageResource(dataMoviesDetail.image!!)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
