package ca.georgiancollege.assignment1v2

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import ca.georgiancollege.assignment1v2.databinding.ActivityMainBinding
import kotlin.toString

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(R.layout.activity_main)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        //Search button function
        binding.buttonSearch.setOnClickListener {
            val query = binding.editTextSearch.text.toString().trim()

        }

    }
}