package ca.georgiancollege.assignment1v2.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ca.georgiancollege.assignment1v2.model.MovieDetails
import ca.georgiancollege.assignment1v2.model.MovieSummary
import ca.georgiancollege.assignment1v2.utils.ApiClient
import okhttp3.Call
import okhttp3.Callback
import okhttp3.Response
import org.json.JSONObject
import java.io.IOException

class MovieViewModel : ViewModel() {

    val movies = MutableLiveData<List<MovieSummary>?>()
    val selectedMovie = MutableLiveData<MovieDetails>()
    val error = MutableLiveData<String>()

    // Search movies by keyword
    fun searchMovies(apiKey: String, query: String) {
        val url = "https://www.omdbapi.com/?apikey=$apiKey&type=movie&s=${query.replace(" ", "+")}"

        ApiClient.get(url, object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("API_ERROR", "Request failed: ${e.message}", e)
                error.postValue("Network error: ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    response.body?.string()?.let { responseData ->
                        try {
                            val movieList = parseSearchResults(responseData)
                            movies.postValue(movieList)
                        } catch (e: Exception) {
                            Log.e("JSON_ERROR", "Parsing error", e)
                            error.postValue("Data parsing error")
                        }
                    }
                } else {
                    error.postValue("Server error: ${response.message}")
                }
            }
        })
    }

    // Get detailed movie info by title
    fun getMovieDetails(apiKey: String, title: String) {
        val url = "https://www.omdbapi.com/?apikey=$apiKey&t=${title.replace(" ", "+")}"

        ApiClient.get(url, object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                error.postValue("Network error: ${e.message}")
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    response.body?.string()?.let { responseData ->
                        try {
                            val movieDetails = parseMovieDetails(responseData)
                            selectedMovie.postValue(movieDetails)
                        } catch (e: Exception) {
                            error.postValue("Failed to parse movie details")
                        }
                    }
                } else {
                    error.postValue("Server error: ${response.message}")
                }
            }
        })
    }

    // Parse the search results JSON to list of MovieSummary
    private fun parseSearchResults(jsonString: String): List<MovieSummary> {
        val json = JSONObject(jsonString)

        // Check if response is "True"
        if (json.optString("Response") != "True") {
            return emptyList()
        }

        val searchArray = json.optJSONArray("Search") ?: return emptyList()

        val list = mutableListOf<MovieSummary>()
        for (i in 0 until searchArray.length()) {
            val item = searchArray.getJSONObject(i)
            val movie = MovieSummary(
                title = item.getString("Title"),
                year = item.getString("Year"),
                imdbID = item.getString("imdbID"),
                type = item.getString("Type"),
                poster = item.getString("Poster")
            )
            list.add(movie)
        }
        return list
    }

    // Parse the movie details JSON to MovieDetails
    private fun parseMovieDetails(jsonString: String): MovieDetails {
        val json = JSONObject(jsonString)
        return MovieDetails(
            title = json.getString("Title"),
            year = json.getString("Year"),
            rated = json.getString("Rated"),
            released = json.getString("Released"),
            runtime = json.getString("Runtime"),
            genre = json.getString("Genre"),
            director = json.getString("Director"),
            writer = json.getString("Writer"),
            actors = json.getString("Actors"),
            plot = json.getString("Plot"),
            poster = json.getString("Poster"),
            imdbRating = json.getString("imdbRating"),
            production = json.getString("Production")
        )
    }
}
