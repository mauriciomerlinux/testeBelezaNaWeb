package test.belezanaweb.com.br.testebelezanaweb.view.contract;

import java.util.List;

import test.belezanaweb.com.br.testebelezanaweb.model.Product;

public interface ProductsView {

    void showLoading();

    void hideLoading();

    void showProducts(List<Product> products);

    void showError();
}
