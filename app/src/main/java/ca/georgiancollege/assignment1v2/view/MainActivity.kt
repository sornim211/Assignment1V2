package ca.georgiancollege.assignment1v2.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import ca.georgiancollege.assignment1v2.R
import ca.georgiancollege.assignment1v2.databinding.ActivityMainBinding

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