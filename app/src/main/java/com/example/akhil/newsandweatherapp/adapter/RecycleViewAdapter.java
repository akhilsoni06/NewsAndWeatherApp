package com.example.akhil.newsandweatherapp.adapter;


import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.akhil.newsandweatherapp.R;
import com.example.akhil.newsandweatherapp.interactor.ItemFavoriteListener;
import com.example.akhil.newsandweatherapp.model.Article;
import com.example.akhil.newsandweatherapp.model.NewsItem;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ItemHolder> {

    private List<Article> mNewsList;
    private ItemFavoriteListener mListener;

    public RecycleViewAdapter(ItemFavoriteListener listener, List<Article> list) {
        this.mNewsList = list;
        this.mListener = listener;
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.news_item_layout, parent, false);
        return new ItemHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
       final Article newsItem = mNewsList.get(position);
        holder.mTitle.setText(newsItem.title);
        holder.mDescription.setText(newsItem.description);
        Picasso.with(holder.itemView.getContext()).load(newsItem.urlToImage).error(holder.itemView.getResources().getDrawable(R.drawable.ic_launcher_background)).into(holder.mItemImage);


        holder.mTxtFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onItemFavoriteClicked(newsItem);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mNewsList.size();
    }

    public static class ItemHolder extends RecyclerView.ViewHolder {
        private TextView mTitle;
        private TextView mDescription;
        private ImageView mItemImage;
        private TextView mTxtFavorite;

        public ItemHolder(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.txt_title);
            mDescription = (TextView) itemView.findViewById(R.id.txt_description);
            mTxtFavorite = (TextView) itemView.findViewById(R.id.txt_bookmark);
            mItemImage = (ImageView) itemView.findViewById(R.id.image);
        }
    }

}
