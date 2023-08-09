package com.megamendhie.streamon.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.megamendhie.core.data.models.Movie
import com.megamendhie.streamon.BuildConfig
import com.megamendhie.streamon.R
import com.megamendhie.streamon.databinding.FragmentHomeBinding
import com.megamendhie.streamon.presentation.adapters.MoviesAdapter
import com.megamendhie.streamon.presentation.viewmodels.MoviesViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    @Inject
    lateinit var viewModel: MoviesViewModel
    private lateinit var token: String
    private val adapterPopularMovies = MoviesAdapter()
    private val adapterTrendingMovies = MoviesAdapter()
    private val adapterDiscoverMovies = MoviesAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.fragment = this

        //setup layout manager and adapter for popular movies
        binding.lstPopularMovies.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.lstPopularMovies.adapter = adapterPopularMovies

        //setup layout manager and adapter for trending movies
        binding.lstTrendingMovies.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.lstTrendingMovies.adapter = adapterTrendingMovies

        //setup layout manager and adapter for discover movies
        binding.lstDiscoverMovies.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.lstDiscoverMovies.adapter = adapterDiscoverMovies

        viewModel.trendingMovies.observe(viewLifecycleOwner){ movieList ->
            val movies = movieList.map { Movie(
                id = it.id, title = it.title, posterPath = it.posterPath, releaseDate = it.releaseDate,
                voteAverage = it.voteAverage, overview = it.overview, popularity = it.popularity,
                adult = it.adult, genreIds = it.genreIds, backdropPath = it.backdropPath
            ) }
            adapterTrendingMovies.updateMovies(movies)
        }
        viewModel.popularMovies.observe(viewLifecycleOwner){ movieList ->
            val movies = movieList.map { Movie(
                id = it.id, title = it.title, posterPath = it.posterPath, releaseDate = it.releaseDate,
                voteAverage = it.voteAverage, overview = it.overview, popularity = it.popularity,
                adult = it.adult, genreIds = it.genreIds, backdropPath = it.backdropPath
            ) }
            adapterPopularMovies.updateMovies(movies)
        }
        viewModel.discoverMovies.observe(viewLifecycleOwner){ movieList ->
            val movies = movieList.map { Movie(
                id = it.id, title = it.title, posterPath = it.posterPath, releaseDate = it.releaseDate,
                voteAverage = it.voteAverage, overview = it.overview, popularity = it.popularity,
                adult = it.adult, genreIds = it.genreIds, backdropPath = it.backdropPath
            ) }
            adapterDiscoverMovies.updateMovies(movies)
        }

        //retrieve TMDB token from buildConfig
        token = BuildConfig.TMDB_TOKEN

        fetchMovies()
        return binding.root
    }

    private fun fetchMovies() {
        //get popular movies
        viewModel.getPopularMovies(token)

        //get trending movies
        viewModel.getTrendingMovies(token)

        //get discovered movies
        viewModel.getDiscoveredMovies(token)

        //get favorite movies
        viewModel.getFavoriteMovies(token)
    }

    fun seeAllClick(view: View){
        Toast.makeText(requireContext(), "Not necessary in this version", Toast.LENGTH_SHORT).show()
    }
}