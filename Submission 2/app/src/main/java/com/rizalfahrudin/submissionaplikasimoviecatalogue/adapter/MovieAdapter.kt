package com.rizalfahrudin.submissionaplikasimoviecatalogue.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rizalfahrudin.submissionaplikasimoviecatalogue.model.Movie
import com.rizalfahrudin.submissionaplikasimoviecatalogue.R
import kotlinx.android.synthetic.main.item.view.*

class MovieAdapter(private val listMovie: ArrayList<Movie>): RecyclerView.Adapter<MovieAdapter.ListViewHolder>(){

    private var onItemClickCallback: OnItemClickCallback? = null

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Movie)
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(movie: Movie) {
            with(itemView) {
                tv_title_movie.text = movie.title
                tv_description_movie.text = movie.description
                img_poster.setImageResource(movie.image!!)

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

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listMovie[position])
    }

}