package test.belezanaweb.com.br.testebelezanaweb.presenter;

import com.meuapt.testemeuapt.service.BaseSchedulerProvider;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;
import test.belezanaweb.com.br.testebelezanaweb.model.Product;
import test.belezanaweb.com.br.testebelezanaweb.model.service.BelezaApiService;
import test.belezanaweb.com.br.testebelezanaweb.view.contract.ProductsView;

public class ProductsPresenter {

    private final BelezaApiService service;
    private final ProductsView view;
    private CompositeSubscription subscriptions;
    private BaseSchedulerProvider scheduler;

    public ProductsPresenter(ProductsView view, BaseSchedulerProvider scheduler, BelezaApiService service) {
        this.view = view;
        this.service = service;
        this.scheduler = scheduler;
        this.subscriptions = new CompositeSubscription();
    }

    public void loadProducts(Integer page, Integer size) {
        view.showLoading();

        this.subscriptions.add(this.service.getApi().getProducts(page, size)
                .subscribeOn(Schedulers.newThread())
                .observeOn(scheduler.ui())
                .subscribe(new Observer<List<Product>>() {
                               @Override
                               public void onCompleted() { }

                               @Override
                               public void onError(Throwable e) {
                                   e.printStackTrace();
                                   view.showError();
                                   view.hideLoading();
                               }

                               @Override
                               public void onNext(List<Product> products) {
                                   view.hideLoading();
                                   if (page > 1) {
                                       view.updateProducts(products);
                                   } else {
                                       view.showProducts(products);
                                   }
                               }
                           }

                ));
    }

    public void onStop() {
        subscriptions.unsubscribe();
        view.hideLoading();
    }

    public void onResume() {
        if (subscriptions.isUnsubscribed()) {
            subscriptions = new CompositeSubscription();
        }
    }
}
