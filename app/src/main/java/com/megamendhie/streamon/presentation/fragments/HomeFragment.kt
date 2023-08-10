package com.megamendhie.streamon.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.megamendhie.core.data.models.FavoriteMovie
import com.megamendhie.core.data.models.Movie
import com.megamendhie.streamon.BuildConfig
import com.megamendhie.streamon.R
import com.megamendhie.streamon.databinding.FragmentHomeBinding
import com.megamendhie.streamon.presentation.adapters.MoviesAdapter
import com.megamendhie.streamon.presentation.interfaces.MoviesAdapterInterface
import com.megamendhie.streamon.presentation.viewmodels.MoviesViewModel
import com.megamendhie.streamon.utils.Commons.Companion.POPULAR_MOVIES
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment(), MoviesAdapterInterface {
    private lateinit var binding: FragmentHomeBinding
    @Inject
    lateinit var viewModel: MoviesViewModel
    private lateinit var token: String
    private lateinit var adapterPopularMovies: MoviesAdapter
    private lateinit var  adapterTrendingMovies: MoviesAdapter
    private lateinit var  adapterDiscoverMovies: MoviesAdapter

    private var favoriteMovies: List<Int> = listOf()
    private var popularMovies: List<Movie> = listOf()

    private var SWITCH_STATUS = false


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.fragment = this

        //clear favorite movies response
        viewModel.clearVal()

        //setup layout manager and adapter for popular movies
        adapterPopularMovies = MoviesAdapter(this)
        binding.lstPopularMovies.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.lstPopularMovies.adapter = adapterPopularMovies

        //setup layout manager and adapter for trending movies
        adapterTrendingMovies = MoviesAdapter(this)
        binding.lstTrendingMovies.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.lstTrendingMovies.adapter = adapterTrendingMovies

        //setup layout manager and adapter for discover movies
        adapterDiscoverMovies =  MoviesAdapter(this)
        binding.lstDiscoverMovies.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.lstDiscoverMovies.adapter = adapterDiscoverMovies


        viewModel.favoriteMovies.observe(viewLifecycleOwner){movies->
            favoriteMovies = movies.map { it.id }
            if(SWITCH_STATUS){
                val favMovies = popularMovies.filter { favoriteMovies.contains(it.id) }
                adapterPopularMovies.updateMovies(favMovies, favoriteMovies, POPULAR_MOVIES)
            }
            else{
                adapterPopularMovies.updateMovies(popularMovies, favoriteMovies, POPULAR_MOVIES)
            }
        }
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
            popularMovies = movies
            if(SWITCH_STATUS){
                val favMovies = popularMovies.filter { favoriteMovies.contains(it.id) }
                adapterPopularMovies.updateMovies(favMovies, favoriteMovies, POPULAR_MOVIES)
            }
            else{
                adapterPopularMovies.updateMovies(popularMovies, favoriteMovies, POPULAR_MOVIES)
            }
        }
        viewModel.discoverMovies.observe(viewLifecycleOwner){ movieList ->
            val movies = movieList.map { Movie(
                id = it.id, title = it.title, posterPath = it.posterPath, releaseDate = it.releaseDate,
                voteAverage = it.voteAverage, overview = it.overview, popularity = it.popularity,
                adult = it.adult, genreIds = it.genreIds, backdropPath = it.backdropPath
            ) }
            adapterDiscoverMovies.updateMovies(movies)
        }

        viewModel.addFavoriteMovieResponse.observe(viewLifecycleOwner){responseCode->
            when(responseCode){
                1, 12 -> Snackbar.make(binding.txtPopularMovies, "Added to favorites", Snackbar.LENGTH_SHORT).show()
                13 -> Snackbar.make(binding.txtDiscoverMovies, "Removed from favorites", Snackbar.LENGTH_SHORT).show()
            }
        }

        //retrieve TMDB token from buildConfig
        token = BuildConfig.TMDB_TOKEN

        binding.swtFavorite.setOnCheckedChangeListener { compoundButton, isChecked ->
            if(isChecked==SWITCH_STATUS)
                return@setOnCheckedChangeListener
            SWITCH_STATUS = isChecked
            if(SWITCH_STATUS){
                val favMovies = popularMovies.filter { favoriteMovies.contains(it.id) }
                adapterPopularMovies.updateMovies(favMovies, favoriteMovies, POPULAR_MOVIES)
            }
            else{
                adapterPopularMovies.updateMovies(popularMovies, favoriteMovies, POPULAR_MOVIES)
            }
        }

        fetchMovies()
        return binding.root
    }

    private fun fetchMovies() {
        //get favorite movies
        viewModel.getFavoriteMovies(token)

        //get popular movies
        viewModel.getPopularMovies(token)

        //get trending movies
        viewModel.getTrendingMovies(token)

        //get discovered movies
        viewModel.getDiscoveredMovies(token)
    }

    fun seeAllClick(view: View){
        Toast.makeText(requireContext(), "Not required in this version", Toast.LENGTH_SHORT).show()
    }

    override fun favIconClick(movie: FavoriteMovie) {
        val add = !favoriteMovies.contains(movie.id)
        viewModel.addFavorite(token, movie, add)
    }
}