package ca.georgiancollege.assignment1v2.view

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ca.georgiancollege.assignment1v2.databinding.MovieItemBinding
import ca.georgiancollege.assignment1v2.model.MovieSummary

class MyViewHolder(private val binding: MovieItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: MovieSummary, onItemClick: (MovieSummary) -> Unit) {
        binding.textMovieTitle.text = movie.title
        binding.textMovieYear.text = movie.year

        Log.d("POSTER_DEBUG", "URL: ${movie.poster}")

        if (!movie.poster.isNullOrBlank() && movie.poster != "N/A") {
            Glide.with(binding.root.context)
                .load(movie.poster)
                .placeholder(android.R.color.darker_gray)
                .error(android.R.color.holo_red_light)
                .into(binding.imagePoster)
        } else {
            binding.imagePoster.setImageResource(android.R.color.darker_gray)
        }

        binding.root.setOnClickListener {
            onItemClick(movie)
        }
    }
}
