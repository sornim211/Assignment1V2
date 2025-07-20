package ca.georgiancollege.assignment1v2.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ca.georgiancollege.assignment1v2.databinding.MovieItemBinding
import ca.georgiancollege.assignment1v2.model.Movie

// RecyclerView adapter for movie items
class MyAdapter(
    private val movies: List<Movie>,               // Movie data list
    private val onItemClick: (Movie) -> Unit       // Item click callback
) : RecyclerView.Adapter<MyViewHolder>() {

    // Inflate view and return ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MovieItemBinding.inflate(inflater, parent, false)
        return MyViewHolder(binding)
    }

    // Bind movie to the holder
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(movies[position], onItemClick)
    }

    // List size
    override fun getItemCount() = movies.size
}
