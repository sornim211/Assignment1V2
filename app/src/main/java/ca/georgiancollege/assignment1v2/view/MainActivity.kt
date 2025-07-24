package ca.georgiancollege.assignment1v2.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ca.georgiancollege.assignment1v2.databinding.ActivityMainBinding
import ca.georgiancollege.assignment1v2.viewmodel.MovieViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MovieViewModel by viewModels()
    private val apiKey = "b1adca2"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        binding.buttonSearch.setOnClickListener {
            val query = binding.editTextSearch.text.toString().trim()
            if (query.isNotEmpty()) {
                viewModel.searchMovies(apiKey, query)
            } else {
                Toast.makeText(this, "Enter a movie title", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.movies.observe(this) { movieList ->
            if (movieList.isNullOrEmpty()) {
                Toast.makeText(this, "No results found", Toast.LENGTH_SHORT).show()
                binding.recyclerView.adapter = null
            } else {
                Log.d("OMDB_RESPONSE", "Loaded ${movieList.size} movies")
                binding.recyclerView.adapter = MyAdapter(movieList) { movie ->
                    val intent = Intent(this, MovieDetailsActivity::class.java)
                    intent.putExtra("MOVIE_TITLE", movie.title)
                    startActivity(intent)
                }
            }
        }

        viewModel.error.observe(this) { error ->
            error?.let {
                Toast.makeText(this, it, Toast.LENGTH_LONG).show()
                Log.e("OMDB_ERROR", it)
            }
        }
    }
}
