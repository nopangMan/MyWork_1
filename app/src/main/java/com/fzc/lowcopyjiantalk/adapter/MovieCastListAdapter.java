package com.fzc.lowcopyjiantalk.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fzc.lowcopyjiantalk.R;
import com.fzc.lowcopyjiantalk.entity.MovieCast;

import java.util.List;

/**
 * 项目名：LowCopyJianTalk
 * 包名：com.fzc.lowcopyjiantalk.adapter
 * 文件名：MovieCastListAdapter
 * 创建者：fzc
 * 创建日期：2018/5/28 17:32
 * 描述
 */

public class MovieCastListAdapter extends BaseAdapter {


    private List<MovieCast> mList ;

    private Context mContext;

    public MovieCastListAdapter(Context context, List<MovieCast> casts) {

        this.mContext = context;

        this.mList = casts;

    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null){

            viewHolder = new ViewHolder();

            convertView = View.inflate(mContext, R.layout.find_list_item,null);

            viewHolder.tv_cast_name = convertView.findViewById(R.id.tv_movie_cast_name);

            viewHolder.iv_cast_img = convertView.findViewById(R.id.iv_movie_cast_img);

            viewHolder.tv_cast_name.setText(mList.get(position).getCastName());

            Glide.with(mContext).load(mList.get(position).getCastImgUrl()).centerCrop().into(viewHolder.iv_cast_img);

            convertView.setTag(viewHolder);

        }else {
            viewHolder = (ViewHolder) convertView.getTag();

            viewHolder.tv_cast_name.setText(mList.get(position).getCastName());

            Glide.with(mContext).load(mList.get(position).getCastImgUrl()).centerCrop().into(viewHolder.iv_cast_img);

        }

        return convertView;
    }

    class ViewHolder{

        TextView tv_cast_name;

        ImageView iv_cast_img;

    }
}
