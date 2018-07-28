
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
import com.suzanelsamahy.vidviewer.playlists.PlayList;

import java.util.List;


public class VideoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<PlayList> channelItemsList;
    private Context context;
    private static int ITEM_TYPE_CHANNEL;
    private static int ITEM_TYPE_PLAYLIST;


    public VideoAdapter(List<PlayList> channelItemsList, Context context, OnPlaylistItemClickListener listener) {
        this.channelItemsList = channelItemsList;
        this.context = context;
        this.onPlaylistItemClickListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.item_video, parent, false);

        return new MovieViewHolder(itemView);
    }


//    @Override
//    public int getItemViewType(int position) {
//        if (channelItemsList.get(position) instanceof Search) {
//            return ITEM_TYPE_CHANNEL;
//        } else {
//            return ITEM_TYPE_PLAYLIST;
//        }
//    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final PlayList movie = channelItemsList.get(position);
        MovieViewHolder itemViewHolder = (MovieViewHolder) holder;
        itemViewHolder.title.setText(movie.getSnippet().getTitle());

        if(movie.getSnippet().getThumbnails().getMedium().getUrl()!=null){
            Picasso.get().load(movie.getSnippet().getThumbnails().getMedium().getUrl()).
            error(R.drawable.place_holder_youtube).placeholder(R.drawable.place_holder_youtube).into(itemViewHolder.image);
            itemViewHolder.image.setContentDescription(movie.getSnippet().getTitle());
        }
        itemViewHolder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onPlaylistItemClickListener.onChannelItemClicked(movie, position);
                //   listener.openMovieDetails(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return channelItemsList.size();
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


    public void updateMovies(List<PlayList> movies) {
        this.channelItemsList.clear();
        this.channelItemsList.addAll(movies);
        notifyDataSetChanged();
    }


    public interface OnPlaylistItemClickListener {
        void onChannelItemClicked(PlayList item , int position);
    }

    private OnPlaylistItemClickListener onPlaylistItemClickListener;

    public OnPlaylistItemClickListener getOnItemClickListener() {
        return onPlaylistItemClickListener;
    }

    public void setOnPlaylistItemClickListener(OnPlaylistItemClickListener onItemClickListener) {
        this.onPlaylistItemClickListener = onItemClickListener;
    }
    
}

