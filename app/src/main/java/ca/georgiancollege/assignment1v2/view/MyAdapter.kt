package ca.georgiancollege.assignment1v2.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ca.georgiancollege.assignment1v2.databinding.MovieItemBinding
import ca.georgiancollege.assignment1v2.model.MovieSummary

class MyAdapter(
    private val movies: List<MovieSummary>,                 // Use MovieSummary for search results
    private val onItemClick: (MovieSummary) -> Unit         // Callback with MovieSummary
) : RecyclerView.Adapter<MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MovieItemBinding.inflate(inflater, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(movies[position], onItemClick)
    }

    override fun getItemCount() = movies.size
}
