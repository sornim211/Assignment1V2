package ca.georgiancollege.assignment1v2.view

// Required Android & app imports
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import ca.georgiancollege.assignment1v2.databinding.ActivityMovieDetailsBinding
import ca.georgiancollege.assignment1v2.viewmodel.MovieViewModel

// Shows details of a selected movie
class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailsBinding     // View binding
    private val viewModel: MovieViewModel by viewModels()         // ViewModel
    private val apiKey = "8c568b95"                               // OMDB key

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = intent.getStringExtra("MOVIE_TITLE") // Get movie title

        if (title.isNullOrEmpty()) {
            Toast.makeText(this, "No title found", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        viewModel.getMovieDetails(apiKey, title) // Fetch details

        // Observe movie data
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

        // Observe errors
        viewModel.error.observe(this) { msg ->
            msg?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
            }
        }

        // Back button
        binding.buttonBack.setOnClickListener {
            finish()
        }
    }
}
