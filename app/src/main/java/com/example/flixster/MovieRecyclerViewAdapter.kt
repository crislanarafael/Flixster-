package com.example.flixster

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flixster.R.id

class MovieRecyclerViewAdapter(
    private val movies: List<Movie>,
            private val mListener: OnListFragmentInteractionListener?) : RecyclerView.Adapter<MovieRecyclerViewAdapter.MovieViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_movie, parent, false)
        return MovieViewHolder(view)
    }

    inner class MovieViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        var mItem: Movie? = null
        val mMovieTitle: TextView = mView.findViewById<View>(id.movie_title) as TextView
        val mMovieDescription: TextView = mView.findViewById<View>(id.movie_description) as TextView
        val mMovieImage : ImageView = mView.findViewById<View>(id.movie_image) as ImageView
        override fun toString(): String {
            return mMovieTitle.toString() + " '" + mMovieDescription.text + "'"
        }
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val book = movies[position]

        holder.mItem = book
        holder.mMovieTitle.text = book.title
        holder.mMovieDescription.text = book.description

        Glide.with(holder.mView)
            .load("https://image.tmdb.org/t/p/w500/"+book.movieImageUrl)
            .centerInside()
            .into(holder.mMovieImage)


        holder.mView.setOnClickListener {
            holder.mItem?.let { book ->
                mListener?.onItemClick(book)
            }
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}