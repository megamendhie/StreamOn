package com.megamendhie.streamon.presentation.adapters

import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.bumptech.glide.Glide
import com.megamendhie.core.data.models.FavoriteMovie
import com.megamendhie.core.data.models.Movie
import com.megamendhie.streamon.R
import com.megamendhie.streamon.databinding.ItemTrendingMovieBinding
import com.megamendhie.streamon.presentation.fragments.HomeFragmentDirections
import com.megamendhie.streamon.presentation.interfaces.MoviesAdapterInterface
import com.megamendhie.streamon.utils.Commons.Companion.BASE_IMAGE_URL
import com.megamendhie.streamon.utils.Commons.Companion.POPULAR_MOVIES
import com.megamendhie.streamon.utils.Commons.Companion.TRENDING_MOVIES
import com.megamendhie.streamon.utils.Commons.Companion.popularityScore

/**
 * Created by Mendhie on 8/8/2023
 * Copyright (c) 2023 Mendhie Imeh. All rights reserved.
 */
class MoviesAdapter(val listener: MoviesAdapterInterface): Adapter<MoviesAdapter.MoviesViewHolder>(){
    private var movies = listOf<Movie>()
    private var favoriteMovies = listOf<Int>()
    private var type = ""

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bindViews(movies[position])
    }

    override fun getItemCount(): Int = movies.size

    fun updateMovies(movies: List<Movie>, favoriteMovies: List<Int> = listOf(), type: String = ""){
        this.movies = movies
        this.favoriteMovies = favoriteMovies
        this.type = type
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder(
            ItemTrendingMovieBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    inner class MoviesViewHolder(private val binding: ItemTrendingMovieBinding):
        RecyclerView.ViewHolder(binding.root){
            fun bindViews(movie: Movie){
                binding.apply {
                    txtTitle.text = movie.title
                    txtReleaseDate.text = movie.releaseDate
                    txtScore.text = "${popularityScore(movie.voteAverage)}"

                    // Set favorite icon
                    if(type== POPULAR_MOVIES){
                        imgFav.visibility = View.VISIBLE
                        if(favoriteMovies.contains(movie.id))
                            imgFav.setColorFilter(imgFav.context.resources.getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_IN)
                        else
                            imgFav.setColorFilter(imgFav.context.resources.getColor(R.color.colorGrey), PorterDuff.Mode.SRC_IN)
                    }
                    else
                        imgFav.visibility = View.GONE

                    //load image with Glide into imgPoster
                    val posterUrl = BASE_IMAGE_URL + movie.posterPath

                    Glide
                        .with(imgPoster.context)
                        .load(posterUrl)
                        .centerCrop()
                        .placeholder(R.drawable.pic_placeholder)
                        .into(imgPoster)

                    imgFav.setOnClickListener {
                        listener.favIconClick(FavoriteMovie(movie.id, movie.title))
                    }
                }

                binding.root.setOnClickListener {
                    val action = HomeFragmentDirections.actionHomeToMovieDetail(movie, "trending")
                    binding.root.findNavController().navigate(action)
                }
            }
    }
}