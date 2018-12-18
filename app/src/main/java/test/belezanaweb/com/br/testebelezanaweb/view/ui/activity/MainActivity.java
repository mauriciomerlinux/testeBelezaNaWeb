package test.belezanaweb.com.br.testebelezanaweb.view.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import test.belezanaweb.com.br.testebelezanaweb.R;
import test.belezanaweb.com.br.testebelezanaweb.view.ui.fragment.ProductsFragment;

public class MainActivity extends AppCompatActivity {

    public static final String TAG_PRODUCTS = "products";
    public static final String TAG_PRODUCT_DETAILS = "productDetail";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getFragmentManager().beginTransaction().add(R.id.fragment, ProductsFragment.newInstance(), TAG_PRODUCTS).commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }
}
