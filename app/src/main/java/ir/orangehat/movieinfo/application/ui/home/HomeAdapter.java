package ir.orangehat.movieinfo.application.ui.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ir.orangehat.movieinfo.R;
import ir.orangehat.movieinfo.bussines.model.Movie;

/**
 * HomeAdapter
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private Context context;
    private List<Movie> movies;

    public HomeAdapter(Context context, List<Movie> movies) {

        this.context = context;
        this.movies = movies;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.item_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Movie movie = movies.get(position);
        Picasso.with(context)
                .load(movie.getPoster())
                .placeholder(R.drawable.ic_batman)
                .error(R.drawable.ic_batman)
                .into(holder.imageViewPoster);
        holder.textViewTitle.setText(movie.getTitle());
        holder.textViewYear.setText(movie.getYear());
    }

    @Override
    public int getItemCount() {
        return movies == null ? 0 : movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageViewPoster;
        private TextView textViewTitle;
        private TextView textViewYear;

        public ViewHolder(View itemView) {
            super(itemView);
            imageViewPoster = itemView.findViewById(R.id.imageViewPoster);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewYear = itemView.findViewById(R.id.textViewYear);
        }
    }
}
