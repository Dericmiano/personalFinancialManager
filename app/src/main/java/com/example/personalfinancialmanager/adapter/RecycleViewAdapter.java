package com.example.personalfinancialmanager.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.personalfinancialmanager.R;
import com.example.personalfinancialmanager.model.Spend;
import com.example.personalfinancialmanager.utils.Util;
import com.google.android.material.chip.Chip;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {
    private final List<Spend> spendList;
    private final OnTodoClickListener todoClickListener;

    public RecycleViewAdapter(List<Spend> spendList, OnTodoClickListener onTodoClickListener) {
        this.spendList = spendList;
        this.todoClickListener = onTodoClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_page_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Spend spend = spendList.get(position);


        int total = (spend.getFood()+spend.getGf() + spend.getAirtime() + spend.getCharity()+
                spend.getEntertainment()+spend.getFee()+spend.getOtherNeed());
        int save = (spend.getTarget() - total);
        int[] arr = {spend.getFood(),spend.getGf(),spend.getAirtime(),spend.getCharity(),
        spend.getEntertainment(),spend.getOtherNeed(),spend.getFee()};
        int max = Arrays.stream(arr).max().getAsInt();
        int min = Arrays.stream(arr).min().getAsInt();




        holder.daysTarget.setText(String.valueOf(spend.getTarget()));
        holder.daysSave.setText(String.valueOf(save));
        holder.daysUsage.setText(String.valueOf(total));
        holder.top1Spend.setText(String.valueOf(max));
        holder.top2Spend.setText(String.valueOf(min));
        String formatted = Util.formatDate(spend.dateToday);
        holder.todayDate.setText(formatted);
//        holder.todayDate.setText(String.valueOf(spend.dateToday));
        Log.d("max", "onBindViewHolder: "+max);

        Log.d("dater", "onBindViewHolder: "+spend.dateToday);
//        Log.d("dater", "onBindViewHolder: "+formatted.toString());

    }

    @Override
    public int getItemCount() {
        return spendList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public AppCompatTextView todayDate;
        public TextView daysTarget;
        public TextView daysUsage;
        public TextView daysSave;
        public TextView top1Spend;
        public TextView top2Spend;
        public TextView top1Spend_cash;
        public TextView top2Spend_cash;
        public Chip addSpendButton;
        public RadioButton radioButton;

        OnTodoClickListener onTodoClickListener;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            todayDate = itemView.findViewById(R.id.home_date_textView);
            daysTarget = itemView.findViewById(R.id.home_days_target);
            daysUsage = itemView.findViewById(R.id.home_usage_cash);
            daysSave = itemView.findViewById(R.id.home_save_cash);
            top1Spend = itemView.findViewById(R.id.home_top1_spend_cash);
            top2Spend = itemView.findViewById(R.id.home_top2_spend_cash);
            top1Spend_cash = itemView.findViewById(R.id.home_top1_spend_name);
            top2Spend_cash = itemView.findViewById(R.id.home_top2_spend_name);
            addSpendButton = itemView.findViewById(R.id.home_chip_addSpending);
            radioButton = itemView.findViewById(R.id.radioButton);

            this.onTodoClickListener = todoClickListener;

            addSpendButton.setOnClickListener(this);
            radioButton.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            Spend currSpend;
            int id = v.getId();
            if (id == R.id.home_chip_addSpending){
                currSpend = spendList.get(getAdapterPosition());
                onTodoClickListener.onTodoClick(currSpend);
            }else if (id == R.id.radioButton){
                currSpend = spendList.get(getAdapterPosition());
                onTodoClickListener.onTodoRadioButtonClick(currSpend);
            }


        }
    }
}
