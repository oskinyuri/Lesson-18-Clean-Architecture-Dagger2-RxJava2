package com.example.oskin.lesson_15_clean_architecture.Presentation.UI;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.WeatherModel.ForecastDay;
import com.example.oskin.lesson_15_clean_architecture.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ForecastAdapter extends RecyclerView.Adapter {

    private List<ForecastDay> mForecastDayList;

    public void setData(List<ForecastDay> forecastDayList){
        mForecastDayList = forecastDayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mForecastDayList.size();
    }

    public class ForecastHolder extends RecyclerView.ViewHolder{

        public TextView date;
        public TextView day;
        public TextView temp;
        public TextView textCondition;
        public TextView imgCondition;

        public ForecastHolder(@NonNull View itemView) {
            super(itemView);

            date = itemView.findViewById(R.id.item_forecast_date);
            day = itemView.findViewById(R.id.item_forecast_day);
            temp = itemView.findViewById(R.id.item_forecast_temp);
            textCondition = itemView.findViewById(R.id.item_forecast_condition_text);
            imgCondition = itemView.findViewById(R.id.item_forecast_condition_image);
        }
    }
}
