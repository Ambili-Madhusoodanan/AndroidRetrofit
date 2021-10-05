package com.capgemini.androidretrofit

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.bumptech.glide.Glide
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieDetailActivity : AppCompatActivity() {

    lateinit var titleTextView: TextView
    lateinit var tagLineTextView: TextView
    lateinit var releasDateTextView: TextView
    lateinit var runTimeTextView: TextView
    lateinit var statusTextView: TextView
    lateinit var overView: TextView
    lateinit var revenue:TextView
    lateinit var ratingBar: RatingBar
    lateinit var imageView: ImageView
    lateinit var faButton:FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        supportActionBar?.hide()

        titleTextView=findViewById(R.id.titleD)
        tagLineTextView=findViewById(R.id.tagD)
        releasDateTextView=findViewById(R.id.releaseD)
        runTimeTextView=findViewById(R.id.timeD)
        statusTextView=findViewById(R.id.statusD)
        overView=findViewById(R.id.overViewD)
        revenue=findViewById(R.id.revenueD)
        ratingBar=findViewById(R.id.ratingBar2)
        imageView=findViewById(R.id.imageD)
        faButton=findViewById(R.id.fab)

        val movieId = intent.getIntExtra("id",0)

        if(movieId !=0){

            //execute the request

            val key = resources.getString(R.string.apiKey)

            TmdbInterface.getInstance().getMovieDetails(movieId,key).enqueue(MovieDetailsCallback())
        }
    }

    inner class MovieDetailsCallback : Callback<MovieDetails>{
        override fun onResponse(call: Call<MovieDetails>, response: Response<MovieDetails>) {

            if(response.isSuccessful) {

                val details = response.body()
                Toast.makeText(
                    this@MovieDetailActivity,
                    "Got Details : ${details?.title}",
                    Toast.LENGTH_LONG
                ).show()

                Log.d("MovieDetailsActivity", "Movie : $details")

                titleTextView.text = details?.title
                tagLineTextView.text = details?.tagline
                releasDateTextView.text = details?.release_date
                runTimeTextView.text = details?.runtime.toString()
                statusTextView.text = details?.status
                overView.text = details?.overview
                revenue.text = details?.revenue.toString()
                ratingBar.rating = details?.vote_average!!.toFloat()


                val imageUrl = "https://image.tmdb.org/t/p/w500${details.poster_path}"


                val imageUri = Uri.parse(imageUrl)

                Glide.with(this@MovieDetailActivity).load(imageUri).into(imageView)

                faButton.setOnClickListener {
                    val i = Intent(Intent.ACTION_VIEW, Uri.parse(details?.homepage))
                    startActivity(i)
                }
            }



            else{
                Toast.makeText(this@MovieDetailActivity,"Problem fetching details ...", Toast.LENGTH_LONG).show()
            }

        }

        override fun onFailure(call: Call<MovieDetails>, t: Throwable) {
            Toast.makeText(this@MovieDetailActivity,"Some error ...", Toast.LENGTH_LONG).show()
        }

    }


}