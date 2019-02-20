package com.example.oskin.lesson_17_clean_architecture_dagger_2.Presentation.UI;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.Domain.Entity.DTO.ForecastDTOOutput;
import com.example.oskin.lesson_17_clean_architecture_dagger_2.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ForecastAdapter extends RecyclerView.Adapter {

    private List<ForecastDTOOutput.Day> mForecastDayList;
    private View.OnClickListener mOnClickListener;
    private Context mContext;

    public ForecastAdapter(View.OnClickListener onClickListener, Context context) {
        mForecastDayList = new ArrayList<>();
        mOnClickListener = onClickListener;
        mContext = context;
    }

    /*public ForecastAdapter(AppCompatActivity activity) {
        mForecastDayList = new ArrayList<>();
        mOnClickListener = (View.OnClickListener) activity;
        mContext = activity;
    }*/

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
        forecastHolder.temp.setText(mContext.getResources().getString(
                R.string.min_max_temperature,
                String.valueOf(mForecastDayList.get(position).getMinTemp()),
                String.valueOf(mForecastDayList.get(position).getMaxTemp())));
        forecastHolder.textCondition.setText(mForecastDayList.get(position).getConditionText());

        //Not work on emulator
        String url = "http:" + mForecastDayList.get(position).getConditionImgUrl();
        Glide.with(forecastHolder.itemView)
                .load(url)
                .into(forecastHolder.imgCondition);

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
        public ImageView imgCondition;

        public ForecastHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(mOnClickListener);

            date = itemView.findViewById(R.id.item_forecast_date);
            day = itemView.findViewById(R.id.item_forecast_day);
            temp = itemView.findViewById(R.id.item_forecast_temp);
            textCondition = itemView.findViewById(R.id.item_forecast_condition_text);
            imgCondition = itemView.findViewById(R.id.item_forecast_condition_image);
        }
    }
}
