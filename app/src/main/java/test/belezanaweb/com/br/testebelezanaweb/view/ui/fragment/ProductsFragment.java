package test.belezanaweb.com.br.testebelezanaweb.view.ui.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.meuapt.testemeuapt.service.SchedulerProvider;

import java.util.List;

import test.belezanaweb.com.br.testebelezanaweb.R;
import test.belezanaweb.com.br.testebelezanaweb.model.Product;
import test.belezanaweb.com.br.testebelezanaweb.model.service.BelezaApiService;
import test.belezanaweb.com.br.testebelezanaweb.presenter.ProductsPresenter;
import test.belezanaweb.com.br.testebelezanaweb.view.adapter.ProductRecyclerViewAdapter;
import test.belezanaweb.com.br.testebelezanaweb.view.contract.ProductsView;
import test.belezanaweb.com.br.testebelezanaweb.view.ui.activity.MainActivity;

public class ProductsFragment extends Fragment implements ProductsView {

    private static final Integer SIZE = 3;
    private static final Integer START_PAGE = 1;
    private Integer currentPage;

    private RecyclerView recyclerView;
    private ProductRecyclerViewAdapter adapter;
    private ProductsPresenter presenter;
    private ProgressDialog dialog;

    private List<Product> products;

    public static ProductsFragment newInstance() {
        return new ProductsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_products, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MainActivity activity = ((MainActivity) getActivity());

        if (activity.getSupportActionBar() != null) {
            activity.getSupportActionBar().setTitle(R.string.products);
            activity.getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            activity.getSupportActionBar().setDisplayShowHomeEnabled(false);
        }

        initView(view);

        presenter = new ProductsPresenter(this, new SchedulerProvider(), new BelezaApiService().getApi());
        currentPage = START_PAGE;
        presenter.loadProducts(currentPage, SIZE);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
        if (products == null || products.size() == 0){
            presenter.loadProducts(currentPage, SIZE);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.onStop();
    }

    @Override
    public void showLoading() {
        if (dialog == null || !dialog.isShowing()) {
            dialog = ProgressDialog.show(getActivity(), getString(R.string.products), getString(R.string.products_loading));
        }
    }

    @Override
    public void hideLoading() {
        dialog.dismiss();
    }

    @Override
    public void showProducts(final List<Product> products) {
        this.products = products;

        adapter = new ProductRecyclerViewAdapter(products);
        adapter.setOnItemClickListener((view, position) -> {
            final Product product = products.get(position);
            getActivity().getFragmentManager().beginTransaction().replace(R.id.fragment, ProductDetailFragment.newInstance(product)).addToBackStack(MainActivity.TAG_PRODUCTS).commit();
        });

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void updateProducts(List<Product> products) {
        if (products.size() > 0){
            currentPage = currentPage + 1;
            adapter.updateData(products);
        }
    }

    @Override
    public void showError() {
        Toast.makeText(getActivity().getApplicationContext(), R.string.products_error, Toast.LENGTH_LONG).show();
    }

    void initView(View view) {
        recyclerView = view.findViewById(R.id.shots);
        Button bLoadMore = view.findViewById(R.id.load_more);
        bLoadMore.setOnClickListener(view1 -> {
            presenter.loadProducts(currentPage + 1, SIZE);
        });
    }
}
