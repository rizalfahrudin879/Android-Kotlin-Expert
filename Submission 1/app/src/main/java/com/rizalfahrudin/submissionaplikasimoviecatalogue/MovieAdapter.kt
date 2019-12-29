package com.rizalfahrudin.submissionaplikasimoviecatalogue

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class MovieAdapter internal constructor(private val context: Context): BaseAdapter() {

    internal var movies = arrayListOf<Movie>()

    override fun getItem(i: Int): Any = movies[i]

    override fun getItemId(i: Int): Long = i.toLong()

    override fun getCount(): Int = movies.size


    override fun getView(position: Int, view: View?, viewGroup: ViewGroup): View {
        var itemView = view
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.item, viewGroup, false)
        }

        val viewHolder = ViewHolder(itemView as View)

        val movie = getItem(position) as Movie
        viewHolder.bind(movie)
        return itemView
    }

    private inner class ViewHolder constructor(view: View) {
        private val title: TextView = view.findViewById(R.id.tv_title_movie)
        private val description: TextView = view.findViewById(R.id.tv_description_movie)
        private val image: ImageView = view.findViewById(R.id.img_poster)

        internal fun bind(movie: Movie) {
            title.text = movie.title
            description.text = movie.description
            image.setImageResource((movie.image)!!.toInt())
        }

    }


}