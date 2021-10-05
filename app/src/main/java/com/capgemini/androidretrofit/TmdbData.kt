package com.capgemini.androidretrofit

data class Movie(val title:String,
                 val poster_path: String,
                 val overview:String,
                 val id:Int,
                 val release_date:String,
                 val vote_average:Double)

data class PopularMovies(val results:List<Movie>)

data class MovieDetails(val homepage:String,
                        val overview: String,
                        val poster_path: String,
                        val release_date: String,
                        val revenue:Long,
                        val runtime:Int,
                        val status:String,
                        val tagline:String,
                        val title: String,
                        val vote_average: Double)