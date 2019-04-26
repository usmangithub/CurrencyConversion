package com.currencyconversion.ui.home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.currencyconversion.R;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeViewHolder> {
    private List<String> mCurrencyList;
    private List<String> mCurrencyKeyList;

    public HomeAdapter(List<String> mCurrencyList) {
        this.mCurrencyList = mCurrencyList;
    }

    public void refreshList(List<String> mCurrencyList, List<String> mCurrencyKeyList) {
        this.mCurrencyList = mCurrencyList;
        this.mCurrencyKeyList = mCurrencyKeyList;
        notifyDataSetChanged();
    }

    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);
        return new HomeViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(final HomeViewHolder holder, int position) {
        holder.tvFlag.setText(localeToEmoji(mCurrencyKeyList.get(position)));
        holder.tvCurrency.setText(mCurrencyList.get(position));
    }

    private String localeToEmoji(String locale) {
        int firstUnicode = Character.codePointAt(locale.substring(3, 5), 0) - 0x41 + 0x1F1E6;
        int secondUnicode = Character.codePointAt(locale.substring(3, 5), 1) - 0x41 + 0x1F1E6;
        return new String(Character.toChars(firstUnicode)) + new String(Character.toChars(secondUnicode));
    }

    @Override
    public int getItemCount() {
        return mCurrencyList.size();
    }
}

