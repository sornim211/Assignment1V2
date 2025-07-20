package ca.georgiancollege.assignment1v2.utils

import android.util.Log
import com.google.gson.Gson
import ca.georgiancollege.assignment1v2.model.Movie
import ca.georgiancollege.assignment1v2.model.MovieSearchResponse
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

object OmdbHttpClient {
    private const val BASE_URL = "https://www.omdbapi.com/"
    private const val API_KEY = "b1adca2" // Api key

    fun searchMovies(query: String): MovieSearchResponse? {
        val urlString = "$BASE_URL?s=$query&apikey=$API_KEY"
        return makeRequest(urlString, MovieSearchResponse::class.java)
    }

    fun getMovieDetails(title: String): Movie? {
        val urlString = "$BASE_URL?t=$title&apikey=$API_KEY"
        return makeRequest(urlString, Movie::class.java)
    }

    private fun <T> makeRequest(urlString: String, clazz: Class<T>): T? {
        return try {
            val url = URL(urlString)
            val conn = url.openConnection() as HttpURLConnection
            conn.requestMethod = "GET"

            val reader = BufferedReader(InputStreamReader(conn.inputStream))
            val response = reader.readText()
            reader.close()

            Gson().fromJson(response, clazz)
        } catch (e: Exception) {
            Log.e("HTTP_ERROR", "Request failed: ${e.message}", e)
            null
        }
    }
}