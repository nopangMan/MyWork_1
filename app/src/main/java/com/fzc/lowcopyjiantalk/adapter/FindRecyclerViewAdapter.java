package com.fzc.lowcopyjiantalk.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fzc.lowcopyjiantalk.R;
import com.fzc.lowcopyjiantalk.activity.MovieActivity;
import com.fzc.lowcopyjiantalk.entity.LoadBody;
import com.fzc.lowcopyjiantalk.service.Bean.Movie;
import com.fzc.lowcopyjiantalk.utils.LogUtil;

import java.util.List;

/**
 * 项目名：LowCopyJianTalk
 * 包名：com.fzc.lowcopyjiantalk.adapter
 * 文件名：FindRecyclerViewAdapter
 * 创建者：fzc
 * 创建日期：2018/5/22 20:01
 * 描述
 */

public class FindRecyclerViewAdapter extends RecyclerView.Adapter<FindRecyclerViewAdapter.ViewHolder> {

    private Context mContext;

    private List<LoadBody> mLoadBodyLists;

    private Movie mMovie;

    public FindRecyclerViewAdapter(Context context, List<LoadBody> mlist, Movie movie) {

        LogUtil.i(" ListSize  " + mlist.size());

        this.mMovie = movie;
        this.mContext = context;
        this.mLoadBodyLists = mlist;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(mContext).inflate(R.layout.find_recycler_item, parent, false);
        final ViewHolder holder = new ViewHolder(v);
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Intent intent = new Intent(mContext, MovieActivity.class);
                intent.putExtra("name", mLoadBodyLists.get(position).getMovieName());
                intent.putExtra("imgUrl", mLoadBodyLists.get(position).getImgUrl());
                intent.putExtra("time", position);
                Bundle bundle = new Bundle();
                bundle.putSerializable("movieBody", mMovie);
                intent.putExtras(bundle);
                mContext.startActivity(intent);

            }
        });
        return holder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.fruitName.setText(mLoadBodyLists.get(position).getMovieName());

        Glide.with(mContext)
                .load(mLoadBodyLists.get(position).getImgUrl())
                .crossFade()
                .centerCrop()
                .into(holder.fruitImg);
    }


    @Override
    public int getItemCount() {
        return mLoadBodyLists.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView mCardView;
        ImageView fruitImg;
        TextView fruitName;

        public ViewHolder(View itemView) {
            super(itemView);
            mCardView = (CardView) itemView;
            fruitImg = itemView.findViewById(R.id.fruit_img);
            fruitName = itemView.findViewById(R.id.fruit_name);
        }
    }

}
