package test.belezanaweb.com.br.testebelezanaweb;

import org.junit.Test;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import rx.Observable;
import test.belezanaweb.com.br.testebelezanaweb.model.Product;
import test.belezanaweb.com.br.testebelezanaweb.model.service.BelezaApiService;
import test.belezanaweb.com.br.testebelezanaweb.presenter.ProductsPresenter;
import test.belezanaweb.com.br.testebelezanaweb.scheduler.ImmediateSchedulerProvider;
import test.belezanaweb.com.br.testebelezanaweb.view.contract.ProductsView;

@RunWith(MockitoJUnitRunner.class)
public class ProductPresenterTest {

    @Mock
    private ProductsView view;
    @Mock
    private List<Product> products;
    @Mock
    private BelezaApiService.Api api ;

    private ProductsPresenter presenter;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        presenter = new ProductsPresenter(view, new ImmediateSchedulerProvider(), api);
    }

    @Test
    public void testPresenter() throws Exception {
        Mockito.when(api.getProducts(1, 3)).thenReturn(Observable.just(products));
        presenter.loadProducts(1, 3);
        InOrder inOrder = Mockito.inOrder(view);
        inOrder.verify(view).showLoading();
        inOrder.verify(view).showProducts(products);
        inOrder.verify(view).hideLoading();
    }
}