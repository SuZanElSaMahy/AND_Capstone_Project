
package com.suzanelsamahy.vidviewer.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.suzanelsamahy.vidviewer.R;
import com.suzanelsamahy.vidviewer.channel.Search;

import java.util.List;


public class SearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Search> searchItemsList;
    private Context context;



    public SearchAdapter(List<Search> searchItemsList, Context context, OnChannelItemClickListener listener) {
        this.searchItemsList = searchItemsList;
        this.context = context;
        this.onChannelItemClickListener = listener;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.item_video, parent, false);

        return new MovieViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final Search movie = searchItemsList.get(position);
        MovieViewHolder itemViewHolder = (MovieViewHolder) holder;
        itemViewHolder.title.setText(movie.getSnippet().getTitle());

        if(movie.getSnippet().getThumbnails().getMedium().getUrl()!=null){
            Picasso.get().load(movie.getSnippet().getThumbnails().getMedium().getUrl())
            .error(R.drawable.place_holder_youtube).placeholder(R.drawable.place_holder_youtube).into(itemViewHolder.image);
            itemViewHolder.image.setContentDescription(movie.getSnippet().getTitle());
        }
        itemViewHolder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onChannelItemClickListener.onChannelItemClicked(movie, position);
                //   listener.openMovieDetails(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return searchItemsList.size();
    }


    public class MovieViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        ImageView image;

        public MovieViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.tv_video_title);
            image = (ImageView) view.findViewById(R.id.iv_video);
        }
    }


    public void updateMovies(List<Search> movies) {
        this.searchItemsList.clear();
        this.searchItemsList.addAll(movies);
        notifyDataSetChanged();
    }


    public interface OnChannelItemClickListener {
        void onChannelItemClicked(Search item , int position);
    }

    private OnChannelItemClickListener onChannelItemClickListener;
}

