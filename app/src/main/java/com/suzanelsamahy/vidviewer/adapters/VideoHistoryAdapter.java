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
import com.suzanelsamahy.vidviewer.database.HistoryModel;

import java.util.List;

public class VideoHistoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<HistoryModel> channelItemsList;
    private Context context;


    public VideoHistoryAdapter(List<HistoryModel> channelItemsList, Context context, OnChannelItemClickListener listener) {
        this.channelItemsList = channelItemsList;
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
        final HistoryModel movie = channelItemsList.get(position);
        MovieViewHolder itemViewHolder = (MovieViewHolder) holder;
        itemViewHolder.title.setText(movie.getVideo_title());

        if (movie.getThumbnail_url() != null) {
            Picasso.get().load(movie.getThumbnail_url()).into(itemViewHolder.image);
            itemViewHolder.image.setContentDescription(movie.getVideo_title());
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


    public void updateMovies(List<HistoryModel> movies) {
        this.channelItemsList.clear();
        this.channelItemsList.addAll(movies);
        notifyDataSetChanged();
    }


    public interface OnChannelItemClickListener {
        void onChannelItemClicked(HistoryModel item, int position);
    }

    private OnChannelItemClickListener onChannelItemClickListener;

    public OnChannelItemClickListener getOnItemClickListener() {
        return onChannelItemClickListener;
    }

    public void setOnChannelItemClickListener(OnChannelItemClickListener onItemClickListener) {
        this.onChannelItemClickListener = onItemClickListener;
    }
}
