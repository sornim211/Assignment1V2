package ca.georgiancollege.assignment1v2.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import ca.georgiancollege.assignment1v2.databinding.ActivityMovieDetailsBinding
import ca.georgiancollege.assignment1v2.viewmodel.MovieViewModel
import com.bumptech.glide.Glide

class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailsBinding
    private val viewModel: MovieViewModel by viewModels()
    private val apiKey = "b1adca2" // Use the same key from OmdbHttpClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = intent.getStringExtra("MOVIE_TITLE")

        if (title.isNullOrEmpty()) {
            Toast.makeText(this, "No title found", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        viewModel.getMovieDetails(apiKey, title)

        viewModel.selectedMovie.observe(this) { movie ->
            binding.textTitle.text = movie.title
            binding.textDetails.text =
                "${movie.year} | ${movie.genre}\nDirector: ${movie.director}\nIMDb: ${movie.imdbRating}"

            binding.textExtraDetails.text =
                "Rated: ${movie.rated}\nReleased: ${movie.released}\nLength: ${movie.runtime}\nWriter: ${movie.writer}\nActors: ${movie.actors}\nStudio: ${movie.production}"

            binding.textPlot.text = movie.plot

            Glide.with(this)
                .load(movie.poster)
                .into(binding.imagePoster)
        }

        viewModel.error.observe(this) { msg ->
            Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
        }

        binding.buttonBack.setOnClickListener {
            finish()
        }
    }
}