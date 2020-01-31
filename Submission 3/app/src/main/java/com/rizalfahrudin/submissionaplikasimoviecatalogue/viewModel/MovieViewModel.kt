package com.rizalfahrudin.submissionaplikasimoviecatalogue.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import com.rizalfahrudin.submissionaplikasimoviecatalogue.model.MovieAndTv
import cz.msebera.android.httpclient.Header
import org.json.JSONObject

class MovieViewModel : ViewModel() {

    companion object {
        private const val API_KEY = "6cde11bfb58b4abdad0d7ed93b8b2bab"
    }

    val listMovieAndTv = MutableLiveData<ArrayList<MovieAndTv>>()

    internal fun getMovieAndTv(): LiveData<ArrayList<MovieAndTv>> = listMovieAndTv

    internal fun setMovieAndTV(type: String, language: String) {
        val client = AsyncHttpClient()
        val listItemMovieAndTv = ArrayList<MovieAndTv>()

        val url = "https://api.themoviedb.org/3/discover/$type?api_key=$API_KEY&language=$language"

        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray
            ) {
                try {
                    val result = String(responseBody)
                    val responseObject = JSONObject(result)
                    val list = responseObject.getJSONArray("results")

                    for (i in 0 until list.length()) {
                        when (type) {
                            "movie" -> {
                                val movie = list.getJSONObject(i)
                                val items = MovieAndTv(
                                    movie.getInt("id"),
                                    movie.getString("original_title"),
                                    movie.getString("release_date"),
                                    movie.getString("poster_path"),
                                    movie.getString("overview")
                                )
                                listItemMovieAndTv.add(items)
                                listMovieAndTv.postValue(listItemMovieAndTv)
                            }

                            "tv" -> {
                                val tv = list.getJSONObject(i)
                                val items = MovieAndTv(
                                    tv.getInt("id"),
                                    tv.getString("name"),
                                    tv.getString("first_air_date"),
                                    tv.getString("poster_path"),
                                    tv.getString("overview")
                                )
                                listItemMovieAndTv.add(items)
                                listMovieAndTv.postValue(listItemMovieAndTv)
                            }
                        }
                    }
                } catch (e: Exception) {
                    Log.d("Exception", e.message.toString())
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Array<out Header>?,
                responseBody: ByteArray?,
                error: Throwable
            ) {
                Log.d("onFailure", error.message.toString())
            }

        })
    }


}