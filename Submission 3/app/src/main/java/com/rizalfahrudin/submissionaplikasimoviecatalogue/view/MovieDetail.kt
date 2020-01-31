package com.rizalfahrudin.submissionaplikasimoviecatalogue.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.rizalfahrudin.submissionaplikasimoviecatalogue.R
import com.rizalfahrudin.submissionaplikasimoviecatalogue.model.MovieAndTv
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

        val dataMoviesDetail = intent.getParcelableExtra(EXTRA_MOVIES) as MovieAndTv

        if (dataMoviesDetail.id == null) {
            showLoading(true)
        } else {
            tv_content_title.text = dataMoviesDetail.title
            tv_content_release.text = dataMoviesDetail.date
            tv_content_description.text = dataMoviesDetail.description

            Glide.with(applicationContext)
                .load("https://image.tmdb.org/t/p/w342" + dataMoviesDetail.image)
                .into(img_detail_poster)
            showLoading(false)
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) progressBar.visibility = View.VISIBLE
        else progressBar.visibility = View.GONE
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
