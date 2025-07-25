package ca.georgiancollege.assignment1v2.model

import com.google.gson.annotations.SerializedName

// basic search results
data class MovieSummary(
    @SerializedName("Title") val title: String,
    @SerializedName("Year") val year: String,
    @SerializedName("imdbID") val imdbID: String,
    @SerializedName("Type") val type: String,
    @SerializedName("Poster") val poster: String
)

// Full movie details
data class MovieDetails(
    @SerializedName("Title") val title: String,
    @SerializedName("Year") val year: String,
    @SerializedName("Rated") val rated: String,
    @SerializedName("Released") val released: String,
    @SerializedName("Runtime") val runtime: String,
    @SerializedName("Genre") val genre: String,
    @SerializedName("Director") val director: String,
    @SerializedName("Writer") val writer: String,
    @SerializedName("Actors") val actors: String,
    @SerializedName("Plot") val plot: String,
    @SerializedName("Poster") val poster: String,
    @SerializedName("imdbRating") val imdbRating: String,
    @SerializedName("Production") val production: String
)

data class MovieSearchResponse(
    @SerializedName("Search") val search: List<MovieSummary>?,
    @SerializedName("totalResults") val totalResults: String?,
    @SerializedName("Response") val response: String,
    @SerializedName("Error") val error: String? = null
)

