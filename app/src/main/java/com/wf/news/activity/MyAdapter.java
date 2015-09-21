package com.wf.news.activity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wf.news.R;
import com.wf.news.bean.NewsListItemBean;

import java.util.ArrayList;

/**
 * Created by Kesson on 2015/9/20.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private Context context;
    private ArrayList<NewsListItemBean> mapList;

    public MyAdapter(Context context, ArrayList<NewsListItemBean> mapList){
        this.context = context;
        this.mapList = mapList;
    }

    // Provide a reference to the type of views that you are using
    // (custom viewholder)
    public static class ViewHolder extends RecyclerView.ViewHolder {

        View itemView;

        ImageView NewsItemImage;
        TextView Title;
        TextView Abstract;
        TextView NewsTimestamp;
        TextView NewsLabel;

        public ViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
        }

    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(ArrayList<NewsListItemBean> mapList) {
        this.mapList = mapList;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_news, parent, false);
        // set the view's size, margins, paddings and layout parameters
        //ButterKnife.bind(this, v);
        ViewHolder vh = new ViewHolder(v);
        vh.NewsItemImage = (ImageView)v.findViewById(R.id.NewsItemImage);
        vh.Title = (TextView)v.findViewById(R.id.Title);
        vh.Abstract = (TextView)v.findViewById(R.id.Abstract);
        vh.NewsTimestamp = (TextView)v.findViewById(R.id.NewsTimestamp);
        vh.NewsLabel = (TextView)v.findViewById(R.id.NewsLabel);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        //Picasso.with(context).load((String)mapList.get(position).get("Image")).placeholder(R.drawable.placeholder).into(holder.NewsItemImage);
        Log.i("onBindViewHolder", "position: " + position);
        holder.Title.setText(mapList.get(position).getTitle());
        holder.Abstract.setText(mapList.get(position).getAbstract_());
        holder.NewsTimestamp.setText(mapList.get(position).getDate());
        holder.NewsLabel.setText(mapList.get(position).getLabel());
        final int p = position;
        holder.NewsItemImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "position: " + p, Toast.LENGTH_SHORT).show();
            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mapList.size();
    }
}
