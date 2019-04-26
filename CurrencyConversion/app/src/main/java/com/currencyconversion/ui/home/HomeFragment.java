package com.currencyconversion.ui.home;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.currencyconversion.BaseFragment;
import com.currencyconversion.BasePresenter;
import com.currencyconversion.R;
import com.currencyconversion.ui.home.di.HomeComponent;
import com.currencyconversion.ui.home.model.CurrencyData;
import com.currencyconversion.ui.home.model.CurrencyName;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Completable;

public class HomeFragment extends BaseFragment implements HomeContract.View {
    public static final String TAG = "HomeFragment";

    private HomeComponent homeComponent;
    private Unbinder unbinder;
    private HomeAdapter mHomeAdapter;
    private ProgressDialog progressDialog;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @BindView(R.id.currency_picker)
    NumberPicker currencyPicker;

    @BindView(R.id.emptyView)
    TextView emptyView;

    @Inject
    HomePresenter presenter;

    protected void inject(HomeComponent menuComponent) {
        menuComponent.inject(this);
    }

    public HomeComponent getCategoryComponent() {
        if (homeComponent == null) {
            homeComponent = HomeComponent.Initializer
                    .init(this, getTestApplication().getApplicationComponent());
        }
        return homeComponent;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        inject(getCategoryComponent());
    }

    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        getActivity().setTitle("Currency Conversion");
        unbinder = ButterKnife.bind(this, view);
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager(layoutManager);
        mHomeAdapter = new HomeAdapter(new ArrayList<>());
        recyclerView.setAdapter(mHomeAdapter);
        presenter.init();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showConversionData(CurrencyData currencyData) {
        List<String> valueList = new ArrayList<>(currencyData.getCurrencyData().values());
        List<String> keyList = new ArrayList<>(currencyData.getCurrencyData().keySet());
        mHomeAdapter.refreshList(valueList, keyList);
        if (!valueList.isEmpty()) {
            emptyView.setVisibility(View.GONE);
        }
    }

    @Override
    public void showCurrencyNameList(CurrencyName list) {
        Set<Map.Entry<String, String>> entries = list.getCurrenciesMap().entrySet();
        Iterator<Map.Entry<String, String>> it = entries.iterator();
        String[] nameList = new String[list.getCurrenciesMap().size()];
        int index = 0;
        while (it.hasNext()) {
            Map.Entry<String, String> e = it.next();
            nameList[index] = e.getKey() + "-" + e.getValue();
            index++;
        }

        currencyPicker.setMinValue(0);
        currencyPicker.setMaxValue(list.getCurrenciesMap().size() - 1);
        currencyPicker.setDisplayedValues(nameList);
        currencyPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);

        NumberPicker.OnScrollListener asd = (numberPicker, scrollState) -> {
            if (scrollState == NumberPicker.OnScrollListener.SCROLL_STATE_IDLE) {
                presenter.doSearch(
                        nameList[numberPicker.getValue()].substring(0,
                                nameList[numberPicker.getValue()].indexOf("-")));
            }
        };
        currencyPicker.setOnScrollListener(asd);
    }

    @Override
    public Completable showLoadingAnimation() {
        progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Loading currency...");
        progressDialog.setIndeterminate(false);
        progressDialog.setCancelable(true);
        progressDialog.show();
        return Completable.complete();
    }

    @Override
    public Completable hideLoadingAnimation() {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        return Completable.complete();
    }
}

