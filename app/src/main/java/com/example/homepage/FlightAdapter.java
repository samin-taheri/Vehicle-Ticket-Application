package com.example.homepage;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class FlightAdapter extends ArrayAdapter<FlightDraft> implements View.OnClickListener {
        private ArrayList<FlightDraft> flightList;
        Context mContext;

        private static class ViewHolder {
            TextView startCity;
            TextView startTime;
            TextView date;
            TextView endCity;
            TextView arrivalTime;
            TextView ticketPrice;
            TextView company_name;
            ImageView rightArrow;
        }

        public FlightAdapter(ArrayList<FlightDraft> data, Context context){
            super(context, R.layout.flight_list,data);
            this.flightList = data;
            this.mContext = context;

        }

        @Override
        public void onClick(View v) {

        }

        private int lastPosition = -1;

    @SuppressLint("ResourceType")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        FlightDraft flightDraft = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.flight_list, parent, false);
            viewHolder.startCity = (TextView) convertView.findViewById(R.id.startCity);
            viewHolder.startTime = (TextView) convertView.findViewById(R.id.startTime);
            viewHolder.date = (TextView) convertView.findViewById(R.id.flight_date);
            viewHolder.endCity = (TextView) convertView.findViewById(R.id.endCity);
            viewHolder.arrivalTime = (TextView) convertView.findViewById(R.id.arrivalTime);
            viewHolder.ticketPrice = (TextView) convertView.findViewById(R.id.flight_price);
            viewHolder.company_name = (TextView) convertView.findViewById(R.id.company_name);
            viewHolder.rightArrow = (ImageView) convertView.findViewById(R.id.imageView5);
            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;

        viewHolder.startCity.setText(flightDraft.getStartCity());
        viewHolder.startTime.setText(flightDraft.getStartTime());
        viewHolder.date.setText(flightDraft.getDate());
        viewHolder.endCity.setText(flightDraft.getEndCity());
        viewHolder.arrivalTime.setText(flightDraft.getArrivalTime());
        viewHolder.ticketPrice.setText(flightDraft.getTicketPrice());
        viewHolder.company_name.setText(flightDraft.getCompany_name());
        viewHolder.rightArrow.setImageResource(R.drawable.arrow_right);
        // Return the completed view to render on screen
        return convertView;
    }
}

