package ca.georgiancollege.assignment1v2.view

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import ca.georgiancollege.assignment1v2.databinding.MovieItemBinding
import ca.georgiancollege.assignment1v2.model.Movie
import com.bumptech.glide.Glide

// ViewHolder for binding movie data
class MyViewHolder(private val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root) {

    // Binds movie info to layout
    fun bind(movie: Movie, onItemClick: (Movie) -> Unit) {
        binding.textMovieTitle.text = movie.title   // Set title
        binding.textMovieYear.text = movie.year     // Set year

        Log.d("POSTER_DEBUG", "URL: ${movie.poster}")

        // Load poster if available
        if (!movie.poster.isNullOrBlank() && movie.poster != "N/A") {
            Glide.with(binding.root.context)
                .load(movie.poster)
                .placeholder(android.R.color.darker_gray)
                .error(android.R.color.holo_red_light)
                .into(binding.imagePoster)
        } else {
            // Fallback image
            binding.imagePoster.setImageResource(android.R.color.darker_gray)
        }

        // Handle item click
        binding.root.setOnClickListener {
            onItemClick(movie)
        }
    }
}
