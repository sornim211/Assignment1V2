package ca.georgiancollege.assignment1v2.model

// Importing Gson for JSON mapping
import com.google.gson.annotations.SerializedName

// Model for individual movie details
data class Movie(
    @SerializedName("Title")
    val title: String,              // Movie name

    @SerializedName("Year")
    val year: String,               // Release year

    @SerializedName("Rated")
    val rated: String,              // Age rating

    @SerializedName("Released")
    val released: String,           // Release date

    @SerializedName("Runtime")
    val runtime: String,            // Duration

    @SerializedName("Genre")
    val genre: String,              // Genre type

    @SerializedName("Director")
    val director: String,           // Director info

    @SerializedName("Writer")
    val writer: String,             // Writer info

    @SerializedName("Actors")
    val actors: String,             // Main cast

    @SerializedName("Plot")
    val plot: String,               // Summary

    @SerializedName("Poster")
    val poster: String,             // Poster URL

    @SerializedName("imdbRating")
    val imdbRating: String,         // IMDb score

    @SerializedName("Production")
    val production: String          // Studio name
)

// Model for search result from API
data class MovieSearchResponse(
    val Search: List<Movie>?,       // Found movies
    val totalResults: String?,      // Count of results
    val Response: String,           // API response flag
    val Error: String? = null       // Error text if failed
)

