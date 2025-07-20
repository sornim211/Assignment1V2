package ca.georgiancollege.assignment1v2.utils

// Retrofit for API requests
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

// Data classes
import ca.georgiancollege.assignment1v2.model.Movie
import ca.georgiancollege.assignment1v2.model.MovieSearchResponse

// Retrofit API interface for OMDB
interface MovieApi {

    @GET("/")
    suspend fun searchMovies(
        @Query("apikey") apiKey: String, // API key
        @Query("s") query: String        // Search term
    ): MovieSearchResponse              // Search results

    @GET("/")
    suspend fun getMovieDetails(
        @Query("apikey") apiKey: String, // API key
        @Query("t") title: String        // Title param
    ): Movie                             // Full movie data
}

// Singleton object to build Retrofit client
object ApiClient {
    private const val BASE_URL = "https://www.omdbapi.com/" // API base

    val movieApi: MovieApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)                                // Set base URL
            .addConverterFactory(GsonConverterFactory.create()) // JSON converter
            .build()
            .create(MovieApi::class.java)                     // Interface setup
    }
}
