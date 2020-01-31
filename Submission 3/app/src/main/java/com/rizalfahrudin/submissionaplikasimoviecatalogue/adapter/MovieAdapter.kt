package com.rizalfahrudin.submissionaplikasimoviecatalogue.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rizalfahrudin.submissionaplikasimoviecatalogue.R
import com.rizalfahrudin.submissionaplikasimoviecatalogue.model.MovieAndTv
import kotlinx.android.synthetic.main.item.view.*

class MovieAdapter(private val listMovie: ArrayList<MovieAndTv>) :
    RecyclerView.Adapter<MovieAdapter.ListViewHolder>() {

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: MovieAndTv)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: MovieAndTv) {
            with(itemView) {
                tv_title_movie.text = movie.title
                tv_description_movie.text = movie.description
                Glide.with(context)
                    .load("https://image.tmdb.org/t/p/w342" + movie.image)
                    .into(img_poster)

                itemView.setOnClickListener {
                    onItemClickCallback?.onItemClicked(movie)
                }
            }
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.item, viewGroup, false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int = listMovie.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) =
        holder.bind(listMovie[position])
}