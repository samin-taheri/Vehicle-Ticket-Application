package com.example.homepage;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
public class announcementAdapter extends RecyclerView.Adapter<announcementAdapter.MyViewHolder> {
    private List<announcementDraft> announcementList;
    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView announcement;
        MyViewHolder(View view) {
            super(view);
            announcement = view.findViewById(R.id.announcementText);
        }
    }
    public announcementAdapter(List<announcementDraft> announcementList) {
        this.announcementList = announcementList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_announcementlist, parent, false);
        return new MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        announcementDraft announcement = announcementList.get(position);
        holder.announcement.setText(announcement.getAnnouncement());
    }
    @Override
    public int getItemCount() {
        return announcementList.size();
    }
}
