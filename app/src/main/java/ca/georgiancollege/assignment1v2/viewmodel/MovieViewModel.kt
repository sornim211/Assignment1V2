package ca.georgiancollege.assignment1v2.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.georgiancollege.assignment1v2.model.Movie
import ca.georgiancollege.assignment1v2.utils.OmdbHttpClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {

    val movies = MutableLiveData<List<Movie>?>()
    val selectedMovie = MutableLiveData<Movie>()
    val error = MutableLiveData<String>()

    // Search for movies by query
    fun searchMovies(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = OmdbHttpClient.searchMovies(query)
                if (result?.Response == "True" && !result.Search.isNullOrEmpty()) {
                    movies.postValue(result.Search)
                } else {
                    error.postValue(result?.Error ?: "No results found")
                }
            } catch (e: Exception) {
                error.postValue("Search error: ${e.message}")
            }
        }
    }

    // Get full movie details by title
    fun getMovieDetails(title: String, string: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val detail = OmdbHttpClient.getMovieDetails(title)
                if (detail != null) {
                    selectedMovie.postValue(detail)
                } else {
                    error.postValue("Movie details not found.")
                }
            } catch (e: Exception) {
                error.postValue("Details error: ${e.message}")
            }
        }
    }
}