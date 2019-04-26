package com.currencyconversion.ui.home;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.currencyconversion.R;

public class HomeViewHolder extends RecyclerView.ViewHolder {
    TextView tvFlag;
    TextView tvCurrency;
    CardView mCardView;

    HomeViewHolder(View itemView) {
        super(itemView);
        tvFlag = itemView.findViewById(R.id.tvFlag);
        tvCurrency = itemView.findViewById(R.id.tvCurrency);
        mCardView = itemView.findViewById(R.id.cardview);
    }
}