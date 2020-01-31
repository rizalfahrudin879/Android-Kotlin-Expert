package com.rizalfahrudin.submissionaplikasimoviecatalogue.view.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rizalfahrudin.submissionaplikasimoviecatalogue.R
import com.rizalfahrudin.submissionaplikasimoviecatalogue.adapter.MovieAdapter
import com.rizalfahrudin.submissionaplikasimoviecatalogue.model.MovieAndTv
import com.rizalfahrudin.submissionaplikasimoviecatalogue.view.MovieDetail
import com.rizalfahrudin.submissionaplikasimoviecatalogue.viewModel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_movie.*

class MovieFragment : Fragment() {
    private lateinit var movieViewModel: MovieViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(MovieViewModel::class.java)
        showLoading(true)
        movieViewModel.setMovieAndTV("movie", getString(R.string.language))
        movieViewModel.getMovieAndTv().observe(this.viewLifecycleOwner, Observer { items ->
            if (items != null) {
                showRecycleList(items)
                showLoading(false)
            }
        })
    }

    private fun showRecycleList(list: ArrayList<MovieAndTv>) {
        val listMovieAdapter = MovieAdapter(list)

        rv_movie.layoutManager = LinearLayoutManager(context)
        rv_movie.adapter = listMovieAdapter
        listMovieAdapter.setOnItemClickCallback(object : MovieAdapter.OnItemClickCallback {
            override fun onItemClicked(data: MovieAndTv) {
                val mIntent = Intent(activity, MovieDetail::class.java)
                mIntent.putExtra(MovieDetail.EXTRA_MOVIES, data)
                startActivity(mIntent)
            }
        })
    }

    private fun showLoading(state: Boolean) {
        if (state) progressBar.visibility = View.VISIBLE
        else progressBar.visibility = View.GONE
    }

}
