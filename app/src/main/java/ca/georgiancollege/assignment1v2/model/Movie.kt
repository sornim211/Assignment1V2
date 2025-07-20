package ca.georgiancollege.assignment1v2.model

import com.google.gson.annotations.SerializedName

// Model for individual movie details
data class Movie(
    @SerializedName("Title")
    val title: String,

    @SerializedName("Year")
    val year: String,

    @SerializedName("Rated")
    val rated: String,

    @SerializedName("Released")
    val released: String,

    @SerializedName("Runtime")
    val runtime: String,

    @SerializedName("Genre")
    val genre: String,

    @SerializedName("Director")
    val director: String,

    @SerializedName("Writer")
    val writer: String,

    @SerializedName("Actors")
    val actors: String,

    @SerializedName("Plot")
    val plot: String,

    @SerializedName("Poster")
    val poster: String,

    @SerializedName("imdbRating")
    val imdbRating: String,

    @SerializedName("Production")
    val production: String
)

// Model for search results
data class MovieSearchResponse(
    val Search: List<Movie>?,
    val totalResults: String?,
    val Response: String,
    val Error: String? = null
)