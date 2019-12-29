package com.rizalfahrudin.submissionaplikasimoviecatalogue.view.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.rizalfahrudin.submissionaplikasimoviecatalogue.R
import com.rizalfahrudin.submissionaplikasimoviecatalogue.adapter.MovieAdapter
import com.rizalfahrudin.submissionaplikasimoviecatalogue.model.Movie
import com.rizalfahrudin.submissionaplikasimoviecatalogue.view.MovieDetail
import kotlinx.android.synthetic.main.fragment_tv_show.*

class TvShowFragment : Fragment() {
    private var list = ArrayList<Movie>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list.addAll(getDataMovies())
        showRecycleList()

    }
    private fun getDataMovies():ArrayList<Movie> {
        val dataTitle = resources.getStringArray(R.array.data_title_tv_show)
        val dataDesc = resources.getStringArray(R.array.data_description_tv_show)
        val dataPhoto = resources.obtainTypedArray(R.array.data_image_tv_show)
        val dataDate = resources.getStringArray(R.array.data_date_tv_show)
        val dataGenres = resources.getStringArray(R.array.data_genres_tv_show)
        val dataTime = resources.getStringArray(R.array.data_runtime_tv_show)


        val list = ArrayList<Movie>()

        for (position in dataTitle.indices) {
            val movie =
                Movie(
                    dataTitle[position],
                    dataDate[position],
                    dataGenres[position],
                    dataTime[position],
                    dataDesc[position],
                    dataPhoto.getResourceId(position, -1)
                )
            list.add(movie)
        }
        dataPhoto.recycle()
        return list
    }

    private fun showRecycleList() {
        rv_tv_show.layoutManager = LinearLayoutManager(context)
        val listMovieAdapter =
            MovieAdapter(
                list
            )
        rv_tv_show.adapter = listMovieAdapter

        listMovieAdapter.setOnItemClickCallback(object: MovieAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Movie) {
                val mIntent = Intent(activity, MovieDetail::class.java)
                mIntent.putExtra(MovieDetail.EXTRA_MOVIES, data)
                startActivity(mIntent)
            }
        })

    }

}
