package com.example.oskin.lesson_15_clean_architecture.Presentation.UI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.oskin.lesson_15_clean_architecture.Domain.Entity.DTO.ForecastDTOOutput;
import com.example.oskin.lesson_15_clean_architecture.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ForecastAdapter extends RecyclerView.Adapter {

    private List<ForecastDTOOutput.Day> mForecastDayList;
    private View.OnClickListener mOnClickListener;

    public ForecastAdapter(View.OnClickListener onClickListener) {
        mForecastDayList = new ArrayList<>();
        mOnClickListener = onClickListener;
    }

    public void setData(List<ForecastDTOOutput.Day> forecastDayList){
        mForecastDayList = forecastDayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_forecast,parent,false);
        return new ForecastHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ForecastHolder forecastHolder = (ForecastHolder) holder;
        forecastHolder.date.setText(mForecastDayList.get(position).getDate());
        forecastHolder.day.setText(mForecastDayList.get(position).getDayOfWeek());
        forecastHolder.temp.setText(String.valueOf(mForecastDayList.get(position).getMinTemp()));
        forecastHolder.textCondition.setText(mForecastDayList.get(position).getConditionText());
    }

    @Override
    public int getItemCount() {
        return mForecastDayList.size();
    }

    public class ForecastHolder extends RecyclerView.ViewHolder{

        //TODO load image

        public TextView date;
        public TextView day;
        public TextView temp;
        public TextView textCondition;
        //public TextView imgCondition;

        public ForecastHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(mOnClickListener);

            date = itemView.findViewById(R.id.item_forecast_date);
            day = itemView.findViewById(R.id.item_forecast_day);
            temp = itemView.findViewById(R.id.item_forecast_temp);
            textCondition = itemView.findViewById(R.id.item_forecast_condition_text);
            //imgCondition = itemView.findViewById(R.id.item_forecast_condition_image);
        }
    }
}
