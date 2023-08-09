package com.megamendhie.streamon.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.megamendhie.streamon.BuildConfig
import com.megamendhie.streamon.databinding.FragmentMovieDetailBinding
import com.megamendhie.streamon.presentation.viewmodels.MovieDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {
    private lateinit var binding: FragmentMovieDetailBinding
    private val args: MovieDetailFragmentArgs by navArgs()
    private lateinit var token: String
    @Inject
    lateinit var viewModel: MovieDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        val actionBar = (requireActivity() as AppCompatActivity).supportActionBar
        actionBar?.title = ""
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }
        val movie = args.movie
        val movieType = args.type

        viewModel.trendingMovie.postValue(movie)

        /*if (movie.tagline == null) {
            //retrieve TMDB token from buildConfig
            token = BuildConfig.TMDB_TOKEN

            viewModel.getMovieDetails(token, movie.id.toInt(), movieType).observe(viewLifecycleOwner){
                viewModel.trendingMovie.postValue(it)

        }
*/
        return binding.root
    }
}