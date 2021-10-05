package com.capgemini.androidretrofit

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MovieAdapter(val mlist: List<Movie>, val selectionCallback: (Movie) -> Unit): RecyclerView.Adapter<MovieAdapter.MovieHolder>() {

    class MovieHolder(v: View): RecyclerView.ViewHolder(v){
        val posterIv = v.findViewById<ImageView>(R.id.imageView)
        val titleTextView = v.findViewById<TextView>(R.id.titleT)
        val overviewTextView = v.findViewById<TextView>(R.id.overViewT)
        val rDateTextView = v.findViewById<TextView>(R.id.releaseT)
        val ratinBar = v.findViewById<RatingBar>(R.id.ratingBar)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieAdapter.MovieHolder {
        val v =LayoutInflater.from(parent.context).inflate(R.layout.movie_item,parent
            ,false)
        return  MovieHolder(v)
    }

    override fun onBindViewHolder(holder: MovieAdapter.MovieHolder, position: Int) {
        val movie = mlist[position]

        holder.titleTextView.text = movie.title
        holder.overviewTextView.text = movie.overview
        holder.rDateTextView.text = movie.release_date
        holder.ratinBar.rating = movie.vote_average.toFloat()

        val imageUrl = "https://image.tmdb.org/t/p/w500${movie.poster_path}"


        val imageUri= Uri.parse(imageUrl)

        Glide.with(holder.itemView).load(imageUrl).into(holder.posterIv)

        holder.itemView.setOnClickListener{
            selectionCallback(movie)
        }

    }
    override fun getItemCount() = mlist.size
}