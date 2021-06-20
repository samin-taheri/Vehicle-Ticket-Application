package com.example.homepage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
public class newsAdapter extends RecyclerView.Adapter<newsAdapter.MyViewHolder> {
    private List<NewsDraft> newsList;
    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView newsTitle, date, news;
        MyViewHolder(View view) {
            super(view);
            newsTitle = view.findViewById(R.id.newsTitle);
            news = view.findViewById(R.id.news);
            date = view.findViewById(R.id.date);
        }
    }
    public newsAdapter(List<NewsDraft> newsList) {
        this.newsList = newsList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_list, parent, false);
        return new MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        NewsDraft movie = newsList.get(position);
        holder.newsTitle.setText(movie.getNewsTitle());
        holder.news.setText(movie.getNews());
        holder.date.setText(movie.getDate());
    }
    @Override
    public int getItemCount() {
        return newsList.size();
    }
}
